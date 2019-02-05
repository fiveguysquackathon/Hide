import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Vernam {

	/**
	 * Encrypts a string using the vernam cypher.
	 *
	 * @param text String to encrypt
	 * @return Array containing ciphertext [0] and key [1]
	 */
	String[] encrypt(String text) {
		List<String> asciiList = new ArrayList<>();
		List<String> shiftList = new ArrayList<>();
		for (int i = 0; i < text.length(); i++) {
			char character = text.charAt(i);
			int ascii = (int) character;
			int[] shiftAndVal = genChar(ascii);

			shiftList.add(Integer.toString(shiftAndVal[0]));
			char newCharacter = (char) shiftAndVal[1];
			asciiList.add(Character.toString(newCharacter));
		}
		String[] asciiArrary = asciiList.toArray(new String[0]);

//        System.out.println("Ascii list");
//        for(int i = 0; i<asciiList.size(); i++) {
//        	 System.out.print(asciiList.get(i));
//        }
//        System.out.println("");
//        System.out.println("end of array");

		return new String[] { String.join("", asciiArrary), String.join(",", shiftList.toArray(new String[0])) };
	}

	private int[] genChar(int val) {
		int shift = 0;
		int newVal = 0;
		boolean valid = false;
		while (!valid) {
			newVal = 0;
			shift = genShift(val);
			newVal = shift + val;
			if (32 <= newVal && newVal < 127 && newVal != 92) {
				valid = true;
			}
		}
		return new int[] { shift, newVal };
	}

	/**
	 * Generates a number to shift the ascii value by.
	 *
	 * @param val int representing ascii value of character
	 * @return int to shift by
	 */
	private int genShift(int val) {
		Random random = new Random();
		return random.ints(0 - val, (128 - val)).limit(1).findFirst().getAsInt();
	}

	/**
	 * Decrypts given ciphertext using given key
	 *
	 * @param cipher Array containing ciphertext [0] and decryption key [1] as
	 *               strings
	 * @return String of decrypted text
	 */
	String decrypt(int[] text, int[] key) {

		int[] cipherArray = new int[text.length];
//		System.out.println("CipherArray:");
		for (int i = 0; i < text.length; i++) {
			cipherArray[i] = text[i];
//			System.out.print(cipherArray[i] + " ");
		}
		System.out.println("");
		int[] keyVals = new int[key.length];
//		System.out.println("keyArray:");
		for (int i = 0; i < key.length; i++) {
			keyVals[i] = key[i];
//			System.out.print(keyVals[i] + " ");
		}
		List<String> decryptedText = new ArrayList<>();
		String characterToDecrypt;
		String finalOutput = "";
		int asciiOfCTD;
		int shift;

//		System.out.println("");
//		System.out.println("Output: \n\n\n");

		for (int i = 0; i < cipherArray.length; i++) {

			asciiOfCTD = cipherArray[i];

			shift = keyVals[i];

//			System.out.print(i + ": " + asciiOfCTD + "-" + shift + "|");

			int newVal = asciiOfCTD - shift;
			char newChar = (char) newVal;

			// decryptedText.add(Character.toString(newVal));

			finalOutput += newChar;
		}
		return finalOutput;
	}

}
