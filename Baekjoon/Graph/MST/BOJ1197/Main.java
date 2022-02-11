package Graph.MST.BOJ1197;

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

    static int V, E;
    static long answer;
    static Edge [] edgeList;
    static int[] group;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E+1];
        group = new int[V+1];
        for(int i=1 ; i<=V ; i++){
            group[i] = i;
        }

        // 간선에 대한 정보 -> 간선리스트로 만들자. 크루스칼 알고리즘인듯
        int a,b,c;
        for(int i=1 ; i<=E ; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(a,b,c);
        }

        Arrays.sort(edgeList, 1,E+1);

        int connectCount = 0;
        for(int i=1 ; i<=E ; i++){
            if(find(edgeList[i].from) != find(edgeList[i].to)){
                union(edgeList[i].from, edgeList[i].to);
                connectCount++;
                answer += edgeList[i].cost;
            }

            if(connectCount == V-1)
                break;
        }

        bw.write(answer + "\n");
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
