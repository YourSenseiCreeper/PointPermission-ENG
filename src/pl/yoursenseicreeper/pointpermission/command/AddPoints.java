package pl.yoursenseicreeper.pointpermission.command;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PPExecutor;
import pl.yoursenseicreeper.pointpermission.PointPermission;

public class AddPoints extends PointPermission implements PPExecutor{

	public AddPoints(Main plugin, PointPermission base){
		this.base = base;
		plugin.getCommand("add").setExecutor(this);
	}
	
	private PointPermission base;
	
	//Experimenting with CommandExecutor
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args, PointPermission pp) {

		String brak_permisji = base.mbase.getPermisionAbsence();
		String nie_dla_konsoli = base.mbase.getNotForConsole();
			 
			 if(s.hasPermission("pp.addpoints")){
				 
				 if(args.length != 2){
					 s.sendMessage(fix("&cCorrect usage:"+ChatColor.YELLOW+" /addpoints <player> <points>"));
					 return false;
				 	}
	                    if (!(s instanceof Player)){
	                    	s.sendMessage(nie_dla_konsoli);
	                    	return false;
	                    }
	                	
	                    int toAdd = 0;
	                    try {
	                        toAdd = Integer.parseInt(args[1]);
	                    } catch(NumberFormatException er) {
	                        s.sendMessage(ChatColor.RED + "Error: " + ChatColor.YELLOW + args[1] + ChatColor.RED + " is not a number!");
	                        return true;
	                    }
	                    
						Player cel = Bukkit.getPlayer(args[0]);
						if (cel == null){
							s.sendMessage(ChatColor.RED+"Player "+ChatColor.YELLOW+args[0]+ChatColor.RED+" is offline!");
							return false;
						}
						UUID uid = cel.getUniqueId();
						if(players.containsKey(uid)){
							players.replace(uid, players.get(uid)+toAdd);
							s.sendMessage(ChatColor.GRAY+"You add "+ChatColor.AQUA+toAdd+ChatColor.GRAY+" points for player "+ChatColor.AQUA+ChatColor.BOLD+args[0]);
							int pts = players.get(cel.getUniqueId());
							cel.sendMessage(ChatColor.GRAY+"Now you have "+ChatColor.AQUA+pts+ChatColor.GRAY+" points.");
						}else{
							players.put(uid, toAdd);
							s.sendMessage(ChatColor.GRAY+"You add player "+ChatColor.AQUA+ChatColor.BOLD+args[0]+ChatColor.GRAY+" with "+ChatColor.AQUA+toAdd+ChatColor.GRAY+" points.");
						}
	            }
	            else
	            {
	                s.sendMessage(brak_permisji);
	            }
		
		return false;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
