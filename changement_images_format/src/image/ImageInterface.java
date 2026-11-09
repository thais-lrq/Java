package image;

/**
 * The interface ImageInterface
 */
public interface ImageInterface {
	int getWidth();

	int getHeight();

	Pixel getPixel(int x, int y) throws UnknownPixelException;

}