package generals.util;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Util class
 * @author Yidi Chen
 * @date 2022-01-17
 */
public class Util {

    /**
     * Turn variadic args to array
     * @return array
     */
    public static String[] messageOf(Object ...str) {
        return Arrays.stream(str).map(Object::toString).toArray(String[]::new);
    }
}
