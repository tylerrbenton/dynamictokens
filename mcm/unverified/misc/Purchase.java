/*    */ package mcm.unverified.misc;
/*    */ 
/*    */ import mcm.unverified.dynamistokens.DynamisTokens;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Purchase
/*    */ {
/*    */   private ItemStack itemStack;
/*    */   private int price;
/*    */   private String name;
/*    */   
/*    */   public Purchase(ItemStack itemStack, String name) {
/* 20 */     FileConfiguration configuration = DynamisTokens.getDynamisTokens().getConfig();
/*    */     
/* 22 */     this.itemStack = itemStack;
/* 23 */     this.name = name;
/*    */     
/* 25 */     for (String category : configuration.getConfigurationSection("config.shop.inventories").getKeys(false)) {
/*    */       
/* 27 */       for (String item : configuration.getConfigurationSection(String.format("config.shop.inventories.%s.items", new Object[] { category })).getKeys(false)) {
/* 28 */         ConfigurationSection configurationSection = configuration.getConfigurationSection(String.format("config.shop.inventories.%s.items.%s", new Object[] { category, item }));
/*    */         
/* 30 */         if (itemStack.getTypeId() == configurationSection.getInt("id") && itemStack.getData().getData() == configurationSection.getInt("data value") && itemStack
/* 31 */           .getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtils.format(configurationSection.getString("name")))) {
/* 32 */           this.price = configurationSection.getInt("price");
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack() {
/* 40 */     return this.itemStack;
/*    */   }
/*    */   
/*    */   public int getPrice() {
/* 44 */     return this.price;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 48 */     return this.name;
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/misc/Purchase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */