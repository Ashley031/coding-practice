package BOJ3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS

class Point {
    int x;
    int y;
    char type;

    public Point(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}

public class Main {
    // 좌, 우, 위, 아래
    static final int[] dX = {-1, 1, 0, 0};
    static final int[] dY = {0, 0, -1, 1};

    static int R, C, waterCnt;
    static char[][] map;
    static int[][] hedgehog;
    static boolean isSuccess;
    static Queue<Point> q;
    static Point startHedge;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        hedgehog = new int[R][C];
        q = new LinkedList<>();

        String input;
        char tmp;
        for(int i=0 ; i<R ; i++) {
            input = br.readLine();
            for(int j=0 ; j<C ; j++) {
                tmp = input.charAt(j);
                map[i][j] = tmp;

                if(tmp == 'S') { // 고슴도치
                    startHedge = new Point(i, j, 'S');
                } else if(tmp == '*') { // 물 - 먼저 번질 수 있게 queue에 먼저 넣어주자.
                    waterCnt++;
                    q.add(new Point(i, j, '*'));
                }
            }
        }

        q.add(startHedge);

        isSuccess = false;
        bfs();

        if(!isSuccess) {
            System.out.println("KAKTUS");
        }

        br.close();
    }

    static void bfs() {
        Point now;
        int nx, ny;
        int curWaterCnt = 0;

        while(!q.isEmpty()) {
            now = q.peek();

            if(now.type == '*') {   // 물
                curWaterCnt = waterCnt;
                for(int i=0 ; i<curWaterCnt ; i++) {
                    // 1. 큐에서 꺼내옴 - 방문 시작
                    now = q.poll();
                    waterCnt--;

                    // 3. 연결된 곳 순회
                    for(int j=0 ; j<4 ; j++){
                        nx = now.x + dX[j];
                        ny = now.y + dY[j];

                        // 4. 갈 수 있는가?
                        if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                        if(map[nx][ny] == 'X' || map[nx][ny] == '*') continue;
                        if(map[nx][ny] == 'D') continue;

                        // 5. 체크인
                        map[nx][ny] = '*';
                        // 6. 큐에 넣음 - 물
                        q.add(new Point(nx, ny, '*'));
                        waterCnt++;
                    }
                }

            }

            else {  // 고슴도치
                now = q.poll();

                // 2. 연결된 곳 순회
                for(int i=0 ; i<4 ; i++) {
                    nx = now.x + dX[i];
                    ny = now.y + dY[i];

                    // 3. 갈 수 있는가?
                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if(map[nx][ny] == '*' || map[nx][ny] == 'X' || hedgehog[nx][ny] > 0) continue;
                    if(nx == startHedge.x && ny == startHedge.y) continue;

                    // 4. 목적지인가?
                    if(map[nx][ny] == 'D'){
                        isSuccess = true;
                        System.out.println(hedgehog[now.x][now.y] + 1);
                        return;
                    }
                    // 5. 체크인
                    hedgehog[nx][ny] = hedgehog[now.x][now.y] + 1;
                    // 6. 큐에 넣어줌
                    q.add(new Point(nx, ny, 'S'));
                }

            }
        }
    }
}
