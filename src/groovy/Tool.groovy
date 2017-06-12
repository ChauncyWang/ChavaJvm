/**
 * 工具类
 * Created by 13969 on 2017/6/13.
 */
class Tool {
    static void main(String[] args) {
        File file = new File("src/res/新建文本文档.txt")
        file.eachLine {
            line ->
                String[] re = line.split(" +")
                println("/** " + re[2] + " */")
                println(re[1]+"("+re[0]+"),")
        }
    }
}
