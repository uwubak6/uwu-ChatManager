package uwubak.uwuchatmanager.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import uwubak.uwuchatmanager.Utils.ConfigBuilder;
import uwubak.uwuchatmanager.Uwu_ChatManager;

public class ChatEvent implements Listener {

    private final Uwu_ChatManager plugin;

    public ChatEvent(Uwu_ChatManager plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void ChatUse(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (plugin.chat == true) {
            if (!player.hasPermission("chat.bypass")) {
                for (String blocked : plugin.getConfig().getStringList("blocked-words")) {
                    if (event.getMessage().contains(blocked)) {
                        event.setCancelled(true);
                        String words_type = plugin.getConfig().getString("words.type");
                        String words_message = plugin.getConfig().getString("words.message");

                        ConfigBuilder.SendMessage(words_message, words_type, player);
                    }
                }
            } else {
                ///
            }
        }


        if (!player.hasPermission("chat.bypass")) {
            if (plugin.chat == false) {
                event.setCancelled(true);
                String chat_blocked_type = plugin.getConfig().getString("chat-blocked.type");
                String chat_blocked_message = plugin.getConfig().getString("chat-blocked.message");

                ConfigBuilder.SendMessage(chat_blocked_message, chat_blocked_type, player);
            }
        } else {
            ///
        }



    }


}
