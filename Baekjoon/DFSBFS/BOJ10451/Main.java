package BOJ10451;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
    static int[] permutation;
    static Vector<Integer>[] cycle;
    static boolean[] visited;
    static int T, N, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            permutation = new int[N + 1];
            visited = new boolean[N + 1];
            cycle = new Vector[N + 1];
            for (int j = 0; j <= N; j++)
                cycle[j] = new Vector<>();
            permutation[0] = 0;
            st = new StringTokenizer(br.readLine());

            // 순열 입력받기
            for (int j = 1; j <= N; j++)
                permutation[j] = Integer.parseInt(st.nextToken());

            // cycle 입력하기
            for (int j = 1; j <= N; j++) {
                cycle[j].add(permutation[j]);
            }

            count = 0;
            for (int j = 1; j <= N; j++) {
                if (!visited[permutation[j]]) {
                    dfs(permutation[j]);
                    count++;
                }
            }
            System.out.println(count);
        }
    }
    static void dfs(int num){
        visited[num] = true;

        int idx = 0;
        for (int node : cycle[num]) {
            if (!visited[node]) dfs(node);
        }
    }
}
