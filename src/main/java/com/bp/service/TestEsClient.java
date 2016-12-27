package com.bp.service;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bp.entity.Student;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class TestEsClient {

    public static void main(String[] args) {

        try {

            //设置集群名称
            Settings settings = Settings.builder().put("cluster.name", "currentbpcluster").build();
            //创建client
            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//            if (!elasticsearchTemplate.indexExists(APP.ESProp.INDEX_NAME)) {
//                elasticsearchTemplate.createIndex(APP.ESProp.INDEX_NAME);
//            }
//            elasticsearchTemplate.putMapping(TaskInfo.class);



//            for(int i=0;i<10;i++) {
//                Student student = new Student();
//                student.setId(i+1);
//                student.setName("baopan"+(i+1));
//                student.setAddress("address:"+(i+1));
//                student.setAge(25+(i+1));
//
//                Map<String,Object> map = (HashMap<String,Object>)JSON.parseObject(JSON.toJSONString(student),Map.class);
//                System.out.println(map);
//
//                client.prepareIndex("lenews", "student").setId(""+student.getId()).setSource(map).execute();
//            }

            //搜索数据
            GetResponse response = client.prepareGet("lenews", "student", "1").execute().actionGet();
            //输出结果
            System.out.println(response.getSourceAsString());
            //关闭client
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}