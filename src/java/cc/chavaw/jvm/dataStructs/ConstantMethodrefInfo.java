package cc.chavaw.jvm.dataStructs;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTools.getInteger;

/**
 *  声明方法的信息
 * Created by 13969 on 2017/6/13.
 */
public class ConstantMethodrefInfo {
    /** 指向声明方法的类描述符 CONSTANT_CLASS_INFO 的索引项 */
    public int index1;
    /** 指向声明方法的类描述符 CONSTANT_NameAndType_Info 的索引项 */
    public int index2;

    public ConstantMethodrefInfo(InputStream in) throws IOException {
        index1 = getInteger(in,2);
        index2 = getInteger(in,2);
    }
}
