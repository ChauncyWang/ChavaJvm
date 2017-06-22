package cc.chavaw.jvm;

import cc.chavaw.jvm.dataStructs.JvmClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 测试类
 * Created by 13969 on 2017/6/13.
 */
public class Test {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(
                    new File("D:\\WorkSpaces\\java\\Main.class"));
            JvmClass c = new JvmClass(fis);
            System.out.println(c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
