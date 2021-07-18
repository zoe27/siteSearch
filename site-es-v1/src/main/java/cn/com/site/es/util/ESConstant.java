package cn.com.site.es.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhoubin
 * es相关的常量
 */
@Component
public class ESConstant {

    public enum ES_SITE_INFO {
        TYPE("site"),
        INDEX("perfectsite_v4"),
        NUMBER_OF_SHARDS("1"),
        NUMBER_OF_REPLICAS("0");

        private String info;

        private ES_SITE_INFO(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    public static String TITLE = "title";

    public static String KEY_WORDS = "key_words";

    public static String KEYWORDS = "keyWords";

    public static String URL = "url";

    public static String IMAGE_PATH = "imagePath";

    public static String DESC = "desc";

    public static String DATE = "date";

    public static String UP = "upCnt";

    public static String DOWN = "downCnt";

}
