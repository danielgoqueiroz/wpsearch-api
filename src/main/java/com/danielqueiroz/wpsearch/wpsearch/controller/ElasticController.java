package com.danielqueiroz.wpsearch.wpsearch.controller;

import com.danielqueiroz.wpsearch.wpsearch.dto.PostDTO;
import com.danielqueiroz.wpsearch.wpsearch.service.ElasticClientService;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticController {

    // @GetMapping("/save")
    // public RestHighLevelClient save(PostDTO postDTO) {

    // // Recebom post completo
    // // Faz parser para post como objeto
    // // salva post

    // IndexRequest request = new IndexRequest("publicacao");
    // IndexResponse response = ElasticClientService.getClient().index(request,
    // RequestOptions.DEFAULT);
    // RestHighLevelClient client = ElasticClientService.getClient();
    // ElasticClientService.getClient().close();
    // return client;
    // }

}
