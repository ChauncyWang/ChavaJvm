package cc.chavaw.jvm;

import java.io.IOException;

/**
 * 测试类
 * Created by 13969 on 2017/6/13.
 */
public class Test {
   public static void main(String[] args) throws IOException {
        int a = System.in.read();
        if(a=='a') {
            a = System.in.read();
        }
        if(a=='b'){
            System.out.append('b');
            System.out.flush();
        }
   }
}