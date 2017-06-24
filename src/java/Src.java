import java.io.Serializable;

/**
 * 用来解析的 类
 * Created by 13969 on 2017/6/22.
 */
public class Src implements Serializable, Runnable {
    @Override
    public void run() {

    }

    public static int gg = 11111;
    private int hh;
    private Class<?> clazz;
    public static void main(String args) {
        int a = 1111;
        long b = 9222222222222222222l;
        float c = 1.2f;
        double d = 2.4;
        boolean e = true;
        byte f = 111;
        char g = 'g';
        short h = 1002;
        e |= true;
        System.out.println("hello World!");
        Runnable at = new Runnable() {
            @Override
            public void run() {

            }
        };
    }
    public class inner {

    }
    public static class innerS {

    }
}
