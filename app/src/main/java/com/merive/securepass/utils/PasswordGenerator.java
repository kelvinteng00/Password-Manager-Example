package com.merive.securepass.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {

    final int lowerLength;
    final int upperLength;
    final int numberLength;
    final int symbolsLength;

    final String[] lowerAlf = "abcdefghijklmnopqrstuvwxyz".split("");
    final String[] upperAlf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    final String[] numberAlf = "0123456789".split("");
    final String[] symbolsAlf = "!#$%&'()*+`-_@^/:;<>=?,[]\\{}|~.".split("");

    public PasswordGenerator(int length) {
        /* Set count of symbols */
        if (length % 2 != 0) length--;
        lowerLength = (int) Math.round(0.4 * length);
        upperLength = (int) Math.round(0.4 * length);
        numberLength = (int) Math.round(0.1 * length);
        symbolsLength = (int) Math.round(0.1 * length) + (length % 2 == 0 ? 0 : 1);
    }

    public String generatePassword() {
        /* Generate shuffled password */
        return shuffle(generateAlfsString());
    }

    public String generateAlfString(String[] alf, int length) {
        /* Generate alphabet */
        StringBuilder string = new StringBuilder();
        while (string.length() != length)
            string.append(alf[new Random().nextInt(alf.length)]);

        return string.toString();
    }

    public String generateAlfsString() {
        /* Generate full alphabet string  */
        return generateAlfString(lowerAlf, lowerLength)
                + generateAlfString(upperAlf, upperLength)
                + generateAlfString(numberAlf, numberLength)
                + generateAlfString(symbolsAlf, symbolsLength);
    }

    public String shuffle(String string) {
        /* Shuffle symbols in string */
        List<Character> characters = new ArrayList<>();
        for (char c : string.toCharArray()) characters.add(c);
        StringBuilder output = new StringBuilder(string.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}
