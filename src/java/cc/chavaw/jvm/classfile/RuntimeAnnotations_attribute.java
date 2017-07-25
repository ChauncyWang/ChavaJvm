package cc.chavaw.jvm.classfile;

import java.io.IOException;

/**
 * 那些有关 runtime  annotation 的都需要继承者这个属性
 */
public class RuntimeAnnotations_attribute extends Attribute {

    public RuntimeAnnotations_attribute(ClassReader cr, int attribute_name_index, int attribute_length) throws IOException {
        super(attribute_name_index,attribute_length);
        num_annotations = cr.readUnsignedShort();
        annotations = new Annotation[num_annotations];
        for(int i = 0;i < num_annotations;++i) {

        }
    }

    /**
     * 注解的数量
     */
    public final int num_annotations;
    /**
     * 每个成员都代表一个注解类型
     */
    public final Annotation[] annotations;
}
