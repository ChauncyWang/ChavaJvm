package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * 域常量池
 * Created by 13969 on 2017/6/13.
 */
public class ConstantFieldrefInfo implements ConstantPoolInfo {
    /** 指向声明字段的类或者接口描述符CONSTANT_CLASS_INFO的索引项 */
    public short index1;
    /** 指向声明字段描述符 CONSTANT_NameAndType_Info 的索引项 */
    public short index2;

    public ConstantFieldrefInfo(InputStream in) throws IOException {
        index1 = getShort(in);
        index2 = getShort(in);
    }
}
