package me.ton.minecraftclasses;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.ton.minecraftclasses.commands.CommandManager;
import me.ton.minecraftclasses.events.ServerJoinEvent;
import me.ton.minecraftclasses.handlers.ConfigHandler;
import me.ton.minecraftclasses.handlers.RaceClassHandler;
import me.ton.minecraftclasses.handlers.SettingsHandler;


public class MinecraftClasses extends JavaPlugin {
  private static MinecraftClasses instance;
  
  public static RaceClassHandler raceClassHandler;

public static Object raceClassHandler1;
  
  ServerJoinEvent serverJoinEvent;
  
  CommandManager commandManager;
  
  public static MinecraftClasses getInstance() {
    return instance;
  }
  
  public void onEnable() {
    this.commandManager = new CommandManager(this);
    raceClassHandler = new RaceClassHandler(this);
    instance = this;
    checkDefaultConfig();
    this.serverJoinEvent = new ServerJoinEvent(this);
    Bukkit.getServer().getPluginManager().registerEvents((Listener)this.serverJoinEvent, (Plugin)instance);
    raceClassHandler.addClasses();
    getCommand("Class").setExecutor((CommandExecutor)this.commandManager);
  }
  
  private void checkDefaultConfig() {
    File defaultFiles = new File(getInstance().getDataFolder(), "Settings");
    File checkFile = new File(defaultFiles, "config.yml");
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this);
    if (!checkFile.exists()) {
      SettingsHandler settingsHandler = new SettingsHandler(this);
      cfg.createSection("Settings");
      cfg.createSection("Races-Classes");
      cfg.getConfigurationSection("Settings").createSection("Gameplay-Settings");
      raceClassHandler.setClassList();
      raceClassHandler.setRaceList();
      ConfigHandler.saveDefaultConfig(this, cfg);
      settingsHandler.setClassesMode(true);
      settingsHandler.setRacesMode(true);
      settingsHandler.setDeathMode(true);
    } 
  }
  
  public void onDisable() {
    getServer().getPluginManager();
  }
  }
