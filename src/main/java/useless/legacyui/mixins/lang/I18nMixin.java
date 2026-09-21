package useless.legacyui.mixins.lang;

import net.minecraft.core.lang.I18n;
import net.minecraft.core.lang.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.legacyui.LegacyUI;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Mixin(value = I18n.class, remap = false)
public class I18nMixin {
    @Shadow
    private Language currentLanguage;

    @Inject(method = "reload", at = @At("TAIL"))
    private void legacyui$mergeTranslations(final String languageCode, final CallbackInfo ci){
        if (this.currentLanguage == null){
            return;
        }
        final String path = "/assets/legacyui/lang/" + languageCode + "/" + languageCode + ".lang";
        try (InputStream stream = LegacyUI.class.getResourceAsStream(path)) {
            if (stream == null){
                return;
            }
            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                ((LanguageAccessor) this.currentLanguage).legacyui$getEntries().load(reader);
                LegacyUI.LOGGER.info("Loaded legacyui translations for language '{}'.", languageCode);
            }
        } catch (IOException e) {
            LegacyUI.LOGGER.error("Failed to load legacyui language file for '{}'!", languageCode, e);
        }
    }
}
