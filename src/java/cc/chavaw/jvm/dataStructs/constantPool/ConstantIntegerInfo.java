package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getInteger;

/**
 * 按照高位在前存储的int值
 * Created by 13969 on 2017/6/13.
 */
public class ConstantIntegerInfo implements ConstantPoolInfo{
    /** 读取的int 数值 */
    public int value;
    public ConstantIntegerInfo(InputStream in) throws IOException {
        value = getInteger(in);
    }
}
