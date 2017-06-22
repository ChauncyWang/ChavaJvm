package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * 字段名字和类型
 * Created by 13969 on 2017/6/13.
 */
public class ConstantNameAndTypeInfo implements ConstantPoolInfo {
    /** 指向字段或方法名称常量的索引  */
    public short index1;
    /** 指向字段或方法描述符常量的索引 */
    public short index2;

    public ConstantNameAndTypeInfo(InputStream in) throws IOException {
        index1 = getShort(in);
        index2 = getShort(in);
    }
}
