package code.aze.leaf.mp;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModes{	
}

class Creative implements CommandExecutor {

	MultipleFunctions plugin;

	public Creative(MultipleFunctions passedPlugin) {
		this.plugin = passedPlugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("mp.creative") || player.isOp()){
		if(args.length == 0){
			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage(ChatColor.YELLOW + "GameMode Updated");
		} else if(args.length == 1){
			if(player.getServer().getPlayer(args[0])!=null){
			Player tarpl = player.getServer().getPlayer(args[0]);
			tarpl.setGameMode(GameMode.CREATIVE);
			tarpl.sendMessage(ChatColor.DARK_GREEN + "Your GameMode Has Been Changed to Creative By " + player.getName());
			player.sendMessage(ChatColor.DARK_GREEN + "You Have Successfully Changed " + args[0]+"' " + ChatColor.DARK_GREEN + " Gamemode");
			} else {
				player.sendMessage(ChatColor.RED + "The Specified Player Is Not Online");
			}
		}
	} else player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.creative "
                   + ChatColor.RED + "To Execute This Command");
		return true;
	}

}

class Survival implements CommandExecutor{

	
MultipleFunctions plugin;
	
	public Survival(MultipleFunctions passedPlugin) {
		this.plugin = passedPlugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("mp.survival") || player.isOp()){
		if(args.length == 0){
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage(ChatColor.YELLOW + "GameMode Updated");
		} else if(args.length == 1){
			if(player.getServer().getPlayer(args[0])!=null){
			Player tarpl = player.getServer().getPlayer(args[0]);
			tarpl.setGameMode(GameMode.SURVIVAL);
			tarpl.sendMessage(ChatColor.DARK_GREEN + "Your GameMode Has Been Changed to Survival By " + player.getName());
			player.sendMessage(ChatColor.DARK_GREEN + "You Have Successfully Changed " + args[0]+"' " + ChatColor.DARK_GREEN + " Gamemode");
			} else {
				player.sendMessage(ChatColor.RED + "The Specified Player Is Not Online");
			}
		}
	} else player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.survival "
            + ChatColor.RED + "To Execute This Command");
		return true;
	}
}
class Adventure implements CommandExecutor{

MultipleFunctions plugin;
	
	public Adventure(MultipleFunctions passedPlugin) {
		this.plugin = passedPlugin;
	}
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
				String[] args) {
			Player player = (Player) sender;
			if(player.hasPermission("mp.adventure") || player.isOp()){
			if(args.length == 0){
				player.setGameMode(GameMode.ADVENTURE);
				player.sendMessage(ChatColor.YELLOW + "GameMode Updated");
			} else if(args.length == 1){
				if(player.getServer().getPlayer(args[0])!=null){
					Player tarpl = player.getServer().getPlayer(args[0]);
					tarpl.setGameMode(GameMode.ADVENTURE);
					tarpl.sendMessage(ChatColor.DARK_GREEN + "Your GameMode Has Been Changed to Adventure By " + player.getName());
					player.sendMessage(ChatColor.DARK_GREEN + "You Have Successfully Changed " + args[0]+"' " + ChatColor.DARK_GREEN + " Gamemode");
					} else {
						player.sendMessage(ChatColor.RED + "The Specified Player Is Not Online");		
					}
			}
		} else player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.creative "
                + ChatColor.RED + "To Execute This Command");
			return true;
		}
	}
class Spectator implements CommandExecutor{

MultipleFunctions plugin;

public Spectator(MultipleFunctions passedPlugin) {
	this.plugin = passedPlugin;
}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("mp.spectator") || player.isOp()){
		if(args.length == 0){
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage(ChatColor.YELLOW + "GameMode Updated");
		} else if(args.length == 1){
			if(player.getServer().getPlayer(args[0])!=null){
			Player tarpl = player.getServer().getPlayer(args[0]);
			tarpl.setGameMode(GameMode.SPECTATOR);
			tarpl.sendMessage(ChatColor.DARK_GREEN + "Your GameMode Has Been Changed to Spectator By " + player.getName());
			player.sendMessage(ChatColor.DARK_GREEN + "You Have Successfully Changed " + args[0]+"' " + ChatColor.DARK_GREEN + " Gamemode");
			} else {
				player.sendMessage(ChatColor.RED + "The Specified Player Is Not Online");
			}
		}
	} else player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.creative "
            + ChatColor.RED + "To Execute This Command");
		return true;
			}
}