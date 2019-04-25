package com.wrh.elasticsearch;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;

import org.elasticsearch.transport.client.PreBuiltTransportClient;




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


//    public ElasticsearchUtils(String clusterName, String ipAddress,int port) throws UnknownHostException {
    public ElasticsearchUtils() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();
        client =  new PreBuiltTransportClient(settings). addTransportAddress(new TransportAddress(InetAddress.getByName("172.18.90.61"), 9300));
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
                       String jsonData) {
        client = getRestClient();
        System.out.println("client: " + client);
        IndexRequestBuilder requestBuilder = client.prepareIndex(indexName,
                typeName, id).setRefreshPolicy(WriteRequest.RefreshPolicy.NONE);//设置索引名称，索引类型，id
        requestBuilder.setSource("content",jsonData).execute().actionGet();//创建索引
    }

    private Client getRestClient() {
//        this.client = new
        return null;
    }

}
