/*     */ package mcm.unverified.events;
/*     */ 
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import mcm.unverified.dynamistokens.DynamisTokens;
/*     */ import mcm.unverified.handlers.MySQLHandler;
/*     */ import mcm.unverified.misc.ChatUtils;
/*     */ import mcm.unverified.misc.Inventories;
/*     */ import mcm.unverified.misc.Items;
/*     */ import mcm.unverified.misc.Messages;
/*     */ import mcm.unverified.misc.MySQL;
/*     */ import mcm.unverified.misc.Purchase;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.EnchantmentStorageMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class InventoryClick
/*     */   implements Listener {
/*  34 */   private Map<UUID, Purchase> purchases = new HashMap<>();
/*     */   
/*     */   @EventHandler
/*     */   public void onClick(InventoryClickEvent e) {
/*     */     try {
/*  39 */       Items items = new Items();
/*  40 */       Inventories inventories = new Inventories();
/*  41 */       Messages messages = new Messages();
/*  42 */       MySQLHandler mySQLHandler = new MySQLHandler();
/*     */       
/*  44 */       FileConfiguration configuration = DynamisTokens.getDynamisTokens().getConfig();
/*     */ 
/*     */ 
/*     */       
/*  48 */       ConfigurationSection configurationSection = configuration.getConfigurationSection("config.misc");
/*  49 */       if (e.getInventory().contains(items.back()) || e.getInventory().contains(items.cancel()) || e.getInventory().getTitle().equalsIgnoreCase(configurationSection.getString("shop gui name")) || e
/*  50 */         .getInventory().getTitle().equalsIgnoreCase(configurationSection.getString("confirmation  gui name"))) {
/*  51 */         e.setCancelled(true);
/*     */       }
/*     */       
/*  54 */       if (!e.getInventory().contains(items.back()) && !e.getInventory().contains(items.cancel()) && !e.getInventory().getTitle().equalsIgnoreCase(configurationSection.getString("shop gui name")) && 
/*  55 */         !e.getInventory().getTitle().equalsIgnoreCase(configurationSection.getString("confirmation  gui name"))) {
/*     */         return;
/*     */       }
/*     */       
/*  59 */       if (!e.getClick().isRightClick() && !e.getClick().isLeftClick()) {
/*  60 */         e.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/*  64 */       if (e.getWhoClicked() instanceof Player) {
/*  65 */         Player p = (Player)e.getWhoClicked();
/*     */         
/*     */         try {
/*  68 */           if (e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().equals(items.blackGlass())) {
/*     */             return;
/*     */           }
/*  71 */         } catch (NullPointerException nullPointerException) {}
/*     */ 
/*     */ 
/*     */         
/*  75 */         if (!e.getInventory().getTitle().equalsIgnoreCase(ChatUtils.format(configurationSection.getString("confirmation gui name"))) && this.purchases.containsKey(p.getUniqueId())) {
/*  76 */           this.purchases.remove(p.getUniqueId());
/*     */         }
/*     */         
/*     */         try {
/*  80 */           if (e.getCurrentItem().equals(items.cancel())) {
/*  81 */             p.closeInventory();
/*  82 */             p.sendRawMessage(messages.inventoryClosed());
/*  83 */             if (this.purchases.containsKey(p.getUniqueId()))
/*  84 */               this.purchases.remove(p.getUniqueId()); 
/*     */             return;
/*     */           } 
/*  87 */           if (e.getCurrentItem().getItemMeta().getDisplayName().equals(items.back().getItemMeta().getDisplayName())) {
/*  88 */             p.openInventory(inventories.getShopGUIInventory());
/*     */             return;
/*     */           } 
/*  91 */         } catch (NullPointerException nullPointerException) {}
/*     */ 
/*     */ 
/*     */         
/*  95 */         if (e.getInventory().getTitle().equals(ChatUtils.format(DynamisTokens.getDynamisTokens().getConfig().getString("config.misc.shop gui name")))) {
/*  96 */           p.openInventory((Inventory)inventories.getShopCategoryInventories().get(e.getCurrentItem()));
/*     */           
/*     */           return;
/*     */         } 
/* 100 */         if (e.getSlot() > 53 && e.getSlot() < 0) {
/*     */           return;
/*     */         }
/*     */         
/* 104 */         if (!this.purchases.containsKey(p.getUniqueId())) {
/*     */           try {
/* 106 */             this.purchases.put(p.getUniqueId(), new Purchase(e.getCurrentItem(), e.getCurrentItem().getItemMeta().getDisplayName()));
/* 107 */           } catch (NullPointerException nullPointerException) {}
/*     */ 
/*     */           
/* 110 */           p.openInventory(inventories.getConfirmationMenu(e.getCurrentItem(), ((Purchase)this.purchases.get(p.getUniqueId())).getPrice()));
/*     */           
/*     */           return;
/*     */         } 
/* 114 */         if (e.getInventory().getTitle().equalsIgnoreCase(ChatUtils.format(configuration.getString("config.misc.confirmation gui name")))) {
/*     */           
/* 116 */           if (e.getCurrentItem().getTypeId() != items.confirm().getTypeId() || e.getCurrentItem().getData().getData() != items.confirm().getData().getData() || 
/* 117 */             !e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(items.confirm().getItemMeta().getDisplayName())) {
/*     */             return;
/*     */           }
/* 120 */           mySQLHandler.getData(p);
/* 121 */           if (mySQLHandler.getTokens() < ((Purchase)this.purchases.get(p.getUniqueId())).getPrice()) {
/* 122 */             p.sendRawMessage(messages.notEnoughTokens());
/*     */             return;
/*     */           } 
/*     */           try {
/* 126 */             PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE tokens SET tokens = tokens - ? WHERE uuid = ?");
/* 127 */             preparedStatement.setInt(1, ((Purchase)this.purchases.get(p.getUniqueId())).getPrice());
/* 128 */             preparedStatement.setString(2, p.getUniqueId().toString());
/* 129 */             preparedStatement.executeUpdate();
/* 130 */             preparedStatement.close();
/* 131 */           } catch (SQLException sql) {
/* 132 */             sql.printStackTrace();
/*     */           } 
/*     */           
/* 135 */           Purchase purchase = this.purchases.get(p.getUniqueId());
/* 136 */           ItemStack item = purchase.getItemStack();
/*     */           
/* 138 */           for (String mInventory : DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection("config.shop.inventories").getKeys(false)) {
/*     */             
/* 140 */             for (String mItem : DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection(String.format("config.shop.inventories.%s.items", new Object[] { mInventory })).getKeys(false)) {
/* 141 */               configurationSection = DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection(String.format("config.shop.inventories.%s.items.%s", new Object[] { mInventory, mItem }));
/*     */               
/* 143 */               if (item.getTypeId() != configurationSection.getInt("id") || item.getData().getData() != configurationSection.getInt("data value") || !purchase.getName().equalsIgnoreCase(
/* 144 */                   ChatUtils.format(configurationSection.getString("name")))) {
/*     */                 continue;
/*     */               }
/* 147 */               configurationSection = DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection(String.format("config.shop.inventories.%s.items.%s.received items", new Object[] { mInventory, mItem }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 153 */               for (String mCommand : configurationSection.getStringList("commands")) {
/* 154 */                 Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), mCommand.replace("%player%", p.getName()));
/*     */               }
/*     */               
/* 157 */               ItemStack enchantedBook = null;
/*     */               try {
/* 159 */                 for (String mReceivedEnchant : configurationSection.getConfigurationSection("enchantments").getKeys(false)) {
/* 160 */                   configurationSection = DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection(String.format("config.shop.inventories.%s.items.%s.received items.enchantments.%s", new Object[] { mInventory, mItem, mReceivedEnchant }));
/*     */ 
/*     */                   
/* 163 */                   enchantedBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
/* 164 */                   EnchantmentStorageMeta storageMeta = (EnchantmentStorageMeta)enchantedBook.getItemMeta();
/*     */                   
/* 166 */                   storageMeta.addStoredEnchant(Enchantment.getById(configurationSection.getInt("enchantment id")), configurationSection.getInt("enchantment level"), true);
/*     */                   
/* 168 */                   enchantedBook.setItemMeta((ItemMeta)storageMeta);
/*     */                 } 
/* 170 */               } catch (NullPointerException nullPointerException) {}
/*     */ 
/*     */ 
/*     */               
/* 174 */               if (enchantedBook != null) {
/* 175 */                 EnchantmentStorageMeta storageMeta = (EnchantmentStorageMeta)enchantedBook.getItemMeta();
/*     */                 
/* 177 */                 if (storageMeta.hasStoredEnchants()) {
/* 178 */                   p.getInventory().addItem(new ItemStack[] { enchantedBook });
/*     */                 }
/*     */               } 
/*     */               
/*     */               try {
/* 183 */                 for (String mReceivedItem : DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection(String.format("config.shop.inventories.%s.items.%s.received items.items", new Object[] { mInventory, mItem
/* 184 */                       })).getKeys(false)) {
/*     */                   
/* 186 */                   configurationSection = DynamisTokens.getDynamisTokens().getConfig().getConfigurationSection(String.format("config.shop.inventories.%s.items.%s.received items.items.%s", new Object[] { mInventory, mItem, mReceivedItem }));
/*     */ 
/*     */                   
/* 189 */                   ItemStack receivedItem = new ItemStack(Material.getMaterial(configurationSection.getInt("id")), configurationSection.getInt("amount"), (short)configuration.getInt("data value"));
/* 190 */                   ItemMeta receivedItemMeta = receivedItem.getItemMeta();
/*     */                   
/* 192 */                   receivedItemMeta.setDisplayName(ChatUtils.format(configurationSection.getString("name")));
/*     */                   
/* 194 */                   receivedItem.setItemMeta(receivedItemMeta);
/*     */                   
/* 196 */                   for (String mReceivedItemEnchant : configurationSection.getConfigurationSection("enchantments").getKeys(false)) {
/* 197 */                     configurationSection = configurationSection.getConfigurationSection(String.format("enchantments.%s", new Object[] { mReceivedItemEnchant }));
/*     */                     
/* 199 */                     receivedItem.addEnchantment(Enchantment.getById(configurationSection.getInt("enchantment id")), configurationSection.getInt("enchantment level"));
/*     */                   } 
/*     */                   
/* 202 */                   p.getInventory().addItem(new ItemStack[] { receivedItem });
/*     */                 } 
/* 204 */               } catch (NullPointerException nullPointerException) {}
/*     */ 
/*     */               
/* 207 */               p.sendRawMessage(messages.confirmed());
/* 208 */               p.closeInventory();
/* 209 */               p.getWorld().playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
/*     */               
/* 211 */               this.purchases.remove(p.getUniqueId());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 216 */     } catch (NullPointerException nullPointerException) {}
/*     */   }
/*     */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/events/InventoryClick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */