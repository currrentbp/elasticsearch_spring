package com.bp.bean;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by issuser on 2016/12/26.
 */
@Configuration
public class BeanConfig {

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;
    @Value("${elasticsearch.esNodes}")
    private String nodes;
    @Value("${elasticsearch.port}")
    private Integer port;


    @Bean
    public TransportClient client(){
        TransportClient client = null;
        System.out.println("===>client: clusterName:"+clusterName+" nodes:"+nodes);

        Settings settings = Settings.builder().put("cluster.name", clusterName).build();

        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(nodes), port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return client;
    }
}
