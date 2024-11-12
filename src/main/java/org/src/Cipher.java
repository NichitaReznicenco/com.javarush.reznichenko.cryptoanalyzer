package org.src;

import java.util.Arrays;
import java.util.List;

public class Cipher {
    private static final Character[] ALPH = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я'
    };

    private static final List<Character> ALPHABET = Arrays.asList(ALPH);
    private static final List<Character> SYMBOLS = Arrays.asList('.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ');

    public static String encrypt(String text, int shift) {
        char[] textEncrypt = text.toCharArray();

        for (int i = 0; i < textEncrypt.length; i++) {
            char currentChar = textEncrypt[i];

            if (Character.isLetter(currentChar)) {
                int currentIndex = ALPHABET.indexOf(Character.toLowerCase(currentChar));
                if (currentIndex != -1) {
                    int newIndex = (currentIndex + shift) % ALPHABET.size();

                    if (newIndex < 0) {
                        newIndex += ALPHABET.size();
                    }

                    char newChar = ALPHABET.get(newIndex);
                    textEncrypt[i] = Character.isUpperCase(currentChar) ? Character.toUpperCase(newChar) : newChar;
                }
            }
            else if (SYMBOLS.contains(currentChar)) {
                int currentIndex = SYMBOLS.indexOf(currentChar);
                int newIndex = (currentIndex + shift) % SYMBOLS.size();

                if (newIndex < 0) {
                    newIndex += SYMBOLS.size();
                }

                textEncrypt[i] = SYMBOLS.get(newIndex);
            }
        }

        return new String(textEncrypt);
    }

    public static String decrypt(String encryptedText, int shift) {
        char[] textDecrypt = encryptedText.toCharArray();

        for (int i = 0; i < textDecrypt.length; i++) {
            char currentChar = textDecrypt[i];

            if (Character.isLetter(currentChar)) {
                int currentIndex = ALPHABET.indexOf(Character.toLowerCase(currentChar));
                if (currentIndex != -1) {
                    int newIndex = (currentIndex - shift) % ALPHABET.size();

                    if (newIndex < 0) {
                        newIndex += ALPHABET.size();
                    }

                    char newChar = ALPHABET.get(newIndex);
                    textDecrypt[i] = Character.isUpperCase(currentChar) ? Character.toUpperCase(newChar) : newChar;
                }
            }
            else if (SYMBOLS.contains(currentChar)) {
                int currentIndex = SYMBOLS.indexOf(currentChar);
                int newIndex = (currentIndex - shift) % SYMBOLS.size();

                if (newIndex < 0) {
                    newIndex += SYMBOLS.size();
                }

                textDecrypt[i] = SYMBOLS.get(newIndex);
            }
        }

        return new String(textDecrypt);
    }
}
