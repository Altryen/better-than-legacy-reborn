package useless.legacyui.mixins.gui;

import useless.legacyui.settings.LegacySettings;
import net.minecraft.client.gui.MainMenuBackground;
import net.minecraft.client.render.renderer.GLRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import useless.legacyui.LegacyUI;

@Mixin(value = MainMenuBackground.class, remap = false)
public class MainMenuBackgroundMixin {
    @Redirect(method = "drawImage(Lnet/minecraft/client/gui/MainMenuBackground$Background;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/renderer/GLRenderer;setColor4f(FFFF)V"))
    private void darkenImage(float red, float green, float blue, float alpha){
        float brightness = LegacySettings.MAIN_MENU_BRIGHTNESS.value;
        GLRenderer.setColor4f(brightness * red, brightness * green, brightness * blue, alpha);
    }
}
