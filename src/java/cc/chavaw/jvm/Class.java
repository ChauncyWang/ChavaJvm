package cc.chavaw.jvm;

/**
 * Class文件格式
 * Created by 13969 on 2017/6/13.
 */
public class Class {
    public byte[] magic = new byte[4];
    public byte[] minor_version = new byte[2];
    public byte[] major_version = new byte[2];
    public byte[] constant_pool_count = new byte[2];
}
