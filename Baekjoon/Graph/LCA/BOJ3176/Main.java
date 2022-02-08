package Graph.LCA.BOJ3176;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class City {
    int to;
    int len;

    public City(int to, int len) {
        this.to = to;
        this.len = len;
    }
}

public class Main {
    static int N, K, logN;
    static int max, min;
    static int[] depth;
    static int[][] parents;
    static int[][] maxLen, minLen;
    static ArrayList<City> [] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        getLogN();
        parents = new int[logN+1][N+1];
        maxLen = new int[logN+1][N+1];
        minLen = new int[logN+1][N+1];

        depth = new int[N+1];
        map = new ArrayList[N+1];
        for(int i=1 ; i<=N ; i++) {
            map[i] = new ArrayList<>();
        }

        int a,b,c;
        for(int i=1 ; i<N ; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            map[a].add(new City(b,c));
            map[b].add(new City(a,c));
        }

        doBfs(1);
        makeSparseTable();

        K = Integer.parseInt(br.readLine());
        int d,e;
        for(int i=1 ; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            getLCA(d,e);

            bw.write(min + " " + max + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void getLogN() {
        logN = 0;
        for(int i=1 ; i<=N ; i*=2){
            logN++;
        }
    }

    static void doBfs(int start) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        depth[start] = 1;
        dq.add(start);

        while(!dq.isEmpty()) {
            int now = dq.poll();

            for(City next : map[now]){
                if(depth[next.to] == 0){
                    depth[next.to] = depth[now] + 1;
                    dq.add(next.to);
                    parents[0][next.to] = now;
                    maxLen[0][next.to] = next.len;
                    minLen[0][next.to] = next.len;
                }
            }
        }
    }

    static void makeSparseTable() {
        for(int i=1 ; i<=logN ; i++) {
            for(int j=1 ; j<=N ; j++) {
                parents[i][j] = parents[i-1][parents[i-1][j]];
                maxLen[i][j] = Math.max(maxLen[i-1][j], maxLen[i-1][parents[i-1][j]]);
                minLen[i][j] = Math.min(minLen[i-1][j], minLen[i-1][parents[i-1][j]]);
            }
        }
    }

    static int getLCA(int a, int b) {
        if(depth[a] < depth[b]) return getLCA(b,a);

        max = 0;
        min = 1000001;

        // 높이 맞추기
        for(int i=0 ; i<=logN ; i++){
            if(((depth[a]-depth[b]) & (1<<i)) >= 1){
                min = Math.min(min, minLen[i][a]);
                max = Math.max(max, maxLen[i][a]);
                a = parents[i][a];
            }
        }

        // 높이 맞췄으면 같은지 검사
        if(a==b)
            return a;

        // 공통조상이 아닐 때까지 부모를 따라 올라간다.
        // 최종적으로는 LCA 바로 밑칸까지만 올라간다.
        for(int i=logN ; i<=0 ; i--){
            if(parents[i][a] != parents[i][b]){
                min = Math.min(min, Math.min(minLen[i][a], minLen[i][b]));
                max = Math.max(max, Math.max(maxLen[i][a], maxLen[i][b]));
                a = parents[i][a];
                b = parents[i][b];
            }
        }

        min = Math.min(min, Math.min(minLen[0][a], minLen[0][b]));
        max = Math.max(max, Math.max(maxLen[0][a], maxLen[0][b]));

        return parents[0][a];
    }
}