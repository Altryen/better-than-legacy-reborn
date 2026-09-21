package useless.legacyui.settings;

import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.OptionBoolean;
import net.minecraft.client.option.OptionColor;
import net.minecraft.client.option.OptionEnum;
import net.minecraft.client.option.OptionFloat;
import net.minecraft.client.option.OptionRange;
import net.minecraft.core.player.inventory.menu.MenuInventory;
import net.minecraft.core.player.gamemode.Gamemodes;
import net.minecraft.core.util.helper.Color;
import useless.legacyui.gui.screens.options.ControllerType;

/**
 * BTA 8.0+ made GameSettings a fully static class (no more instance / mc field),
 * so mod options are now added the same way vanilla options are: by calling
 * GameSettings.register(...) directly, instead of mixing fields into an instance.
 * This class replaces the old GameSettingsMixin.
 */
public final class LegacySettings {

    public static final OptionBoolean CRAFTING_HIDE_UNDISCOVERED_ITEMS =
            GameSettings.register(new OptionBoolean("legacyui.craftingHideUndiscoveredItems", true));

    public static final OptionBoolean USE_LEGACY_SOUNDS =
            GameSettings.register(new OptionBoolean("legacyui.useLegacySounds", true));

    public static final OptionBoolean HIDE_HOTBAR_IN_GUIS =
            GameSettings.register(new OptionBoolean("legacyui.hideHotbarInGUIs", true));

    public static final OptionBoolean ENABLE_LEGACY_CRAFTING =
            GameSettings.register(new OptionBoolean("legacyui.enableLegacyCrafting", true));

    public static final OptionBoolean ENABLE_LEGACY_INVENTORY_SURVIVAL =
            GameSettings.register((OptionBoolean) (new OptionBoolean("legacyui.enableLegacyInventorySurvival", true))
                    .addOnChangeCallback((mc, option) -> {
                        if (mc.thePlayer != null && mc.thePlayer.getGamemode() == Gamemodes.SURVIVAL) {
                            MenuInventory newContainer = Gamemodes.SURVIVAL.getInventoryMenu(mc.thePlayer.inventory);
                            if (mc.thePlayer.containerMenu == mc.thePlayer.inventoryMenu) {
                                mc.thePlayer.containerMenu = newContainer;
                            }
                            mc.thePlayer.inventoryMenu = newContainer;
                        }
                    }));

    public static final OptionBoolean ENABLE_LEGACY_INVENTORY_CREATIVE =
            GameSettings.register((OptionBoolean) (new OptionBoolean("legacyui.enableLegacyInventoryCreative", true))
                    .addOnChangeCallback((mc, option) -> {
                        if (mc.thePlayer != null && mc.thePlayer.getGamemode() == Gamemodes.CREATIVE) {
                            MenuInventory newContainer = Gamemodes.CREATIVE.getInventoryMenu(mc.thePlayer.inventory);
                            if (mc.thePlayer.containerMenu == mc.thePlayer.inventoryMenu) {
                                mc.thePlayer.containerMenu = newContainer;
                            }
                            mc.thePlayer.inventoryMenu = newContainer;
                        }
                    }));

    public static final OptionBoolean ENABLE_LEGACY_FLAG =
            GameSettings.register(new OptionBoolean("legacyui.enableLegacyFlag", true));

    public static final OptionBoolean ENABLE_LEGACY_DARK_MODE =
            GameSettings.register(new OptionBoolean("legacyui.enableLegacyDarkMode", false));

    public static final OptionBoolean SHOW_CRAFTING_ITEM_NAME_PREVIEW =
            GameSettings.register(new OptionBoolean("legacyui.showCraftingItemNamePreview", true));

    public static final OptionBoolean USE_RANDOM_PITCH =
            GameSettings.register(new OptionBoolean("legacyui.useRandomPitch", false));

    public static final OptionColor GUI_PROMPT_COLOR =
            GameSettings.register(new OptionColor("legacyui.guiPromptColor", new Color().setARGB(0xFFFFFF)));

    public static final OptionColor HIGHLIGHT_COLOR =
            GameSettings.register(new OptionColor("legacyui.highlightColor", new Color().setARGB(0xFF0000)));

