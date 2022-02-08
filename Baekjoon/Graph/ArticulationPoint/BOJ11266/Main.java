package Graph.ArticulationPoint.BOJ11266;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int V, E, count;
    static int order;
    static ArrayList<Integer> [] map;
    static int[] searchOrder;
    static boolean[] isCutVertex;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new ArrayList[V+1];
        for(int i=1 ; i<=V ; i++)
            map[i] = new ArrayList<>();
        searchOrder = new int[V+1];
        isCutVertex = new boolean[V+1];

        int a, b;
        for(int i=1 ; i<=E ; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        order = 0;
        for(int i=1 ; i<=V ; i++){
            if(searchOrder[i] == 0){
                dfs(i, true);
            }
        }

        StringBuilder result = new StringBuilder();
        for(int i=1 ; i<=V ; i++){
            if(isCutVertex[i]) {
                count++;
                result.append(i + " ");
            }
        }

        bw.write(count + "\n");
        if(count > 0)
            bw.write(result.toString() + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    // DFS
    static int dfs(int now, boolean isRoot) {
        order++;
        // 1. 체크인
        searchOrder[now] = order;

        int rtn = order;    // 지금 정점 이후에 도달할 수 있는 모든 정점들의 탐색순서 중 가장 작은 값 = low
        int child = 0;      // 자식의 숫자 - Root일 경우 단절점 판단을 위함

        // 3. 연결된 곳 순회
        for(int next : map[now]){
            // 4. 갈 수 있는가? - 탐색 전인가?
            if(searchOrder[next] == 0){
                child++;

                // 5. 간다.
                int low = dfs(next, false);

                if(!isRoot && low >= searchOrder[now]){
                    isCutVertex[now] = true;
                }

                rtn = Math.min(rtn, low);
            } else {    // 자식 정점이 이미 방문한 경우
                rtn = Math.min(rtn, searchOrder[next]);
            }
        }

        if(isRoot && child >= 2){
            isCutVertex[now] = true;
        }

        return rtn;
    }
}
