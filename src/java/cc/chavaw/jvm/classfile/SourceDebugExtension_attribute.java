package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 调试用???<br>
 */
public class SourceDebugExtension_attribute extends Attribute {
    public SourceDebugExtension_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        debug_extension = new int[attribute_length];
        for (int i = 0; i < attribute_length; ++i) {
            debug_extension[i] = cr.readUnsignedByte();
        }
    }

    public final int[] debug_extension;
}