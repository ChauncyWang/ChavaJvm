package cc.chavaw.jvm.dataStructs.constantPool;

import java.io.IOException;
import java.io.InputStream;

import static cc.chavaw.jvm.tools.ByteTool.getShort;

/**
 * 表示 invokeDynamic 指令所用到的引导方法、引导方法所用到的动态调用名称、参数和返回类型，
 * 并可以给引导传入 一系列成为静态常树的常量
 * Created by 13969 on 2017/6/30.
 */
public class ConstantInvokeDynamicInfo {
    /** 当前 class 文件中引导方法表的 bootstrapMethods 数组的有效索引 */
    private short bootstrapMethodAttrIndex;
    /** 对常量池的索引 该索引位置必须为 ConstantNameAndTypeInfo 结构 */
    private short nameAndTypeIndex;
    public ConstantInvokeDynamicInfo(InputStream in) throws IOException {
        bootstrapMethodAttrIndex = getShort(in);
        nameAndTypeIndex = getShort(in);
    }
}
