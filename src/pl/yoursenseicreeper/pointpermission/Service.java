package pl.yoursenseicreeper.pointpermission;

import java.util.ArrayList;

public class Service {
	
	private String code;
	private String name;
	private int cost;
	private ArrayList<String> cmds, infos;
	
	public Service(String code, String name, int cost, ArrayList<String> cmds, ArrayList<String> infos){
		this.code = code;
		this.name = name;
		this.cost = cost;
		this.cmds = cmds;
		this.infos = infos;
	}
	
	public String getCode(){ return this.code;}
	
	public String getName(){ return this.name;}
	
	public int getPrice(){ return this.cost;}
	
	public ArrayList<String> getCommands(){ return this.cmds;}
	
	public ArrayList<String> getInformations(){ return this.infos;}
}
