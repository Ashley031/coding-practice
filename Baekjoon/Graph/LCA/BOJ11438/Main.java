package Graph.LCA.BOJ11438;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, logN;
    static int[] depth;
    static int[][] parents;
    static ArrayList<Integer> [] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        getLogN();
        depth = new int[N+1];
        parents = new int[logN+1][N+1];
        map = new ArrayList[N+1];
        for(int i=1 ; i<=N ; i++) {
            map[i] = new ArrayList<>();
        }

        StringTokenizer st;
        int a,b;
        for(int i=1 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        doBfs(1);
        makeSparseTable();

        M = Integer.parseInt(br.readLine());
        for(int i=1 ; i<=M ; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            bw.write(getLCA(a,b) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
    
    static void getLogN() {
        logN = 1;
        for(int i=1 ; i<=N ; i*=2)
            logN++;
    }

    // Init depth & parents[0]
    static void doBfs(int start) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        depth[start] = 1;
        dq.add(start);

        while(!dq.isEmpty()){
            int now = dq.poll();
            for(int next : map[now]){
                if(depth[next] == 0){   // if not visited
                    depth[next] = depth[now] + 1;
                    parents[0][next] = now;
                    dq.add(next);
                }
            }
        }
    }

    static void makeSparseTable() {
        for(int i=1 ; i<=logN ; i++){
            for(int j=1 ; j<=N ; j++){
                // LCA 점화식
                parents[i][j] = parents[i-1][parents[i-1][j]];
            }
        }
    }

    static int getLCA(int a, int b) {
        // 깊이가 깊은 것부터 조작할 수 있게 조정
        if(depth[a] < depth[b])
            return getLCA(b,a);

        // b와 깊이가 맞을 때까지 a를 올린다
        for(int i=0 ; i<=logN ; i++){
            if(((depth[a] - depth[b]) & (1<<i)) >= 1){
                a = parents[i][a];
            }
        }

        // 높이를 맞췄다면 같은지 검사
        if(a == b)
            return a;

        // 두 부모가 같을 때까지 올라감
        for(int i=logN ; i>=0 ; i--){
            if(parents[i][a] != parents[i][b]){
                a = parents[i][a];
                b = parents[i][b];
            }
        }

        // a와 b 둘 중 어느 부모를 리턴해도 어차피 같은 LCA
        return parents[0][a];
    }
}
