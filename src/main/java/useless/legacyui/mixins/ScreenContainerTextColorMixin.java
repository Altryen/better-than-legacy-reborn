package useless.legacyui.mixins;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.container.ScreenContainer;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.container.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.legacyui.settings.LegacySettings;

@Mixin(value = ScreenContainer.class, remap = false)
public abstract class ScreenContainerTextColorMixin extends Screen {

    @Inject(method = "drawGuiContainerForegroundLayer()V", at = @At("HEAD"), cancellable = true)
    private void legacyui$darkModeContainerText(CallbackInfo ci){
        if (LegacySettings.ENABLE_LEGACY_DARK_MODE.value){
            I18n i18n = I18n.getInstance();
            int color = LegacySettings.guiTextColor();
            int ySize = LegacySettings.readYSize(this);
            Container upper = (Container) LegacySettings.readField(this, "upperChestInventory");
            Container lower = (Container) LegacySettings.readField(this, "lowerChestInventory");
            this.drawStringNoShadow(this.fontRenderer, i18n.translateKey(lower.getNameTranslationKey()), 8, 6, color);
            this.drawStringNoShadow(this.fontRenderer, i18n.translateKey(upper.getNameTranslationKey()), 8, ySize - 96 + 2, color);
            ci.cancel();
        }
    }
}
