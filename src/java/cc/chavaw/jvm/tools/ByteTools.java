package cc.chavaw.jvm.tools;

import java.io.IOException;
import java.io.InputStream;

/**
 * byte相关的工具
 * Created by 13969 on 2017/6/13.
 */
public class ByteTools {
    /**
     * 从io流中读取长度为length的int数值
     * @param in io流
     * @param length int长度
     * @return 读取的int数值
     * @throws IOException io异常
     */
    public static int getInteger(InputStream in,int length) throws IOException {
        int result = 0;
        for(int i = 0;i < length - 1;++i) {
            result <<= 8;
            result |= in.read();
        }
        result |= in.read();

        return result;
    }

    /**
     * 从io流中读取长度为length的 long 数值
     * @param in
     * @param length
     * @return
     * @throws IOException
     */
    public static long getLong(InputStream in,int length) throws IOException {
        long result = 0;
        for(int i = 0;i < length - 1;++i) {
            result <<= 8;
            result |= in.read();
        }
        result |= in.read();

        return result;
    }
}
