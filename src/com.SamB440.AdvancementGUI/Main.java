package com.SamB440.AdvancementGUI;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Logger;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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
				if(p.isOp() && getConfig().getBoolean("Server.Messages.Join_Messages"))
				{
					/*
					 * We use the variables we defined in our createFiles method to show if everything is OK.
					 */
					p.sendMessage(px + ChatColor.BLUE + "This server is running AdvancementGUI " + ChatColor.YELLOW + getDescription().getVersion() + ChatColor.BLUE + " by SamB440");
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
		Bukkit.getServer().getPluginManager().registerEvents(new InventoryManager(), this);
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
			/*p.sendMessage(ChatColor.GREEN + "/AdvancementGUI counterup (advancementname)");
			p.sendMessage(ChatColor.WHITE + "   Aliases: None.");
			p.sendMessage(ChatColor.WHITE + "   Description: Increase a goal advancements count.");
			p.sendMessage(ChatColor.WHITE + "   Permission(s): AdvancementGUI.counterup");
			p.sendMessage(ChatColor.GREEN + "/AdvancementGUI counterdown (advancementname)");
			p.sendMessage(ChatColor.WHITE + "   Aliases: None.");
			p.sendMessage(ChatColor.WHITE + "   Description: Decrease a goal advancements count.");
			p.sendMessage(ChatColor.WHITE + "   Permission(s): AdvancementGUI.counterdown");*/
			p.sendMessage(ChatColor.GREEN + "/AdvancementGUI list");
			p.sendMessage(ChatColor.WHITE + "   Aliases: None.");
			p.sendMessage(ChatColor.WHITE + "   Description: Lists recently created advancements.");
			p.sendMessage(ChatColor.WHITE + "   Permission(s): AdvancementGUI.list");
			p.sendMessage(ChatColor.YELLOW + "© 2017 IslandEarth. Made with" + " ❤ " + "by SamB440.");
		}
		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("edit") && p.hasPermission("AdvancementGUI.edit"))
			{
				p.sendMessage(ChatColor.RED + "This is coming soon!");
			}
			if(args[0].equalsIgnoreCase("list") && p.hasPermission("AdvancementGUI.list"))
			{
				TextComponent help = new TextComponent(ChatColor.YELLOW + "Showing recent list of advancements for AdvancementGUI " + ChatColor.WHITE + "1/1");
				help.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.WHITE + "Showing page 1/1, click to go to the next page.").create()));
				p.spigot().sendMessage(help);
				for(AdvancementAPI list : AdvancementAPI.getAdvancements())
				{
					p.sendMessage(ChatColor.GREEN + list.getTitle());
				}
			}
			if(args[0].equalsIgnoreCase("create") && p.hasPermission("AdvancementGUI.create"))
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
				InventoryManager.i1.setItem(4, normal);
				ItemStack goal = new ItemStack(Material.PAPER);
				ItemMeta goalmeta = normal.getItemMeta();
				goalmeta.setDisplayName(ChatColor.GREEN + "Goal Advancement");
				goalmeta.setLore(Arrays.asList(ChatColor.YELLOW + "Warning: This is W.I.P!", ChatColor.WHITE + "A 'Goal Advancement'.", "Not much here, I don't know...,", "similar to challenges I guess?."));
				goal.setItemMeta(goalmeta);
				InventoryManager.i1.setItem(0, goal);
				ItemStack challenge = new ItemStack(Material.PAPER);
				ItemMeta challengemeta = normal.getItemMeta();
				challengemeta.setDisplayName(ChatColor.GREEN + "Challenge Advancement");
				challengemeta.setLore(Arrays.asList(ChatColor.YELLOW + "Warning: This is W.I.P!", ChatColor.WHITE + "A 'Challenge Advancement'.", "Challenge advancements are used to define challenges,", "such as travelling a certain amount of blocks."));
				challenge.setItemMeta(challengemeta);
				InventoryManager.i1.setItem(8, challenge);
				p.openInventory(InventoryManager.i1);
			}
			else if(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("list") && !p.hasPermission("AdvancementGUI.create") || !p.hasPermission("AdvancementGUI.list"))
			{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Server.Messages.No_Permission")));
			}
			else if(!args[0].equalsIgnoreCase("create") && !args[0].equalsIgnoreCase("list"))
			{
				p.sendMessage(ChatColor.RED + "That sub-command could not be found. Type /agui for help.");
			}
		}
		return true;
	}
}
