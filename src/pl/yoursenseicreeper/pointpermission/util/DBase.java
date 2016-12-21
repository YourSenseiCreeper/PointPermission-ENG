package pl.yoursenseicreeper.pointpermission.util;

public class DBase{
	
	private int startPoints;
	private String preFormat;
	private String postFormat;
	private boolean chatFormat;
	private int autosaveTime;
	private boolean signs;
	
	public DBase(int startPoints, String preFormat, String postFormat, boolean chatFormat, int autosaveTime, boolean signs){
		this.startPoints = startPoints;
		this.preFormat = preFormat;
		this.postFormat = postFormat;
		this.chatFormat = chatFormat;
		this.autosaveTime = autosaveTime;
		this.signs = signs;
	}
	
	public int getStartPoints(){ return this.startPoints;}
	
	public String getPreFormat(){ return this.preFormat;}
	
	public String getPostFormat(){ return this.postFormat;}
	
	public boolean isChatFormatted(){ return this.chatFormat;}
	
	public int getAutosaveTime(){ return this.autosaveTime;}
	
	public boolean areSignsEnabled(){ return this.signs;}

}
