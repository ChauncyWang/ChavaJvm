package cc.chavaw.jvm.classfile;

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
    /** 将栈顶 int 型数值存入第二个局部变量 */
    istore_1(0x3C),
    /** 将栈顶 int 型数值存入第三个局部变量 */
    istore_2(0x3D),
    /** 将栈顶 int 型数值存入第四个局部变量 */
    istore_3(0x3E),
    /** 将栈顶 long 型数值存入第一个局部变量 */
    lstore_0(0x3F),
    /** 将栈顶 long 型数值存入第二个局部变量 */
    lstore_1(0x40),
    /** 将栈顶 long 型数值存入第三个局部变量 */
    lstore_2(0x41),
    /** 将栈顶 long 型数值存入第四个局部变量 */
    lstore_3(0x42),
    /** 将栈顶 float 型数值存入第一个局部变量 */
    fstore_0(0x43),
    /** 将栈顶 float 型数值存入第二个局部变量 */
    fstore_1(0x44),
    /** 将栈顶 float 型数值存入第三个局部变量 */
    fstore_2(0x45),
    /** 将栈顶 float 型数值存入第四个局部变量 */
    fstore_3(0x46),
    /** 将栈顶 double 型数值存入第一个局部变量 */
    dstore_0(0x47),
    /** 将栈顶 double 型数值存入第二个局部变量 */
    dstore_1(0x48),
    /** 将栈顶 double 型数值存入第三个局部变量 */
    dstore_2(0x49),
    /** 将栈顶 double 型数值存入第四个局部变量 */
    dstore_3(0x4A),
    /**
     * 指令格式:astore_0
     * 功能描述:该指令的行为类似于astore指令index为0的情况
     */
    astroe_0(0x4B),
    /** 同上 */
    astore_1(0x4C),
    /** 同上 */
    astore_2(0x4D),
    /** 同上 */
    astore_3(0x4E),
    /** 将栈顶 int 型数组存入指定数组的下标处 */
    iastore(0x4F),
    /** 将栈顶 long 型数组存入指定数组的下标处 */
    lastore(0x50),
    /** 将栈顶 float 型数组存入指定数组的下标处 */
    fastore(0x51),
    /** 将栈顶 double 型数组存入指定数组的下标处 */
    dastore(0x52),
    /**
     * 指令格式:aastore
     * 功能描述:根据栈顶的引用型数值（value）、数组下标（index）、数组引用（arrayref）出栈，将数值存入对应的数组元素
     */
    aastore(0x53),
    /** 将栈顶 boolean 或 byte 型数组存入指定数组的指定下标处 */
    bastore(0x54),
    /** 将栈顶 char 型数值存入指定数组的指定下标处 */
    castore(0x55),
    /** 将栈顶 short 型数值存入指定数组的指定下标处 */
    sastore(0x56),
    /** 栈顶数值出栈（该栈顶值不能是 long 或是 double 型）*/
    pop(0x57),
    /** 栈顶的一个（如果是 long 、double ）或两个（其他类型）数值出栈 */
    pop2(0x58),
    /** 复制栈顶数值，并且复制值进栈 */
    dup(0x59),
    /** 复制栈顶数值，并且复制值进栈2次 */
    dup_x1(0x5A),
    /** 复制栈顶数值，并且复制值进栈2次或3次 */
    dup_x2(0x5B),
    /** 复制栈顶一个（long,double型的）或两个其他类型的数值，并且复制值进栈 */
    dup2(0x5C),
    dup2_x1(0x5D),
    dup2_x2(0x5E),
    /** 栈顶两个数值互换（要求栈顶的两个数值 不能是long或double型的） */
    swap(0x5F),

    /** 栈顶两个 int 型数值相加,并将结果进栈 */
    iadd(0x60),
    /** 栈顶两个 long 型数值相加,并将结果进栈 */
    ladd(0x61),
    /** 栈顶两个 float 型数值相加,并将结果进栈 */
    fadd(0x62),
    /** 栈顶两个 double 型数值相加,并将结果进栈 */
    dadd(0x63),

    /** 栈顶两个 int 型数值相减,并将结果进栈 */
    isub(0x64),
    /** 栈顶两个 long 型数值相减,并将结果进栈 */
    lsub(0x65),
    /** 栈顶两个 float 型数值相减,并将结果进栈 */
    fsub(0x66),
    /** 栈顶两个 double 型数值相减,并将结果进栈 */
    dsub(0x67),

    /** 栈顶两个 int 型数值相乘,并将结果进栈 */
    imul(0x68),
    /** 栈顶两个 long 型数值相乘,并将结果进栈 */
    lmul(0x69),
    /** 栈顶两个 float 型数值相乘,并将结果进栈 */
    fmul(0x6A),
    /** 栈顶两个 double 型数值相乘,并将结果进栈 */
    dmul(0x6B),

    /** 栈顶两个 int 型数值相除,并将结果进栈 */
    idiv(0x6C),
    /** 栈顶两个 long 型数值相除,并将结果进栈 */
    ldiv(0x6D),
    /** 栈顶两个 float 型数值相除,并将结果进栈 */
    fdiv(0x6E),
    /** 栈顶两个 double 型数值相除,并将结果进栈 */
    ddiv(0x6F),

    /** 栈顶两个 int 型数值取模运算,并将结果进栈 */
    irem(0x70),
    /** 栈顶两个 long 型数值取模运算,并将结果进栈 */
    lrem(0x71),
    /** 栈顶两个 float 型数值取模运算,并将结果进栈 */
    frem(0x72),
    /** 栈顶两个 double 型数值取模运算,并将结果进栈 */
    drem(0x73),

    /** 栈顶两个 int 型数值取负,并将结果进栈 */
    ineg(0x74),
    /** 栈顶两个 long 型数值取负,并将结果进栈 */
    lneg(0x75),
    /** 栈顶两个 float 型数值取负,并将结果进栈 */
    fneg(0x76),
    /** 栈顶两个 double 型数值取负,并将结果进栈 */
    dneg(0x77),

    /** int 型数值 左移 指定位数，并且结果入栈 */
    ishl(0x78),
    /** long 型数值 左移 指定位数，并且结果入栈 */
    lshl(0x79),

    /** int 型数值 带符号右移 指定位数，并且结果入栈 */
    ishr(0x7A),
    /** long 型数值 带符号右移 指定位数，并且结果入栈 */
    lshr(0x7B),
    /** int 型数值 无符号右移 指定位数，并且结果入栈 */
    iushr(0x7C),
    /** long 型数值 无符号右移 指定位数，并且结果入栈 */
    lushr(0x7D),

    /** 将栈顶两 int 型数值按位与，并且结果进栈 */
    iand(0x7E),
    /** 将栈顶两 long 型数值按位与，并且结果进栈 */
    land(0x7F),

    /** 将栈顶两 int 型数值按位或，并且结果进栈 */
    ior(0x80),
    /** 将栈顶两 long 型数值按位或，并且结果进栈 */
    lor(0x81),

    /** 将栈顶两 int 型数值按位异或，并且结果进栈 */
    ixor(0x82),
    /** 将栈顶两 long 型数值按位异或，并且结果进栈 */
    lxor(0x83),


    /** 将指定int型变量增加指定值（i++, */
    iinc(0x84),
    /** 将栈顶int型数值强制转换成long型数值并将结果压入栈顶 */
    i2l(0x85),
    /** 将栈顶int型数值强制转换成float型数值并将结果压入栈顶 */
    i2f(0x86),
    /** 将栈顶int型数值强制转换成double型数值并将结果压入栈顶 */
    i2d(0x87),
    /** 将栈顶long型数值强制转换成int型数值并将结果压入栈顶 */
    l2i(0x88),
    /** 将栈顶long型数值强制转换成float型数值并将结果压入栈顶 */
    l2f(0x89),
    /** 将栈顶long型数值强制转换成double型数值并将结果压入栈顶 */
    l2d(0x8a),
    /** 将栈顶float型数值强制转换成int型数值并将结果压入栈顶 */
    f2i(0x8b),
    /** 将栈顶float型数值强制转换成long型数值并将结果压入栈顶 */
    f2l(0x8c),
    /** 将栈顶float型数值强制转换成double型数值并将结果压入栈顶 */
    f2d(0x8d),
    /** 将栈顶double型数值强制转换成int型数值并将结果压入栈顶 */
    d2i(0x8e),
    /** 将栈顶double型数值强制转换成long型数值并将结果压入栈顶 */
    d2l(0x8f),
    /** 将栈顶double型数值强制转换成float型数值并将结果压入栈顶 */
    d2f(0x90),
    /** 将栈顶int型数值强制转换成byte型数值并将结果压入栈顶 */
    i2b(0x91),
    /** 将栈顶int型数值强制转换成char型数值并将结果压入栈顶 */
    i2c(0x92),
    /** 将栈顶int型数值强制转换成short型数值并将结果压入栈顶 */
    i2s(0x93),
    /** 比较栈顶两long型数值大小，并将结果（1，0，-1）压入栈顶 */
    lcmp(0x94),
    /** 比较栈顶两float型数值大小，并将结果（1，0，-1）压入栈顶；当其中一个数值为NaN时，将-1压入栈顶 */
    fcmpl(0x95),
    /** 比较栈顶两float型数值大小，并将结果（1，0，-1）压入栈顶；当其中一个数值为NaN时，将1压入栈顶 */
    fcmpg(0x96),
    /** 比较栈顶两double型数值大小，并将结果（1，0，-1）压入栈顶；当其中一个数值为NaN时，将-1压入栈顶 */
    dcmpl(0x97),
    /** 比较栈顶两double型数值大小，并将结果（1，0，-1）压入栈顶；当其中一个数值为NaN时，将1压入栈顶 */
    dcmpg(0x98),
    /** 当栈顶int型数值等于0时跳转 */
    ifeq(0x99),
    /** 当栈顶int型数值不等于0时跳转 */
    ifne(0x9a),
    /** 当栈顶int型数值小于0时跳转 */
    iflt(0x9b),
    /** 当栈顶int型数值大于等于0时跳转 */
    ifge(0x9c),
    /** 当栈顶int型数值大于0时跳转 */
    ifgt(0x9d),
    /** 当栈顶int型数值小于等于0时跳转 */
    ifle(0x9e),
    /** 比较栈顶两int型数值大小，当结果等于0时跳转 */
    if_icmpeq(0x9f),
    /** 比较栈顶两int型数值大小，当结果不等于0时跳转 */
    if_icmpne(0xa0),
    /** 比较栈顶两int型数值大小，当结果小于0时跳转 */
    if_icmplt(0xa1),
    /** 比较栈顶两int型数值大小，当结果大于等于0时跳转 */
    if_icmpge(0xa2),
    /** 比较栈顶两int型数值大小，当结果大于0时跳转 */
    if_icmpgt(0xa3),
    /** 比较栈顶两int型数值大小，当结果小于等于0时跳转 */
    if_icmple(0xa4),
    /** 比较栈顶两引用型数值，当结果相等时跳转 */
    if_acmpeq(0xa5),
    /** 比较栈顶两引用型数值，当结果不相等时跳转 */
    if_acmpne(0xa6),
    /** 无条件跳转 */
    GOTO(0xa7),
    /** 跳转至指定16位offset位置，并将jsr下一条指令地址压入栈顶 */
    jsr(0xa8),
    /** 返回至本地变量指定的index的指令位置（一般与jsr, */
    ret(0xa9),
    /** 用于switch条件跳转，case值连续（可变长度指令） */
    tableswitch(0xaa),
    /** 用于switch条件跳转，case值不连续（可变长度指令） */
    lookupswitch(0xab),
    /** 从当前方法返回int */
    ireturn(0xac),
    /** 从当前方法返回long */
    lreturn(0xad),
    /** 从当前方法返回float */
    freturn(0xae),
    /** 从当前方法返回double */
    dreturn(0xaf),
    /** 从当前方法返回对象引用 */
    areturn(0xb0),
    /** 从当前方法返回void */
    RETURN(0xb1),
    /** 获取指定类的静态域，并将其值压入栈顶 */
    getstatic(0xb2),
    /** 为指定的类的静态域赋值 */
    putstatic(0xb3),
    /** 获取指定类的实例域，并将其值压入栈顶 */
    getfield(0xb4),
    /** 为指定的类的实例域赋值 */
    putfield(0xb5),
    /** 调用实例方法 */
    invokevirtual(0xb6),
    /** 调用超类构造方法，实例初始化方法，私有方法 */
    invokespecial(0xb7),
    /** 调用静态方法 */
    invokestatic(0xb8),
    /** 调用接口方法 */
    invokeinterface(0xb9),
    /** 未使用 */
    __(0xba),
    /** 创建一个对象，并将其引用值压入栈顶 */
    NEW(0xbb),
    /** 创建一个指定原始类型（如int, */
    newarray(0xbc),
    /** 创建一个引用型（如类，接口，数组）的数组，并将其引用值压入栈顶 */
    anewarray(0xbd),
    /** 获得数组的长度值并压入栈顶 */
    arraylength(0xbe),
    /** 将栈顶的异常抛出 */
    athrow(0xbf),
    /** 检验类型转换，检验未通过将抛出ClassCastException */
    checkcast(0xc0),
    /** 检验对象是否是指定的类的实例，如果是将1压入栈顶，否则将0压入栈顶 */
    INSTANCEOF(0xc1),
    /** 获得对象的锁，用于同步方法或同步块 */
    monitorenter(0xc2),
    /** 释放对象的锁，用于同步方法或同步块 */
    monitorexit(0xc3),
    /** <待补充> */
    wide(0xc4),
    /** 创建指定类型和指定维度的多维数组（执行该指令时，操作栈中必须包含各维度的长度值），并将其引用值压入栈顶 */
    multianewarray(0xc5),
    /** 为null时跳转 */
    ifnull(0xc6),
    /** 不为null时跳转 */
    ifnonnull(0xc7),
    /** 无条件跳转（宽索引） */
    goto_w(0xc8),
    /** 跳转至指定32位offset位置，并将jsr_w下一条指令地址压入栈顶 */
    jsr_w(0xc9),

    breakpoint(0xCA),
    impdep1(0xFE),
    impdep2(0xFF),
    ;

    /** 指令码 */
    int code;
    /** 构造函数 */
    OrderSet(int code) {
        this.code = code;
    }
}