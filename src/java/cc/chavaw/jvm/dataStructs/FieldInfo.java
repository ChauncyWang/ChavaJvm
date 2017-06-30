package cc.chavaw.jvm.dataStructs;

import cc.chavaw.jvm.dataStructs.attribute.AttributeInfo;

/**
 * 字段
 * Created by 13969 on 2017/6/22.
 */
public class FieldInfo {
    /** 访问标志组成的掩码 */
    private AccessFlag[] accessFlags;
    /** 有效的非限定名在常量池表中的一个有效索引 */
    private short nameIndex;
    /** 字段描述在常量池表的一个有效索引 */
    private short descriptorIndex;
    /** 字段的附加属性的数量 */
    private short attributeCount;
    private AttributeInfo[] attributeInfos;
}
