package me.ton.minecraftclasses.objects.managers;

import java.util.ArrayList;
import java.util.UUID;

import me.ton.minecraftclasses.MinecraftClasses;
import me.ton.minecraftclasses.objects.PlayerClass;
import me.ton.minecraftclasses.player.MccPlayer;

public class PlayerManager {
  public static ArrayList<MccPlayer> players = new ArrayList<>();
  
  public static ArrayList<PlayerClass> playerClasses = new ArrayList<>();
  
  private static String addPlayerError = "[MC_Classes] - ERROR: Player already exists! Tried adding player to list when already exists!";
  
  private static String removePlayerError = "[MC_Classes] - ERROR: Player doesn't exist! Tried remove player from a list that it wasn't in!";
  
  private static String addClassError = "[MC_Classes] - ERROR: Class already exists! Tried adding class to list when already exists!";
  
  private static String removeClassError = "[MC_Classes] - ERROR: Class doesn't exist! Tried remove class from a list that it wasn't in!";
  
  MinecraftClasses mcclasses;
  
  public PlayerManager(MinecraftClasses mcclasses) {
    this.mcclasses = mcclasses;
  }
  
  public static void addPlayer(MccPlayer player) {
    if (doesPlayerExist(player)) {
      System.out.println(addPlayerError);
    } else {
      players.add(player);
    } 
  }
  
  private static boolean doesPlayerExist(MccPlayer player) {
    return players.contains(player);
  }
  
  public static void removePlayer(MccPlayer player) {
    if (!doesPlayerExist(player)) {
      System.out.println(removePlayerError);
    } else {
      ArrayList<MccPlayer> playersClone = (ArrayList<MccPlayer>)players.clone();
      playersClone.remove(player);
      players = playersClone;
    } 
  }
  
  public static void addClass(PlayerClass playerClass) {
    if (doesClassExist(playerClass)) {
      System.out.println(addClassError);
    } else {
      playerClasses.add(playerClass);
      System.out.println("[Minecraft_Classes] - ADDED CLASS " + playerClass.getName().toUpperCase() + ".");
    } 
  }
  
  private static boolean doesClassExist(PlayerClass playerClass) {
    return playerClasses.contains(playerClass);
  }
  
  public static void removeClass(PlayerClass playerClass) {
    if (!doesClassExist(playerClass)) {
      System.out.println(removeClassError);
    } else {
      ArrayList<PlayerClass> playerClassesClone = (ArrayList<PlayerClass>)playerClasses.clone();
      playerClassesClone.remove(playerClass);
      playerClasses = playerClassesClone;
    } 
  }
  
  public static PlayerClass getPlayerClass(String name) {
    for (PlayerClass pc : playerClasses) {
      if (pc.getName().equalsIgnoreCase(name))
        return pc; 
    } 
    return null;
  }
  
  public static MccPlayer getPlayer(UUID uuid) {
    for (MccPlayer mccPlayer : players) {
      if (mccPlayer.getUuid() == uuid)
        return mccPlayer; 
    } 
    return null;
  }
}
