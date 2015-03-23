package code.aze.leaf.mp;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@SuppressWarnings("deprecation")
public class GenericFunctions implements Listener {

MultipleFunctions plugin;
	public GenericFunctions(MultipleFunctions multipleFunctions){
		this.plugin = multipleFunctions;
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    if(player.hasPermission("mp.strike") || player.isOp()){
	    	if(!(plugin.strikeToggled.contains(player))){
	    		return;
	    	}
	    if (player.getInventory().getItemInHand().getTypeId() == plugin.getConfig().getInt("MultipleFunction.Lightning.Material")) {
	        player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>)null, 200).getLocation());
	    }
	}    
  }	
	@EventHandler
	public void onPlayerInteractEvent1(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    if(player.hasPermission("mp.explosion") || player.isOp()){
	    	if(!(plugin.explosionToggled.contains(player))){
	    		return;
	    	}
	    if (player.getItemInHand().getType().getId() == plugin.getConfig().getInt("MultipleFunction.Explosion.Material")) {
	        player.getWorld().createExplosion(player.getTargetBlock((Set<Material>)null, 200).getLocation(), 10);
	    }
	   }
	}
	@EventHandler
    public void onPlayerInteractEvent2(PlayerInteractEvent event){
    	Player player = event.getPlayer();
    	if(player.hasPermission("mp.weather") || player.isOp()){
    		if(!(plugin.weatherToggled.contains(player))){
	    		return;
	    	}
    	if(player.getItemInHand().getType().getId() == plugin.getConfig().getInt("MultipleFunction.Rain.Material")){
    		player.getWorld().setWeatherDuration(100);
    	}
    }
  }
	@EventHandler
    public void onPlayerInteractEvent3(PlayerInteractEvent event){
    	Player player = event.getPlayer();
		World world = player.getWorld();
		if(player.hasPermission("mp.settime") || player.isOp()){
			if(!(plugin.setTimeToggled.contains(player))){
	    		return;
	    	}
    	if(player.getItemInHand().getType().getId() == plugin.getConfig().getInt("MultipleFunction.DayNight.Material")){
    	    if (event.getAction() == Action.LEFT_CLICK_AIR){
    		     world.setTime(0);
    		     player.sendMessage(ChatColor.YELLOW + "Time Set To Day");
    	    }else if(event.getAction() == Action.RIGHT_CLICK_AIR) {
    		       world.setTime(1800);
    		       player.sendMessage(ChatColor.YELLOW + "Time Set To Night");
    	    }
    	}
    }
  }
	
}
