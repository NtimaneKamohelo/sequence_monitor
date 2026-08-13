package org.example.util;

import java.time.Year;

public class CenturyResolver {

    public int resolveCentury(int year) {

        int currentYear = Year.now().getValue() % 100;

        return year <= currentYear
                ? 2000 + year
                : 1900 + year;
    }
}