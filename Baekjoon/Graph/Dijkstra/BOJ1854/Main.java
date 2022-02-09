package Graph.Dijkstra.BOJ1854;

import java.io.*;
import java.util.*;

class City implements Comparable<City> {
    int num;
    int cost;

    public City(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(City o) {
        return cost - o.cost;
    }
}

public class Main {

    static int N, M ,K;
    static ArrayList<City> [] map;
    static PriorityQueue<Integer> [] dist;
    // < PQ로 dist 배열을 만든 이유 >
    // K번째 최단경로까지 일단 다 찾고,
    // 이 안에서 알아서 K번째 최단 경로가 맨 위로 올라오도록 정렬하기 위함

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N+1];
        dist = new PriorityQueue[N+1];
        for(int i=1 ; i<=N ; i++){
            map[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>(Comparator.reverseOrder());   // K번째 최단 경로가 제일 위로 올라오게 하기 위함
        }

        int a,b,c;
        for(int i=1 ; i<=M ; i++){
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            map[a].add(new City(b,c));
        }

        dijkstra(1);

        for(int i=1 ; i<=N ; i++){
            if(dist[i].size() == K)
                bw.write(dist[i].peek() + "\n");
            else
                bw.write(-1 + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static void dijkstra(int start) {
        PriorityQueue<City> pq = new PriorityQueue<>();

        dist[start].add(0);
        pq.add(new City(start, 0));

        while(!pq.isEmpty()) {
            City now = pq.poll();

            // 지금 현재 비용이 dist에 저장되어 있는 최단 경로보다 크다면 탐색할 필요 X
            if(now.cost > dist[now.num].peek())
                continue;

            for(City next : map[now.num]) {
                // 아직 K번째 최단경로까지 안 찾은 경우 -> 추가
                if(dist[next.num].size() < K){
                    dist[next.num].add(now.cost + next.cost);
                    pq.add(new City(next.num, (now.cost + next.cost)));
                }
                // K번째 최단경로까지는 찾았지만, 더 짧은 최단 경로가 나왔을 경우 -> 교체
                else if(dist[next.num].peek() > now.cost + next.cost){
                    dist[next.num].poll();
                    dist[next.num].add(now.cost + next.cost);
                    pq.add(new City(next.num, (now.cost + next.cost)));
                }
            }
        }

    }
}
