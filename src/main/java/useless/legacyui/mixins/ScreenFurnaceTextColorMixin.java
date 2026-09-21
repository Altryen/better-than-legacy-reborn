package useless.legacyui.mixins;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.container.ScreenFurnace;
import net.minecraft.core.lang.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.legacyui.settings.LegacySettings;

@Mixin(value = ScreenFurnace.class, remap = false)
public abstract class ScreenFurnaceTextColorMixin extends Screen {

    @Inject(method = "drawGuiContainerForegroundLayer()V", at = @At("HEAD"), cancellable = true)
    private void legacyui$darkModeFurnaceText(CallbackInfo ci){
        if (LegacySettings.ENABLE_LEGACY_DARK_MODE.value){
            I18n i18n = I18n.getInstance();
            int color = LegacySettings.guiTextColor();
            int ySize = useless.legacyui.settings.LegacySettings.readYSize(this);
            this.drawStringNoShadow(this.fontRenderer, i18n.translateKey("gui.furnace.label.furnace"), 60, 6, color);
            this.drawStringNoShadow(this.fontRenderer, i18n.translateKey("gui.furnace.label.inventory"), 8, ySize - 96 + 2, color);
            ci.cancel();
        }
    }
}
