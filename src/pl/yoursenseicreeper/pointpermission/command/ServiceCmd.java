package pl.yoursenseicreeper.pointpermission.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PointPermission;
import pl.yoursenseicreeper.pointpermission.Service;

public class ServiceCmd extends PointPermission implements CommandExecutor{
	
	public ServiceCmd (Main plugin){
		plugin.getCommand("service").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		String prefix = mbase.getPrefix();
		String brak_permisji = mbase.getPermisionAbsence();
		String brak_uslugi = mbase.getServiceAbsence();
			
		if (args.length == 0){
  			s.sendMessage(ChatColor.RED+"Correct usage:"+ChatColor.YELLOW+" /service list");
  			s.sendMessage(ChatColor.RED+"Correct usage:"+ChatColor.YELLOW+" /service info <serviceCode>");
  			return false;
  		  }
		    if (args.length == 1){
				if(args[0].equalsIgnoreCase("list")){
		    	if (s.hasPermission("pp.service.list")){
		    		s.sendMessage(prefix+ChatColor.YELLOW+"Service list:");
		    		for (Service se : servs.values()) {
		    			int nr = 1;
		    			s.sendMessage(prefix+ChatColor.GRAY+nr+". "+ChatColor.AQUA+se.getCode()+ChatColor.GRAY+" - "+ChatColor.AQUA+se.getName()+ChatColor.GRAY+
		            		  " - Price: "+ChatColor.AQUA+se.getPrice()+ChatColor.GRAY + " points.");
		    		}
		    		return false;
		    		
		    	}else{
		    	  s.sendMessage(brak_permisji);
		    	}
				}
				if (args[0].equalsIgnoreCase("info")){
					s.sendMessage(ChatColor.RED+"Correct usage:"+ChatColor.YELLOW+" /service info <serviceCode>");
					return false;
				}
		    	}else{
		    		if (!args[0].equalsIgnoreCase("list") || !args[0].equalsIgnoreCase("info")){}else{
		    			s.sendMessage(ChatColor.RED+"Correct usage:"+ChatColor.YELLOW+" /service list");
		    		}
		    	}
		
  		  if (args.length == 2){
  			  if(args[0].equalsIgnoreCase("info")){
	    	  if (s.hasPermission("pp.service.info")){
	        			 if (servs.containsKey(args[1])){
	        				 	Service se = servs.get(args[1]);
	        				 	s.sendMessage(prefix+ChatColor.DARK_GRAY+"----- "+ChatColor.AQUA+args[1]+ChatColor.DARK_GRAY+" -----");
	        				 	s.sendMessage(prefix+ChatColor.GRAY+"Name: "+ChatColor.AQUA+se.getName());
	        				 	s.sendMessage(prefix+ChatColor.GRAY+"Price: "+ChatColor.AQUA+se.getPrice());
	        				 	s.sendMessage(prefix+ChatColor.GRAY+"Info: ");
	        				 	for(String inf : se.getInformations()){
	        				 		s.sendMessage(ChatColor.DARK_GRAY+"- "+inf);
	        				 	}
	        				 	s.sendMessage(prefix+ChatColor.DARK_GRAY+"---------------------");
	        			  }else{
	        				   s.sendMessage(brak_uslugi);
	        			  }
	    		  }else{
		    		  s.sendMessage(brak_permisji);
		    	  }
	    	  }
	      }else{
	    	  if (!args[0].equalsIgnoreCase("list") || !args[0].equalsIgnoreCase("info")){}else{
				  s.sendMessage(ChatColor.RED+"Correct usage:"+ChatColor.YELLOW+" /service info <serviceCode>");
				  return false;
	    	  }
		  }
  		  if (args.length > 2){
  			s.sendMessage(ChatColor.RED+"Correct usage:"+ChatColor.YELLOW+" /service list");
  			s.sendMessage(ChatColor.RED+"Correct usage:"+ChatColor.YELLOW+" /service info <serviceCode>");
  			return false;
  		  }
		
		return false;
	}

}
