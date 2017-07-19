package cc.chavaw.jvm;

import java.io.IOException;

/**
 * 测试类
 * Created by 13969 on 2017/6/13.
 */
public class Test {
    public long x = -1l;
    public static void main(String[] args) throws IOException {
        Test t = new Test();
        new Thread1(t).start();
        new Thread2(t).start();

        while (true) {
            long temp = t.x;
            String str = toBinary(temp);
            if (!str.equals("0000000000000000000000000000000000000000000000000000000000000001")
                    && !str.equals("1111111111111111111111111111111111111111111111111111111111111111")) {
                System.out.println("l的写不是原子操作");
                System.out.println(temp);
                System.out.println(str);
                break;
            }
        }
    }
    private static String toBinary(long l) {
        StringBuilder sb = new StringBuilder(Long.toBinaryString(l));
        while (sb.length() < 64) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }
}


class Thread1 extends Thread {
    private Test t;

    public Thread1(Test t) {
        this.t = t;
    }
    @Override
    public void run() {
        while (true) {
            t.x = 1l;


        }
    }
}

class Thread2 extends Thread {
    private Test t;

    public Thread2(Test t) {
        this.t = t;
    }
    @Override
    public void run() {
        while (true) {
            t.x = -1l;

        }
    }
}