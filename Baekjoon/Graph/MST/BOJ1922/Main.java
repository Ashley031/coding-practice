package Graph.MST.BOJ1922;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*MST - 크루스칼 알고리즘
* 간선 리스트 사용
* */

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
        return cost - o.cost;
    }
}

public class Main {

    static int N, M, answer;
    static Edge [] edgeList;
    static int [] Group;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edgeList = new Edge[M+1];
        Group = new int[N+1];
        for(int i=1 ; i<=N ; i++){
            Group[i] = i;
        }

        StringTokenizer st;
        int a,b,c;
        for(int i=1 ; i<=M ; i++){
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(a,b,c);
        }

        // cost 기준 오름차순 - 최소 비용으로 연결하기 위해
        Arrays.sort(edgeList,1,M+1);

        int connectCount = 0;
        for(int i=1 ; i<=M ; i++){
            // Union-Find 사용
            // find로 다른 그룹인지 확인 - 같은 그룹이면 더 적은 비용으로 이미 이어진 것이므로 연결 필요 X
            if(find(edgeList[i].from) != find(edgeList[i].to)){
                // 같은 그룹이 아니라면 아직 이어져 있지 않은 것이므로 union으로 이어줌
                union(edgeList[i].from, edgeList[i].to);
                answer += edgeList[i].cost;
                connectCount++;
            }

            if(connectCount == N-1)
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

        Group[aGroup] = bGroup;
    }

    static int find(int a) {
        if(a == Group[a])
            return a;
        return Group[a] = find(Group[a]);
    }
}