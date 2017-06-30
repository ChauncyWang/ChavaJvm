package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * 表示方法类型
 * Created by 13969 on 2017/6/30.
 */
public class ConstantMethodTypeInfo {
    private short descriptorIndex;
    public ConstantMethodTypeInfo(InputStream in) throws IOException {
        descriptorIndex = getShort(in);

    }
}
