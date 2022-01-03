/* Decompiler 36ms, total 266ms, lines 93 */
package me.aylias.parrot.lifesteal;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.logging.Level;

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
import org.bukkit.scheduler.BukkitRunnable;

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

      Update();
   }

   private void Update() {


//      try {
//         if (new File("plugins/aLifeSteal.jar").exists()) {
//            if (filesCompareByByte(Path.of("plugins/aLifeSteal.jar"), Path.of("plugins/LifeSteal.jar")) != -1) {
//               getLogger().log(Level.INFO, "I gots the update just needa install it bro");
//               Files.copy(Path.of("plugins/aLifeSteal.jar"), Path.of("plugins/LifeSteal.jar"),
//                       StandardCopyOption.REPLACE_EXISTING);
//            } else {
//
//            }
//         } else {
//            InputStream in = new URL("https://github.com/AyliasTheCoder/LifeSteal/blob/master/artifact/LifeSteal.jar?raw=true").openStream();
//            Files.copy(in, Paths.get("plugins/aLifeSteal.jar"), StandardCopyOption.REPLACE_EXISTING);
//
//            if (filesCompareByByte(Path.of("plugins/aLifeSteal.jar"), Path.of("plugins/LifeSteal.jar")) != -1) {
//               getLogger().log(Level.INFO, "They do be different tho");
////            new File("plugins/LifeSteal.jar").delete();
////            Files.copy(Path.of("plugins/aLifeSteal.jar"), Path.of("plugins/LifeSteal.jar"),
////                    StandardCopyOption.REPLACE_EXISTING);
//               new BukkitRunnable() {
//                  @Override
//                  public void run() {
//                     Bukkit.dispatchCommand(getServer().getConsoleSender(), "restart");
//                  }
//               }.runTaskLater(this, 200);
//            } else {
//               new File("plugins/aLifeSteal.jar").delete();
//            }
//         }

      try {
//         if (new File("plugins/aLifeSteal.jar").exists()) {
//            if (filesCompareByByte(Path.of("plugins/aLifeSteal.jar"), Path.of("plugins/LifeSteal.jar")) != -1) {
//               getLogger().log(Level.INFO, "I gots the update just needa install it bro");
//               Files.copy(Path.of("plugins/aLifeSteal.jar"), Path.of("plugins/LifeSteal.jar"),
//                       StandardCopyOption.REPLACE_EXISTING);
//            } else {
//
//            }
//         } else {
         InputStream in = new URL("https://github.com/AyliasTheCoder/LifeSteal/blob/master/artifact/LifeSteal.jar?raw=true").openStream();
         Files.copy(in, Paths.get("plugins/aLifeSteal.jar"), StandardCopyOption.REPLACE_EXISTING);

         if (filesCompareByByte(Path.of("plugins/aLifeSteal.jar"), Path.of("plugins/LifeSteal.jar")) != -1) {
            getLogger().log(Level.INFO, "They do be different tho");
//            new File("plugins/LifeSteal.jar").delete();
//            Files.copy(Path.of("plugins/aLifeSteal.jar"), Path.of("plugins/LifeSteal.jar"),
//                    StandardCopyOption.REPLACE_EXISTING);
            new BukkitRunnable() {
               @Override
               public void run() {
                  getServer().getPluginManager().disablePlugin(instance);
                  try {
                     Files.copy(Path.of("plugins/aLifeSteal.jar"), Path.of("plugins/LifeSteal.jar"),
                       StandardCopyOption.REPLACE_EXISTING);
                     new File("plugins/aLifeSteal.jar").delete();
                  } catch (IOException e) {}
                  Bukkit.dispatchCommand(getServer().getConsoleSender(), "restart");
               }
            }.runTaskLater(this, 200);
         } else {
            new File("plugins/aLifeSteal.jar").delete();
         }
//         }


      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public static long filesCompareByByte(Path path1, Path path2) throws IOException {
      try (BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(path1.toFile()));
           BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(path2.toFile()))) {

         int ch = 0;
         long pos = 1;
         while ((ch = fis1.read()) != -1) {
            if (ch != fis2.read()) {
               return pos;
            }
            pos++;
         }
         if (fis2.read() == -1) {
            return -1;
         }
         else {
            return pos;
         }
      }
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
