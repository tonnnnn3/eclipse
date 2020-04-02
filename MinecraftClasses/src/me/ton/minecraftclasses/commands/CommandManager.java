package me.ton.minecraftclasses.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.ton.minecraftclasses.MinecraftClasses;
import me.ton.minecraftclasses.handlers.ConfigHandler;
import me.ton.minecraftclasses.handlers.RaceClassHandler;
import me.ton.minecraftclasses.objects.managers.PlayerManager;

public class CommandManager implements CommandExecutor {
  MinecraftClasses mcClasses;
  
  RaceClassHandler raceClassHandler;
  
  public CommandManager(MinecraftClasses mcClasses) {
    this.mcClasses = mcClasses;
    this.raceClassHandler = new RaceClassHandler(mcClasses);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("You must be a player to use this command!");
    } else {
      Player player = Bukkit.getPlayer(((Player)sender).getUniqueId());
      if (Label.equalsIgnoreCase("Class"))
        classCommand(player, cmd, Label, args); 
    } 
    return true;
  }
  
  private void classCommand(Player player, Command command, String label, String[] args) {
    if (this.raceClassHandler.getClasses().contains(args[0])) {
      FileConfiguration cfg = ConfigHandler.getPlayerConfig(this.mcClasses, player.getName());
      if (cfg.getConfigurationSection("Player-Stats").get("Class").equals(args[0])) {
        player.sendMessage(ChatColor.RED + "Error: " + ChatColor.YELLOW + "You are already class: " + args[0] + "!");
      } else {
        cfg.getConfigurationSection("Player-Stats").set("Class", args[0]);
        ConfigHandler.savePlayerConfig(this.mcClasses, player.getName(), cfg);
        player.sendMessage(ChatColor.GREEN + "You have selected the " + ChatColor.YELLOW + args[0] + ChatColor.GREEN + " class!");
        PlayerManager.getPlayer(player.getUniqueId()).setPlayerClass(PlayerManager.getPlayerClass(args[0]));
        player.getInventory().clear();
        PlayerManager.getPlayer(player.getUniqueId()).giveClassItems();
      } 
    } else {
      player.sendMessage(ChatColor.RED + "Error: " + ChatColor.YELLOW + "That class does not exist!");
    } 
  }
}
