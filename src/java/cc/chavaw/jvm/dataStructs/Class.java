package cc.chavaw.jvm.dataStructs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static cc.chavaw.jvm.tools.ByteTools.*;

/**
 * Class文件格式
 * Created by 13969 on 2017/6/13.
 */
public class Class {
    /** 魔数 */
    public byte[] magic = new byte[4];
    /** 次要版本号 */
    public int minor_version;
    /** 主要版本号 */
    public int major_version;
    /** 常量池大小 */
    public int constant_pool_count;
    /** 常量池 **/
    public ConstantPoolInfo[] constant_pools;

    public Class(InputStream in) throws IOException {
        //读取魔数
        in.read(magic,0,4);
        //读取次版本号
        minor_version = getInteger(in,2);
        major_version = getInteger(in,2);
        constant_pool_count = getInteger(in,2);
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
