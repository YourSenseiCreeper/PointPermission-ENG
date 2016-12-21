package pl.yoursenseicreeper.pointpermission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public interface PPExecutor extends CommandExecutor{
	
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args, PointPermission pp);

}
