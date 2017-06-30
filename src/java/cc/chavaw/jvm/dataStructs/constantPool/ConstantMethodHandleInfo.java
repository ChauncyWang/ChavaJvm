package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * 方法句柄
 * Created by 13969 on 2017/6/30.
 */
public class ConstantMethodHandleInfo implements ConstantPoolInfo{
    /** 范围必须在[1,9] 之内，表示方法句柄的类型 */
    private byte referenceKind;
    /** 对常量池的有效索引 */
    private short referenceIndex;
    public ConstantMethodHandleInfo(InputStream in) throws IOException {
        referenceKind = (byte) in.read();
        referenceIndex = getShort(in);
    }
}
