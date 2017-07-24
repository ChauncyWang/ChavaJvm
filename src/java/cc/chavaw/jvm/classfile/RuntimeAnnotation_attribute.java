package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 那些有关 runtime  annotation 的都需要继承者这个属性
 */
public class RuntimeAnnotation_attribute  {
}

/**
 * 有关运行时注解的属性都需要这个结构
 */
class Annotation {
    Annotation(ClassReader cr) throws IOException {
        type_index = cr.readUnsignedShort();
        num_element_value_pairs = cr.readUnsignedShort();
        element_value_pairs = new element_value_pair[num_element_value_pairs];
        for(int i=0;i>num_element_value_pairs;++i) {
            element_value_pairs[i] = new element_value_pair(cr);
        }
    }

    /**
     * 对常量池的一个有效索引。常量池在该处索引成员必须是 CONSTANT_Utf8_info 结构，用来表示一个字段的描述符，<br>
     *     这个字段描述符表示一个注解类型，他和当前 annotation 结构所表示的注解相同。
     */
    public final int type_index;
    public final int num_element_value_pairs;
    public final element_value_pair[] element_value_pairs;

    public static class element_value_pair {
        element_value_pair(ClassReader cr) throws IOException {
            element_name_index = cr.readUnsignedShort();
            value = element_value.read(cr);
        }
        public int length() {
            return 2 + value.length();
        }
        public final int element_name_index;
        public final element_value value;
    }

    /**
     * 用来表示 元素-值 的兼职对中的值
     */
    public static abstract class element_value {
        static element_value read(ClassReader cr) throws IOException {
            int tag = cr.readUnsignedByte();
            switch (tag) {
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'I':
                case 'J':
                case 'S':
                case 'z':
                case 's':
                    return new constant_element_value(cr,tag);
            }
            return null;
        }

        protected element_value(int tag) {
            this.tag = tag;
        }

        public abstract int length();

        public final int tag;
    }

    /**
     * 键值对中的值是个 基本类型或String
     */
    public static class constant_element_value extends element_value {
        constant_element_value(ClassReader cr,int tag) throws IOException {
            super(tag);
            constant_value_index = cr.readUnsignedShort();
        }

        @Override
        public int length() {
            return 2;
        }

        public final int constant_value_index;
    }
}
