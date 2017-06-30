package cc.chavaw.jvm.dataStructs.constantPool;

/**
 * 描述不同常量池的 tag 标识
 * Created by 13969 on 2017/6/30.
 */
public interface Tag {
    int CONSTANT_Utf8 = 1;
    int CONSTANT_Integer = 3;
    int CONSTANT_Float = 4;
    int CONSTANT_Long = 5;
    int CONSTANT_Double = 6;
    int CONSTANT_Class = 7;
    int CONSTANT_String = 8;
    int CONSTANT_Fieldref = 9;
    int COSNTANT_Methodref = 10;
    int COSNTANT_InterfaceMethodref = 11;
    int CONSTANT_NameAndType = 12;
    int CONSTANT_MethodHandle = 15;
    int CONSTANT_MethodType = 16;
    int CONSTANT_InvokeDynamic = 18;
}
