package me.ton.salve;

import org.bukkit.plugin.java.JavaPlugin;

import me.ton.salve.commands.SalveCommand;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable(){
		new SalveCommand(this);
	}

}
