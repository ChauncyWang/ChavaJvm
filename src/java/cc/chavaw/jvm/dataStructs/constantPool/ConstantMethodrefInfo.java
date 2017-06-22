package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 *  声明方法的信息
 * Created by 13969 on 2017/6/13.
 */
public class ConstantMethodrefInfo implements ConstantPoolInfo {
    /** 指向声明方法的类描述符 CONSTANT_CLASS_INFO 的索引项 */
    public short index1;
    /** 指向声明方法的类描述符 CONSTANT_NameAndType_Info 的索引项 */
    public short index2;

    public ConstantMethodrefInfo(InputStream in) throws IOException {
        index1 = getShort(in);
        index2 = getShort(in);
    }
}
