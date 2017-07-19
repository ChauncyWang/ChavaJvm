package cc.chavaw.jvm.classfile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 访问标识符
 * Created by chava on 17-7-9.
 */
public class AccessFlags {
    /**
     * 共有标志,适用于　类、内联类、字段和方法
     */
    public static final int ACC_PUBLIC = 0x0001;// class, inner, field, method
    /**
     * 私有标志,适用于　内联类、字段和方法
     */
    public static final int ACC_PRIVATE = 0x0002;//        inner, field, method
    /**
     * 受保护的,适用于　内联类、字段和方法
     */
    public static final int ACC_PROTECTED = 0x0004;//        inner, field, method
    /**
     * 静态成员标志,适用于　内联类、字段和方法
     */
    public static final int ACC_STATIC = 0x0008;//        inner, field, method
    /**
     * 最终态标志,适用于　类,内联类、字段和方法
     */
    public static final int ACC_FINAL = 0x0010;// class, inner, field, method
    /**
     * 父类标识,表示如何调用父类,只适用于类
     */
    public static final int ACC_SUPER = 0x0020;// class
    /**
     * 同步锁标识,如果方法有该标识,标识该方法有同步锁
     */
    public static final int ACC_SYNCHRONIZED = 0x0020;//                      method
    /**
     * 保证变量的可见性,防止重排序,保证64位变量(long,double)的原子性读写
     */
    public static final int ACC_VOLATILE = 0x0040;//               field
    /**
     * 桥接标识,在使用接口模板时会在实现类中会为模板方法自动实现一个返回值为 Object 的方法
     */
    public static final int ACC_BRIDGE = 0x0040;//                      method
    /**
     * 标识字段,表示字段不需要进行序列化
     */
    public static final int ACC_TRANSIENT = 0x0080;//               field
    /**
     * 不定长参数标识,该方法具有不定长参数
     */
    public static final int ACC_VARARGS = 0x0080;//                      method
    /**
     * 本地方法,告诉 jvm 该方法为本地方法
     */
    public static final int ACC_NATIVE = 0x0100;//                      method
    /**
     * 标识类和内联类为接口
     */
    public static final int ACC_INTERFACE = 0x0200;// class, inner
    /**
     * 标识类,内联类和方法为抽象
     */
    public static final int ACC_ABSTRACT = 0x0400;// class, inner,        method
    /**
     * 严格的方法,让该方法中所有的float和double表达式都严格遵守FP-strict的限制,符合IEEE-754规范
     */
    public static final int ACC_STRICT = 0x0800;//                      method
    /**
     * 合成类,非静态内部类的　this$
     */
    public static final int ACC_SYNTHETIC = 0x1000;// class, inner, field, method
    /**
     * 标识类和内联类为注解
     */
    public static final int ACC_ANNOTATION = 0x2000;// class, inner
    /**
     * 标识类,内联类和属性为枚举
     */
    public static final int ACC_ENUM = 0x4000;// class, inner, field
    /**
     * 委托,貌似为 Java 9提供的
     */
    public static final int ACC_MANDATED = 0x8000;// class, inner, field, method

    /**
     * 构造方法　从 ClassReader 里读取数值进行构造
     *
     * @param cr ClassReader
     * @throws IOException IO异常
     */
    public AccessFlags(ClassReader cr) throws IOException {
        this(cr.readInt());
    }

    /**
     * 直接进行构造
     *
     * @param flags flag的值
     */
    public AccessFlags(int flags) {
        this.flags = flags;
    }

    /**
     * 获取类的所有标识
     *
     * @return 标识的文字描述集合
     */
    public Set<String> getClassFlag() {
        return getFlag(classFlags, Kind.Class);
    }

    /**
     * 获取方法的所有标识
     *
     * @return 标识的文字描述集合
     */
    public Set<String> getMethodFlag() {
        return getFlag(methodFlags, Kind.Method);
    }

