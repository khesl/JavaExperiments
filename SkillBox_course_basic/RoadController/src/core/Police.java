package src.core;

import java.util.HashSet;

/**
 * Created by Danya on 24.08.2015.
 */
public class Police
{
    private static Boolean called = false;
    private static HashSet<String> criminalNumbers;

    public static HashSet<String> getCriminalNumbers()
    {
        if(criminalNumbers != null) {
            return criminalNumbers;
        }
        criminalNumbers = new HashSet<>();
        Integer count = (int) (200 * Math.random());
        for(Integer i = 0; i < count; i++)
        {
            String randomNumber = Double.toString(Math.random()).substring(2, 5);
            criminalNumbers.add(randomNumber);
        }
        return criminalNumbers;
    }

    public static void call(String message)
    {
        called = true;
        System.out.println("Вызов полиции... Причина: " + message);
    }

    public static Boolean wasCalled()
    {
        return called;
    }

    //=======================================

    static void resetCalled()
    {
        called = false;
    }
}
