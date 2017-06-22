package cc.chavaw.jvm.dataStructs;

import cc.chavaw.jvm.dataStructs.constantPool.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import static cc.chavaw.jvm.tools.ByteTool.*;
import static cc.chavaw.jvm.tools.ClassParseTool.parseConstantPools;

/**
 * Class文件格式
 * Created by 13969 on 2017/6/13.
 */
public class JvmClass {
    /** 魔数 */
    private byte[] magic = new byte[4];
    /** 次要版本号 */
    private short minorVersion;
    /** 主要版本号 */
    private short majorVersion;
    /** 常量池大小 */
    private short constantPoolCount;
    /** 常量池 **/
    private Vector<ConstantPoolInfo> constantPoolInfos;
    /** 类访问标志 */
    public Vector<AccessFlag> accessFlags;

    /**
     * 构造函数
     * @param in 构造所需要的流
     * @throws IOException
     */
    public JvmClass(InputStream in) throws IOException {
        //读取魔数
        in.read(magic,0,4);
        //读取次版本号
        minorVersion = getShort(in);
        majorVersion = getShort(in);
        constantPoolCount = getShort(in);

        constantPoolInfos = parseConstantPools(in, constantPoolCount);

        int a = 1+1;
    }

}
