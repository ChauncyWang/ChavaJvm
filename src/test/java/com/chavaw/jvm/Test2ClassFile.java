package com.chavaw.jvm;

import cc.chavaw.jvm.classfile.ClassFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * classfile 读取 测试
 * Created by chava on 17-7-15.
 */
public class Test2ClassFile {
    public static void main(String[] args) throws Exception {
        File f = new File("build/classes/main/cc/chavaw/jvm/Test.class");
        ClassFile cf = new ClassFile(new FileInputStream(f));
    }
}
