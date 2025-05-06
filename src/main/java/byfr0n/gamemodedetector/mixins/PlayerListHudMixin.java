package byfr0n.gamemodedetector.mixins;

import byfr0n.gamemodedetector.Gamemodedetector;
import byfr0n.gamemodedetector.utils.TabRenderer;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerListHud.class)
public abstract class PlayerListHudMixin {
    @Shadow
    protected abstract List<PlayerListEntry> collectPlayerEntries();

    @Inject(method = "getPlayerName", at = @At("HEAD"), cancellable = true)
    public void getPlayerName(PlayerListEntry playerListEntry, CallbackInfoReturnable<Text> info) {
        TabRenderer tabRenderer = Gamemodedetector.tabRenderer;

        info.setReturnValue(tabRenderer.getPlayerName(playerListEntry));
    }
}