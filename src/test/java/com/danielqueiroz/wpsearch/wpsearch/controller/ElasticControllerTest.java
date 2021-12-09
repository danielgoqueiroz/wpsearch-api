package com.danielqueiroz.wpsearch.wpsearch.controller;

import static com.danielqueiroz.wpsearch.wpsearch.service.ElasticClientService.*;
import static com.danielqueiroz.wpsearch.wpsearch.service.ElasticClientService.getClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.danielqueiroz.wpsearch.wpsearch.dto.PostDTO;
import com.danielqueiroz.wpsearch.wpsearch.model.Person;
import com.danielqueiroz.wpsearch.wpsearch.model.Post;
import com.danielqueiroz.wpsearch.wpsearch.service.ElasticClientService;

import org.apache.commons.io.FileUtils;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ElasticControllerTest {

	@Autowired
	private ElasticClientService service;
	
    @Test
    public void salvarPublicações() throws IOException { 
        File baseFolder = new File("D:/Desenvolvimento/MachineLearning/Dados Ndmais/2012");
        Files.walk(baseFolder.toPath()).filter(Files::isRegularFile).forEach(filePath -> {
        	System.out.println(filePath);
            try {
        		JSON json = (JSON) JSON.parse(FileUtils.readFileToString(filePath.toFile()));
				Result result = service.publishRequest(json);
                assertEquals(Result.CREATED, result);
            } catch (Exception e) {
            	System.out.println("Erro ao processar arquivo " + filePath);
                e.printStackTrace();
            }
        });
    }

    @Test
    public void buscarPublicacoes() throws IOException {
    	
    	List<Post> searchHits = service.getPublishies("Covid-19 Joinville");
        assertTrue(searchHits.size() > 0);
        searchHits.forEach(item-> {
        	System.out.println(item);
        });
        
        searchHits.stream().map(post-> {
            post.getId();
            post.getTitle();
            post.getContent();
            post.getAuthor();
            post.getDate();
            return post;
        }).collect(Collectors.toList());
    }

    @Test
    @Disabled
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
