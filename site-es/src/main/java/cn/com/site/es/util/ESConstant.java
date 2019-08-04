package cn.com.site.es.util;

/**
 * es相关的常量
 * @author zhoubin
 *
 */
public class ESConstant {
	
	// ES的ip列表
	public static final String NODE_IP = "192.168.199.186";//"127.0.0.1";
	
	// ES的端口
	public static final int PORT = 9300;
	
	public enum ES_SITE_INFO{
		TYPE("site"),
		INDEX("perfectsite"),
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
	    
	 public static String KEY_WORDS = "keyWords";
	 
	 public static String URL = "url";
	 
	 public static String IMAGE_PATH = "imagePath";

}
