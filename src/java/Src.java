import java.io.Serializable;

/**
 * 用来解析的 类
 * Created by 13969 on 2017/6/22.
 */
public class Src  {
    public static void main(String args) {
        Src src = new Src();
        src.b = 100;
        src.setAa(111);
    }
    public int b;
    private int aa;
    public void setAa(int a) {
        aa = a;
    }
}
