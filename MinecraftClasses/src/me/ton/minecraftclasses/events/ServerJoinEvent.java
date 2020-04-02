package me.ton.minecraftclasses.events;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.ton.minecraftclasses.MinecraftClasses;
import me.ton.minecraftclasses.handlers.ConfigHandler;
import me.ton.minecraftclasses.handlers.PlayerHandler;
import me.ton.minecraftclasses.objects.managers.PlayerManager;
import me.ton.minecraftclasses.player.MccPlayer;

public class ServerJoinEvent implements Listener {
  MinecraftClasses mcClasses;
  
  public ServerJoinEvent(MinecraftClasses mcClasses) {
    this.mcClasses = mcClasses;
  }
  
  @EventHandler
  public void onServerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(this.mcClasses, player.getName());
    checkConfig(player, cfg);
  }
  
  private void checkConfig(Player player, FileConfiguration cfg) {
    File playerFiles = new File(MinecraftClasses.getInstance().getDataFolder(), "Players");
    File checkPlayer = new File(playerFiles, player.getName() + ".yml");
    if (checkPlayer.exists()) {
      PlayerManager.addPlayer(new MccPlayer(player.getUniqueId()));
    } else {
      cfg.createSection("Player-Stats");
      cfg.getConfigurationSection("Player-Stats").set("Kills", Integer.valueOf(0));
      cfg.getConfigurationSection("Player-Stats").set("Deaths", Integer.valueOf(0));
      cfg.getConfigurationSection("Player-Stats").set("Race", "**");
      cfg.getConfigurationSection("Player-Stats").set("Class", "**");
      cfg.getConfigurationSection("Player-Stats").set("Level", Integer.valueOf(1));
      ConfigHandler.savePlayerConfig(this.mcClasses, player.getName(), cfg);
      PlayerManager.addPlayer(new MccPlayer(player.getUniqueId(), null));
    } 
  }
  
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {
    Player player = event.getEntity();
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(this.mcClasses, player.getName());
    cfg.getConfigurationSection("Player-Stats").set("Deaths", Integer.valueOf(PlayerHandler.getPlayerDeaths(player) + 1));
    ConfigHandler.savePlayerConfig(this.mcClasses, player.getName(), cfg);
    if (player.getKiller() instanceof Player) {
      Player killer = player.getKiller();
      FileConfiguration cfg2 = ConfigHandler.getPlayerConfig(this.mcClasses, player.getName());
      cfg2.getConfigurationSection("Player-Stats").set("Kills", Integer.valueOf(PlayerHandler.getPlayerKills(killer) + 1));
      ConfigHandler.savePlayerConfig(this.mcClasses, killer.getName(), cfg2);
    } 
  }
}
