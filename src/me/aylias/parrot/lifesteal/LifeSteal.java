/* Decompiler 36ms, total 266ms, lines 93 */
package me.aylias.parrot.lifesteal;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class LifeSteal extends JavaPlugin implements Listener {
   public static LifeSteal instance;
   public ItemStack beaconOfLife;
   public ItemStack heart;
   public ItemStack heartFragment;
   Inventory gui;

   public void onEnable() {
      instance = this;
      this.getConfig().options().copyDefaults();
      this.saveDefaultConfig();
      this.beaconOfLife = new ItemStack(Material.BEACON);
      ItemMeta beaconOfLifeMeta = this.beaconOfLife.getItemMeta();
      beaconOfLifeMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Beacon Of Life");
      beaconOfLifeMeta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "Right-Click this to bring somone back to life!"));
      this.beaconOfLife.setItemMeta(beaconOfLifeMeta);
      this.heart = new ItemStack(Material.FIREWORK_STAR);
      ItemMeta heartMeta = this.heart.getItemMeta();
      heartMeta.setDisplayName(ChatColor.RED + "Heart");
      heartMeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Use 4 of these to craft the Beacon Of Life!"));
      this.heart.setItemMeta(heartMeta);
      this.heartFragment = new ItemStack(Material.CARROT_ON_A_STICK);
      ItemMeta heartFragmentMeta = this.heartFragment.getItemMeta();
      heartFragmentMeta.setDisplayName(ChatColor.RED + "Heart Fragment");
      heartFragmentMeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "Use 4 of these to craft a Heart!"));
      this.heartFragment.setItemMeta(heartFragmentMeta);
      new Recipes(this);
      Bukkit.getPluginManager().registerEvents(new Events(), this);
      Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (label.equalsIgnoreCase("withdraw") && sender instanceof Player player && args.length == 1) {
         boolean var6 = false;

         int hearts;
         try {
            hearts = Integer.parseInt(args[0]);
         } catch (NumberFormatException var10) {
            player.sendMessage(ChatColor.RED + "Please put in the number of hearts you have, and make sure it's an integer!");
            return false;
         }

         if (hearts > 0) {
            int playerHearts = (int)player.getMaxHealth() - (hearts * 2);
            if (playerHearts > 0) {
               player.setMaxHealth(playerHearts);
               int i;
               if (player.getInventory().firstEmpty() != -1) {
                  for(i = 0; i < hearts; ++i) {
                     player.getInventory().addItem(getInstance().heart);
                  }
               } else {
                  for(i = 0; i < hearts; ++i) {
                     player.getWorld().dropItemNaturally(player.getLocation(), getInstance().heart);
                  }
               }

               player.sendMessage(ChatColor.GOLD + "Successfully withdrawed hearts!");
            } else {
               player.sendMessage(ChatColor.RED + "You can't withdraw more or the same amount of hearts than you currently have!");
            }
         } else if (hearts == 0) {
            player.sendMessage(ChatColor.GOLD + "Whats the point of withdrawing 0 hearts!");
         } else {
            ChatColor var10001 = ChatColor.GOLD;
            player.sendMessage(var10001 + "" + player.getMaxHealth() + " - " + hearts + " = " + (player.getMaxHealth() - (double)hearts) + ", and that's greater than your original hearts! You think imma let you add hearts on a /withdraw command!");
         }
      }

      return true;
   }

   public static LifeSteal getInstance() {
      return instance;
   }
}
