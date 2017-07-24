package cc.chavaw.jvm.classfile;

/**
 * 属性表的超类
 * Created by chava on 17-7-9.
 */
public class Attribute {
    /**
     * 直接进行构造
     *
     * @param attribute_name_index 　属性名称索引
     * @param attribute_length     属性的长度
     */
    public Attribute(int attribute_name_index, int attribute_length) {
        this.attribute_name_index = attribute_name_index;
        this.attribute_length = attribute_length;
    }

    /**
     * 属性所占的字节长度
     *
     * @return 字节长度
     */
    public int byteLength() {
        return 6 + attribute_length;
    }

    /**
     * 属性名称索引,指向常量池中的一个UTF8常量
     */
    public final int attribute_name_index;
    /**
     * 属性的长度
     */
    public final int attribute_length;
}
