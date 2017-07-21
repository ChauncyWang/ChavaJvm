package cc.chavaw.jvm.classfile.visitor;

import cc.chavaw.jvm.classfile.ConstantPool;
import org.apache.commons.lang3.StringUtils;

/**
 * 简单的一个 ConstantPool 访问者类型<br>
 */
public class SimpleConstantPoolVisitor implements ConstantPool.Visitor<String, Integer> {

    @Override
    public String visitClass(ConstantPool.CONSTANT_Class_info class_info, Integer integer) {
        try {
            return class_info.cp.getUTF8Value(class_info.name_index);
        } catch (ConstantPool.InvalidIndexException e) {
            e.printStackTrace();
        } catch (ConstantPool.UnexpectedTagException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitDouble(ConstantPool.CONSTANT_Double_info double_info, Integer integer) {
        return StringUtils.rightPad("" + double_info.value, integer);
    }

    @Override
    public String visitFieldref(ConstantPool.CONSTANT_Fieldref_info fieldref_info, Integer integer) {
        ConstantPool cp = fieldref_info.cp;
        try {
            return String.format("%s.%s", visitClass(cp.getClassInfo(fieldref_info.class_index), integer),
                    visitNameAndType(cp.getNameAndTypeInfo(fieldref_info.name_and_type_index), integer));
        } catch (ConstantPool.InvalidIndexException e) {
            e.printStackTrace();
        } catch (ConstantPool.UnexpectedTagException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String visitFloat(ConstantPool.CONSTANT_Float_info float_info, Integer integer) {
        return StringUtils.rightPad("" + float_info.value, integer);
    }

    @Override
    public String visitInteger(ConstantPool.CONSTANT_Integer_info integer_info, Integer integer) {
        return StringUtils.rightPad("" + integer_info.value, integer);
    }

    @Override
    public String visitInterfaceMethodref(ConstantPool.CONSTANT_InterfaceMethodref_info interfaceMethodref_info, Integer integer) {
        ConstantPool cp = interfaceMethodref_info.cp;
        try {
            return String.format("%s.%s", visitClass(cp.getClassInfo(interfaceMethodref_info.class_index), integer),
                    visitNameAndType(cp.getNameAndTypeInfo(interfaceMethodref_info.name_and_type_index), integer));
        } catch (ConstantPool.InvalidIndexException e) {
            e.printStackTrace();
        } catch (ConstantPool.UnexpectedTagException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitInvokeDynamic(ConstantPool.CONSTANT_InvokeDynamic_info invokeDynamic_info, Integer integer) {
        return null;
    }

    @Override
    public String visitLong(ConstantPool.CONSTANT_Long_info long_info, Integer integer) {
        return StringUtils.rightPad("" + long_info.value, integer);
    }

    @Override
    public String visitMethodHandle(ConstantPool.CONSTANT_MethodHandle_info methodHandle_info, Integer integer) {
        ConstantPool cp = methodHandle_info.cp;
        String name = methodHandle_info.reference_kind.name;
        return null;
    }

    @Override
    public String visitMethodType(ConstantPool.CONSTANT_MethodType_info methodType_info, Integer integer) {
        try {
            return methodType_info.cp.getUTF8Value(methodType_info.descriptor_index);
        } catch (ConstantPool.InvalidIndexException e) {
            e.printStackTrace();
        } catch (ConstantPool.UnexpectedTagException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitMethodRef(ConstantPool.CONSTANT_Methodref_info methodref_info, Integer integer) {
        ConstantPool cp = methodref_info.cp;
        try {
            return String.format("%s.%s", visitClass(cp.getClassInfo(methodref_info.class_index), integer),
                    visitNameAndType(cp.getNameAndTypeInfo(methodref_info.name_and_type_index), integer));
        } catch (ConstantPool.InvalidIndexException e) {
            e.printStackTrace();
        } catch (ConstantPool.UnexpectedTagException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitNameAndType(ConstantPool.CONSTANT_NameAndType_info nameAndType_info, Integer integer) {
        int name_index = nameAndType_info.name_index;
        int type_index = nameAndType_info.type_index;
        ConstantPool cp = nameAndType_info.cp;
        String name = null;
        String type = null;
        try {
            name = cp.getUTF8Value(name_index);
            type = cp.getUTF8Value(type_index);
        } catch (ConstantPool.InvalidIndexException e) {
            e.printStackTrace();
        } catch (ConstantPool.UnexpectedTagException e) {
            e.printStackTrace();
        }
        return String.format("%s:%s", name, type);
    }

    @Override
    public String visitString(ConstantPool.CONSTANT_String_info string_info, Integer integer) {
        String string = StringUtils.rightPad("#" + string_info.string_index, integer);
        try {
            string = string + "//  " + string_info.cp.getUTF8Value(string_info.string_index);
        } catch (ConstantPool.InvalidIndexException e) {
            e.printStackTrace();
        } catch (ConstantPool.UnexpectedTagException e) {
            e.printStackTrace();
        }

        return string;
    }

    @Override
    public String visitUTF8(ConstantPool.CONSTANT_Utf8_info utf8_info, Integer integer) {
        return utf8_info.value;
    }
}