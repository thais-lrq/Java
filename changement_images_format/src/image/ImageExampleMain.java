package image;

import image.util.*;

public class ImageExampleMain {

   private static final String LAMP_IMAGE = "/images/lamp.pgm";
   private static final String CAT_IMAGE = "/images/chat.pgm";

   public static void main(String[] args) {

      Image lampImg = ImageLoader.loadPGM(LAMP_IMAGE);
      Image catImg = ImageLoader.loadPGM(CAT_IMAGE);

      ImageDisplayer displayer = new ImageDisplayer();
      displayer.display(lampImg, "Lamp", 100, 100);
      displayer.display(catImg, "Chat", 450, 100);

   }

}
