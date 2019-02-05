
//import java.awt.Color;
import java.awt.Color;

import org.apache.commons.lang3.ArrayUtils;

public class TextToRGBA {
	String cipherText, key;

	public int[] getTextRGB(String encryptionArray) {

		cipherText = encryptionArray;
		char[] characterArray = splitText();
		int[] intArray = convertTextToInt(characterArray);
//		System.out.println("TTR line 15");
//		for(int i = 0; i< intArray.length;i++) {
//			System.out.print(intArray[i] + " ");
//		}
//		System.out.println("");
//		PhotoManipulation pm = new PhotoManipulation();
		int[] textRGBArray = genAllText(intArray);

//		for(int i = 0; i<textRGBArray.length;i++) {
//			System.out.println("\n"+i);
//			pm.RGBSeparate(textRGBArray[i],0);
//		}

		return textRGBArray;
	}

	public int[] getKeyRGB(String encryptionArray) {
		key = encryptionArray;
		String[] keyArray = key.split(",");
//		System.out.println("TTR line 23");
//		for (int i = 0; i < keyArray.length; i++) {
//			System.out.print(keyArray[i] + ",");
//		}
//		System.out.println("\n--------");
		// Convert keyArray to IntArray

		int[] intArray = convertKeyToInt(keyArray);

		// Generates pixels
		int[] keyRGBArray = genAllKey(intArray);

		return keyRGBArray;
	}

	/** Splits the private key into an array of characters */
	public char[] splitText() {
		int size = cipherText.length();
		char[] characterArray = cipherText.toCharArray();

		return characterArray;
	}

	/** Converts text characters into integer array */
	public int[] convertTextToInt(char[] characterArray) {
		int size = characterArray.length;
		int[] intArray = new int[size];
		for (int i = 0; i < size; i++) {
			intArray[i] = (int) characterArray[i];
		}
		return intArray;
	}

	public int[] oldGenAll(int[] intArray) {
		int size = intArray.length;
		int[] pixelArray = genPixel(intArray[0]);
		for (int i = 1; i < size; i++) {
			int[] onePixelArray = genPixel(intArray[i]);
			pixelArray = ArrayUtils.addAll(pixelArray, onePixelArray);
		}
		return pixelArray;
	}

	public int[] genPixel(int ascii) {
		int red = ascii;
		int green_prov = 128 - ascii;
		int green;
		int blue = 256 - ascii;
		int opacity = 1;

		if (green_prov < 0) {
			green = green_prov * -1;
		} else {
			green = green_prov;
		}

		int onePixelArray[] = { red, green, blue, opacity };
		return onePixelArray;
	}

	public int[] genAllText(int[] intArray) {
		int size = intArray.length;
		int[] pixelArray = new int[0];
		for (int i = 0; i < size; i++) {
			int[] onePixelArray = genPixel(intArray[i]);
			int[] rgb = { new Color(onePixelArray[0], onePixelArray[1], onePixelArray[2]).getRGB() };
			pixelArray = ArrayUtils.addAll(pixelArray, rgb);
		}
		return pixelArray;
	}

	/** Converts key string array into integer array */
	public int[] convertKeyToInt(String[] stringArray) {
		int size = stringArray.length;
		int[] intArray = new int[size];
		System.out.print(size + "\n");
		for (int i = 0; i < size; i++) {
			System.out.print(stringArray[i] + " ");
			intArray[i] = Integer.parseInt(stringArray[i]);
		}
		return intArray;
	}

	/** Generates all the pixels based on */
	public int[] genAllKey(int[] intArray) {
		// Creates blank array for pixels
		int[] exportArray = new int[0];
		// Gets size of the array
		int size = intArray.length;
		// Goes through each integer
		for (int i = 0; i < size; i++) {
			int currentInt = intArray[i];
			// Turns -127 to 127 into 1 to 255
			int optimizedInt = currentInt + 128;
			// Generates appropriate values for pixel
			int[] onePixelArray = genPixel(optimizedInt);
			// Adds pixel to the main array
			exportArray = ArrayUtils.addAll(exportArray, onePixelArray);
		}
		return exportArray;
	}
}
