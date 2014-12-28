package me.Arabianfellow.AdminStick;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public void onEnable() {
		try {
			Metrics metrics = new Metrics(this); metrics.start();
			} catch (IOException e) { // Failed to submit the stats :-(
			System.out.println("Error Submitting stats!");
			}
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
		getServer().getPluginManager().registerEvents(this, this);
		if(!new File(getDataFolder(), "config.yml").exists()){
			saveConfig();
		}
	}
	 List<String> online = getConfig().getStringList("online");
  
		public void openGUI(Player player) {
    	 Inventory atp = Bukkit.createInventory(null, 45, ChatColor.RED + "Admin Information");
    	 ItemStack tr = new ItemStack(Material.ENCHANTMENT_TABLE);
    	 ItemStack lb = new ItemStack(Material.LAVA_BUCKET);
    	 ItemStack bq = new ItemStack(Material.BOOK_AND_QUILL);
    	 ItemStack wb = new ItemStack(Material.WATER_BUCKET);
    	 ItemStack b = new ItemStack(Material.BEACON);
    	 ItemStack s = new ItemStack(Material.SIGN);
    	 ItemStack cse = new ItemStack(Material.BOOKSHELF);
    	 ItemStack em = new ItemStack(Material.EMERALD);
    	 ItemStack rL = new ItemStack(Material.REDSTONE_LAMP_OFF);
    	 ItemStack db = new ItemStack(Material.DEAD_BUSH);
    	 ItemMeta dbMeta = db.getItemMeta();
    	 ItemMeta rlMeta = rL.getItemMeta();
    	 ItemMeta emMeta = em.getItemMeta();
    	 ItemMeta cseMeta = cse.getItemMeta();
    	 ItemMeta sMeta = s.getItemMeta();
    	 ItemMeta bMeta = b.getItemMeta();
    	 ItemMeta wbMeta = wb.getItemMeta();
    	 ItemMeta bqMeta = bq.getItemMeta();
    	 ItemMeta trMeta = tr.getItemMeta();
    	 ItemMeta lbMeta = lb.getItemMeta();
    	 dbMeta.setDisplayName(ChatColor.GOLD + "View Bukkit/Spigot Version");
    	 rlMeta.setDisplayName(ChatColor.RED + "Stop the Server!");
    	 emMeta.setDisplayName(ChatColor.GOLD + "View MOTD!");
    	 cseMeta.setDisplayName(ChatColor.GOLD + "View Online Player Count");
    	 sMeta.setDisplayName(ChatColor.GOLD + "View Whitelist");
    	 bMeta.setDisplayName(ChatColor.GOLD + "Operator List");
    	 wbMeta.setDisplayName(ChatColor.GOLD + "Reload the Server!");
    	 bqMeta.setDisplayName(ChatColor.GOLD + "Your UUID");
    	 trMeta.setDisplayName(ChatColor.GOLD + "TPS");
    	 lbMeta.setDisplayName(ChatColor.GOLD + "Server Lag Percent");
    	 ArrayList<String> lore = new ArrayList<String>();
    	 ArrayList<String> stop = new ArrayList<String>();
 		lore.add(ChatColor.RED + "Click to view details!");
 		stop.add(ChatColor.DARK_RED + "Warning:");
 		stop.add(ChatColor.RED + "This WILL stop your server");
 		lbMeta.setLore(lore);
 		dbMeta.setLore(lore);
 		rlMeta.setLore(stop);
 		emMeta.setLore(lore);
 		cseMeta.setLore(lore);
 		sMeta.setLore(lore);
 		 bMeta.setLore(lore);
 		 bqMeta.setLore(lore);
 		 trMeta.setLore(lore);
    	 cseMeta.setLore(lore);
    	 db.setItemMeta(dbMeta);
    	 b.setItemMeta(bMeta);
    	 em.setItemMeta(emMeta);
    	 s.setItemMeta(sMeta);
    	 cse.setItemMeta(cseMeta);
    	 wb.setItemMeta(wbMeta);
    	 tr.setItemMeta(trMeta);
    	 lb.setItemMeta(lbMeta);
    	 bq.setItemMeta(bqMeta);
    	 rL.setItemMeta(rlMeta);
    	 atp.setItem(42, db);
    	 atp.setItem(35, em);
    	 atp.setItem(27, cse);
    	 atp.setItem(26, s);
    	 atp.setItem(18, b);
    	 atp.setItem(17, wb);
    	 atp.setItem(9, bq);
    	 atp.setItem(2, tr);
    	 atp.setItem(6, lb);
    	 atp.setItem(38, rL);
         player.openInventory(atp);
		}
		@EventHandler
		public void onInvClick(InventoryClickEvent event){
			 if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Admin Information"))
			 return;
			 Player player = (Player) event.getWhoClicked();
			 event.setCancelled(true);

			 if(event.getCurrentItem() ==null 
			 || event.getCurrentItem().getType()==Material.AIR 
			 ||!event.getCurrentItem().hasItemMeta()){
			 player.closeInventory();
			 return;
			 }
			 switch(event.getCurrentItem().getType()){
			 case ENCHANTMENT_TABLE:
			 player.sendMessage(ChatColor.GOLD + "Server Exact TPS: " + ChatColor.WHITE + Lag.getTPS());
			 default:
			 player.closeInventory();
			 break;
			 }
			 switch(event.getCurrentItem().getType()){
			 case LAVA_BUCKET:
		    	 double tps = Lag.getTPS();
		    	 double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
				 player.sendMessage(ChatColor.GOLD + "Server Lag Percent: " + ChatColor.WHITE + lag);
			 default:
			 player.closeInventory();
			 break;
			 }
			 switch(event.getCurrentItem().getType()){
			 case BOOK_AND_QUILL:
		    	 player.sendMessage(ChatColor.GOLD + "Your UUID: " + ChatColor.WHITE + player.getUniqueId());
			 default:
			 player.closeInventory();
			 break;
			 }
			 switch(event.getCurrentItem().getType()){
			 case WATER_BUCKET:
		    	 player.sendMessage(ChatColor.GREEN + "Reloading the Server");
		    	 Bukkit.getServer().reload();
			 default:
			 player.closeInventory();
			 break;
			 }
			 switch(event.getCurrentItem().getType()){
			 case BEACON:
		    	 player.sendMessage(ChatColor.GOLD  + "Server Operators: " + ChatColor.WHITE + Bukkit.getOperators());
			 default:
			 player.closeInventory();
			 break;
			 }
			 switch(event.getCurrentItem().getType()){
			 case SIGN:
		    player.sendMessage(ChatColor.GOLD + "Whitelisted Players: ");
		    player.sendMessage(Bukkit.getWhitelistedPlayers() + "");
			 default:
			 player.closeInventory();
			 break;
			 }
			 switch(event.getCurrentItem().getType()){
			 case BOOKSHELF:
		     player.sendMessage(ChatColor.GOLD + "Online Player Count:");
		     player.sendMessage(Bukkit.getOnlinePlayers().length + "");
			 default:
			 player.closeInventory();
			 break;
			 }
		 switch(event.getCurrentItem().getType()){
		 case EMERALD:
	     player.sendMessage(ChatColor.GOLD + "Current MOTD: ");
	     player.sendMessage(Bukkit.getServer().getMotd() + "");
		 default:
		 player.closeInventory();
		 break;
		 }
		 switch(event.getCurrentItem().getType()){
		 case REDSTONE_LAMP_OFF:
	     player.sendMessage(ChatColor.DARK_RED + "Stopping the server!");
	     Bukkit.getServer().savePlayers();
	     Bukkit.getServer().shutdown();
		 default:
		 player.closeInventory();
		 break;
		 }
		 switch(event.getCurrentItem().getType()){
		 case DEAD_BUSH:
	     player.sendMessage(ChatColor.GOLD + "Bukkit Version:");
	     player.sendMessage(Bukkit.getServer().getBukkitVersion());
		 default:
		 player.closeInventory();
		 break;
		 }
		}
    	 @EventHandler
	  public void onRightClick(PlayerInteractEvent event) {
    	 double tps = Lag.getTPS();
    	 double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
    	 Player player = event.getPlayer();
    	 if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
    		 if(player.getItemInHand().getItemMeta().getDisplayName().equals("Admin Stick")){
    	 player.sendMessage(ChatColor.GOLD + "Server Lag Percent: " + ChatColor.WHITE + lag);
    	 player.sendMessage(ChatColor.GOLD + "Server Exact TPS: " + ChatColor.WHITE + Lag.getTPS());
    	 player.sendMessage(ChatColor.GOLD + "Current World: "+ ChatColor.WHITE + player.getWorld());
    	 player.sendMessage(ChatColor.GOLD + "Your UUID: " + ChatColor.WHITE + player.getUniqueId());
    	 player.sendMessage(ChatColor.GOLD + "Entities in your chunk: " + ChatColor.WHITE + player.getWorld().getChunkAt(player.getLocation()).getEntities().length);
    	 if(player.isFlying()) { 
         player.sendMessage(ChatColor.GOLD + "Current Fly Speed: " + ChatColor.WHITE + player.getFlySpeed());
    	 } else{
    	 player.sendMessage(ChatColor.GOLD + "Current Walk Speed: " + ChatColor.WHITE + player.getWalkSpeed());
    	 } 
    		 }
    	 }
     }
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("astick") || cmd.getName().equalsIgnoreCase("adminstick")){
			Player player = (Player) sender;
			if(player.hasPermission("adminstick.stick")){
		ItemStack stick = new ItemStack(Material.STICK, 1);
		ItemMeta meta = stick.getItemMeta();
		meta.setDisplayName("Admin Stick");
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Use this stick to view server lag details!");
		lore.add("Right click to use!");
		meta.setLore(lore);
		meta.addEnchant(Enchantment.DURABILITY, 5, true);
		stick.setItemMeta(meta);
		player.getInventory().addItem(stick);
		player.sendMessage(ChatColor.GREEN + "AdminStick added to your inventory");
			}else{
				player.sendMessage("No permission");
			}
        }else if(cmd.getName().equalsIgnoreCase("admingui")){
        	Player player = (Player) sender;
        	if(player.hasPermission("adminstick.gui")){
           openGUI(player);
        	}else{
        		player.sendMessage(ChatColor.RED + "No Permission!");
        	}
	    }
		return false;
		
	}
}

