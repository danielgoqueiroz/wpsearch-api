package com.danielqueiroz.wpsearch.wpsearch.controller;

import com.danielqueiroz.wpsearch.wpsearch.dto.PostDTO;
import com.danielqueiroz.wpsearch.wpsearch.model.Post;
import com.danielqueiroz.wpsearch.wpsearch.service.ElasticClientService;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticController {

	@Autowired
	private ElasticClientService service;
	
	@GetMapping("/save")
	public ResponseEntity<?> get(@RequestParam("q") String query) {
		try {
			List<Post> publishies = service.getPublishies(query);
			return ResponseEntity.accepted().body(publishies);
		} catch (IOException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}

	}
	
	@PostMapping("/save")
	public String save(PostDTO postDTO) {

		try {
			IndexRequest request = new IndexRequest("publicacao");
			IndexResponse response;
			response = ElasticClientService.getClient().index(request, RequestOptions.DEFAULT);
			ElasticClientService.getClient().close();
			return response.getResult().toString();
		} catch (IOException e) {
			return e.getMessage();
		}

	}

}
