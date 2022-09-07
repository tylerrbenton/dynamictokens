/*    */ package mcm.unverified.misc;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Category
/*    */ {
/* 18 */   private Map<ItemStack, String> items = new HashMap<>();
/*    */   
/*    */   private String name;
/*    */   private int minecraftID;
/*    */   private int amount;
/*    */   private int minecraftDataValue;
/*    */   private int inventorySlot;
/*    */   
/*    */   Category(String name, int minecraftID, int amount, int minecraftDataValue, int inventorySlot) {
/* 27 */     this.name = name;
/* 28 */     this.minecraftID = minecraftID;
/* 29 */     this.amount = amount;
/* 30 */     this.minecraftDataValue = minecraftDataValue;
/* 31 */     this.inventorySlot = inventorySlot;
/*    */   }
/*    */   
/*    */   String getName() {
/* 35 */     return this.name;
/*    */   }
/*    */   
/*    */   public int getMinecraftID() {
/* 39 */     return this.minecraftID;
/*    */   }
/*    */   
/*    */   public int getMinecraftDataValue() {
/* 43 */     return this.minecraftDataValue;
/*    */   }
/*    */   
/*    */   int getInventorySlot() {
/* 47 */     return this.inventorySlot;
/*    */   }
/*    */   
/*    */   ItemStack getItem() {
/* 51 */     ItemStack item = new ItemStack(Material.getMaterial(this.minecraftID), this.amount, (short)this.minecraftDataValue);
/* 52 */     ItemMeta meta = item.getItemMeta();
/* 53 */     meta.setDisplayName(ChatUtils.format(this.name));
/* 54 */     item.setItemMeta(meta);
/* 55 */     return item;
/*    */   }
/*    */   
/*    */   void addShopItem(int minecraftID, int amount, int minecraftDataValue, String name, String itemKey, List<String> lore) {
/* 59 */     ItemStack item = new ItemStack(Material.getMaterial(minecraftID), amount, (short)minecraftDataValue);
/* 60 */     ItemMeta meta = item.getItemMeta();
/* 61 */     meta.setDisplayName(ChatUtils.format(name));
/* 62 */     meta.setLore(lore);
/* 63 */     item.setItemMeta(meta);
/* 64 */     this.items.put(item, itemKey);
/*    */   }
/*    */   
/*    */   public Map<ItemStack, String> getItems() {
/* 68 */     return this.items;
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/misc/Category.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */