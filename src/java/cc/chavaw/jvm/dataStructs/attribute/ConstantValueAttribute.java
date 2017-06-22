package cc.chavaw.jvm.dataStructs.attribute;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * 定长属性，只会出现在field属性中，用于表示常量表达式的值
 * Created by 13969 on 2017/6/23.
 */
public class ConstantValueAttribute extends AttributeInfo {
    /** 常量在常量池中的索引 */
    private short constantValueIndex;
    public ConstantValueAttribute(InputStream in) throws IOException {
        super(in);
        if(attributeLength != 2) {
            throw new RuntimeException("ConstantValue长度必须为2!");
        }
        constantValueIndex = getShort(in);
    }
}
