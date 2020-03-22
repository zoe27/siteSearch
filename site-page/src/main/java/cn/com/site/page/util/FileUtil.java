package cn.com.site.page.util;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @title: FileUtil
 * @projectName siteFrontEnd
 * @description: TODO
 * @User: zhoubin
 * @Date: 2020-03-09 21:31
 */
public class FileUtil {

    public static boolean isFileExist(String filePath){
        File f = new File(filePath);
        return f.exists();
    }
}
