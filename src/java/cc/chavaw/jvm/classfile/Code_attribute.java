package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 【使用位置】方法表
 * 【含　　义】Code属性中包含某个方法，实力初始化方法，类或接口的初始化方法的 java 虚拟机指令及相关辅助信息。<br>
 * 如果方法声明为 native 或者 abstract 方法，那么 方法属性结构决不能有Code属性。<br>
 * 在其他情况下，方法属性结构中有且只能有一个 Code 属性。<br>
 * Created by root on 7/19/17.
 */
public class Code_attribute extends Attribute {

    /**
     * 从 ClassReader 中读取对象
     * @param cr ClassReader
     * @param attribute_name_index 属性名字索引
     * @param attribute_length　属性长度
     * @throws IOException IO异常
     */
    public Code_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        max_stack = cr.readUnsignedShort();
        max_locals = cr.readUnsignedShort();
        code_length = cr.readInt();
        codes = new byte[code_length];
        for(int i = 0; i < code_length; ++i) {
            codes[i] = (byte) cr.readUnsignedByte();
        }
        exception_table_length = cr.readUnsignedShort();
        exception_table = new Exception_info[exception_table_length];
        for(int i = 0; i < exception_table_length; ++i) {
            exception_table[i] = new Exception_info(cr);
        }
        attribute_count = cr.readUnsignedShort();
        attributes = new Attribute[attribute_count];
    }

    /**
     * 直接进行构造
     *
     * @param attribute_name_index   名字索引
     * @param attribute_length       属性长度
     * @param max_stack              最大栈帧大小
     * @param max_locals             局部变量的存储空间
     * @param code_length            字节码长度
     * @param codes                  字节码表
     * @param exception_table_length 异常表长度
     * @param exception_table        异常表
     * @param attribute_count        属性表大小
     * @param attributes             属性表
     */
    public Code_attribute(int attribute_name_index, int attribute_length, int max_stack, int max_locals, int code_length, byte[] codes, int exception_table_length, Exception_info[] exception_table, int attribute_count, Attribute[] attributes) {
        super(attribute_name_index, attribute_length);
        this.max_stack = max_stack;
        this.max_locals = max_locals;
        this.code_length = code_length;
        this.codes = codes;
        this.exception_table_length = exception_table_length;
        this.exception_table = exception_table;
        this.attribute_count = attribute_count;
        this.attributes = attributes;
    }

    /**
     * 操作数栈的深度最大值
     */
    public final int max_stack;
    /**
     * 局部变量表所需的存储空间
     */
    public final int max_locals;
    /**
     * 字节码长度
     */
    public final int code_length;
    /**
     * 字节码集合
     */
    public final byte[] codes;
    /**
     * 异常表大小
     */
    public final int exception_table_length;
    /**
     * 异常表
     */
    public final Exception_info[] exception_table;
    /**
     * Code属性的附加属性长度
     */
    public final int attribute_count;
    /**
     * 属性表
     */
    public final Attribute[] attributes;

    /**
     * code 异常信息表类型
     */
    public static class Exception_info {
        /**
         * 从ClassReader中读取一个异常表
         *
         * @param cr ClassReader
         * @throws IOException IO异常
         */
        public Exception_info(ClassReader cr) throws IOException {
            start_pc = cr.readUnsignedShort();
            end_pc = cr.readUnsignedShort();
            handler_pc = cr.readUnsignedShort();
            catch_type = cr.readUnsignedShort();
        }

        /**
         * 直接进行构造
         *
         * @param start_pc   开始位置
         * @param end_pc     结束位置
         * @param handler_pc 处理位置
         * @param catch_type 处理类型
         */
        public Exception_info(int start_pc, int end_pc, int handler_pc, int catch_type) {
            this.start_pc = start_pc;
            this.end_pc = end_pc;
            this.handler_pc = handler_pc;
            this.catch_type = catch_type;
        }

        /**
         * 异常监控的指令开头位置
         */
        public final int start_pc;
        /**
         * 异常监控的指令结束位置
         */
        public final int end_pc;
        /**
         * 在 start_pc 和　end_pc 之间<br>
         * [start_pc,end_pc)<br>
         * 获取到　catch_type(及其子类) 类型的异常时<br>
         * 都跳转到 handler_pc 的位置进行处理
         */
        public final int handler_pc;
        /**
         * 一个指向 常量池的索引(类型为UTF8),
         * 表示要处理的异常类型
         */
        public final int catch_type;
    }
}
