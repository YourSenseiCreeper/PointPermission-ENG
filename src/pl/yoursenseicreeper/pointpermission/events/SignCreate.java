package pl.yoursenseicreeper.pointpermission.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PointPermission;

public class SignCreate extends PointPermission implements Listener {
	
	   public SignCreate(Main plugin){
		   plugin.getServer().getPluginManager().registerEvents(this, plugin);
	   }
	   
	@EventHandler
	public void onCreate(SignChangeEvent e){
		
		String brak_permisji = mbase.getPermisionAbsence();
		
	    String znak_punkty = mbase.getSignPoints();
	    String znak_uslugi = mbase.getSignServiceList();
	    String znak_usluga_info = mbase.getSignServiceInfo();
	    String znak_kup = mbase.getSignBuy();
		
		if (e.getLine(0).equals(znak_punkty) || 
				e.getLine(0).equals(znak_uslugi) ||
				e.getLine(0).equals(znak_usluga_info) ||
				e.getLine(0).equals(znak_kup)){
			if (e.getLine(0).equals(znak_usluga_info)){
				if (!servs.containsKey(e.getLine(2))){
					e.getPlayer().sendMessage(ChatColor.RED+"Service "+ChatColor.YELLOW+e.getLine(2)+ChatColor.RED+" not exist!");
					e.setCancelled(true);
					return;
				}
			}
			if(!e.getPlayer().hasPermission("pp.sign.create")){
				e.getPlayer().sendMessage(brak_permisji);
				e.setCancelled(true);
				return;
			}else{
				e.getPlayer().sendMessage(ChatColor.GRAY+"You create PointPermission sign: "+ChatColor.AQUA+e.getLine(0));
			}
		}
		
	}

}
