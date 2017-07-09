package cc.chavaw.jvm;

import cc.chavaw.jvm.classfile.ClassFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 测试类
 * Created by 13969 on 2017/6/13.
 */
public class Test {
    @Override
    public String toString() {
        return "Test{}";
    }

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(
                    new File("build/classes/main/Src.class"));
            ClassFile c = new ClassFile(fis);
            System.out.println(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
