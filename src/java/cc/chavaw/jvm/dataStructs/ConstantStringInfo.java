package cc.chavaw.jvm.dataStructs;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTools.getInteger;

/**
 * 字符串常量
 * Created by 13969 on 2017/6/13.
 */
public class ConstantStringInfo {
    /** 字符串常量索引 */
    public int index;

    public ConstantStringInfo(InputStream in) throws IOException {
        index = getInteger(in,2);
    }
}