    public static final OptionColor GUI_BACKGROUND_COLOR =
            GameSettings.register(new OptionColor("legacyui.guiBackgroundColor", new Color().setARGB(0x90101010)));

    public static final OptionEnum<ControllerType> GUI_CONTROLLER_TYPE =
            GameSettings.register(new OptionEnum<>("legacyui.guiControllerType", ControllerType.class, ControllerType.GENERIC));

    public static final OptionBoolean ENABLE_PANORAMA =
            GameSettings.register(new OptionBoolean("legacyui.enablePanorama", true));

    public static final OptionRange PANORAMA_SCROLL_LENGTH =
            GameSettings.register((OptionRange) (new OptionRange("legacyui.panoramaSpeed", 3, 12))
                    .withDisplayStringProvider((mc, i18n, option) ->
                            ((Integer) option.value + 1) * 15 + " " + i18n.translateKey("options.legacyui.panoramaSpeed.unit")));

    public static final OptionFloat MAIN_MENU_BRIGHTNESS =
            GameSettings.register(new OptionFloat("legacyui.mainMenuBrightness", 1f));

    public static final OptionBoolean COORDS_ON_MAPS =
            GameSettings.register(new OptionBoolean("legacyui.coordsOnMaps", true));

    public static final OptionBoolean FORCE_BUTTON_PROMPTS =
            GameSettings.register(new OptionBoolean("legacyui.forceButtonPrompts", false));

    public static final OptionBoolean FORCE_LEGACY_TOOLTIP =
            GameSettings.register(new OptionBoolean("legacyui.forceLegacyTooltip", false));

    public static final OptionBoolean ENABLE_PAPER_DOLL =
            GameSettings.register(new OptionBoolean("legacyui.enablePaperDoll", false));

    public static final OptionBoolean ENABLE_HUD_FADEOUT =
            GameSettings.register(new OptionBoolean("legacyui.enableHUDFadeout", true));

    public static final OptionFloat HUD_FADEOUT_DELAY =
            GameSettings.register((OptionFloat) (new OptionFloat("legacyui.hudFadeoutDelay", 0.5f))
                    .withDisplayStringProvider((mc, i18n, option) ->
                            String.format("%.1f", (Float) option.value * 10) + " " + i18n.translateKey("options.legacyui.hudFadeout.unit")));

    public static final OptionFloat HUD_FADEOUT_ALPHA =
            GameSettings.register(new OptionFloat("legacyui.hudFadeoutAlpha", 0.20f));

    private LegacySettings() {
    }

    /**
     * Returns the path to a Legacy GUI texture, automatically picking the "_dark" variant
     * when the Dark Mode setting is enabled. baseName should be the texture's plain name
     * without extension, e.g. "legacycrafting".
     */
    public static String guiTexture(String baseName) {
        String suffix = ENABLE_LEGACY_DARK_MODE.value ? "_dark" : "";
        return "/assets/legacyui/textures/gui/" + baseName + suffix + ".png";
    }

    /**
     * Returns the color to use for Legacy GUI label text - the normal dark gray in
     * light mode, or a light gray when Dark Mode is enabled so text stays readable
     * against the darkened panel backgrounds.
     */
    public static int guiTextColor() {
        return ENABLE_LEGACY_DARK_MODE.value ? 0xC0C0C0 : 0x404040;
    }

    /**
     * Walks up the class hierarchy of the given object to find a field named "ySize"
     * (declared on a superclass rather than the object's own runtime class in some
     * cases) and returns its current value. Falls back to 166 (the standard vanilla
     * container GUI height) if it can't be found for any reason.
     */
    public static int readYSize(Object instance) {
        Object value = readField(instance, "ySize");
        if (value instanceof Integer) {
            return (Integer) value;
        }
        return 166;
    }

    /**
     * Walks up the class hierarchy of the given object to find a field with the given
     * name (regardless of which specific superclass declares it or its visibility)
     * and returns its current value, or null if it can't be found.
     */
    public static Object readField(Object instance, String fieldName) {
        Class<?> clazz = instance.getClass();
        while (clazz != null) {
            try {
                final java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(instance);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Referencing this class from LegacyUI's mod init is enough to trigger the static
     * initializers above, which register every option with GameSettings before the
     * game itself calls GameSettings.init() and loads the saved values.
     */
    public static void init() {
    }
}
