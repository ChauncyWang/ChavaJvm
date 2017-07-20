package cc.chavaw.jvm.classfile;

import java.io.IOException;

public class BootstrapMethods_attribute extends Attribute {
    public final int num_bootstrap_methods;
    public final bootstrap_method[] bootstrap_methods;

    public BootstrapMethods_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        num_bootstrap_methods = cr.readUnsignedShort();
        bootstrap_methods = new bootstrap_method[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; ++i) {
            bootstrap_methods[i] = new bootstrap_method(cr);
        }
    }

    public static class bootstrap_method {
        public final int bootstrap_method_ref;
        public final int num_bootstrap_arguments;
        public final int[] bootstrap_arguments;

        public bootstrap_method(ClassReader cr) throws IOException {
            bootstrap_method_ref = cr.readUnsignedShort();
            num_bootstrap_arguments = cr.readUnsignedShort();
            bootstrap_arguments = new int[num_bootstrap_arguments];
            for (int i = 0; i < num_bootstrap_arguments; ++i) {
                bootstrap_arguments[i] = cr.readUnsignedShort();
            }
        }
    }
}
