package com.SamB440.AdvancementGUI;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class InventoryManager implements Listener {
	/*
	 * Here I create the inventories for use later, null is used so that anyone can use the inventory.
	 */
	static Inventory i1 = Bukkit.createInventory(null, 9, ChatColor.GREEN + "AdvancementGUI " + ChatColor.YELLOW + " Creator");
	static Inventory inormal = Bukkit.createInventory(null, 9, "What do you want to name your Advancement as?");
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent ice)
	{
		final Player p = (Player)ice.getWhoClicked();
		ItemStack clicked = ice.getCurrentItem();
		Inventory inv = ice.getInventory();
		/*
		 * Check if anything is null otherwise you will get NPE's
		 */
		if(inv != null && clicked != null && clicked.getType() != null && clicked.getType() != Material.AIR && inv.equals(i1))
		{
			ItemStack normal = new ItemStack(Material.PAPER);
			ItemMeta normalmeta = normal.getItemMeta();
			normal.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
			normalmeta.setDisplayName(ChatColor.GREEN + "Normal Advancement");
			normalmeta.setLore(Arrays.asList(ChatColor.WHITE + "A 'Normal Advancement'.", "This is not a goal or challenge advancement."));
			normal.setItemMeta(normalmeta);
			if(clicked.equals(normal))
			{
				ItemStack name = new ItemStack(Material.BOOK_AND_QUILL);
				ItemMeta namemeta = name.getItemMeta();
				namemeta.setDisplayName(ChatColor.GREEN + "Name Advancement");
				name.setItemMeta(namemeta);
				inormal.setItem(4, name);
				ItemStack cancel = new ItemStack(Material.BARRIER);
				ItemMeta cancelmeta = cancel.getItemMeta();
				cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
				cancel.setItemMeta(cancelmeta);
				inormal.setItem(0, cancel);
				p.openInventory(inormal);	
			}
			/*
			 * Play a sound and cancel moving the items every time you click.
			 */
			p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.0F);
			ice.setCancelled(true);
		}
		if(inv != null && clicked != null && clicked.getType() != null && clicked.getType() != Material.AIR && inv.equals(inormal))
		{
			/*
			 * Here we set the items for inormal inventory.
			 */
			ItemStack name = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta namemeta = name.getItemMeta();
			namemeta.setDisplayName(ChatColor.GREEN + "Name Advancement");
			name.setItemMeta(namemeta);
			ItemStack cancel = new ItemStack(Material.BARRIER);
			ItemMeta cancelmeta = cancel.getItemMeta();
			cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
			cancel.setItemMeta(cancelmeta);
			if(clicked.equals(cancel))
			{
				p.closeInventory();
				p.sendMessage(ChatColor.RED + "Cancelled Advancement creation.");
			}
			else if(clicked.equals(name))
			{
				/*
				 * This is where AnvilGUI.class comes into play.
				 */
				AnvilGUI gui = new AnvilGUI(p.getPlayer(), new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Enter the name of your Advancement above!");
				item.setItemMeta(itemmeta);
	            gui.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, item);

	            try {
	                gui.open();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (InvocationTargetException e) {
	                e.printStackTrace();
	            } catch (InstantiationException e) {
	                e.printStackTrace();
	            }
			}
			p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.0F);
			ice.setCancelled(true);
		}
	}
}
