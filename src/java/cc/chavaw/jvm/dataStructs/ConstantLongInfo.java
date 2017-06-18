package cc.chavaw.jvm.dataStructs;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTools.getLong;

/**
 * 按照高位在前存储的 long 值
 * Created by 13969 on 2017/6/13.
 */
public class ConstantLongInfo {
    public long value;

    public ConstantLongInfo(InputStream in) throws IOException {
        value = getLong(in,8);
    }
}
