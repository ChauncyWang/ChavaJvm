package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 运行时不可见注解
 */
public class RuntimeInvisibleAnnotations_attribute extends RuntimeAnnotations_attribute{
    public RuntimeInvisibleAnnotations_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(cr, attribute_name_index, attribute_length);
    }
}
