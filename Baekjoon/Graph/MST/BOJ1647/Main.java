package Graph.MST.BOJ1647;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.cost);
    }
}

public class Main {

    static long answer;
    static int N,M;
    static int[] group;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new Edge[M+1];
        group = new int[N+1];
        for(int i=1 ; i<=N ; i++)
            group[i] = i;

        int a,b,c;
        for(int i=1 ; i<=M ; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(a,b,c);
        }

        Arrays.sort(edges,1,M+1);

        int connectCount = 0;
        int maxCost = 0;
        for(int i=1 ; i<=M ; i++){
            if(find(edges[i].from) != find(edges[i].to)){
                union(edges[i].from, edges[i].to);
                connectCount++;
                answer += edges[i].cost;
                // 두 마을로 분리했을 때의 최소 비용 구하는 방법
                // 일단 최소신장트리로 만들고, 그 중 가장 큰 길을 없앤다.
                maxCost = Math.max(maxCost, edges[i].cost);
            }

            if(connectCount == N-1)
                break;
        }

        bw.write((answer-maxCost) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void union(int a, int b) {
        int aGroup = find(a);
        int bGroup = find(b);

        group[aGroup] = bGroup;
    }

    static int find(int a) {
        if(a == group[a])
            return a;
        return group[a] = find(group[a]);
    }
}
