package code.aze.leaf.mp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayNightCycle implements CommandExecutor {

MultipleFunctions plugin;
	
	public DayNightCycle(MultipleFunctions passedPlugin) {
		this.plugin = passedPlugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		Player player =(Player) sender;
        if(!player.hasPermission("mp.day") || player.isOp()){
            player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.day "
                    + ChatColor.RED + "To Execute This Command");
        }

        if(args.length == 0){
         
            player.getLocation().getWorld().setTime(0);
            player.sendMessage(ChatColor.GREEN + "Time set to day.");
	}
    return true;
	} 
}
class Night implements CommandExecutor{

	MultipleFunctions plugin;
	
	public Night(MultipleFunctions passedPlugin) {
		this.plugin = passedPlugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args){ 
Player player =(Player) sender;

        if(!player.hasPermission("mp.night") || player.isOp()){
            player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.night "
                    + ChatColor.RED + "To Execute This Command");
        }

        if(args.length == 0){
         
            player.getLocation().getWorld().setTime(16000);
            player.sendMessage(ChatColor.GREEN + "Time set to Night.");
		
	}
    return true;
	}
}