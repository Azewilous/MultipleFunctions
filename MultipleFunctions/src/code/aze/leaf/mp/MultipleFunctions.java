package code.aze.leaf.mp;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
/*
 * @author Azewilous
 */
public class MultipleFunctions extends JavaPlugin implements Listener {
    public final Logger Leaveslogger = Logger.getLogger("Minecraft");
    
    FileConfiguration config;
    File cFile;
    
    ArrayList<Player> doubleJumpToggled = new ArrayList<Player>(); 
    ArrayList<Player> strikeToggled = new ArrayList<Player>();
    ArrayList<Player> explosionToggled = new ArrayList<Player>();
    ArrayList<Player> weatherToggled = new ArrayList<Player>();
    ArrayList<Player> setTimeToggled = new ArrayList<Player>();
    ArrayList<Player> teleportBowToggled = new ArrayList<Player>();
    
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
		       this.getCommand("toggle").setExecutor(new EventToggler(this));
		       
		       registerEvents(this, new DoubleJump(this)
		       , new GenericFunctions(this)
		       , new TeleportBow(this));
		   }
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners){
		for(Listener listener : listeners ){
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}

	@Override
    public void onDisable(){
    	PluginDescriptionFile pdfFile = this.getDescription();
        this.Leaveslogger.info(pdfFile.getName() + " Version " + pdfFile.getVersion());
        saveConfig();
    }
}