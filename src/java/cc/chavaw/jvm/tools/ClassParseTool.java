package cc.chavaw.jvm.tools;

import cc.chavaw.jvm.dataStructs.AccessFlag;
import cc.chavaw.jvm.dataStructs.constantPool.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 与解析 class 文件相关的小工具
 * Created by 13969 on 2017/6/22.
 */
public class ClassParseTool {

    /**
     * 从流中解析常量池
     * @param in 要解析的流
     * @param constant_pool_count 常量池大小
     * @return 解析出来的 常量池
     * @throws IOException 读取出现io异常
     * @throws NoSuchMethodException 没有常量池中的常量没有相应的个构造函数
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static Vector<ConstantPoolInfo> parseConstantPools(InputStream in, int constant_pool_count) throws
            IOException {
        Vector<ConstantPoolInfo> constant_pools = new Vector<>();
        for (int i = 0; i < constant_pool_count - 1; ++i) {
            int tag = in.read();
            //如果是long和double加1（占位两个位置（8字节））
            if (tag == 5 || tag == 6) {
                i++;
            }
            Class clazz;
            ConstantPoolInfo poolInfo;
            // 暂时支持的常量池tag
            if (tag >= 1 && tag <= 12 && tag != 2) {
                clazz = map.get(tag);
                Constructor c = null;
                try {
                    c = clazz.getDeclaredConstructor(new Class[]{InputStream.class});
                    poolInfo = (ConstantPoolInfo) c.newInstance(in);
                    constant_pools.add(poolInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            } else {
                throw new RuntimeException("不支持的常量池Tag:" + tag);
            }
        }

        return constant_pools;
    }

    /**
     * 解析类的访问标识
     * @param in 要解析的流
     * @return 访问标志的Vector
     */
    public static Vector<AccessFlag> pasrseClassAccessFlag(InputStream in) {
        Vector<AccessFlag> accessFlags = new Vector<>();


        return accessFlags;
    }

    /**
     * ConstantPoolInfo tag常量值和对应结构类名的映射
     */
    private static Map<Integer, Class> map = new HashMap<>();

    /** constant pool tag */
    static {
        map.put(1, ConstantUtf8Info.class);
        map.put(3, ConstantIntegerInfo.class);
        map.put(4, ConstantFloatInfo.class);
        map.put(5, ConstantLongInfo.class);
        map.put(6, ConstantDoubleInfo.class);
        map.put(7, ConstantClassInfo.class);
        map.put(8, ConstantStringInfo.class);
        map.put(9, ConstantFieldrefInfo.class);
        map.put(10, ConstantMethodrefInfo.class);
        map.put(11, ConstantInterfaceMethodrefInfo.class);
        map.put(12, ConstantNameAndTypeInfo.class);
    }
}
