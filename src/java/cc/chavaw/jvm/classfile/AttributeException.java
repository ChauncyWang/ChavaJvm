package cc.chavaw.jvm.classfile;

/**
 * 属性异常，在解析 class file 属性时出现异常。
 */
public class AttributeException extends Exception{
    AttributeException(){}
    AttributeException(String msg) {
        super(msg);
    }
}
