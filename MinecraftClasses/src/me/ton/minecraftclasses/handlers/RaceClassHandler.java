package me.ton.minecraftclasses.handlers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import me.ton.minecraftclasses.MinecraftClasses;
import me.ton.minecraftclasses.objects.PlayerClass;

public class RaceClassHandler {
  MinecraftClasses mcClasses;
  
  public RaceClassHandler(MinecraftClasses mcClasses) {
    this.mcClasses = mcClasses;
  }
  
  public List<String> getRaces() {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    return cfg.getConfigurationSection("Races-Classes").getStringList("Races");
  }
  
  public ArrayList<String> getClasses() {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    ArrayList<String> classes = (ArrayList<String>)cfg.getConfigurationSection("Races-Classes").getStringList("Classes");
    return classes;
  }
  
  public void setRaceList() {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    List<String> races = new ArrayList<>();
    races.add("Human");
    races.add("Elf");
    races.add("Orc");
    races.add("Troll");
    cfg.getConfigurationSection("Races-Classes").set("Races", races);
    ConfigHandler.saveDefaultConfig(this.mcClasses, cfg);
  }
  
  public void setClassList() {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    List<String> classes = new ArrayList<>();
    classes.add("Warrior");
    classes.add("Priest");
    classes.add("Mage");
    classes.add("Archer");
    ConfigHandler.saveDefaultConfig(this.mcClasses, cfg);
  }
  
  public void addClasses() {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    String defaultDescription = "A default class from [MC_Classes].";
    List<String> classes = cfg.getConfigurationSection("Races-Classes").getStringList("Classes");
    for (String s : classes)
      addClass(new PlayerClass(s, s, defaultDescription, false, 0.0D)); 
  }
  
  public boolean doesClassExist(String name) {
    FileConfiguration cfg = ConfigHandler.getDefaultConfig(this.mcClasses);
    List<String> classes = cfg.getConfigurationSection("Races-Classes").getStringList("Classes");
    return classes.contains(name);
  }
  
  public void addClass(PlayerClass playerClass) {
    File defaultFiles = new File(MinecraftClasses.getInstance().getDataFolder(), "Classes");
    File checkFile = new File(defaultFiles, playerClass.getName() + ".yml");
    FileConfiguration cfg = ConfigHandler.getClassConfig(this.mcClasses, playerClass.getName());
    if (checkFile.exists()) {
      List<ItemStack> items = getItemsFromConfig(playerClass);
      for (ItemStack i : items)
        playerClass.addItem(i); 
    } else {
      cfg.createSection("Properties");
      cfg.getConfigurationSection("Properties").set("Name", playerClass.getName());
      cfg.getConfigurationSection("Properties").set("Display-Name", playerClass.getDisplayName());
      cfg.getConfigurationSection("Properties").set("Description", playerClass.getDescription());
      cfg.getConfigurationSection("Properties").set("Cost", Double.valueOf(playerClass.getCost()));
      cfg.createSection("Items");
      cfg.createSection("Settings");
      cfg.getConfigurationSection("Settings").set("Cost-Required", Boolean.valueOf(playerClass.isCostRequired()));
      cfg.getConfigurationSection("Settings").set("Has-Arrows", Boolean.valueOf(false));
      cfg.getConfigurationSection("Settings").set("Arrow-Stacks", Integer.valueOf(0));
      if (playerClass.getName().equalsIgnoreCase("Warrior")) {
        cfg.getConfigurationSection("Items").set("Helmet", "IRON_HELMET");
        cfg.getConfigurationSection("Items").set("Chestplate", "IRON_CHESTPLATE");
        cfg.getConfigurationSection("Items").set("Leggings", "IRON_LEGGINGS");
        cfg.getConfigurationSection("Items").set("Boots", "IRON_BOOTS");
        cfg.getConfigurationSection("Items").set("Weapon", "DIAMOND_SWORD");
        cfg.getConfigurationSection("Items").createSection("Enchants");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Helmet", "FIRE_RESISTANCE_III");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Helmet", "PROTECTION_II");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Chestplate", "PROTECTION_II");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Leggings", "NONE");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Boots", "NONE");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Weapon", "NONE");
        playerClass.addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
        playerClass.addItem(new ItemStack(Material.IRON_HELMET, 1));
        playerClass.addItem(new ItemStack(Material.IRON_CHESTPLATE, 1));
        playerClass.addItem(new ItemStack(Material.IRON_LEGGINGS, 1));
        playerClass.addItem(new ItemStack(Material.IRON_BOOTS, 1));
      } else {
        cfg.getConfigurationSection("Items").set("Helmet", "NONE");
        cfg.getConfigurationSection("Items").set("Chestplate", "NONE");
        cfg.getConfigurationSection("Items").set("Leggings", "NONE");
        cfg.getConfigurationSection("Items").set("Boots", "NONE");
        cfg.getConfigurationSection("Items").set("Weapon", "NONE");
        cfg.getConfigurationSection("Items").createSection("Enchants");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Helmet", "NONE");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Chestplate", "NONE");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Leggings", "NONE");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Boots", "NONE");
        cfg.getConfigurationSection("Items").getConfigurationSection("Enchants").set("Weapon", "NONE");
      } 
      ConfigHandler.saveClassConfig(this.mcClasses, playerClass.getName(), cfg);
    } 
  }
  
  public List<ItemStack> getItemsFromConfig(PlayerClass playerClass) {
    FileConfiguration cfg = ConfigHandler.getClassConfig(this.mcClasses, playerClass.getName());
    List<ItemStack> items = new ArrayList<>();
    if (cfg.getConfigurationSection("Settings").getBoolean("Has-Arrows"))
      for (int i = 0; i < cfg.getConfigurationSection("Settings").getInt("Arrow-Stacks"); i++)
        items.add(new ItemStack(Material.ARROW, 64));  
    if (!cfg.getConfigurationSection("Items").getString("Helmet").equalsIgnoreCase("NONE")) {
      ItemStack helmet = new ItemStack(Material.getMaterial(cfg.getConfigurationSection("Items").getString("Helmet")), 1);
      items.add(helmet);
    } 
    if (!cfg.getConfigurationSection("Items").getString("Chestplate").equalsIgnoreCase("NONE")) {
      ItemStack chestplate = new ItemStack(Material.getMaterial(cfg.getConfigurationSection("Items").getString("Chestplate")), 1);
      items.add(chestplate);
    } 
    if (!cfg.getConfigurationSection("Items").getString("Leggings").equalsIgnoreCase("NONE")) {
      ItemStack leggings = new ItemStack(Material.getMaterial(cfg.getConfigurationSection("Items").getString("Leggings")), 1);
      items.add(leggings);
    } 
    if (!cfg.getConfigurationSection("Items").getString("Boots").equalsIgnoreCase("NONE")) {
      ItemStack boots = new ItemStack(Material.getMaterial(cfg.getConfigurationSection("Items").getString("Boots")), 1);
      items.add(boots);
    } 
    if (!cfg.getConfigurationSection("Items").getString("Weapon").equalsIgnoreCase("NONE")) {
      ItemStack weapon = new ItemStack(Material.getMaterial(cfg.getConfigurationSection("Items").getString("Weapon")), 1);
      items.add(weapon);
    } 
    return items;
  }
}
