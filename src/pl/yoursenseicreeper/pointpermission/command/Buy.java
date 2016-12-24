package pl.yoursenseicreeper.pointpermission.command;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PointPermission;
import pl.yoursenseicreeper.pointpermission.Service;

public class Buy extends PointPermission implements CommandExecutor{
	
	public Buy (Main plugin){
		plugin.getCommand("buy").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		String prefix = mbase.getPrefix();
		String brak_permisji = mbase.getPermisionAbsence();
		String brak_uslugi = mbase.getServiceAbsence();
		String nie_dla_konsoli = mbase.getNotForConsole();
		
	         if(args.length != 1){
	        	 s.sendMessage(ChatColor.RED + "Correct usage:"+ChatColor.YELLOW+" /buy <serviceCode>");
	         }
	        	 if (!(s instanceof Player)){
                 	s.sendMessage(nie_dla_konsoli);
                 	return false;
                 }
	        	 
	        	 //Player UUID
	        	 Player p = (Player) s;
	        	 UUID uid = p.getUniqueId();
	        	 
	        	 if(!s.hasPermission("pp.buy")){	//Checking permissions
	        		 s.sendMessage(brak_permisji);
	        	 }
	        	 if(!servs.containsKey(args[0])){	//Checking service
	        		 s.sendMessage(brak_uslugi);
	        	 }
	        	 
	        	 //Service & player points
	        	 Service se = servs.get(args[0]);
	        	 String name = se.getName();
    			 int price = se.getPrice();
    			 int pkt = players.get(uid);
				 
				 if(price > pkt)
				 {
					 int brakuje = price - pkt;
    				 s.sendMessage(ChatColor.RED+"If you want buy "+ChatColor.YELLOW+ChatColor.BOLD+args[0]+ChatColor.RED+" ("+ChatColor.YELLOW+ChatColor.BOLD+name+ChatColor.RED+") you must have "+ChatColor.YELLOW+ChatColor.BOLD+brakuje+ChatColor.RED+" points more.");
				 }
				 	else
				 {
				 		List<String> cmds = se.getCommands();
            		 	players.replace(uid, players.get(uid)-price);
            		 	
            		 	s.sendMessage(prefix+ChatColor.GRAY+"You bought service: "+ChatColor.AQUA+name);
            		 	s.sendMessage(prefix+ChatColor.GRAY+"You have now "+ChatColor.AQUA+(pkt-price)+ChatColor.GRAY+" points.");
            		 	
            		 	for	(int i = 0; i < cmds.size(); i++){
            		 		String cmda = cmds.get(i);
            		 		cmda = cmda.replaceAll("PLAYER", p.getName());
            		 		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmda);
            		 	}
            		 	return true;
				 }
				 
				 return false;
	}
}
