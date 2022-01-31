package BOJ2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] area;    // 주어진 지역 높이
    static int[][] dryArea;   // 잠기지 않는 지역 체크
    static boolean[][] visited;
    static int N, maxHeight, count, max;
    static int[] dx = { -1, 1, 0, 0 }; //x방향배열-상하
    static int[] dy = { 0, 0, -1, 1 }; //y방향배열-좌우
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        area = new int[N][N];

        maxHeight = 0;
        for(int i=0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0 ; j<N ; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                if(maxHeight < area[i][j]) maxHeight = area[i][j];
            }
        }

        max = 1;
        for(int i=1 ; i<=maxHeight ; i++){
            count = 0;
            dryArea = new int[N][N];
            visited = new boolean[N][N];

            // 3중포문이 맞나...시간복잡도 개나 줬네
            // 안 잠기는 지역 확인
            for(int j=0 ; j<N ; j++) {
                for(int k=0 ; k<N ; k++) {
                    if (area[j][k] > i) dryArea[j][k] = 1;
                }
            }

            for(int x=0 ; x<N ; x++){
                for(int y=0 ; y<N ; y++){
                    if(dryArea[x][y] == 1 && !visited[x][y]){
                        dfs(x,y);
                        count++;
                    }
                }
            }

            if(max < count) max = count;

        }

        System.out.println(max);
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        int nextX, nextY;
        for(int i=0 ; i<4 ; i++){
            nextX = x + dx[i];
            nextY = y + dy[i];

            if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
                continue;
            if(visited[nextX][nextY] || dryArea[nextX][nextY] == 0)
                continue;

            dfs(nextX, nextY);
        }



    }
}
