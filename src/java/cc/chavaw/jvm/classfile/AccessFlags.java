package cc.chavaw.jvm.classfile;

/**
 * 访问标识符
 * Created by chava on 17-7-9.
 */
public class AccessFlags {
    public static final int ACC_PUBLIC          = 0x0001;// class, inner, field, method
    public static final int ACC_PRIVATE         = 0x0002;//        inner, field, method
    public static final int ACC_PROTECTED       = 0x0004;//        inner, field, method
    public static final int ACC_STATIC          = 0x0008;//        inner, field, method
    public static final int ACC_FINAL           = 0x0010;// class, inner, field, method
    public static final int ACC_SUPER           = 0x0020;// class
    public static final int ACC_SYNCHRONIZED    = 0x0020;//                      method
    public static final int ACC_VOLATILE        = 0x0040;//               field
    public static final int ACC_BRIDGE          = 0x0040;//                      method
    public static final int ACC_TRANSIENT       = 0x0080;//               field
    public static final int ACC_VARARGS         = 0x0080;//                      method
    public static final int ACC_NATIVE          = 0x0100;//                      method
    public static final int ACC_INTERFACE       = 0x0200;// class, inner
    public static final int ACC_ABSTRACT        = 0x0400;// class, inner,        method
    public static final int ACC_STRICT          = 0x0800;//                      method
    public static final int ACC_SYNTHETIC       = 0x1000;// class, inner, field, method
    public static final int ACC_ANNOTATION      = 0x2000;// class, inner
    public static final int ACC_ENUM            = 0x4000;// class, inner, field
    public static final int ACC_MANDATED        = 0x8000;// class, inner, field, method
    /** 构造函数 */
    public AccessFlags(int flags) {
        this.flags = flags;
    }

    public enum Kind {Class, InnerClass, Field, Method}
    /** 类的修饰符 */
    private static final int[] classFlags = {
            ACC_PUBLIC, ACC_FINAL, ACC_SUPER, ACC_INTERFACE, ACC_ABSTRACT,
            ACC_SYNTHETIC, ACC_ANNOTATION, ACC_ENUM, ACC_MANDATED};
    /** 内部类的修饰符 */
    private static final int[] innerClassFlags = {
            ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_INTERFACE, ACC_ABSTRACT,
            ACC_SYNTHETIC, ACC_ANNOTATION, ACC_ENUM, ACC_MANDATED};
    /** 属性的修饰符 */
    private static final int[] fieldFlags = {
            ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_VOLATILE,
            ACC_TRANSIENT, ACC_SYNTHETIC, ACC_ENUM, ACC_MANDATED};
    /** 方法的修饰符 */
    private static final int[] methodFlags = {
            ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_SYNCHRONIZED,
            ACC_BRIDGE, ACC_VARARGS, ACC_NATIVE, ACC_ABSTRACT, ACC_STRICT,ACC_SYNTHETIC,
            ACC_MANDATED};
    /** 整形的修饰符，需要进行解析 */
    private int flags;
}
