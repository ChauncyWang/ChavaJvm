package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 【使用位置】Code属性 <br>
 * 【含   义】该属性会在jvm类加载的字节码验证阶段被检查验证器使用<br>
 * code 属性中最多可以包含一个 StackMapTable 属性。<br>
 * 在版本号大于等于50的class文件中，如果方法的code属性中没有附带StackMapTable属性<br>
 * 那么就以为着它带有一个隐式的栈映射属性(StackMap)<br>
 * 这个隐式的栈映射属性的作用等同于 number_of_entries 值为0的StackMapTable属性。<br>
 * 【注   意】栈帧映射的类型中的 local 、 stack 和 运行时栈帧中的操作数栈、局部变量表有映射关系，但并非同一东西。
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
     * 异常:无效的 stack map
     */
    static class InvalidStackMap extends AttributeException {
        InvalidStackMap(String msg) {
            super(msg);
        }
    }

    /**
     * StackMapTable 属性所包含的 栈映射帧<br>
     * 用于表示该执行到该字节码时局部变量表和操作数帧的验证类型<br>
     * 栈帧映射 显示或是隐式的指定了某个字节码偏移量，用来表示该帧所针对的字节码的位置,<br>
     * 并且指定了此偏移量处的局部变量和操作数栈顶项所需的核查类型。
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
         *
         * @return frame 的字节码偏移量
         */
        public abstract int getOffsetDelta();

        /**
         * 获取栈映射帧的长度
         *
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

    /**
     * 栈帧类型标记的取值范围为 0~63 。如果类型所标记的帧类型是 same_frame 类型，则表明:<br>
     * 当前帧拥有和前一栈帧映射完全相同的 local[] 表，并且对应的 stack[] 表的成员个数为0。<br>
     * 当前帧的 offset_delta 值就是 frame_type 项的值来表示。
     */
    public class same_frame extends stack_map_frame {

        public same_frame(int frame_type) {
            super(frame_type);
        }

        @Override
        public int getOffsetDelta() {
            return frame_type;
        }
    }

    /**
     * 栈帧类型标记的取值范围为 64~127 。如果标记该类型,则说明:<br>
     * 当前栈帧拥有和前一栈帧映射完全相同的 local[] 表，并且对应的 stack[] 表的成员个数为1。<br>
     * 当前栈帧映射的 offset_delta 值就是 frame_type - 64。<br>
     * 并且有一个 verification_type_info 项跟随在此映射帧类型之后，就是表示那一个栈项的成员。
     */
    public static class same_locals_1_stack_item_frame extends stack_map_frame {
        public same_locals_1_stack_item_frame(int frame_type, ClassReader cr) throws IOException, InvalidStackMap {
            super(frame_type);
            stacks = new verification_type_info[1];
            stacks[0] = verification_type_info.read(cr);
        }

        @Override
        public int getOffsetDelta() {
            return frame_type - 64;
        }

        @Override
        public int length() {
            return super.length() + stacks.length;
        }

        public final verification_type_info[] stacks;
    }

    // 范围 128~246 的类型标记值是为未来使用而预留的。

    /**
     * 栈帧类型标记的取值为 247 。如果标记该类型,则说明:<br>
     * 当前栈帧拥有和前一栈帧映射完全相同的 local[] 表，并且对应的 stack[] 表的成员个数为1。<br>
     * 与 same_locals_1_stack_item_frame 不同，当前映射帧的 offset_delta 的值需要明确指定。<br>
     * 有个一 stack[] 表的成员在 offset_delta 之后。
     */
    public static class same_locals_1_stack_item_frame_extended extends stack_map_frame {
        public same_locals_1_stack_item_frame_extended(int frame_type, ClassReader cr) throws IOException, InvalidStackMap {
            super(frame_type);
            offset_delta = cr.readUnsignedShort();
            stacks = new verification_type_info[1];
            stacks[0] = verification_type_info.read(cr);
        }

        @Override
        public int getOffsetDelta() {
            return offset_delta;
        }

        public final int offset_delta;
        public final verification_type_info[] stacks;
    }


    /**
     * 类型标记的取值范围为 248~250。<br>
     * 如果类型标记所确定的类型是 chop_frame 则说明<br>
     * 对应的操作数栈为空，并且拥有和前一个栈帧映射相同的 local[] 表，但是该表缺少 k 个 local 项。<br>
     * k = 251 - frame_type。这种类型的帧会明确给出 offset_delta 项。
     */
    public static class chop_frame extends stack_map_frame {
        public chop_frame(int frame_type, ClassReader cr) throws IOException, InvalidStackMap {
            super(frame_type);
            offset_delta = cr.readUnsignedShort();
            locals = new verification_type_info[251 - frame_type];
            for (int i = 0; i < 251 - frame_type; ++i) {
                locals[i] = verification_type_info.read(cr);
            }

        }

        @Override
        public int getOffsetDelta() {
            return offset_delta;
        }

        public final int offset_delta;
        public final verification_type_info[] locals;
    }

    /**
     * 栈帧类型标记的取值 251 。如果类型所标记的帧类型是 same_frame_extended 类型，则表明:<br>
     * 当前帧拥有和前一栈帧映射完全相同的 local[] 表，并且对应的 stack[] 表的成员个数为0。<br>
     * 与 same_frame 不同，该类型需要明确指出 offset_delta 的值。
     */
    public static class same_frame_extended extends stack_map_frame {
        public same_frame_extended(int frame_type, ClassReader cr) throws IOException {
            super(frame_type);
            offset_delta = cr.readUnsignedShort();
        }

        @Override
        public int getOffsetDelta() {
            return offset_delta;
        }

        public final int offset_delta;
    }

    /**
     * 类型标记的取值范围为 252~254。<br>
     * 如果类型标记所确定的类型是 append_frame 则说明<br>
     * 对应的操作数栈为空，并且拥有和前一个栈帧映射相同的 local[] 表，但是该表追加 k 个 local 项。<br>
     * k = frame_type - 251。这种类型的帧会明确给出 offset_delta 项。
     */
    public static class append_frame extends stack_map_frame {
        public append_frame(int frame_type, ClassReader cr) throws IOException, InvalidStackMap {
            super(frame_type);
            offset_delta = cr.readUnsignedShort();
            locals = new verification_type_info[frame_type - 251];
            for (int i = 0; i < frame_type - 251; ++i) {
                locals[i] = verification_type_info.read(cr);
            }

        }

        @Override
        public int getOffsetDelta() {
            return offset_delta;
        }

        public final int offset_delta;
        public final verification_type_info[] locals;
    }

    /**
     * 类型标记为255，所有信息都要明确指出。
     */
    public static class full_frame extends stack_map_frame {
        public full_frame(int frame_type, ClassReader cr) throws IOException, InvalidStackMap {
            super(frame_type);
            offset_delta = cr.readUnsignedShort();
            int num = cr.readUnsignedShort();
            stacks = new verification_type_info[num];
            for (int i = 0; i < num; ++i) {
                stacks[i] = verification_type_info.read(cr);
            }
            num = cr.readUnsignedShort();
            locals = new verification_type_info[num];
            for (int i = 0; i < num; ++i) {
                locals[i] = verification_type_info.read(cr);
            }
        }

        @Override
        public int getOffsetDelta() {
            return offset_delta;
        }

        public final int offset_delta;
        public final verification_type_info[] stacks;
        public final verification_type_info[] locals;
    }

    /**
     * 核查类型
     */
    public static class verification_type_info {
        public static final int ITEM_Top = 0;
        public static final int ITEM_Integer = 1;
        public static final int ITEM_Float = 2;
        public static final int ITEM_Double = 3;
        public static final int ITEM_Long = 4;
        public static final int ITEM_Null = 5;
        public static final int ITEM_UninitializedThis = 6;
        public static final int ITEM_Object = 7;
        public static final int ITEM_Uninitialized = 8;

        verification_type_info(int tag) {
            this.tag = tag;
        }

        /**
         * 利用 classReader 构造 verification_type_info
         *
         * @param cr class 读取类
         * @return verification_type_info
         * @throws IOException     读取发生 io 异常
         * @throws InvalidStackMap verification_type_info 的 tag 是无效的。
         */
        static verification_type_info read(ClassReader cr) throws IOException, InvalidStackMap {
            int tag = cr.readUnsignedByte();
            switch (tag) {
                case ITEM_Top:
                case ITEM_Integer:
                case ITEM_Float:
                case ITEM_Double:
                case ITEM_Long:
                case ITEM_Null:
                case ITEM_UninitializedThis:
                    return new verification_type_info(tag);
                case ITEM_Object:
                    return new Object_variable_info(cr);
                case ITEM_Uninitialized:
                    return new Uninitialized_variable_info(cr);
                default:
                    throw new InvalidStackMap("无效的 verification_type_info 标签:" + tag);
            }
        }

        public int length() {
            return 1;
        }

        public final int tag;
    }

    /**
     * 核查类型为某个类
     */
    public static class Object_variable_info extends verification_type_info {
        Object_variable_info(ClassReader cr) throws IOException {
            super(ITEM_Object);
            cpool_index = cr.readUnsignedShort();
        }

        @Override
        public int length() {
            return super.length() + 2;
        }

        /**
         * 对 CONSTANT_Class_info 结构的索引
         */
        public final int cpool_index;
    }

    public static class Uninitialized_variable_info extends verification_type_info {
        Uninitialized_variable_info(ClassReader cr) throws IOException {
            super(ITEM_Uninitialized);
            offset = cr.readUnsignedShort();
        }

        @Override
        public int length() {
            return super.length() + 2;
        }

        public final int offset;
    }
}
