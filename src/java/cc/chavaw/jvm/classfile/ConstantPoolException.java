package cc.chavaw.jvm.classfile;

/**
 * �������쳣
 * Created by root on 7/13/17.
 */
public class ConstantPoolException extends Exception {
    /**
     * ���캯��
     * @param index �ڳ�����λ������
     */
    public ConstantPoolException(int index) {
        this.index = index;
    }

    /**
     * �쳣���ֵ�λ��:�ڳ������е�����
     */
    public final int index;
}
