package me.ton.minecraftclasses.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.ton.minecraftclasses.MinecraftClasses;
import me.ton.minecraftclasses.handlers.PlayerHandler;
import me.ton.minecraftclasses.objects.PlayerClass;
import me.ton.minecraftclasses.objects.managers.PlayerManager;

public class MccPlayer {
  UUID uuid;
  
  PlayerClass playerClass;
  
  public MccPlayer(UUID uuid, PlayerClass playerClass) {
    this.uuid = uuid;
    this.playerClass = playerClass;
  }
  
  public MccPlayer(UUID uuid) {
    this.uuid = uuid;
    for (String s : MinecraftClasses.raceClassHandler.getClasses()) {
      if (s.equalsIgnoreCase(PlayerHandler.getPlayerClass(Bukkit.getPlayer(uuid)))) {
        this.playerClass = PlayerManager.getPlayerClass(s);
        break;
      } 
    } 
  }
  
  public void giveClassItems() {
    Player player = Bukkit.getPlayer(this.uuid);
    if (!this.playerClass.getItems().isEmpty())
      for (ItemStack item : this.playerClass.getItems()) {
        player.getInventory().addItem(new ItemStack[] { item });
      }  
  }
  
  public PlayerClass getPlayerClass() {
    return this.playerClass;
  }
  
  public void setPlayerClass(PlayerClass playerClass) {
    this.playerClass = playerClass;
  }
  
  public UUID getUuid() {
    return this.uuid;
  }
}
