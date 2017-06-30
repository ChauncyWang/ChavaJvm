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
    private static Map<Integer, Class> map = new HashMap<>();

    /** constant pool tag */
    static {
        map.put(CONSTANT_Utf8, ConstantUtf8Info.class);
        map.put(CONSTANT_Integer, ConstantIntegerInfo.class);
        map.put(CONSTANT_Float, ConstantFloatInfo.class);
        map.put(CONSTANT_Long, ConstantLongInfo.class);
        map.put(CONSTANT_Double, ConstantDoubleInfo.class);
        map.put(CONSTANT_Class, ConstantClassInfo.class);
        map.put(CONSTANT_String, ConstantStringInfo.class);
        map.put(CONSTANT_Fieldref, ConstantFieldrefInfo.class);
        map.put(COSNTANT_Methodref, ConstantMethodrefInfo.class);
        map.put(COSNTANT_InterfaceMethodref, ConstantInterfaceMethodrefInfo.class);
        map.put(CONSTANT_NameAndType, ConstantNameAndTypeInfo.class);
        map.put(CONSTANT_MethodHandle,ConstantMethodHandleInfo.class);
        map.put(CONSTANT_MethodType,ConstantMethodTypeInfo.class);
        map.put(CONSTANT_InvokeDynamic,ConstantInvokeDynamicInfo.class);
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
                clazz = map.get(tag);
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
     * 解析类的访问标识
     *
     * @param in 要解析的流
     * @return 访问标志的Vector
     */
    public static Vector<AccessFlag> pasrseClassAccessFlag(InputStream in) throws IOException {
        Vector<AccessFlag> accessFlags = new Vector<>();
        AccessFlag[] classFlags = new AccessFlag[]{
                ACC_PUBLIC, ACC_FINAL, ACC_SUPER, ACC_INTERFACE,
                ACC_ABSTRACT, ACC_SYNTHETIC, ACC_ANNOTATION, ACC_ENUM};
        int t = getShort(in);

        for (AccessFlag flag : classFlags) {
            if ((t & flag.value) != 0) {
                accessFlags.add(flag);
            }
        }

        return accessFlags;
    }

    public static Vector<Short> parseIntefaces(InputStream in, short interfacesCount) throws IOException {
        Vector<Short> intefaces = new Vector<>();

        for (int i = 0; i < interfacesCount; ++i) {
            intefaces.add(getShort(in));
        }

        return intefaces;
    }
}
