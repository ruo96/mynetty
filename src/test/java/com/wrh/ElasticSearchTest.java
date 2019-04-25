package com.wrh;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.wrh.elasticsearch.ElasticsearchUtils;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:48 2019/4/25 0025
 * @Modified By:
 */
@Slf4j
public class ElasticSearchTest {

    private  TransportClient client = null;

    @Autowired
    ElasticsearchUtils elasticsearchUtils;


    @Test
    public  void getResult() throws Exception{
        SearchResponse response = client.prepareSearch("forum")//创建查询索引,参数productindex表示要查询的索引库为blog、index
                .setTypes("article")  //设置type
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)//设置查询类型 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询  2.SearchType.SCAN =扫描查询,无序
                .setQuery(QueryBuilders.termQuery("name", "w5"))  //设置查询项以及对应的值
                //	        .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // 设置Filter过滤
                .setFrom(0).setSize(60)//设置分页
                .setExplain(true) //设置是否按查询匹配度排序
                //        .addSort("id", SortOrder.DESC)//设置按照id排序
                .execute()
                .actionGet();
        SearchHits hits = response.getHits();
        System.out.println("总数："+hits.getTotalHits());
        for(SearchHit hit: hits.getHits()) {
            if(hit.getSourceAsMap().containsKey("title")) {
                System.out.println("source.title: " + hit.getSourceAsMap().get("title"));
            }
        }
        System.out.println(response.toString());
        closeClient();
    }


    @Test
    public void getStart() throws Exception{
        List<DiscoveryNode> response = client.listedNodes();
//        log.info("discovernode is : {}",response.get(0).getHostAddress());
        System.out.println("getHostAddress is : " + response.get(0).getHostAddress());
        System.out.println("getHostName is : " + response.get(0).getHostName());
        System.out.println("getName is : " + response.get(0).getName());
        System.out.println("getEphemeralId is : " + response.get(0).getEphemeralId());
        System.out.println("getId is : " + response.get(0).getId());
        System.out.println("getVersion is : " + response.get(0).getVersion());
        System.out.println("getAddress is : " + response.get(0).getAddress());
        System.out.println("getAttributes is : " + response.get(0).getAttributes());
        System.out.println("getRoles is : " + response.get(0).getRoles());
    }

    @Test
    public void getMyIndex() throws Exception{
        SearchResponse response = client.prepareSearch("ecommerce")//创建查询索引,参数productindex表示要查询的索引库为blog、index
                .setTypes("product")  //设置type
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)//设置查询类型 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询  2.SearchType.SCAN =扫描查询,无序
                .setQuery(QueryBuilders.termQuery("name", "yagao"))  //设置查询项以及对应的值
                //	        .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // 设置Filter过滤
                .setFrom(2).setSize(1)//设置分页
                .setExplain(true) //设置是否按查询匹配度排序
                //        .addSort("id", SortOrder.DESC)//设置按照id排序
                .execute()
                .actionGet();
        SearchHits hits = response.getHits();
        System.out.println("总数："+hits.getTotalHits());
        for(SearchHit hit: hits.getHits()) {
            if(hit.getSourceAsMap().containsKey("title")) {
                System.out.println("source.title: " + hit.getSourceAsMap().get("title"));
            }
        }
        System.out.println(response.toString());
        closeClient();
    }

    @Test
    public void getMyRequest() throws Exception{
        SearchResponse response = client.prepareSearch("ecommerce")//创建查询索引,参数productindex表示要查询的索引库为blog、index
//                .addDocValueField("yagao","_source")
                .execute()
                .actionGet();
        SearchHits hits = response.getHits();
        System.out.println("总数："+hits.getTotalHits());
        for(SearchHit hit: hits.getHits()) {
            if(hit.getSourceAsMap().containsKey("name")) {
//            if(hit.getFields().containsKey("name")) {
                System.out.println("source.title: " + hit.getSourceAsMap().get("name"));
                System.out.println("fieldMap: " + JSON.toJSONString(hit.getFields()));
            }
        }
        System.out.println(response.getHits().getHits().length);
        closeClient();
    }

    @Test
    public void getMyScroll() throws Exception{

        SearchResponse response = client.prepareSearch("ecommerce")//创建查询索引,参数productindex表示要查询的索引库为blog、index
                .setScroll("1m")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .execute()
                .actionGet();
        String scrollId = response.getScrollId();
        System.out.println("srollId: "+ scrollId);
        printResult(response);

        for (int i = 0; i < 5; i++) {
            response = client.prepareSearchScroll(scrollId).execute().actionGet();
            printResult(response);
            scrollId = response.getScrollId();
        }
    }

    private void printResult(SearchResponse response){
        SearchHits hits = response.getHits();
        System.out.println("总数："+hits.getTotalHits());
        for(SearchHit hit: hits.getHits()) {
            if(hit.getSourceAsMap().containsKey("name")) {
                System.out.println("source.title: " + hit.getSourceAsMap().get("name"));
            }
        }
        System.out.println(response.getHits().getHits().length);
//        closeClient();
    }

    @Test
    public void testMyInsert() throws UnknownHostException {

        System.out.println("-------------begin------------");
        Student student = new Student();
        ElasticsearchUtils elasticsearchUtils = new ElasticsearchUtils();
        for (int i = 10011; i < 10020; i++) {
            student = new Student();
            student.setName("name" + i);
            student.setId(i);

            String id = "id_" + i;
            try {
//                elasticsearchUtils.insert("school","student",id,new Gson().toJson(student));
                elasticsearchUtils.insert("school","student",id,"content_" + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("--------------end---------------");
    }

    @Test
    public void testMyPageSearch() throws UnknownHostException {

        System.out.println("-------------begin------------");
        Student student = new Student();
        ElasticsearchUtils elasticsearchUtils = new ElasticsearchUtils();

        SearchResponse response = elasticsearchUtils.getClient()
                                    .prepareSearch("school").setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                                    .setSize(200).setScroll(new TimeValue(20000)).execute().actionGet();
        long totalCount = response.getHits().getTotalHits();
//        int page = (int)totalCount*1.0/(200);
        int page = (int)Math.ceil(totalCount*1.0/200);
        System.out.println("总页数为："+page);

        System.out.println("totalCount: " + totalCount);
        Set<String> set = new TreeSet<>();

        for(SearchHit hit: response.getHits()){
            set.add((String) hit.getSourceAsMap().get("content"));
        }


        for (int i = 1; i < page  ; i++) {
            System.out.println("=====page" + i +"=======  start!!!!!");

            response = elasticsearchUtils.getClient()
                        .prepareSearchScroll(response.getScrollId())
                        .setScroll(new TimeValue(20000))
                        .execute().actionGet();
            for(SearchHit hit: response.getHits()){
                set.add((String) hit.getSourceAsMap().get("content"));
            }
            System.out.println("=====page" + i + "===count: " + response.getHits().getHits().length + "=======  done!!!!!");
        }

        System.out.println("===set num:" + set.size());
        System.out.println(set);

        Set<String> notExist = new TreeSet<>();
        for (int i = 0; i < 10000 ; i++) {
            String data = "content_id_"+i;
            if(set.contains(data)){

            }else {
                notExist.add(data);
            }
        }
        System.out.println("not exist count: " + notExist.size());
        System.out.println("not exist: " + notExist);
        System.out.println("--------------end---------------");
    }



    // 获取客户端
    @Before
    public   void getClient() throws Exception{
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();
        client =  new PreBuiltTransportClient(settings). addTransportAddress(new TransportAddress(InetAddress.getByName("172.18.90.61"), 9300));
    }

    // 关闭客户端
    @After
    public  void closeClient(){
        if (this.client != null){
            this.client.close();
        }
    }

}
