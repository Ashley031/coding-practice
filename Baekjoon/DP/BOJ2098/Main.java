package DP.BOJ2098;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, visitAll, answer;
    static int[][] map;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        visitAll = (1<<N) - 1;
        map = new int[N+1][N+1];
        dp = new int[N+1][visitAll+1];

        StringTokenizer st;
        for(int r=1 ; r<=N ; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=1 ; c<=N ; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        answer = INF;
        for(int i=1 ; i<=N ; i++){
            for(int j=0 ; j<=visitAll ; j++){
                dp[i][j] = INF;
            }
        }

        dp[1][1] = 0;
        getDP(1,1);

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void getDP(int now, int visited) {
        // 재귀 종료조건
        if(visited == visitAll) {
            if(map[now][1] == 0)
                return;
            answer = Math.min(answer, dp[now][visited] + map[now][1]);
        }

        for(int i=1 ; i<=N ; i++) {
            int next = (1<<(i-1));
            int nextVisited = visited | next;

            if(nextVisited == visited)
                continue;
            if(map[now][i] == 0)
                continue;

            if(dp[i][nextVisited] > dp[now][visited] + map[now][i]){
                dp[i][nextVisited] = dp[now][visited] + map[now][i];
                getDP(i, nextVisited);
            }
        }
    }
}
