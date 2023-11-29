package net.fryc.craftingmanipulator.rules.oncraft;


import net.fryc.craftingmanipulator.conditions.ConditionsHelper;
import net.fryc.craftingmanipulator.conditions.UnlockConditions;
import net.fryc.craftingmanipulator.rules.AbstractRule;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class OnCraftRules extends AbstractRule {

    @Nullable
    protected TagKey<?> neededThings;
    @Nullable
    private HashSet<?> additionalNeededThings;
    protected int unlockLevel;

    protected static ArrayList<OnCraftRules> onCraftRules = new ArrayList<OnCraftRules>();
    private final ArrayList<Class<? extends ScreenHandler>> selectedScreenHandler = new ArrayList<>();



    /**
     * @param ruleItems - items affected by this rule
     */
    protected OnCraftRules(TagKey<Item> ruleItems){
        super(ruleItems);
        this.neededThings = null;
        this.unlockLevel = 0;
        onCraftRules.add(this);
    }

    /**
     * @param ruleItems - items affected by this rule
     * @param condition - unlock condition: must be properly paired with tag
     * @param neededThings   - ItemTag, BlockTag or BiomeTag: required to enable this OCR
     */
    protected OnCraftRules(TagKey<Item> ruleItems, UnlockConditions condition, @Nullable TagKey<?> neededThings){
        this(ruleItems);
        this.unlockCondition = condition;
        this.neededThings = neededThings;
    }

    /**
     * @param ruleItems - items affected by this rule
     * @param requiredLevel - level required to enable this OCR
     */
    protected OnCraftRules(TagKey<Item> ruleItems, int requiredLevel){
        this(ruleItems);
        this.unlockLevel = requiredLevel;
        this.unlockCondition = UnlockConditions.PLAYER_LEVEL;
    }


    public static ArrayList<OnCraftRules> getOnCraftRules() {
        return onCraftRules;
    }

    public ArrayList<Class<? extends ScreenHandler>> getSelectedScreenHandlers() {
        return selectedScreenHandler;
    }

    @Nullable
    private TagKey<?> getNeededThings() {
        return this.neededThings;
    }

    public int getUnlockLevel(){
        return this.unlockLevel;
    }

    public void setAdditionalNeededThings(@Nullable HashSet<?> additionalNeededThings) {
        this.additionalNeededThings = additionalNeededThings;
    }

    private @NotNull HashSet<?> getAdditionalNeededThings() {
        if(this.additionalNeededThings == null){
            this.additionalNeededThings = new HashSet<>();
        }
        return this.additionalNeededThings;
    }

    public boolean isAdditionalNeededThingsNull() {
        return this.additionalNeededThings == null;
    }


    @Override
    public boolean conditionsAreMet(PlayerEntity player) {
        if(!this.getSelectedScreenHandlers().isEmpty()){
            if(!this.getSelectedScreenHandlers().contains(player.currentScreenHandler.getClass())){
                return false;
            }
        }
        if(this.getUnlockCondition() == UnlockConditions.NONE){
            return true;
        }
        else if(this.getUnlockCondition() == UnlockConditions.PLAYER_LEVEL){
            boolean bl = ConditionsHelper.playerHasLevel(player, this.getUnlockLevel());
            if(this.isReversed()){
                bl = !bl;
            }
            return bl;
        }

        boolean returnValue = false;
        if(this.getNeededThings() != null){
            returnValue = ConditionsHelper.detectAndUnlock(this.getUnlockCondition(), player, this.getNeededThings());
        }
        if(!returnValue && !this.isAdditionalNeededThingsNull()) {
            returnValue = ConditionsHelper.detectAndUnlock(this.getUnlockCondition(), player, this.getAdditionalNeededThings());
        }

        if(this.isReversed()){
            returnValue = !returnValue;
        }
        return returnValue;
    }


    public abstract void apply(World world, PlayerEntity player, ItemStack stack);

    /**
     *  If you want your rule to work on ScreenHandler(-s) other than CraftingScreenHandler, this method have to return false
     *  @return When true, code from "apply(...)" method is executed BEFORE crafting item (when crafting result is shown) and rule only works in Crafting ScreenHandler.
     */
    public abstract boolean canModifyItemStack();

}
