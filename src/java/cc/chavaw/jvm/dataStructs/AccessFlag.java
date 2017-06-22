package cc.chavaw.jvm.dataStructs;

/**
 * 类（或者接口）的访问修饰符
 * Created by 13969 on 2017/6/22.
 */
public enum AccessFlag {
    ACC_PUBLIC(0x0001),         //class field method
    ACC_PRIVATE(0x0002),        //      field method
    ACC_PROTECTED(0x0004),      //      field method
    ACC_STATIC(0x0008),         //      field method
    ACC_FINAL(0x0010),          //class field method
    ACC_SUPER(0x0020),          //class
    ACC_SYNCHRONIZED(0x0020),   //            method
    ACC_VOLATILE(0x0040),       //      field
    ACC_BRIDGE(0x0040),         //            method
    ACC_TRANSIENT(0x0080),      //      field
    ACC_VARARGS(0x0080),        //            method
    ACC_NATIVE(0x0100),         //            method
    ACC_INTERFACE(0x0200),      //class
    ACC_ABSTRACT(0x0400),       //class
    ACC_STRICT(0x0800),         //            method
    ACC_SYNTHETIC(0x1000),      //class field method
    ACC_ANNOTATION(0x2000),     //class
    ACC_ENUM(0x4000);           //class field

    public int value;
    AccessFlag(int value) {
        this.value = value;
    }
}
