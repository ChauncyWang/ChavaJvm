package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * 字符串常量
 * Created by 13969 on 2017/6/13.
 */
public class ConstantStringInfo implements ConstantPoolInfo {
    /** 字符串常量索引 */
    public short index;

    public ConstantStringInfo(InputStream in) throws IOException {
        index = getShort(in);
    }
}
