package useless.legacyui.mixins;

import net.minecraft.client.gui.container.ScreenFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import useless.legacyui.settings.LegacySettings;

@Mixin(value = ScreenFurnace.class, remap = false)
public class ScreenFurnaceDarkModeMixin {

    @ModifyArg(method = "drawGuiContainerBackgroundLayer(F)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TextureManager;loadTexture(Ljava/lang/String;)Lnet/minecraft/client/render/texture/Texture;"))
    private String legacyui$darkModeFurnaceTexture(String name){
        if (LegacySettings.ENABLE_LEGACY_DARK_MODE.value){
            return "/assets/legacyui/textures/gui/vanilla_dark/furnace.png";
        }
        return name;
    }
}
