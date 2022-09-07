/*    */ package mcm.unverified.misc;
/*    */ 
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class AMob
/*    */ {
/*    */   private int minecraftID;
/*    */   private int murderedInRow;
/*    */   private int minTokens;
/*    */   private int maxTokens;
/*    */   
/*    */   AMob(int minecraftID, int murderedInRow, int minTokens, int maxTokens) {
/* 13 */     this.minecraftID = minecraftID;
/* 14 */     this.minTokens = minTokens;
/* 15 */     this.maxTokens = maxTokens;
/* 16 */     this.murderedInRow = murderedInRow;
/*    */   }
/*    */   
/*    */   public int getMinecraftID() {
/* 20 */     return this.minecraftID;
/*    */   }
/*    */   
/*    */   public int getMurderedInRow() {
/* 24 */     return this.murderedInRow;
/*    */   }
/*    */   
/*    */   public int getMinTokens() {
/* 28 */     return this.minTokens;
/*    */   }
/*    */   
/*    */   public int getMaxTokens() {
/* 32 */     return this.maxTokens;
/*    */   }
/*    */   
/*    */   public EntityType getEntity() {
/* 36 */     return EntityType.fromId(this.minecraftID);
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/misc/AMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */