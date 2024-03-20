package net.fryc.craftingmanipulator.rules;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fryc.craftingmanipulator.network.ModPackets;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public interface CraftingRule {

    /**
     *  Executed only on SERVER, before item appears in result slot.
     *  Replacing an item with other item may collide with other rules (unless you replace it with ItemStack.EMPTY)
     */
    ItemStack modifyCraftedItem(ItemStack craftedItem, PlayerEntity player, World world, ScreenHandler handler, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory);

    /**
     * Executed on both SERVER and CLIENT, after player takes output from result slot.
     * Modifying item doesn't work if player quick moves item from result slot
     */
    void onTakeOutput(ItemStack craftedItem, int amount, PlayerEntity player, World world);

    default boolean isEnabled(){
        return true;
    }

    default boolean isInAppriopriateScreenHandler(ScreenHandler handler){
        return handler.getClass() == CraftingScreenHandler.class || handler.getClass() == PlayerScreenHandler.class;
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
        PacketByteBuf buf = PacketByteBufs.create();
        for(String id : drawingId){
            if(id.isEmpty()) continue;
            buf.writeString(id);
        }
        ServerPlayNetworking.send(player, ModPackets.SEND_INFO_ABOUT_ITEM_MODIFICATION, buf);
    }

    default void drawMouseOverTooltip(ServerPlayerEntity player, Text content, int x, int y, int width, int height){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeText(content);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(width);
        buf.writeInt(height);
        ServerPlayNetworking.send(player, ModPackets.DRAW_MOUSE_OVER_TOOLTIP, buf);
    }

}
