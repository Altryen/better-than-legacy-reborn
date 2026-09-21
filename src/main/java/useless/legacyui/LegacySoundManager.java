package useless.legacyui;

import useless.legacyui.settings.LegacySettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.sound.SoundEngine;
import net.minecraft.core.sound.SoundCategory;
import java.util.Random;

public class LegacySoundManager {
    public static Random rand = new Random();
    public static Minecraft mc = Minecraft.getMinecraft();
    public static SoundEngine sndManager = mc.sndManager;
    public static float volume = 1f;
    public static float getPitch(boolean randomPitch){
        return (randomPitch && LegacySettings.USE_RANDOM_PITCH.value) ? 1f + ((rand.nextFloat()-0.5f)/16f) : 1f;
    }
    public static class play {
        public static void press(boolean randomPitch){
            sndManager.playSound("ui.ui_click", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }
        public static void back(boolean randomPitch){
            sndManager.playSound("ui.ui_back", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }
        public static void craft(boolean randomPitch){
            sndManager.playSound("ui.crafting_made", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }
        public static void craftfail(boolean randomPitch){
            sndManager.playSound("ui.craft_fail", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }
        public static void focus(boolean randomPitch){
            sndManager.playSound("ui.ui_focus", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }
        public static void scroll(boolean randomPitch){
            sndManager.playSound("ui.ui_scroll", SoundCategory.GUI_SOUNDS, volume, getPitch(randomPitch));
        }

    }
}
