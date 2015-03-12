package code.aze.leaf.mp;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hidden implements Listener, CommandExecutor {

    MultipleFunctions plugin;

    public Hidden(MultipleFunctions passedPlugin) {
        this.plugin = passedPlugin;
    }
    
    private ArrayList<Player> vanished = new ArrayList<Player>();
    
    public boolean onCommand(CommandSender sender, Command cmd, String commmandLabel, String args[]){
    	
    	if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Must Be A Player To Vanish");
            return true;
    	}	
    	Player player = (Player) sender;
    	if(player.hasPermission("mp.hide") || player.isOp()){
    	if(args.length == 0){
    	if (!vanished.contains(player)) {
            for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                    pl.hidePlayer(player);
                    pl.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 1));
            }
            vanished.add(player);
            player.sendMessage(ChatColor.GREEN + "You have been vanished!");
            return true;
    		} else 
    			for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                pl.showPlayer(player);
				}
        		vanished.remove(player);
        		player.sendMessage(ChatColor.GREEN + "You have been unvanished!");
        		player.removePotionEffect(PotionEffectType.INVISIBILITY);
    	}
    } else 
    	player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.hide "
            + ChatColor.RED + "To Execute This Command");
		return true;
    }

}
