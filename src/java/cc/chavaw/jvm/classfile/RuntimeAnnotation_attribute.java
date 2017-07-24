package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 那些有关 runtime  annotation 的都需要继承者这个属性
 */
public class RuntimeAnnotation_attribute {
}

/**
 * 有关运行时注解的属性都需要这个结构
 */
class Annotation {
    /**
     * 利用 class 读取类加载一个 Annotation
     *
     * @param cr class 读取类
     * @throws IOException 读取时发生 io 异常
     */
    Annotation(ClassReader cr) throws IOException {
        type_index = cr.readUnsignedShort();
        num_element_value_pairs = cr.readUnsignedShort();
        element_value_pairs = new element_value_pair[num_element_value_pairs];
        for (int i = 0; i > num_element_value_pairs; ++i) {
            element_value_pairs[i] = new element_value_pair(cr);
        }
    }

    public int length() {
        int n = 2 /*type_index*/ + 2 /*num_element_value_pairs*/;
        for (element_value_pair pair : element_value_pairs)
            n += pair.length();
        return n;
    }

    /**
     * 对常量池的一个有效索引。常量池在该处索引成员必须是 CONSTANT_Utf8_info 结构，用来表示一个字段的描述符，<br>
     * 这个字段描述符表示一个注解类型，他和当前 annotation 结构所表示的注解相同。
     */
    public final int type_index;
    /**
     * 注解中键值对个个数
     */
    public final int num_element_value_pairs;
    /**
     * 数组中每个成员代表注解中的一个键值对
     */
    public final element_value_pair[] element_value_pairs;

    /**
     * 键值对结构
     */
    public static class element_value_pair {
        /**
         * 利用 class 读取类加载一个键值对
         *
         * @param cr class 读取类
         * @throws IOException 读取时发生 io 异常
         */
        element_value_pair(ClassReader cr) throws IOException {
            element_name_index = cr.readUnsignedShort();
            value = element_value.read(cr);
        }

        /**
         * 获取键值对所占的字节空间
         *
         * @return 所占字节空间的大小
         */
        public int length() {
            return 2 + value.length();
        }

        /**
         * 一个常量池索引，对常量池中 utf8 结构的有效索引<br>
         * 用来表示键值对中键的值
         */
        public final int element_name_index;
        /**
         * 给出了键值对中 一个 element_value 类型的值
         */
        public final element_value value;
    }

    /**
     * 用来表示 元素-值 的键值对中的值
     */
    public static abstract class element_value {
        /**
         * 此静态函数用来构造 element_value
         *
         * @param cr class 读取类
         * @return 一个读取到的 element_value 对象
         * @throws IOException 读取时发生 io 异常
         */
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
                    return new constant_element_value(cr, tag);
                case 'e':
                    return new enum_element_value(cr, tag);
                case 'c':
                    return new class_element_value(cr, tag);
                case '@':
                    return new annotation_element_value(cr,tag);
                case '[':
                    return new array_element_vlaue(cr,tag);
                default:
                    return null;
            }
        }

        /**
         * 子类可以使用 tag 构造子类对象
         *
         * @param tag 键值对中的值是什么类型
         */
        protected element_value(int tag) {
            this.tag = tag;
        }

        /**
         * 获取键值对中值所占的长度
         *
         * @return 键值对中值所占的字节长度
         */
        public abstract int length();

        /**
         * 用来表示键值对中的值是什么类型
         */
        public final int tag;
    }

    /**
     * 键值对中的值是个 基本类型或String
     */
    public static class constant_element_value extends element_value {
        /**
         * 利用 class 读取类加载 constant_element_value 类型的对象
         *
         * @param cr  class 读取类
         * @param tag 表示类型的 tag
         * @throws IOException 读取时发生 io 异常
         */
        constant_element_value(ClassReader cr, int tag) throws IOException {
            super(tag);
            constant_value_index = cr.readUnsignedShort();
        }

        @Override
        public int length() {
            return 2;
        }

        /**
         * 在常量池中，基本常量类型 或是 utf8 结构的索引
         */
        public final int constant_value_index;
    }

    /**
     * 键值对中的值为枚举类型
     */
    public static class enum_element_value extends element_value {
        /**
         * 利用 class 读取类加载 enum_element_value 类型的对象
         *
         * @param cr  class 读取类
         * @param tag 表示类型的 tag
         * @throws IOException 读取时发生 io 异常
         */
        enum_element_value(ClassReader cr, int tag) throws IOException {
            super(tag);
            type_name_index = cr.readUnsignedShort();
            constant_name_index = cr.readUnsignedShort();
        }

        @Override
        public int length() {
            return 4;
        }

        /**
         * 常量池中 utf8 类型的有效索引,用以表示一个有效的字段描述符<br>
         * 字段描述符给出了当前 element_value 结构所表示的枚举类型常量的二进制名称的内部形式。
         */
        public final int type_name_index;
        /**
         * 常量池中 utf8 类型的有效索引<br>
         * 表示当前结构所表示的枚举常量的简单名称。
         */
        public final int constant_name_index;
    }

    public static class class_element_value extends element_value {
        class_element_value(ClassReader cr, int tag) throws IOException {
            super(tag);
            class_info_index = cr.readUnsignedShort();
        }

        @Override
        public int length() {
            return 2;
        }

        public final int class_info_index;
    }

    public static class annotation_element_value extends element_value {
        public annotation_element_value(ClassReader cr, int tag) throws IOException {
            super(tag);
            annotation_value = new Annotation(cr);
        }

        @Override
        public int length() {
            return annotation_value.length();
        }

        public final Annotation annotation_value;
    }

    public static class array_element_vlaue extends element_value {
        public array_element_vlaue(ClassReader cr, int tag) throws IOException {
            super(tag);
            num_values = cr.readUnsignedShort();
            values = new element_value[num_values];
            for (int i = 0; i < num_values; ++i) {
                values[i] = element_value.read(cr);
            }
        }

        @Override
        public int length() {
            int n = 2;
            for (int i = 0; i < values.length; i++)
                n += values[i].length();
            return n;
        }

        public final int num_values;
        public final element_value[] values;
    }
}
