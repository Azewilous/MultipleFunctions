package code.aze.leaf.mp;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;

public class TeleportBow implements Listener {

MultipleFunctions plugin;
	public TeleportBow(MultipleFunctions multipleFunctions){
		this.plugin = multipleFunctions;
	}
	
	ArrayList<Player> teleported = new ArrayList<Player>(); 
	
	@EventHandler
    public void arrowEvent(ProjectileHitEvent event) {
        if(event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow)event.getEntity();
            Entity shooter = (Entity) arrow.getShooter();
         
            if(shooter instanceof Player) {
                Player player = (Player)shooter;
                if(player.hasPermission("mp.teleportbow") || player.isOp()){
                	if(!(plugin.teleportBowToggled.contains(player))){
                		return;
                	}
                    player.teleport(arrow);
                    player.playSound(player.getLocation(), Sound.FIREWORK_BLAST2, 100, 0);
                    player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, true);
                    teleported.add(player);
                }
            }
        }
	}
	
	@EventHandler
	public void fallDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			Entity player = event.getEntity();
			if(event.getCause() == DamageCause.FALL){
				event.setDamage(0.0);
				teleported.remove(player);
			}
		}
	}
	
}
