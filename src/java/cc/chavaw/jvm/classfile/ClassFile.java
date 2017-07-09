package cc.chavaw.jvm.classfile;

import java.io.InputStream;

/**
 * Created by chava on 17-7-9.
 */
public class ClassFile {
    public ClassFile(InputStream in) throws Exception {
        cr = new ClassReader(this, in);
        magic = cr.readInt();
        minor_version = cr.readUnsignedShort();
        major_version = cr.readUnsignedShort();
        constant_pool = new ConstantPool(cr);
        access_flags = new AccessFlags(cr.readInt());
        this_class = cr.readUnsignedShort();
        super_class = cr.readUnsignedShort();
        int interfaces_count = cr.readUnsignedShort();
        interfaces = new int[interfaces_count];
        for (int i = 0; i < interfaces_count; ++i) {
            interfaces[i] = cr.readUnsignedShort();
        }
        fields = new Field[2];
        methods = new Method[2];
        attributes = new Attributes();
    }

    public ClassFile(int magic, int minor_version, int major_version, ConstantPool constant_pool,
                     AccessFlags access_flags, int this_class, int super_class, int[] interfaces,
                     Field[] fields, Method[] methods, Attributes attributes) {
        this.magic = magic;
        this.minor_version = minor_version;
        this.major_version = major_version;
        this.constant_pool = constant_pool;
        this.access_flags = access_flags;
        this.this_class = this_class;
        this.super_class = super_class;
        this.interfaces = interfaces;
        this.fields = fields;
        this.methods = methods;
        this.attributes = attributes;
    }


    public final int magic;
    public final int minor_version;
    public final int major_version;
    public final ConstantPool constant_pool;
    public final AccessFlags access_flags;
    public final int this_class;
    public final int super_class;
    public final int[] interfaces;
    public final Field[] fields;
    public final Method[] methods;
    public final Attributes attributes;

    private ClassReader cr;
}
