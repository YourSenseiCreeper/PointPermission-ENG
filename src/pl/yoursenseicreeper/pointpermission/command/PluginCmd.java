package pl.yoursenseicreeper.pointpermission.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PointPermission;

public class PluginCmd extends PointPermission implements CommandExecutor{
	
	public PluginCmd (Main plugin){
		plugin.getCommand("pp").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
		String brak_permisji = mbase.getPermisionAbsence();
		
		if(cmd.getName().equalsIgnoreCase("pp")){
		
			if(args.length == 0){
				s.sendMessage(fix("&7===== &bPointPermission&7 ====="));
				s.sendMessage(fix("&7Plugin created by&b gastherr&7 aka.&b YourSenseiCreeper"));
				s.sendMessage(fix("&7Plugin commands:&b /pp help"));
				return false;
		}
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("reload")){
				if(s.hasPermission("pp.reload")){
					getCM().playerSaver();
					players = new HashMap<>();
					configs = new HashMap<>();
					servs = new HashMap<>();
					getCM().loadAll();
					s.sendMessage(ChatColor.GRAY+"Configurations reloaded!");
					return true;
				}else{
					s.sendMessage(ChatColor.RED+brak_permisji);
				}
			}
			if(args[0].equalsIgnoreCase("help")){
				if(s.hasPermission("pp.help")){
					String[] text = 
						{"&7===== &bPointPermission help&7 =====",
						"&b/points&7 - show amount of points",
						"&b/points <player>&7 - show amount of someones' points",
						"&b/addpoints <player> <points>&7 - add points to someone",
						"&b/removepoints <player> <points>&7 - remove someones' points",
						"&b/setpoints <player> <points>&7 - set someones' points",
						"&b/givepoints <player> <points>&7 - give some of your point to someone",
						"&b/buy <serviceCode>&7 - buy a service",
						"&b/service list&7 - all services",
						"&b/service info <serviceCode>&7 - details about service"};
					Collection<String> textColl = Arrays.asList(text);
					ArrayList<String> rows = new ArrayList<>(20);
					rows.addAll(textColl);
					
					for(String ms : rows){
						s.sendMessage(fix(ms));
					}
				}else{
					s.sendMessage(ChatColor.RED+brak_permisji);
				}
			}
		}
			
		}
		
		return false;
	}

}
