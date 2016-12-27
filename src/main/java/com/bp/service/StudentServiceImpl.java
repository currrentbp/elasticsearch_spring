package com.bp.service;

import com.alibaba.fastjson.JSON;
import com.bp.entity.Student;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by issuser on 2016/12/26.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private TransportClient client;

    public boolean createStudent(Student student) {
        System.out.println("===>createStudent: student:" + JSON.toJSONString(student));
        Map<String, Object> map = (HashMap<String, Object>) JSON.parseObject(JSON.toJSONString(student), Map.class);
        System.out.println(map);

        client.prepareIndex("lenews", "student").setId("" + student.getId()).setSource(map).execute();
        return true;
    }

    public boolean updateStudent(Student student) {
        System.out.println("===>updateStudent: student:" + JSON.toJSONString(student));
        Map<String, Object> map = (HashMap<String, Object>) JSON.parseObject(JSON.toJSONString(student), Map.class);
        System.out.println(map);
        client.prepareIndex("lenews", "student").setId("" + student.getId()).setSource(map).execute();
        return false;
    }

    public Student getStudentById(int id) {
        System.out.println("===>getStudentById: id:" + JSON.toJSONString(id));
        GetResponse response = client.prepareGet("lenews", "student", "" + id).execute().actionGet();
        System.out.println(response.getSourceAsString());
        Student student = JSON.parseObject(response.getSourceAsString(), Student.class);
        return student;
    }

    public List<Student> getStudents(String name) {
        System.out.println("===>getStudents: name:" + JSON.toJSONString(name));
        SearchResponse actionGet = client.prepareSearch("lenews").setTypes("student")
                .setQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", name))
                ).execute().actionGet();
        SearchHits hits = actionGet.getHits();
        System.out.println("===>getStudents: hits:"+JSON.toJSONString(hits));
        List<Map<String, Object>> matchRsult = new LinkedList<Map<String, Object>>();
        for (SearchHit hit : hits.getHits())
        {
            matchRsult.add(hit.getSource());
        }
//        GetResponse response1 = client.prepareGet("lenews", "student", "").execute().actionGet();
        return null;
    }
}
