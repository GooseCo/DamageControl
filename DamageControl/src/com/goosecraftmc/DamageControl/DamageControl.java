package com.goosecraftmc.DamageControl;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class DamageControl extends JavaPlugin implements Listener{	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static DamageControl plugin;
	public boolean noFall;
	public boolean noFire;
	public boolean noHunger;
	public boolean noHungerTemp;
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " has been started successfully!");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	 public void onEntityFallDamage(EntityDamageEvent e){
		 if(((e.getEntity() instanceof Player) && e.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL) && (noFall == true))
			   e.setCancelled(true);
			 }
	
	@EventHandler
	 public void onEntityFireDamage(EntityDamageEvent event){
		 if(((event.getEntity() instanceof Player) && event.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FIRE && noFire == true || event.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FIRE_TICK) && (noFire == true))
			   event.setCancelled(true);  
			 }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("test")){
			
			player.sendMessage(ChatColor.GOLD + "DamageControl debug command.");
			
		}else if(commandLabel.equalsIgnoreCase("nofall")){
			
			if(noFall == true){
				noFall = false;
				player.sendMessage(ChatColor.AQUA + "Nofall has been disabled.");
			}else if(noFall == false){
				noFall = true;
				player.sendMessage(ChatColor.AQUA + "Nofall has been enabled.");	
			}
		}else if(commandLabel.equalsIgnoreCase("nofire")){
				
			if(noFire == true){
				noFire = false;
				player.sendMessage(ChatColor.RED + "You are now succeptable to damage from fire.");
			}else if(noFire == false){
				noFire = true;
				player.sendMessage(ChatColor.RED + "You will no longer take damage from fire.");	
				
				}
		}
		return false;
	}
}