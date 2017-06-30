package cc.chavaw.jvm.dataStructs;

import cc.chavaw.jvm.dataStructs.constantPool.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Vector;

import static cc.chavaw.jvm.dataStructs.AccessFlag.pasrseClassAccessFlag;
import static cc.chavaw.jvm.tools.ByteTool.*;
import static cc.chavaw.jvm.tools.ClassParseTool.parseConstantPools;
import static cc.chavaw.jvm.tools.ClassParseTool.parseIntefaces;

/**
 * Class文件格式
 * Created by 13969 on 2017/6/13.
 */
public class ClassFile {
    /** 魔数 */
    private byte[] magic = new byte[4];
    /** 次要版本号 */
    private short minorVersion;
    /** 主要版本号 */
    private short majorVersion;
    /** 常量池计数器 */
    private short constantPoolCount;
    /** 常量池 **/
    private Map<Integer,ConstantPoolInfo> constantPoolInfos;
    /** 类访问标志 */
    private Vector<AccessFlag> accessFlags;
    /** this class */
    private short thisClass;
    /** super class */
    private short superClass;
    /** interface count */
    private short interfacesCount;
    /** interface 在常量池的索引 集合 */
    private Vector<Short> interfaces;
    /**
     * 构造函数
     * @param in 构造所需要的流
     * @throws IOException
     */
    public ClassFile(InputStream in) throws IOException {
        //读取魔数
        in.read(magic,0,4);
        //读取次版本号
        minorVersion = getShort(in);
        majorVersion = getShort(in);
        constantPoolCount = getShort(in);

        constantPoolInfos = parseConstantPools(in, constantPoolCount);
        accessFlags = pasrseClassAccessFlag(getShort(in));

        thisClass = getShort(in);
        superClass = getShort(in);

        interfacesCount = getShort(in);
        interfaces = parseIntefaces(in,interfacesCount);
        int a = 1+1;
    }

}
