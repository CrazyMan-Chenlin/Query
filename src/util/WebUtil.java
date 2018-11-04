package util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebUtil {
    public static <T> T copyRequestToBean(HttpServletRequest request, Class<T> clazz) {
        T t;
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            t = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(t, parameterMap);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
