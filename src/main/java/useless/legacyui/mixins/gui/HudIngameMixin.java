package useless.legacyui.mixins.gui;

import net.minecraft.client.option.GameSettings;
import useless.legacyui.settings.LegacySettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.container.ScreenContainerAbstract;
import net.minecraft.client.gui.hud.HudIngame;
import net.minecraft.client.render.font.FontRenderer;
import net.minecraft.client.render.TextureManager;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.player.gamemode.Gamemodes;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.legacyui.gui.screens.UtilGui;
import useless.legacyui.LegacyUI;

@Mixin(value = HudIngame.class, remap = false)
public class HudIngameMixin {
    @Shadow
    protected Minecraft mc;

    @Inject(method = "renderGameOverlay(FZII)V", at = @At(value = "TAIL"))
    private void paperDoll(final float partialTicks, final boolean flag, final int mouseX, final int mouseY, final CallbackInfo ci){
        if (LegacySettings.HIDE_HOTBAR_IN_GUIS.value){
            if (this.mc.currentScreen instanceof ScreenContainerAbstract){
                return;
            }
        }
        if (LegacySettings.ENABLE_PAPER_DOLL.value && !GameSettings.SHOW_DEBUG_SCREEN.value){
            boolean clock = false;
            boolean compass = false;
            boolean rotaryCalendar = false;
            if (this.mc.thePlayer.getGamemode() == Gamemodes.CREATIVE) {
                clock = true;
                compass = true;
                rotaryCalendar = true;
            } else {
                for (int iinv = 0; iinv < this.mc.thePlayer.inventory.getContainerSize(); ++iinv) {
                    final ItemStack item = this.mc.thePlayer.inventory.getItem(iinv);
                    if (item == null) continue;
                    if (item.itemID == Items.TOOL_CLOCK.id) {
                        clock = true;
                    }
                    if (item.itemID == Items.TOOL_COMPASS.id) {
                        compass = true;
                    }
                    if (item.itemID != Items.TOOL_CALENDAR.id) continue;
                    rotaryCalendar = true;
                }
            }
            boolean drawRight;
            drawRight = (clock && GameSettings.TIME_IN_OVERLAY.value);
            drawRight = drawRight || (compass && (GameSettings.COORDS_IN_OVERLAY.value || GameSettings.DIRECTION_IN_OVERLAY.value));
            drawRight = drawRight || (rotaryCalendar && (GameSettings.SEASON_IN_OVERLAY.value || GameSettings.WEATHER_IN_OVERLAY.value));
            UtilGui.drawPaperDoll(drawRight);
        }
        net.minecraft.client.render.renderer.GLRenderer.disableState(net.minecraft.client.render.renderer.State.BLEND);
        net.minecraft.client.render.renderer.GLRenderer.setColor4f(1,1,1, 1);
        UtilGui.blockAlpha = 1f;
    }
}
