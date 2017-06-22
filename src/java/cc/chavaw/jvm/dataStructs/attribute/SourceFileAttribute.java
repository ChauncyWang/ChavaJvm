package cc.chavaw.jvm.dataStructs.attribute;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * SourceFile是可选定长属性，只出现在ClassFile结构中，用于指出源文件文件名
 * Created by 13969 on 2017/6/22.
 */
public class SourceFileAttribute extends AttributeInfo {
    /** 文件名在常量池中的索引 */
    private short sourceFileIndex;
    public SourceFileAttribute(InputStream in) throws IOException {
        super(in);
        if(attributeLength != 2) {
            throw new RuntimeException("SourceFile属性长度必须为2！");
        }
        sourceFileIndex = getShort(in);
    }
}
