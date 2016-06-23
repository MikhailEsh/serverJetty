package utils;

/**
 * Created by Агент on 31.08.15.
 */

public class TimeHelper {
    public static void sleep(int period){
        try{
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(){
        try{
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
