package in.fssa.sundaratravels.util;

import java.util.Random;

public class RandomStringGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;

    public static String generateRandomString() {
        StringBuilder randomString = new StringBuilder(LENGTH);
        Random random = new Random();

        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            randomString.append(CHARACTERS.charAt(index));
        }

        return randomString.toString();
    }
}
