package cc.chavaw.jvm;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ¹ú¼Ê»¯
 * Created by root on 7/13/17.
 */
public class Internationalization {
    private static ResourceBundle res = ResourceBundle.getBundle("lang.info", Locale.CHINA);
    public static String I(String key, Object...objects) {
        MessageFormat form = new MessageFormat(res.getString(key));
        return form.format(objects);
    }
}
