package dev.foolen.vaulttags.listeners;

import dev.foolen.vaulttags.VaultTagsPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        ScoreboardManager manager = VaultTagsPlugin.getInstance().getServer().getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        String group = VaultTagsPlugin.getPermissions().getPrimaryGroup(p);
        Team team = board.getTeam(group);

        team.addEntry(p.getName());
    }
}
