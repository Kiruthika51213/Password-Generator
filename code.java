import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[{]}|;:',<.>/?";

    public static String generatePassword(int length, boolean useUpper, boolean useLower, boolean useDigits, boolean useSpecial) {
        StringBuilder characterPool = new StringBuilder();

        if (useUpper) characterPool.append(UPPER);
        if (useLower) characterPool.append(LOWER);
        if (useDigits) characterPool.append(DIGITS);
        if (useSpecial) characterPool.append(SPECIAL);

        if (characterPool.length() == 0) {
            throw new IllegalArgumentException("At least one character set must be selected.");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensures at least one character from each selected type
        if (useUpper) password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        if (useLower) password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        if (useDigits) password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        if (useSpecial) password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        while (password.length() < length) {
            int index = random.nextInt(characterPool.length());
            password.append(characterPool.charAt(index));
        }

        // Shuffle characters (basic shuffle logic)
        return shuffleString(password.toString(), random);
    }

    private static String shuffleString(String input, SecureRandom random) {
        char[] a = input.toCharArray();
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        return new String(a);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter password length: ");
        int length = scanner.nextInt();

        System.out.print("Include uppercase letters? (true/false): ");
        boolean useUpper = scanner.nextBoolean();

        System.out.print("Include lowercase letters? (true/false): ");
        boolean useLower = scanner.nextBoolean();

        System.out.print("Include numbers? (true/false): ");
        boolean useDigits = scanner.nextBoolean();

        System.out.print("Include special characters? (true/false): ");
        boolean useSpecial = scanner.nextBoolean();

        try {
            String password = generatePassword(length, useUpper, useLower, useDigits, useSpecial);
            System.out.println("Generated Password: " + password);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
