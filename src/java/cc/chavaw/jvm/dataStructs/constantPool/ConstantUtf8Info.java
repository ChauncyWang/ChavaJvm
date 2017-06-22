package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * utf8编码的字符串
 * Created by 13969 on 2017/6/13.
 */
public class ConstantUtf8Info implements ConstantPoolInfo {
    /** UTF-8编码的字符串长度 */
    public short length;
    /** 长度为 length 的 UTF8 编码的字符串 */
    public String value;
    /**
     * 构造函数
     * @param in 输入流
     * @throws IOException io异常
     */
    public ConstantUtf8Info(InputStream in) throws IOException {
        length = getShort(in);
        byte[] bytes = new byte[length];
        in.read(bytes,0,length);
        value = new String(bytes,"UTF8");
    }
}
