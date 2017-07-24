package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 【使用位置】 ClassFile 文件中<br>
 * 【含   义】直接 new 接口时会生成一个匿名类，在匿名类中利用此属性，来告知使用该对象的位置。
 */
public class EnclosingMethod_attribute extends Attribute {
    public EnclosingMethod_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        class_index = cr.readUnsignedShort();
        method_index = cr.readUnsignedShort();
    }

    /**
     * 对常量池的有效索引，常量池在此处的索引必须是 CONSTANT_Class_info 结构，用以表示包含当前类声明的最内层类。
     */
    public final int class_index;
    /**
     * 如果当前类不是直接包含在某个方法或是构造器中，那么 method_index 必须为 0。
     */
    public final int method_index;
}
