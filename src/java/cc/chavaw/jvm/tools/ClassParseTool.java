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

import static cc.chavaw.jvm.dataStructs.AccessFlag.*;
import static cc.chavaw.jvm.tools.ByteTool.getShort;
import static cc.chavaw.jvm.dataStructs.constantPool.Tag.*;

/**
 * 与解析 class 文件相关的小工具
 * Created by 13969 on 2017/6/22.
 */
public class ClassParseTool {

    /**
     * ConstantPoolInfo tag常量值和对应结构类名的映射
     */
    private static Map<Integer, Class> poolMap = new HashMap<>();

    /** constant pool tag */
    static {
        poolMap.put(CONSTANT_Utf8, ConstantUtf8Info.class);
        poolMap.put(CONSTANT_Integer, ConstantIntegerInfo.class);
        poolMap.put(CONSTANT_Float, ConstantFloatInfo.class);
        poolMap.put(CONSTANT_Long, ConstantLongInfo.class);
        poolMap.put(CONSTANT_Double, ConstantDoubleInfo.class);
        poolMap.put(CONSTANT_Class, ConstantClassInfo.class);
        poolMap.put(CONSTANT_String, ConstantStringInfo.class);
        poolMap.put(CONSTANT_Fieldref, ConstantFieldrefInfo.class);
        poolMap.put(COSNTANT_Methodref, ConstantMethodrefInfo.class);
        poolMap.put(COSNTANT_InterfaceMethodref, ConstantInterfaceMethodrefInfo.class);
        poolMap.put(CONSTANT_NameAndType, ConstantNameAndTypeInfo.class);
        poolMap.put(CONSTANT_MethodHandle,ConstantMethodHandleInfo.class);
        poolMap.put(CONSTANT_MethodType,ConstantMethodTypeInfo.class);
        poolMap.put(CONSTANT_InvokeDynamic,ConstantInvokeDynamicInfo.class);
    }

    /**
     * 从流中解析常量池
     *
     * @param in                  要解析的流
     * @param constant_pool_count 常量池大小
     * @return 解析出来的 常量池
     * @throws IOException               读取出现io异常
     * @throws NoSuchMethodException     没有常量池中的常量没有相应的个构造函数
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static Map<Integer, ConstantPoolInfo> parseConstantPools(InputStream in, int constant_pool_count) throws
            IOException {
        Map<Integer, ConstantPoolInfo> constant_pools = new HashMap<>();
        for (int i = 0; i < constant_pool_count - 1; ++i) {
            int tag = in.read();
            Class clazz;
            ConstantPoolInfo poolInfo;
            // 暂时支持的常量池tag
            if (tag >= 1 && tag <= 16 && tag != 2 && tag != 13 && tag != 14 && tag != 17) {
                clazz = poolMap.get(tag);
                Constructor c = null;
                try {
                    c = clazz.getDeclaredConstructor(new Class[]{InputStream.class});
                    poolInfo = (ConstantPoolInfo) c.newInstance(in);
                    constant_pools.put(i + 1, poolInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            } else {
                throw new RuntimeException("不支持的常量池Tag:" + tag);
            }
            //如果是long和double加1（占位两个位置（8字节））
            if (tag == 5 || tag == 6) {
                i++;
            }
        }

        return constant_pools;
    }


    /**
     * 解析 类的接口
     * @param in 输入流
     * @param interfacesCount 接口数量
     * @return 接口在常量池的索引集合
     * @throws IOException 发生io异常
     */
    public static Vector<Short> parseIntefaces(InputStream in, short interfacesCount) throws IOException {
        Vector<Short> intefaces = new Vector<>();

        for (int i = 0; i < interfacesCount; ++i) {
            intefaces.add(getShort(in));
        }

        return intefaces;
    }
}
