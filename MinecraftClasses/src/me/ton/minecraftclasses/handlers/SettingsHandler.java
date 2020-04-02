package me.ton.minecraftclasses.handlers;

import org.bukkit.configuration.file.FileConfiguration;

import me.ton.minecraftclasses.MinecraftClasses;

public class SettingsHandler {
  MinecraftClasses mcClasses;
  
  public SettingsHandler(MinecraftClasses mcClasses) {
    this.mcClasses = mcClasses;
  }

public boolean isRacesEnabled() {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    return cfg.getConfigurationSection("Settings").getBoolean("Races-Enabled");
  }
  
  public boolean isClassesEnabled() {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    return cfg.getConfigurationSection("Settings").getBoolean("Classes-Enabled");
  }
  
  public boolean onDeathReset() {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    return cfg.getConfigurationSection("Settings").getConfigurationSection("Gameplay-Settings").getBoolean("Death-Reset-Enabled");
  }
  
  public void setRacesMode(boolean mode) {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    cfg.getConfigurationSection("Settings").set("Races-Enabled", Boolean.valueOf(mode));
    ConfigHandler.saveDefaultConfig(this.mcClasses, cfg);
  }
  
  public void setClassesMode(boolean mode) {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    cfg.getConfigurationSection("Settings").set("Classes-Enabled", Boolean.valueOf(mode));
    ConfigHandler.saveDefaultConfig(this.mcClasses, cfg);
  }
  
  public void setDeathMode(boolean mode) {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    cfg.getConfigurationSection("Settings").getConfigurationSection("Gameplay-Settings").set("Death-Reset-Enabled", Boolean.valueOf(mode));
    ConfigHandler.saveDefaultConfig(this.mcClasses, cfg);
  }
}
