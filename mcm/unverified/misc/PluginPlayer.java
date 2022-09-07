/*    */ package mcm.unverified.misc;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import mcm.unverified.dynamistokens.DynamisTokens;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ 
/*    */ 
/*    */ public class PluginPlayer
/*    */ {
/* 12 */   private Map<ABlock, Integer> blocks = new HashMap<>();
/* 13 */   private Map<AMob, Integer> mobs = new HashMap<>();
/*    */   
/*    */   private UUID uuid;
/*    */   
/*    */   public PluginPlayer(UUID uuid) {
/* 18 */     this.uuid = uuid;
/*    */ 
/*    */     
/* 21 */     for (String block : DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection("config.blocks").getKeys(false)) {
/* 22 */       ConfigurationSection configurationSection = DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection(String.format("config.blocks.%s", new Object[] { block }));
/*    */       
/* 24 */       this.blocks.put(new ABlock(configurationSection
/* 25 */             .getInt("id"), configurationSection.getInt("data value"), configurationSection.getInt("mined in row"), configurationSection.getInt("min tokens"), configurationSection
/* 26 */             .getInt("max tokens")), Integer.valueOf(1));
/*    */     } 
/*    */     
/* 29 */     for (String mob : DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection("config.mobs").getKeys(false)) {
/* 30 */       ConfigurationSection configurationSection = DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection(String.format("config.mobs.%s", new Object[] { mob }));
/*    */       
/* 32 */       this.mobs.put(new AMob(configurationSection.getInt("id"), configurationSection.getInt("murdered in row"), configurationSection.getInt("min tokens"), configurationSection.getInt("max tokens")), Integer.valueOf(1));
/*    */     } 
/*    */   }
/*    */   
/*    */   public UUID getUUID() {
/* 37 */     return this.uuid;
/*    */   }
/*    */   
/*    */   public Map<ABlock, Integer> getBlocks() {
/* 41 */     return this.blocks;
/*    */   }
/*    */   
/*    */   public Map<AMob, Integer> getMobs() {
/* 45 */     return this.mobs;
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/misc/PluginPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */