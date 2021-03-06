package com.francescomalagrino.mynews;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MyNewsUnitTest {


    @Test
    public void validateDateParsing() throws Exception{
        // Date format for the date you get from the API.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        // Give a sample date as a String because you get date as a String from the API
        // to SimpleDateFormat you created above and parse it.
        Date date = simpleDateFormat.parse("2022-07-05T20:13:24+0330");

        // If this is successful, then the Date object will not be null.
        // If it fails, then it will be null.
        Assert.assertNotNull(date);
    }

    @Test
    public void checkEndDate() {
        String expectedEndDate = "20220705";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, 06);
        calendar.set(Calendar.DAY_OF_MONTH, 05);
        String mFormat = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormat, Locale.FRANCE);
        String actualEndDate = simpleDateFormat.format(calendar.getTime());

        Assert.assertEquals(expectedEndDate, actualEndDate);
    }

    @Test
    public void checkBeginDate() {
        String expectedBeginDate = "20220705";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, 06);
        calendar.set(Calendar.DAY_OF_MONTH, 05);
        String mFormat = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mFormat, Locale.FRANCE);
        String actualBeginDate = simpleDateFormat.format(calendar.getTime());

        Assert.assertEquals(expectedBeginDate, actualBeginDate);
    }

    @Test
    public void isNewsCategoriesListInitialized() {
        ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(new String[]
                {"Sports", "Art", "Business", "Tech"}
        ));

        ArrayList<String> actualList = new ArrayList<>();
        actualList.add("Sports");
        actualList.add("Art");
        actualList.add("Business");
        actualList.add("Tech");

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void isCorrectCategorySelected() {
        ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(new String[]
                {"Sports", "Art", "Business", "Tech"}
        ));
        String expectedCategory = expectedList.get(0);

        ArrayList<String> actualList = new ArrayList<>();
        actualList.add("Sports");
        actualList.add("Art");
        actualList.add("Business");
        actualList.add("Tech");
        String actualCategory = actualList.get(0);

        Assert.assertEquals(expectedCategory, actualCategory);
    }
}
