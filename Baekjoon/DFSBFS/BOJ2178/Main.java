package BOJ2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] maze;
    static boolean[][] visited;
    static int N, M;
    static int[] dx = { -1, 1, 0, 0 }; //x방향배열-상하
    static int[] dy = { 0, 0, -1, 1 }; //y방향배열-좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        for(int i=0 ; i<N ; i++){
            for(int j=0 ; j<M ; j++)
                maze[i][j] = br.read() - '0';
            br.readLine();
        }

        visited = new boolean[N][M];
        visited[0][0] = true;
        bfs(0,0);
        System.out.println(maze[N-1][M-1]);

    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {x,y});

        int curX, curY;
        int[] node;
        while(!q.isEmpty()){

            /* TEST */
//            System.out.println("[ M A Z E ]");
//            for(int i=0 ; i<N ; i++){
//                for(int j=0 ; j<M ; j++){
//                    System.out.print(maze[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("--------------------------");

            node = q.poll();
//            System.out.println("(" + node[0] + ", " + node[1] + ")");
            curX = node[0];
            curY = node[1];
            for(int i=0; i<4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                // 미로 밖으로 벗어날 경우 skip
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
                    continue;

                // 이미 방문했거나 방문할 수 없는 곳일 경우 skip
                if (visited[nextX][nextY] || maze[nextX][nextY] == 0)
                    continue;

                q.add(new int[] {nextX, nextY});
                maze[nextX][nextY] = maze[curX][curY] + 1;
                visited[nextX][nextY] = true;
//                System.out.println("maze[" + nextX + "][" + nextY + "] = " + maze[nextX][nextY]);
            }

        }

    }
}
