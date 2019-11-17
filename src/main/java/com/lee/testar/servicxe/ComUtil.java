package com.lee.testar.servicxe;

import com.lee.testar.utils.FirstAnnotation;
import org.springframework.stereotype.Service;

/**
 * ComUtil
 *
 * @author Lee
 * @date 2019/11/15
 */
@Service
public class ComUtil {
    @FirstAnnotation("来电")
    public String write() {
        return "write something";
    }
}
