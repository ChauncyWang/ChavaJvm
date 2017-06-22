package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getLong;

/**
 * Double数字常量 用8个字节存储 IEEE754 双精度浮点数
 * Created by 13969 on 2017/6/13.
 */
public class ConstantDoubleInfo implements ConstantPoolInfo {
    public double value;

    public ConstantDoubleInfo(InputStream in) throws IOException {
        value = Double.longBitsToDouble(getLong(in));
    }
}
