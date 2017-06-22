package cc.chavaw.jvm.tools;

import java.io.IOException;
import java.io.InputStream;

/**
 * byte相关的工具
 * Created by 13969 on 2017/6/13.
 */
public class ByteTool {

    /**
     * 读取一个 short 数值
     * @param in
     * @return
     * @throws IOException
     */
    public static short getShort(InputStream in) throws IOException {
        short result = 0;

        for(int i = 0;i < 2; ++i) {
            result <<= 8;
            result |= in.read();
        }

        return result;
    }
    /**
     * 从流中读取一个 int 数值
     * @param in 读取的流
     * @return int 数值
     * @throws IOException io异常
     */
    public static int getInteger(InputStream in) throws IOException {
        int result = 0;

        for(int i = 0;i < 4;++i) {
            result <<= 8;
            result |= in.read();
        }

        return result;
    }

    /**
     * 从流中读取一个 long 数值
     * @param in 要读取的流
     * @return 读取到的 long 数值
     * @throws IOException io异常
     */
    public static long getLong(InputStream in) throws IOException {
        long result = 0;

        for(int i = 0;i < 8;++i) {
            result <<= 8;
            result |= in.read();
        }

        return result;
    }

}
