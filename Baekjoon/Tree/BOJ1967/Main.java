package Baekjoon.Tree.BOJ1967;

/*
* Tree - application problem
* Baekjoon 5052
*
* DFS Algorithm
*/

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static boolean visit[];
    public static int dist[], max, index;
    public static LinkedList<Node> tree[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int parent, child, weight;

        int n = Integer.parseInt(br.readLine());
        tree = new LinkedList[10001];
        visit = new boolean[10001];
        dist = new int[10001];
        for(int i=1 ; i<10001 ; i++)
            tree[i] = new LinkedList<>();


        for(int i=0 ; i<n-1 ; i++){
            st = new StringTokenizer(br.readLine());

            parent = Integer.parseInt(st.nextToken());
            child = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            // 양방향 그래프로 설계
            tree[parent].add(new Node(child, weight));
            tree[child].add(new Node(parent, weight));
        }

        /* TEST */
//        for(int i=1 ; i<n+1 ; i++){
//            System.out.print("tree[" + i + "] = ");
//            for(int j=0 ; j<tree[i].size() ; j++)
//                System.out.print(tree[i].get(j).num + " ");
//            System.out.println();
//        }

        if(n > 1){
            // root에서 가장 먼 노드(A) 탐색
            dfs(1,0);
//            System.out.println("Ok, I found. index : " + index);
            visit = new boolean[10001];
            dist = new int[10001];
            // A에서 가장 먼 노드 탐색
            dfs(index, 0);
            System.out.println(max);
        }
        else
            System.out.println(0);


    }

    public static void dfs(int node, int weight){
        dist[node] = weight;
        visit[node] = true;

        if(weight > max){
            max = weight;
            index = node;
        }

        for(Node next : tree[node]){
//            System.out.println("=== next num : " + next.num + ", next weight : " + next.weight + ", cur weight : "+ weight +", max : " + max);
            if(!visit[next.num])
                dfs(next.num, weight + next.weight);
        }
    }
}

class Node{
    int num, weight;
    Node(int num, int w){
        this.num = num;
        this.weight = w;
    }
}
