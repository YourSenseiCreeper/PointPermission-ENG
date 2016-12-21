package pl.yoursenseicreeper.pointpermission;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

//import pl.yoursenseicreeper.pointpermission.command.AddPoints;
import pl.yoursenseicreeper.pointpermission.command.Buy;
import pl.yoursenseicreeper.pointpermission.command.GivePoints;
import pl.yoursenseicreeper.pointpermission.command.PluginCmd;
import pl.yoursenseicreeper.pointpermission.command.Points;
import pl.yoursenseicreeper.pointpermission.command.RemovePoints;
import pl.yoursenseicreeper.pointpermission.command.ServiceCmd;
import pl.yoursenseicreeper.pointpermission.command.SetPoints;
import pl.yoursenseicreeper.pointpermission.events.PlayerChat;
import pl.yoursenseicreeper.pointpermission.events.PlayerInAndOut;
import pl.yoursenseicreeper.pointpermission.events.SignCreate;
import pl.yoursenseicreeper.pointpermission.events.SignUse;
import pl.yoursenseicreeper.pointpermission.util.CType;
import pl.yoursenseicreeper.pointpermission.util.DBase;
import pl.yoursenseicreeper.pointpermission.util.MBase;

public class PointPermission{
	
	protected ConfigManager cm;
	protected static PointPermissionAPI api;
	private DBase db;
	public MBase mbase;
	protected static Main plug;
	
	public HashMap<CType, FileConfiguration> configs = new HashMap<>();
	public HashMap<String, Service> servs = new HashMap<>();
	public HashMap<UUID, Integer> players = new HashMap<>();
	
	protected FileConfiguration getCfg(){ return configs.get(CType.CFG);}
	protected FileConfiguration getServ(){ return configs.get(CType.SERV);}
	protected FileConfiguration getMsg(){ return configs.get(CType.MSG);}
	protected FileConfiguration getDB(){ return configs.get(CType.DB);}
	protected ConfigManager getCM(){ return cm;}
	public static PointPermissionAPI getAPI(){ return api;}
	public DBase getDBase(){ return db;}
	
	
	public String fix(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public PointPermission loader(Main pl) throws InstantiationException, IllegalAccessException{
		plug = pl;
		 
		 cm = new ConfigManager(pl); 
		 api = new PointPermissionAPI();
		 db = cm.databaseLoader();
		 
		 //Commands
		 //new AddPoints();
		 new Buy(pl);
		 new GivePoints(pl);
		 new PluginCmd(pl);
		 new Points(pl);
		 new RemovePoints(pl);
		 new ServiceCmd(pl);
		 new SetPoints(pl);
		 
		 //Events
		 new SignUse(pl);
		 new SignCreate(pl);
		 new PlayerChat(pl);
		 new PlayerInAndOut(pl);
	     
	     
	     final int mTime = getDBase().getAutosaveTime();
	     Bukkit.getScheduler().runTaskTimer(pl, new Runnable(){
				@Override
	     		public void run() {
					if (mTime > 0){
						cm.playerSaver();
						cm.savePluginConfig(CType.DB);
					}
			     }
	          }, 0, 20*60*mTime);
		return this;
	}

}