package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 【使用位置】类文件中
 * 【含   义】用于保存 invokedynamic 指令引用的引导方法的限定符
 */
public class BootstrapMethods_attribute extends Attribute {
    public BootstrapMethods_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        num_bootstrap_methods = cr.readUnsignedShort();
        bootstrap_methods = new bootstrap_method[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; ++i) {
            bootstrap_methods[i] = new bootstrap_method(cr);
        }
    }


    /**
     * bootstrap methods 数组中引导方法限定符的数量
     */
    public final int num_bootstrap_methods;
    /**
     * 引导方法限定符数组
     */
    public final bootstrap_method[] bootstrap_methods;

    /**
     * 引导方法的限定符
     */
    public static class bootstrap_method {
        public bootstrap_method(ClassReader cr) throws IOException {
            bootstrap_method_ref = cr.readUnsignedShort();
            num_bootstrap_arguments = cr.readUnsignedShort();
            bootstrap_arguments = new int[num_bootstrap_arguments];
            for (int i = 0; i < num_bootstrap_arguments; ++i) {
                bootstrap_arguments[i] = cr.readUnsignedShort();
            }
        }

        /**
         * 指向常量池 CONSTANT_MethodHandle 结构的索引值
         */
        public final int bootstrap_method_ref;
        /**
         * bootstrap_arguments 的数组成员的数量
         */
        public final int num_bootstrap_arguments;
        /**
         * 数组中的每个成员必须是一个对常量池的有效索引，常量池在此处的索引必须是下列结构:<br>
         * CONSTANT_String,CONSTANT_Class,CONSTANT_Integer,CONSTANT_Long,<br>
         * CONSTANT_Float,CONSTANT_Double,CONSTANT_MethodHandle,CONSTANT_MethodType
         */
        public final int[] bootstrap_arguments;
    }
}
