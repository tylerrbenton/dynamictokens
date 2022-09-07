/*    */ package mcm.unverified.misc;
/*    */ 
/*    */ public class ABlock {
/*    */   private int minecraftID;
/*    */   private int minecraftDataValue;
/*    */   private int minedInRow;
/*    */   private int minTokens;
/*    */   private int maxTokens;
/*    */   
/*    */   ABlock(int minecraftID, int minecraftDataValue, int minedInRow, int minTokens, int maxTokens) {
/* 11 */     this.minecraftID = minecraftID;
/* 12 */     this.minecraftDataValue = minecraftDataValue;
/* 13 */     this.minedInRow = minedInRow;
/* 14 */     this.minTokens = minTokens;
/* 15 */     this.maxTokens = maxTokens;
/*    */   }
/*    */   
/*    */   public int getMinecraftID() {
/* 19 */     return this.minecraftID;
/*    */   }
/*    */   
/*    */   public int getMinecraftDataValue() {
/* 23 */     return this.minecraftDataValue;
/*    */   }
/*    */   
/*    */   public int getMinedInRow() {
/* 27 */     return this.minedInRow;
/*    */   }
/*    */   
/*    */   public int getMinTokens() {
/* 31 */     return this.minTokens;
/*    */   }
/*    */   
/*    */   public int getMaxTokens() {
/* 35 */     return this.maxTokens;
/*    */   }
/*    */ }


/* Location:              /home/tyler/Downloads/jar files/DynamisTokens.jar!/mcm/unverified/misc/ABlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */