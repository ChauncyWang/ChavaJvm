package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 【使用位置】类文件<br>
 * 【含   义】记录生成这个Class文件的源码文件名<br>
 * 可选属性，可以使用 -g:none 或 -g:source 选项来关闭或要求生成<br>
 * 如果不生成这个属性，抛出异常时，堆栈中将不会显示出错代码所属的文件名
 */
public class SourceFile_attribute extends Attribute {
    /**
     * 利用 class 读取类来获取属性
     *
     * @param cr                   class 读取类
     * @param attribute_name_index 属性名字的索引
     * @param attribute_length     属性的长度
     * @throws IOException 读取时发生 IO 异常
     */
    public SourceFile_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        sourcefile_index = cr.readUnsignedShort();
    }

    /**
     * 直接按字段进行构造
     *
     * @param attribute_name_index 属性的名字索引
     * @param attribute_length     属性长度
     * @param sourcefile_index     文件名的索引
     */
    public SourceFile_attribute(int attribute_name_index, int attribute_length, int sourcefile_index) {
        super(attribute_name_index, attribute_length);
        this.sourcefile_index = sourcefile_index;
    }

    /**
     * 文件名，指向常量池中 CONSTANT_Utf8_info 型常量的索引
     */
    public final int sourcefile_index;
}
