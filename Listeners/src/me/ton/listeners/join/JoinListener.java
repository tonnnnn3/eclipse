package me.ton.listeners.join;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.ton.listeners.Main;
import me.ton.listeners.utils.Utils;

public class JoinListener implements Listener{
	
	private Main plugin;
	
	public JoinListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this,  plugin);
	}
	
	@EventHandler
	public void onJoin (PlayerJoinEvent pj) {
		Player p = pj.getPlayer();
		
		if (!p.hasPlayedBefore()) {
			Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("firstJoin_message").replace("<player>", p.getName())));
		}
		else {
			Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("join_message").replace("<player>", p.getName())));
		}
	}

}
