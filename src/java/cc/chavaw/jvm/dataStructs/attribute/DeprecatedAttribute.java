package cc.chavaw.jvm.dataStructs.attribute;

import java.io.IOException;
import java.io.InputStream;

/**
 * Deprecated属性，指出类，接口，字段，方法不推荐使用
 * Created by 13969 on 2017/6/22.
 */
public class DeprecatedAttribute extends AttributeInfo{
    public DeprecatedAttribute(InputStream in) throws IOException {
       super(in);
        if(attributeLength != 0) {
            throw new RuntimeException("Deprecated属性不包含任何数据!");
        }
    }
}
