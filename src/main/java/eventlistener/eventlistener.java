package eventlistener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class eventlistener implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){
        ItemStack itemStack=new ItemStack(Material.valueOf("SAND"),100);
        //event.getPlayer().getInventory().addItem(itemStack);
        //event.getPlayer().sendMessage(String.valueOf(event.getPlayer().getInventory().contains(itemStack)));
    }

}