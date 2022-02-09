package Graph.Dijkstra.BOJ1854;

import java.io.*;
import java.util.*;

class City implements Comparable<City>{
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

    static int N, M, K;
    static ArrayList<City> [] map;
    static PriorityQueue<Integer> [] dist;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = new int[N+1];
        map = new ArrayList[N+1];
        dist = new PriorityQueue[N+1];
        for(int i=1 ; i<=N ; i++){
            map[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
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
            if(dist[i].size() < K)
                bw.write(-1 + "\n");
            else
                bw.write(dist[i].peek());
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static void dijkstra(int start) {
        PriorityQueue<City> pq = new PriorityQueue<>();
        dist[start].add(0);
        pq.add(new City(start, 0));

        while(!pq.isEmpty()){
            City now = pq.poll();

            if(now.cost > dist[now.num].peek()) {
                continue;
            }

            for(City next : map[now.num]){
                if(dist[next.num].size() < K){
                    dist[next.num].add(now.cost + next.cost);
                    pq.add(new City(next.num, now.cost + next.cost));
                } else if(dist[next.num].peek() > (now.cost + next.cost)) {
                    dist[next.num].poll();
                    dist[next.num].add(now.cost + next.cost);
                    pq.add(new City(next.num, (now.cost + next.cost)));
                }
            }
        }



    }
}
