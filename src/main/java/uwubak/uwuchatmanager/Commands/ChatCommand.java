package uwubak.uwuchatmanager.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uwubak.uwuchatmanager.Utils.ConfigBuilder;
import uwubak.uwuchatmanager.Uwu_ChatManager;

public class ChatCommand implements CommandExecutor {

    private final Uwu_ChatManager plugin;

    public ChatCommand(Uwu_ChatManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                if (player.hasPermission("chat.use")) {
                    String usage_type = plugin.getConfig().getString("usage.type");
                    String usage_message = plugin.getConfig().getString("usage.message");

                    ConfigBuilder.SendMessage(usage_message, usage_type, player);
                }
            }

            if (player.hasPermission("chat.use")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("on")) {
                        if (!plugin.chat == true) {
                            plugin.chat = true;

                            String chat_on_type = plugin.getConfig().getString("chat-on.type");
                            String chat_on_message = plugin.getConfig().getString("chat-on.message");
                            chat_on_message = chat_on_message.replace("{PLAYER}", player.getDisplayName());

                            if (plugin.getConfig().getString("chat-alerts").equalsIgnoreCase("true")) {
                                for (Player players : Bukkit.getOnlinePlayers()) {
                                    ConfigBuilder.SendMessage(chat_on_message, chat_on_type, players);
                                }
                            } else {
                                ConfigBuilder.SendMessage(chat_on_message, chat_on_type, player);
                            }
                        } else {

                            String chat_is_on_type = plugin.getConfig().getString("chat-is-on.type");
                            String chat_is_on_message = plugin.getConfig().getString("chat-is-on.message");

                            ConfigBuilder.SendMessage(chat_is_on_message, chat_is_on_type, player);
                        }
                    }

                    if (args[0].equalsIgnoreCase("off")) {
                        if (!plugin.chat == false) {
                            plugin.chat = false;

                            String chat_off_type = plugin.getConfig().getString("chat-off.type");
                            String chat_off_message = plugin.getConfig().getString("chat-off.message");
                            chat_off_message = chat_off_message.replace("{PLAYER}", player.getDisplayName());

                            if (plugin.getConfig().getString("chat-alerts").equalsIgnoreCase("true")) {
                                for (Player players : Bukkit.getOnlinePlayers()) {
                                    ConfigBuilder.SendMessage(chat_off_message, chat_off_type, players);
                                }
                            } else {
                                ConfigBuilder.SendMessage(chat_off_message, chat_off_type, player);
                            }

                        } else {
                            String chat_is_off_type = plugin.getConfig().getString("chat-is-off.type");
                            String chat_is_off_message = plugin.getConfig().getString("chat-is-off.message");

                            ConfigBuilder.SendMessage(chat_is_off_message, chat_is_off_type, player);
                        }
                    }

                    if (args[0].equalsIgnoreCase("clear")) {
                            for (int i = 0; i < 150; i++) {
                                for (Player players : Bukkit.getOnlinePlayers()) {
                                    if (!players.hasPermission("chat.clear")) {
                                        players.sendMessage("");
                                    } else {
                                        ///
                                    }
                                }
                            }
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                String chat_clear_type = plugin.getConfig().getString("chat-clear.type");
                                String chat_clear_message = plugin.getConfig().getString("chat-clear.message");
                                chat_clear_message = chat_clear_message.replace("{PLAYER}", player.getDisplayName());

                                ConfigBuilder.SendMessage(chat_clear_message, chat_clear_type, players);
                            }
                        }


                    if (args[0].equalsIgnoreCase("reload")) {

                        plugin.reloadConfig();

                        String reload_type = plugin.getConfig().getString("reload.type");
                        String reload_message = plugin.getConfig().getString("reload.message");

                        ConfigBuilder.SendMessage(reload_message, reload_type, player);

                    }


                }

            } else {
                String no_perms_type = plugin.getConfig().getString("no-permission.type");
                String no_perms_message = plugin.getConfig().getString("no-permission.message");
                ConfigBuilder.SendMessage(no_perms_message, no_perms_type, player);
            }




        }



        return true;
    }
}
