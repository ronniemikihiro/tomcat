package br.com.tomcat.util;

import java.util.List;

/**
 * Created by ronnie-msl on 14/09/17.
 */
public class ListUtil {

    public static boolean isNull(final List list) {
        return list == null;
    }

    public static boolean nonNull(final List list) {
        return !isNull(list);
    }

    public static boolean isNullEmpty(final List list) {
        return list == null || list.isEmpty();
    }

    public static boolean nonNullEmpty(final List list) {
        return !isNullEmpty(list);
    }

}
