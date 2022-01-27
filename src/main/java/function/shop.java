package function;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.logger;
import core.network;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Objects;

import static main.whes1015.DATA;

public class shop {
    public static void Shop() {
        JsonElement Data = JsonParser.parseString(DATA.toString());
        JsonObject data = Data.getAsJsonObject();
        data.addProperty("Type", "checklist");
        data.addProperty("FormatVersion", 1);
        JsonObject response = network.Post(data);
        if(response==null) return;
        try {
            if (response.get("response").getAsJsonArray().size() != 0) {
                JsonArray handler = response.get("response").getAsJsonArray();
                for (int i = 0; i < handler.size(); i++) {
                    JsonObject handlerData = handler.get(i).getAsJsonObject();
                    if (Objects.equals(handlerData.get("function").getAsString(), "buy")) {
                        Player player1 = Bukkit.getPlayer(handlerData.get("player").getAsString());
                        ItemStack itemStack = new ItemStack(Material.valueOf(handlerData.get("item").getAsString()), handlerData.get("amount").getAsInt());
                        assert player1 != null;
                        player1.getInventory().addItem(itemStack);
                        JsonElement Data1 = JsonParser.parseString(DATA.toString());
                        JsonObject data1 = Data1.getAsJsonObject();
                        data1.addProperty("Type", "sellcheck");
                        data1.addProperty("Uuid", String.valueOf(player1.getUniqueId()));
                        data1.addProperty("FormatVersion", 1);
                        data1.addProperty("item", handlerData.get("item").getAsString());
                        data1.addProperty("amount", handlerData.get("amount").getAsInt());
                        data1.addProperty("id", handlerData.get("id").getAsInt());
                        network.Post(data1);
                    } else if (Objects.equals(handlerData.get("function").getAsString(), "sell")) {
                        Player player1 = null;
                        for (int j = 0; j < handlerData.get("player").getAsJsonArray().size(); j++) {
                            try {
                                if (Bukkit.getPlayer(handlerData.get("player").getAsJsonArray().get(i).getAsString()) != null) {
                                    player1 = Bukkit.getPlayer(handlerData.get("player").getAsJsonArray().get(i).getAsString());
                                }
                            }catch (IndexOutOfBoundsException e){
                                JsonElement Data1 = JsonParser.parseString(DATA.toString());
                                JsonObject data1 = Data1.getAsJsonObject();
                                data1.addProperty("Type", "sellcheck");
                                data1.addProperty("FormatVersion", 1);
                                data1.addProperty("item", handlerData.get("item").getAsString());
                                data1.addProperty("amount", handlerData.get("amount").getAsInt());
                                data1.addProperty("id", handlerData.get("id").getAsInt());
                                data1.addProperty("status", false);
                                network.Post(data1);
                            }
                        }
                        if (player1 == null) return;
                        JsonElement Data1 = JsonParser.parseString(DATA.toString());
                        JsonObject data1 = Data1.getAsJsonObject();
                        data1.addProperty("Type", "sellcheck");
                        data1.addProperty("Uuid", String.valueOf(player1.getUniqueId()));
                        data1.addProperty("FormatVersion", 1);
                        data1.addProperty("item", handlerData.get("item").getAsString());
                        data1.addProperty("amount", handlerData.get("amount").getAsInt());
                        data1.addProperty("id", handlerData.get("id").getAsInt());
                        ItemStack itemStack = new ItemStack(Material.valueOf(handlerData.get("item").getAsString()), handlerData.get("amount").getAsInt());
                        if (player1.getInventory().contains(itemStack)) {
                            player1.getInventory().clear(player1.getInventory().first(itemStack));
                            data1.addProperty("status", true);
                        } else {
                            data1.addProperty("status", false);
                        }
                        network.Post(data1);
                    }
                }
            }
        }catch (RuntimeException e){
            logger.log("ERROR","Economy_Post",e.getMessage());
        }
    }
}
