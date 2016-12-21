package pl.yoursenseicreeper.pointpermission.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PointPermission;

public class SignCreate extends PointPermission implements Listener {
	
	ChatColor g = ChatColor.GRAY;
	ChatColor r = ChatColor.RED;
	
	String brak_permisji = mbase.getPermisionAbsence();
    String znak_punkty = mbase.getSignPoints();
    String znak_uslugi = mbase.getSignServiceList();
    String znak_usluga_info = mbase.getSignServiceInfo();
    String znak_kup = mbase.getSignBuy();
	
	   public SignCreate(Main plugin){
		   plugin.getServer().getPluginManager().registerEvents(this, plugin);
	   }
	   
	@EventHandler
	public void onCreate(SignChangeEvent e){
		
		
		String[] lines = e.getLines();
		if (signChecker(lines[0])){
			if (lines[0].equals(znak_usluga_info)){
				if (!servs.containsKey(lines[2])){
					e.getPlayer().sendMessage(r+"Service "+ChatColor.YELLOW+lines[2]+r+" not exist!");
					e.setCancelled(true);
					return;
				}
			}
			if(!e.getPlayer().hasPermission("pp.sign.create")){
				e.getPlayer().sendMessage(brak_permisji);
				e.setCancelled(true);
				return;
			}else{
				e.getPlayer().sendMessage(g+"You create PointPermission sign: "+ChatColor.AQUA+lines[0]);
			}
		}
		
	}
	
	private boolean signChecker(String line){
		if (line.equals(znak_punkty) || line.equals(znak_uslugi) ||
			line.equals(znak_usluga_info) || line.equals(znak_kup)){
			return true;
		}
		return false;
	}

}
