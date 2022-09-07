/*    */ package mcm.unverified.misc;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Items
/*    */ {
/*    */   private ItemStack itemStack;
/*    */   private ItemStack enchantedBook;
/*    */   private ItemMeta itemMeta;
/*    */   private ItemMeta enchantedBookMeta;
/*    */   
/*    */   public ItemStack shopItem(int minecraftID, int amount, int minecraftDataValue, String category) {
/* 21 */     this.itemStack = new ItemStack(Material.getMaterial(minecraftID), amount, (short)minecraftDataValue);
/* 22 */     this.itemMeta = this.itemStack.getItemMeta();
/* 23 */     this.itemMeta.setDisplayName(category);
/* 24 */     this.itemStack.setItemMeta(this.itemMeta);
/*    */     
/* 26 */     return this.itemStack;
/*    */   }
/*    */   
/*    */   public ItemStack cancel() {
/* 30 */     this.itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
/* 31 */     this.itemMeta = this.itemStack.getItemMeta();
/* 32 */     this.itemMeta.setDisplayName(ChatUtils.format("&cCancel"));
/* 33 */     this.itemStack.setItemMeta(this.itemMeta);
/* 34 */     return this.itemStack;
/*    */   }
/*    */   
/*    */   public ItemStack back() {
/* 38 */     this.itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
/* 39 */     this.itemMeta = this.itemStack.getItemMeta();
/* 40 */     this.itemMeta.setDisplayName(ChatUtils.format("&7Back"));
/* 41 */     this.itemStack.setItemMeta(this.itemMeta);
/* 42 */     return this.itemStack;
/*    */   }
/*    */   
/*    */   public ItemStack confirm() {
/* 46 */     this.itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)13);
/* 47 */     this.itemMeta = this.itemStack.getItemMeta();
/* 48 */     this.itemMeta.setDisplayName(ChatUtils.format("&aConfirm"));
/* 49 */     this.itemStack.setItemMeta(this.itemMeta);
/* 50 */     return this.itemStack;
/*    */   }
/*    */   
/*    */   ItemStack confirmationItem(ItemStack item, int price) {
/* 54 */     this.itemStack = item;
/* 55 */     this.itemMeta = this.itemStack.getItemMeta();
/* 56 */     this.itemMeta.setDisplayName(String.format(ChatUtils.format("&6CONFIRM &7%s"), new Object[] { item.getItemMeta().getDisplayName() }));
/* 57 */     List<String> lore = new ArrayList<>();
/* 58 */     lore.add(ChatUtils.format("&7Please confirm your purchase."));
/* 59 */     lore.add(String.format(ChatUtils.format("&7Price: &e%d"), new Object[] { Integer.valueOf(price) }));
/* 60 */     this.itemMeta.setLore(lore);
/* 61 */     this.itemStack.setItemMeta(this.itemMeta);
/* 62 */     return item;
/*    */   }
/*    */   
/*    */   public ItemStack blackGlass() {
/* 66 */     this.itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
/* 67 */     this.itemMeta = this.itemStack.getItemMeta();
/* 68 */     this.itemMeta.setDisplayName(ChatUtils.format("&8"));
/* 69 */     this.itemStack.setItemMeta(this.itemMeta);
/* 70 */     return this.itemStack;
/*    */   }
/*    */   
/*    */   public ItemStack enchantedBook() {
/* 74 */     this.enchantedBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
/* 75 */     return this.enchantedBook;
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/misc/Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */