package in.fssa.sundaratravels.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BusNumberGenerator {

    private static final Set<String> generatedBusNumbers = new HashSet<>();

    public static String generateRandomBusNumber() {
        //String prefix = generateRandomPrefix();
        String prefix = "TN";
        String numericPart = generateRandomNumericPart();
        String suffix = generateRandomSuffix();

        String busNumber = prefix + numericPart + suffix;

        // Check if the generated bus number has already been generated
        while (generatedBusNumbers.contains(busNumber)) {
//            prefix = generateRandomPrefix();
            prefix = "TN";
            numericPart = generateRandomNumericPart();
            suffix = generateRandomSuffix();
            busNumber = prefix + numericPart + suffix;
        }

        // Add the generated bus number to the set
        generatedBusNumbers.add(busNumber);

        return busNumber;
    }

    private static String generateRandomPrefix() {
        Random random = new Random();
        char[] prefixChars = new char[2];

        for (int i = 0; i < 2; i++) {
            int ascii = random.nextInt(26) + 65; // A-Z
            prefixChars[i] = (char) ascii;
        }

        return new String(prefixChars);
    }

    private static String generateRandomNumericPart() {
        Random random = new Random();
        int numericPart = random.nextInt(100);

        return String.format("%02d", numericPart);
    }

    private static String generateRandomSuffix() {
        Random random = new Random();
        char[] suffixChars = new char[random.nextInt(2) + 1];

        for (int i = 0; i < suffixChars.length; i++) {
            int ascii = random.nextInt(26) + 65; // A-Z
            suffixChars[i] = (char) ascii;
        }

        int numericSuffix = random.nextInt(10000);
        String formattedNumericSuffix = String.format("%04d", numericSuffix);

        return new String(suffixChars) + formattedNumericSuffix;
    }

}
