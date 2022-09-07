/*     */ package mcm.unverified.commands;
/*     */ 
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.SQLException;
/*     */ import mcm.unverified.handlers.MySQLHandler;
/*     */ import mcm.unverified.misc.Inventories;
/*     */ import mcm.unverified.misc.Messages;
/*     */ import mcm.unverified.misc.MySQL;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Commands
/*     */   implements CommandExecutor
/*     */ {
/*     */   public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
/*  24 */     Inventories inventories = new Inventories();
/*  25 */     Messages messages = new Messages();
/*  26 */     MySQLHandler mySQLHandler = new MySQLHandler();
/*     */ 
/*     */ 
/*     */     
/*  30 */     if (command.getName().equalsIgnoreCase("TShop")) {
/*  31 */       if (commandSender instanceof Player)
/*  32 */       { Player p = (Player)commandSender;
/*     */         
/*  34 */         if (args.length != 0) {
/*  35 */           p.sendRawMessage(messages.shop());
/*  36 */           return true;
/*     */         } 
/*  38 */         p.openInventory(inventories.getShopGUIInventory());
/*  39 */         p.getWorld().playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F); }
/*  40 */       else { commandSender.sendMessage(messages.mustBePlayer()); }
/*     */     
/*     */     }
/*  43 */     if (command.getName().equalsIgnoreCase("TGive")) {
/*  44 */       if (args.length != 2) {
/*  45 */         commandSender.sendMessage(messages.give());
/*  46 */         return true;
/*     */       } 
/*  48 */       String name = args[0];
/*     */       
/*  50 */       Player pTarget = Bukkit.getPlayerExact(name);
/*  51 */       if (pTarget == null) {
/*  52 */         commandSender.sendMessage(messages.playerNotAvailable());
/*  53 */         return true;
/*     */       } 
/*     */       
/*  56 */       int amount = 0;
/*     */       try {
/*  58 */         amount = Integer.parseInt(args[1]);
/*  59 */       } catch (IllegalArgumentException e) {
/*  60 */         commandSender.sendMessage(messages.give());
/*     */       } 
/*  62 */       if (amount == 0) {
/*  63 */         commandSender.sendMessage(messages.cannotBeZero());
/*  64 */         return true;
/*     */       } 
/*     */       try {
/*  67 */         PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE tokens SET tokens = tokens + ? WHERE uuid = ?");
/*  68 */         preparedStatement.setInt(1, amount);
/*  69 */         preparedStatement.setString(2, pTarget.getUniqueId().toString());
/*  70 */         preparedStatement.executeUpdate();
/*  71 */         preparedStatement.close();
/*  72 */       } catch (SQLException e) {
/*  73 */         e.printStackTrace();
/*     */       } 
/*  75 */       commandSender.sendMessage(messages.successfullyGave(amount, name));
/*  76 */       pTarget.sendRawMessage(messages.recievedTokens(amount));
/*     */     } 
/*     */     
/*  79 */     if (command.getName().equalsIgnoreCase("TTake")) {
/*  80 */       if (args.length != 2) {
/*  81 */         commandSender.sendMessage(messages.take());
/*  82 */         return true;
/*     */       } 
/*  84 */       String name = args[0];
/*     */       
/*  86 */       Player pTarget = Bukkit.getPlayerExact(name);
/*  87 */       if (pTarget == null) {
/*  88 */         commandSender.sendMessage(messages.playerNotAvailable());
/*  89 */         return true;
/*     */       } 
/*     */       
/*  92 */       int amount = 0;
/*     */       try {
/*  94 */         amount = Integer.parseInt(args[1]);
/*  95 */       } catch (IllegalArgumentException e) {
/*  96 */         commandSender.sendMessage(messages.take());
/*     */       } 
/*  98 */       if (amount == 0) {
/*  99 */         commandSender.sendMessage(messages.cannotBeZero());
/* 100 */         return true;
/*     */       } 
/* 102 */       mySQLHandler.getData(pTarget);
/* 103 */       if (mySQLHandler.getTokens() <= 0) {
/* 104 */         commandSender.sendMessage(messages.noTokensToRemove(name));
/* 105 */         return true;
/*     */       } 
/* 107 */       mySQLHandler.getData(pTarget);
/* 108 */       if (mySQLHandler.getTokens() < amount)
/*     */       { try {
/* 110 */           PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE tokens SET tokens = 0 WHERE uuid = ?");
/* 111 */           preparedStatement.setString(1, pTarget.getUniqueId().toString());
/* 112 */           preparedStatement.executeUpdate();
/* 113 */           preparedStatement.close();
/* 114 */         } catch (SQLException e) {
/* 115 */           e.printStackTrace();
/*     */         } 
/* 117 */         commandSender.sendMessage(messages.successfullyRemoved(amount, name));
/* 118 */         pTarget.sendRawMessage(messages.removedTokens(amount)); }
/*     */       else { try {
/* 120 */           PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE tokens SET tokens = tokens - ? WHERE uuid = ?");
/* 121 */           preparedStatement.setInt(1, amount);
/* 122 */           preparedStatement.setString(2, pTarget.getUniqueId().toString());
/* 123 */           preparedStatement.executeUpdate();
/* 124 */           preparedStatement.close();
/* 125 */         } catch (SQLException e) {
/* 126 */           e.printStackTrace();
/*     */         }  }
/* 128 */        commandSender.sendMessage(messages.successfullyRemoved(amount, name));
/* 129 */       pTarget.sendRawMessage(messages.removedTokens(amount));
/*     */     } 
/*     */     
/* 132 */     if (command.getName().equalsIgnoreCase("TPay")) {
/* 133 */       if (commandSender instanceof Player)
/* 134 */       { Player p = (Player)commandSender;
/*     */         
/* 136 */         if (args.length != 2) {
/* 137 */           p.sendRawMessage(messages.pay());
/* 138 */           return true;
/*     */         } 
/* 140 */         String name = args[0];
/*     */         
/* 142 */         if (name.equalsIgnoreCase(p.getName())) {
/* 143 */           p.sendRawMessage(messages.cannotPayYourself());
/* 144 */           return true;
/*     */         } 
/* 146 */         Player pTarget = Bukkit.getPlayerExact(name);
/*     */         
/* 148 */         if (pTarget == null) {
/* 149 */           p.sendRawMessage(messages.playerNotAvailable());
/* 150 */           return true;
/*     */         } 
/* 152 */         int amount = 0;
/*     */         try {
/* 154 */           amount = Integer.parseInt(args[1]);
/* 155 */         } catch (IllegalArgumentException e) {
/* 156 */           p.sendRawMessage(messages.pay());
/*     */         } 
/*     */         
/* 159 */         if (amount == 0) {
/* 160 */           p.sendRawMessage(messages.cannotBeZero());
/* 161 */           return true;
/*     */         } 
/*     */         
/* 164 */         mySQLHandler.getData(p);
/* 165 */         if (mySQLHandler.getTokens() < amount) {
/* 166 */           p.sendRawMessage(messages.notEnoughTokens());
/* 167 */           return true;
/*     */         } 
/*     */         try {
/* 170 */           PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE tokens SET tokens = tokens - ? WHERE uuid = ?");
/* 171 */           preparedStatement.setInt(1, amount);
/* 172 */           preparedStatement.setString(2, p.getUniqueId().toString());
/* 173 */           preparedStatement.executeUpdate();
/* 174 */           preparedStatement.close();
/* 175 */         } catch (SQLException e) {
/* 176 */           e.printStackTrace();
/*     */         } 
/*     */         
/*     */         try {
/* 180 */           PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE tokens SET tokens = tokens + ? WHERE uuid = ?");
/* 181 */           preparedStatement.setInt(1, amount);
/* 182 */           preparedStatement.setString(2, pTarget.getUniqueId().toString());
/* 183 */           preparedStatement.executeUpdate();
/* 184 */           preparedStatement.close();
/* 185 */         } catch (SQLException e) {
/* 186 */           e.printStackTrace();
/*     */         } 
/* 188 */         p.sendRawMessage(messages.paidSuccessfully(name, amount));
/* 189 */         pTarget.sendRawMessage(messages.hasPaid(p.getName(), amount)); }
/* 190 */       else { commandSender.sendMessage(messages.mustBePlayer()); }
/*     */     
/*     */     }
/* 193 */     if (command.getName().equalsIgnoreCase("TBal"))
/* 194 */       if (args.length == 0)
/* 195 */       { if (commandSender instanceof Player)
/* 196 */         { Player p = (Player)commandSender;
/*     */           
/* 198 */           mySQLHandler.getData(p);
/* 199 */           p.sendRawMessage(messages.yourCurrentBalance(mySQLHandler.getTokens())); }
/* 200 */         else { commandSender.sendMessage(messages.mustBePlayer()); }  }
/* 201 */       else if (args.length == 1)
/* 202 */       { String name = args[0];
/*     */         
/* 204 */         Player pTarget = Bukkit.getPlayerExact(name);
/* 205 */         if (pTarget == null) {
/* 206 */           commandSender.sendMessage(messages.playerNotAvailable());
/* 207 */           return true;
/*     */         } 
/* 209 */         mySQLHandler.getData(pTarget);
/* 210 */         commandSender.sendMessage(messages.currentBalance(name, mySQLHandler.getTokens())); }
/* 211 */       else { commandSender.sendMessage(messages.bal()); }
/*     */        
/* 213 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/commands/Commands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */