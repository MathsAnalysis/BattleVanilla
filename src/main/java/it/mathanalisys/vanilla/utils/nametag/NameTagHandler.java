package it.mathanalisys.vanilla.utils.nametag;

import it.mathanalisys.vanilla.utils.CC;
import org.apache.commons.lang3.StringEscapeUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class NameTagHandler {

	private static final String PREFIX = "nt_";

	public static void color(Player player, Player other, String color, boolean showHealth) {
		Team team = player.getScoreboard().getTeam(PREFIX + color);

		if (team == null) {
			team = player.getScoreboard().registerNewTeam(PREFIX + color);

			team.setPrefix(CC.translate(color));
		}

		if (team.hasEntry(other.getName())) {
			return;
		}


		reset(player, other);

		team.addEntry(other.getName());

		if (showHealth) {
			Objective objective = player.getScoreboard().getObjective(DisplaySlot.BELOW_NAME);

			if (objective == null) {
				objective = player.getScoreboard().registerNewObjective("showhealth", "health");
			}

			objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
			objective.setDisplayName(ChatColor.RED + StringEscapeUtils.unescapeJava("\u2764"));
			objective.getScore(other.getName()).setScore((int) Math.floor(other.getHealth()));
		}
	}

	public static void lobby(Player player, Player other) {
		if (player == null || other == null) {
			return;
		}

		for (Team team : player.getScoreboard().getTeams()) {
			team.removeEntry(other.getName());
		}
	}

	public static void reset(Player player, Player other) {
		if (player == null || other == null) {
			return;
		}

		for (Team team : player.getScoreboard().getTeams()) {
			team.removeEntry(other.getName());
		}
	}

	public static void removeHealthDisplay(Player player) {
		if (player == null) {
			return;
		}

		Objective objective = player.getScoreboard().getObjective(DisplaySlot.BELOW_NAME);

		if (objective != null) {
			objective.unregister();
		}
	}

}