package code.aze.leaf.mp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventory implements CommandExecutor {

	MultipleFunctions plugin;
	
	public ClearInventory(MultipleFunctions passedPlugin) {
	    this.plugin = passedPlugin;	
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
        Player player = (Player) sender;
        if(!player.hasPermission("mp.clearinv") || !player.isOp()){
           player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.clearinv "
                   + ChatColor.RED + "To Execute This Command");
        }
		if(args.length == 0){
		  player.getInventory().clear();
		} else if(args.length == 1){
			if(player.getServer().getPlayer(args[0])!=null){
			Player tarpl = player.getServer().getPlayer(args[0]);
			tarpl.getInventory().clear();
			}
		} else {
			player.sendMessage(ChatColor.RED + "This Player Is Not Online");
		}
		return true;
	}

}
