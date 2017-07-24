package cc.chavaw.jvm;

import cc.chavaw.jvm.classfile.ClassFile;
import cc.chavaw.jvm.classfile.ConstantPool;
import cc.chavaw.jvm.classfile.visitor.SimpleConstantPoolVisitor;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;

/**
 * 测试类
 * Created by 13969 on 2017/6/13.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        int a = 1;
        long b = 2;
        float c = 3;
        double d = 4;
        ClassFile classFile = new ClassFile(new FileInputStream("D:\\WorkSpaces\\java\\ChavaJvm\\out\\production\\classes\\cc\\chavaw\\jvm\\Test.class"));
        ConstantPool cp = classFile.constant_pool;

        SimpleConstantPoolVisitor visitor = new SimpleConstantPoolVisitor();

        int index = 1;
        for (ConstantPool.CPInfo info : cp.entries()) {
            String str;
            if (info instanceof ConstantPool.CPRefInfo) {
                ConstantPool.CPRefInfo cpRefInfo = (ConstantPool.CPRefInfo) info;
                str = StringUtils.rightPad(String.format("#%d.#%d", cpRefInfo.class_index, cpRefInfo.name_and_type_index), 10);
                str += info.accept(visitor, null);
            } else {
                str = info.accept(visitor, 40);
            }

            str = String.format("%s = %s%s",
                    StringUtils.leftPad("#" + index, 5),
                    StringUtils.rightPad(info.getClass().getSimpleName(), 40),
                    str);
            System.out.println(str);
            if (info.getTag() == ConstantPool.CONSTANT_Long || info.getTag() == ConstantPool.CONSTANT_Double) {
                index += 2;
            } else {
                index += 1;
            }
        }
    }
}