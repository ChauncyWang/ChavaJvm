# Class文件格式
    我们使用一组专门的数据类型来表示 class 文件内容，它们包括 u1、u2和u4,分别代表1、2和4个字节的无符号数。
## ClassFile 结构
    每个 class 文件对应一个如下所示的 ClassFile 结构。
```
ClassFile {
    u4          magic;                              //魔数
    u2          minor_version;                      //次版本号
    u2          major_version;                      //主版本号
    u2          constant_pool_count;                //常量池大小
    cp_info     constant_pool[constant_pool_count]; //常量池
}
```    