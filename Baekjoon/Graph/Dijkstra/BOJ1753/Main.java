package Graph.Dijkstra.BOJ1753;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
    int num;
    int weight;

    public Node(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }
}

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int V, E, K;
    static ArrayList<Node> [] map;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new ArrayList[V+1];
        dist = new int[V+1];
        for(int i=1 ; i<=V ; i++){
            map[i] = new ArrayList<>();
            dist[i] = INF;
        }

        K = Integer.parseInt(br.readLine());    // 시작점

        int u,v,w;
        for(int i=1 ; i<=E ; i++){
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map[u].add(new Node(v,w));  // 유향 그래프
        }

        dijkstra(K);

        for(int i=1 ; i<=V ; i++){
            if(dist[i] != INF)
                bw.write(dist[i] + "\n");
            else
                bw.write("INF\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(now.weight > dist[now.num]) continue;

            for(Node next : map[now.num]){
                if(dist[next.num] > dist[now.num] + next.weight){
                    dist[next.num] = dist[now.num] + next.weight;
                    pq.add(new Node(next.num, dist[next.num]));
                }
            }
        }
    }
}
