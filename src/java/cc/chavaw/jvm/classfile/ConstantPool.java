package cc.chavaw.jvm.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 常量池
 * Created by chava on 17-7-9.
 */
public class ConstantPool {
    private final CPInfo[] pool;

    ConstantPool(ClassReader cr) throws Exception {
        int count = cr.readUnsignedShort();
        pool = new CPInfo[count];
        for (int i = 1; i < count; i++) {
            int tag = cr.readUnsignedByte();
            switch (tag) {
                case CONSTANT_Class:
                    pool[i] = new CONSTANT_Class_info(this, cr);
                    break;

                case CONSTANT_Double:
                    pool[i] = new CONSTANT_Double_info(cr);
                    i++;
                    break;

                case CONSTANT_Fieldref:
                    pool[i] = new CONSTANT_Fieldref_info(this, cr);
                    break;

                case CONSTANT_Float:
                    pool[i] = new CONSTANT_Float_info(cr);
                    break;

                case CONSTANT_Integer:
                    pool[i] = new CONSTANT_Integer_info(cr);
                    break;

                case CONSTANT_InterfaceMethodref:
                    pool[i] = new CONSTANT_InterfaceMethodref_info(this, cr);
                    break;

                case CONSTANT_InvokeDynamic:
                    pool[i] = new CONSTANT_InvokeDynamic_info(this, cr);
                    break;

                case CONSTANT_Long:
                    pool[i] = new CONSTANT_Long_info(cr);
                    i++;
                    break;

                case CONSTANT_MethodHandle:
                    pool[i] = new CONSTANT_MethodHandle_info(this, cr);
                    break;

                case CONSTANT_MethodType:
                    pool[i] = new CONSTANT_MethodType_info(this, cr);
                    break;

                case CONSTANT_Methodref:
                    pool[i] = new CONSTANT_Methodref_info(this, cr);
                    break;

                case CONSTANT_NameAndType:
                    pool[i] = new CONSTANT_NameAndType_info(this, cr);
                    break;

                case CONSTANT_String:
                    pool[i] = new CONSTANT_String_info(this, cr);
                    break;

                case CONSTANT_Utf8:
                    pool[i] = new CONSTANT_Utf8_info(cr);
                    break;

                default:
                    throw new Exception("第" + i + "项常量池,不存在的tag:" + tag);
            }
        }
    }

    public int size() {
        return pool.length;
    }

    public int byteLength() {
        int length = 2;
        for (int i = 1; i < size(); ) {
            CPInfo cpInfo = pool[i];
            length += cpInfo.byteLength();
            i += cpInfo.size();
        }
        return length;
    }

    public enum RefKind {
        REF_getField(1, "getfield"),
        REF_getStatic(2, "getstatic"),
        REF_putField(3, "putfield"),
        REF_putStatic(4, "putstatic"),
        REF_invokeVirtual(5, "invokevirtual"),
        REF_invokeStatic(6, "invokestatic"),
        REF_invokeSpecial(7, "invokespecial"),
        REF_newInvokeSpecial(8, "newinvokespecial"),
        REF_invokeInterface(9, "invokeinterface");

        public final int tag;
        public final String name;

        RefKind(int tag, String name) {
            this.tag = tag;
            this.name = name;
        }

        static RefKind getRefkind(int tag) {
            switch (tag) {
                case 1:
                    return REF_getField;
                case 2:
                    return REF_getStatic;
                case 3:
                    return REF_putField;
                case 4:
                    return REF_putStatic;
                case 5:
                    return REF_invokeVirtual;
                case 6:
                    return REF_invokeStatic;
                case 7:
                    return REF_invokeSpecial;
                case 8:
                    return REF_newInvokeSpecial;
                case 9:
                    return REF_invokeInterface;
                default:
                    return null;
            }
        }
    }

    /**
     * 常量池中所有常量的抽象父类
     */
    public static abstract class CPInfo {
        protected final ConstantPool cp;

        CPInfo() {
            this.cp = null;
        }

        CPInfo(ConstantPool cp) {
            this.cp = cp;
        }

        /**
         * 获取 当前常量池的 tag
         *
         * @return 前常量池的 tag
         */
        public abstract int getTag();

        /**
         * 获取 当前常量在常量池中所占的大小（除了 long 和 double info 该值都是 1)
         *
         * @return 当前常量在常量池中所占的大小
         */
        public int size() {
            return 1;
        }

        /**
         * 获取 当前常量在常量池中所占的字节长度
         *
         * @return 当前常量在常量池中所占的字节长度
         */
        public abstract int byteLength();
    }

    /**
     * 常量池中所有引用常量的抽象父类
     */
    public static abstract class CPRefInfo extends CPInfo {
        public final int tag;
        /**
         * 指向声明字段的类或者接口描述符CONSTANT_CLASS_INFO的索引项
         */
        public final int class_index;
        /**
         * 指向声明字段描述符 CONSTANT_NameAndType_Info 的索引项
         */
        public final int name_and_type_index;

