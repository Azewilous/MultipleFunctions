package code.aze.leaf.mp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EventToggler implements CommandExecutor {

MultipleFunctions plugin;
	public EventToggler(MultipleFunctions multipleFunctions){
		this.plugin = multipleFunctions;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {

		if(!(sender instanceof Player)){
			sender.sendMessage("Must Be A Player To Execute This Command");
		}
		
		Player player = (Player) sender;
		if(!player.hasPermission("mp.toggle")){
			player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.toggle "
                    + ChatColor.RED + "To Execute This Command");
			return false;
		}
		
		if(args.length == 0){
			player.sendMessage(ChatColor.RED + "Too Few Arguments");
		}
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("doublejump") || args[0].equalsIgnoreCase("dj")){
				if(!(plugin.doubleJumpToggled.contains(player))){
					plugin.doubleJumpToggled.add(player);
					player.sendMessage(ChatColor.GREEN + "Toggled On!");
				} else 
					plugin.doubleJumpToggled.remove(player);
					player.sendMessage(ChatColor.DARK_PURPLE + "Toggled Off!");
			} else 
				if(args[0].equalsIgnoreCase("strike") || args[0].equalsIgnoreCase("lightning")){
					if(!(plugin.strikeToggled.contains(player))){
						plugin.strikeToggled.add(player);
						player.sendMessage(ChatColor.GREEN + "Toggled On!");
					} else
						plugin.strikeToggled.remove(player);
						player.sendMessage(ChatColor.DARK_PURPLE + "Toggle Off!");
				} else
					if(args[0].equalsIgnoreCase("explode") || args[0].equalsIgnoreCase("explosion")){
						if(!(plugin.explosionToggled.contains(player))){
							plugin.explosionToggled.add(player);
							player.sendMessage(ChatColor.GREEN + "Toggled On!");
						} else
						plugin.explosionToggled.remove(player);
						player.sendMessage(ChatColor.DARK_PURPLE + "Toggled Off!");
					} else
						if(args[0].equalsIgnoreCase("weather") || args[0].equalsIgnoreCase("storm")){
							if(!(plugin.weatherToggled.contains(player))){
								plugin.weatherToggled.add(player);
								player.sendMessage(ChatColor.GREEN + "Toggled On!");
							} else
								plugin.weatherToggled.remove(player);
								player.sendMessage(ChatColor.DARK_PURPLE + "Toggled Off!");
						} else 
							if(args[0].equalsIgnoreCase("timechange") || args[0].equalsIgnoreCase("time")){
								if(!(plugin.setTimeToggled.contains(player))){
									plugin.setTimeToggled.add(player);
									player.sendMessage(ChatColor.GREEN + "Toggled On!");
								} else
									plugin.setTimeToggled.remove(player);
									player.sendMessage(ChatColor.DARK_PURPLE + "Toggled Off!");
							} else
								if(args[0].equalsIgnoreCase("teleportbow") || args[0].equalsIgnoreCase("telebow")){
									if(!(plugin.teleportBowToggled.contains(player))){
										plugin.teleportBowToggled.add(player);
										player.sendMessage(ChatColor.GREEN + "Toggled On!");
									} else
										plugin.teleportBowToggled.remove(player);
										player.sendMessage(ChatColor.DARK_PURPLE + "Toggled Off!");
								}
		}
		
		return false;
	}

}
