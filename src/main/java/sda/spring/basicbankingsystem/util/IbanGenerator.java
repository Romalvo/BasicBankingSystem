package sda.spring.basicbankingsystem.util;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Component;

@Component
public class IbanGenerator {

    public Iban generate(CountryCode countryCode, String accountNumber) {
        // Generate random IBAN

        return new Iban.Builder()
                .countryCode(countryCode)
                .bankCode("1000000")
                .branchCode("100")
                .accountNumber(accountNumber)
                .build();
    }
}
