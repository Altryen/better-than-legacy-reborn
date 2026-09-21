package useless.legacyui.gui.screens;

import useless.legacyui.settings.LegacySettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.Lighting;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.renderer.State;
import useless.legacyui.LegacyUI;

import java.util.Random;

public class UtilGui {
    public static Minecraft mc = Minecraft.getMinecraft();
    public static final int tabScrollRepeatDelay = 1000/6;
    public static final int tabScrollInitialDelay = 150;
    public static final int verticalScrollRepeatDelay = 1000/6;
    public static final int verticalScrollInitialDelay = 150;
    public static final int repeatCraftDelay = 1000/10;
    public static final int initialCraftDelay = 300;

    public static void drawTexturedModalRect(final Gui gui, final double x, final double y, final double u, final double v, final double width, final double height, final double scale) {
        final TessellatorGeneral tessellator = GLRenderer.getTessellator();
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0,      y + height, gui.zLevel, (u + 0) * scale,        (v + height) * scale);
        tessellator.addVertexWithUV(x + width,  y + height, gui.zLevel, (u + width) * scale,    (v + height) * scale);
        tessellator.addVertexWithUV(x + width,  y + 0,      gui.zLevel, (u + width) * scale,    (v + 0) * scale);
        tessellator.addVertexWithUV(x + 0,      y + 0,      gui.zLevel, (u + 0) * scale,        (v + 0) * scale);
        tessellator.draw();
    }

    public static float blockAlpha = 1f;
    private static float lastOffset = -1;
    private static int scrollsCompleted = 0;
    private static long fadeTime = 0;
    private static long prevTime = -1;
    private static boolean changedPano = false;
    public static int panoCount = -1;
    public static int currentPano = -1;
    private static final Random random = new Random();
    public static void drawPanorama(final Screen gui){
        if (prevTime == -1){
            prevTime = System.currentTimeMillis();
        }
        final long deltaTime = System.currentTimeMillis() - prevTime;
        prevTime = System.currentTimeMillis();

        final TessellatorGeneral tessellator = GLRenderer.getTessellator();
        final float imageWidth = 820f;
        final float imageHeight = 144f;
        final float imageAspectRatio = imageWidth / imageHeight;
        final float screenAspectRatio = (float)gui.width / (float)gui.height;
        final float finalAspectRatio = (float)gui.width / imageWidth / ((float)gui.height / imageHeight);
        mc.textureManager.loadTexture("/assets/legacyui/panoramas/pn_"+currentPano+".png").bind(); // TODO readd blur
        GLRenderer.setColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        tessellator.startDrawingQuads();
        final float brightness = LegacySettings.MAIN_MENU_BRIGHTNESS.value;


        final int scrollLength = (1000 * ((LegacySettings.PANORAMA_SCROLL_LENGTH.value + 1) * 15));

        final float offset = (float) (System.currentTimeMillis() % scrollLength) /scrollLength;

        if (lastOffset == -1){
            lastOffset = offset;
        }

        if (offset < lastOffset){
            scrollsCompleted++;
        }
        if (scrollsCompleted > 0){
            fadeTime += deltaTime;
            final float fadeFactor = Math.abs((500f - fadeTime)/500f);
            tessellator.setColor4f(brightness * fadeFactor, brightness * fadeFactor, brightness * fadeFactor, 1.0f);
            if (500 <= fadeTime && fadeTime < 1000){
                if (!changedPano){
                    changedPano = true;
                    currentPano = random.nextInt(panoCount);
                }

            } else if (fadeTime >= 1000) {
                changedPano = false;
                scrollsCompleted = 0;
                fadeTime = 0;
            }
        } else {
            tessellator.setColor4f(brightness, brightness, brightness, 1.0f);
        }
        lastOffset = offset;
        if (screenAspectRatio < imageAspectRatio) {
            tessellator.addVertexWithUV(0.0, gui.height, 0.0, (0.5f + offset) - finalAspectRatio / 2.0f, 1.0);
            tessellator.addVertexWithUV(gui.width, gui.height, 0.0, (0.5f + offset) + finalAspectRatio / 2.0f, 1.0);
            tessellator.addVertexWithUV(gui.width, 0.0, 0.0, (0.5f + offset) + finalAspectRatio / 2.0f, 0.0);
            tessellator.addVertexWithUV(0.0, 0.0, 0.0, (0.5f + offset) - finalAspectRatio / 2.0f, 0.0);
        } else {
            tessellator.addVertexWithUV(0.0, gui.height, 0.0, 0.0, 0.5f + 0.5f / finalAspectRatio);
            tessellator.addVertexWithUV(gui.width, gui.height, 0.0, 1.0, 0.5f + 0.5f / finalAspectRatio);
            tessellator.addVertexWithUV(gui.width, 0.0, 0.0, 1.0, 0.5f - 0.5f / finalAspectRatio);
            tessellator.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 0.5f - 0.5f / finalAspectRatio);
        }
        tessellator.draw();
        GLRenderer.setColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    private static float prevYRot = -1000;
    private static float desiredYRot = 0;
    public static void drawPaperDoll(final boolean drawRight){
        if (EntityRendererDispatcher.instance.textureManager == null) return;
        if (prevYRot == -1000){
            prevYRot = mc.thePlayer.yRot;
        }
        GLRenderer.setColor4f(1f, 1f, 1f, 1f);
        desiredYRot += mc.thePlayer.yRot - prevYRot;
        final float lookRange = 30;
        desiredYRot = Math.max(desiredYRot, -lookRange);
        desiredYRot = Math.min(desiredYRot, lookRange);
        GLRenderer.enableState(State.DEPTH_TEST);
        GLRenderer.pushFrame();
        final int width = mc.resolution.getScaledWidthScreenCoords();
        final float xOff = drawRight ? width - 30: 30;
        final float yOff = 75;
        GLRenderer.modelM4f().translate(xOff, yOff, 0.0f);
        final float f1 = 30.0f;
        GLRenderer.modelM4f().scale(-f1, f1, f1);
        GLRenderer.modelM4f().rotate((float) Math.toRadians(180.0f), 0.0f, 0.0f, 1.0f);
        final float oYawOff = mc.thePlayer.yBodyRot;
        final float oYRot = mc.thePlayer.yRot;
        final float oXRot = mc.thePlayer.xRot;

        Lighting.enableLight();
        mc.thePlayer.yBodyRot = drawRight ? 15: -15;
        mc.thePlayer.yRot = desiredYRot;

        mc.thePlayer.entityBrightness = 1.0f;
        GLRenderer.modelM4f().translate(0.0f, mc.thePlayer.heightOffset, 0.0f);
        EntityRendererDispatcher.instance.viewLerpYaw = 180.0f;
        EntityRendererDispatcher.instance.renderEntityWithPosYaw(GLRenderer.getTessellator(), mc.thePlayer, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        mc.thePlayer.entityBrightness = 0.0f;

        mc.thePlayer.yBodyRot = oYawOff;
        mc.thePlayer.yRot = oYRot;
        mc.thePlayer.xRot = oXRot;
        GLRenderer.popFrame();
        Lighting.disable();
        prevYRot = mc.thePlayer.yRot;
    }
    private static int prevItem = -1;
    private static long timeHotbarLastActive = 0;
    public static float getHotbarAlpha(){
        if (mc.currentScreen != null) return 1f;
        if (!LegacySettings.ENABLE_HUD_FADEOUT.value) return 1;
        if (mc.thePlayer.inventory.getCurrentSlot() != prevItem) {
            timeHotbarLastActive = System.currentTimeMillis();
        }
        prevItem = mc.thePlayer.inventory.getCurrentSlot();
        final long currentTime = System.currentTimeMillis();
        if (currentTime - timeHotbarLastActive <= (LegacySettings.HUD_FADEOUT_DELAY.value * 10000)){
            return 1f;
        }
        return Math.max(1f - ((currentTime - timeHotbarLastActive) - (LegacySettings.HUD_FADEOUT_DELAY.value * 10000))/1000f, LegacySettings.HUD_FADEOUT_ALPHA.value);
    }
}
