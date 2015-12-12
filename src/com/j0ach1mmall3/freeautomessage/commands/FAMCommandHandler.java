package com.j0ach1mmall3.freeautomessage.commands;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.jlib.commands.CommandHandler;
import com.j0ach1mmall3.jlib.integration.Placeholders;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 12/12/15
 */
public class FAMCommandHandler extends CommandHandler {
    private final Main plugin;

    public FAMCommandHandler(Main plugin) {
        this.plugin = plugin;
    }

    public boolean handleCommand(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("fam.reload")) {
                sender.sendMessage(Placeholders.parse(this.plugin.getBabies().getNoPermissionMessage(), sender instanceof Player?(Player) sender:null));
                return true;
            }
            this.plugin.reload();
            sender.sendMessage(ChatColor.GREEN + "Reloaded Configs!");
            return true;
        }
        if (args[0].equalsIgnoreCase("addsign")) {
            if (!sender.hasPermission("fam.addsign")) {
                sender.sendMessage(Placeholders.parse(this.plugin.getBabies().getNoPermissionMessage(), sender instanceof Player?(Player) sender:null));
                return true;
            }
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You need to be a player to execute this command!");
                return true;
            }
            Player p = (Player) sender;
            Block b = p.getTargetBlock((Set<Material>) null, 5);
            if(!(b.getState() instanceof Sign)) {
                p.sendMessage(ChatColor.RED + "You need to be looking at a placed Sign!");
                return true;
            }
            if (args.length < 2) {
                p.sendMessage(ChatColor.RED + "You need to specify a Broadcaster you want to add this Sign to!");
                return true;
            }
            com.j0ach1mmall3.freeautomessage.api.Sign sign = new com.j0ach1mmall3.freeautomessage.api.Sign(this.plugin, args[1], b.getLocation());
            if(!sign.isSignsBroadcaster()) {
                p.sendMessage(ChatColor.RED + args[1] + " isn't a valid Signs Broadcaster!");
                return true;
            }
            if(sign.exists()) {
                p.sendMessage(ChatColor.RED + args[1] + " already has this Sign!");
                return true;
            }
            sign.add();
            p.sendMessage(ChatColor.GREEN + "Successfully added Sign to " + args[1]);
            p.sendMessage(ChatColor.GOLD + "Use /fam reload to apply the changes");
            return true;
        }
        if (args[0].equalsIgnoreCase("removesign")) {
            if (!sender.hasPermission("fam.removesign")) {
                sender.sendMessage(Placeholders.parse(this.plugin.getBabies().getNoPermissionMessage(), sender instanceof Player?(Player) sender:null));
                return true;
            }
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You need to be a player to execute this command!");
                return true;
            }
            Player p = (Player) sender;
            Block b = p.getTargetBlock((Set<Material>) null, 5);
            if(!(b.getState() instanceof Sign)) {
                p.sendMessage(ChatColor.RED + "You need to be looking at a placed Sign!");
                return true;
            }
            if (args.length < 2) {
                p.sendMessage(ChatColor.RED + "You need to specify a Broadcaster you want to remove this Sign from!");
                return true;
            }
            com.j0ach1mmall3.freeautomessage.api.Sign sign = new com.j0ach1mmall3.freeautomessage.api.Sign(this.plugin, args[1], b.getLocation());
            if(!sign.isSignsBroadcaster()) {
                p.sendMessage(ChatColor.RED + args[1] + " isn't a valid Signs Broadcaster!");
                return true;
            }
            if(!sign.exists()) {
                p.sendMessage(ChatColor.RED + args[1] + " doesn't have this Sign!");
                return true;
            }
            sign.remove();
            p.sendMessage(ChatColor.GREEN + "Successfully removed Sign from " + args[1]);
            p.sendMessage(ChatColor.GOLD + "Use /fam reload to apply the changes");
            return true;
        }
        if (args[0].equalsIgnoreCase("listsigns")) {
            if (!sender.hasPermission("fam.listsigns")) {
                sender.sendMessage(Placeholders.parse(this.plugin.getBabies().getNoPermissionMessage(), sender instanceof Player?(Player) sender:null));
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "You need to specify a Broadcaster you want to list the Signs from!");
                return true;
            }
            com.j0ach1mmall3.freeautomessage.api.Sign sign = new com.j0ach1mmall3.freeautomessage.api.Sign(this.plugin, args[1], null);
            if(!sign.isSignsBroadcaster()) {
                sender.sendMessage(ChatColor.RED + args[1] + " isn't a valid Signs Broadcaster!");
                return true;
            }
            sender.sendMessage(ChatColor.GREEN + args[1] + " Signs:");
            for(String s : sign.list()) {
                String[] parts = s.split("/");
                sender.sendMessage(ChatColor.GOLD + "- World: " + parts[0] + " X: " + parts[1] + " Y: " + parts[2] + " Z: " + parts[3]);
            }
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Usage: /fam reload, /fam addsign, /fam removesign, /fam listsigns");
        return true;
    }
}
