package cc.chavaw.jvm.dataStructs;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTools.getInteger;

/**
 * 全限定类名常量
 * Created by 13969 on 2017/6/13.
 */
public class ConstantClassInfo {
    /** 全限定类名常量 的索引 */
    public int index;
    public ConstantClassInfo(InputStream in) throws IOException {
        index = getInteger(in,2);
    }
}
