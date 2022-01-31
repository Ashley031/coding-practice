package BOJ7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 참고: https://yongku.tistory.com/entry/%EB%B0%B1%EC%A4%80-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-7576%EB%B2%88-%ED%86%A0%EB%A7%88%ED%86%A0-%EC%9E%90%EB%B0%94Java
* */

class Tomato {
    int x;
    int y;

    Tomato(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N, M; // 2 <= M,N <= 1000
    static int[][] tomato;
    static Queue<Tomato> q;
//  static boolean[][] visited;
    /*
    * 처음엔 무조건 방문여부 체크해주는 배열 만들었는데
    * 이번 문제에서는 방문여부를 tomato 배열을 활용해서 체크해주고 있다
    * 그래서 이번 문제에서는 visited 배열을 생략했다.
    * */
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        tomato = new int[N][M];
        q = new LinkedList<>();

        for(int i=0 ; i<N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0 ; j<M ; j++)
                tomato[i][j] = Integer.parseInt(st.nextToken());
        }

        // 익은 토마토는 Queue에 넣어주자
        for(int i=0 ; i<N ; i++){
            for(int j=0 ; j<M ; j++){
                if(tomato[i][j] == 1) {
                    q.add(new Tomato(i, j));
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int nx, ny;
        while(!q.isEmpty()) {
            Tomato cur = q.poll();
            for(int i=0 ; i<4 ; i++){
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];

                // 배열을 벗어나거나 이미 방문했거나, 혹은 방문할 수 없는 경우 continue
                // 이미 익은 경우도 continue
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(tomato[nx][ny] != 0) continue;

                tomato[nx][ny] = tomato[cur.x][cur.y] + 1;
                q.add(new Tomato(nx, ny));
            }
        }

        int answer = Integer.MIN_VALUE;
        for(int i=0 ; i<N ; i++){
            for(int j=0 ; j<M ; j++) {
                if(tomato[i][j] == 0) return -1;

                answer = Math.max(answer, tomato[i][j]);
            }
        }

        if(answer == 1) return 0;
        else return answer - 1;
        // 시작점에서 1이 이미 카운트되어있는 상태였지만
        // 실제로는 "익지 않은 토마토가 처음으로 익혀진 날" 부터 1일이기 때문에
        // 1을 빼줘야 한다.

    }
}
