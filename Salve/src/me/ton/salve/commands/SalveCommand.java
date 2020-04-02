package me.ton.salve.commands;
import me.ton.salve.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class SalveCommand implements CommandExecutor{
	
	private Main plugin;
	
	public SalveCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("salve").setExecutor(this);
	}
	
	@Override
	public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Somente jogadores podem usar este comando");
			return true;
		}
		Player p = (Player) sender;
		
			if (p.hasPermission("salve.use")) {
			p.sendMessage("Salve parceiro");
				return true;
			}
			else {
			p.sendMessage("Voce não tem permisão para usar este comando");
		}
		return false;
	}

}
