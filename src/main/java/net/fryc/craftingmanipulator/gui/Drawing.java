package net.fryc.craftingmanipulator.gui;


import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Identifier;

public class Drawing {

    private final Class<? extends ScreenHandler> targetScreenHandler;
    private final Identifier texture;
    private final int x;
    private final int y;
    private final int xInFile;
    private final int yInFile;
    private final int width;
    private final int height;
    public boolean enabled = false;

    /**
     * @param texture Gui with additional drawings outside
     * @param x Position on the gui
     * @param y Position on the gui
     * @param xInFile Position of your drawing in file (starting from gui's width)
     * @param yInFile Position of your drawing in file (starting from 0)
     * @param width Width of your drawing
     * @param height Height of your drawing
     */
    public Drawing(Class<? extends ScreenHandler> targetScreenHandler, Identifier texture, int x, int y, int xInFile, int yInFile, int width, int height){
        this.targetScreenHandler = targetScreenHandler;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.xInFile = xInFile;
        this.yInFile = yInFile;
        this.width = width;
        this.height = height;
    }

    public Class<? extends ScreenHandler> getTargetScreenHandler() {
        return this.targetScreenHandler;
    }
    public Identifier getTexture() {
        return this.texture;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getxInFile() {
        return this.xInFile;
    }

    public int getyInFile() {
        return this.yInFile;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isEnabled(Class<? extends ScreenHandler> currentScreenHandler){
        return this.enabled && this.targetScreenHandler == currentScreenHandler;
    }
}
