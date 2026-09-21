package useless.legacyui.mixins;

import net.minecraft.client.gui.container.ScreenTrommel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import useless.legacyui.settings.LegacySettings;

@Mixin(value = ScreenTrommel.class, remap = false)
public class ScreenTrommelDarkModeMixin {

    @ModifyArg(method = "drawGuiContainerBackgroundLayer(F)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TextureManager;loadTexture(Ljava/lang/String;)Lnet/minecraft/client/render/texture/Texture;"))
    private String legacyui$darkModeTrommelTexture(String name){
        if (LegacySettings.ENABLE_LEGACY_DARK_MODE.value){
            return "/assets/legacyui/textures/gui/vanilla_dark/trommel.png";
        }
        return name;
    }
}
