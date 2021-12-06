package BOJ18352;

import java.io.*;
import java.util.*;

public class Main {

    static boolean[] visited = new boolean[300001];
    static Vector<Integer>[] graph;
    static int[] route;
    static int N, M, K, X;
    public static void main(String[] args) throws IOException {

        Vector<Integer> answer = new Vector<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // Graph
        int A, B;
        graph = new Vector[N+1];
        for(int i=0 ; i<=N ; i++)
            graph[i] = new Vector<>();
        for(int i=0 ; i<M ; i++){
            String cityNum = br.readLine();
            st = new StringTokenizer(cityNum);
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
        }

        route = new int[300001];
        bfs(X);

        for(int i=1 ; i<=N ; i++) {
            if(route[i] == K) answer.add(i);
        }

        if(answer.isEmpty()) System.out.println(-1);
        else{
            answer.sort((o1, o2) -> o1-o2);
            for(int i=0 ; i<answer.size() ; i++){
                System.out.println(answer.get(i));
            }
        }

    }

    public static void bfs(int num) {
        Queue<Integer> city = new LinkedList<>();
        visited[num] = true;
        city.add(num);

        while(!city.isEmpty()){
            int node = city.poll();
            for(int i=0 ; i<graph[node].size() ; i++){
                int tmp = graph[node].get(i);
                if(!visited[tmp]){
                    visited[tmp] = true;
                    city.add(tmp);
                    route[tmp] = route[node] + 1;
                }
            }
        }
    }
}
