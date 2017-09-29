package br.com.tomcat.util;

/**
 * Created by ronnie-msl on 14/09/17.
 */
public class ObjectUtil {

    public static boolean isNull(final Object obj) {
        return obj == null;
    }

    public static boolean nonNull(final Object obj) {
        return obj != null;
    }
}
