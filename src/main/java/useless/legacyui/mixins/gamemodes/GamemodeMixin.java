package useless.legacyui.mixins.gamemodes;

import useless.legacyui.settings.LegacySettings;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import net.minecraft.core.player.inventory.menu.MenuInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import useless.legacyui.gui.containers.LegacyContainerPlayerCreative;
import useless.legacyui.gui.containers.LegacyContainerPlayerSurvival;
import useless.legacyui.LegacyUI;

// BTA 8.0+ merged GamemodeSurvival and GamemodeCreative into a single Gamemode class,
// distinguished only by its registry id. This mixin replaces the old
// GamemodeSurvivalMixin and GamemodeCreativeMixin, branching on getId() instead.
@Mixin(value = Gamemode.class, remap = false)
public class GamemodeMixin {
    @Inject(method = "getInventoryMenu(Lnet/minecraft/core/player/inventory/container/ContainerInventory;)Lnet/minecraft/core/player/inventory/menu/MenuInventory;", at = @At("RETURN"), cancellable = true)
    private void returnModdedContainer(final ContainerInventory inventory, final CallbackInfoReturnable<MenuInventory> cir) {
        final String id = ((Gamemode) (Object) this).getId();

        if (id.equals("minecraft:gamemode/survival") && LegacySettings.ENABLE_LEGACY_INVENTORY_SURVIVAL.value) {
            cir.setReturnValue(new LegacyContainerPlayerSurvival(inventory));
        } else if (id.equals("minecraft:gamemode/creative") && LegacySettings.ENABLE_LEGACY_INVENTORY_CREATIVE.value) {
            cir.setReturnValue(new LegacyContainerPlayerCreative(inventory));
        }
    }
}
