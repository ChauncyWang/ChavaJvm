package cc.chavaw.jvm;

import cc.chavaw.jvm.dataStructs.ClassInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 测试类
 * Created by 13969 on 2017/6/13.
 */
public class Test {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(
                    new File("build/classes/main/Src.class"));
            ClassInfo c = new ClassInfo(fis);
            System.out.println(c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
