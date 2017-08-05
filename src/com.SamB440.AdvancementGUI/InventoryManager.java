package com.SamB440.AdvancementGUI;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
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
	 * Variables that will be defined when creating an advancement
	 */
	String advname;
	String advdescription;
	String advicon;
	String advbackground;
	String advparent;
	String advtrigger;
	int advcounter;
	Boolean withparent = false;
	Boolean withcounter = false;
	Boolean allworlds = false;
	/*
	 * Here I create the inventories for use later, null is used so that anyone can use the inventory.
	 */
	static Inventory i1 = Bukkit.createInventory(null, 9, ChatColor.GREEN + "AdvancementGUI " + ChatColor.YELLOW + " Creator");
	static Inventory inormal = Bukkit.createInventory(null, 9, "What do you want to name your Advancement as?");
	static Inventory igoal = Bukkit.createInventory(null, 9, ChatColor.GREEN + "AdvancementGUI " + ChatColor.YELLOW + " Creator");
	static Inventory ichallenge = Bukkit.createInventory(null, 9, ChatColor.GREEN + "AdvancementGUI " + ChatColor.YELLOW + " Creator");
	static Inventory additional = Bukkit.createInventory(null, 9, "Additional features");
	static Inventory additionalgoal = Bukkit.createInventory(null, 9, "Additional features");
	static Inventory additionalchallenge = Bukkit.createInventory(null, 18, "Additional features");
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent ice)
	{
		final Player p = (Player) ice.getWhoClicked();
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
			ItemStack goal = new ItemStack(Material.PAPER);
			ItemMeta goalmeta = normal.getItemMeta();
			goalmeta.setDisplayName(ChatColor.GREEN + "Goal Advancement");
			goalmeta.setLore(Arrays.asList(ChatColor.YELLOW + "Warning: This is W.I.P!", ChatColor.WHITE + "A 'Goal Advancement'.", "Not much here, I don't know...,", "similar to challenges I guess?."));
			goal.setItemMeta(goalmeta);
			ItemStack challenge = new ItemStack(Material.PAPER);
			ItemMeta challengemeta = normal.getItemMeta();
			challengemeta.setDisplayName(ChatColor.GREEN + "Challenge Advancement");
			challengemeta.setLore(Arrays.asList(ChatColor.YELLOW + "Warning: This is W.I.P!", ChatColor.WHITE + "A 'Challenge Advancement'.", "Challenge advancements are used to define challenges,", "such as travelling a certain amount of blocks."));
			challenge.setItemMeta(challengemeta);
			if(clicked.equals(normal))
			{
				ItemStack name = new ItemStack(Material.BOOK_AND_QUILL);
				ItemMeta namemeta = name.getItemMeta();
				namemeta.setDisplayName(ChatColor.GREEN + "Name Advancement");
				namemeta.setLore(Arrays.asList(ChatColor.RED + "NOTE: Choosing the name of an advancement that", ChatColor.RED + "already exists, will overwrite the old one!", ChatColor.WHITE + "Open the Anvil GUI to name your advancement."));
				name.setItemMeta(namemeta);
				inormal.setItem(4, name);
				ItemStack cancel = new ItemStack(Material.BARRIER);
				ItemMeta cancelmeta = cancel.getItemMeta();
				cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
				cancel.setItemMeta(cancelmeta);
				inormal.setItem(0, cancel);
				p.openInventory(inormal);	
			}
			else if(clicked.equals(goal))
			{
				ItemStack name = new ItemStack(Material.BOOK_AND_QUILL);
				ItemMeta namemeta = name.getItemMeta();
				namemeta.setDisplayName(ChatColor.GREEN + "Name Advancement");
				namemeta.setLore(Arrays.asList(ChatColor.RED + "NOTE: Choosing the name of an advancement that", ChatColor.RED + "already exists will overwrite the old one!", ChatColor.WHITE + "Open the Anvil GUI to name your advancement."));
				name.setItemMeta(namemeta);
				igoal.setItem(4, name);
				ItemStack cancel = new ItemStack(Material.BARRIER);
				ItemMeta cancelmeta = cancel.getItemMeta();
				cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
				cancel.setItemMeta(cancelmeta);
				igoal.setItem(0, cancel);
				p.openInventory(igoal);	
			}
			else if(clicked.equals(challenge))
			{
				ItemStack name = new ItemStack(Material.BOOK_AND_QUILL);
				ItemMeta namemeta = name.getItemMeta();
				namemeta.setDisplayName(ChatColor.GREEN + "Name Advancement");
				namemeta.setLore(Arrays.asList(ChatColor.RED + "NOTE: Choosing the name of an advancement that", ChatColor.RED + "already exists will overwrite the old one!", ChatColor.WHITE + "Open the Anvil GUI to name your advancement."));
				name.setItemMeta(namemeta);
				ichallenge.setItem(4, name);
				ItemStack cancel = new ItemStack(Material.BARRIER);
				ItemMeta cancelmeta = cancel.getItemMeta();
				cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
				cancel.setItemMeta(cancelmeta);
				ichallenge.setItem(0, cancel);
				p.openInventory(ichallenge);	
			}
			/*
			 * Play a sound and cancel moving the items every time you click.
			 */
			p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.0F);
			ice.setCancelled(true);
		}
		if(inv != null && clicked != null && clicked.getType() != null && clicked.getType() != Material.AIR && inv.equals(ichallenge))
		{
			ItemStack name = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta namemeta = name.getItemMeta();
			namemeta.setDisplayName(ChatColor.GREEN + "Name Advancement");
			namemeta.setLore(Arrays.asList(ChatColor.RED + "NOTE: Choosing the name of an advancement that", ChatColor.RED + "already exists will overwrite the old one!", ChatColor.WHITE + "Open the Anvil GUI to name your advancement."));
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
				AnvilGUI gui = new AnvilGUI(p.getPlayer(), new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advname = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        ItemStack icon = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta iconmeta = icon.getItemMeta();
	            			iconmeta.setDisplayName(ChatColor.GREEN + "Set an icon");
	            			iconmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add an icon to your advancement.", "An icon is an item in Minecraft, such is minecraft:book.", "This will display in the advancements list."));
	            			icon.setItemMeta(iconmeta);
	            			additionalchallenge.setItem(4, icon);
	            			ItemStack parent = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta parentmeta = parent.getItemMeta();
	            			parentmeta.setDisplayName(ChatColor.GREEN + "Set a parent");
	            			parentmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a parent to your advancement.", "A parent is a sub-advancement, ", "it branches off an advancement set already."));
	            			parent.setItemMeta(parentmeta);
	            			additionalchallenge.setItem(5, parent);
	            			ItemStack background = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta backgroundmeta = background.getItemMeta();
	            			backgroundmeta.setDisplayName(ChatColor.GREEN + "Set a background");
	            			backgroundmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a background to your advancement.", "A backround image such as stone.png."));
	            			background.setItemMeta(backgroundmeta);
	            			additionalchallenge.setItem(6, background);
	            			ItemStack trigger = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta triggermeta = trigger.getItemMeta();
	            			triggermeta.setDisplayName(ChatColor.GREEN + "Set a trigger");
	            			triggermeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a trigger to your advancement.", "A trigger is how it is granted.", "Setting it to minecraft:impossible will make it so", "it can only be granted via the grant command."));
	            			trigger.setItemMeta(triggermeta);
	            			additionalchallenge.setItem(7, trigger);
	            			ItemStack description = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta descriptionmeta = description.getItemMeta();
	            			descriptionmeta.setDisplayName(ChatColor.GREEN + "Set a description");
	            			descriptionmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a description to your advancement."));
	            			description.setItemMeta(descriptionmeta);
	            			additionalchallenge.setItem(8, description);
	            			ItemStack counter = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta countermeta = counter.getItemMeta();
	            			countermeta.setDisplayName(ChatColor.GREEN + "Set a counter");
	            			countermeta.setLore(Arrays.asList(ChatColor.RED + "NOTE: You MUST input a number!", ChatColor.WHITE + "Add a counter to your advancement."));
	            			counter.setItemMeta(countermeta);
	            			additionalchallenge.setItem(13, counter);
	            			ItemStack cancel = new ItemStack(Material.BARRIER);
	            			ItemMeta cancelmeta = cancel.getItemMeta();
	            			cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
	            			cancel.setItemMeta(cancelmeta);
	            			additionalchallenge.setItem(0, cancel);
	            			ItemStack done = new ItemStack(Material.EMERALD);
	            			ItemMeta donemeta = done.getItemMeta();
	            			donemeta.setDisplayName(ChatColor.GREEN + "Done!");
	            			donemeta.setLore(Arrays.asList(ChatColor.RED + "WARNING: You cannot edit the advancement after it's created!"));
	            			done.setItemMeta(donemeta);
	            			additionalchallenge.setItem(2, done);
	            			ItemStack rename = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta renamemeta = rename.getItemMeta();
	            			renamemeta.setDisplayName(ChatColor.YELLOW + "Rename Advancement");
	            			rename.setItemMeta(renamemeta);
	            			additionalchallenge.setItem(1, rename);
	            	        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	            				@Override
	            				public void run() {
	            					p.openInventory(additionalchallenge);
	            				}
	            	        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("IslandEarth");
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
		if(inv != null && clicked != null && clicked.getType() != null && clicked.getType() != Material.AIR && inv.equals(igoal))
		{
			ItemStack name = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta namemeta = name.getItemMeta();
			namemeta.setDisplayName(ChatColor.GREEN + "Name Advancement");
			namemeta.setLore(Arrays.asList(ChatColor.RED + "NOTE: Choosing the name of an advancement that", ChatColor.RED + "already exists will overwrite the old one!", ChatColor.WHITE + "Open the Anvil GUI to name your advancement."));
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
				AnvilGUI gui = new AnvilGUI(p.getPlayer(), new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advname = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        ItemStack icon = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta iconmeta = icon.getItemMeta();
	            			iconmeta.setDisplayName(ChatColor.GREEN + "Set an icon");
	            			iconmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add an icon to your advancement.", "An icon is an item in Minecraft, such is minecraft:book.", "This will display in the advancements list."));
	            			icon.setItemMeta(iconmeta);
	            			additionalgoal.setItem(4, icon);
	            			ItemStack parent = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta parentmeta = parent.getItemMeta();
	            			parentmeta.setDisplayName(ChatColor.GREEN + "Set a parent");
	            			parentmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a parent to your advancement.", "A parent is a sub-advancement, ", "it branches off an advancement set already."));
	            			parent.setItemMeta(parentmeta);
	            			additionalgoal.setItem(5, parent);
	            			ItemStack background = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta backgroundmeta = background.getItemMeta();
	            			backgroundmeta.setDisplayName(ChatColor.GREEN + "Set a background");
	            			backgroundmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a background to your advancement.", "A backround image such as stone.png."));
	            			background.setItemMeta(backgroundmeta);
	            			additionalgoal.setItem(6, background);
	            			ItemStack trigger = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta triggermeta = trigger.getItemMeta();
	            			triggermeta.setDisplayName(ChatColor.GREEN + "Set a trigger");
	            			triggermeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a trigger to your advancement.", "A trigger is how it is granted.", "Setting it to minecraft:impossible will make it so", "it can only be granted via the grant command."));
	            			trigger.setItemMeta(triggermeta);
	            			additionalgoal.setItem(7, trigger);
	            			ItemStack description = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta descriptionmeta = description.getItemMeta();
	            			descriptionmeta.setDisplayName(ChatColor.GREEN + "Set a description");
	            			descriptionmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a description to your advancement."));
	            			description.setItemMeta(descriptionmeta);
	            			additionalgoal.setItem(8, description);
	            			/*ItemStack counter = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta countermeta = counter.getItemMeta();
	            			countermeta.setDisplayName(ChatColor.GREEN + "Set a counter");
	            			countermeta.setLore(Arrays.asList(ChatColor.RED + "NOTE: You MUST input a number!", ChatColor.WHITE + "Add a counter to your advancement."));
	            			counter.setItemMeta(countermeta);
	            			additionalgoal.setItem(12, counter);*/
	            			ItemStack cancel = new ItemStack(Material.BARRIER);
	            			ItemMeta cancelmeta = cancel.getItemMeta();
	            			cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
	            			cancel.setItemMeta(cancelmeta);
	            			additionalgoal.setItem(0, cancel);
	            			ItemStack done = new ItemStack(Material.EMERALD);
	            			ItemMeta donemeta = done.getItemMeta();
	            			donemeta.setDisplayName(ChatColor.GREEN + "Done!");
	            			donemeta.setLore(Arrays.asList(ChatColor.RED + "WARNING: You cannot edit the advancement after it's created!"));
	            			done.setItemMeta(donemeta);
	            			additionalgoal.setItem(2, done);
	            			ItemStack rename = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta renamemeta = rename.getItemMeta();
	            			renamemeta.setDisplayName(ChatColor.YELLOW + "Rename Advancement");
	            			rename.setItemMeta(renamemeta);
	            			additionalgoal.setItem(1, rename);
	            	        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	            				@Override
	            				public void run() {
	            					p.openInventory(additionalgoal);
	            				}
	            	        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("IslandEarth");
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
		if(inv != null && clicked != null && clicked.getType() != null && clicked.getType() != Material.AIR && inv.equals(inormal))
		{
			ItemStack name = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta namemeta = name.getItemMeta();
			namemeta.setDisplayName(ChatColor.GREEN + "Name Advancement");
			namemeta.setLore(Arrays.asList(ChatColor.RED + "NOTE: Choosing the name of an advancement that", ChatColor.RED + "already exists, will overwrite the old one!", ChatColor.WHITE + "Open the Anvil GUI to name your advancement."));
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
	                        advname = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        ItemStack icon = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta iconmeta = icon.getItemMeta();
	            			iconmeta.setDisplayName(ChatColor.GREEN + "Set an icon");
	            			iconmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add an icon to your advancement.", "An icon is an item in Minecraft, such is minecraft:book.", "This will display in the advancements list."));
	            			icon.setItemMeta(iconmeta);
	            			additional.setItem(4, icon);
	            			ItemStack parent = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta parentmeta = parent.getItemMeta();
	            			parentmeta.setDisplayName(ChatColor.GREEN + "Set a parent");
	            			parentmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a parent to your advancement.", "A parent is a sub-advancement, ", "it branches off an advancement set already."));
	            			parent.setItemMeta(parentmeta);
	            			additional.setItem(5, parent);
	            			ItemStack background = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta backgroundmeta = background.getItemMeta();
	            			backgroundmeta.setDisplayName(ChatColor.GREEN + "Set a background");
	            			backgroundmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a background to your advancement.", "A backround image such as stone.png."));
	            			background.setItemMeta(backgroundmeta);
	            			additional.setItem(6, background);
	            			ItemStack trigger = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta triggermeta = trigger.getItemMeta();
	            			triggermeta.setDisplayName(ChatColor.GREEN + "Set a trigger");
	            			triggermeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a trigger to your advancement.", "A trigger is how it is granted.", "Setting it to minecraft:impossible will make it so", "it can only be granted via the grant command."));
	            			trigger.setItemMeta(triggermeta);
	            			additional.setItem(7, trigger);
	            			ItemStack description = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta descriptionmeta = description.getItemMeta();
	            			descriptionmeta.setDisplayName(ChatColor.GREEN + "Set a description");
	            			descriptionmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a description to your advancement."));
	            			description.setItemMeta(descriptionmeta);
	            			additional.setItem(8, description);
	            			ItemStack cancel = new ItemStack(Material.BARRIER);
	            			ItemMeta cancelmeta = cancel.getItemMeta();
	            			cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
	            			cancel.setItemMeta(cancelmeta);
	            			additional.setItem(0, cancel);
	            			ItemStack done = new ItemStack(Material.EMERALD);
	            			ItemMeta donemeta = done.getItemMeta();
	            			donemeta.setDisplayName(ChatColor.GREEN + "Done!");
	            			donemeta.setLore(Arrays.asList(ChatColor.RED + "WARNING: You cannot edit the advancement after it's created!"));
	            			done.setItemMeta(donemeta);
	            			additional.setItem(2, done);
	            			ItemStack rename = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta renamemeta = rename.getItemMeta();
	            			renamemeta.setDisplayName(ChatColor.YELLOW + "Rename Advancement");
	            			rename.setItemMeta(renamemeta);
	            			additional.setItem(1, rename);
	            	        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	            				@Override
	            				public void run() {
	            					p.openInventory(additional);
	            				}
	            	        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("IslandEarth");
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
		if(inv != null && clicked != null && clicked.getType() != null && clicked.getType() != Material.AIR && inv.equals(additionalchallenge))
		{
			ItemStack icon = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta iconmeta = icon.getItemMeta();
			iconmeta.setDisplayName(ChatColor.GREEN + "Set an icon");
			iconmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add an icon to your advancement.", "An icon is an item in Minecraft, such is minecraft:book.", "This will display in the advancements list."));
			icon.setItemMeta(iconmeta);
			ItemStack parent = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta parentmeta = parent.getItemMeta();
			parentmeta.setDisplayName(ChatColor.GREEN + "Set a parent");
			parentmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a parent to your advancement.", "A parent is a sub-advancement, ", "it branches off an advancement set already."));
			parent.setItemMeta(parentmeta);
			ItemStack background = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta backgroundmeta = background.getItemMeta();
			backgroundmeta.setDisplayName(ChatColor.GREEN + "Set a background");
			backgroundmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a background to your advancement.", "A backround image such as stone.png."));
			background.setItemMeta(backgroundmeta);
			ItemStack trigger = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta triggermeta = trigger.getItemMeta();
			triggermeta.setDisplayName(ChatColor.GREEN + "Set a trigger");
			triggermeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a trigger to your advancement.", "A trigger is how it is granted.", "Setting it to minecraft:impossible will make it so", "it can only be granted via the grant command."));
			trigger.setItemMeta(triggermeta);
			ItemStack description = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta descriptionmeta = description.getItemMeta();
			descriptionmeta.setDisplayName(ChatColor.GREEN + "Set a description");
			descriptionmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a description to your advancement."));
			description.setItemMeta(descriptionmeta);
			ItemStack counter = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta countermeta = counter.getItemMeta();
			countermeta.setDisplayName(ChatColor.GREEN + "Set a counter");
			countermeta.setLore(Arrays.asList(ChatColor.RED + "NOTE: You MUST input a number!", ChatColor.WHITE + "Add a counter to your advancement."));
			counter.setItemMeta(countermeta);
			ItemStack cancel = new ItemStack(Material.BARRIER);
			ItemMeta cancelmeta = cancel.getItemMeta();
			cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
			cancel.setItemMeta(cancelmeta);
			ItemStack done = new ItemStack(Material.EMERALD);
			ItemMeta donemeta = done.getItemMeta();
			donemeta.setDisplayName(ChatColor.GREEN + "Done!");
			donemeta.setLore(Arrays.asList(ChatColor.RED + "WARNING: You cannot edit the advancement after it's created!"));
			done.setItemMeta(donemeta);
			ItemStack rename = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta renamemeta = rename.getItemMeta();
			renamemeta.setDisplayName(ChatColor.YELLOW + "Rename Advancement");
			rename.setItemMeta(renamemeta);
			if(clicked.equals(rename))
			{
				p.closeInventory();
				AnvilGUI gui = new AnvilGUI(p.getPlayer(), new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advname = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	            	        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	            				@Override
	            				public void run() {
	            					p.openInventory(additionalchallenge);
	            				}
	            	        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: IslandEarth");
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
			if(clicked.equals(cancel))
			{
				p.closeInventory();
				p.sendMessage(ChatColor.RED + "Cancelled Advancement creation.");
			}
			if(clicked.equals(icon))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advicon = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalchallenge);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: minecraft:book");
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
			if(clicked.equals(parent))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advparent = event.getName();
	                        withparent = true;
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalchallenge);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("advancementname");
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
			if(clicked.equals(background))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advbackground = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalchallenge);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: stone.png");
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
			if(clicked.equals(trigger))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advtrigger = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalchallenge);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: minecraft:impossible");
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
			if(clicked.equals(counter))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advcounter = Integer.valueOf(event.getName());
	                        withcounter = true;
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalchallenge);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: 10");
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
			if(clicked.equals(done))
			{
				p.closeInventory();
				p.sendMessage(ChatColor.GREEN + "Saving...");
				createChallengeAdvancement(p, advname, advdescription, advicon, advtrigger, advbackground, advparent, withparent, advcounter, withcounter);
			}
			if(clicked.equals(description))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advdescription = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalchallenge);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Enter description");
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
		}
		if(inv != null && clicked != null && clicked.getType() != null && clicked.getType() != Material.AIR && inv.equals(additionalgoal))
		{
            ItemStack icon = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta iconmeta = icon.getItemMeta();
			iconmeta.setDisplayName(ChatColor.GREEN + "Set an icon");
			iconmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add an icon to your advancement.", "An icon is an item in Minecraft, such is minecraft:book.", "This will display in the advancements list."));
			icon.setItemMeta(iconmeta);
			ItemStack parent = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta parentmeta = parent.getItemMeta();
			parentmeta.setDisplayName(ChatColor.GREEN + "Set a parent");
			parentmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a parent to your advancement.", "A parent is a sub-advancement, ", "it branches off an advancement set already."));
			parent.setItemMeta(parentmeta);
			ItemStack background = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta backgroundmeta = background.getItemMeta();
			backgroundmeta.setDisplayName(ChatColor.GREEN + "Set a background");
			backgroundmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a background to your advancement.", "A backround image such as stone.png."));
			background.setItemMeta(backgroundmeta);
			ItemStack trigger = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta triggermeta = trigger.getItemMeta();
			triggermeta.setDisplayName(ChatColor.GREEN + "Set a trigger");
			triggermeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a trigger to your advancement.", "A trigger is how it is granted.", "Setting it to minecraft:impossible will make it so", "it can only be granted via the grant command."));
			trigger.setItemMeta(triggermeta);
			ItemStack description = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta descriptionmeta = description.getItemMeta();
			descriptionmeta.setDisplayName(ChatColor.GREEN + "Set a description");
			descriptionmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a description to your advancement."));
			description.setItemMeta(descriptionmeta);
			/*ItemStack counter = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta countermeta = counter.getItemMeta();
			countermeta.setDisplayName(ChatColor.GREEN + "Set a counter");
			countermeta.setLore(Arrays.asList(ChatColor.RED + "NOTE: You MUST input a number!", ChatColor.WHITE + "Add a counter to your advancement."));
			counter.setItemMeta(countermeta);*/
			ItemStack cancel = new ItemStack(Material.BARRIER);
			ItemMeta cancelmeta = cancel.getItemMeta();
			cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
			cancel.setItemMeta(cancelmeta);
			ItemStack done = new ItemStack(Material.EMERALD);
			ItemMeta donemeta = done.getItemMeta();
			donemeta.setDisplayName(ChatColor.GREEN + "Done!");
			donemeta.setLore(Arrays.asList(ChatColor.RED + "WARNING: You cannot edit the advancement after it's created!"));
			done.setItemMeta(donemeta);
			ItemStack rename = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta renamemeta = rename.getItemMeta();
			renamemeta.setDisplayName(ChatColor.YELLOW + "Rename Advancement");
			rename.setItemMeta(renamemeta);
			if(clicked.equals(rename))
			{
				p.closeInventory();
				AnvilGUI gui = new AnvilGUI(p.getPlayer(), new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advname = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	            	        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	            				@Override
	            				public void run() {
	            					p.openInventory(additionalgoal);
	            				}
	            	        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: IslandEarth");
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
			if(clicked.equals(cancel))
			{
				p.closeInventory();
				p.sendMessage(ChatColor.RED + "Cancelled Advancement creation.");
			}
			if(clicked.equals(icon))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advicon = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalgoal);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: minecraft:book");
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
			if(clicked.equals(parent))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advparent = event.getName();
	                        withparent = true;
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalgoal);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("advancementname");
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
			if(clicked.equals(background))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advbackground = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalgoal);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: stone.png");
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
			if(clicked.equals(trigger))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advtrigger = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalgoal);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: minecraft:impossible");
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
			/*if(clicked.equals(counter))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advcounter = Integer.valueOf(event.getName());
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalgoal);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: 10");
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
			}*/
			if(clicked.equals(done))
			{
				p.closeInventory();
				p.sendMessage(ChatColor.GREEN + "Saving...");
				createGoalAdvancement(p, advname, advdescription, advicon, advtrigger, advbackground, advparent, withparent);
			}
			if(clicked.equals(description))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advdescription = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additionalgoal);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Enter description");
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
		}
		if(inv != null && clicked != null && clicked.getType() != null && clicked.getType() != Material.AIR && inv.equals(additional))
		{
			ItemStack icon = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta iconmeta = icon.getItemMeta();
			iconmeta.setDisplayName(ChatColor.GREEN + "Set an icon");
			iconmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add an icon to your advancement.", "An icon is an item in Minecraft, such is minecraft:book.", "This will display in the advancements list."));
			icon.setItemMeta(iconmeta);
			ItemStack parent = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta parentmeta = parent.getItemMeta();
			parentmeta.setDisplayName(ChatColor.GREEN + "Set a parent");
			parentmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a parent to your advancement.", "A parent is a sub-advancement, ", "it branches off an advancement set already."));
			parent.setItemMeta(parentmeta);
			ItemStack background = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta backgroundmeta = background.getItemMeta();
			backgroundmeta.setDisplayName(ChatColor.GREEN + "Set a background");
			backgroundmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a background to your advancement.", "A backround image such as stone.png."));
			background.setItemMeta(backgroundmeta);
			ItemStack trigger = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta triggermeta = trigger.getItemMeta();
			triggermeta.setDisplayName(ChatColor.GREEN + "Set a trigger");
			triggermeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a trigger to your advancement.", "A trigger is how it is granted.", "Setting it to minecraft:impossible will make it so", "it can only be granted via the grant command."));
			trigger.setItemMeta(triggermeta);
			ItemStack description = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta descriptionmeta = description.getItemMeta();
			descriptionmeta.setDisplayName(ChatColor.GREEN + "Set a description");
			descriptionmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a description to your advancement."));
			description.setItemMeta(descriptionmeta);
			ItemStack cancel = new ItemStack(Material.BARRIER);
			ItemMeta cancelmeta = cancel.getItemMeta();
			cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
			cancel.setItemMeta(cancelmeta);
			ItemStack done = new ItemStack(Material.EMERALD);
			ItemMeta donemeta = done.getItemMeta();
			donemeta.setDisplayName(ChatColor.GREEN + "Done!");
			donemeta.setLore(Arrays.asList(ChatColor.RED + "WARNING: You cannot edit the advancement after it's created!"));
			done.setItemMeta(donemeta);
			ItemStack rename = new ItemStack(Material.BOOK_AND_QUILL);
			ItemMeta renamemeta = rename.getItemMeta();
			renamemeta.setDisplayName(ChatColor.YELLOW + "Rename Advancement");
			rename.setItemMeta(renamemeta);
			if(clicked.equals(rename))
			{
				p.closeInventory();
				AnvilGUI gui = new AnvilGUI(p.getPlayer(), new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advname = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        ItemStack icon = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta iconmeta = icon.getItemMeta();
	            			iconmeta.setDisplayName(ChatColor.GREEN + "Set an icon");
	            			iconmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add an icon to your advancement.", "An icon is an item in Minecraft, such is minecraft:book.", "This will display in the advancements list."));
	            			icon.setItemMeta(iconmeta);
	            			additional.setItem(4, icon);
	            			ItemStack parent = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta parentmeta = parent.getItemMeta();
	            			parentmeta.setDisplayName(ChatColor.GREEN + "Set a parent");
	            			parentmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a parent to your advancement.", "A parent is a sub-advancement, ", "it branches off an advancement set already."));
	            			parent.setItemMeta(parentmeta);
	            			additional.setItem(5, parent);
	            			ItemStack background = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta backgroundmeta = background.getItemMeta();
	            			backgroundmeta.setDisplayName(ChatColor.GREEN + "Set a background");
	            			backgroundmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a background to your advancement.", "A backround image such as stone.png."));
	            			background.setItemMeta(backgroundmeta);
	            			additional.setItem(6, background);
	            			ItemStack trigger = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta triggermeta = trigger.getItemMeta();
	            			triggermeta.setDisplayName(ChatColor.GREEN + "Set a trigger");
	            			triggermeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a trigger to your advancement.", "A trigger is how it is granted.", "Setting it to minecraft:impossible will make it so", "it can only be granted via the grant command."));
	            			trigger.setItemMeta(triggermeta);
	            			additional.setItem(7, trigger);
	            			ItemStack description = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta descriptionmeta = description.getItemMeta();
	            			descriptionmeta.setDisplayName(ChatColor.GREEN + "Set a description");
	            			descriptionmeta.setLore(Arrays.asList(ChatColor.WHITE + "Add a description to your advancement."));
	            			description.setItemMeta(descriptionmeta);
	            			additional.setItem(8, description);
	            			ItemStack cancel = new ItemStack(Material.BARRIER);
	            			ItemMeta cancelmeta = cancel.getItemMeta();
	            			cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
	            			cancel.setItemMeta(cancelmeta);
	            			additional.setItem(0, cancel);
	            			ItemStack done = new ItemStack(Material.EMERALD);
	            			ItemMeta donemeta = done.getItemMeta();
	            			donemeta.setDisplayName(ChatColor.GREEN + "Done!");
	            			donemeta.setLore(Arrays.asList(ChatColor.RED + "WARNING: You cannot edit the advancement after it's created!"));
	            			done.setItemMeta(donemeta);
	            			additional.setItem(2, done);
	            			ItemStack rename = new ItemStack(Material.BOOK_AND_QUILL);
	            			ItemMeta renamemeta = rename.getItemMeta();
	            			renamemeta.setDisplayName(ChatColor.YELLOW + "Rename Advancement");
	            			rename.setItemMeta(renamemeta);
	            			additional.setItem(1, rename);
	            	        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	            				@Override
	            				public void run() {
	            					p.openInventory(additional);
	            				}
	            	        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: IslandEarth");
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
			if(clicked.equals(cancel))
			{
				p.closeInventory();
				p.sendMessage(ChatColor.RED + "Cancelled Advancement creation.");
			}
			if(clicked.equals(icon))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advicon = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additional);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: minecraft:book");
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
			if(clicked.equals(parent))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advparent = event.getName();
	                        withparent = true;
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additional);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("advancementname");
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
			if(clicked.equals(background))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advbackground = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additional);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: stone.png");
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
			if(clicked.equals(trigger))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advtrigger = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additional);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Example: minecraft:impossible");
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
			if(clicked.equals(done))
			{
				p.closeInventory();
				p.sendMessage(ChatColor.GREEN + "Saving...");
				createAdvancement(p, advname, advdescription, advicon, advtrigger, advbackground, advparent, withparent);
			}
			if(clicked.equals(description))
			{
				AnvilGUI gui = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
	                @Override
	                public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
	                    if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
	                        event.setWillClose(true);
	                        event.setWillDestroy(true);
	                        advdescription = event.getName();
	                        p.sendMessage(event.getName()); //event.getName() == string in anvil
	                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
	                			@Override
	                			public void run() {
	                				p.openInventory(additional);
	                			}
	                        }, 5L);
	                    } else {
	                        event.setWillClose(false);
	                        event.setWillDestroy(false);
	                    }
	                }
	            });
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta itemmeta = item.getItemMeta();
				itemmeta.setDisplayName("Enter description");
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
		}
	}
	@SuppressWarnings("deprecation")
	public void createAdvancement(Player p, String title, String description, String icon, String trigger, String background, String advancementparent, Boolean parent)
	{
		if(description == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide a description!");
			p.openInventory(additional);
		}
		else if(icon == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide an icon!");
			p.openInventory(additional);
		}
		else if(trigger == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide a trigger!");
			p.openInventory(additional);
		}
		else if(background == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide a background!");
			p.openInventory(additional);
		}
		else
		{
			AdvancementAPI api = AdvancementAPI.build(Main.instance, title.replaceAll(" ", "_"))
			    .title(title)
			    .description(description)
			    .icon(icon)
			    .trigger(trigger)
			    .frame(AdvancementAPI.FrameType.TASK)
			    .background("minecraft:textures/gui/advancements/backgrounds/" + background);
			if(parent)
			{
				for(World w : Bukkit.getWorlds())
				{
					
					if(Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
					{
						api.parent("advancementgui:" + advancementparent)
						.save(w);
					}
					else if(!Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
					{	
						api.parent("advancementgui:" + advancementparent)
						.save(p.getWorld());
					}
				}
			}
			else if(!parent)
			{
				for(World w : Bukkit.getWorlds())
				{
					if(Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
					{
						api.save(w);
					}
					else if(!Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
					{
						api.save(p.getWorld());
					}
					Bukkit.getServer().reloadData();
					api.grant(p);
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	public void createGoalAdvancement(Player p, String title, String description, String icon, String trigger, String background, String advancementparent, Boolean parent)
	{
		if(description == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide a description!");
			p.openInventory(additionalgoal);
		}
		else if(icon == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide an icon!");
			p.openInventory(additionalgoal);
		}
		else if(trigger == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide a trigger!");
			p.openInventory(additionalgoal);
		}
		else if(background == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide a background!");
			p.openInventory(additionalgoal);
		}
		else
		{
			AdvancementAPI api = AdvancementAPI.build(Main.instance, title.replaceAll(" ", "_"))
			    .title(title)
			    .description(description)
			    .icon(icon)
			    .trigger(trigger)
			    .frame(AdvancementAPI.FrameType.GOAL)
			    .background("minecraft:textures/gui/advancements/backgrounds/" + background);
			if(parent)
			{
				for(World w : Bukkit.getWorlds())
				{
					if(Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
					{
						api.parent("advancementgui:" + advancementparent)
						.save(w);
					}
					else if(!Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
					{
						api.parent("advancementgui:" + advancementparent)
						.save(p.getWorld());
					}
					Bukkit.getServer().reloadData();
				}
			}
			else if(!parent)
			{
				for(World w : Bukkit.getWorlds())
				{
					if(Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
					{
						api.save(w);
					}
					else if(!Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
					{
						api.save(p.getWorld());
					}
					p.sendMessage(ChatColor.GREEN + "Reloading advancements...");
					Bukkit.getServer().reloadData();
					p.sendMessage(ChatColor.GREEN + "Done!");
					api.grant(p);
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	public void createChallengeAdvancement(Player p, String title, String description, String icon, String trigger, String background, String advancementparent, Boolean parent, int counter, Boolean wcounter)
	{
		if(description == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide a description!");
			p.openInventory(additionalchallenge);
		}
		else if(icon == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide an icon!");
			p.openInventory(additionalchallenge);
		}
		else if(trigger == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide a trigger!");
			p.openInventory(additionalchallenge);
		}
		else if(background == null)
		{
			p.sendMessage(ChatColor.RED + "You need to provide a background!");
			p.openInventory(additionalchallenge);
		}
		else
		{
			AdvancementAPI api = AdvancementAPI.build(Main.instance, title.replaceAll(" ", "_"))
			    .title(title)
			    .description(description)
			    .icon(icon)
			    .trigger(trigger)
			    .frame(AdvancementAPI.FrameType.GOAL)
			    .background("minecraft:textures/gui/advancements/backgrounds/" + background);
			if(parent)
			{
				for(World w : Bukkit.getWorlds())
				{
					api.parent("advancementgui:" + advancementparent);
					if(wcounter)
					{
						if(Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
						{
							api.counter(counter)
							.save(w);
						}
						else if(!Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
						{
							api.counter(counter)
							.save(p.getWorld());
						}
					}
					else if(!wcounter)
					{
						if(Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
						{
							api.counter(counter)
							.save(w);
						}
						else if(!Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
						{
							api.counter(counter)
							.save(p.getWorld());
						}
					}
					p.sendMessage(ChatColor.GREEN + "Reloading advancements...");
					Bukkit.getServer().reloadData();
					p.sendMessage(ChatColor.GREEN + "Done!");
					api.grant(p);
				}
			}
			else if(!parent)
			{
				for(World w : Bukkit.getWorlds())
				{
					if(wcounter)
					{
						if(Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
						{
							api.counter(counter)
							.save(w);
						}
						else if(!Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
						{
							api.counter(counter)
							.save(p.getWorld());
						}
					}
					else if(!wcounter)
					{
						if(Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
						{
							api
							.save(w);
						}
						else if(!Main.instance.getConfig().getBoolean("Server.Worlds.Add_To_All_Worlds"))
						{
							api
							.save(p.getWorld());
						}
					}
					p.sendMessage(ChatColor.GREEN + "Reloading advancements...");
					Bukkit.getServer().reloadData();
					p.sendMessage(ChatColor.GREEN + "Done!");
				}
			}
		}
	}
	public static void counterUp(String advancementname, Player p)
	{
		AdvancementAPI api = AdvancementAPI.build(Main.instance, advancementname);
		api.counterUp(p);
	}
	public static void counterDown(String advancementname, Player p)
	{
		AdvancementAPI api = AdvancementAPI.build(Main.instance, advancementname);
		api.counterDown(p);
	}
}
