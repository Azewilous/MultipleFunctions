package code.aze.leaf.mp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Recover implements CommandExecutor {
    
MultipleFunctions plugin;
	
	public Recover(MultipleFunctions passedPlugin) {
		this.plugin = passedPlugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {

		Player player = (Player) sender;
		if(player.hasPermission("mp.recover") || player.isOp()){
		if(args.length == 0){
			player.setHealth(20.0);
			player.setFoodLevel(20);
			player.setFireTicks(0);
			player.setExhaustion(20.0f);
			player.sendMessage(ChatColor.DARK_GREEN + "You Have Been Healed By The Magnificent Azewilous");	
			} else if(args.length == 1){
				if(player.getServer().getPlayer(args[0])!=null){
				Player tarpl = player.getServer().getPlayer(args[0]);
				tarpl.setHealth(20.0);
				tarpl.setFoodLevel(20);
				tarpl.setFireTicks(0);
				tarpl.setExhaustion(20.0f);
				tarpl.sendMessage(ChatColor.DARK_GREEN + "You Have Been Healed By " + player.getName());
				player.sendMessage(ChatColor.DARK_GREEN + "You Have Successfully Heales" + args[0]);
				} else {
					player.sendMessage(ChatColor.RED + "The Specified Player Is Not Online");
				}
			}
		} else 
			player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.recover "
                    + ChatColor.RED + "To Execute This Command");
		return true;
	}
}