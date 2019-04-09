package com.wrh.graph;

import java.util.LinkedList;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:16 2019/3/26 0026
 * @Modified By:
 */
public class TestGraph {
    private static class Vertex{
        int data;
        Vertex(int data){
            this.data = data;
        }
    }

    private static class Graph{
        private int size;
        private Vertex[] vertices;
        private LinkedList<Integer> adj[];

        Graph(int size){
            this.size = size;

//            初始化顶点和邻接矩阵
            vertices = new Vertex[size];
            adj = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                vertices[i] = new Vertex(i);
                adj[i] = new LinkedList<>();
            }
        }
    }

    private static void bfs(Graph graph, int start,boolean[]visited){}

}
