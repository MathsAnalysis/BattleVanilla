package dev.killergamerpls.vanilla.command.staff.pvp;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter
public class PvPManager {

    @Getter
    @Setter
    private boolean PvP = true;
    private Player player;

    public void setPvP(boolean PvP, Player player) {
        this.PvP = PvP;
        this.player = player;
    }
}