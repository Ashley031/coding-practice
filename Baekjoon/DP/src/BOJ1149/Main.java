package BOJ1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

/*
* Top-down 풀이 (재귀)
*
* 규칙: 인접한 집과 색이 달라야한다.
*
* 1. 그때그때의 최솟값을 선택하자. (이전에 선택한 컬러 제외) -> 오답
* 무작정 그때그때의 최솟값을 더하면 안된다!
* -> 이번 집에서 조금 손해보고 다음집에서의 최솟값을 고르는 선택이 더 나을 수도 있다.
*
* 2. 이번 집에서 각 컬러당 나올 수 있는 최솟값을 구하여 누적해서 더하고, 마지막에 최솟값을 선택하자 -> 성공
*
* 점화식:
*   cost[n][0] += min( cost[n-1][1], cost[n-1][2] )
*   cost[n][1] += min( cost[n-1][0], cost[n-1][2] )
*   cost[n][2] += min( cost[n-1][0], cost[n-1][1] )
*/

public class Main {

    static int N;
    static int R, G ,B;
    static int answer;
    static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];

        for(int i=0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            cost[i][0] = R;
            cost[i][1] = G;
            cost[i][2] = B;
        }

        solution(1);
        System.out.println(answer);

    }

    public static void solution(int home) {
        cost[home][0] += Math.min(cost[home-1][1], cost[home-1][2]);
        cost[home][1] += Math.min(cost[home-1][0], cost[home-1][2]);
        cost[home][2] += Math.min(cost[home-1][0], cost[home-1][1]);

        if(home == N-1)
            answer = Math.min(cost[N-1][0], Math.min(cost[N-1][1], cost[N-1][2]));
        else
            solution(home+1);
    }
}
