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

public class GivePoints extends PointPermission implements CommandExecutor{
	
	public GivePoints (Main plugin){
		plugin.getCommand("givepoints").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		String brak_permisji = mbase.getPermisionAbsence();
		String nie_dla_konsoli = mbase.getNotForConsole();
			 
			 if(s.hasPermission("pp.givepoints")){
	                if(args.length == 2){
	                    if (!(s instanceof Player)){
	                    	s.sendMessage(nie_dla_konsoli);
	                    	return false;
	                    }
	                    int toGive = 0;
	                    try {
	                        toGive = Integer.parseInt(args[1]);
	                    } catch(NumberFormatException er) {
	                        s.sendMessage(ChatColor.RED + "Error: " + ChatColor.YELLOW + args[1] + ChatColor.RED + " is not a number!");
	                        return true;
	                    }
	                    Player p = (Player) s;
	                    UUID uid = p.getUniqueId();
	                    int spts = players.get(uid);
	                    
	                    Player tar = Bukkit.getPlayer(args[0]);
	                    if(tar == null){
	                    	s.sendMessage(ChatColor.RED+"You can't exchange with someone who's offline!");
	                    	return false;
	                    }
	                    
	                    if(tar == p){
	                    	s.sendMessage(ChatColor.RED+"You can't exchange with yourself!");
	                    	return false;
	                    }
	                    
	                    UUID uidt = tar.getUniqueId();
	                    if (toGive > spts){
	                    	s.sendMessage(ChatColor.RED+"You can't give more points than you have!");
	                    	return false;
	                    }
	                    
	                    players.replace(uid, players.get(uid)-toGive);
	                    players.replace(uidt, players.get(uidt)+toGive);
	                    
	                    s.sendMessage(ChatColor.GRAY+"You gave "+ChatColor.AQUA+toGive+ChatColor.GRAY+" points for "+ChatColor.AQUA+args[0]);
	                    tar.sendMessage(ChatColor.GRAY+"You get "+ChatColor.AQUA+toGive+ChatColor.GRAY+" points from "+ChatColor.AQUA+p.getName());
	                    
	                }else{
	                    s.sendMessage(ChatColor.RED + "Correct usage:"+ChatColor.YELLOW+" /givepoints <player> <points>");
	                }
	            }
	            else
	            {
	                s.sendMessage(brak_permisji);
	            }
		
		return false;
	}

}
