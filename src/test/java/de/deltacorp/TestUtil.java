package de.deltacorp;

public class TestUtil {

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
            System.out.println("Sleep was interrupted ... just so you know. Dont throw on test case.");
        }
    }
}
