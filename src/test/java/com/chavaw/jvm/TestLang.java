package com.chavaw.jvm;

import cc.chavaw.jvm.classfile.ConstantPool;

/**
 * ���Թ��ʻ�
 * Created by root on 7/13/17.
 */
public class TestLang {
    public static void main(String[] args) {
        try {
            throw new ConstantPool.UnexpectedTagException(10, 2);
        } catch (ConstantPool.UnexpectedTagException unexpectedTag) {
            unexpectedTag.printStackTrace();
        }

    }
}
