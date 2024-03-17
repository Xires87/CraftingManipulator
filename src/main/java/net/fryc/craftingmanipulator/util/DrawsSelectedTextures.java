package net.fryc.craftingmanipulator.util;

public interface DrawsSelectedTextures {

    boolean hasEnabledDrawing();
    boolean isItemModified();
    void setItemIsModified(boolean modified);
    void informAboutItemModification();
}
