package cc.chavaw.jvm;

import cc.chavaw.jvm.classfile.ConstantPool;

import java.io.IOException;

/**
 * 测试类
 * Created by 13969 on 2017/6/13.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Object a= new ConstantPool.CPRefInfo(10,20,20);
        System.out.println(a);
        a = new ConstantPool.CONSTANT_Class_info(10);
        System.out.println(a);
        a = new ConstantPool.CONSTANT_Double_info(10);
        System.out.println(a);
        a = new ConstantPool.CONSTANT_Fieldref_info(10,20,30);
        System.out.println(a);
        a = new ConstantPool.CONSTANT_Float_info(1.2345f);
        System.out.println(a);
        a = new ConstantPool.CONSTANT_InterfaceMethodref_info(10,23,35);
        System.out.println(a);
        a = new ConstantPool.CONSTANT_MethodHandle_info(ConstantPool.RefKind.REF_getField,35);
        System.out.println(a);
        a = new ConstantPool.CONSTANT_Methodref_info(10,23,35);
        System.out.println(a);
        a = new ConstantPool.CONSTANT_String_info(10);
        System.out.println(a);
        a = new ConstantPool.CONSTANT_Utf8_info("妈卖批啊!");
        System.out.println(a);
    }
}
