package BOJ2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Vector;

public class Main {
    static int N, totalCount, cnt;
    static Vector<Integer> homeCount = new Vector<>();
    static int[][] home;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0 ,0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력받고 단지 및 방문여부 배열 초기화
        N = Integer.parseInt(br.readLine());
        home = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0 ; i<N ; i++) {
            for(int j=0 ; j<N ; j++){
                home[i][j] = br.read() - '0';
            }
            br.readLine();
        }

        // dfs로 단지 수 세기
        totalCount = 0;
        for(int i=0 ; i<N ; i++){
            for(int j=0 ; j<N ; j++){
                if(!visited[i][j] && home[i][j] == 1){
                    cnt = 0;
                    dfs(i, j);
                    totalCount++;
                    homeCount.add(cnt);
                }
            }
        }

        // 출력
        System.out.println(totalCount);
        homeCount.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for(int i=0 ; i<homeCount.size() ; i++)
            System.out.println(homeCount.get(i));

        br.close();
    }

    static void dfs(int x, int y){
        visited[x][y] = true;
        cnt++;
        int nx, ny;

        for(int i=0 ; i<4 ; i++){
            nx = x + dx[i];
            ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(visited[nx][ny] || home[nx][ny] == 0) continue;

            dfs(nx, ny);
        }
    }
}
