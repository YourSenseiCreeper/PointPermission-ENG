package pl.yoursenseicreeper.pointpermission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class PointPermissionAPI extends PointPermission{
	
	public PointPermissionAPI() {}

	public int getPoints(UUID playerUUID){
		if (players.containsKey(playerUUID)){
			return players.get(playerUUID);
		}else{
			return getCfg().getInt("StartPoints");
		}
	}
	
	public void setPoints(UUID playerUUID, int points){
		if (players.containsKey(playerUUID)){
			players.replace(playerUUID, points);
		}else{ 
			players.put(playerUUID, points);
		}
	}
	
	public void resetPoints(UUID playerUUID){
		int st = getStartPoints();
		if (players.containsKey(playerUUID)){
			players.replace(playerUUID, st);
		}else{ 
			players.put(playerUUID, st);
		}
	}
	
	public void addPoints(UUID playerUUID, int points){
		setPoints(playerUUID, getPoints(playerUUID)+points);
	}
	
	public void removePoints(UUID playerUUID, int points){
		setPoints(playerUUID, getPoints(playerUUID)-points);
	}
	
	public Collection<Service> serviceList(){
		return servs.values();
	}
	
	public List<String> serviceCodeList(){
		List<String> codes = new ArrayList<>();
		for(Service se : serviceList()){
			codes.add(se.getCode());
		}
		return codes;
	}
	
	public List<String> getServiceCommands(String serviceCode){
		if (servs.containsKey(serviceCode)){
			return servs.get(serviceCode).getCommands();
		}else{
			return null;
		}
	}
	
	public String getServiceName(String serviceCode){
		if (servs.containsKey(serviceCode)){
			return servs.get(serviceCode).getName();
		}else{
			return null;
		}
	}
	public int getServicePrice(String serviceCode){
		if (servs.containsKey(serviceCode)){
			return servs.get(serviceCode).getPrice();
		}else{
			return 0;
		}
	}
	public List<String> getServiceInfo(String serviceCode){
		if (servs.containsKey(serviceCode)){
			return servs.get(serviceCode).getInformations();
		}else{
			return null;
		}
	}
	public boolean isSignsEnable(){
		return getCfg().getBoolean("Signs");
	}
	
	public boolean isServiceExist(String serviceCode){
		if (servs.containsKey(serviceCode)){
			return true;
		}else{
			return false;
		}
	}
	public int getStartPoints(){
		return getCfg().getInt("StartPoints");
	}
	
	public boolean isChatEnable(){
		return getCfg().getBoolean("ChatFormat.Working");
	}
	
	public String getPluginPrefix(){
		return fix(getMsg().getString("plugin_prefix"));
	}

}