        public CPRefInfo(ConstantPool cp, ClassReader cr, int tag) throws IOException {
            super(cp);
            this.tag = tag;
            class_index = cr.readUnsignedShort();
            name_and_type_index = cr.readUnsignedShort();
        }

        @Override
        public int getTag() {
            return tag;
        }

        @Override
        public int byteLength() {
            return 5;
        }

        @Override
        public String toString() {
            return "[class_index:" + class_index + ", name_and_type_index:" + name_and_type_index + "]";
        }
    }

    /**
     * 表示全限定类或接口的符号引用
     */
    public static class CONSTANT_Class_info extends CPInfo {
        /**
         * 全限定类名常量 在常量池的索引 指向ConstantUtf8Info常量
         */
        public final int name_index;

        public CONSTANT_Class_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp);
            name_index = cr.readUnsignedShort();
        }

        @Override
        public int getTag() {
            return CONSTANT_Class;
        }

        @Override
        public int byteLength() {
            return 3;
        }

        @Override
        public String toString() {
            return "CONSTANT_Class_info[name_index:" + name_index + ']';
        }
    }

    /**
     * Double数字常量 用8个字节存储 IEEE754 双精度浮点数
     */
    public static class CONSTANT_Double_info extends CPInfo {
        public final double value;

        public CONSTANT_Double_info(ClassReader cr) throws IOException {
            value = cr.readDouble();
        }

        @Override
        public int size() {
            return 2;
        }

        @Override
        public int getTag() {
            return CONSTANT_Double;
        }

        @Override
        public int byteLength() {
            return 9;
        }

        @Override
        public String toString() {
            return "CONSTANT_Double_info[value:" + value + "]";
        }
    }

    /**
     * 域 常量
     */
    public static class CONSTANT_Fieldref_info extends CPRefInfo {

        public CONSTANT_Fieldref_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp, cr, CONSTANT_Fieldref);
        }

        @Override
        public String toString() {
            return "CONSTANT_Fieldref_info" + super.toString();
        }
    }

    /**
     * 浮点型 常量
     */
    public static class CONSTANT_Float_info extends CPInfo {
        public final float value;

        public CONSTANT_Float_info(ClassReader cr) throws IOException {
            value = cr.readFloat();
        }

        @Override
        public int getTag() {
            return CONSTANT_Float;
        }

        @Override
        public int byteLength() {
            return 5;
        }

        @Override
        public String toString() {
            return "CONSTANT_Float_info[value:" + value + ']';
        }
    }

    /**
     * 整形 常量
     */
    private static class CONSTANT_Integer_info extends CPInfo {
        private final int value;

        public CONSTANT_Integer_info(ClassReader cr) throws IOException {
            value = cr.readInt();
        }

        @Override
        public int getTag() {
            return CONSTANT_Integer;
        }

        @Override
        public int byteLength() {
            return 5;
        }

        @Override
        public String toString() {
            return "CONSTANT_Integer_info[value:" + value + ']';
        }
    }

    /**
     * 接口方法
     */
    public static class CONSTANT_InterfaceMethodref_info extends CPRefInfo {
        CONSTANT_InterfaceMethodref_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp, cr, CONSTANT_InterfaceMethodref);
        }

        @Override
        public String toString() {
            return "CONSTANT_InterfaceMethodref_info" + super.toString();
        }
    }

    /**
     * 表示 invokeDynamic 指令所用到的引导方法、引导方法所用到的动态调用名称、参数和返回类型，
     * 并可以给引导传入 一系列成为静态常树的常量
     */
    public static class CONSTANT_InvokeDynamic_info extends CPInfo {
        /**
         * 当前 class 文件中引导方法表的 bootstrapMethods 数组的有效索引
         */
        private final int bootstrap_method_attr_index;
        /**
         * 对常量池的索引 该索引位置必须为 ConstantNameAndTypeInfo 结构
         */
        private final int name_and_type_index;

        CONSTANT_InvokeDynamic_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp);
            bootstrap_method_attr_index = cr.readUnsignedShort();
            name_and_type_index = cr.readUnsignedShort();
        }

        @Override
        public int getTag() {
            return CONSTANT_InvokeDynamic;
        }

        @Override
        public int byteLength() {
            return 5;
        }

        @Override
        public String toString() {
            return "CONSTANT_InvokeDynamic_info[bootstrap_method_index: " + bootstrap_method_attr_index + ", name_and_type_index: " + name_and_type_index + "]";
        }

    }

    /**
     * 长整形 常量
     */
    public static class CONSTANT_Long_info extends CPInfo {
        public final long value;

        CONSTANT_Long_info(ClassReader cr) throws IOException {
            value = cr.readLong();
        }

        public int getTag() {
            return CONSTANT_Long;
        }

        @Override
        public int size() {
            return 2;
        }

        public int byteLength() {
            return 9;
        }

        @Override
        public String toString() {
            return "CONSTANT_Long_info[value: " + value + "]";
        }
    }

    /**
     * 方法句柄
     */
    public static class CONSTANT_MethodHandle_info extends CPInfo {
        /**
         * 范围必须在[1,9] 之内，表示方法句柄的类型
         */
        public final RefKind reference_kind;
        /**
         * 对常量池的有效索引
         */
        public final int reference_index;

        CONSTANT_MethodHandle_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp);
            reference_kind = RefKind.getRefkind(cr.readUnsignedByte());
            reference_index = cr.readUnsignedShort();
        }

        @Override
        public int getTag() {
            return CONSTANT_MethodHandle;
        }

        @Override
        public int byteLength() {
            return 4;
        }

        @Override
        public String toString() {
            return "CONSTANT_MethodHandle_info[ref_kind: " + reference_kind + ", member_index: " + reference_index + "]";
        }
    }

    /**
     * 表示方法类型
     */
    public static class CONSTANT_MethodType_info extends CPInfo {
        public final int descriptor_index;

        CONSTANT_MethodType_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp);
            descriptor_index = cr.readUnsignedShort();
        }

        public int getTag() {
            return CONSTANT_MethodType;
        }

        public int byteLength() {
            return 3;
        }

        @Override
        public String toString() {
            return "CONSTANT_MethodType_info[signature_index: " + descriptor_index + "]";
        }
    }

    /**
     * 声明方法的信息
     */
    public static class CONSTANT_Methodref_info extends CPRefInfo {
        CONSTANT_Methodref_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp, cr, CONSTANT_Methodref);
        }

        @Override
        public String toString() {
            return "CONSTANT_Methodref_info" + super.toString();
        }
    }

    /**
     * 字段名字和类型
     */
    public static class CONSTANT_NameAndType_info extends CPInfo {
        /**
         * 指向字段或方法名称常量的索引
         */
        public final int name_index;
        /**
         * 指向字段或方法描述符常量的索引
         */
        public final int type_index;

        CONSTANT_NameAndType_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp);
            name_index = cr.readUnsignedShort();
            type_index = cr.readUnsignedShort();
        }

        public int getTag() {
            return CONSTANT_NameAndType;
        }

        public int byteLength() {
            return 5;
        }

        @Override
        public String toString() {
            return "CONSTANT_NameAndType_info[name_index: " + name_index + ", type_index: " + type_index + "]";
        }
    }

    /**
     * 字符串常量
     */
    public static class CONSTANT_String_info extends CPInfo {
        /**
         * 字符串常量在常量池中的索引(utf8编码的字符串)
         */
        public final int string_index;

        CONSTANT_String_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp);
            string_index = cr.readUnsignedShort();
        }

        public CONSTANT_String_info(ConstantPool cp, int string_index) {
            super(cp);
            this.string_index = string_index;
        }

        public int getTag() {
            return CONSTANT_String;
        }

        public int byteLength() {
            return 3;
        }

        @Override
        public String toString() {
            return "CONSTANT_String_info[class_index: " + string_index + "]";
        }
    }

    /**
     * utf8编码的字符串
     */
    public static class CONSTANT_Utf8_info extends CPInfo {
        public final String value;

        CONSTANT_Utf8_info(ClassReader cr) throws IOException {
            value = cr.readUTF();
        }

        public CONSTANT_Utf8_info(String value) {
            this.value = value;
        }

        static boolean isPrintableAscii(String s) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c < 32 || c >= 127)
                    return false;
            }
            return true;
        }

        @Override
        public int getTag() {
            return CONSTANT_Utf8;
        }

        @Override
        public int byteLength() {
            class SizeOutputStream extends OutputStream {
                int size;

                @Override
                public void write(int b) {
                    size++;
                }
            }
            SizeOutputStream sizeOut = new SizeOutputStream();
            DataOutputStream out = new DataOutputStream(sizeOut);
            try {
                out.writeUTF(value);
            } catch (IOException ignore) {
            }
            return 1 + sizeOut.size;
        }

        @Override
        public String toString() {
            if (value.length() < 32 && isPrintableAscii(value))
                return "CONSTANT_Utf8_info[value: \"" + value + "\"]";
            else
                return "CONSTANT_Utf8_info[value: (" + value.length() + " chars)]";
        }
    }

    public static final int CONSTANT_Utf8 = 1;
    public static final int CONSTANT_Integer = 3;
    public static final int CONSTANT_Float = 4;
    public static final int CONSTANT_Long = 5;
    public static final int CONSTANT_Double = 6;
    public static final int CONSTANT_Class = 7;
    public static final int CONSTANT_String = 8;
    public static final int CONSTANT_Fieldref = 9;
    public static final int CONSTANT_Methodref = 10;
    public static final int CONSTANT_InterfaceMethodref = 11;
    public static final int CONSTANT_NameAndType = 12;
    public static final int CONSTANT_MethodHandle = 15;
    public static final int CONSTANT_MethodType = 16;
    public static final int CONSTANT_InvokeDynamic = 18;
}
