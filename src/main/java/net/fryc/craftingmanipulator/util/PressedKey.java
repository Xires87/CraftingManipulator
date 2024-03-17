package net.fryc.craftingmanipulator.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public enum PressedKey {
    SHIFT{
        public boolean isPressingKey(){
            return Screen.hasShiftDown();
        }
    },
    CTRL{
        public boolean isPressingKey(){
            return Screen.hasControlDown();
        }
    },
    ALT{
        public boolean isPressingKey(){
            return Screen.hasAltDown();
        }
    },
    NONE{
        public boolean isPressingKey(){
            return false;
        }
    };


    PressedKey(){
    }

    public abstract boolean isPressingKey();
}
