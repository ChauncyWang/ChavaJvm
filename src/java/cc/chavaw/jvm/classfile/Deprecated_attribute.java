package cc.chavaw.jvm.classfile;

/**
 * 【使用位置】类，方法表，字段表<br>
 * 【含   义】该属性没有任何属性值，只有有和没有的区别<br>
 * 该属性用于表示某个类、字段、方法，已经不再被作者推荐使用<br>
 * 他通过代码中使用 @deprecated 注解进行设置
 */
public class Deprecated_attribute extends Attribute{

    /**
     * 构造函数
     * @param cr class 读取类
     * @param attribute_name_index 　属性名称索引
     * @param attribute_length     属性的长度
     */
    public Deprecated_attribute(ClassReader cr, int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }
}
