package cc.chavaw.jvm.classfile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chava on 17-7-9.
 */
public class ClassReader {
    ClassReader(ClassFile classFile, InputStream in) throws IOException {
        this.classFile = classFile;
        this.in = new DataInputStream(new BufferedInputStream(in));
    }

    public int readUnsignedByte() throws IOException {
        return in.readUnsignedByte();
    }

    public int readUnsignedShort() throws IOException {
        return in.readUnsignedShort();
    }

    public int readInt() throws IOException {
        return in.readInt();
    }

    public long readLong() throws IOException {
        return in.readLong();
    }

    public float readFloat() throws IOException {
        return in.readFloat();
    }

    public double readDouble() throws IOException {
        return in.readDouble();
    }

    public String readUTF() throws IOException {
        return in.readUTF();
    }

    private DataInputStream in;
    private ClassFile classFile;

}
