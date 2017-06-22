package cc.chavaw.jvm.dataStructs.attribute;

import java.io.IOException;
import java.io.InputStream;

/**
 * Code 是变长属性，只存在与 method_info 结构中。Code 存放字节码方法相关信息
 * Created by 13969 on 2017/6/23.
 */
public class CodeAttribute extends AttributeInfo{
    private short maxStack;
    private short maxLocals;
    private int codeLength;
    private byte[] code;
    private short exceptionTableLength;
    private short[] exceptionTable;
    private short attributeCount;
    private AttributeInfo[] attributes;
    public CodeAttribute(InputStream in) throws IOException {
        super(in);


    }
}
