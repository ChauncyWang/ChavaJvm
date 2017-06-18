package cc.chavaw.jvm.dataStructs;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTools.getInteger;

/**
 * 字段名字和类型
 * Created by 13969 on 2017/6/13.
 */
public class ConstantNameAndTypeInfo {
    /** 指向字段或方法名称常量的索引  */
    public int index1;
    /** 指向字段或方法描述符常量的索引 */
    public int index2;

    public ConstantNameAndTypeInfo(InputStream in) throws IOException {
        index1 = getInteger(in,2);
        index2 = getInteger(in,2);
    }
}
