/* Decompiler 3ms, total 153ms, lines 28 */
package me.aylias.parrot.lifesteal;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class Recipes {
   public Recipes(LifeSteal lifeSteal) {
      ShapedRecipe heartFragment = new ShapedRecipe(new NamespacedKey(lifeSteal, "heartfragment"), lifeSteal.heartFragment);
      heartFragment.shape(new String[]{"^^^", "^%^", "^^^"});
      heartFragment.setIngredient('^', Material.GOLD_BLOCK);
      heartFragment.setIngredient('%', Material.TOTEM_OF_UNDYING);
      ShapedRecipe heart = new ShapedRecipe(new NamespacedKey(lifeSteal, "heart"), lifeSteal.heart);
      heart.shape(new String[]{"&!&", "!%!", "&!&"});
      heart.setIngredient('&', Material.CARROT_ON_A_STICK);
      heart.setIngredient('!', Material.DIAMOND_BLOCK);
      heart.setIngredient('%', Material.ELYTRA);
      ShapedRecipe beaconOfLife = new ShapedRecipe(new NamespacedKey(lifeSteal, "beaconoflife"), lifeSteal.beaconOfLife);
      beaconOfLife.shape(new String[]{"fnf", "nsn", "fnf"});
      beaconOfLife.setIngredient('n', Material.NETHERITE_INGOT);
      beaconOfLife.setIngredient('f', Material.FIREWORK_STAR);
      beaconOfLife.setIngredient('s', Material.NETHER_STAR);
      lifeSteal.getServer().addRecipe(heartFragment);
      lifeSteal.getServer().addRecipe(heart);
      lifeSteal.getServer().addRecipe(beaconOfLife);
   }
}
