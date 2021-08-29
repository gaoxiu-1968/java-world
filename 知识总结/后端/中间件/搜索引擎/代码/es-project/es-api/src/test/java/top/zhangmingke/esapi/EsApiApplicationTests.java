package top.zhangmingke.esapi;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.eql.EqlSearchResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import top.zhangmingke.esapi.pojo.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * es 7.14.0
 */
@SpringBootTest
class EsApiApplicationTests {
	@Autowired
	@Qualifier("restHighLevelClient")
	private RestHighLevelClient client;

	/**
	 * 创建索引
	 */
	@Test
	public void createIndex() throws IOException {
		CreateIndexRequest request = new CreateIndexRequest("person");
		CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
		System.out.println(createIndexResponse.toString());
	}

	/**
	 * 获取索引,判断时候存在
	 */
	@Test
	void getIndex() throws IOException {
		GetIndexRequest request = new GetIndexRequest("jc_org_info");
		GetIndexResponse getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);
		System.out.println(getIndexResponse.getMappings());
	}

	/**
	 * 删除索引
	 */
	@Test
	void deleteIndex() throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest("person");
		AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
		System.out.println(delete.isAcknowledged());
	}

	/**
	 * 添加文档
	 * 规则; PUT /person/_doc/1
	 */
	@Test
	void createDoc() throws IOException {
		//创建对象
		Person person = new Person("张三", 23);

		//创建请求
		IndexRequest request = new IndexRequest("person");

		//规则 PUT /person/_doc/1 ,超时设置1s
		request.id("1");
		request.timeout(TimeValue.timeValueSeconds(1));

		//把数据放入请求体 json格式
		request.source(JSON.toJSONString(person), XContentType.JSON);

		//客户端发送请求
		IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

		//查看响应结果
		System.out.println(indexResponse.toString());
		System.out.println(indexResponse.status());
	}

	/**
	 * 获取文档信息
	 * 规则 GET /person/_doc/1
	 */
	@Test
	void getDocInfo() throws IOException {
		//创建请求
		GetRequest request = new GetRequest("person", "1");
		//客户端发送请求
		GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
		//打印返回的信息
		System.out.println(getResponse.getSourceAsString());
		System.out.println(getResponse.toString());
	}

	/**
	 * 更新文档
	 * POST /person/_doc/1
	 * {
	 *     "doc":{
	 *         更新的信息
	 *     }
	 *
	 * }
	 */
	@Test
	void updateDoc() throws IOException {
		//创建请求
		UpdateRequest request = new UpdateRequest("person", "1");
		//创建对象
		Person person = new Person("王二", 22);
		request.doc(JSON.toJSONString(person),XContentType.JSON);
		//客户端发送请求
		UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
		System.out.println(response.status());
	}

	/**
	 * 删除文档
	 * DELETE /person/_doc/1
	 */
	@Test
	void deleteDoc() throws IOException {
		//创建请求
		DeleteRequest request = new DeleteRequest("person", "1");
		//客户端发送请求
		DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
		System.out.println(response.status());
	}

	/**
	 * 批量插入
	 * 批量删除和批量更新和这个类似
	 */
	@Test
	void bulkDoc() throws IOException {
		//创建请求
		BulkRequest request = new BulkRequest();
		List<Person> list = new ArrayList<>();
		list.add(new Person("李四四",24));
		list.add(new Person("李四李四",24));
		list.add(new Person("李四1",24));
		list.add(new Person("李四2",24));
		list.add(new Person("李四3",24));
		list.add(new Person("李四4",24));
		for (int i = 0; i < list.size(); i++) {
			request.add(
					new IndexRequest("person")
					.id(""+(i+7))
					.source(JSON.toJSONString(list.get(i)),XContentType.JSON)
			);
		}
		//发送请求
		BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
		System.out.printf(String.valueOf(responses.hasFailures()));
	}

	/**
	 * 查询
	 */
	@Test
	void queryDoc() throws IOException {
		//创建请求
		SearchRequest request = new SearchRequest("person");

		//构建查询条件
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		TermQueryBuilder termQueryBuilder = new TermQueryBuilder("name", "zhangsan1");
		sourceBuilder.query(termQueryBuilder);
		request.source(sourceBuilder);

		//客户端发送请求
		SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
		for (SearchHit doc:searchResponse.getHits().getHits()){
			System.out.println(doc.getSourceAsMap());
		}

	}

	/**
	 * 分页查询
	 */
	@Test
	void limitSearch() throws IOException {
		SearchRequest searchRequest = new SearchRequest("person");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.from(0);
		searchSourceBuilder.size(2);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHit[] hits = searchResponse.getHits().getHits();
		for (SearchHit h:hits) {
			System.out.println(h.getSourceAsMap());
		}
	}

	/**
	 * 查询指定字段
	 */
	@Test
	void searchFiled() throws IOException {
		SearchRequest searchRequest = new SearchRequest("person");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		String[] includes = {"name"};
		String[] excludes = {};
		searchSourceBuilder.fetchSource(includes,excludes);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		for (SearchHit h:searchResponse.getHits().getHits()) {
			System.out.println(h.getSourceAsMap());
		}
	}

	/**
	 * 查询所有
	 */
	@Test
	void searchAll() throws IOException {
		SearchRequest searchRequest = new SearchRequest("person");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
		searchSourceBuilder.query(matchAllQueryBuilder);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		for (SearchHit h:searchResponse.getHits().getHits()) {
			System.out.println(h.getSourceAsMap());
		}
	}

	/**
	 * 单条件查询
	 */
	@Test
	void simpleSearch() throws IOException {
		SearchRequest searchRequest = new SearchRequest("person");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("name","李四");
		searchSourceBuilder.query(queryBuilder);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		for (SearchHit h:searchResponse.getHits().getHits()) {
			System.out.println(h.getSourceAsMap());
		}
	}


}
