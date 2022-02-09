package Graph.ArticulationPoint.BOJ11400;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
* 단절선
*/

class Node implements Comparable<Node> {
    int from;
    int to;

    public Node(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int compareTo(Node o) {
        int comp = Integer.compare(from, o.from);
        if(comp == 0){
            return Integer.compare(to, o.to);
        } else{
            return comp;
        }
    }
}
public class Main {

    static int V,E,K;
    static int order;
    static ArrayList<Integer> [] map;
    static ArrayList<Node> answer;
    static boolean[] isCutVertex;
    static int[] searchOrder;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        searchOrder = new int[V+1];
        isCutVertex = new boolean[V+1];
        answer = new ArrayList<>();
        map = new ArrayList[V+1];
        for(int i=1 ; i<=V ; i++)
            map[i] = new ArrayList<>();

        int a,b;
        for(int i=1 ; i<=E ; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        order = 1;
        for(int i=1 ; i<=V ; i++) {
            if(searchOrder[i] == 0) {
                dfs(i, 0);
            }
        }



        bw.write(K+"\n");
        if(K > 1){
            Collections.sort(answer);
            for(int i=0 ; i<K ; i++){
                bw.write(answer.get(i).from + " " + answer.get(i).to + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int now, int parent) {
        searchOrder[now] = order++;
        int rtn = searchOrder[now];

        for(int next : map[now]) {
            if(next == parent)
                continue;

            if(searchOrder[next] == 0){ // 아직 탐색 X
                int low = dfs(next, now);

                if(low > searchOrder[now]){
                    if(next > now)
                        answer.add(new Node(now, next));
                    else
                        answer.add(new Node(next, now));
                    K++;
                }

                rtn = Math.min(rtn, low);
            } else {    // 자식 정점이 이미 탐색
                rtn = Math.min(rtn, searchOrder[next]);
            }
        }

        return rtn;
    }
}
