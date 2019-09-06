package cn.com.site.es.esUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Component;

import cn.com.site.es.util.ESConstant;


/**
 * 配置es的settings
 * 
 * @author zhoubin
 *
 */
@Component
public class ESSettings {
	
	/**
     * log日志
     */
    private Log logger = LogFactory.getLog(this.getClass());

	// 创建索引，并配置一些参数
	public Settings createIndexWithSettings() {
		// 参数配置器
		Settings settings = Settings.builder()// 指定索引分区的数量
				.put("index.number_of_shards", 3)
				// 指定索引副本的数量（注意：不包括本身，如果设置数据存储副本为2，实际上数据存储了3份）
				.put("index.number_of_replicas", 1).build();
		return settings;
	}
	
	public String getCommomSettings() {
		String json = null;
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().
					startObject()
					
					.startObject("index")
					.startObject("analysis")
					.startObject("analyzer")
					.startObject("zh_analyzer")
					.field("tpye","custom").field("tokenizer", "ik_max_word")
					.array("filte", "lowercase","asciifolding").array("char_filter", "html_strip")
					.endObject()
					
					.startObject("en_analyzer")
					.field("type","custom").field("tokenizer","letter")
					.array("filter", "lowercase","asciifolding","en_snow").array("char_filter", "html_strip")
					.endObject()
					
					.startObject("ngram_analyzer")
					.field("type", "custom").field("tokenizer", "letter")
					.array("filter", "lowercase","asciifolding").array("char_filter", "html_strip")
					.endObject()
					.endObject()
					
					.startObject("filter")
					.startObject("en_snow").field("type", "snowball").field("language","english").endObject()
					.endObject()
					
					.startObject("tokenizer")
					.startObject("ngram_tokenizer")
					.field("type", "ngram")
					.field("min_gram", 3)
					.field("max_gram", 3)
					.array("token_chars", "letter", "digit")
					.endObject()
					.endObject()
					.endObject()
					
					.field("number_of_shards", "3")
					.field("number_of_replicas","1")
					.endObject()
					.endObject();
			
			json = builder.toString();
			
		}catch(Exception e) {
			//logger.error("get settings erros", e);
		}
		return json;
	}
	
	public XContentBuilder getCommomSettingsBuider() {
		XContentBuilder builder = null;
		try {
			builder = XContentFactory.jsonBuilder().
					startObject()
					
					.startObject("index")
					.startObject("analysis")
					.startObject("analyzer")
					.startObject("zh_analyzer")
					.field("tpye","custom").field("tokenizer", "ik_max_word")
					//.field("tpye","custom").field("tokenizer", "standard")
					.array("filte", "lowercase","asciifolding").array("char_filter", "html_strip")
					.endObject()
					
					.startObject("en_analyzer")
					.field("type","custom").field("tokenizer","letter")
					.array("filter", "lowercase","asciifolding","en_snow").array("char_filter", "html_strip")
					.endObject()
					
					.startObject("ngram_analyzer")
					.field("type", "custom").field("tokenizer", "letter")
					.array("filter", "lowercase","asciifolding").array("char_filter", "html_strip")
					.endObject()
					.endObject()
					
					.startObject("filter")
					.startObject("en_snow").field("type", "snowball").field("language","english").endObject()
					.endObject()
					
					.startObject("tokenizer")
					.startObject("ngram_tokenizer")
					.field("type", "ngram")
					.field("min_gram", 3)
					.field("max_gram", 3)
					.array("token_chars", "letter", "digit")
					.endObject()
					.endObject()
					.endObject()
					
					.field("number_of_shards", ESConstant.ES_SITE_INFO.NUMBER_OF_SHARDS.getInfo())
					.field("number_of_replicas",ESConstant.ES_SITE_INFO.NUMBER_OF_REPLICAS.getInfo())
					.endObject()
					.endObject();
			
			
		}catch(Exception e) {
			logger.error("get settings erros", e);
		}
		return builder;
	}
	
	public static void main(String[] args) {
		//System.out.println(getCommomSettings());
	}

}
