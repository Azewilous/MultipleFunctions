package code.aze.leaf.mp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    
	MultipleFunctions plugin;
    
    public Spawn(MultipleFunctions passedPlugin){
    	this.plugin = passedPlugin;
    }
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("mp.tpspawn") || player.isOp()){
	    if(args.length == 0){
			player.teleport(player.getWorld().getSpawnLocation());
			player.sendMessage(ChatColor.YELLOW + "Teleporting....");
		}
	} else 
		player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.tpspawn "
                + ChatColor.RED + "To Execute This Command");
     return true;
	}

}
class CreateSpawn implements CommandExecutor{

MultipleFunctions plugin;

	public CreateSpawn(MultipleFunctions passedPlugin){
		this.plugin = passedPlugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		
		Player player = (Player) sender;
		World loc = player.getWorld();
		
		if(player.hasPermission("mp.createspawn") || player.isOp()){
		if(args.length == 0){
		loc.setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY() , player.getLocation().getBlockZ());
		Bukkit.broadcastMessage(ChatColor.GOLD + "New World Spawn Set By " + player.getName());
		}
	} else 
		player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.createspawn "
            + ChatColor.RED + "To Execute This Command");
		return true;
	}
	
}