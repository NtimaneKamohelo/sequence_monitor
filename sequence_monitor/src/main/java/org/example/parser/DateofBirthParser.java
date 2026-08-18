package org.example.parser;

import org.example.util.CenturyResolver;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Extracts and validates the date of birth from the
 * first six digits of RSA ID number
 *
 * Structure: YYMMDD
 * example:   900315
 *
 * YY = 90
 * MM = 03
 * DD = 15
 *
 * 1990-03-15
 */
@Component
public class DateofBirthParser {

    private static final int DOB_START_INDEX = 0;
    private static final int DOB_END_INDEX = 6;
    private static final int YEAR_START_INDEX = 0;
    private static final int YEA_END_INDEX = 2;
    private static final int MONTH_START_INDEX = 2;
    private static final int MONTH_END_INDEX = 4;
    private static final int DAY_START_INDEX = 4;
    private static final int DAY_END_INDEX = 6;

    public final CenturyResolver centuryResolver;

    public DateofBirthParser(CenturyResolver centuryResolver){
        if (centuryResolver == null){
            throw new IllegalArgumentException("CenturyResolver cannot be null");
        }
        this.centuryResolver = centuryResolver;
    }

    /**
     * Extracts and Validates the date of birth
     *
     * @param idNumber 13-digit RSA ID
     * @return LocalDate representing the persons's date of birth
     */
    public LocalDate parse(String idNumber){
        validatedIdNumber(idNumber);

        String datePart = idNumber.substring(
                DOB_START_INDEX,
                DOB_END_INDEX
        );

        int twoDigitYear = Integer.parseInt(
                datePart.substring(
                        YEAR_START_INDEX,
                        YEA_END_INDEX
                )
        );

        int month = Integer.parseInt(
                datePart.substring(
                        MONTH_START_INDEX,
                        MONTH_END_INDEX
                )
        );

        int day = Integer.parseInt(
                datePart.substring(
                        DAY_START_INDEX,
                        DAY_END_INDEX
                )
        );

        int year = centuryResolver.centuryResolver(twoDigitYear);

        return createDate(
                year,
                month,
                day);
    }

    private LocalDate createDate(
            int year,
            int month,
            int day
    ){
        try {
            return LocalDate.of(
                    year,
                    month,
                    day
            );
        } catch (DateTimeException exception){
            throw new IllegalArgumentException(
                    toString().format(
                            "Invalid date of Birth: %04d-%02-",
                            year,
                            month,
                            day
                    ),
                    exception
            );
        }
    }

    private void validatedIdNumber(String idNumber){
        if (idNumber == null){
            throw new IllegalArgumentException(
                    "South African ID number cannot be null"
            );
        }

        if (!idNumber.matches("\\d{13}")){
            throw new IllegalArgumentException(
                    "South African ID number must contain exactly 13 digits"
            );
        }

    }
}
