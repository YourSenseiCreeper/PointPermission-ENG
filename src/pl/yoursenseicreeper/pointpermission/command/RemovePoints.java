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

public class RemovePoints extends PointPermission implements CommandExecutor{
	
	public RemovePoints (Main plugin){
		plugin.getCommand("removepoints").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		String brak_permisji = mbase.getPermisionAbsence();
		String nie_dla_konsoli = mbase.getNotForConsole();
		String gracz_nie_istnieje = mbase.getPlayerNotExist();
			 
			 if(s.hasPermission("pp.removepoints")){
				 	if(args.length == 2){
	                	if (!(s instanceof Player)){
	                    	s.sendMessage(nie_dla_konsoli);
	                    	return false;
	                    }
	                    int toRemove = 0;
	                    try {
	                        toRemove = Integer.parseInt(args[1]);
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
						if (toRemove > players.get(uid)){
							s.sendMessage(ChatColor.RED+"You can't remove more than player got!");
							return false;
						}
						if(players.containsKey(uid)){
							players.replace(uid, players.get(uid)-toRemove);
							s.sendMessage(ChatColor.GRAY+"You remove "+ChatColor.AQUA+toRemove+ChatColor.GRAY+" points from player "+ChatColor.AQUA+ChatColor.BOLD+args[0]);
							int pts = players.get(cel.getUniqueId());
							cel.sendMessage(ChatColor.GRAY+"Now you have "+ChatColor.AQUA+pts+ChatColor.GRAY+" points.");
						}else{
							s.sendMessage(ChatColor.RED+gracz_nie_istnieje);
						}
	                }else{
	                    s.sendMessage(ChatColor.RED + "Correct usage:"+ChatColor.YELLOW+" /removepoints <player> <points>");
	                }
	            }
	            else
	            {
	                s.sendMessage(brak_permisji);
	            }
		
		return false;
	}

}
