package cc.chavaw.jvm.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static cc.chavaw.jvm.Internationalization.I;

/**
 * 常量池
 * Created by chava on 17-7-9.
 */
public class ConstantPool {
    /**
     * 从　ClassReader 读取所有的常量池
     *
     * @param cr classReader
     * @throws Exception 发生各种异常
     */
    ConstantPool(ClassReader cr) throws Exception {
        // 常量池大小
        int count = cr.readUnsignedShort();
        pool = new CPInfo[count];
        for (int i = 1; i < count; i++) {
            // 下一个常量类型　tag
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
                    throw new UnexpectedTagException(i, tag);
            }
        }
    }

    /**
     * 常量池大小
     *
     * @return 常量池的大小
     */
    public int size() {
        return pool.length;
    }

    /**
     * 常量池大小
     *
     * @return 常量池所占字节数
     */
    public int byteLength() {
        int length = 2;
        for (int i = 1; i < size(); ) {
            CPInfo cpInfo = pool[i];
            length += cpInfo.byteLength();
            i += cpInfo.size();
        }
        return length;
    }

    /**
     * 访问者借口
     */
    public interface Visitor {
        void visitClass(CONSTANT_Class_info class_info);
        void visitDouble(CONSTANT_Double_info double_info);
        void visitFieldref(CONSTANT_Fieldref_info fieldref_info);
        void visitFloat(CONSTANT_Float_info float_info);
        void visitInteger(CONSTANT_Integer_info integer_info);
        void visitInterfaceMethodref(CONSTANT_InterfaceMethodref_info interfaceMethodref_info);
        void visitInvokeDynamic(CONSTANT_InvokeDynamic_info invokeDynamic_info);
        void visitLong(CONSTANT_Long_info long_info);
        void visitMethodHandle(CONSTANT_MethodHandle_info methodHandle_info);
        void visitMethodType(CONSTANT_MethodType_info methodType_info);
        void visitMethodRef(CONSTANT_Methodref_info methodref_info);
        void visitNameAndType(CONSTANT_NameAndType_info nameAndType_info);
        void visitString(CONSTANT_String_info string_info);
        void visitUTF8(CONSTANT_Utf8_info utf8_info);
    }


    /**
     * 索引异常:无效的索引
     */
    public static class InvalidIndexException extends ConstantPoolException {
        public InvalidIndexException(int index) {
            super(index);
        }

        @Override
        public String getMessage() {
            return I("cp.e.invalid_index", index);
        }
    }

    /**
     * 不支持的常量池　tag
     */
    public static class UnexpectedTagException extends ConstantPoolException {
        public UnexpectedTagException(int index, int tag) {
            super(index);
            this.tag = tag;
        }

        @Override
        public String getMessage() {
            return I("cp.e.unexpected_tag", index, tag);
        }

        public final int tag;
    }


    /**
     * 常量池中所有常量类型的抽象父类
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
         * 访问者 accept 函数
         * @param visitor 访问者
         */
        public abstract void accept(Visitor visitor);

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
     * 常量池中所有引用常量的父类
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

        /**
         * 构造函数
         *
         * @param cp  常量池
         * @param cr  classFile读取类
         * @param tag 　常量的标志
         * @throws IOException 　发生io异常
         */
        public CPRefInfo(ConstantPool cp, ClassReader cr, int tag) throws IOException {
            super(cp);
            this.tag = tag;
            class_index = cr.readUnsignedShort();
            name_and_type_index = cr.readUnsignedShort();
        }

        public CPRefInfo(int tag, int class_index, int name_and_type_index) {
            this.tag = tag;
            this.class_index = class_index;
            this.name_and_type_index = name_and_type_index;
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
            return I("cp.s.ref", class_index, name_and_type_index);
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

        /** 利用常量池和Class读取类构造
         * @param cp 常量池
         * @param cr class 读取类
         * @throws IOException 发生IO异常
         */
        public CONSTANT_Class_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp);
            name_index = cr.readUnsignedShort();
        }

        /**
         * 测试用构造函数
         *
         * @param name_index 　名字在常量池中的索引
         */
        public CONSTANT_Class_info(int name_index) {
            this.name_index = name_index;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitClass(this);
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
            return I("cp.s.class", name_index);
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

        public CONSTANT_Double_info(double value) {
            this.value = value;
        }

        @Override
        public int size() {
            return 2;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitDouble(this);
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
            return I("cp.s.double", value);
        }
    }

    /**
     * 域 常量
     */
    public static class CONSTANT_Fieldref_info extends CPRefInfo {

        public CONSTANT_Fieldref_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp, cr, CONSTANT_Fieldref);
        }

        public CONSTANT_Fieldref_info(int tag, int class_index, int name_and_type_index) {
            super(tag, class_index, name_and_type_index);
        }

        @Override
        public String toString() {
            return I("cp.s.fieldref", super.toString());
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitFieldref(this);
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

        public CONSTANT_Float_info(float value) {
            this.value = value;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitFloat(this);
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
            return I("cp.s.float", value);
        }
    }

    /**
     * 整形 常量
     */
    public static class CONSTANT_Integer_info extends CPInfo {
        private final int value;

        public CONSTANT_Integer_info(ClassReader cr) throws IOException {
            value = cr.readInt();
        }

        public CONSTANT_Integer_info(int value) {
            super();
            this.value = value;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitInteger(this);
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
            return I("cp.s.integer", value);
        }
    }

    /**
     * 接口中方法的符号引用
     */
    public static class CONSTANT_InterfaceMethodref_info extends CPRefInfo {
        public CONSTANT_InterfaceMethodref_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp, cr, CONSTANT_InterfaceMethodref);
        }

        public CONSTANT_InterfaceMethodref_info(int tag, int class_index, int name_and_type_index) {
            super(tag, class_index, name_and_type_index);
        }

        @Override
        public String toString() {
            return I("cp.s.interface_methodref", super.toString());
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitInterfaceMethodref(this);
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
        public void accept(Visitor visitor) {
            visitor.visitInvokeDynamic(this);
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

        @Override
        public void accept(Visitor visitor) {
            visitor.visitLong(this);
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
            return I("cp.s.long", value);
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

        public CONSTANT_MethodHandle_info(RefKind reference_kind, int reference_index) {
            this.reference_kind = reference_kind;
            this.reference_index = reference_index;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitMethodHandle(this);
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
            return I("cp.s.method_handle", reference_kind, reference_index);
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

        @Override
        public void accept(Visitor visitor) {
            visitor.visitMethodType(this);
        }

        public int getTag() {
            return CONSTANT_MethodType;
        }

        public int byteLength() {
            return 3;
        }

        @Override
        public String toString() {
            return I("cp.s.method_type", descriptor_index);
        }
    }

    /**
     * 声明方法的信息
     */
    public static class CONSTANT_Methodref_info extends CPRefInfo {
        CONSTANT_Methodref_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp, cr, CONSTANT_Methodref);
        }

        public CONSTANT_Methodref_info(int tag, int class_index, int name_and_type_index) {
            super(tag, class_index, name_and_type_index);
        }

        @Override
        public String toString() {
            return I("cp.s.methodref", super.toString());
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitMethodRef(this);
        }
    }

    /**
     * 字段的名字和类型
     */
    public static class CONSTANT_NameAndType_info extends CPInfo {
        CONSTANT_NameAndType_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp);
            name_index = cr.readUnsignedShort();
            type_index = cr.readUnsignedShort();
        }

        public CONSTANT_NameAndType_info(int name_index, int type_index) {
            this.name_index = name_index;
            this.type_index = type_index;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitNameAndType(this);
        }

        public int getTag() {
            return CONSTANT_NameAndType;
        }

        public int byteLength() {
            return 5;
        }

        @Override
        public String toString() {
            return I("cp.s.name_and_type", name_index, type_index);
        }

        /**
         * 指向字段或方法名称常量的索引
         */
        public final int name_index;
        /**
         * 指向字段或方法描述符常量的索引
         */
        public final int type_index;
    }

    /**
     * 字符串常量
     */
    public static class CONSTANT_String_info extends CPInfo {
        public CONSTANT_String_info(ConstantPool cp, ClassReader cr) throws IOException {
            super(cp);
            string_index = cr.readUnsignedShort();
        }

        public CONSTANT_String_info(int string_index) {
            this.string_index = string_index;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitString(this);
        }

        public int getTag() {
            return CONSTANT_String;
        }

        public int byteLength() {
            return 3;
        }

        @Override
        public String toString() {
            return I("cp.s.string", string_index);
        }
        /**
         * 字符串常量在常量池中的索引(utf8编码的字符串)
         */
        public final int string_index;
    }

    /**
     * utf8编码的字符串
     */
    public static class CONSTANT_Utf8_info extends CPInfo {
        public final String value;

        public CONSTANT_Utf8_info(ClassReader cr) throws IOException {
            value = cr.readUTF();
        }

        public CONSTANT_Utf8_info(String value) {
            this.value = value;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitUTF8(this);
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
             return I("cp.s.utf8", value);
        }
    }

    /**
     * 方法句柄中的方法类型
     */
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

    private final CPInfo[] pool;
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
