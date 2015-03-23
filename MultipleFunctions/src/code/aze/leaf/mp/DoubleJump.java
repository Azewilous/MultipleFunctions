package code.aze.leaf.mp;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJump implements Listener{

MultipleFunctions plugin;
	public DoubleJump(MultipleFunctions multipleFunctions){
		this.plugin = multipleFunctions;
	}
	
	ArrayList<Player> jumpers = new ArrayList<Player>();
	
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event){
    	Player player = event.getPlayer();
    	if(player.hasPermission("mp.launch") || player.isOp()){
    		if(!plugin.doubleJumpToggled.contains(player)){
    			return;
    		}
    	if (player.getGameMode() == GameMode.CREATIVE)
    		return;
    	event.setCancelled(true);
    	player.setAllowFlight(false);
    	player.setFlying(false);
    	player.setVelocity(player.getLocation().getDirection()
    			.multiply(plugin.getConfig().getDouble("MultipleFunction.DoubleJump.Diection"))
    			.setY(plugin.getConfig().getInt("MultipleFunction.DoubleJump.Velocity")));
        jumpers.add(event.getPlayer());
    	}
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
    	Player player = event.getPlayer();
    	if(player.hasPermission("mp.launched") || player.isOp()){
    	   if((player.getGameMode()!=GameMode.CREATIVE)&&(
    			player.getLocation().subtract(0, 1, 0).getBlock().getType()!= Material.AIR)
    		&&(!player.isFlying())){
        player.setAllowFlight(true);
    	   }
    	}
    }
    
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
            if (event.getEntity() instanceof Player) {
                    Player player = (Player) event.getEntity();
                    if (event.getCause() == DamageCause.FALL) {
                            event.setDamage(0.0);
                            jumpers.remove(player);
                    }
            }
    }
	
}
