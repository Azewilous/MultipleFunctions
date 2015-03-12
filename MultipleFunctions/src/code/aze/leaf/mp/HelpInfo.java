package code.aze.leaf.mp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpInfo implements CommandExecutor {

	MultipleFunctions plugin;

    public HelpInfo(MultipleFunctions passedPlugin) {
        this.plugin = passedPlugin;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String args[]) {
		
		Player player = (Player) sender;
		
		if(player.hasPermission("mp.help")){
		player.sendMessage(ChatColor.AQUA + "==========Multiple Function by: Azewilous=========" 
				+ "\n/crea <playername> : sets player in creative mode"
				+ "\n/surv <playername> : sets player in survival mode"
				+ "\n/adven <playername> : sets player in adventure mode"
				+ "\n/spect <plyername> : sets player in adventure mode"
				+ "\n/find <playername> : teleports to a player"
				+ "\n/recover <playername> : heals a player"
				+ "\n/empty <playername> : clears a players inventory"
				+ "\n/morning : sets the time to morning"
				+ "\n/evening : sets the time to evening"
				+ "\n/thunder : create a thunder storm"
				+ "\n/stormend: stops a strom"
				+ "\n/tpspawn : teleports player to spawn broken"
				+ "\n/hide : vanishes and unvanishes players"
				+ "\n/mpreload : reloads the plugin"
				+ "\n/mpversion : displays the version of the plugin"
				+ "\n==========Multiple Function by: Azewilous=========");
		} else 
			player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.help "
                    + ChatColor.RED + "To Execute This Command");
		return true;
	}

}
