package cc.chavaw.jvm.dataStructs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * 类 接口 属性 字段 的访问修饰符
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

    /**
     * 解析类的访问标识
     *
     * @param flags short类型的访问标识掩码
     * @return 解析的 AccessFlag 集合
     * @throws IOException io异常
     */
    public static Vector<AccessFlag> pasrseClassAccessFlag(short flags) throws IOException {
        Vector<AccessFlag> accessFlags = new Vector<>();
        /** 类 支持的访问标识 */
        AccessFlag[] classFlags = new AccessFlag[]{
                ACC_PUBLIC, ACC_FINAL, ACC_SUPER, ACC_INTERFACE,
                ACC_ABSTRACT, ACC_SYNTHETIC, ACC_ANNOTATION, ACC_ENUM};

        for (AccessFlag flag : classFlags) {
            if ((flags & flag.value) != 0) {
                accessFlags.add(flag);
            }
        }

        return accessFlags;
    }

    /**
     * 解析 字段的 访问标识
     * @param flags 字段的访问标识掩码
     * @return AccessFlag集合
     * @throws IOException io异常
     */
    public static Vector<AccessFlag> parseFieldAccessFlags(short flags) throws IOException {
        Vector<AccessFlag> accessFlags = new Vector<>();
        AccessFlag[] fieldFlags = new AccessFlag[]{
                ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL,
                ACC_VOLATILE, ACC_TRANSIENT, ACC_SYNTHETIC, ACC_ENUM};

        for(AccessFlag flag:fieldFlags) {
            if((flags & flag.value) != 0) {
                accessFlags.add(flag);
            }
        }
        return accessFlags;
    }
}
