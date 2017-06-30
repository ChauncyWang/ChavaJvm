package cc.chavaw.jvm.dataStructs.attribute;

import java.io.IOException;
import java.io.InputStream;

/**
 * Synthetic源文件中不存在，由编译器生成的类成员
 * Created by 13969 on 2017/6/22.
 */
public class SyntheticAttribute extends AttributeInfo {
    public SyntheticAttribute(InputStream in) throws IOException {
        super(in);
        if (attributeLength != 0) {
            throw new RuntimeException("Synthetic属性不包含任何数据!");
        }
    }
}
