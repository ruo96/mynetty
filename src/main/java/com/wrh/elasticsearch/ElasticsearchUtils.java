package com.wrh.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;

import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:37 2019/4/25 0025
 * @Modified By:
 */
public class ElasticsearchUtils {
    private Client client;

    private RestHighLevelClient newClient;

    private static ObjectMapper mapper = new ObjectMapper();

    public final RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("172.18.90.67", 9200, "HTTP")); //开发的es
//    public final RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("172.18.90.61", 9300, "HTTP"));



//    public ElasticsearchUtils(String clusterName, String ipAddress,int port) throws UnknownHostException {
    public ElasticsearchUtils() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();
        client =  new PreBuiltTransportClient(settings). addTransportAddress(new TransportAddress(InetAddress.getByName("172.18.90.61"), 9300)); // 测试的es  9300是tcp的   9200是http的
    }

    public Client getClient(){
        return this.client;
    }


    /**
     * 创建索引
     * @param indexName 索引名称，相当于数据库名称
     * @param typeName 索引类型，相当于数据库中的表名
     * @param id id名称，相当于每个表中某一行记录的标识
     * @param jsonData json数据
     */
    public void insert(String indexName, String typeName, String id,
                       String jsonData) {
        client = getClient();
        System.out.println("client: " + client);
        IndexRequestBuilder requestBuilder = client.prepareIndex(indexName,
                typeName, id).setRefreshPolicy(WriteRequest.RefreshPolicy.NONE);//设置索引名称，索引类型，id
        requestBuilder.setSource("content",jsonData).execute().actionGet();//创建索引
    }

    public void insertJsonData(String indexName, String typeName, String id,
                       String jsonData) throws IOException {
        newClient = getRestClient();
        System.out.println("client: " + newClient);

        IndexRequest request = new IndexRequest(indexName,typeName,id);//创建索引
//        request.source(mapper.writeValueAsBytes(jsonData), XContentType.JSON);
        request.source(jsonData, XContentType.JSON);
        IndexResponse indexResponse = newClient.index(request);

        System.out.println("====insert once====");
        /*ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                System.out.println("执行成功了！！");
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("执行失败了！！！");
            }
        };
        newClient.indices().createAsync(request,listener);//需要传入请求和执行完成时候使用的listener*/

       /* boolean acknowledged = createIndexResponse.isAcknowledged();//指示是否所有节点都已确认请求
        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();//指示是否在超市之前未索引中的每个分片启动了必须的分片副本数*/

    }

    private RestHighLevelClient getRestClient() {


        return new RestHighLevelClient(restClientBuilder);

    }

}
