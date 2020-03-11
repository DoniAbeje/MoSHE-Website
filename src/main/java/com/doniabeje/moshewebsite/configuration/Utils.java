package com.doniabeje.moshewebsite.configuration;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Calendar;
import java.util.Locale;

public class Utils {

    public String name = "theasdfadsf";

    public static String minify(String content, int length) {
        if (content.length() <= length) {
            return content + "...";
        }

        return content.substring(0, length - 1) + "...";
    }

    public static int getCurrentYear() {
        Locale locale = LocaleContextHolder.getLocale();

        if (locale.getLanguage().equals("am")) {
            return getCurrentEthiopianYear();
        }
        return getCurrentGregorianYear();
    }

    public static int getCurrentGregorianYear() {
        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentEthiopianYear() {
//        if this year is Ethiopian leap year
//             if date == sept 11
//                    do sth
//             else
//                  7 year diff from sept 12 to dec 31
//                  8 year diff from jan 1 to sept 10

//        if last year was Ethiopian leap year
//            7 year diff from sept 12 to dec 31
//            8 year diff from jan 1 to sept 10

//        else
//             7 year diff from sept 11 to dec 31
//             8 year diff from jan 1 to sept 10

        Calendar today = Calendar.getInstance();
        Calendar sept10 = new Calendar.Builder()
                .set(Calendar.MONTH, Calendar.SEPTEMBER)
                .set(Calendar.DAY_OF_MONTH, 10)
                .build();

        Calendar sept11 = new Calendar.Builder()
                .set(Calendar.MONTH, Calendar.SEPTEMBER)
                .set(Calendar.DAY_OF_MONTH, 11)
                .build();

        Calendar sept12 = new Calendar.Builder()
                .set(Calendar.MONTH, Calendar.SEPTEMBER)
                .set(Calendar.DAY_OF_MONTH, 12)
                .build();

        Calendar dec31 = new Calendar.Builder()
                .set(Calendar.MONTH, Calendar.DECEMBER)
                .set(Calendar.DAY_OF_MONTH, 31)
                .build();

        Calendar jan1 = new Calendar.Builder()
                .set(Calendar.YEAR, today.get(Calendar.YEAR))
                .set(Calendar.MONTH, Calendar.DECEMBER)
                .set(Calendar.DAY_OF_MONTH, 31)
                .build();

        if (isEthiopianLeapYear()) {
            if (today.get(Calendar.MONTH) == Calendar.SEPTEMBER && today.get(Calendar.DAY_OF_MONTH) == 11) {
                return (getCurrentGregorianYear() + 1) % 4 == 0 ? getCurrentGregorianYear() - 8 : getCurrentGregorianYear() - 7;
            }

            if (isAfter(today, sept11) && isEqualOrBefore(today, dec31)) {
                return getCurrentGregorianYear() - 7;
            }

            return getCurrentGregorianYear() - 8;
        }

        if (wasLastYearEthiopianLeapYear()) {
            if (isEqualOrAfter(today, sept12) && isEqualOrBefore(today, dec31)) {
                return getCurrentGregorianYear() - 7;
            }

            if (isEqualOrAfter(today, jan1) && isEqualOrBefore(today, sept10)) {
                return getCurrentGregorianYear() - 8;
            }
        }

        if (isEqualOrAfter(today, sept11) && isEqualOrBefore(today, dec31)) {
            return getCurrentGregorianYear() - 7;
        }

        return getCurrentGregorianYear() - 8;

    }

    public static boolean isEthiopianLeapYear() {
//        2011 - 2018 sept 11 - 2019 sept 11
//        if (gregorian year + 2) % 4 == 0
//            if date >= sept 11
//                return true
//        if (gregorian year + 1) % 4 == 0
//            if date <= sept 11
//              return true

        Calendar today = Calendar.getInstance();
        Calendar sept11 = new Calendar.Builder()
                .set(Calendar.MONTH, Calendar.SEPTEMBER)
                .set(Calendar.DAY_OF_MONTH, 11)
                .build();

        if (today.get(Calendar.YEAR) + 2 % 4 == 0) {
            if (today.equals(sept11) || today.after(sept11)) {
                return true;
            }
        }

        if ((today.get(Calendar.YEAR) + 1) % 4 == 0) {
            if (isEqualOrBefore(today, sept11)) {
                return true;
            }
        }
        return false;
    }

    public static boolean wasLastYearEthiopianLeapYear() {
//        2012 - 2019 sept 12 - 2020 sept 10
//          if (gregorian yar + 1) % 4 == 0
//            if gregorian date >= sept 12
//                return true
//          if  gregorian year % 4 == 0
//              if gregorian date <= sept 10
//                return true

        Calendar today = Calendar.getInstance();
        Calendar sept12 = new Calendar.Builder()
                .set(Calendar.MONTH, Calendar.SEPTEMBER)
                .set(Calendar.DAY_OF_MONTH, 12)
                .build();
        Calendar sept10 = new Calendar.Builder()
                .set(Calendar.MONTH, Calendar.SEPTEMBER)
                .set(Calendar.DAY_OF_MONTH, 10)
                .build();

        if ((today.get(Calendar.YEAR) + 1) % 4 == 0) {
            if (isEqualOrAfter(today, sept12)) {
                return true;
            }
        }

        if (today.get(Calendar.YEAR) % 4 == 0) {
            if (isEqualOrBefore(today, sept10)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEqualOrAfter(Calendar calendar1, Calendar calendar2) {
        return isEqual(calendar1, calendar2) || isAfter(calendar1, calendar2);
    }

    public static boolean isEqualOrBefore(Calendar calendar1, Calendar calendar2) {
        return isEqual(calendar1, calendar2) || isBefore(calendar1, calendar2);
    }

    public static boolean isAfter(Calendar calendar1, Calendar calendar2) {
        if (calendar1.get(Calendar.MONTH) > calendar2.get(Calendar.MONTH)) {
            return true;
        }

        if (calendar1.get(Calendar.MONTH) < calendar2.get(Calendar.MONTH)) {
            return false;
        }

        return calendar1.get(Calendar.DAY_OF_MONTH) > calendar2.get(Calendar.DAY_OF_MONTH);
    }

    public static boolean isBefore(Calendar calendar1, Calendar calendar2) {
        if (calendar1.get(Calendar.MONTH) < calendar2.get(Calendar.MONTH)) {
            return true;
        }

        if (calendar1.get(Calendar.MONTH) > calendar2.get(Calendar.MONTH)) {
            return false;
        }

        return calendar1.get(Calendar.DAY_OF_MONTH) < calendar2.get(Calendar.DAY_OF_MONTH);
    }

    public static boolean isEqual(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }
}
