package com.SamB440.AdvancementGUI;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.logging.Logger;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	/*
	 * Code is fully commented to help anyone reading it!
	 *---------------------------------------------------
	 * Here we define our variables
	 */
	public String px = ChatColor.BLUE + "[" + ChatColor.GREEN + "AdvancementGUI" + ChatColor.BLUE + "] ";
	public Boolean files = false;
	public Boolean config = false;
	public Boolean version = true;
	/*
	 * We use this later on to define the plugin, which is needed in AnvilGUI.java
	 */
	public static Main instance;
	/*
	 * Here I create the inventories for use later, null is used so that anyone can use the inventory.
	 */
	Inventory i1 = Bukkit.createInventory(null, 9, ChatColor.GREEN + "AdvancementGUI " + ChatColor.YELLOW + " Creator");
	Inventory inormal = Bukkit.createInventory(null, 9, "What do you want to name your Advancement as?");
	/*
	 * This is just easier to log to console
	 */
	private static final Logger log = Logger.getLogger("Minecraft");
	
	/*
	 * Here I define our onEnable and use the createFiles and registerEvents void.
	 */
	public void onEnable()
	{
		log.info(px + "Starting...");
		/*
		 * Here I define the instance, which is this class.
		 */
		instance = this;
		createFiles();
		registerEvents();
		log.info(px + "Done!");
	}
	/*
	 * Here I define our onDisable, and use #disablePlugin() to prevent any issues.
	 */
	public void onDisable()
	{
		log.info(px + "Disabling...");
		Bukkit.getServer().getPluginManager().disablePlugin(this);
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent pje)
	{

		final Player p = pje.getPlayer();
		/*
		 * Here I define a scheduler to send messages after the player joins (Avoids some issues & makes our message stand out!)
		 */
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				p.sendMessage(px + ChatColor.BLUE + "This server is running AdvancementGUI " + ChatColor.YELLOW + getDescription().getVersion() + ChatColor.BLUE + " by SamB440");
				if(p.isOp() && getConfig().getBoolean("Server.Messages.Join_Messages"))
				{
					/*
					 * We use the variables we defined in our createFiles method to show if everything is OK.
					 */
					p.sendMessage(px + ChatColor.WHITE + "Latest version? " + version);
					p.sendMessage(px + ChatColor.WHITE + "Files? " + files);
					p.sendMessage(px + ChatColor.WHITE + "Config? " + config);
					p.sendMessage(px + ChatColor.BLUE + "AdvancementGUI has been installed successfully.");
					p.sendMessage(px + ChatColor.RED + "Made with " + "❤");
				}
			}
		}, 5L);
	}
	/*
	 * Here I register our events
	 */
	public void registerEvents()
	{
		log.info(px + "Registering events (0%)");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		log.info(px + "Done! (100%)");
		
	}
	/*
	 * Here are the file check & creation methods
	 */
	public void createFiles()
	{
		File dir = new File("plugins/AdvancementGUI");
		File conf = new File("plugins/AdvancementGUI/config.yml");
		log.info(px + "Importing files (0%)");
		/*
		 * Check if the dir exists
		 */
		if (!dir.exists()) {
			/*
			 * It doesn't, so let's make it.
			 */
			dir.mkdir();
			log.info(px + "Importing files: plugins/AdvancementGUI (50%)");
		}
		else if(dir.exists())
		{
			/*
			 * It does, so set the boolean files to true.
			 */
			files = true;
		}
		/*
		 * Check if config exists
		 */
		if(!conf.exists())
		{
			/*
			 * It doesn't, so let's make it.
			 * We get the plugins data folder and name the file config.yml 
			 */
			new File(getDataFolder(), "config.yml");
			log.info(px + "Importing files plugins/AdvancementGUI/config.yml (75%)");
			/*
			 * Get config defaults and save it.
			 */
			saveDefaultConfig();
			log.info(px + "Config file created!");
			config = true;
		}
		else if(conf.exists())
		{
			/*
			 * It does, so set the boolean config to true.
			 */
			config = true;
		}
	    log.info(px + "Done! (100%)"); 
	}
	/*
	 * Here we do all the command stuff!
	 */
	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player p = (Player)sender;
		if(args.length == 0)
		{
			/*
			 * TextComponent is used to show text on hover
			 */
			TextComponent help = new TextComponent(ChatColor.YELLOW + "Showing help for AdvancementGUI " + ChatColor.WHITE + "1/1");
			help.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.WHITE + "Showing page 1/1, click to go to the next page.").create()));
			p.spigot().sendMessage(help);
			p.sendMessage(ChatColor.GREEN + "/AdvancementGUI");
			p.sendMessage(ChatColor.WHITE + "   Aliases: /agui, /ag, /advgui.");
			p.sendMessage(ChatColor.WHITE + "   Description: Displays help page.");
			p.sendMessage(ChatColor.GREEN + "/AdvancementGUI create");
			p.sendMessage(ChatColor.WHITE + "   Aliases: None.");
			p.sendMessage(ChatColor.WHITE + "   Description: Create a new advancement.");
			p.sendMessage(ChatColor.WHITE + "   Permission(s): AdvancementGUI.create");
			p.sendMessage(ChatColor.YELLOW + "© 2017 IslandEarth.");
		}
		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("create") && p.hasPermission("AdvancementGUI.create") || p.isOp())
			{
				/*
				 * Here we set the items in the i1 inventory we defined earlier. Then we open it.
				 */
				ItemStack normal = new ItemStack(Material.PAPER);
				ItemMeta normalmeta = normal.getItemMeta();
				normal.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
				normalmeta.setDisplayName(ChatColor.GREEN + "Normal Advancement");
				normalmeta.setLore(Arrays.asList(ChatColor.WHITE + "A 'Normal Advancement'.", "This is not a goal or challenge advancement."));
				normal.setItemMeta(normalmeta);
				i1.setItem(4, normal);
				ItemStack goal = new ItemStack(Material.PAPER);
				ItemMeta goalmeta = normal.getItemMeta();
				goalmeta.setDisplayName(ChatColor.YELLOW + "Goal Advancement");
				goalmeta.setLore(Arrays.asList(ChatColor.RED + "Coming soon! (v1.0.1)", ChatColor.WHITE + "A 'Goal Advancement'.", "Goal advancements are used to define goals,", "such as travelling a certain amount of blocks."));
				goal.setItemMeta(goalmeta);
				i1.setItem(0, goal);
				ItemStack challenge = new ItemStack(Material.PAPER);
				ItemMeta challengemeta = normal.getItemMeta();
				challengemeta.setDisplayName(ChatColor.YELLOW + "Challenge Advancement");
				challengemeta.setLore(Arrays.asList(ChatColor.RED + "Coming soon! (v1.0.1)", ChatColor.WHITE + "A 'Challenge Advancement'.", "Challenge advancements are.. well... a challenge?", "I didn't know what to put here. Similar to goals I guess."));
				challenge.setItemMeta(challengemeta);
				i1.setItem(8, challenge);
				p.openInventory(i1);
			}
		}
		return true;
	}
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
