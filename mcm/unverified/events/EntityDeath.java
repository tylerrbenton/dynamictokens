/*    */ package mcm.unverified.events;
/*    */ 
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ThreadLocalRandom;
/*    */ import mcm.unverified.handlers.PluginHandler;
/*    */ import mcm.unverified.misc.AMob;
/*    */ import mcm.unverified.misc.Messages;
/*    */ import mcm.unverified.misc.MySQL;
/*    */ import mcm.unverified.misc.PluginPlayer;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDeathEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityDeath
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onDeath(EntityDeathEvent e) {
/* 28 */     Messages messages = new Messages();
/* 29 */     PluginHandler pluginHandler = new PluginHandler();
/*    */     
/* 31 */     LivingEntity mob = e.getEntity();
/* 32 */     Player p = e.getEntity().getKiller();
/*    */     
/* 34 */     ThreadLocalRandom random = ThreadLocalRandom.current();
/*    */     
/* 36 */     if (e.getEntity() instanceof Player) {
/*    */       return;
/*    */     }
/*    */     
/* 40 */     if (p == null) {
/*    */       return;
/*    */     }
/*    */     
/* 44 */     if (pluginHandler.getPluginPlayers().get(p.getUniqueId()) == null || 
/* 45 */       !pluginHandler.getPluginPlayers().containsKey(p.getUniqueId())) {
/* 46 */       pluginHandler.getPluginPlayers().put(p.getUniqueId(), new PluginPlayer(p.getUniqueId()));
/*    */     }
/*    */     
/* 49 */     PluginPlayer pluginPlayer = (PluginPlayer)pluginHandler.getPluginPlayers().get(p.getUniqueId());
/*    */     
/*    */     try {
/* 52 */       for (Map.Entry<AMob, Integer> entry : (Iterable<Map.Entry<AMob, Integer>>)pluginPlayer.getMobs().entrySet()) {
/* 53 */         AMob aMob = entry.getKey();
/*    */         
/* 55 */         if (aMob.getMinecraftID() != mob.getType().getTypeId()) {
/*    */           continue;
/*    */         }
/*    */         
/* 59 */         if (((Integer)pluginPlayer.getMobs().get(aMob)).intValue() != aMob.getMurderedInRow()) {
/* 60 */           pluginPlayer.getMobs().put(aMob, Integer.valueOf(((Integer)pluginPlayer.getMobs().get(aMob)).intValue() + 1));
/*    */           
/*    */           return;
/*    */         } 
/* 64 */         pluginPlayer.getMobs().put(aMob, Integer.valueOf(1));
/*    */         
/* 66 */         int amount = random.nextInt(aMob.getMinTokens(), aMob.getMaxTokens() + 1);
/* 67 */         if (amount == 0) {
/*    */           return;
/*    */         }
/*    */         try {
/* 71 */           PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE tokens SET tokens = tokens + ? WHERE uuid = ?");
/*    */           
/* 73 */           preparedStatement.setInt(1, amount);
/* 74 */           preparedStatement.setString(2, p.getUniqueId().toString());
/* 75 */           preparedStatement.executeUpdate();
/* 76 */           preparedStatement.close();
/* 77 */         } catch (SQLException sql) {
/* 78 */           sql.printStackTrace();
/*    */         } 
/* 80 */         p.sendRawMessage(messages.gainedForMurdering(mob.getType(), amount));
/* 81 */         p.getWorld().playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
/*    */       } 
/* 83 */     } catch (NullPointerException nullPointerException) {}
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/events/EntityDeath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */