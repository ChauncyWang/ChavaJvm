package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 运行时可见的注解
 */
public class RuntimeVisibleAnnotations_attribute extends RuntimeAnnotations_attribute {
    public RuntimeVisibleAnnotations_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(cr, attribute_name_index, attribute_length);
    }
}
