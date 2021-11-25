package BOJ1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Vector<Integer>[] graph;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M, V;
        StringTokenizer st;
        String num = br.readLine();
        st = new StringTokenizer(num);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        visited = new int[N+1];
        graph = new Vector[N+1];
        for(int i=0 ; i<=N ; i++){
            graph[i] = new Vector<>();
        }

        // Graph
        for(int i=0 ; i<M ; i++){
            num = br.readLine();
            st = new StringTokenizer(num);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }
        
        // 오름차순 정렬
        for(int i=0 ; i<=N ; i++) {
            graph[i].sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
        }

        dfs(V);
        visited = new int[N+1];
        System.out.println();
        bfs(V);

        br.close();
    }

    static void dfs(int num) {

        // 방문 체크
        visited[num] = 1;

        System.out.print(num + " ");

        /* TEST */
//        System.out.println("< GRAPH >");
//        for(int i=0 ; i<graph[num].size() ; i++)
//            System.out.println(graph[num].get(i) + ": 방문여부 = " + visited[graph[num].get(i)]);
//        System.out.println("==============");

        int idx = 0;
        for(int node : graph[num]){
            if(visited[graph[num].get(idx++)] < 1)
                dfs(node);
        }

    }

    static void bfs(int num) {

        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();

        q.offer(num);

        visited[num] = 1;

        while(!q.isEmpty()){
            int node = q.poll();
            sb.append(node + " ");
            for(int i=0 ; i<graph[node].size() ; i++){
                int tmp = graph[node].get(i);
                if(visited[tmp] < 1){
                    visited[tmp] = 1;
                    q.offer(tmp);
                }
            }
        }

        System.out.println(sb.toString());

    }
}
