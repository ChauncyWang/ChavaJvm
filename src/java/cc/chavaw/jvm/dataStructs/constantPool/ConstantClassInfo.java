package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * 表示全限定类或接口的符号引用
 * Created by 13969 on 2017/6/13.
 */
public class ConstantClassInfo implements ConstantPoolInfo {
    /** 全限定类名常量 在常量池的索引 指向ConstantUtf8Info常量 */
    private short name_index;
    public ConstantClassInfo(InputStream in) throws IOException {
        name_index = getShort(in);
    }
}
