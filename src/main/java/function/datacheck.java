package function;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.network;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static main.whes1015.DATA;

public class datacheck {
    public static JsonObject playerData = new JsonObject();

    public static void DataCheck() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ItemStack[] itemStacks = Objects.requireNonNull(Bukkit.getPlayer(player.getName())).getInventory().getContents();
            JsonArray playerItem = new JsonArray();
            String playerString = "";
            for (ItemStack itemStack1 : itemStacks) {
                JsonObject jsonObject = new JsonObject();
                if (itemStack1 == null) {
                    jsonObject.addProperty("item", "null");
                    jsonObject.addProperty("amount", "null");
                    playerString = playerString + "null/null.";
                } else {
                    jsonObject.addProperty("item", itemStack1.getType().name());
                    jsonObject.addProperty("amount", itemStack1.getAmount());
                    playerString = playerString + itemStack1.getType().name() + "/" + itemStack1.getAmount() + ".";
                }
                playerItem.add(jsonObject);
            }
            if (playerData.get(player.getName()) == null || !Objects.equals(playerData.get(player.getName()).getAsString(), playerString)) {
                JsonElement Data = JsonParser.parseString(DATA.toString());
                JsonObject data = Data.getAsJsonObject();
                data.addProperty("Type", "Inventory");
                data.addProperty("Uuid", String.valueOf(player.getUniqueId()));
                data.addProperty("FormatVersion", 1);
                data.add("Value", playerItem);
                playerData.addProperty(player.getName(), playerString);
                network.Post(data);
            }
        }
    }
}
