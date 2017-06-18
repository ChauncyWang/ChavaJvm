package cc.chavaw.jvm.dataStructs;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTools.getInteger;

/**
 * 按照高位在前存储的 float 值
 * Created by 13969 on 2017/6/13.
 */
public class ConstantFloatInfo {
    /** 存储的 float 值 */
    public float value;

    public ConstantFloatInfo(InputStream in) throws IOException {
        value = Float.intBitsToFloat(getInteger(in,4));
    }
}
