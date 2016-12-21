package pl.yoursenseicreeper.pointpermission.command;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PointPermission;

public class SetPoints extends PointPermission implements CommandExecutor{
	
	public SetPoints (Main plugin){
		plugin.getCommand("setpoints").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		String brak_permisji = mbase.getPermisionAbsence();
		String nie_dla_konsoli = mbase.getNotForConsole();
			 
			 if(s.hasPermission("pp.setpoints")){
				 	if(args.length == 2){
	                    if (!(s instanceof Player)){
	                    	s.sendMessage(nie_dla_konsoli);
	                    	return false;
	                    }
	                	
	                    int toSet = 0;
	                    try {
	                        toSet = Integer.parseInt(args[1]);
	                    } catch(NumberFormatException er) {
	                        s.sendMessage(ChatColor.RED + "Error: " + ChatColor.YELLOW + args[1] + ChatColor.RED + " is not a number!");
	                        return true;
	                    }
	                    
	                    if(toSet < 0){
	                    	s.sendMessage(ChatColor.RED+"You can't set points lower than 0!");
							return false;
	                    }
	                    
						Player cel = Bukkit.getPlayer(args[0]);
						if (cel == null){
							s.sendMessage(ChatColor.RED+"Player "+ChatColor.YELLOW+args[0]+ChatColor.RED+" is offline!");
							return false;
						}
						UUID uid = cel.getUniqueId();
						if(players.containsKey(uid)){
							players.replace(uid, toSet);
							s.sendMessage(ChatColor.GRAY+"You set "+ChatColor.AQUA+args[0]+ChatColor.GRAY+"s' points to "+ChatColor.AQUA+ChatColor.BOLD+toSet);
							int pts = players.get(cel.getUniqueId());
							cel.sendMessage(ChatColor.GRAY+"Now you have "+ChatColor.AQUA+pts+ChatColor.GRAY+" points.");
						}else{
							players.put(uid, toSet);
							s.sendMessage(ChatColor.GRAY+"You add player "+ChatColor.AQUA+ChatColor.BOLD+args[0]+ChatColor.GRAY+" and set its points to "+ChatColor.AQUA+toSet+ChatColor.GRAY);
						}
	                }
	                else
	                {
	                    s.sendMessage(ChatColor.RED + "Correct usage:"+ChatColor.YELLOW+" /setpoints <player> <points>");
	                }
	            }
	            else
	            {
	                s.sendMessage(brak_permisji);
	            }
		
		return false;
	}

}
