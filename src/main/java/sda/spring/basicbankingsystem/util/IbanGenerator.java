package sda.spring.basicbankingsystem.util;

import java.math.BigInteger;
import java.util.Random;

import org.springframework.stereotype.Component;


@Component
public class IbanGenerator {

    private static final String ESTONIA_CODE = "EE";

    private static final Random random = new Random();

    //Method to generate an IBAN based on country code

    public String generateIban(String countryCode) {
        switch (countryCode) {
            case "EE":
                return generateEstoniaIban();
                default:
                    throw new IllegalArgumentException("Unsupported country code: " + countryCode);
        }
    }

    // Generate Estonia IBAN
    private String generateEstoniaIban() {
        String bankCode = generateRandomDigits(4);
        String accountNumber = generateRandomDigits(14);
        String iban = bankCode + accountNumber;
        String checkDigits = calculateCheckDigits(ESTONIA_CODE + "00" + iban);
        return ESTONIA_CODE + checkDigits + " " + formatIban(iban);
    }

    // Method to generate random digits of specified length
    private String generateRandomDigits(int length) {
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < length; i++) {
            digits.append(random.nextInt(10));
        }
        return digits.toString();
    }

    // Calculate check digits using the MOD97 algorithm
    private String calculateCheckDigits(String iban) {
        //Rearrange and convert numeric
        String rearranged = iban.substring(4) + convertlettersToNumbers(iban.substring(0,4) + "00");

        // Use BigInteger to handle large numbers
        BigInteger checkSum = new BigInteger(rearranged).mod(BigInteger.valueOf(97));
        int checkDigits = 98 - checkSum.intValue();

        return String.format("%02d", checkDigits);
    }

    private String convertlettersToNumbers(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                //convert A-Z to 10-35
                int value = Character.toUpperCase(c) - 'A' +10;
                result.append(value);
            }else {
                result.append(c);
                }

        }
        return result.toString();
    }
    //Format IBAN for display (optional, removes spaces)
    private String formatIban(String iban) {
        return iban.replaceAll("(.{4})(?=.)", "$1 "); // Adds space every 4 characters
    }

}
