package useless.legacyui.gui.screens.options;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.options.ScreenOptions;
import net.minecraft.client.gui.options.components.BooleanOptionComponent;
import net.minecraft.client.gui.options.components.FloatOptionComponent;
import net.minecraft.client.gui.options.components.OptionsCategory;
import net.minecraft.client.gui.options.components.ToggleableOptionComponent;
import net.minecraft.client.gui.options.data.OptionsPage;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.client.option.GameSettings;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import useless.legacyui.settings.LegacySettings;

public class GuiOptionsPageLegacy implements ClientStartEntrypoint {
    public static final OptionsPage LegacyUIPage = OptionsPages.register(new OptionsPage("legacyui.options.title", Items.MAP.getDefaultStack())
            .withComponent(
                    new OptionsCategory("legacyui.options.gameplay")
                            .withComponent(new BooleanOptionComponent(LegacySettings.COORDS_ON_MAPS)))
            .withComponent(new OptionsCategory("legacyui.options.hud")
                    .withComponent(new BooleanOptionComponent(LegacySettings.HIDE_HOTBAR_IN_GUIS))
                    .withComponent(new BooleanOptionComponent(LegacySettings.ENABLE_PAPER_DOLL))
                    .withComponent(new BooleanOptionComponent(LegacySettings.ENABLE_HUD_FADEOUT))
                    .withComponent(new FloatOptionComponent(LegacySettings.HUD_FADEOUT_DELAY))
                    .withComponent(new FloatOptionComponent(LegacySettings.HUD_FADEOUT_ALPHA)))
            .withComponent(new OptionsCategory("legacyui.options.gui")
                    .withComponent(new BooleanOptionComponent(LegacySettings.ENABLE_LEGACY_INVENTORY_SURVIVAL))
                    .withComponent(new BooleanOptionComponent(LegacySettings.ENABLE_LEGACY_CRAFTING))
                    .withComponent(new BooleanOptionComponent(LegacySettings.ENABLE_LEGACY_INVENTORY_CREATIVE))
                    .withComponent(new BooleanOptionComponent(LegacySettings.ENABLE_LEGACY_FLAG))
                    .withComponent(new BooleanOptionComponent(LegacySettings.ENABLE_LEGACY_DARK_MODE))
                    .withComponent(new ToggleableOptionComponent<>(LegacySettings.GUI_CONTROLLER_TYPE))
                    .withComponent(new BooleanOptionComponent(LegacySettings.SHOW_CRAFTING_ITEM_NAME_PREVIEW))
                    .withComponent(new BooleanOptionComponent(LegacySettings.CRAFTING_HIDE_UNDISCOVERED_ITEMS))
                    .withComponent(new BooleanOptionComponent(LegacySettings.FORCE_BUTTON_PROMPTS))
                    .withComponent(new BooleanOptionComponent(LegacySettings.FORCE_LEGACY_TOOLTIP)))
            .withComponent(new OptionsCategory("legacyui.options.panorama")
                    .withComponent(new BooleanOptionComponent(LegacySettings.ENABLE_PANORAMA))
                    .withComponent(new ToggleableOptionComponent<>(LegacySettings.PANORAMA_SCROLL_LENGTH))
                    .withComponent(new FloatOptionComponent(LegacySettings.MAIN_MENU_BRIGHTNESS)))
            .withComponent(new OptionsCategory("legacyui.options.sound")
                    .withComponent(new BooleanOptionComponent(LegacySettings.USE_LEGACY_SOUNDS))
                    .withComponent(new BooleanOptionComponent(LegacySettings.USE_RANDOM_PITCH))));
    public static ScreenOptions legacyOptionsScreen(final Screen parent){
        return new ScreenOptions(parent, LegacyUIPage);
    }

    @Override
    public void beforeClientStart() {

    }

    @Override
    public void afterClientStart() {

    }
}
