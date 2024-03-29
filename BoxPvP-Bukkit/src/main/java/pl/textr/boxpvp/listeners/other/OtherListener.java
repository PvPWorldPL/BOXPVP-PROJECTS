package pl.textr.boxpvp.listeners.other;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import api.data.UserProfile;
import api.managers.UserAccountManager;
import api.menu.AnvilIMenu;
import api.menu.ShopMenu;
import api.menu.SzamanMenu;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import pl.textr.boxpvp.Main;
import pl.textr.boxpvp.utils.ChatUtil;
import pl.textr.boxpvp.utils.ItemBuilder;
import pl.textr.boxpvp.utils.RandomUtil;

import java.util.*;

public class OtherListener implements Listener {


    private static Location PVP_LOCATION;
    private static Location SPAWN_LOCATION;
    private static Location KOPALNIA_LOCATION;
    private static Location KOPALNIAPREMIUM_LOCATION;
    
    

    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPortalEntered(EntityPortalEnterEvent e) {
        if (e.getEntityType() != EntityType.PLAYER) {
            return;
        }
        Player p = (Player) e.getEntity();

        if (PVP_LOCATION == null) {
            PVP_LOCATION = Main.getPlugin().getConfiguration().getPvpLocation();
        }
        p.teleport(PVP_LOCATION);
    }

    @EventHandler
    public void onPortalTravel2(PlayerPortalEvent event) {
        Player p = event.getPlayer();

        if (event.getCause() == TeleportCause.END_PORTAL) {
            event.setCanCreatePortal(false);
            event.setCancelled(true);

            if (SPAWN_LOCATION == null) {
                SPAWN_LOCATION = Main.getPlugin().getConfiguration().getSpawnLocation(); 
            }
           p.teleport(SPAWN_LOCATION);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
      Player player = e.getPlayer();
      Block block = e.getClickedBlock();
      if (block == null) {
        return;
      }
      if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
        return;
      }
      if (block.getType() == Material.ANVIL) {
        e.setCancelled(true);
        AnvilIMenu.show(player);
      } 
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) {
            return;
        }

        Player attacker = (Player) e.getDamager();
        if (e.isCancelled()) {
            return;
        }

        UserProfile attackerUser = UserAccountManager.getUser(attacker);
        if (attackerUser.getPerkWampiryzmu() == 1 && RandomUtil.getChance(Main.getPlugin().getConfiguration().getPerkWampiryzmu1())) {
            attacker.setFireTicks(0);
            attacker.setFoodLevel(20);
            attacker.setHealth(attacker.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            ChatUtil.sendMessage(attacker, "&7Uzyto perku &awampiryzmu &7zostales uleczony &8(&aPoziom 1&8)");
        } else if (attackerUser.getPerkWampiryzmu() == 2 && RandomUtil.getChance(Main.getPlugin().getConfiguration().getPerkWampiryzmu2())) {
            attacker.setFireTicks(0);
            attacker.setFoodLevel(20);
            attacker.setHealth(attacker.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            ChatUtil.sendMessage(attacker, "&7Uzyto perku &awampiryzmu &7zostales uleczony &8(&aPoziom 2&8)");
        } else if (attackerUser.getPerkWampiryzmu() == 3 && RandomUtil.getChance(Main.getPlugin().getConfiguration().getPerkWampiryzmu3())) {
            attacker.setFireTicks(0);
            attacker.setFoodLevel(20);
            attacker.setHealth(attacker.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            ChatUtil.sendMessage(attacker, "&7Uzyto perku &awampiryzmu &7zostales uleczony &8(&aPoziom 3&8)");
        } else if (attackerUser.getPerkWampiryzmu() == 4 && RandomUtil.getChance(Main.getPlugin().getConfiguration().getPerkWampiryzmu4())) {
            attacker.setFireTicks(0);
            attacker.setFoodLevel(20);
            attacker.setHealth(attacker.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            ChatUtil.sendMessage(attacker, "&7Uzyto perku &awampiryzmu &7zostales uleczony &8(&aPoziom 4&8)");
        }
    }





    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
            event.getWorld().setWeatherDuration(0);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent event) {
        if (event.toThunderState()) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void interactVoucher(final PlayerInteractEvent e) {
        if (e.getItem() == null) {
            return;
        }
        final Player p = e.getPlayer();
        final ItemStack is = p.getInventory().getItemInMainHand();


        final ItemBuilder sponsor = new ItemBuilder(Material.PAPER, 1).setTitle(ChatUtil.fixColor("&e&lVoucher SPONSOR"))
                .addLore("")
                .addLore("")
                .addLore(ChatUtil.fixColor("&7Kliknij aby aktywować range"))
                .addLore("")
                .setGlow();

        final ItemBuilder vip = new ItemBuilder(Material.PAPER, 1).setTitle(ChatUtil.fixColor("&e&lVoucher VIP"))
                .addLore("")
                .addLore("")
                .addLore(ChatUtil.fixColor("&7Kliknij aby aktywować range"))
                .addLore("")
                .setGlow();
        final ItemBuilder svip = new ItemBuilder(Material.PAPER, 1).setTitle(ChatUtil.fixColor("&e&lVoucher SVIP"))
                .addLore("")
                .addLore("")
                .addLore(ChatUtil.fixColor("&7Kliknij aby aktywować range"))
                .addLore("")
                .setGlow();

        if (is.isSimilar(sponsor.ToItemStack())) {
            p.getInventory().remove(sponsor.ToItemStack());
            ChatUtil.sendMessage(p, "&aPomyslnie uzyles Vouchera Sponsor!");
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " group set sponsor");
            return;
        }
        if (is.isSimilar(svip.ToItemStack())) {
            p.getInventory().remove(svip.ToItemStack());
            ChatUtil.sendMessage(p, "&aPomyslnie uzyles Vouchera SVIP!");
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " group set svip");
            return;
        }
        if (is.isSimilar(vip.ToItemStack())) {
            p.getInventory().remove(vip.ToItemStack());
            ChatUtil.sendMessage(p, "&aPomyslnie uzyles Vouchera vip!");
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " group set vip");
        }
    }






