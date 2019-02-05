import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PhotoManipulation {
	BufferedImage img = null;
	File input = null;
	File output = null;

	public void keyController(String Key, int[] nums) {
		openFile();
		encodeCoord(Key);
		encodeKey(nums);
		writeToFile();
	}

	public void openFile() {
		try {
			input = new File("./media/2.png");
			img = ImageIO.read(input);
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void writeToFile() {
		try {
			output = new File("./media/2.png");
			ImageIO.write(img, "png", output);
		} catch (IOException t) {
			System.out.println(t);
		}
	}
	
	public void writeToFile2() {
		try {
			output = new File("./media/3.png");
			ImageIO.write(img, "png", output);
		} catch (IOException t) {
			System.out.println(t);
		}
	}

	public int getWidth() {
		return img.getWidth();

	}

	public int getHeight() {
		return img.getHeight();
	}

	public int RGB_INT(int r, int g, int b) {
		Color tempColor = new Color(r, g, b);
		int rgb = tempColor.getRGB();
		return rgb;
	}

	public int RGBSeparate(int RGB, int type) {
		Color tempColor = new Color(RGB);
		int output = 0;
		output = tempColor.getRed();
//		System.out.print("Red: " + output + " ");
		output = tempColor.getGreen();
//		System.out.print("Green: " + output + " ");
		output = tempColor.getBlue();
//		System.out.print("Blue: " + output + " ");

		return output;
	}

	public int[] returnText(int[] encoded) {
		int[] temp = new int[encoded.length];
		for (int i = 0; i < encoded.length; i++) {
			temp[i] = RGBSeparate(encoded[i], 0);

		}

		return temp;

	}

	public String returnCoord(int[] encoded) {

		String temp = "";

		for (int i = 0; i < encoded.length; i++) {
			if (i + 1 == encoded.length) {
				temp = temp + Integer.toString(encoded[i]);
			} else {
				temp = temp + Integer.toString(encoded[i]) + ",";
			}

		}
		return temp;
	}

	public void changePixel(int rgb, int x, int y) {
		img.setRGB(x, y, rgb);
	}

	public void printVals() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(getPixel(j, i) + " ");
			}
			System.out.println("");
		}
	}

	public void encodeCoord(String Key) {
		String[] splitKey = Key.split(",");
		int temp = splitKey.length;
		changePixel(temp, 0, 0);
		for (int i = 1; i < (splitKey.length) + 1; i++) {
			changePixel(Integer.parseInt(splitKey[i - 1]), i, 0);
		}
	}

	public void encodeKey(int[] Key) {
		int temp = Key.length;
//		System.out.println("Length of Key[] before it has been encoded: "+temp);
		changePixel(temp, 0, img.getHeight() - 1);
//		System.out.println("GetPixels: " + getPixel(0, img.getHeight()-1));
		for (int j = 1; j < Key.length + 1; j++) {
//			System.out.print(Key[j-1] + " ");
			changePixel(Key[j - 1], j, img.getHeight() - 1);
		}
//		System.out.println("GetPixels: " + getPixel(0, img.getHeight()-1));

	}

	public int[] decodeKey() {

		int length = (img.getRGB(0, img.getHeight() - 1));
		System.out.println("length is " + length);
		int[] temp = new int[length];
		System.out.println("\n PhotoMan line 124");
		for (int i = 1; i < length + 1; i++) {
			temp[i - 1] = img.getRGB(i, img.getHeight() - 1);
			System.out.print(temp[i - 1] + " ");

		}
//		System.out.println("but brando's key is only this long?: " + temp.length);
		return temp;
	}

	public int[] decodeCoord() {
		int length = img.getRGB(0, 0);
//		System.out.println(length);
		int[] temp = new int[length];
		for (int i = 1; i < length + 1; i++) {
			temp[i - 1] = img.getRGB(i, 0);

		}
		return temp;
	}

	public int getPixel(int x, int y) {

		return img.getRGB(x, y);

	}

	public void createImage(int[] input) {
		int width = img.getWidth();
		int height = img.getHeight();
		int counter = 0;
		int length = input.length;
		System.out.println(height + " " + width + " " + length);

		for (int i = 0; i < height; i++) {

			for (int j = 0; j < width; j++) {

				if (counter < length) {
					changePixel(input[counter], j, i);
					writeToFile();
					counter++;
				} else {
					changePixel(-000000000, j, i);
				}
				System.out.println(i + " " + j);
			}

		}

	}
}
