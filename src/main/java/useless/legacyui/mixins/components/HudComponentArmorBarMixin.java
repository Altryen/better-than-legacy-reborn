package useless.legacyui.mixins.components;

import net.minecraft.client.gui.hud.component.HudComponentArmorBar;
import net.minecraft.client.render.renderer.GLRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import useless.legacyui.gui.screens.UtilGui;

@Mixin(value = HudComponentArmorBar.class, remap = false)
public class HudComponentArmorBarMixin {
    @Redirect(method = "render(Lnet/minecraft/client/gui/hud/HudIngame;IIF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/renderer/GLRenderer;setColor4f(FFFF)V"))
    private void hotbarFadeout(final float red, final float green, final float blue, final float alpha){
        GLRenderer.setColor4f(red, green, blue, UtilGui.getHotbarAlpha());
    }
}
