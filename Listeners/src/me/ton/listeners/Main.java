package me.ton.listeners;

import org.bukkit.plugin.java.JavaPlugin;

import me.ton.listeners.join.JoinListener;
import me.ton.listeners.join.DeathListener;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable(){
		 saveDefaultConfig();
		 
		 new JoinListener(this);
		 new DeathListener(this);
	}

}
