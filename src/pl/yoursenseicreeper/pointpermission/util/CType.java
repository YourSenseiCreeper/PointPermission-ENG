package pl.yoursenseicreeper.pointpermission.util;

public enum CType {
	
	CFG("CONFIG"),
	SERV("SERVICES"),
	MSG("MESSAGES"),
	DB("DATABASE");
	
	private String name;
	
	private CType(String name){this.name = name;}
	
	public String getName(){
		return this.name;
	}
	
	public String pname(){
		return name.toLowerCase();
	}

}
