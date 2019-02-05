import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;
import java.util.Scanner;

public class ImageDescrypting {

	public static BufferedImage imageDescryptRead() throws IOException {

		BufferedImage imagex = null;

		try {

			File input_file = new File("./media/2.png"); // image file path

			// Reading input file
			imagex = ImageIO.read(input_file);

			System.out.println("Reading complete.");
		}

		catch (IOException e) {
			System.out.println("Error: " + e);
		}

		return imagex;

	}

	public int[] ImageDescrypting(String key) throws IOException {

		BufferedImage imagex = ImageDescrypting.imageDescryptRead();

		// splits the string and adds it into a array of strings
		String[] parts = key.split(",");

		// the number of full random pixels and not full random pixels
		int fullRandom = Integer.parseInt(parts[parts.length - 2]);
		int NOTfullRandom = Integer.parseInt(parts[parts.length - 1]);

		// how many random pixels there are
		int randomPixels = fullRandom;
		if (NOTfullRandom != 0) {
			randomPixels++;
		}

		
//		

		System.out.println("ID line 51");
		System.out.println(randomPixels);
		int[] x = new int[randomPixels];
		int[] y = new int[randomPixels];

		// -------------------------------------------------------------------------//

		// save the coordinates of the random pixels

		int counter = 0;
		for (int i = 0; i < randomPixels; i++) {
			x[i] = Integer.parseInt(parts[counter]);
			counter = counter + 2;
		}

		counter = 1;
		for (int i = 0; i < randomPixels; i++) {
			y[i] = Integer.parseInt(parts[counter]);
			counter = counter + 2;
		}

		// -------------------------------------------------------------------------//

		// number of pixels
		int pixelsQ = fullRandom * 4 + NOTfullRandom;
		int[] pixels = new int[pixelsQ];

		// decryptes the pixels

		// full random pixels ()
		int count = 0;
		for (int i = 0; i < fullRandom; i++) {
			pixels[count] = imagex.getRGB(x[i], y[i] - 1);
			pixels[count + 1] = imagex.getRGB(x[i] + 1, y[i]);
			pixels[count + 2] = imagex.getRGB(x[i], y[i] + 1);
			pixels[count + 3] = imagex.getRGB(x[i] - 1, y[i]);
			count = count + 4;
		}

		// NOT full random pixels

		if (NOTfullRandom != 0) {
			if (NOTfullRandom == 1) {
				pixels[count] = imagex.getRGB(x[randomPixels - 1], y[randomPixels - 1] - 1);
			}
			if (NOTfullRandom == 2) {
				pixels[count] = imagex.getRGB(x[randomPixels - 1], y[randomPixels - 1] - 1);
				pixels[count + 1] = imagex.getRGB(x[randomPixels - 1] + 1, y[randomPixels - 1]);
			}
			if (NOTfullRandom == 3) {
				pixels[count] = imagex.getRGB(x[randomPixels - 1], y[randomPixels - 1] - 1);
				pixels[count + 1] = imagex.getRGB(x[randomPixels - 1] + 1, y[randomPixels - 1]);
				pixels[count + 2] = imagex.getRGB(x[randomPixels - 1], y[randomPixels - 1] + 1);
			}
		}
		System.out.println("Decrypting line 113 ");
		for (int i = 0; i < pixels.length; i++) {
			System.out.print(pixels[i] + ",");
		}

		return pixels;

	}
}
