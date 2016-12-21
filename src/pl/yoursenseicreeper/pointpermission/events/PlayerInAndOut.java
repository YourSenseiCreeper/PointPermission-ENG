package pl.yoursenseicreeper.pointpermission.events;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PointPermission;

public class PlayerInAndOut extends PointPermission implements Listener {

	   public PlayerInAndOut(Main plugin){
		   plugin.getServer().getPluginManager().registerEvents(this, plugin);
	   }
	
	   @EventHandler
	   public void registratePlayer(PlayerJoinEvent e){
		   
	       UUID uid = e.getPlayer().getUniqueId();
		   
	       if (!players.containsKey(uid)){
	    	   e.getPlayer().sendMessage("NO!");
	    	   e.getPlayer().sendMessage(uid.toString());
	    	   e.getPlayer().sendMessage(players.values().toString());
		      	 players.put(uid, getDBase().getStartPoints());
			     return;
	    	  }else{
	    		  e.getPlayer().sendMessage("YES!");
	    	  }
	   }
	   
	   @EventHandler
	   public void onLeave(PlayerQuitEvent e){
		   
		   UUID uid = e.getPlayer().getUniqueId();
		   
		   int punkty = players.get(uid);
		   int start = getDBase().getStartPoints();
		   if (punkty == start){
			   players.remove(uid);
			   if (getDB().contains(uid.toString())){
				   getDB().set(uid.toString(), null);
				   return;
			   }
		   }else{
			   if (!getDB().contains(uid.toString())){
				   getDB().createSection(uid.toString());
				   getDB().set(uid.toString(), punkty);
				   return;
			   }else{
				   getDB().set(uid.toString(), punkty);
				   return;
			   }
		   }
		  
	   }
	
}
