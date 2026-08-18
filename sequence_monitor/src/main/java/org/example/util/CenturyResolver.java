package org.example.util;

import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class CenturyResolver {

    public int centuryResolver(int twoDigitYear) {

        validatedYear(twoDigitYear);
        int currentYear = Year.now().getValue();
        int currentTwoDigitYear = currentYear % 100;
        if (twoDigitYear <= currentTwoDigitYear){
            return 2000 + twoDigitYear;
        }
        return 1900 + twoDigitYear;
    }

    private void validatedYear(int twoDigitYear){
        if (twoDigitYear < 0 || twoDigitYear > 99){
            throw new IllegalArgumentException(
                    "Two-digit year must be between 00 and 99"
            );
        }
    }
}