    /**
     * 获取字段的所有标识
     *
     * @return 标识的文字描述集合
     */
    public Set<String> getFieldFlag() {
        return getFlag(fieldFlags, Kind.Field);
    }

    /**
     * 获取内联类的所有标识
     *
     * @return 标识的文字描述集合
     */
    public Set<String> getInnerFlag() {
        return getFlag(innerClassFlags, Kind.InnerClass);
    }

    /**
     * 获取 flags 包含的 flag 的名字
     *
     * @param flags 要检查的 flags
     * @param kind  检查类型
     * @return flag 名字的集合
     */
    private Set<String> getFlag(int[] flags, Kind kind) {
        Set<String> strings = new HashSet<>();
        for (int flag : flags) {
            if (is(flag)) {
                strings.add(getName(flag, Kind.Class));
            }
        }
        return strings;
    }

    /**
     * 根据 单个flag 和 flag 类型类返回字段的字符串名字
     *
     * @param flag 标识
     * @param kind flag 类型
     * @return flag 的字符串表示
     */
    static String getName(int flag, Kind kind) {
        switch (flag) {
            case ACC_PUBLIC:
                return "ACC_PUBLIC";
            case ACC_PRIVATE:
                return "ACC_PRIVATE";
            case ACC_PROTECTED:
                return "ACC_PROTECTED";
            case ACC_STATIC:
                return "ACC_STATIC";
            case ACC_FINAL:
                return "ACC_FINAL";
            case 0x0020:
                if (kind == Kind.Class)
                    return "ACC_SUPER";
                if (kind == Kind.Method)
                    return "ACC_SYNCHRONIZED";
            case 0x0040:
                if (kind == Kind.Field)
                    return "ACC_VOLATILE";
                if (kind == Kind.Method)
                    return "ACC_BRIDGE";
            case 0x0080:
                if (kind == Kind.Field)
                    return "ACC_TRANSIENT";
                if (kind == Kind.Method)
                    return "ACC_VARARGS";
            case ACC_NATIVE:
                return "ACC_NATIVE";
            case ACC_INTERFACE:
                return "ACC_INTERFACE";
            case ACC_ABSTRACT:
                return "ACC_ABSTRACT";
            case ACC_STRICT:
                return "ACC_STRICT";
            case ACC_SYNTHETIC:
                return "ACC_SYNTHETIC";
            case ACC_ANNOTATION:
                return "ACC_ANNOTATION";
            case ACC_ENUM:
                return "ACC_ENUM";
            case ACC_MANDATED:
                return "ACC_MANDATED";
            default:
                return null;
        }
    }

    /**
     * 判断 是否包含某个 flag
     *
     * @param mask 要判断的flag
     * @return 是否包含
     */
    public boolean is(int mask) {
        return (flags & mask) != 0;
    }

    public enum Kind {Class, InnerClass, Field, Method}

    /**
     * 类的修饰符
     */
    public static final int[] classFlags = {
            ACC_PUBLIC, ACC_FINAL, ACC_SUPER, ACC_INTERFACE, ACC_ABSTRACT,
            ACC_SYNTHETIC, ACC_ANNOTATION, ACC_ENUM, ACC_MANDATED};
    /**
     * 内部类的修饰符
     */
    public static final int[] innerClassFlags = {
            ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_INTERFACE, ACC_ABSTRACT,
            ACC_SYNTHETIC, ACC_ANNOTATION, ACC_ENUM, ACC_MANDATED};
    /**
     * 属性的修饰符
     */
    public static final int[] fieldFlags = {
            ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_VOLATILE,
            ACC_TRANSIENT, ACC_SYNTHETIC, ACC_ENUM, ACC_MANDATED};
    /**
     * 方法的修饰符
     */
    public static final int[] methodFlags = {
            ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_SYNCHRONIZED,
            ACC_BRIDGE, ACC_VARARGS, ACC_NATIVE, ACC_ABSTRACT, ACC_STRICT, ACC_SYNTHETIC,
            ACC_MANDATED};
    /**
     * 整形的修饰符,需要进行解析
     */
    private int flags;
}
