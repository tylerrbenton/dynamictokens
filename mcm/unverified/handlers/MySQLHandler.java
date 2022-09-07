/*    */ package mcm.unverified.handlers;
/*    */ 
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.UUID;
/*    */ import mcm.unverified.misc.MySQL;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MySQLHandler
/*    */ {
/*    */   private int pTokens;
/*    */   private UUID pUUID;
/*    */   private String pName;
/*    */   private PreparedStatement preparedStatement;
/*    */   
/*    */   public boolean hasData(Player p) {
/* 22 */     boolean hasData = false;
/*    */     try {
/* 24 */       this.preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM tokens WHERE uuid = ?");
/* 25 */       this.preparedStatement.setString(1, p.getUniqueId().toString());
/* 26 */       ResultSet resultSet = this.preparedStatement.executeQuery();
/* 27 */       if (resultSet.next()) {
/* 28 */         hasData = true;
/*    */       }
/* 30 */       this.preparedStatement.close();
/* 31 */       resultSet.close();
/* 32 */     } catch (SQLException sql) {
/* 33 */       sql.printStackTrace();
/*    */     } 
/* 35 */     return hasData;
/*    */   }
/*    */   
/*    */   public void getData(Player p) {
/*    */     try {
/* 40 */       this.preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM tokens WHERE uuid = ?");
/* 41 */       this.preparedStatement.setString(1, p.getUniqueId().toString());
/* 42 */       ResultSet resultSet = this.preparedStatement.executeQuery();
/* 43 */       while (resultSet.next()) {
/* 44 */         this.pUUID = UUID.fromString(resultSet.getString(1));
/* 45 */         this.pName = resultSet.getString(2);
/* 46 */         this.pTokens = resultSet.getInt(3);
/*    */       } 
/* 48 */       this.preparedStatement.close();
/* 49 */       resultSet.close();
/* 50 */     } catch (SQLException sql) {
/* 51 */       sql.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setData(Player p) {
/*    */     try {
/* 57 */       this.preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO tokens (uuid, name, tokens) VALUES (?, ?, 100)");
/* 58 */       this.preparedStatement.setString(1, p.getUniqueId().toString());
/* 59 */       this.preparedStatement.setString(2, p.getName());
/* 60 */       this.preparedStatement.executeUpdate();
/* 61 */       this.preparedStatement.close();
/* 62 */     } catch (SQLException sql) {
/* 63 */       sql.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getTokens() {
/* 68 */     return this.pTokens;
/*    */   }
/*    */   
/*    */   public UUID getUUID() {
/* 72 */     return this.pUUID;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 76 */     return this.pName;
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/handlers/MySQLHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */