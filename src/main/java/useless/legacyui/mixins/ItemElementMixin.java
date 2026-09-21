package useless.legacyui.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ItemElement;
import net.minecraft.client.render.font.FontRenderer;
import net.minecraft.client.render.TextureManager;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.Slot;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.legacyui.gui.slots.IHighlightable;
import useless.legacyui.gui.slots.IResizable;

@Mixin(value = ItemElement.class, remap = false, priority = 2000)
public class ItemElementMixin extends Gui {

    @Redirect(method = "render(Lnet/minecraft/core/item/ItemStack;IIZLnet/minecraft/core/player/inventory/slot/Slot;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/model/ItemModel;renderGui(Lnet/minecraft/client/render/tessellator/TessellatorGeneral;Lnet/minecraft/core/entity/Entity;Lnet/minecraft/core/item/ItemStack;IIBF)V"))
    private void drawItemRedirect(final ItemModel model, final TessellatorGeneral tessellator, final net.minecraft.core.entity.Entity holder, final ItemStack itemstack, final int x, final int y, final byte lightIndex, final float partialTick, @Local(name = "slot") final Slot slot, @Local(name = "discovered") final boolean discovered){
        final float renderScale;
        if (slot instanceof IResizable){
            renderScale = (((IResizable) slot).getWidth())/18f;
        } else {
            renderScale = 1f;
        }

        final boolean notCraftable = slot instanceof IHighlightable && ((IHighlightable) slot).isHighlighted() && !((IHighlightable) slot).drawStandardHighlight();
        final boolean fadeOut = discovered && notCraftable;
        final boolean revealUndiscoveredCraftable = !discovered && !notCraftable;
        if (fadeOut){
            GLRenderer.setColor4f(1f, 1f, 1f, 0.35f);
        }

        final byte usedLightIndex = revealUndiscoveredCraftable ? net.minecraft.core.util.helper.LightIndexHelper.lightIndex2i(15, 15) : lightIndex;

        final int newX = (int)(x * (1/ renderScale));
        final int newY = (int)(y * (1/ renderScale));
        GLRenderer.pushFrame();
        GLRenderer.modelM4f().scale(renderScale, renderScale, renderScale);
        GLRenderer.modelM4f().translate((float) newX, (float) newY, 0f);
        model.renderGui(tessellator, holder, itemstack, 0, 0, usedLightIndex, partialTick);

        if (fadeOut){
            GLRenderer.setColor4f(1f, 1f, 1f, 1f);
        }
    }

    @Redirect(method = "render(Lnet/minecraft/core/item/ItemStack;IIZLnet/minecraft/core/player/inventory/slot/Slot;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/model/ItemModel;renderItemOverlayIntoGUI(Lnet/minecraft/client/render/tessellator/TessellatorGeneral;Lnet/minecraft/client/render/font/FontRenderer;Lnet/minecraft/client/render/TextureManager;Lnet/minecraft/core/item/ItemStack;IILjava/lang/String;F)V"))
    private void drawTextRedirect(final ItemModel model, final TessellatorGeneral tessellator, final FontRenderer font, final TextureManager textureManager, final ItemStack itemstack, final int x, final int y, final String s, final float alpha,
                                  @Local(name = "discovered") final boolean discovered, @Local(name = "slot") final Slot slot){
        final String overlayText;
        final boolean newlyCraftable = !discovered && slot instanceof IHighlightable && !((IHighlightable) slot).isHighlighted();
        if (discovered){
            overlayText = null;
        } else if (newlyCraftable){
            overlayText = "!!!"; // Undiscovered but craftable right now - let the player know!
        } else {
            overlayText = "?";
        }
        if (newlyCraftable){
            GLRenderer.setColor4f(0.3f, 1f, 0.3f, 1f);
        }
        model.renderItemOverlayIntoGUI((TessellatorGeneral) tessellator, font, textureManager, itemstack, 0, 0, overlayText, alpha);
        if (newlyCraftable){
            GLRenderer.setColor4f(1f, 1f, 1f, 1f);
        }
        GLRenderer.popFrame();
    }

    @Redirect(method = "render(Lnet/minecraft/core/item/ItemStack;IIZLnet/minecraft/core/player/inventory/slot/Slot;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/ItemElement;drawRect(IIIII)V"))
    private void drawRectRedirect(final ItemElement guiRenderItem, final int minX, final int minY, final int maxX, final int maxY, final int argb,
                                  @Local(name = "slot") final Slot slot, @Local(name = "isSelected") final boolean isSelected){
        final int slotSize;
        if (slot instanceof IResizable){
            slotSize = ((IResizable) slot).getWidth();
        } else {
            slotSize = 18;
        }

        this.drawRect(minX, minY,minX + slotSize - 2,minY + slotSize - 2, 0x80ffffff);
    }

    @Inject(method = "render(Lnet/minecraft/core/item/ItemStack;IIZLnet/minecraft/core/player/inventory/slot/Slot;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Lighting;disable()V", shift = At.Shift.BEFORE))
    private void drawHighlight(final ItemStack itemStack, final int x, final int y, final boolean isSelected, final Slot slot, final CallbackInfo ci){
        if(slot instanceof IHighlightable && ((IHighlightable)slot).isHighlighted() && ((IHighlightable)slot).drawStandardHighlight()){
            final int slotSize;
            if (slot instanceof IResizable){
                slotSize = ((IResizable) slot).getWidth();
            } else {
                slotSize = 18;
            }

            this.drawRect(x, y, x + slotSize - 2, y + slotSize - 2, 0x80000000 | ((IHighlightable)slot).getHighlightColor());
        }
    }
}
