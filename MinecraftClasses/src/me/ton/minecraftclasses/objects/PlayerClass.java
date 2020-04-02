package me.ton.minecraftclasses.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.ton.minecraftclasses.objects.managers.PlayerManager;

public class PlayerClass {
  String name;
  
  String displayName;
  
  String description;
  
  boolean isCostRequired;
  
  double cost;
  
  List<ItemStack> items;
  
  public PlayerClass(String name, String displayName, String description, boolean isCostRequired, double cost, List<ItemStack> items) {
    this.name = name;
    this.displayName = displayName;
    this.description = description;
    this.isCostRequired = isCostRequired;
    this.cost = cost;
    this.items = items;
  }
  
  public PlayerClass(String name, String displayName, String description, boolean isCostRequired, double cost) {
    this.name = name;
    this.displayName = displayName;
    this.description = description;
    this.isCostRequired = isCostRequired;
    this.cost = cost;
    this.items = new ArrayList<>();
    PlayerManager.addClass(this);
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getDisplayName() {
    return this.displayName;
  }
  
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public boolean isCostRequired() {
    return this.isCostRequired;
  }
  
  public void setCostRequired(boolean costRequired) {
    this.isCostRequired = costRequired;
  }
  
  public double getCost() {
    return this.cost;
  }
  
  public void setCost(double cost) {
    this.cost = cost;
  }
  
  public void addItem(ItemStack item) {
    if (this.items.contains(item)) {
      if (item.getType().equals(Material.ARROW)) {
        this.items.add(item);
        System.out.println("[MC_CLASSES] - CLASS " + getName() + " - ITEM " + item.getType().name().toUpperCase() + " ADDED");
      } else {
        System.out.println("ERROR: Item already stored in class.");
      } 
    } else {
      this.items.add(item);
      System.out.println("[MC_CLASSES] - CLASS " + getName() + " - ITEM " + item.getType().name().toUpperCase() + " ADDED");
    } 
  }
  
  public void removeItem(Material material) {
    List<ItemStack> itemsClone = this.items;
    for (ItemStack item : itemsClone) {
      if (item.getType() == material) {
        itemsClone.remove(item);
        System.out.println("[MC_CLASSES] - ITEM REMOVED");
        break;
      } 
    } 
    this.items = itemsClone;
  }
  
  public void removeItem(ItemStack itemStack) {
    List<ItemStack> itemsClone = this.items;
    for (ItemStack item : itemsClone) {
      if (item.equals(itemStack)) {
        itemsClone.remove(item);
        System.out.println("[MC_CLASSES] - ITEM REMOVED");
        break;
      } 
    } 
    this.items = itemsClone;
  }
  
  public List<ItemStack> getItems() {
    return this.items;
  }
  
  public void setItems(List<ItemStack> items) {
    this.items = items;
  }
}
