/*    */ package mcm.unverified.misc;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ public class MySQL
/*    */ {
/*    */   private String host;
/*    */   private String database;
/*    */   private String username;
/*    */   private String password;
/*    */   private int port;
/*    */   private static Connection connection;
/*    */   
/*    */   public MySQL(String host, int port, String database, String username, String password) {
/* 17 */     this.host = host;
/* 18 */     this.port = port;
/* 19 */     this.database = database;
/* 20 */     this.username = username;
/* 21 */     this.password = password;
/*    */     
/*    */     try {
/* 24 */       openConnection();
/* 25 */     } catch (SQLException|ClassNotFoundException e) {
/* 26 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void openConnection() throws SQLException, ClassNotFoundException {
/* 31 */     if (connection != null && !connection.isClosed()) {
/*    */       return;
/*    */     }
/* 34 */     synchronized (this) {
/* 35 */       Class.forName("com.mysql.jdbc.Driver");
/* 36 */       connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Connection getConnection() {
/* 41 */     return connection;
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/misc/MySQL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */