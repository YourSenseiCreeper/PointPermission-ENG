package pl.yoursenseicreeper.pointpermission.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		
		if (arg0.getName().equalsIgnoreCase("dupa")){
			return false;
		}
		
		return false;
	}

}
