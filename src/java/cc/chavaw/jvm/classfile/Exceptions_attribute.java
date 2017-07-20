package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 异常属性,列举出 throws 后面方法可能抛出的异常<br>
 * [使用位置]方法表<br>
 * [含   义]方法抛出的异常<br>
 * Created by root on 7/19/17.
 */
public class Exceptions_attribute extends Attribute {
    /**
     * 从 ClassReader 中读取构造
     *
     * @param cr                   ClassReader
     * @param attribute_name_index 属性名称索引
     * @param attribute_length     属性的长度
     * @throws IOException IO异常
     */
    public Exceptions_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        number_of_exceptions = cr.readUnsignedShort();
        exception_index_table = new int[number_of_exceptions];
        for (int i = 0; i < number_of_exceptions; ++i) {
            exception_index_table[i] = cr.readUnsignedShort();
        }

    }

    /**
     * 直接进行构造
     *
     * @param attribute_name_index  　属性名称索引
     * @param attribute_length      属性的长度
     * @param number_of_exceptions  可能抛出异常的种类
     * @param exception_index_table number_of_exceptions 个常量池中的指针索引,指向一个 Constant_Class_info 常量
     */
    public Exceptions_attribute(int attribute_name_index, int attribute_length, int number_of_exceptions, int[] exception_index_table) {
        super(attribute_name_index, attribute_length);
        this.number_of_exceptions = number_of_exceptions;
        this.exception_index_table = exception_index_table;
    }

    /**
     * 可能抛出异常的种类
     */
    public final int number_of_exceptions;
    /**
     * 抛出异常的类型表 指向常量池中的 number_of_exceptions 个 Constant_Class_info 常量
     */
    public final int[] exception_index_table;
}
