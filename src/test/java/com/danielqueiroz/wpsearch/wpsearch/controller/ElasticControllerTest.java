package com.danielqueiroz.wpsearch.wpsearch.controller;

import static com.danielqueiroz.wpsearch.wpsearch.service.ElasticClientService.getClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import model.Person;

public class ElasticControllerTest {

    @BeforeAll
    public static void setUp() {
    }

    @Test
    public void givenJsonString_whenJavaObject_thenIndexDocument() throws IOException {

        // Create Json object
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.field("name", "Daniel");
        builder.field("age", "30");
        builder.field("email", "teste@gmail.com");
        builder.endObject();

        // Salva json
        IndexRequest request = new IndexRequest("people");
        request.source(builder);

        IndexResponse response = getClient().index(request, RequestOptions.DEFAULT);
        String index = response.getIndex();
        long version = response.getVersion();

        assertEquals(Result.CREATED, response.getResult());
        assertEquals(1, version);
        assertEquals("people", index);

        // Search
        SearchRequest searchRequest = new SearchRequest();
        SearchResponse sResponse = getClient().search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = sResponse.getHits().getHits();
        List<Person> resultsList = Arrays.stream(searchHits)
                .map(hit -> JSON.parseObject(hit.getSourceAsString(), Person.class)).collect(Collectors.toList());

        assertTrue(resultsList.size() > 0);
    }
}
