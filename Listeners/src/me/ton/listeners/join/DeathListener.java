package me.ton.listeners.join;

import org.bukkit.Bukkit;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import me.ton.listeners.Main;
import me.ton.listeners.utils.Utils;

public class DeathListener implements Listener{
	
	private Main plugin;
	
	public DeathListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this,  plugin);
	}
	
	@EventHandler
	public void onPlayerDeath (PlayerDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			Player killer = e.getEntity().getKiller();
			Player p = e.getEntity();
			
			killer.sendMessage(Utils.chat(plugin.getConfig().getString("killer_message").replace("<player>", p.getName())));
			p.sendMessage(Utils.chat(plugin.getConfig().getString("killed_message").replace("<player>", killer.getName())));
			if (e.getEntity().getKiller() instanceof Boss) {
		}
		}
	}

}
