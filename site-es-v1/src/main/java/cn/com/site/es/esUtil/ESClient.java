package cn.com.site.es.esUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.site.es.conf.EsConfInfo;
import cn.com.site.es.util.ESConstant;

/**
 * 建立一个ES客户端
 * 
 * @author zhoubin
 *
 */
@Component
public class ESClient {

	private TransportClient client = null;

	@Autowired
	private ESSettings settings;
	
	@Autowired
	private EsConfInfo esConfInfo;

	/**
	 * log日志
	 */
	private Log logger = LogFactory.getLog(ESClient.class);

	/**
	 * 初始化一个连接client， 指定端口和ip
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	public TransportClient initClient(String ip, int port) {
		TransportClient client = null;
		// 设置集群名称
		Settings setTmp = settings.createIndexWithSettings();
		// 创建client
		try {
			client = new PreBuiltTransportClient(setTmp);
			client.addTransportAddress(new TransportAddress(InetAddress.getByName(ip), port));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			logger.error("", e);
			;
		}

		return client;
	}

	/**
	 * 初始化类的时候，创建一个clinet对象
	 * 
	 * @return
	 */
	@PostConstruct
	public TransportClient initClient() {
		String nodes = esConfInfo.getIp();//ESConstant.NODE_IP;
		int port = esConfInfo.getPort();//ESConstant.PORT;
		// Splitter.maps

		String clusterName = "elasticsearch";
		Settings settings = Settings.builder().put("cluster.name", clusterName)//.put("clinet.transport.sniff", true)
				.build();
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
		try {
			logger.info("es ip is " + nodes + " and port is " + port);
			client.addTransportAddress(new TransportAddress(InetAddress.getByName(nodes), port));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			logger.error("clinet inti the ip ,port error", e);
		}
		this.client = client;
		return client;
	}

	/**
	 * 创建一个索引，7.2版本下貌似不可用
	 * 
	 * @param index
	 * @param settingsJson
	 * @return
	 */
	public boolean createIndex(String index, String settingsJson) {
		IndicesAdminClient adminClient = client.admin().indices();
		boolean isSuc = false;
		try {
			CreateIndexResponse response = adminClient.prepareCreate(index).setSettings(settingsJson, XContentType.JSON)
					.get();
			if (response.isAcknowledged()) {
				logger.info("create index success, index = " + index);
			} else {
				logger.error("create index error, index = " + index);
			}
			isSuc = response.isAcknowledged();
		} catch (Exception e) {
			logger.error("create index error", e);
		}
		return isSuc;
	}

	/**
	 * 创建一个type，7.2版本貌似不可用
	 * 
	 * @param index
	 * @param type
	 * @param mappingJson
	 * @return
	 */
	public boolean createType(String index, String type, String mappingJson) {
		IndicesAdminClient adminClient = client.admin().indices();
		AcknowledgedResponse response = adminClient.preparePutMapping(index).setType(type)
				.setSource(mappingJson, XContentType.JSON).get();
		if (response.isAcknowledged()) {
			logger.info("create type success , index = " + index + " , type = " + type);
		} else {
			logger.info("create type error , index = " + index + " , type = " + type);
		}
		return response.isAcknowledged();
	}

	/**
	 * 判断索引是否存在
	 * 
	 * @param index
	 * @return
	 */
	public boolean isIndexEsxist(String index) {
		IndicesAdminClient adminClient = client.admin().indices();
		IndicesExistsResponse response = adminClient.exists(new IndicesExistsRequest().indices(new String[] { index }))
				.actionGet();
		logger.info("index is exist: " + response.isExists());
		return response.isExists();
	}

	/**
	 * 创建一个索引， 7.2索引貌似不可用
	 * 
	 * @param index
	 * @param settings
	 * @param type
	 * @param mappings
	 * @return
	 */
	public boolean createNewIndexAndType(String index, String settings, String type, String mappings) {
		if (isIndexEsxist(index)) {
			logger.warn("index is exist, index name is :" + index);
			return false;
		}
		boolean createIndex = createIndex(index, settings);
		boolean createType = createType(index, type, mappings);
		return createIndex & createType;
	}

