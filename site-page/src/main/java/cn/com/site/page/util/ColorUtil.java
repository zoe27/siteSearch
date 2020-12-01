package cn.com.site.page.util;

import org.apache.commons.lang.StringUtils;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: ColorUtil
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-11-01 23:37
 */
public class ColorUtil {

    public static String randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        String[] s = {""+r,""+g,""+b};
        return StringUtils.join(s, ",");
    }

    public static void main(String[] args){
        System.out.println(randomColor());
    }
}
