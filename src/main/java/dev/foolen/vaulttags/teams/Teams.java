package dev.foolen.vaulttags.teams;

import dev.foolen.vaulttags.VaultTagsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class Teams {

    private static ArrayList<Team> teams;

    public Teams() {
        teams = new ArrayList<>();

        createTeams();
    }

    private void createTeams() {
        ScoreboardManager manager = VaultTagsPlugin.getInstance().getServer().getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        for (String group : VaultTagsPlugin.getPermissions().getGroups()) {
            Team team = board.registerNewTeam(group);

            String prefix = VaultTagsPlugin.getChat().getGroupPrefix(Bukkit.getWorlds().get(0), group);
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', prefix));

            teams.add(team);
        }
    }

    public static void removeTeams() {
        // Kick players to be able to remove teams
        Bukkit.getOnlinePlayers().forEach(p -> p.kickPlayer(ChatColor.RED + "Server shutdown!"));

        // Remove teams
        ScoreboardManager manager = VaultTagsPlugin.getInstance().getServer().getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        teams.forEach(team -> team.unregister());
    }
}
