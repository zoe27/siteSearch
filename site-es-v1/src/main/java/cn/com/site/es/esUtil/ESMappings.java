package cn.com.site.es.esUtil;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author zhoubin
 * 添加es的mappings
 */
@Component
public class ESMappings {

    /**
     * log日志
     */
    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ESClient esClient;

    private String ip = "127.0.0.1";
    private int port = 9300;

    public void setMappings() {
        XContentBuilder builder;
        try {
            builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("dynamic", "true")
                    //设置type中的属性
                    .startObject("properties")
                    //id属性
                    .startObject("num")
                    //类型是integer
                    .field("type", "integer")
                    //不分词，但是建索引
                    .field("index", "false")
                    //在文档中存储
                    .field("store", "true")
                    .endObject()
                    //name属性
                    .startObject("stuname")
                    //string类型
                    .field("type", "text")
                    //在文档中存储
                    .field("store", "true")
                    //建立索引
                    .field("index", "true")
                    .endObject()
                    .endObject()
                    .endObject();

            CreateIndexRequestBuilder prepareCreate = esClient.initClient(ip, port).admin().indices().prepareCreate("site_test_2");
            prepareCreate.addMapping("stu", builder).execute().actionGet();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error("create mapping error ", e);
        }


    }

    public String getCommonMappings() {
        String analyzer = "zh_analyzer";
        //String enAnalyzer = "ngram_analyzer";
        String json = null;
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
//					.startObject("_all")
//					.field("enabled", false)
//					.endObject()

                    .startObject("properties")
                    .startObject("an").field("type", "text").field("analyzer", analyzer).field("search_analyzer", analyzer).endObject()
                    .startObject("ean").field("type", "text").field("analyzer", analyzer).field("search_analyzer", analyzer).endObject()
                    .endObject()
                    .endObject();
            json = builder.toString();
        } catch (Exception e) {
            logger.error("get mappings error", e);
        }
        return json;
    }

    public XContentBuilder getCommonMappingsBuilder() {
        String analyzer = "zh_analyzer";
        //String enAnalyzer = "ngram_analyzer";
        XContentBuilder builder = null;
        try {
            builder = XContentFactory.jsonBuilder()
                    .startObject()
//					.startObject("_all")
//					.field("enabled", false)
//					.endObject()
//					
                    .startObject("properties")
                    .startObject("title").field("type", "text").field("analyzer", analyzer).field("search_analyzer", "ik_smart").endObject() // 分词用ik_max，查询用ik_smart
                    .startObject("key_words").field("type", "text").field("analyzer", analyzer).field("search_analyzer", "ik_smart").endObject()
                    .startObject("imagePath").field("type", "text").endObject()
                    .startObject("url").field("type", "text").endObject()
                    .startObject("urlMd5").field("type", "text").endObject()
                    .endObject()
                    .endObject();
        } catch (Exception e) {
            logger.error("get mappings error", e);
        }
        return builder;
    }
}
