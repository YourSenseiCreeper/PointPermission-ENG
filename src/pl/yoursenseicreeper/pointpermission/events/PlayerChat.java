package pl.yoursenseicreeper.pointpermission.events;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pl.yoursenseicreeper.pointpermission.Main;
import pl.yoursenseicreeper.pointpermission.PointPermission;

public class PlayerChat extends PointPermission implements Listener {
	
	   public PlayerChat(Main pp){
		   pp.getServer().getPluginManager().registerEvents(this, pp);
	   }
	
	@EventHandler
    private void onPlayerChat(AsyncPlayerChatEvent e) {
            UUID uid = e.getPlayer().getUniqueId();
            String format = e.getFormat();
            String pre = fix(getDBase().getPreFormat());
            String post = fix(getDBase().getPostFormat());
            int pkt = players.get(uid);
            String chatForm = pre+pkt+post;
            boolean chat = getDBase().isChatFormatted();
            if (chat){
            	format = chatForm+format;
            	e.setFormat(format);
            }else{
            	return;
            }
    
}
	
}
