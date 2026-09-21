package useless.legacyui.mixins;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.container.ScreenActivator;
import net.minecraft.core.lang.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.legacyui.settings.LegacySettings;

@Mixin(value = ScreenActivator.class, remap = false)
public abstract class ScreenActivatorTextColorMixin extends Screen {

    @Inject(method = "drawGuiContainerForegroundLayer()V", at = @At("HEAD"), cancellable = true)
    private void legacyui$darkModeActivatorText(CallbackInfo ci){
        if (LegacySettings.ENABLE_LEGACY_DARK_MODE.value){
            I18n i18n = I18n.getInstance();
            int color = LegacySettings.guiTextColor();
            int ySize = useless.legacyui.settings.LegacySettings.readYSize(this);
            this.drawStringNoShadow(this.fontRenderer, i18n.translateKey("gui.activator.label.activator"), 60, 6, color);
            this.drawStringNoShadow(this.fontRenderer, i18n.translateKey("gui.activator.label.inventory"), 8, ySize - 96 + 2, color);
            ci.cancel();
        }
    }
}
