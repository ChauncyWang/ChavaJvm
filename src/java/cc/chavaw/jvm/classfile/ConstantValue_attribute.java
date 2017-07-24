package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 【使用位置】字段<br>
 * 【含   义】通知虚拟机自动为静态变量赋值，只有被 static 关键字修饰的变量(类变量)才可以使用这项属性<br>
 * 对于非静态变量（实例变量）的赋值是在实例构造器 init 中进行的<br>
 * 而对于类变量，可以在类构造器 cinit 中或者使用 ConstantValue 属性<br>
 * 现在 sun javac 编辑器选择是:<br>
 * 同时使用 final 和 static ，并且是基本数据类型和 java.lang.String 的话就生成 ConstantValue 属性<br>
 * 如果没有被 final 修饰，或者并非基本类型和字符串，则会选择在 cinit 方法中进行初始化<br>
 * 【注   意】如果字段结构表示的非静态字段包含了 ConstantValue 属性，那么这个属性必须被虚拟机忽略。<br>
 * 在字段结构的属性表中，最多只能有一个 ConstantValue 属性。
 */
public class ConstantValue_attribute extends Attribute {
    /**
     * 利用 class 读取类来读取
     *
     * @param cr                   class 读取类
     * @param attribute_name_index 属性名字的索引
     * @param attribute_length     属性长度
     * @throws IOException 读取时发生IO异常
     */
    public ConstantValue_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        constantvalue_index = cr.readUnsignedShort();
    }

    /**
     * 代表了常量池中一个字面量常量的引用
     */
    public final int constantvalue_index;
}
