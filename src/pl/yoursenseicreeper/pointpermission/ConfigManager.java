package pl.yoursenseicreeper.pointpermission;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import pl.yoursenseicreeper.pointpermission.util.CType;
import pl.yoursenseicreeper.pointpermission.util.DBase;
import pl.yoursenseicreeper.pointpermission.util.MBase;

public class ConfigManager extends PointPermission {
	
	private Main plugin;
	
	public ConfigManager(Main plugin){
		this.plugin = plugin;
		loadAll();
	}
	
	private static void copy(InputStream in, File file) throws IOException {
        OutputStream out = new FileOutputStream(file);
        byte[] buf = new byte[1024];
        int len;
 
        while((len = in.read(buf)) > 0){
            out.write(buf, 0, len);
        }
 
        out.close();
        in.close();
    }
	
	private void registerPluginConfig(CType typ) throws IOException{
		File file = new File(plugin.getDataFolder(), typ.pname()+".yml");
		FileConfiguration getConfig = YamlConfiguration.loadConfiguration(file);
        ConsoleCommandSender con = Bukkit.getConsoleSender();
        if (!file.exists()) {
                file.getParentFile().mkdirs();
                copy(plugin.getResource(typ.pname()+".yml"), file);
                getConfig = YamlConfiguration.loadConfiguration(file);
                getConfig.save(file);
                configs.put(typ, getConfig);
                con.sendMessage(fix("&f[&bPointPermission&f] Creating:&b "+typ.pname()+".yml"));
        }else{
        	configs.put(typ, getConfig);
        }
	}
	
	public void savePluginConfig(CType typ){
		File f = new File(plugin.getDataFolder(), typ.pname()+".yml");
		FileConfiguration fl = configs.get(typ);
		try {
			fl.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void servicesLoader(){
		File file = new File(plugin.getDataFolder(), "services.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int am = 0;
		Set<String> pt = cfg.getConfigurationSection("services").getKeys(false);
		for (String serv : pt) {
		    String code = serv;
		    String name = cfg.getString("services."+serv+".name");
		    int price = cfg.getInt("services."+serv+".price");
		    ArrayList<String> cmds = (ArrayList<String>) cfg.getStringList("services."+serv+".commands");
		    ArrayList<String> infos = (ArrayList<String>) cfg.getStringList("services."+serv+".info");
		    
		    ArrayList<String> cmds2 = new ArrayList<>();
		    ArrayList<String> infos2 = new ArrayList<>();
		    
		    for(String cm : cmds){
		    	cmds2.add(fix(cm));
		    }
		    for(String ifo2 : infos){
		    	infos2.add(fix(ifo2));
		    }
		    
		    Service nwsc = new Service(code, name, price, cmds2, infos2);
		    servs.put(code, nwsc);
		    am++;
		}
		ConsoleCommandSender con = Bukkit.getConsoleSender();
		con.sendMessage(fix("&f[&bPointPermission&f] All services ("+am+") loaded!"));
	}
	
	public void playerLoader(){
		File file = new File(plugin.getDataFolder(), "database.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		ConsoleCommandSender con = Bukkit.getConsoleSender();
		int all = 0;
		for (String uid : cfg.getConfigurationSection("").getKeys(false)) {
			UUID ud = UUID.fromString(uid);
		    int points = cfg.getInt(uid);
		    players.put(ud, points);
		    all++;
		}
		con.sendMessage(fix("&f[&bPointPermission&f] All saved players ("+all+") loaded!"));
	}
	
	public void playerLoader2(){
		YamlConfiguration cfg = (YamlConfiguration) configs.get(CType.DB);
		ConsoleCommandSender con = Bukkit.getConsoleSender();
		int all = 0;
		for (String uid : cfg.getConfigurationSection("").getKeys(false)) {
			UUID ud = UUID.fromString(uid);
		    int points = cfg.getInt(uid);
		    players.put(ud, points);
		    all++;
		}
		con.sendMessage(fix("&f[&bPointPermission&f] All saved players ("+all+") loaded!"));
	}
	
	public void playerSaver(){
		for(UUID uid : players.keySet()){
				int pktS = getCfg().getInt("StartPoints");
				int pkt = players.get(uid);
				Player ap = Bukkit.getPlayer(uid);
				if (pkt == getDB().getInt(uid.toString()) && ap == null){
					players.remove(uid);
					break;
				}
				if (pkt == pktS && ap == null){
					players.remove(uid);
					break;
				}
				if (pkt == pktS && ap != null){
					if (getDB().contains(uid.toString())){
						getDB().set(uid.toString(), null);
						break;
					}
				}
				if (pkt != pktS){
					if (getDB().contains(uid.toString())){
						getDB().set(uid.toString(), pkt);
						break;
					}else{
						getDB().createSection(uid.toString());
						getDB().set(uid.toString(), pkt);
						break;
					}
					
				}
			}
		savePluginConfig(CType.DB);
	}
	
	public MBase messagesLoader(){
		  String prefix = fix(getMsg().getString("plugin_prefix"))+" ";
		  String brak_permisji = fix(getMsg().getString("permission_absence"));
		  String brak_uslugi = fix(getMsg().getString("service_absence"));
		  String nie_dla_konsoli = fix(getMsg().getString("not_for_console"));
		  String gracz_nie_istnieje = fix(getMsg().getString("player_not_exist"));
		  
		  String znak_punkty = fix(getMsg().getString("sign_points"));
		  String znak_usluga_lista = fix(getMsg().getString("sign_service_list"));
		  String znak_usluga_info = fix(getMsg().getString("sign_service_info"));
		  String znak_kup = fix(getMsg().getString("sign_buy"));
		  
		  MBase ms = new MBase(prefix, brak_permisji, brak_uslugi, nie_dla_konsoli, gracz_nie_istnieje,
				  	znak_punkty, znak_usluga_lista, znak_usluga_info, znak_kup);
		  return ms;
	}
	
	public DBase databaseLoader(){
		int startPoints = getCfg().getInt("StartPoints");
		String preFormat = getCfg().getString("ChatFormat.PreFormat");
		String postFormat = getCfg().getString("ChatFormat.PostFormat");
		boolean chatFormat = getCfg().getBoolean("ChatFormat.Working");
		int autosaveTime = getCfg().getInt("AutosaveTime");
		boolean signs = getCfg().getBoolean("Signs");
		return new DBase(startPoints, preFormat, postFormat, chatFormat, autosaveTime, signs);
	}
	
	public void loadAll(){
		ConsoleCommandSender con = Bukkit.getConsoleSender();
		con.sendMessage(fix("&f[&bPointPermission&f] Loading data..."));
		try {
		    registerPluginConfig(CType.DB);
			registerPluginConfig(CType.CFG);
		    registerPluginConfig(CType.SERV);
		    registerPluginConfig(CType.MSG);
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerLoader2();
		servicesLoader();
		super.mbase = messagesLoader();
	}
	
	public void reloadData(){
		playerSaver();
		loadAll();
	}

}
