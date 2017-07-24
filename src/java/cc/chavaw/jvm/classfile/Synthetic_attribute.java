package cc.chavaw.jvm.classfile;

/**
 * 【使用位置】类，字段，或是方法<br>
 * 【含   义】该属性内部没有任何属性，只有有和没有的区别<br>
 * 该属性用来表示类方法并不是由java源代码生成的，而是编译器自己生成的<br>
 * 由非用户产生的类，字段，属性都应该设置该属性或是添加 ACC_SYNTHETIC 标志位
 */
public class Synthetic_attribute extends Attribute {

    /**
     * 直接进行构造
     *
     * @param cr                   class 读取类
     * @param attribute_name_index 　属性名称索引
     * @param attribute_length     属性的长度
     */
    public Synthetic_attribute(ClassReader cr, int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }
}