    @EventHandler
    public void onRightClick(NPCRightClickEvent event) {
        NPC npc = event.getNPC();
        Player player = event.getClicker();

        if (npc.getName().contains("powrotstrefa")) {
            if (KOPALNIA_LOCATION == null) {
            KOPALNIA_LOCATION = Main.getPlugin().getConfiguration().getKopalniaLocation();
            }
            player.teleport(KOPALNIA_LOCATION);
            return;
        }

        if (npc.getName().contains("Strefa")) {
            if (KOPALNIA_LOCATION == null) {
            KOPALNIA_LOCATION = Main.getPlugin().getConfiguration().getKopalniaLocation();
            }
            player.teleport(KOPALNIA_LOCATION);
            return;
        }

        if (npc.getName().contains("strefapremium")) {
            if (player.hasPermission("core.premium")) {
            if (KOPALNIAPREMIUM_LOCATION == null) {
            KOPALNIAPREMIUM_LOCATION = Main.getPlugin().getConfiguration().getKopalniapremiumLocation();
            }
            player.teleport(KOPALNIAPREMIUM_LOCATION);
             return;
            }
            ChatUtil.sendMessage(player, ChatUtil.translateHexColorCodes("&8[&C&l!&8] &7Ta strefa jest dostępna od rangi &6premium"));
            return;
           }

        if (npc.getName().contains("PowrotPremium")) {
            if (KOPALNIAPREMIUM_LOCATION == null) {
            KOPALNIAPREMIUM_LOCATION = Main.getPlugin().getConfiguration().getKopalniapremiumLocation();
            }
            player.teleport(KOPALNIAPREMIUM_LOCATION);
            return;
          }
       if (npc.getName().contains("spawn")) {
            if (SPAWN_LOCATION == null) {
            SPAWN_LOCATION = Main.getPlugin().getConfiguration().getSpawnLocation(); 
            }
            player.teleport(SPAWN_LOCATION);
            return;
        }
        if (npc.getName().contains("spawnpowrotpremium")) {
           if (SPAWN_LOCATION == null) {
           SPAWN_LOCATION = Main.getPlugin().getConfiguration().getSpawnLocation(); 
           }
           player.teleport(SPAWN_LOCATION);
            return;
        }
        
        if (npc.getName().contains("pvp")) {
            if (PVP_LOCATION == null) {
            PVP_LOCATION = Main.getPlugin().getConfiguration().getPvpLocation(); 
            }
            player.teleport(PVP_LOCATION);
            return;
        }
        if (npc.getName().contains("offrynek")) {
            ChatUtil.sendTitle(player, ChatUtil.translateHexColorCodes(""), "&7Rynek jest dostepny na &FCH1");	
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1.0f, 1.0f);
             return;
            }    
        if (npc.getName().contains("kowal")) {
           AnvilIMenu.show(player);
           return;
    }
     if (npc.getName().contains("sklepczas")) {
    	 ShopMenu.menu(player);
    	 return;
     }
     if (npc.getName().contains("szaman")) {
    	 SzamanMenu.show(player);

     }
     
    }
}

