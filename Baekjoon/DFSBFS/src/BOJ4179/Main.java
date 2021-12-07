package BOJ4179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Loc {
    int x;
    int y;
    int time;

    Loc(int x, int y, int t){
        this.x = x;
        this.y = y;
        this.time = t;
    }
}

public class Main {

    static int R, C;
    static Queue<Loc> q;
    static Queue<Loc> fire;
    static int[][] maze;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0 ,0 ,-1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new int[R][C];
        q = new LinkedList<>();
        fire = new LinkedList<>();

        for(int i=0 ; i<R ; i++){
            char[] tmpArr = br.readLine().toCharArray();
            for(int j=0 ; j<C ; j++) {
                char tmp = tmpArr[j];
                if(tmp == '#') {
                    maze[i][j] = -1;
                }
                else if(tmp == 'F'){
                    maze[i][j] = -2;
                    fire.add(new Loc(i, j, 1));
                }
                else if(tmp == 'J') {
                    q.add(new Loc(i,j, 1));
                    maze[i][j] = 1;
                }
                else maze[i][j] = 0;
            }
        }

        int answer = bfs();

        // 0인 경우 impossible
        if(answer == 0)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(answer);

        br.close();

    }

    static int bfs() {
        Loc curJ, curF;
        int fireLen, jhLen;
        while(!q.isEmpty()){

            int nx, ny;
            fireLen = fire.size();
            for(int f=0 ; f<fireLen ; f++){
                curF = fire.poll();
                for(int i=0 ; i<4 ; i++){
                    nx = curF.x + dx[i];
                    ny = curF.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if(maze[nx][ny] == -1 || maze[nx][ny] == -2) continue;

                    fire.add(new Loc(nx, ny, curF.time+1));
                    maze[nx][ny] = -2;
                }
            }
            /*TEST*/
//            System.out.println("[ M A Z E ]");
//            for(int i=0 ; i<R ; i++){
//                for(int j=0 ; j<C ; j++){
//                    System.out.print(maze[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            jhLen = q.size();
            for(int j=0 ; j<jhLen ; j++){
                curJ = q.poll();

                for(int i=0 ; i<4 ; i++){
                    nx = curJ.x + dx[i];
                    ny = curJ.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) return curJ.time;
                    if(maze[nx][ny] == -1 || maze[nx][ny] == -2 || maze[nx][ny] == 1) continue;


                    q.add(new Loc(nx, ny, curJ.time+1));
                    maze[nx][ny] = 1;
                }
            }

        }
        return 0;
    }

}
