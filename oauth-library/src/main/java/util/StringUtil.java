package util;

public class StringUtil {

    public String appendString(String[] strings) {
        StringBuilder builder = new StringBuilder();
        if (strings.length > 0) {
            builder.append(strings[0]);
            for (int i = 1; i < strings.length; i++) {
                builder.append(",").append(strings[i]);
            }
        }
        return builder.toString();
    }
}
