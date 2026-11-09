package goosegame;

import java.util.Random;

/** A player in the "jeu de l'oie" game */
public class Player {

   private static final Random RAND = new Random();

   /** current cell of the player */
   protected Cell cell;
   /** name of the player */
   protected String name;

   /**
    * When created, player has no cell, it must be added to the game to have one
    * 
    * @param name the name of this player
    */
   public Player(String name) {
      this.name = name;
      this.cell = null;
   }

   /** */
   public String toString() {
      return this.name;
   }

   /**
    * returns the current cell of the player
    * 
    * @return the current cell of the player
    */
   public Cell getCell() {
      return this.cell;
   }

   /**
    * changes the cell of the player
    * 
    * @param newCell the new cell
    */
   public void changeCell(Cell newCell) {
      this.cell = newCell;
   }

   /**
    * result of a 1d6 throw
    * 
    * @return random result of a 1d6 throw
    */
   private int oneDieThrow() {
      return RAND.nextInt(6) + 1;
   }

   /**
    * result of a 2d6 throw
    * 
    * @return random result of a 2d6 throw
    */
   public int twoDiceThrow() {
      int result = oneDieThrow() + oneDieThrow();
      return result;
   }
}// Player
