/*    */ package mcm.unverified.handlers;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import mcm.unverified.misc.PluginPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PluginHandler
/*    */ {
/* 12 */   private static Map<UUID, PluginPlayer> pluginPlayers = new HashMap<>();
/*    */   
/*    */   public Map<UUID, PluginPlayer> getPluginPlayers() {
/* 15 */     return pluginPlayers;
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/handlers/PluginHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */