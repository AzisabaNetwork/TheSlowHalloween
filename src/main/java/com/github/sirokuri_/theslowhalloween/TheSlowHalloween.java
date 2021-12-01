package com.github.sirokuri_.theslowhalloween;

import com.gmail.nossr50.mcMMO;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class TheSlowHalloween extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("tsh")) {
            if (args.length <= 0) return true;
            if (args[0].equalsIgnoreCase("reload")) {
                if(sender.hasPermission("TheSlowHalloween.permission.Admin")) {
                    reloadConfig();
                    p.sendMessage("configリロードしました");
                }
            }
            if (args[0].equalsIgnoreCase("help")) {
                if(sender.hasPermission("TheSlowHalloween.permission.Admin")) {
                    sender.sendMessage("どうされましたか?\nエラーが出ていれば46kuriにディスコードでメンションを送ってください");
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("tsh")) return super.onTabComplete(sender, command, alias, args);
        if (args.length == 1) {
            if(sender.hasPermission("TheSlowHalloween.permission.Admin")) {
                if (args[0].length() == 0) {
                    return Arrays.asList("reload","help");
                } else {
                    //入力されている文字列と先頭一致
                    if ("reload".startsWith(args[0])) {
                        return Collections.singletonList("reload");
                    }
                }
            }
        }
        return super.onTabComplete(sender, command, alias, args);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        Material material = block.getType();
        if (mcMMO.getPlaceStore().isTrue(block)) return;
        String normalWorld = getConfig().getString("normal");
        String netherWorld = getConfig().getString("nether");
        String endWorld = getConfig().getString("end");
        World world = block.getWorld();
        if (world.getName().equals(normalWorld) || world.getName().equals(netherWorld) || world.getName().equals(endWorld)) {
            if (Math.abs(block.getLocation().getX() - world.getSpawnLocation().getX()) < 20) return;
            if (Math.abs(block.getLocation().getZ() - world.getSpawnLocation().getZ()) < 20) return;
            if (material == Material.SAND
                    || material == Material.STONE
                    || material == Material.GRASS_BLOCK
                    || material == Material.GRANITE
                    || material == Material.POLISHED_GRANITE
                    || material == Material.DIORITE
                    || material == Material.POLISHED_DIORITE
                    || material == Material.ANDESITE
                    || material == Material.POLISHED_ANDESITE
                    || material == Material.DIRT
                    || material == Material.COARSE_DIRT
                    || material == Material.PODZOL
                    || material == Material.CRIMSON_NYLIUM
                    || material == Material.WARPED_NYLIUM
                    || material == Material.COBBLESTONE
                    || material == Material.RED_SAND
                    || material == Material.GRAVEL
                    || material == Material.GOLD_ORE
                    || material == Material.IRON_ORE
                    || material == Material.COAL_ORE
                    || material == Material.NETHER_GOLD_ORE
                    || material == Material.OAK_LOG
                    || material == Material.SPRUCE_LOG
                    || material == Material.BIRCH_LOG
                    || material == Material.JUNGLE_LOG
                    || material == Material.ACACIA_LOG
                    || material == Material.DARK_OAK_LOG
                    || material == Material.CRIMSON_STEM
                    || material == Material.WARPED_STEM
                    || material == Material.STRIPPED_OAK_LOG
                    || material == Material.STRIPPED_SPRUCE_LOG
                    || material == Material.STRIPPED_BIRCH_LOG
                    || material == Material.STRIPPED_JUNGLE_LOG
                    || material == Material.STRIPPED_ACACIA_LOG
                    || material == Material.STRIPPED_DARK_OAK_LOG
                    || material == Material.STRIPPED_WARPED_STEM
                    || material == Material.STRIPPED_OAK_WOOD
                    || material == Material.STRIPPED_SPRUCE_WOOD
                    || material == Material.STRIPPED_BIRCH_WOOD
                    || material == Material.STRIPPED_JUNGLE_WOOD
                    || material == Material.STRIPPED_ACACIA_WOOD
                    || material == Material.STRIPPED_DARK_OAK_WOOD
                    || material == Material.STRIPPED_CRIMSON_HYPHAE
                    || material == Material.STRIPPED_WARPED_HYPHAE
                    || material == Material.OAK_WOOD
                    || material == Material.SPRUCE_WOOD
                    || material == Material.BIRCH_WOOD
                    || material == Material.JUNGLE_WOOD
                    || material == Material.ACACIA_WOOD
                    || material == Material.DARK_OAK_WOOD
                    || material == Material.CRIMSON_HYPHAE
                    || material == Material.WARPED_HYPHAE
                    || material == Material.LAPIS_ORE
                    || material == Material.SANDSTONE
                    || material == Material.CHISELED_SANDSTONE
                    || material == Material.CUT_SANDSTONE
                    || material == Material.SMOOTH_RED_SANDSTONE
                    || material == Material.SMOOTH_SANDSTONE
                    || material == Material.SMOOTH_STONE
                    || material == Material.MOSSY_COBBLESTONE
                    || material == Material.OBSIDIAN
                    || material == Material.PURPUR_BLOCK
                    || material == Material.PURPUR_PILLAR
                    || material == Material.DIAMOND_ORE
                    || material == Material.REDSTONE_ORE
                    || material == Material.CLAY
                    || material == Material.NETHERRACK
                    || material == Material.SOUL_SAND
                    || material == Material.SOUL_SOIL
                    || material == Material.BASALT
                    || material == Material.POLISHED_BASALT
                    || material == Material.STONE_BRICKS
                    || material == Material.MOSSY_STONE_BRICKS
                    || material == Material.CRACKED_STONE_BRICKS
                    || material == Material.CHISELED_STONE_BRICKS
                    || material == Material.MYCELIUM
                    || material == Material.NETHER_BRICKS
                    || material == Material.CRACKED_NETHER_BRICKS
                    || material == Material.CHISELED_NETHER_BRICKS
                    || material == Material.END_STONE
                    || material == Material.END_STONE_BRICKS
                    || material == Material.EMERALD_ORE
                    || material == Material.NETHER_QUARTZ_ORE
                    || material == Material.RED_SANDSTONE
                    || material == Material.CHISELED_RED_SANDSTONE
                    || material == Material.CUT_RED_SANDSTONE
                    || material == Material.MAGMA_BLOCK
                    || material == Material.NETHER_WART_BLOCK
                    || material == Material.WARPED_WART_BLOCK
                    || material == Material.RED_NETHER_BRICKS
                    || material == Material.NETHERITE_BLOCK
                    || material == Material.ANCIENT_DEBRIS
                    || material == Material.BLACKSTONE
                    || material == Material.GILDED_BLACKSTONE
                    || material == Material.POLISHED_BLACKSTONE
                    || material == Material.DEEPSLATE
                    || material == Material.DRIPSTONE_BLOCK
                    || material == Material.TUFF
                    || material == Material.CALCITE
                    || material == Material.DEEPSLATE_COPPER_ORE
                    || material == Material.DEEPSLATE_IRON_ORE
                    || material == Material.DEEPSLATE_COAL_ORE
                    || material == Material.DEEPSLATE_REDSTONE_ORE
                    || material == Material.DEEPSLATE_EMERALD_ORE
                    || material == Material.DEEPSLATE_LAPIS_ORE
                    || material == Material.DEEPSLATE_DIAMOND_ORE
                    || material == Material.AMETHYST_BLOCK
                ){
                    //処理部分
                    Random random = new Random();
                    int LuckAmount = random.nextInt(getConfig().getInt("BlockLuckAmount"));
                    int Luck = random.nextInt(getConfig().getInt("BlockLuck"));
                    String itemName = getConfig().getString("BlockDropItemName");
                    if (itemName == null) return;
                    ItemStack itemStack = new ItemStack(Material.ROTTEN_FLESH);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta == null) return;
                    itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',itemName));
                    itemMeta.setCustomModelData(1);
                    itemStack.setItemMeta(itemMeta);
                    if (LuckAmount <= Luck) {
                        player.getWorld().dropItem(block.getLocation(),itemStack);
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2,0);
                        player.spawnParticle(Particle.DRAGON_BREATH,block.getX(),block.getY(),block.getZ(),10);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',itemName + "&fを掘り当てました!"));
                    }
                }
            }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        Entity killer = e.getEntity().getKiller();
        if (killer == null) return;
        String normalWorld = getConfig().getString("normal");
        String netherWorld = getConfig().getString("nether");
        String endWorld = getConfig().getString("end");
        World world = entity.getWorld();
        if (world.getName().equals(normalWorld) || world.getName().equals(netherWorld) || world.getName().equals(endWorld)) {
            if (entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.CREEPER) {
                Random random = new Random();
                int LuckAmount = random.nextInt(getConfig().getInt("MobLuckAmount"));
                int Luck = random.nextInt(getConfig().getInt("MobLuck"));
                String itemName = getConfig().getString("MobDropItemName");
                if (itemName == null) return;
                ItemStack itemStack = new ItemStack(Material.ROTTEN_FLESH);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta == null) return;
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',itemName));
                itemMeta.setCustomModelData(1);
                itemStack.setItemMeta(itemMeta);
                if (LuckAmount <= Luck) {
                    killer.getWorld().dropItem(entity.getLocation(),itemStack);
                    killer.getWorld().playSound(killer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2,0);
                    killer.sendMessage(ChatColor.translateAlternateColorCodes('&',entity.getName() + "&fが" + itemName + "&fを落としたよ!"));
                }
            }
        }
    }
}