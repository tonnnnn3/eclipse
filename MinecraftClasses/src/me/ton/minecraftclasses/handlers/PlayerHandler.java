package me.ton.minecraftclasses.handlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.ton.minecraftclasses.MinecraftClasses;

public class PlayerHandler {
  static MinecraftClasses mcClasses;
  
  public PlayerHandler(MinecraftClasses mcClassess) {
    mcClasses = mcClassess;
  }
  
  public static String getPlayerClass(Player player) {
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(mcClasses, player.getName());
    return cfg.getConfigurationSection("Player-Stats").getString("Class");
  }
  
  public static String getPlayerRace(Player player) {
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(mcClasses, player.getName());
    return cfg.getConfigurationSection("Player-Stats").getString("Race");
  }
  
  public static int getPlayerKills(Player player) {
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(mcClasses, player.getName());
    return cfg.getConfigurationSection("Player-Stats").getInt("Kills");
  }
  
  public static int getPlayerDeaths(Player player) {
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(mcClasses, player.getName());
    return cfg.getConfigurationSection("Player-Stats").getInt("Deaths");
  }
  
  public static int getPlayerLevel(Player player) {
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(mcClasses, player.getName());
    return cfg.getConfigurationSection("Player-Stats").getInt("Level");
  }
  
  public static void setPlayerClass(Player player, String className) {
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(mcClasses, player.getName());
    cfg.getConfigurationSection("Player-Stats").set("Class", className);
    ConfigHandler.savePlayerConfig(mcClasses, player.getName(), cfg);
  }
  
  public static void setPlayerRace(Player player, String raceName) {
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(mcClasses, player.getName());
    cfg.getConfigurationSection("Player-Stats").set("Race", raceName);
    ConfigHandler.savePlayerConfig(mcClasses, player.getName(), cfg);
  }
  
  public static void setPlayerKills(Player player, int amount) {
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(mcClasses, player.getName());
    cfg.getConfigurationSection("Player-Stats").set("Kills", Integer.valueOf(amount));
    ConfigHandler.savePlayerConfig(mcClasses, player.getName(), cfg);
  }
  
  public static void setPlayerDeaths(Player player, int amount) {
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(mcClasses, player.getName());
    cfg.getConfigurationSection("Player-Stats").set("Deaths", Integer.valueOf(amount));
    ConfigHandler.savePlayerConfig(mcClasses, player.getName(), cfg);
  }
  
  public static void setPlayerLevel(Player player, int amount) {
    FileConfiguration cfg = ConfigHandler.getPlayerConfig(mcClasses, player.getName());
    cfg.getConfigurationSection("Player-Stats").set("Level", Integer.valueOf(amount));
    ConfigHandler.savePlayerConfig(mcClasses, player.getName(), cfg);
  }

}
