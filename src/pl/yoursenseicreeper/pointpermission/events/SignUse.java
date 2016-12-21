package pl.yoursenseicreeper.pointpermission.events;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PointPermission;

public class SignUse extends PointPermission implements Listener{
	
	   public SignUse(Main plugin){
		   plugin.getServer().getPluginManager().registerEvents(this, plugin);
	   }
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
			Player p = e.getPlayer();
			boolean znaki = getDBase().areSignsEnabled();
		
			String brak_permisji = mbase.getPermisionAbsence();
			String brak_uslugi = mbase.getServiceAbsence();
		  
		    String znak_punkty = mbase.getSignPoints();
		    String znak_uslugi = mbase.getSignServiceList();
		    String znak_usluga_info = mbase.getSignServiceInfo();
		    String znak_kup = mbase.getSignBuy();
		    
		    
		    if (znaki){
		    	if ((e.hasBlock()) && (e.getClickedBlock().getType().equals(Material.SIGN) || e.getClickedBlock().getType().equals(Material.WALL_SIGN) ||
		    		e.getClickedBlock().getType().equals(Material.SIGN_POST)) &&(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
		    			Sign s = (Sign) e.getClickedBlock().getState();
		    			if (s.getLine(0).equals(znak_punkty)){
		    				if (p.hasPermission("pp.sign.points")){
		    					p.chat("/points");
		    					return;
		    				}else{
		    					p.sendMessage(brak_permisji);
		    					return;
		    				}
		    			}
		    			if (s.getLine(0).equals(znak_uslugi)) {
		    				if (p.hasPermission("pp.sign.service.list")){
		    					p.chat("/service list");
		    					return;
		    				}else{
		    					p.sendMessage(brak_permisji);
		    					return;
		    				}
		    			}
		    			if (s.getLine(0).equalsIgnoreCase(znak_kup)) {
		    				if (p.hasPermission("pp.sign.buy")){
		    					if (servs.containsKey(s.getLine(1))){
		    						p.chat("/buy "+ s.getLine(1));
		    						return;
		    					}else{
		    						p.sendMessage(brak_uslugi);
		    					}
		    				}else{
			    				p.sendMessage(brak_permisji);
			    				return;
			    			}
		    			}
		    			if (s.getLine(0).equalsIgnoreCase(znak_usluga_info)) {
		    				if (p.hasPermission("pp.sign.service.info")){
		    					if (s.getLine(1).equalsIgnoreCase("Info")) {
		    						if (servs.containsKey(s.getLine(2))){
		    							p.chat("/service info " + s.getLine(2));
		    							return;
		    						}else{
		    							p.sendMessage(brak_uslugi);
		    						}
		    					}
		    				}else{
		    					p.sendMessage(brak_permisji);
		    					return;
		    				}
		    			}
		    	}
		    }
	}

}
