package net.fryc.craftingmanipulator.rules;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fryc.craftingmanipulator.network.payloads.DrawMouseOverTooltipPayload;
import net.fryc.craftingmanipulator.network.payloads.SendInfoAboutDrawingPayload;
import net.fryc.craftingmanipulator.util.StringHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CrafterScreenHandler;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface CraftingRule {

    /**
     *  Executed only on SERVER, before item appears in result slot.
     *  Replacing an item with other item may collide with other rules (unless you replace it with ItemStack.EMPTY)
     */
    ItemStack modifyCraftedItem(ItemStack craftedItem, ServerPlayerEntity player, ServerWorld world, ScreenHandler handler, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory);

    /**
     *  Executed only on SERVER, before item is crafted by Crafter
     *  Replacing an item with other item may collide with other rules (unless you replace it with ItemStack.EMPTY)
     */
    ItemStack modifyItemCrafterIsAboutToCraft(ItemStack craftedItem, ServerWorld world, BlockState crafterState, BlockPos crafterPos);

    /**
     * Executed on both SERVER and CLIENT, after player takes output from result slot.
     * Modifying item doesn't work if player quick moves item from result slot
     */
    void onTakeOutput(ItemStack craftedItem, int amount, PlayerEntity player, World world);

    /**
     * Executed only on SERVER, after Crafter crafts item
     */
    void onCraftByCrafter(ItemStack craftedItem, World world);

    default boolean isEnabled(){
        return true;
    }

    default boolean isInAppriopriateScreenHandler(ScreenHandler handler){
        return handler.getClass() == CraftingScreenHandler.class || handler.getClass() == PlayerScreenHandler.class || handler.getClass() == CrafterScreenHandler.class;
    }

    default void informAboutItemModification(ServerPlayerEntity player){
        this.informAboutItemModification(player, "");
    }

    /**
     *
     * @param player Player using the screen handler
     * @param drawingId Drawings that will be drawn on gui
     */
    default void informAboutItemModification(ServerPlayerEntity player, String... drawingId){
        String oneBigString = StringHelper.convertToOneBigString(':', drawingId);
        ServerPlayNetworking.send(player, new SendInfoAboutDrawingPayload(oneBigString));
    }

    default void drawMouseOverTooltip(ServerPlayerEntity player, Text content, int x, int y, int width, int height){
        ServerPlayNetworking.send(player, new DrawMouseOverTooltipPayload(content, x, y, width, height));
    }

}
