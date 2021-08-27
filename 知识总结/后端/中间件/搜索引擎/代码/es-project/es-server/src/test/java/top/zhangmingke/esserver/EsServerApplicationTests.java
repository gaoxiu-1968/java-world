package top.zhangmingke.esserver;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class EsServerApplicationTests {
	@Autowired
	@Qualifier("restHighLevelClient")
	private RestHighLevelClient client;

	@Test
	void getIndex() throws IOException {
		GetIndexRequest request = new GetIndexRequest("jc_org_info");
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);
	}

	/**
	 * 分页查询
	 */
	@Test
	void getLimitList() throws IOException {
		SearchRequest searchRequest = new SearchRequest("jc_org_info");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.from(0);
		searchSourceBuilder.size(1);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		for (SearchHit h:searchResponse.getHits().getHits()){
			System.out.println(h.getSourceAsMap());
		}

	}

}
