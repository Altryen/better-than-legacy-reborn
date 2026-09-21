package useless.legacyui;

import net.minecraft.client.render.texture.stitcher.AtlasStitcher;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.lang.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.ClientStartEntrypoint;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class LegacyUI implements ClientStartEntrypoint {
    public static final String MOD_ID = "legacyui";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void beforeClientStart() {
        LOGGER.info("LegacyUI initialized.");
        loadLanguage();
        for (Map.Entry<String, AtlasStitcher> entry : TextureRegistry.stitcherMap.entrySet()) {
            try {
                TextureRegistry.initializeAllFiles(MOD_ID, entry.getValue(), true);
            } catch (URISyntaxException | IOException e) {
                LOGGER.error("Failed to initialize textures in {} atlas!", entry.getKey(), e);
            }
        }
    }

    private void loadLanguage() {
        try (InputStream stream = LegacyUI.class.getResourceAsStream("/assets/legacyui/lang/en_US/en_US.lang")) {
            if (stream == null) {
                LOGGER.error("Could not find legacyui language file on classpath!");
                return;
            }
            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                Language.Default.INSTANCE.getEntries().load(reader);
                LOGGER.info("Loaded legacyui translations, now {} total default keys.", Language.Default.INSTANCE.getEntries().size());
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load legacyui language file!", e);
        }
    }

    @Override
    public void afterClientStart() {

    }

}
