package cc.chavaw.jvm.classfile;

/**
 * 异常属性,列举出 throws 后面方法可能抛出的异常
 * [使用位置]方法表
 * [含   义]方法抛出的异常
 * Created by root on 7/19/17.
 */
public class Exceptions_attribute extends Attribute{

    public final int number_of_exceptions;
    public final int[] exception_index_table;

    /**
     * 直接进行构造
     *  @param attribute_name_index 　属性名称索引
     * @param attribute_length     属性的长度
     * @param number_of_exceptions
     * @param exception_index_table
     */
    public Exceptions_attribute(int attribute_name_index, int attribute_length, int number_of_exceptions, int[] exception_index_table) {
        super(attribute_name_index, attribute_length);
        this.number_of_exceptions = number_of_exceptions;
        this.exception_index_table = exception_index_table;
    }
}
