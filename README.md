# CraftingManipulator

Library mod for fabric that lets you easily prevent items from being crafted 
when specified conditions are not met


You can use it as dependency by adding this to you build.gradle file:
```groovy
  repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    modImplementation 'com.github.Xires87:CraftingManipulator:TAG'
}
```
Replace TAG with version you want to use for example:
```groovy
    modImplementation 'com.github.Xires87:CraftingManipulator:1.0.0'
```
You will find available versions in releases

---------------------------------------------------------------------------

# Classes:
Currently, there are four classes:
* RecipeBlockingRules - blocks recipes (forever)
* ItemInInventoryRBR - blocks recipes and unlocks them when player has specified item(-s) in inventory
* StandNearBlockRBR - blocks recipes and unlocks them when player stands near specified block(-s)
* BeOnBiomeRBR - blocks recipes and unlocks them when player is on one of specified biomes

Additionally, all classes appends tooltip to blocked items (optional)

-----------
How to use these classes:

Create new object of the class you want in your main file and... thats all.

Here is explained how to use ItemInInventoryRBR class:

ItemInInventoryRBR - blocks recipes and unlocks them when player has required item(-s) in inventory

Parameters:
* tooltip (String) - tooltip that will be displayed on blocked items
* blockedItems (ItemTags) - items that will be blocked
* neededItems (ItemTags) - items required to unlock recipe

Example:
```java
ItemInInventoryRBR PLANKS_NEEDS_WOOL = 
        new ItemInInventoryRBR("Requires wool", ItemTags.PLANKS, ItemTags.WOOL);
```

It will make all planks uncraftable if player doesn't have wool in his inventory

If you don't want tooltip to be added to blocked items, leave an empty string:
```java
ItemInInventoryRBR PLANKS_NEEDS_WOOL = 
        new ItemInInventoryRBR("", ItemTags.PLANKS, ItemTags.WOOL);
```
Of course, you can use your own tags.
You will find some examples here: https://github.com/Xires87/FrycMod/blob/master/src/main/java/net/fryc/frycmod/FrycMod.java

Other classes are used in the same way. The only difference is the third parameter:
* StandNearBlockRBR uses BlockTags
* BeOnBiomeRBR uses BiomeTags
* RecipeBlockingRules doesn't have third parameter

## Note
* creating 2 (or more) rules for the same item makes this item craftable only when conditions from all rules (for that item) are met
* if you want to make items craftable only in specified dimension, use BeOnBiomeRBR with tag containing all biomes from that dimension












    


