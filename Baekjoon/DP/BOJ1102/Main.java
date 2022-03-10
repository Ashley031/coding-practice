package DP.BOJ1102;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* Ref: https://loosie.tistory.com/230
*
* 비트마스킹
* */

public class Main {

    static int N, P, curOn;
    static int[][] cost, dp;
    static int isOn;
    static int answer;
    static final int init = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        cost = new int[N][N];
        dp = new int[N+1][1<<16];
        for(int i=0 ; i<=N ; i++){
            Arrays.fill(dp[i], -1);
        }

        StringTokenizer st;
        for(int i=0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N ; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String status = br.readLine();
        curOn = 0;
        // 비트마스킹
        for(int i=0 ; i<status.length() ; i++){
            if(status.charAt(i) == 'Y'){
                isOn = isOn | (1<<i);
                curOn++;
            }
        }

        P = Integer.parseInt(br.readLine());

        answer = solution(curOn, isOn);
        answer = answer == init ? -1 : answer;

        bw.write(answer +"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(int cnt, int posNum) {
        if(cnt >= P) return 0;

        if(dp[cnt][posNum] != -1) return dp[cnt][posNum];

        dp[cnt][posNum] = init;
        for(int i=0 ; i<N ; i++){
            if((posNum & (1<<i)) == (1<<i)) {
                for(int j=0 ; j<N ; j++){
                    if((i==j) || (posNum & (1<<j)) == (1<<j)) continue;

                    dp[cnt][posNum] = Math.min(dp[cnt][posNum], solution(cnt+1, posNum|(1<<j)) + cost[i][j]);
                }
            }
        }

        return dp[cnt][posNum];
    }

}
