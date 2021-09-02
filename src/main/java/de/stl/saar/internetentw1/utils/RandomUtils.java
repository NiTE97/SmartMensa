package de.stl.saar.internetentw1.utils;

import java.util.Random;

/**
 * Erzeugt Zufallswerte.
 * @author christopher
 *
 */
public class RandomUtils {
	/**
	 * Ein Random-Objekt, das fuer die Erzeugung der Zufallswerte zustaendig ist.
	 */
	private static final Random random;
	/**
	 * Die Nummer des letzten Zeichens im Standard-ASCII.
	 */
	private static final int ASCII_END = 127;
	
	/**
	 * Initialisiert das Random-Objekt.
	 */
	static {
		random = new Random();
	}
	
	/**
	 * Erzeugt eine int-Zufallszahl von 0 bis zu einem Maximalwert.
	 * @param max Der Maximalwert.
	 * @return Die erzeugte Zufallszahl.
	 */
	public static int nextInt(final int max) {
		return random.nextInt(max);
	}
	
	/**
	 * Erzeugt eine long-Zufallszahl..
	 * @return Die erzeugte Zufallszahl.
	 */
	public static long nextLong() {
		return random.nextLong();
	}
	
	/**
	 * Erzeugt einen String, der beliebige Zeichen aus dem ASCII-Zeichensatz enthaelt.
	 * @param charCount Die Anzahl der Zeichen, die der String enthalten soll.
	 * @return Der String der Laenge charCount mit zufaellig gewaehlten Zeichen.
	 */
	public static String createStringWithRandomChars(final int charCount) {
		String stringWithRandomChars = "";
		int counter = 1;
		
		while (counter <= charCount) {
			final char randomChar = (char)nextInt(ASCII_END);
			if (randomChar >= 'A' && randomChar <= 'z') {
				stringWithRandomChars = stringWithRandomChars + randomChar;
				counter++;
			}
		}
		
		return stringWithRandomChars;
	}
}
