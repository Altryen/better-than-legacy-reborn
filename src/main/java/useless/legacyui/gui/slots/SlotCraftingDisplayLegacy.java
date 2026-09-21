package useless.legacyui.gui.slots;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.Slot;

public class SlotCraftingDisplayLegacy extends Slot implements IResizable, IHighlightable {
    public ItemStack item;
    private final boolean highlighted;
    private final int highlightColor;
    private final int slotWidth;
    private final boolean drawStandardHighlight;
    public SlotCraftingDisplayLegacy(final int id, final int x, final int y, final ItemStack item, final boolean discovered, final boolean highlight, final int color) {
        this(id, x, y, item, discovered, highlight, color, 18, false);
    }
    public SlotCraftingDisplayLegacy(final int id, final int x, final int y, final ItemStack item, final boolean discovered, final boolean highlight, final int color, final boolean drawStandardHighlight) {
        this(id, x, y, item, discovered, highlight, color, 18, drawStandardHighlight);
    }
    public SlotCraftingDisplayLegacy(final int id, final int x, final int y, final ItemStack item, final boolean discovered, final boolean highlight, final int color, final int width) {
        this(id, x, y, item, discovered, highlight, color, width, false);
    }
    public SlotCraftingDisplayLegacy(final int id, final int x, final int y, final ItemStack item, final boolean discovered, final boolean highlight, final int color, final int width, final boolean drawStandardHighlight) {
        super(null, id, x, y);
        this.item = item;
        this.discovered = discovered;
        this.highlighted = highlight;
        this.highlightColor = color;
        this.slotWidth = width;
        this.drawStandardHighlight = drawStandardHighlight;
    }
    @Override
    public ItemStack remove(final int i) {
        return null;
    }

    @Override
    public boolean hasItem() {
        return this.item != null;
    }

    @Override
    public int getMaxStackSize() {
        return this.item.getMaxStackSize();
    }

    @Override
    public ItemStack getItemStack() {
        return this.item;
    }

    @Override
    public void onTake(final ItemStack itemstack) {
    }

    @Override
    public void setChanged() {
    }

    @Override
    public void set(final ItemStack itemstack) {
    }

    @Override
    public boolean mayPlace(final ItemStack itemstack) {
        return false;
    }

    @Override
    public boolean enableDragAndPickup() {
        return false;
    }

    @Override
    public boolean allowItemInteraction() {
        return false;
    }

    @Override
    public boolean isHighlighted() {
        return this.highlighted;
    }

    @Override
    public int getHighlightColor() {
        return this.highlightColor;
    }

    @Override
    public boolean drawStandardHighlight() {
        return this.drawStandardHighlight;
    }

    @Override
    public int getWidth() {
        return this.slotWidth;
    }
}