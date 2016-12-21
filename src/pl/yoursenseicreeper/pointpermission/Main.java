package pl.yoursenseicreeper.pointpermission;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import pl.yoursenseicreeper.pointpermission.util.CType;
 
public class Main extends JavaPlugin{

	private PointPermission pp;
	
	@Override
   public void onEnable() {
		try {
			pp = new PointPermission().loader(this);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		ConsoleCommandSender con = Bukkit.getConsoleSender();
		con.sendMessage(pp.fix("&f[&bPointPermission&f] Plugin by&b gastherr&f aka.&b YourSenseiCreeper"));
	}
   
	@Override
	public void onDisable() {
		System.out.println("&f[&bPointPermission&f] Saving data...");
		pp.getCM().playerSaver();
		pp.getCM().savePluginConfig(CType.DB);
	}
}