	/**
	 * 创建一个索引，7.2版本可用
	 * 
	 * @param index
	 * @param settings
	 * @return
	 */
	public boolean createIndexByBuilder(String index, XContentBuilder settings) {
		IndicesAdminClient adminClient = client.admin().indices();
		boolean isSuc = false;
		try {
			CreateIndexResponse response = adminClient.prepareCreate(index).setSettings(settings).get();
			if (response.isAcknowledged()) {
				logger.info("create index success, index = " + index);
			} else {
				logger.error("create index error, index = " + index);
			}
			isSuc = response.isAcknowledged();
		} catch (Exception e) {
			logger.error("create index error", e);
		}
		return isSuc;
	}

	/**
	 * 创建一个type，7.2版本可用
	 * 
	 * @param index
	 * @param type
	 * @param mapping
	 * @return
	 */
	public boolean createTypeByBuilder(String index, String type, XContentBuilder mapping) {
		IndicesAdminClient adminClient = client.admin().indices();
		AcknowledgedResponse response = adminClient.preparePutMapping(index).setType(type).setSource(mapping).get();
		if (response.isAcknowledged()) {
			logger.info("create type success , index = " + index + " , type = " + type);
		} else {
			logger.info("create type error , index = " + index + " , type = " + type);
		}
		return response.isAcknowledged();
	}

	/**
	 * 通过builder的方式创建索引，确定settings和type
	 * 
	 * @param index
	 * @param settings
	 * @param type
	 * @param mappings
	 * @return
	 */
	public boolean createNewIndexAndTypeByBuilder(String index, XContentBuilder settings, String type,
			XContentBuilder mappings) {
		if (isIndexEsxist(index)) {
			logger.warn("index is exist, index name is :" + index);
			return false;
		}
		boolean createIndex = createIndexByBuilder(index, settings);
		boolean createType = createTypeByBuilder(index, type, mappings);
		return createIndex & createType;
	}

	/**
	 * 删除某一个索引
	 * 
	 * @param index
	 * @return
	 */
	public boolean deleteIndex(String index) {
		if (!isIndexEsxist(index)) {
			logger.warn("index is not exist, index = " + index);
			return false;
		}
		IndicesAdminClient adminClient = client.admin().indices();

		AcknowledgedResponse response = adminClient.prepareDelete(index).get();
		logger.info("delete index = " + index + " and finally " + response.isAcknowledged());
		return response.isAcknowledged();
	}

	/**
	 * 获取es中的数据，传入参数依次为 index type query 以及需要返回的字段
	 * 
	 * @param strs
	 * @return
	 */
	public SearchResponse getData(String... strs) {
		SearchResponse response = null;
		try {
			if (null == strs || strs.length < 3) {
				logger.error("params is not enough");
				return null;
			}
			String index = "";
			String type = "";
			String query = "";
			if (strs.length >= 3) {
				index = strs[0];
				type = strs[1];
				query = strs[2];
			}

			SearchRequestBuilder requestBuilder = client.prepareSearch(index).setTypes(type);
			BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
			queryBuilder.should(QueryBuilders.matchQuery(ESConstant.TITLE, query).boost(1.0f))
					.should(QueryBuilders.termQuery(ESConstant.TITLE, query))
					.should(QueryBuilders.termQuery(ESConstant.KEY_WORDS, query))
					.should(QueryBuilders.matchQuery(ESConstant.KEY_WORDS, query).boost(0.5f));
			// 字段排序，后续补上
			// requestBuilder.addSort(field, order)
			requestBuilder.setFrom(0).setSize(20);
			requestBuilder.setQuery(queryBuilder);			
			logger.info(queryBuilder);
			logger.info(requestBuilder);

			// 排除的字段，以及保留的字段
			// requestBuilder.setFetchSource(new String[] {}, new String[] {});
			// 设置 _source 要返回的字段
			// .setFetchSource(Constants.fetchFieldsTSPD, null);
			response = requestBuilder.get();
			if (null != response) {
				response.getHits();
			}
		} catch (Exception e) {
			logger.error("", e);
		}

		return response;

	}
}
