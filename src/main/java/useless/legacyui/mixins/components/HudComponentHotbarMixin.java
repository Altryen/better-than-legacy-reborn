package useless.legacyui.mixins.components;

import useless.legacyui.settings.LegacySettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.container.ScreenContainerAbstract;
import net.minecraft.client.gui.hud.component.HudComponentHotbar;
import net.minecraft.client.render.font.FontRenderer;
import net.minecraft.client.render.TextureManager;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import useless.legacyui.LegacyUI;
import useless.legacyui.gui.screens.UtilGui;

@Mixin(value = HudComponentHotbar.class, remap = false)
public class HudComponentHotbarMixin {
    @Redirect(method = "render(Lnet/minecraft/client/gui/hud/HudIngame;IIF)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/renderer/GLRenderer;setColor4f(FFFF)V"))
    private void hotbarFadeout(final float red, final float green, final float blue, final float alpha){
        net.minecraft.client.render.renderer.GLRenderer.setColor4f(red, green, blue, UtilGui.getHotbarAlpha());
    }
    @Redirect(method = "renderInventorySlot(Lnet/minecraft/client/Minecraft;IIIF)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/model/ItemModel;renderGui(Lnet/minecraft/client/render/tessellator/TessellatorGeneral;Lnet/minecraft/core/entity/Entity;Lnet/minecraft/core/item/ItemStack;IIBF)V"))
    private void fadeIntoGUI(final ItemModel instance, final TessellatorGeneral tessellator, final net.minecraft.core.entity.Entity holder, final ItemStack itemstack, final int x, final int y, final byte lightIndex, final float partialTick){
        net.minecraft.client.render.renderer.GLRenderer.setColor4f(1.0F, 1.0F, 1.0F, UtilGui.getHotbarAlpha());
        instance.renderGui(tessellator, holder, itemstack, x, y, lightIndex, partialTick);
    }
    @Redirect(method = "renderInventorySlot(Lnet/minecraft/client/Minecraft;IIIF)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/model/ItemModel;renderItemOverlayIntoGUI(Lnet/minecraft/client/render/tessellator/TessellatorGeneral;Lnet/minecraft/client/render/font/FontRenderer;Lnet/minecraft/client/render/TextureManager;Lnet/minecraft/core/item/ItemStack;IILjava/lang/String;F)V"))
    private void fadeIntoGUIOverlay(final ItemModel instance, final TessellatorGeneral tessellator, final FontRenderer font, final TextureManager textureManager, final ItemStack itemstack, final int x, final int y, final String discoveredText, final float alpha){
        instance.renderItemOverlayIntoGUI(tessellator, font, textureManager, itemstack, x, y, discoveredText, UtilGui.getHotbarAlpha());
    }

    @Inject(method = "isVisible", at = @At("HEAD"), cancellable = true)
    private void dontRenderInGuiHotbar(final CallbackInfoReturnable<Boolean> cir){
        if (LegacySettings.HIDE_HOTBAR_IN_GUIS.value){
            if (Minecraft.getMinecraft().currentScreen instanceof ScreenContainerAbstract){
                cir.setReturnValue(false);
            }
        }
    }
}
