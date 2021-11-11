package com.danielqueiroz.wpsearch.wpsearch.service;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.stereotype.Service;

@Service
public class ElasticClientService {

    private static RestHighLevelClient client;

    public static RestHighLevelClient getClient() {
        if (client == null) {
            ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo("localhost:9200")
                    .build();
            client = RestClients.create(clientConfiguration).rest();
        }
        return client;
    }

    public static void setClient(RestHighLevelClient client) {
        ElasticClientService.client = client;
    };
}
