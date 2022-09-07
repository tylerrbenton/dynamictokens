/*     */ package mcm.unverified.misc;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import mcm.unverified.dynamistokens.DynamisTokens;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Inventories
/*     */ {
/*  22 */   private static Map<ItemStack, Inventory> shopCategoryInventories = new HashMap<>();
/*     */   
/*  24 */   private FileConfiguration configuration = DynamisTokens.getDynamisTokens().getConfig();
/*     */   
/*  26 */   private static List<Category> categories = new ArrayList<>();
/*     */   
/*  28 */   private static Map<ItemStack, String> umm = new HashMap<>();
/*     */   
/*  30 */   private static Inventory mainMenu = Bukkit.createInventory(null, 27, ChatUtils.format(DynamisTokens.getDynamisTokens().getConfig().getString("config.misc.shop gui name")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Inventory getShopGUIInventory() {
/*  38 */     addGlass(mainMenu);
/*     */     
/*  40 */     for (String category : this.configuration.getConfigurationSection("config.shop.inventories").getKeys(false)) {
/*     */       
/*  42 */       ConfigurationSection configurationSection = DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection(String.format("config.shop.inventories.%s.item", new Object[] { category }));
/*     */       
/*  44 */       if (configurationSection.getInt("slot") > 17 && configurationSection.getInt("slot") < 9)
/*     */       {
/*  46 */         throw new IllegalArgumentException();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  51 */       Category newCategory = new Category(configurationSection.getString("name"), configurationSection.getInt("id"), configurationSection.getInt("amount"), configurationSection.getInt("data value"), configurationSection.getInt("slot"));
/*     */       
/*  53 */       ItemStack categoryItem = newCategory.getItem();
/*     */ 
/*     */       
/*     */       try {
/*  57 */         for (String enchant : this.configuration.getConfigurationSection(String.format("config.shop.inventories.%s.item.enchantments", new Object[] { category })).getKeys(false)) {
/*     */           
/*  59 */           categoryItem.addEnchantment(Enchantment.getById(this.configuration.getInt(String.format("config.shop.inventories.%s.item.enchantments.%s.enchantment id", new Object[] { category, enchant }))), this.configuration
/*  60 */               .getInt(String.format("config.shop.inventories.%s.item.enchantments.%s.enchantment level", new Object[] { category, enchant })));
/*     */         } 
/*  62 */       } catch (NullPointerException nullPointerException) {}
/*     */ 
/*     */ 
/*     */       
/*  66 */       mainMenu.setItem(newCategory.getInventorySlot(), categoryItem);
/*     */       
/*  68 */       Inventory categoryInventory = Bukkit.createInventory(null, 54, ChatUtils.format(newCategory.getName()));
/*  69 */       addGlass(categoryInventory);
/*  70 */       categoryInventory.setItem(53, this.items.cancel());
/*  71 */       categoryInventory.setItem(45, this.items.back());
/*     */       
/*  73 */       if (this.configuration.getConfigurationSection(String.format("config.shop.inventories.%s.items", new Object[] { category })).getKeys(false).size() != 0) {
/*     */         
/*  75 */         for (String item : this.configuration.getConfigurationSection(String.format("config.shop.inventories.%s.items", new Object[] { category })).getKeys(false)) {
/*     */           
/*  77 */           List<String> description = new ArrayList<>();
/*     */           
/*  79 */           for (String line : this.configuration.getStringList(String.format("config.shop.inventories.%s.items.%s.description", new Object[] { category, item }))) {
/*     */             
/*  81 */             description.add(ChatUtils.format(line.replace("%price%", String.valueOf(this.configuration.getInt(String.format("config.shop.inventories.%s.items.%s.price", new Object[] { category, item }))))));
/*     */           } 
/*     */           
/*  84 */           newCategory.addShopItem(this.configuration
/*  85 */               .getInt(String.format("config.shop.inventories.%s.items.%s.id", new Object[] { category, item })), this.configuration
/*  86 */               .getInt(String.format("config.shop.inventories.%s.items.%s.amount", new Object[] { category, item })), this.configuration
/*  87 */               .getInt(String.format("config.shop.inventories.%s.items.%s.data value", new Object[] { category, item })), this.configuration
/*  88 */               .getString(String.format("config.shop.inventories.%s.items.%s.name", new Object[] { category, item })), item, description);
/*     */         } 
/*     */ 
/*     */         
/*  92 */         for (Map.Entry<ItemStack, String> entry : newCategory.getItems().entrySet()) {
/*     */           
/*  94 */           ItemStack item = entry.getKey();
/*     */           
/*  96 */           umm.put(item, entry.getValue());
/*     */         } 
/*     */         
/*  99 */         for (Map.Entry<ItemStack, String> entry : umm.entrySet()) {
/*     */           
/* 101 */           ItemStack item = entry.getKey();
/*     */ 
/*     */           
/*     */           try {
/* 105 */             for (String enchant : this.configuration.getConfigurationSection(String.format("config.shop.inventories.%s.items.%s.enchantments", new Object[] { category, entry.getValue() })).getKeys(false)) {
/*     */               
/* 107 */               item.addEnchantment(Enchantment.getById(this.configuration.getInt(String.format("config.shop.inventories.%s.items.%s.enchantments.%s.enchantment id", new Object[] { category, entry.getValue(), enchant }))), this.configuration
/* 108 */                   .getInt(String.format("config.shop.inventories.%s.items.%s.enchantments.%s.enchantment level", new Object[] { category, entry.getValue(), enchant })));
/*     */             } 
/* 110 */           } catch (NullPointerException|IllegalArgumentException nullPointerException) {}
/*     */ 
/*     */           
/* 113 */           newCategory.getItems().put(item, entry.getValue());
/*     */         } 
/* 115 */         categories.add(newCategory);
/*     */       } 
/*     */       
/* 118 */       for (Map.Entry<ItemStack, String> entry : newCategory.getItems().entrySet()) {
/*     */         
/* 120 */         if (((ItemStack)entry.getKey()).getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtils.format("&eStone Sword uh' A Thousand Baccas"))) {
/*     */           continue;
/*     */         }
/*     */         
/* 124 */         if (this.configuration.getInt(String.format("config.shop.inventories.%s.items.%s.slot", new Object[] { category, entry.getValue() })) <= 8) {
/*     */           continue;
/*     */         }
/*     */         
/* 128 */         categoryInventory.setItem(this.configuration.getInt(String.format("config.shop.inventories.%s.items.%s.slot", new Object[] { category, entry.getValue() })), entry.getKey());
/*     */       } 
/*     */       
/* 131 */       shopCategoryInventories.put(categoryItem, categoryInventory);
/*     */     } 
/* 133 */     mainMenu.setItem(22, this.items.cancel());
/* 134 */     return mainMenu;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addGlass(Inventory inventory) {
/* 139 */     for (int counter = 0; counter < inventory.getSize(); counter++) {
/*     */       
/* 141 */       if (counter < 9 || counter > ((inventory.getSize() == 27) ? 17 : 44))
/*     */       {
/* 143 */         inventory.setItem(counter, this.items.blackGlass());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getConfirmationMenu(ItemStack item, int price) {
/* 150 */     Inventory confirmationMenu = Bukkit.createInventory(null, 27, ChatUtils.format(this.configuration.getString("config.misc.confirmation gui name")));
/*     */     
/* 152 */     for (int counter = 9; counter < 18; counter++) {
/*     */       
/* 154 */       if (counter == 13) {
/*     */         
/* 156 */         confirmationMenu.setItem(counter, this.items.confirmationItem(item, price));
/* 157 */       } else if (counter < 12) {
/*     */         
/* 159 */         confirmationMenu.setItem(counter, this.items.cancel());
/* 160 */       } else if (counter > 14) {
/*     */         
/* 162 */         confirmationMenu.setItem(counter, this.items.confirm());
/*     */       } 
/*     */     } 
/* 165 */     return confirmationMenu;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Category> getCategories() {
/* 170 */     return categories;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<ItemStack, Inventory> getShopCategoryInventories() {
/* 175 */     return shopCategoryInventories;
/*     */   }
/*     */   
/* 178 */   private Items items = new Items();
/*     */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/misc/Inventories.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */