package eventlistener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class eventlistener implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){
        event.getPlayer().sendMessage("\u524d\u5f80 https://exptechweb.mywire.org/ \u53ef\u4ee5\u8cfc\u8cb7\u7269\u8cc7");
    }
}