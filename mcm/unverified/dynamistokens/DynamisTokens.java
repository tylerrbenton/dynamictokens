/*    */ package mcm.unverified.dynamistokens;
/*    */ 
/*    */ import java.util.logging.Logger;
/*    */ import mcm.unverified.commands.Commands;
/*    */ import mcm.unverified.events.BlockBreak;
/*    */ import mcm.unverified.events.EntityDeath;
/*    */ import mcm.unverified.events.InventoryClick;
/*    */ import mcm.unverified.events.PlayerJoin;
/*    */ import mcm.unverified.misc.MySQL;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginDescriptionFile;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ public class DynamisTokens
/*    */   extends JavaPlugin
/*    */ {
/*    */   private static DynamisTokens dynamisTokens;
/* 22 */   private Logger logger = getLogger();
/* 23 */   private PluginDescriptionFile pdf = getDescription();
/* 24 */   private PluginManager pm = getServer().getPluginManager();
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 28 */     dynamisTokens = this;
/*    */     
/* 30 */     getConfig().options().copyDefaults(true);
/* 31 */     saveDefaultConfig();
/*    */     
/* 33 */     getCommand("TShop").setExecutor((CommandExecutor)new Commands());
/* 34 */     getCommand("TGive").setExecutor((CommandExecutor)new Commands());
/* 35 */     getCommand("TTake").setExecutor((CommandExecutor)new Commands());
/* 36 */     getCommand("TPay").setExecutor((CommandExecutor)new Commands());
/* 37 */     getCommand("TBal").setExecutor((CommandExecutor)new Commands());
/*    */     
/* 39 */     this.pm.registerEvents((Listener)new EntityDeath(), (Plugin)this);
/* 40 */     this.pm.registerEvents((Listener)new InventoryClick(), (Plugin)this);
/* 41 */     this.pm.registerEvents((Listener)new PlayerJoin(), (Plugin)this);
/* 42 */     this.pm.registerEvents((Listener)new BlockBreak(), (Plugin)this);
/*    */     
/* 44 */     this.logger.info(String.format("%s v.%s was successfully started.", new Object[] { this.pdf.getName(), this.pdf.getVersion() }));
/*    */     
/* 46 */     Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
/* 47 */     new MySQL(getConfig().getString("config.mysql.host"), getConfig().getInt("config.mysql.port"), getConfig().getString("config.mysql.database"), 
/* 48 */         getConfig().getString("config.mysql.username"), getConfig().getString("config.mysql.password"));
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 52 */     saveDefaultConfig();
/*    */   }
/*    */ 
/*    */   
/*    */   public static DynamisTokens getDynamisTokens() {
/* 57 */     return dynamisTokens;
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/dynamistokens/DynamisTokens.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */