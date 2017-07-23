package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 【使用位置】Code属性 <br>
 * 【含   义】该属性会在jvm类加载的字节码验证阶段被检查验证器使用<br>
 * code 属性中最多可以包含一个 StackMapTable 属性。<br>
 * 在版本号大于等于50的class文件中，如果方法的code属性中没有附带StackMapTable属性<br>
 * 那么就以为着它带有一个隐式的栈映射属性(StackMap)<br>
 * 这个隐式的栈映射属性的作用等同于 number_of_entries 值为0的StackMapTable属性。
 */
public class StackMapTable_attribute extends Attribute {
    /**
     * 构造函数
     *
     * @param cr                   class 读取类
     * @param attribute_name_index 　属性名称索引
     * @param attribute_length     属性的长度
     */
    public StackMapTable_attribute(ClassReader cr, int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }

    /**
     * StackMapTable 属性所包含的 栈映射帧<br>
     * 用于表示该执行到该字节码时局部变量表和操作数帧的验证类型
     */
    public static abstract class stack_map_frame {
        /**
         * 利用 Class 读取类读取一个 stack_map_frame
         *
         * @param cr class 读取类
         * @return 读取到的 stack_map_frame 对象
         * @throws IOException 读取时发生IO异常
         */
        static stack_map_frame read(ClassReader cr) throws IOException {
            int frame_type = cr.readUnsignedByte();
            return null;
        }

        protected stack_map_frame(int frame_type) {
            this.frame_type = frame_type;
        }

        /**
         * 获取 frame 的字节码偏移量
         * @return frame 的字节码偏移量
         */
        public abstract int getOffsetDelta();

        /**
         * 获取栈映射帧的长度
         * @return 栈映射帧的长度
         */
        public int length() {
            return 1;
        }

        /**
         * 栈映射帧的类型
         */
        public final int frame_type;
    }

    public class same_frame extends stack_map_frame {

        public same_frame(int frame_type) {
            super(frame_type);
        }

        @Override
        public int getOffsetDelta() {
            return frame_type;
        }
    }

    public static class same_locals_1_stack_item_frame extends stack_map_frame {
        public same_locals_1_stack_item_frame(int frame_type, ClassReader cr) {
            super(frame_type);
            stacks = new verification_type_info[10];
        }

        @Override
        public int getOffsetDelta() {
            return frame_type-64;
        }

        @Override
        public int length() {
            return super.length() + stacks.length;
        }

        public final verification_type_info[] stacks;
    }


    public static class verification_type_info {
        public static final int ITEM_Top = 0;
        public static final int ITEM_Integer = 1;
    }
}
