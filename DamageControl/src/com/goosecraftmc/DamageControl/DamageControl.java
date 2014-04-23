package com.goosecraftmc.DamageControl;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class DamageControl extends JavaPlugin implements Listener{	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static DamageControl plugin;
	public boolean nofall;
	public boolean nofire;
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " has been started successfully!");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	 public void onPlayerToggleFlight(PlayerToggleFlightEvent event){
		Player player = event.getPlayer();
		
		if(player.getGameMode() == GameMode.CREATIVE)
			return;
		event.setCancelled(true);
		//player.setAllowFlight(false);
		player.setFlying(false);
		player.setVelocity(player.getLocation().getDirection().multiply(1.5));
	}
	
	@EventHandler
	 public void onEntityFallDamage(EntityDamageEvent e){
		 if(((e.getEntity() instanceof Player) && e.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL) && (nofall == true))
			   e.setCancelled(true);
			 }
	
	@EventHandler
	 public void onEntityFireDamage(EntityDamageEvent event){
		 if(((event.getEntity() instanceof Player) && event.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FIRE || event.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FIRE_TICK) && (nofire == true))
			   event.setCancelled(true);
		 	   
		 	   
			 }
	
	@EventHandler
	 public void onPlayerMove(PlayerMoveEvent event) {
	  Player player = event.getPlayer();
	  if ((player.getGameMode() != GameMode.CREATIVE) && (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) && (!player.isFlying()))
	   player.setAllowFlight(true);
	 }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("siggplugin")){
			
			player.sendMessage(ChatColor.GOLD + "You are currently running SiggPlugin successfully!");
			
		}else if(commandLabel.equalsIgnoreCase("nofall")){
			
			if(nofall == true){
				nofall = false;
				player.sendMessage(ChatColor.AQUA + "Nofall has been disabled.");
			}else if(nofall == false){
				nofall = true;
				player.sendMessage(ChatColor.AQUA + "Nofall has been enabled.");	
			}
		}else if(commandLabel.equalsIgnoreCase("nofire")){
				
			if(nofire == true){
				nofire = false;
				player.sendMessage(ChatColor.RED + "You are now succeptable to damage from fire.");
			}else if(nofire == false){
				nofire = true;
				player.sendMessage(ChatColor.RED + "You will no longer take damage from fire.");	
				
				}
			}
		return false;
	}
}