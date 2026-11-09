package image;

import image.color.GrayColor;

public class testImage {

    public static void main(String[] args) throws UnknownPixelException {
        image.Image img = new image.Image(2, 2);
        assert (img.getHeight() == 2);
        assert (img.getWidth() == 2);

        Pixel p = img.getPixel(0, 0);
        assert (p.getColor().equals(GrayColor.WHITE));
        img.changeColorPixel(1, 1, GrayColor.BLACK);
        assert (img.getPixel(1, 1).getColor().equals(GrayColor.BLACK));

        testNegativeMethod();

    }

    public static void testNegativeMethod() throws UnknownPixelException {
        Image originalImage = new Image(2, 1);
        originalImage.changeColorPixel(0, 0, GrayColor.WHITE);
        originalImage.changeColorPixel(1, 0, new GrayColor(100));
        Image negativeImage = originalImage.negative();
        GrayColor expectedColor1 = GrayColor.BLACK;
        GrayColor actualColor1 = (GrayColor) negativeImage.getPixel(0, 0).getColor();
        assert (actualColor1.equals(expectedColor1)) : "Echec du test de negative() pour le Blanc";

        GrayColor expectedColor2 = new GrayColor(155);
        GrayColor actualColor2 = (GrayColor) negativeImage.getPixel(1, 0).getColor();
        assert (actualColor2.equals(expectedColor2)) : "Echec du test de negative() pour le Gris 100";

        assert (originalImage.getPixel(0, 0).getColor().equals(GrayColor.WHITE)) : "L'image originale a été modifiée";

        System.out.println("Tous les tests de negative() sont passés");
    }

}
