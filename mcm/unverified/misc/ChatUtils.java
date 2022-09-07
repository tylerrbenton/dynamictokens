/*    */ package mcm.unverified.misc;
/*    */ 
/*    */ import mcm.unverified.dynamistokens.DynamisTokens;
/*    */ import org.bukkit.ChatColor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatUtils
/*    */ {
/*    */   public static String format(String message) {
/* 12 */     if (message == null) {
/* 13 */       return null;
/*    */     }
/* 15 */     return ChatColor.translateAlternateColorCodes('&', message);
/*    */   }
/*    */   
/*    */   static String consolePrefix() {
/* 19 */     return String.format(format("%s "), new Object[] { DynamisTokens.getDynamisTokens().getConfig().getString("config.misc.console prefix") });
/*    */   }
/*    */   
/*    */   static String errorPrefix() {
/* 23 */     return String.format(format("%s "), new Object[] { DynamisTokens.getDynamisTokens().getConfig().getString("config.misc.error prefix") });
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/misc/ChatUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */