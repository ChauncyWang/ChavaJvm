package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 描述java源代码与字节码行号之间的偏移<br>
 * javac中可以使用-g:none或-g:lines选项来选择是否生成该属性<br>
 * 如果选择不生产成该属性,对程序运行时的最主要影响就是当抛出异常时,堆栈将不会显示出错的行号<br>
 * 并且调试时无法根据行号来设置断点<br>
 * [使用位置]Code属性<br>
 * [含   义]java源码的行号与字节码指令的对应关系<br>
 * Created by 13969 on 2017/7/20.
 */
public class LineNumberTable_attribute extends Attribute {
    /**
     * 利用ClassReader来读取对象
     *
     * @param cr                   ClassReader
     * @param attribute_name_index 属性名字的索引
     * @param attribute_length     属性长度
     * @throws IOException 读取发生IO异常
     */
    public LineNumberTable_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        line_number_table_length = cr.readUnsignedShort();
        line_number_table = new LineNumberInfo[line_number_table_length];
        for (int i = 0; i < line_number_table_length; ++i) {
            line_number_table[i] = new LineNumberInfo(cr);
        }
    }

    /**
     * table 数量(LineNumberInfo类型)
     */
    public final int line_number_table_length;
    /**
     * table 数组(LineNumberInfo类型)
     */
    public final LineNumberInfo[] line_number_table;

    /**
     * LineNumber表
     */
    public static class LineNumberInfo {
        /**
         * 利用ClassReader读取
         *
         * @param cr ClassReader
         * @throws IOException 读取发生IO异常
         */
        public LineNumberInfo(ClassReader cr) throws IOException {
            start_pc = cr.readUnsignedShort();
            line_number = cr.readUnsignedShort();
        }

        /**
         * 直接构造
         *
         * @param start_pc    字节码行号
         * @param line_number 源码行号
         */
        public LineNumberInfo(int start_pc, int line_number) {
            this.start_pc = start_pc;
            this.line_number = line_number;
        }

        /**
         * 字节码行号
         */
        public final int start_pc;
        /**
         * 源码行号
         */
        public final int line_number;
    }
}
