package cc.chavaw.jvm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Class文件格式
 * Created by 13969 on 2017/6/13.
 */
public class Class {
    /** 魔数 */
    public byte[] magic = new byte[4];
    /** 最低版本号 */
    public int minor_version;
    /** 需要的java版本号 */
    public int major_version;
    /** 常量池大小 */
    public int constant_pool_count;

    public Class(InputStream in) throws IOException {
        in.read(magic,0,4);
        minor_version = in.read() << 8 | in.read();
        major_version = in.read() << 8 | in.read();
        constant_pool_count = in.read() << 8 | in.read();
    }

    @Override
    public String toString() {
        return "Class{" +
                "magic=" + Arrays.toString(magic) +
                ", minor_version=" + minor_version +
                ", major_version=" + major_version +
                ", constant_pool_count=" + constant_pool_count +
                '}';
    }
}
