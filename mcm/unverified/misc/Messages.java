/*     */ package mcm.unverified.misc;
/*     */ 
/*     */ import mcm.unverified.dynamistokens.DynamisTokens;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.EntityType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Messages
/*     */ {
/*     */   public String gainedForMurdering(EntityType entityType, int amount) {
/*  20 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("12").replace("%amount%", String.valueOf(amount)).replace("%mob%", entityType.toString().toLowerCase()));
/*     */   }
/*     */   
/*     */   public String gainedForMining(Material material, int amount) {
/*  24 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("13").replace("%amount%", String.valueOf(amount)).replace("%block%", material.name().replace('_', ' ')
/*  25 */           .toLowerCase()));
/*     */   }
/*     */   
/*     */   public String confirmed() {
/*  29 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("3"));
/*     */   }
/*     */   
/*     */   public String yourCurrentBalance(int amount) {
/*  33 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("1").replace("%amount%", String.valueOf(amount)));
/*     */   }
/*     */   
/*     */   public String currentBalance(String name, int amount) {
/*  37 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("2").replace("%player%", name).replace("%amount%", String.valueOf(amount)));
/*     */   }
/*     */   
/*     */   public String hasPaid(String name, int amount) {
/*  41 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("0").replace("%player%", name).replace("%amount%", String.valueOf(amount)));
/*     */   }
/*     */   
/*     */   public String paidSuccessfully(String name, int amount) {
/*  45 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("5").replace("%player%", name).replace("%amount%", String.valueOf(amount)));
/*     */   }
/*     */   
/*     */   public String inventoryClosed() {
/*  49 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("4"));
/*     */   }
/*     */   
/*     */   public String recievedTokens(int amount) {
/*  53 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("6").replace("%amount%", String.valueOf(amount)));
/*     */   }
/*     */   
/*     */   public String removedTokens(int amount) {
/*  57 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("7").replace("%amount%", String.valueOf(amount)));
/*     */   }
/*     */   
/*     */   public String successfullyRemoved(int amount, String name) {
/*  61 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("8").replace("%amount%", String.valueOf(amount)).replace("%player%", name));
/*     */   }
/*     */   
/*     */   public String successfullyGave(int amount, String name) {
/*  65 */     return ChatUtils.format(ChatUtils.consolePrefix() + this.configurationSection.getString("9").replace("%amount%", String.valueOf(amount)).replace("%player%", name));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String noTokensToRemove(String name) {
/*  71 */     return ChatUtils.format(ChatUtils.errorPrefix() + this.configurationSection.getString("10").replace("%player%", name));
/*     */   }
/*     */   
/*     */   public String cannotBeZero() {
/*  75 */     return ChatUtils.format(ChatUtils.errorPrefix() + "&cThe specified amount cannot be zero.");
/*     */   }
/*     */   
/*     */   public String mustBePlayer() {
/*  79 */     return ChatUtils.format(ChatUtils.errorPrefix() + "&cYou must be a player to execute that command.");
/*     */   }
/*     */   
/*     */   public String cannotPayYourself() {
/*  83 */     return ChatUtils.format(ChatUtils.errorPrefix() + "&cYou cannot pay yourself.");
/*     */   }
/*     */   
/*     */   public String playerNotAvailable() {
/*  87 */     return ChatUtils.format(ChatUtils.errorPrefix() + "&cThe specified player could not be found.");
/*     */   }
/*     */   
/*     */   public String notEnoughTokens() {
/*  91 */     return ChatUtils.format(ChatUtils.errorPrefix() + this.configurationSection.getString("11"));
/*     */   }
/*     */   
/*     */   public String needPermission(String permission) {
/*  95 */     return String.format(ChatUtils.format(ChatUtils.errorPrefix() + "&cYou must have permission %s to execute this command."), new Object[] { permission });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String shop() {
/* 101 */     return ChatUtils.format(ChatUtils.errorPrefix() + "&c/TShop");
/*     */   }
/*     */   
/*     */   public String give() {
/* 105 */     return ChatUtils.format(ChatUtils.errorPrefix() + "&c/TGive <Player> <Amount>");
/*     */   }
/*     */   
/*     */   public String take() {
/* 109 */     return ChatUtils.format(ChatUtils.errorPrefix() + "&c/TTake <Player> <Amount>");
/*     */   }
/*     */   
/*     */   public String pay() {
/* 113 */     return ChatUtils.format(ChatUtils.errorPrefix() + "&c/TPay <Player> <Amount>");
/*     */   }
/*     */   
/*     */   public String bal() {
/* 117 */     return ChatUtils.format(ChatUtils.errorPrefix() + "&c/TBal\n" + ChatUtils.errorPrefix() + "&c/TBal <Player>");
/*     */   }
/*     */   
/* 120 */   private ConfigurationSection configurationSection = DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection("config.messages");
/*     */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/misc/Messages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */