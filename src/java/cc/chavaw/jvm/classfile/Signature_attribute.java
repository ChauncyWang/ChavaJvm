package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 【使用位置】类，字段，方法<br>
 * 【含   义】任何类、接口、初始化方法、或成员的泛型签名如果包含了类型变量 或 参数化类型<br>
 *  该属性会为它记录泛型签名信息。之所以要设这样一个属性，是因为java的泛型采用的是擦除法实现的伪泛型。<br>
 *  这样就无法通过运行时反射获取到泛型信息，该属性就是为了弥补这个缺陷而增设的。
 */
public class Signature_attribute extends Attribute{
    /**
     * 利用 class 读取类加载对象
     * @param cr class 读取类
     * @param attribute_name_index 属性的名字索引
     * @param attribute_length 属性的长度
     * @throws IOException 读取时发生IO异常
     */
    public Signature_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index, attribute_length);
        signature_index = cr.readUnsignedShort();
    }
    /**
     * 直接进行构造
     *  @param attribute_name_index 　属性名称索引
     * @param attribute_length     属性的长度
     * @param signature_index 签名的索引
     */
    public Signature_attribute(int attribute_name_index, int attribute_length, int signature_index) {
        super(attribute_name_index, attribute_length);
        this.signature_index = signature_index;


    }

    /**
     * 一个常量池索引，该索引在常量池中的项必须是 Constant Utf8 info 结构，表示类名，方法类型签名或字段类型签名
     */
    public final int signature_index;

}
