package cc.chavaw.jvm.dataStructs;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTools.getInteger;

/**
 * 域常量池
 * Created by 13969 on 2017/6/13.
 */
public class ConstantFieldrefInfo {
    /** 指向声明字段的类或者接口描述符CONSTANT_CLASS_INFO的索引项 */
    public int index1;
    /** 指向声明字段描述符 CONSTANT_NameAndType_Info 的索引项 */
    public int index2;

    public ConstantFieldrefInfo(InputStream in) throws IOException {
        index1 = getInteger(in,2);
        index2 = getInteger(in,2);
    }
}
