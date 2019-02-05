import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

	private Vernam vernam;
	private TextToRGBA textToRGBA;
	private PhotoManipulation pm;
	private ImageEncrypting ie;
	private ImageDescrypting id;
	private RGBAToText rtt;

	int[] textRGB;
	int[] keyRGB;
	String[] cipherAndKey;

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.display();
		menu.decryption();
//        if(menu.password()) {
//            menu.display();
//        } else {
//            System.out.println("Password Incorrect");
//        }

	}

	public boolean password() {
		FileReader fReader;
		BufferedReader bReader;
		String textInput;
		try {
			// Getting the password from the file
			fReader = new FileReader("data/password.txt");
			bReader = new BufferedReader(fReader);
			textInput = bReader.readLine();
			// Makes sure the password is not null
			if (bReader.readLine() != null) {
				textInput = bReader.readLine();
			}

			// Allows the user to input validation
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);

			System.out.println("Please enter the password to begin the program:");

			String input = scanner.nextLine();
			int password = input.hashCode();
			if (Integer.parseInt(textInput) == password) {
				return true;
			} else {

			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			System.out.println("There was not a valid password found. Call 0124710240 for Tech Support!");
		}

		return false;

	}

	public void display() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the data you would like to hide (max 150 characters)");
		System.out.println("If you would like to exit the program, please type 'exit'");

		String data = scanner.nextLine();

		do {
			if (data.length() > 150) {
				System.out.println("The message was too long, try another:");
			} else if (!data.equals("exit")) {
				System.out.println("Encrypting... " + data);
				encryption(data);
				data = scanner.nextLine();

			} else {
				data = scanner.nextLine();
			}

		} while (!data.equals("exit"));
		scanner.close();
		return;
	}

	public void encryption(String data) {

//    	GETTING EVERYTHING FROM NICK

		vernam = new Vernam();
		textToRGBA = new TextToRGBA();
		cipherAndKey = vernam.encrypt(data);
//        System.out.println(cipherAndKey[0]);
//        System.out.println(cipherAndKey[1]);

		System.out.println("Cipher text " + cipherAndKey[0]);
		System.out.println("Cipher key " + cipherAndKey[1]);

//        GETTING EVERYTHING FROM MAX

		textRGB = textToRGBA.getTextRGB(cipherAndKey[0]);
		keyRGB = textToRGBA.getKeyRGB(cipherAndKey[1]);

//		System.out.println("\n Line 113");
//		System.out.print("text: ");
//		for(int i = 0; i<textRGB.length; i++) {
//			System.out.print(textRGB[i] + " ");
//		}
//		System.out.println("\n Line 118");
//		System.out.print("Key: ");
//		for(int i = 0; i<keyRGB.length; i++) {
//			System.out.print(keyRGB[i] + " ");
//		}
//		System.out.println("");

//        finding the length of key rgb
//		int tempLength = keyRGB.length;
//		System.out.println("Key length " + tempLength);

//        System.out.println("");

//		for (int i = 0; i < textRGB.length; i++) {
//			System.out.println(textRGB[i]);
//		}
//		for (int i = 0; i < keyRGB.length; i++) {
//			System.out.println(keyRGB[i]);
//		}

//        GETTING EVERYTHING FROM ELLIE
		ie = new ImageEncrypting();
		String key = "";
		try {
			key = ie.encryptPixels(textRGB);
		} catch (IOException e) {
			System.out.println("lmao sorry fella ya done fucked it");
		}

//        GETTING EVERYTHING TO BRANDON

		pm = new PhotoManipulation();
		pm.openFile();
		// encode the key from Max for Nick's
		pm.encodeKey(keyRGB);
		// encode the coordintes from Ellie's method
		pm.encodeCoord(key);
		// writing to the image
		pm.writeToFile();
		System.out.println("That works!");
	}

	private void decryption() {
		// creating objects
		vernam = new Vernam();
		pm = new PhotoManipulation();
		id = new ImageDescrypting();
		rtt = new RGBAToText();

		// opens the image
		pm.openFile();
		// gets the coord back to an array
		int[] coords = pm.decodeCoord();
		// gets the cord back into a string format with commas
		String textCoords = pm.returnCoord(coords);
		System.out.println("Text coord test:" + textCoords);

		int[] ctext;

		try {
			// gets the pixels in an arrays of ints
			ctext = id.ImageDescrypting(textCoords);

			// gets the key back from the image and stores it into an array
			int[] keyArray = pm.decodeKey();
			System.out.println("\nLine 184:");
			for (int i = 0; i < keyArray.length; i++) {
				System.out.print(keyArray[i] + " ");
			}
			System.out.println("\n--------");

//			 int[] key = pm.returnText(keyArray);
//			 System.out.println("Line 191:");
//			 for(int i = 0; i<key.length; i++) {
//				 System.out.print(key[i]+ " ");
//			 }
			int[] decodedKey = rtt.getKey(keyArray);
			System.out.println("\nDecoded key length: " + decodedKey.length);
			System.out.println("Line 197:");
			for (int i = 0; i < decodedKey.length; i++) {
				System.out.print(decodedKey[i] + " ");
			}

			int[] cipherText = rtt.getText(ctext);
//			System.out.println("Ciphered text");

//            cipherAndKey[0] = "";
//            cipherAndKey[1] = "";
//            for (int p = 0; p < cipherText.length; p++) {
//            	cipherAndKey[0] += cipherText[p];
//            }
			// for (int v = 0; v < decodedKey.length; v++) {
			// cipherAndKey[1] += decodedKey[v];
			// }

//            System.out.println("Cipher 0: "+ cipherAndKey[0] + " Cipher 1: "+ cipherAndKey[1]);
//            System.out.println("\nTHAT KEY DOE " + cipherAndKey[1]+" and it is this long ;)" + cipherAndKey[1].length() + " \n");
			String dataOutput = "";
			dataOutput = vernam.decrypt(cipherText, decodedKey);

//			for (int i = 0; i < cipherText.length; i++) {
//				String strOut = cipherText[i];
//				int intOut = Integer.parseInt(strOut);
//				char charOut = (char) intOut;
//				dataOutput += charOut;
//			}

			System.out.println("By decrypting, we got: " + dataOutput);
		} catch (IOException e) {
			System.out.println("lmao sorry fella ya done fucked it");
		}

	}
}