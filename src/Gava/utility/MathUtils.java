package Gava.utility;

public class MathUtils {
    public static int randint(int min, int max){
        return (int)(Math.random() * (max - min) + min);
    }
}
