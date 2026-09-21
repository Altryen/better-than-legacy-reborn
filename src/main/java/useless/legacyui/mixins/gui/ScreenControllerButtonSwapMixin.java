package useless.legacyui.mixins.gui;

import net.minecraft.client.gui.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Vanilla's default controller handling maps X -> mouseClicked(...,10) (shift-click / quick move)
 * and Y -> mouseClicked/mouseReleased(...,1) (right-click / half stack). The console versions of
 * BTA had this the other way around (Y = move stack, X = take half), so we swap the mouse-button
 * codes here to match that expectation.
 */
@Mixin(value = Screen.class, remap = false)
public class ScreenControllerButtonSwapMixin {

    @Redirect(method = "guiSpecificControllerInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Screen;mouseClicked(III)V", remap = false))
    private void legacyui$swapMouseClicked(final Screen instance, final int mx, final int my, final int button){
        instance.mouseClicked(mx, my, legacyui$swapButton(button));
    }

    @Redirect(method = "guiSpecificControllerInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Screen;mouseReleased(III)V", remap = false))
    private void legacyui$swapMouseReleased(final Screen instance, final int mx, final int my, final int button){
        instance.mouseReleased(mx, my, legacyui$swapButton(button));
    }

    private static int legacyui$swapButton(final int button){
        if (button == 1){
            return 10;
        }
        if (button == 10){
            return 1;
        }
        return button;
    }
}
