package code.aze.leaf.mp;

import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MultipleFunctions extends JavaPlugin implements Listener {
    public final Logger Leaveslogger = Logger.getLogger("Minecraft");
    
	@Override
	public void onEnable(){
          PluginDescriptionFile pdfFile = this.getDescription();
          this.Leaveslogger.info(pdfFile.getName() + " Version " + pdfFile.getVersion());
		  getServer().getPluginManager().registerEvents(this, this);
		  
		  Leaveslogger.info("MultipleFunctions: An Awesome Hub Plugin For The Public");
		  
		       this.getCommand("find").setExecutor(new Find(this));
		       this.getCommand("crea").setExecutor(new Creative(this));
		       this.getCommand("recover").setExecutor(new Recover(this));
		       this.getCommand("surv").setExecutor(new Survival(this));
		       this.getCommand("adven").setExecutor(new Adventure(this));
		       this.getCommand("spect").setExecutor(new Spectator(this));
		       this.getCommand("empty").setExecutor(new ClearInventory(this));
		       this.getCommand("morning").setExecutor(new DayNightCycle(this));
		       this.getCommand("evening").setExecutor(new Night(this));
		       this.getCommand("thunder").setExecutor(new Storm(this));
		       this.getCommand("stormend").setExecutor(new StormEnd(this));
		       this.getCommand("createspawn").setExecutor(new CreateSpawn(this));
		       this.getCommand("tpspawn").setExecutor(new Spawn(this));
		       this.getCommand("hide").setExecutor(new Hidden(this));
		       this.getCommand("mpreload").setExecutor(new Reload(this));
		       this.getCommand("mphelp").setExecutor(new HelpInfo(this));
		       this.getCommand("mpversion").setExecutor(new Version(this));
		   }

	@Override
    public void onDisable(){
    	PluginDescriptionFile pdfFile = this.getDescription();
        this.Leaveslogger.info(pdfFile.getName() + " Version " + pdfFile.getVersion());
    }
	
    ArrayList<Player> jumpers = new ArrayList<Player>();
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event){
    	Player player = event.getPlayer();
    	if(player.hasPermission("mp.launch") || player.isOp()){
    	if (player.getGameMode() == GameMode.CREATIVE)
    		return;
    	event.setCancelled(true);
    	player.setAllowFlight(false);
    	player.setFlying(false);
    	player.setVelocity(player.getLocation().getDirection().multiply(2.5)
    			.setY(1));
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
	public void onPlayerInteractBlock(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    if(player.hasPermission("mp.strike") || player.isOp()){
	    if (player.getItemInHand().getType() == Material.STICK) {
	        player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>)null, 200).getLocation());
	    }
	}    
  }	
	@EventHandler
	public void onPlayerInteractBlock1(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    if(player.hasPermission("mp.explosion") || player.isOp()){
	    if (player.getItemInHand().getType() == Material.FLINT) {
	        player.getWorld().createExplosion(player.getTargetBlock((Set<Material>)null, 200).getLocation(), 10);
	    }
	   }
	}
    @EventHandler
    public void onPlayerInteractBlock2(PlayerInteractEvent event){
    	Player player = event.getPlayer();
    	if(player.hasPermission("mp.weather") || player.isOp()){
    	if(player.getItemInHand().getType() == Material.SNOW_BALL){
    		player.getWorld().setWeatherDuration(100);
    	}
    }
  }
    @EventHandler
    public void onPlayerInteractBlock3(PlayerInteractEvent event){
    	Player player = event.getPlayer();
		World world = player.getWorld();
		if(player.hasPermission("mp.settime") || player.isOp()){
    	if(player.getItemInHand().getType() == Material.BLAZE_ROD){
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
	 
	@EventHandler
	    public void arrowEvent(ProjectileHitEvent event) {
	        if(event.getEntity() instanceof Arrow) {
	            Arrow arrow = (Arrow)event.getEntity();
	            Entity shooter = (Entity) arrow.getShooter();
	         
	            if(shooter instanceof Player) {
	                Player player = (Player)shooter;
	             
	                    player.teleport(arrow);
	                    player.playSound(player.getLocation(), Sound.FIREWORK_BLAST2, 100, 0);
	                }
	            }
	        }
}