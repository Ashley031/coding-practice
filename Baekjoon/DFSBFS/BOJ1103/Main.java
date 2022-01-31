package Baekjoon.DFSBFS.BOJ1103;

// DFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[][] board;
    static int[][] move;
    static boolean[][] visited;
    static boolean isILoop;

    static int[] dx = {-1, 1, 0 ,0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        move = new int[N][M];
        visited = new boolean[N][M];
        for(int r = 0 ; r<N ; r++) {
            String str = br.readLine();
            for(int c = 0 ; c < M ; c++) {
                if(str.charAt(c) == 'H')
                    board[r][c] = -1;
                else
                    board[r][c] = str.charAt(c) - '0';
            }
        }

        answer = 0;
        visited[0][0] = true;
        isILoop = false;
        DFS(0,0, 1);

        if(isILoop){
            System.out.println(-1);
        } else
            System.out.println(answer);

    }

    static void DFS(int x, int y, int cnt) {
        answer = Math.max(answer, cnt);
        // 1. 체크인
        move[x][y] = cnt;
        // 3. 연결된 곳 순회
        int nx, ny, moveCnt = board[x][y];
        for(int i=0 ; i<4 ; i++){
            nx = x + dx[i] * moveCnt;
            ny = y + dy[i] * moveCnt;

            // 4. 갈 수 있는가? - 구멍이거나 보드 바깥인 경우 continue
            if(nx < 0 || ny < 0 || nx >= N || ny >= M || board[nx][ny] == -1)
                continue;
            // 이미 방문한 경우 무한번 움직일 수 있다 -> -1
            if(visited[nx][ny]) {
                isILoop = true;
                return;
            }
            if(move[nx][ny] > cnt) continue;
            visited[nx][ny] = true;
            // 5. 간다.
            DFS(nx, ny, cnt+1);
            // 6. 체크아웃
            visited[nx][ny] = false;
        }
    }
}
