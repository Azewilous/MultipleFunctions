package code.aze.leaf.mp;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
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
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * @author Azewilous
 */

public class MultipleFunctions extends JavaPlugin implements Listener {
    public final Logger Leaveslogger = Logger.getLogger("Minecraft");
    
    FileConfiguration config;
    File cFile;
    
    Plugin plugin;
    
	@Override
	public void onEnable(){
          PluginDescriptionFile pdfFile = this.getDescription();
          this.Leaveslogger.info(pdfFile.getName() + " Version " + pdfFile.getVersion());
		  getServer().getPluginManager().registerEvents(this, this);
		  
		  Leaveslogger.info("MultipleFunctions: An Awesome Hub Plugin For The Public");
		  
		  config = getConfig();
		  config.options().copyDefaults(true);
		  cFile = new File(getDataFolder(), "config.yml");
		  
		  config.addDefault("MultipleFunction.DoubleJump.Diection", 2.5);
		  config.addDefault("MultipleFunction.DoubleJump.Velocity", 1);
		  config.addDefault("MultipleFunction.Lightning.Material", 280);
		  config.addDefault("MultipleFunction.Explosion.Material", 318);
		  config.addDefault("MultipleFunction.Rain.Material", 332);
		  config.addDefault("MultipleFunction.DayNight.Material", 369);
		  saveConfig();
		  
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
	@SuppressWarnings("deprecation")
	@EventHandler
	public void strikeLightning(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    if(player.hasPermission("mp.strike") || player.isOp()){
	    if (player.getItemInHand().getType().getId() 
	    		== plugin.getConfig().getInt("MultipleFunction.Lightning.Material")) {
	        player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>)null, 200).getLocation());
	    }
	}    
  }	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void createExplosion(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    if(player.hasPermission("mp.explosion") || player.isOp()){
	    if (player.getItemInHand().getType().getId() 
	    		== plugin.getConfig().getInt("MultipleFunction.Explosion.Material")) {
	        player.getWorld().createExplosion(player.getTargetBlock((Set<Material>)null, 200).getLocation(), 10);
	    }
	   }
	}
    @SuppressWarnings("deprecation")
	@EventHandler
    public void startRain(PlayerInteractEvent event){
    	Player player = event.getPlayer();
    	if(player.hasPermission("mp.weather") || player.isOp()){
    	if(player.getItemInHand().getType().getId() 
    			== plugin.getConfig().getInt("MultipleFunction.Rain.Material")){
    		player.getWorld().setWeatherDuration(100);
    	}
    }
  }
    @SuppressWarnings("deprecation")
	@EventHandler
    public void changeTime(PlayerInteractEvent event){
    	Player player = event.getPlayer();
		World world = player.getWorld();
		if(player.hasPermission("mp.settime") || player.isOp()){
    	if(player.getItemInHand().getType().getId() 
    			== plugin.getConfig().getInt("MultipleFunction.DayNight.Material")){
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