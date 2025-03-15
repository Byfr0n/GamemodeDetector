package byfr0n.gamemodedetector.utils;

import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

public class TabRenderer {

    public Text getPlayerName(PlayerListEntry playerListEntry) {
        Text name;

        name = playerListEntry.getDisplayName();
        if (name == null) name = Text.literal(playerListEntry.getProfile().getName());


        GameMode gm = playerListEntry.getGameMode();
        String gmText = "?";
        if (gm != null) {
            gmText = switch (gm) {
                case SPECTATOR -> "Spectator";
                case SURVIVAL -> "Survival";
                case CREATIVE -> "Creative";
                case ADVENTURE -> "Adventure";
            };
        }
        MutableText text = Text.literal("");
        text.append(name);
        text.append(" [" + gmText + "]");
        name = text;


        return name;
    }

}
