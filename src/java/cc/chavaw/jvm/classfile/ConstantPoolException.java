package cc.chavaw.jvm.classfile;

/**
 * 常量池异常
 * Created by root on 7/13/17.
 */
public class ConstantPoolException extends Exception {
    /**
     * 构造函数
     * @param index 在常量池位置索引
     */
    public ConstantPoolException(int index) {
        this.index = index;
    }

    /**
     * 异常出现的位置:在常量池中的索引
     */
    public final int index;
}
