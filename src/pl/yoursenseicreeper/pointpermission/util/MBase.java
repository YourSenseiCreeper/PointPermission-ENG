package pl.yoursenseicreeper.pointpermission.util;

public class MBase {
	
	private String prefix;
	private String brak_permisji;
	private String brak_uslugi;
	private String nie_dla_konsoli;
	private String gracz_nie_istnieje;
	private String znak_punkty;
    private String znak_usluga_lista;
    private String znak_usluga_info;
    private String znak_kup;
    
    public MBase(String prefix, String brak_permisji, String brak_uslugi, String nie_dla_konsoli, String gracz_nie_istnieje,
    		String znak_punkty, String znak_usluga_lista, String znak_usluga_info, String znak_kup){
    	this.prefix = prefix;
    	this.brak_permisji = brak_permisji;
    	this.brak_uslugi = brak_uslugi;
    	this.nie_dla_konsoli = nie_dla_konsoli;
    	this.gracz_nie_istnieje = gracz_nie_istnieje;
    	this.znak_punkty = znak_punkty;
    	this.znak_usluga_info = znak_usluga_info;
    	this.znak_usluga_lista = znak_usluga_lista;
    	this.znak_kup = znak_kup;
    }
    
    public String getPrefix(){return this.prefix;}
    
    public String getPermisionAbsence(){return this.brak_permisji;}

    public String getNotForConsole(){return this.nie_dla_konsoli;}
    
    public String getPlayerNotExist(){return this.gracz_nie_istnieje;}
    
    public String getServiceAbsence(){return this.brak_uslugi;}
    
    public String getSignPoints(){return this.znak_punkty;}
    
    public String getSignServiceList(){return this.znak_usluga_lista;}
    
    public String getSignServiceInfo(){return this.znak_usluga_info;}
    
    public String getSignBuy(){return this.znak_kup;}

}
