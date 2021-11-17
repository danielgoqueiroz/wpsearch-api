package com.danielqueiroz.wpsearch.wpsearch.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.queryparser.xml.QueryBuilderFactory;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.danielqueiroz.wpsearch.wpsearch.dto.PostDTO;
import com.danielqueiroz.wpsearch.wpsearch.model.Post;

@Service
public class ElasticClientService {

	public ElasticClientService() {
	}

	public static RestHighLevelClient getClient() {
		ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo("localhost:9200").build();
		return RestClients.create(clientConfiguration).rest();
	}

	public Result publishRequest(String json) throws Exception {
		IndexRequest request = new IndexRequest("publicacao");
		request.source(json, XContentType.JSON);
		RestHighLevelClient client = null;
		try {
			client = getClient();
			IndexResponse response = client.index(request, RequestOptions.DEFAULT);
			return response.getResult();
			
		} catch (Exception e) {
			throw new Exception(e.getCause());
		} finally {
			if (client != null) {
				client.close();				
			}
		}
	}

	public Result publishRequest(JSON json) throws Exception {
		PostDTO postDTO = JSON.toJavaObject(json, PostDTO.class);
		Post post = postDTO.getPost();
		String jsonString = JSON.toJSONString(post);
		return publishRequest(jsonString);
	}

	public List<Post> getPublishies(String text) throws IOException {
		SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("publicacao");
        SearchSourceBuilder query = SearchSourceBuilder.searchSource().size(10).query(QueryBuilders.queryStringQuery(text));
		searchRequest.source(query);
        SearchResponse sResponse = getClient().search(searchRequest, RequestOptions.DEFAULT);
        List<Post> searchHits = Arrays.asList(sResponse.getHits().getHits())
        		.stream().map(item -> 
        			JSON.toJavaObject(JSON.parseObject(item.getSourceAsString()), Post.class)).collect(Collectors.toList()
        					);
		return searchHits;
	}

}
