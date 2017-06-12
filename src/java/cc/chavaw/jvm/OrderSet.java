package cc.chavaw.jvm;

/**
 * jvm 指令集
 * Created by 13969 on 2017/6/12.
 */
public enum OrderSet {
    /** 误操作 */
    nop(0x00),
    /** null 进栈 */
    aconst_null(0x01),
    /** int型常量值-1进栈 */
    iconst_m1(0x02),
    /** int型常量值0进栈 */
    iconst_0(0x03),
    /** int型常量值1进栈 */
    iconst_1(0x04),
    /** int型常量值2进栈 */
    iconst_2(0x05),
    /** int型常量值3进栈 */
    iconst_3(0x06),
    /** int型常量值4进栈 */
    iconst_4(0x07),
    /** int型常量值5进栈 */
    iconst_5(0x08),
    /** long型常量值0进栈 */
    lconst_0(0x09),
    /** long型常量值1进栈 */
    lconst_1(0x0A),
    /** float型常量值0进栈 */
    fconst_0(0x0B),
    /** float型常量值1进栈 */
    fconst_1(0x0C),
    /** float型常量值2进栈 */
    fconst_2(0x0D),
    /** double型常量值0进栈 */
    dconst_0(0x0E),
    /** double型常量值1进栈 */
    dconst_1(0x0F),
    /** 将一个byte型常量值推送至栈顶 */
    bipush(0x10),
    /** 将一个short型常量值推送至栈顶 */
    sipush(0x11),
    /** 将int、float或String型常量值从常量池中推送到栈顶 */
    ldc(0x12),
    /** 将int、float或String型常量值从常量池中推送到栈顶(宽索引) */
    ldc_w(0x13),
    /** 将long或是double型常量值从常量池中推送至栈顶（宽索引） */
    ldc2_w(0x14),
    /** 将指定的int型局部变量进栈 */
    iload(0x15),
    /** 将指定的long型局部变量进栈 */
    lload(0x16),
    /** 将指定的float型局部变量进栈 */
    fload(0x17),
    /** 将指定的double型局部变量进栈 */
    dload(0x18),
    /**
     * 指令格式: aload index
     * 功能:当前frame的局部变量数组中下标为index的引用型局部变量进栈
     * index:无符号一byte型。和wide指令联用，可以使index为两byte
     */
    aload(0x19),
    /** 第一个int型局部变量进栈 */
    iload_0(0x1A),
    /** 第二个int型局部变量进栈 */
    iload_1(0x1B),
    /** 第三个int型局部变量进栈 */
    iload_2(0x1C),
    /** 第四个int型局部变量进栈 */
    iload_3(0x1D),
    /** 第一个long型局部变量进栈 */
    lload_0(0x1E),
    /** 第二个long型局部变量进栈 */
    lload_1(0x1F),
    /** 第三个long型局部变量进栈 */
    lload_2(0x20),
    /** 第四个long型局部变量进栈 */
    lload_3(0x21),
    /** 第一个float型局部变量进栈 */
    fload_0(0x22),
    /** 第二个float型局部变量进栈 */
    fload_1(0x23),
    /** 第三个float型局部变量进栈 */
    fload_2(0x24),
    /** 第四个float型局部变量进栈 */
    fload_3(0x25),
    /** 第一个double型局部变量进栈 */
    dload_0(0x26),
    /** 第二个double型局部变量进栈 */
    dload_1(0x27),
    /** 第三个double型局部变量进栈 */
    dload_2(0x28),
    /** 第四个double型局部变量进栈 */
    dload_3(0x29),
    /**
     * 指令格式:aload_0
     * 该指令的行为类似于aload指令index为0的情况
     */
    aload_0(0x2A),
    /** 同上 */
    aload_1(0x2B),
    /** 同上 */
    aload_2(0x2C),
    /** 同上 */
    aload_3(0x2D),
    /** 指定的 int 型数组的指定下标处的值进栈 */
    iaload(0x2E),
    /** 指定的 long 型数组的指定下标处的值进栈 */
    laload(0x2F),
    /** 指定的 float 型数组的指定下标处的值进栈 */
    faload(0x30),
    /** 指定的 double 型数组的指定下标处的值进栈 */
    daload(0x31),
    /**
     * 指令格式:aaload index arrayref value
     * 功能描述: 栈顶的数组下标（index）、数组引用(arrayref)出栈，
     * 并根据这两个数值取对应的数组元素值（value）进栈
     */
    aaload(0x32),
    /** 指定的 boolean 或 byte 型数组的指定下标处的值进栈 */
    baload(0x33),
    /** 指定的 char 型数组的指定下标处的值进栈 */
    caload(0x34),
    /** 指定的 short 型数组的指定下标处的值进栈 */
    saload(0x35),
    /** 将栈顶 int 型数值存入指定的局部变量 */
    istore(0x36),
    /** 将栈顶 long 型数值存入指定的局部变量 */
    lstore(0x37),
    /** 将栈顶 float 型数值存入指定的局部变量 */
    fstore(0x38),
    /** 将栈顶 double 型数值存入指定的局部变量 */
    dstore(0x39),
    /**
     * 指令格式:astore index
     * 功能描述:将栈顶数值(objectref)存入当前frame的局部变量数组指定的下标（index）处的变量中，栈顶元素出栈
     */
    astore(0x3A),
    /** 将栈顶 int 型数值存入第一个局部变量 */
    istore_0(0x3B),
    ;

    /** 指令码 */
    int code;
    /** 构造函数 */
    OrderSet(int code) {
        this.code = code;
    }
}
