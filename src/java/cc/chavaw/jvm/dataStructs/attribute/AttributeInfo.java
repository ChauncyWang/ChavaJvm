package cc.chavaw.jvm.dataStructs.attribute;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getInteger;
import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * 为了方便存储，所有属性表都必须继承该类
 * Created by 13969 on 2017/6/22.
 */
public class AttributeInfo {
    /** 属性名字在常量池的索引位置 */
    protected short attributeNameIndex;
    /** 属性包含信息的长度 */
    protected int attributeLength;

    public AttributeInfo(InputStream in) throws IOException {
        attributeNameIndex = getShort(in);
        attributeLength = getInteger(in);
    }
}