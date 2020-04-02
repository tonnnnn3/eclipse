package me.ton.classes;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import joptsimple.internal.Classes;


public class Main extends JavaPlugin implements Listener {

	public static Inventory classes;

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		createInventory();
	}

	@Override
	public void onDisable() {

	}

			@Override
			public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
				if (label.equalsIgnoreCase("classes")) {
					if (!(sender instanceof Player)) {
						sender.sendMessage("Somente jogadores podem usar este comando");
						return true;
					}
					Player player = (Player) sender;
					player.openInventory(classes);
					return true;
				}

				return false;

			}
			
	
	private void createInventory() {
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.BLACK + "Classes");
		ItemStack item = new ItemStack(Material.SHIELD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Paladin");
		List<String> lore1 = new ArrayList<>();
		lore1.add("test1");
		lore1.add(ChatColor.RED + "test1");
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		inv.setItem(11, item);

		ItemStack item1 =new ItemStack(Material.BOW);
		ItemMeta meta1 = item1.getItemMeta();
		meta1.setDisplayName(ChatColor.GREEN + "Archer");
		List<String> lore2 = new ArrayList<>();
		lore2.add("test1");
		lore2.add(ChatColor.RED + "test1");
		meta1.addEnchant(Enchantment.DURABILITY, 1, true);
		meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta1);
		inv.setItem(12, item1);

		ItemStack item2 =new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.GREEN + "Warrior");
		List<String> lore3 = new ArrayList<>();
		lore3.add("test1");
		lore3.add(ChatColor.RED + "test1");
		meta2.addEnchant(Enchantment.DURABILITY, 1, true);
		meta2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item2.setItemMeta(meta2);
		inv.setItem(13, item2);

		ItemStack item3 =new ItemStack(Material.DIAMOND_SHOVEL);
		ItemMeta meta3 = item3.getItemMeta();
		meta3.setDisplayName(ChatColor.GREEN + "Gravedigger");
		List<String> lore4 = new ArrayList<>();
		lore4.add("test1");
		lore4.add(ChatColor.RED + "test1");
		meta3.addEnchant(Enchantment.DURABILITY, 1, true);
		meta3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item3.setItemMeta(meta3);
		inv.setItem(14, item3);

		ItemStack item4 =new ItemStack(Material.STICK);
		ItemMeta meta4 = item4.getItemMeta();
		meta4.setDisplayName(ChatColor.GREEN + "Priest");
		List<String> lore5 = new ArrayList<>();
		lore5.add("test1");
		lore5.add(ChatColor.RED + "test1");
		meta4.addEnchant(Enchantment.DURABILITY, 1, true);
		meta4.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item4.setItemMeta(meta4);
		inv.setItem(15, item4);

		classes = inv;
	}



	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().contains("classes"))
			return;
		if (event.getCurrentItem() == null) {
			return;
		}
		if (event.getCurrentItem().getItemMeta() == null)
			return;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		if (event.getClickedInventory().getType() == InventoryType.PLAYER)
			return;

		if (event.getSlot() == 11) {
			if (!player.hasPermission("classes.Paladin"))
				player.sendMessage("You do not have permission to pick this class");
			return;
		}
		player.sendMessage("You choose Paladin Class");
		player.closeInventory();
		player.updateInventory();

		if (event.getSlot() == 12) {
			if (!player.hasPermission("Classes.Archer"))
				player.sendMessage("You do not have permission to pick this class");
			return;
		}
		player.sendMessage("You choose Archer Class");
		player.closeInventory();
		player.updateInventory();

		if (event.getSlot() == 13) {
			if (!player.hasPermission("Classes.Warrior"))
				player.sendMessage("You do not have permission to pick this class");
			return;
		}
		player.sendMessage("You choose Warrior Class");
		player.closeInventory();
		player.updateInventory();

		if (event.getSlot() == 14) {
			if (!player.hasPermission("Classes.Gravedigger"))
				player.sendMessage("You do not have permission to pick this class");
			return;
		}
		player.sendMessage("You choose Gravedigger Class");
		player.closeInventory();
		player.updateInventory();

		if (event.getSlot() == 15) {
			if (!player.hasPermission("Classes.Priest"))
				player.sendMessage("You do not have permission to pick this class");
			return;
		}
		player.sendMessage("You choose Priest Class");
		player.closeInventory();
		player.updateInventory();

	}

}
