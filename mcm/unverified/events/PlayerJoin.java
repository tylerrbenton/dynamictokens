/*    */ package mcm.unverified.events;
/*    */ 
/*    */ import mcm.unverified.handlers.MySQLHandler;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerJoin
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onJoin(PlayerJoinEvent e) {
/* 16 */     MySQLHandler mySQLHandler = new MySQLHandler();
/*    */     
/* 18 */     Player p = e.getPlayer();
/*    */     
/* 20 */     if (!mySQLHandler.hasData(p))
/* 21 */       mySQLHandler.setData(p); 
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/events/PlayerJoin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */