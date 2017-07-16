package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 *
 * Created by chava on 17-7-17.
 */
public class Descriptor {
    public Descriptor(ClassReader cr) throws IOException {
        this(cr.readUnsignedShort());
    }
    public Descriptor(int index) {
        this.index = index;
    }
    public final int index;
}
