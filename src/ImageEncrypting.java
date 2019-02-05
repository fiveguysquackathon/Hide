import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class ImageEncrypting {

	public static BufferedImage imageRead() throws IOException {

		BufferedImage image = null;

		try {

			File input_file = new File("./media/1.png"); // image file path

			// Reading input file
			image = ImageIO.read(input_file);

			System.out.println("Reading complete.");
		}

		catch (IOException e) {
			System.out.println("Error: " + e);
		}

		return image;

	}

	// ---------------------------------------------------------------------------------------------------------------------//

	public void writeImage(BufferedImage image) throws IOException {

		// WRITE IMAGE

		try {
			// Output file path
			File output_file = new File("./media/2.png");

			// Writing to file taking type and path as
			ImageIO.write(image, "png", output_file);

			System.out.println("Writing complete.");
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------//

	// https://www.mkyong.com/java/java-generate-random-integers-in-a-range/ //
	public int getRandomNumber(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException(" Maximum must be greater than the minimum");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;

	}

	// ---------------------------------------------------------------------------------------------------------------------//

	public String encryptPixels(int[] pixels) throws IOException {

		int length = pixels.length;
		System.out.println("EncPix line 79: ");
		for (int i = 0; i < length; i++) {
			System.out.print(pixels[i] + ",");
		}

		// how many random pixels will be needed
		int randomPixels = 0;
		randomPixels = length / 4;
		if (length % 4 != 0)
			randomPixels++;

		BufferedImage image = imageRead();

		// storing the place of the random pixels
		int[] x = new int[randomPixels];
		int[] y = new int[randomPixels];

		// storing width and height of the image
		int width = image.getWidth();
		int height = image.getHeight();

		// generates the places of the random pixels
		for (int i = 0; i < randomPixels; i++) {
			x[i] = getRandomNumber(3, width - 4);
			y[i] = getRandomNumber(1, height - 1);
		}

		// encryptes the pixels

		// full random pixels ()
		int count = 0;
		if (length / 4 != 0) {
			for (int i = 0; i < length / 4; i++) {
				image.setRGB(x[i], y[i] - 1, pixels[count]);
				image.setRGB(x[i] + 1, y[i], pixels[count + 1]);
				image.setRGB(x[i], y[i] + 1, pixels[count + 2]);
				image.setRGB(x[i] - 1, y[i], pixels[count + 3]);
				count = count + 4;
			}
		}

		// NOT full random pixels

		if (length % 4 != 0) {
			if (length % 4 == 1) {
				image.setRGB(x[randomPixels - 1], y[randomPixels - 1] - 1, pixels[count]);
			}
			if (length % 4 == 2) {
				image.setRGB(x[randomPixels - 1], y[randomPixels - 1] - 1, pixels[count]);
				image.setRGB(x[randomPixels - 1] + 1, y[randomPixels - 1], pixels[count + 1]);
			}
			if (length % 4 == 3) {
				image.setRGB(x[randomPixels - 1], y[randomPixels - 1] - 1, pixels[count]);
				image.setRGB(x[randomPixels - 1] + 1, y[randomPixels - 1], pixels[count + 1]);
				image.setRGB(x[randomPixels - 1], y[randomPixels - 1] + 1, pixels[count + 2]);
			}
		}

		// write the image
		writeImage(image);

		// figure out the numeric code as an output
		String key = "";

		for (int i = 0; i < randomPixels; i++) {
			key = key + x[i] + "," + y[i] + ",";
		}

		key = key + length / 4 + "," + length % 4;

		System.out.println(key + "\n");

		return key;

	}

	// ---------------------------------------------------------------------------------------------------------------------//

}
