package cn.com.site.es.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * es相关的常量
 * @author zhoubin
 *
 */
@Component
public class ESConstant {
	
	// ES的ip列表
	@Value("${es.ip}")
	public static String NODE_IP;// = "192.168.199.186";//"172.17.0.2";// 
	
	// ES的端口
	@Value("${es.port}")
	public static int PORT;// = 9300;
	
	public enum ES_SITE_INFO{
		TYPE("site"),
		INDEX("perfectsite_v2"),
		NUMBER_OF_SHARDS("1"),
		NUMBER_OF_REPLICAS("0");
		
		private String info;
		
		private ES_SITE_INFO(String info)
	    {
	        this.info=info;
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

}
