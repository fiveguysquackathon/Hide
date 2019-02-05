import java.awt.Color;

import org.apache.commons.lang3.ArrayUtils;

public class RGBAToText {
//	String[][] textRGB = new String[][]{
//		  {"255","255","255","1"},
//		  {"60","255","255","1"},
//		  {"255","255","255","1"},
//		  {"255","255","255","1"},
//		  {"255","255","255","1"},
//		};
//		
//	String[][] keyRGB = new String[][]{
//		  {"255","255","255","1"},
//		  {"60","255","255","1"},
//		  {"255","255","255","1"},
//		  {"255","255","255","1"},
//		  {"255","255","255","1"},
//		};

	public int[] getText(int[] textRGBInt) {
		int size = textRGBInt.length;

//		System.out.println("Size:" + size);
		int[] intArray = new int[textRGBInt.length];

//		for (int i =0; i< size; i++) {
//			textArray[i] = (textRGBInt[i]) + ",";
//		}
		PhotoManipulation pm = new PhotoManipulation();
		System.out.println("RTT line 28");
		try {
			for (int i = 0; i < size; i++) {
			
				System.out.println("\n" + i);
				pm.RGBSeparate(textRGBInt[i], 0);
				Color tempColor = new Color(textRGBInt[i]);
				
				int red = tempColor.getRed();

				intArray[i] = red;

				System.out.print( " " + red + " ");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("\n " + e);
		}

		// String text = String.join("", textArray);
		return intArray;

	}

	public int[] getKey(int[] keyRGB) {
		int size = keyRGB.length;
		int[] keyArray = new int[keyRGB.length / 4];

		int counter = 0;
//		System.out.println("Key out");
		for (int i = 0; i < size; i += 4) {
			int ActualInt = 0;

			int tempInt = (keyRGB[i]);
//				System.out.print(tempInt + " ");
			ActualInt = tempInt - 128;
			keyArray[counter] = ActualInt;
			counter++;

//			System.out.print(ActualInt + " ");
		}

//			char character = (char)actualInt;
//			if(i+1 == size) {
//				String characterStr = String.valueOf(character);
//				keyArray[i]=characterStr;
//			}else {
//			String characterStr = String.valueOf(character);

//		for (int i=0;i<size;i++) {
//			int red = keyRGB[i];
////		int optimizedInt = Integer.parseInt(red);
//			int actualInt = red - 128;
//			keyArray = ArrayUtils.addAll(keyArray, characterStr);
//		}
//		for(int i = 0; i<size; i++) {
//			System.out.print(keyArray[i] + " ");
//		}
//		System.out.println("I just printed his key lol... it is this many characters long :D " + keyArray.length);
		// String key = String.join(",", keyArray);
		return keyArray;
	}
}
