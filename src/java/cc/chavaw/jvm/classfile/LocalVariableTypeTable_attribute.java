package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * [使用位置]Code属性<br>
 * [含   义]和 LocalVariableTable 属性类似<br>
 * Created by 13969 on 2017/7/20.
 */
public class LocalVariableTypeTable_attribute extends Attribute {
    /**
     * 利用ClassReader读取对象
     *
     * @param cr                   ClassReader
     * @param attribute_name_index 属性名字的索引
     * @param attribute_length     属性长度
     * @throws IOException 读取时发生IO异常
     */
    public LocalVariableTypeTable_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        local_variable_table_length = cr.readUnsignedShort();
    }

    /**
     * 栈帧与源代码中的局部变量的表的长度
     */
    public final int local_variable_table_length;

    /**
     * 栈帧与源代码中的局部变量的关联类
     */
    public static class LocalVariableInfo {

        /**
         * 利用 class 读取类获取对象
         *
         * @param cr class 读取类
         * @throws IOException 读取发生IO异常
         */
        public LocalVariableInfo(ClassReader cr) throws IOException {
            start_pc = cr.readUnsignedShort();
            length = cr.readUnsignedShort();
            name_index = cr.readUnsignedShort();
            signature_index = cr.readUnsignedShort();
            index = cr.readUnsignedShort();

        }

        /**
         * 根据值直接构造
         *
         * @param start_pc         局部变量的生命周期开始的字节码的偏移量
         * @param length           局部变量的作用范围覆盖的长度
         * @param name_index       局部变量的名称的索引
         * @param descriptor_index 局部变量的描述符的索引
         * @param index            部变量在栈帧局部变量表中slot的位置
         */
        public LocalVariableInfo(int start_pc, int length, int name_index, int descriptor_index, int index) {
            this.start_pc = start_pc;
            this.length = length;
            this.name_index = name_index;
            this.signature_index = descriptor_index;
            this.index = index;
        }

        /**
         * 局部变量的生命周期开始的字节码的偏移量(和 length 结合来表示这个局部变量的作用范围)
         */
        public final int start_pc;
        /**
         * 局部变量的作用范围覆盖的长度(和 start_pc 结合来表示这个局部变量的作用范围)
         */
        public final int length;
        /**
         * 指向常量池中 CONSTANT_UTF8_info 型常量的索引，代表局部变量的名称
         */
        public final int name_index;
        /**
         * 指向常量池中 CONSTANT_UTF8_info 型常量的索引，代表局部变量的签名
         */
        public final int signature_index;
        /**
         * 局部变量在栈帧局部变量表中slot的位置<br>
         * 当这个变量类型是64位类型时(long 和 double),它占用Slot为 index 和 index+1 两个
         */
        public final int index;
    }
}