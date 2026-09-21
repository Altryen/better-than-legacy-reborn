package useless.legacyui.mixins.gui;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.gui.ButtonElement;
import net.minecraft.client.gui.container.ScreenInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ScreenInventory.class, remap = false)
public interface GuiInventoryAccessor {
    @Accessor
    ButtonElement getArmorButton();
    @Accessor
    IntList getUvs();
}
