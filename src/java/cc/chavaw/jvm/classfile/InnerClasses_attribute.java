package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 【使用位置】类文件
 * 【含   义】记录内部类和宿主类之间的关系
 */
public class InnerClasses_attribute extends Attribute{
    /**
     * 利用 class 读取类 读取对象
     * @param cr class 读取类
     * @param attribute_name_index 属性名字的索引
     * @param attribute_length 属性的长度
     * @throws IOException 读取时发生IO异常
     */
    public InnerClasses_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        number_of_classes = cr.readUnsignedShort();
        inner_classes = new InnerClassesInfo[number_of_classes];
        for(int i = 0; i < number_of_classes; ++i) {
            inner_classes[i] = new InnerClassesInfo(cr);
        }
    }

    /**
     * 内部类的数量
     */
    public final int number_of_classes;
    /**
     * 内部类的信息表
     */
    public final InnerClassesInfo[] inner_classes;

    /**
     * 内部类和宿主类的关系
     */
    public static class InnerClassesInfo {
        public InnerClassesInfo(ClassReader cr) throws IOException {
            inner_class_info_index = cr.readUnsignedShort();
            outer_class_info_index = cr.readUnsignedShort();
            inner_name_index = cr.readUnsignedShort();
            inner_class_access_flags = new AccessFlags(cr);
        }

        /**
         * 内部类的 Constant class info 索引
         */
        public final int inner_class_info_index;
        /**
         * 宿主类 Constant class info 索引
         */
        public final int outer_class_info_index;
        /**
         * 内部类名字索引
         */
        public final int inner_name_index;
        /**
         * 内部类 access flags
         */
        public final AccessFlags inner_class_access_flags;
    }
}
