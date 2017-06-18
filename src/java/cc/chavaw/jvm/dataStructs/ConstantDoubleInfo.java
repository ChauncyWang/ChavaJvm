package cc.chavaw.jvm.dataStructs;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTools.getLong;

/**
 *
 * Created by 13969 on 2017/6/13.
 */
public class ConstantDoubleInfo {
    public double value;

    public ConstantDoubleInfo(InputStream in) throws IOException {
        value = Double.longBitsToDouble(getLong(in,8));
    }
}
