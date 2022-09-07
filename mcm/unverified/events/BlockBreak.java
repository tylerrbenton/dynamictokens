/*    */ package mcm.unverified.events;
/*    */ import com.massivecraft.factions.Board;
/*    */ import com.massivecraft.factions.FLocation;
/*    */ import com.massivecraft.factions.FPlayer;
/*    */ import com.massivecraft.factions.FPlayers;
/*    */ import com.massivecraft.factions.Faction;
/*    */ import com.massivecraft.factions.Factions;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.SQLException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.ThreadLocalRandom;
/*    */ import mcm.unverified.handlers.PluginHandler;
/*    */ import mcm.unverified.misc.ABlock;
/*    */ import mcm.unverified.misc.Messages;
/*    */ import mcm.unverified.misc.MySQL;
/*    */ import mcm.unverified.misc.PluginPlayer;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ 
/*    */ public class BlockBreak implements Listener {
/* 29 */   private Map<UUID, Integer> mineStreaks = new HashMap<>();
/*    */   
/*    */   @EventHandler(priority = EventPriority.LOWEST)
/*    */   public void onBreak(BlockBreakEvent e) {
/* 33 */     PluginHandler pluginHandler = new PluginHandler();
/* 34 */     Messages messages = new Messages();
/*    */     
/* 36 */     ThreadLocalRandom random = ThreadLocalRandom.current();
/*    */     
/* 38 */     Block b = e.getBlock();
/* 39 */     Player p = e.getPlayer();
/*    */     
/* 41 */     FPlayer mPlayer = FPlayers.getInstance().getByPlayer(p);
/*    */     
/* 43 */     Faction location = Board.getInstance().getFactionAt(new FLocation(e.getBlock().getLocation()));
/*    */     
/* 45 */     if (location != Factions.getInstance().getWilderness() && location != mPlayer.getFaction() && (
/* 46 */       Factions.getInstance().getSafeZone() == location || Factions.getInstance().getWarZone() == location || location != mPlayer)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 52 */     if (p.getGameMode() != GameMode.SURVIVAL) {
/*    */       return;
/*    */     }
/*    */     
/* 56 */     if (!pluginHandler.getPluginPlayers().containsKey(p.getUniqueId())) {
/* 57 */       pluginHandler.getPluginPlayers().put(p.getUniqueId(), new PluginPlayer(p.getUniqueId()));
/*    */       
/*    */       return;
/*    */     } 
/* 61 */     PluginPlayer pluginPlayer = (PluginPlayer)pluginHandler.getPluginPlayers().get(p.getUniqueId());
/*    */     
/* 63 */     for (Map.Entry<ABlock, Integer> entry : (Iterable<Map.Entry<ABlock, Integer>>)pluginPlayer.getBlocks().entrySet()) {
/* 64 */       ABlock aBlock = entry.getKey();
/*    */       
/* 66 */       if (b.getTypeId() != aBlock.getMinecraftID() || b.getData() != aBlock.getMinecraftDataValue()) {
/*    */         continue;
/*    */       }
/*    */       
/* 70 */       pluginPlayer.getBlocks().put(aBlock, Integer.valueOf(((Integer)entry.getValue()).intValue() + 1));
/* 71 */       if (((Integer)entry.getValue()).intValue() != aBlock.getMinedInRow()) {
/*    */         return;
/*    */       }
/*    */       
/* 75 */       int amount = random.nextInt(aBlock.getMinTokens(), aBlock.getMaxTokens() + 1);
/* 76 */       if (amount == 0) {
/*    */         return;
/*    */       }
/*    */       try {
/* 80 */         PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE tokens SET tokens = tokens + ? WHERE uuid = ?");
/* 81 */         preparedStatement.setInt(1, amount);
/* 82 */         preparedStatement.setString(2, p.getUniqueId().toString());
/* 83 */         preparedStatement.executeUpdate();
/* 84 */         preparedStatement.close();
/* 85 */       } catch (SQLException sql) {
/* 86 */         sql.printStackTrace();
/*    */       } 
/* 88 */       p.sendRawMessage(messages.gainedForMining(b.getType(), amount));
/* 89 */       p.getWorld().playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
/* 90 */       pluginPlayer.getBlocks().put(aBlock, Integer.valueOf(0));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/events/BlockBreak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */