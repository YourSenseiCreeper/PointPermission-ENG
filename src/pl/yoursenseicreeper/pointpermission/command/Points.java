package pl.yoursenseicreeper.pointpermission.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PointPermission;

public class Points extends PointPermission implements CommandExecutor{
	
	public Points (Main plugin){
		plugin.getCommand("points").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		String prefix = mbase.getPrefix();
		String brak_permisji = mbase.getPermisionAbsence();
		String nie_dla_konsoli = mbase.getNotForConsole();
		
		if(args.length == 0){
			if(!s.hasPermission("pp.points")){
				s.sendMessage(brak_permisji);
			}
			
			if(!(s instanceof Player)){
				s.sendMessage(nie_dla_konsoli);
				return false;
			
			}
				Player p = (Player) s;
               	int pkt = players.get(p.getUniqueId());
                p.sendMessage(prefix+fix("&7You have&b "+pkt+"&7 points."));
                
                return true;
		}
		if (args.length == 1){
			if (!s.hasPermission("pp.points.others")){
				s.sendMessage(brak_permisji);
			}
				int pkt = 0;
				Player p = Bukkit.getPlayer(args[1]);
					if(players.containsKey(p.getUniqueId())){
						pkt = players.get(p.getUniqueId());
                	}
					else
					{
                		pkt = getDBase().getStartPoints();
                	}
				s.sendMessage(prefix+ChatColor.GRAY+"Player "+ChatColor.AQUA+args[0]+ChatColor.GRAY+" got "+ChatColor.AQUA+pkt+ChatColor.GRAY+" points.");
				return true;
		}
		else
		{
			s.sendMessage(ChatColor.RED+"Correct usage: /points <player>");
			return false;
		}
	}

}
