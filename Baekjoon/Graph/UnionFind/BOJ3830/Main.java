package Graph.UnionFind.BOJ3830;

import java.io.*;
import java.util.StringTokenizer;

/*
 *
 * WeightDiff[i] = 내 Root (Parent) 보다 무거운 정도.
 * 사용예) WeightDiff[2] = 3 이고 Parent[2] = 4 라면 2번 노드는 4번노드 보다 상대적으로 3만큼 더 무겁다.
 *
 * 즉, 최상위 Parent 의 WeightDiff 값은 0이 된다.
 *
 * */

public class Main {

    static int N, M;
    static int[] group;
    static long[] weightDiff;   // 내 parent보다 무거운 정도
    static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        char command;

        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0)
                break;

            weightDiff = new long[N+1];
            group = new int[N+1];
            for(int i=1 ; i<=N ; i++){
                group[i] = i;
            }

            int a,b;
            long w;
            for(int i=1 ; i<=M ; i++){
                st = new StringTokenizer(br.readLine());
                command = st.nextToken().charAt(0);
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                if(command == '!'){ // 무게 측정
                    w = Long.parseLong(st.nextToken());
                    union(a,b,w);
                } else {    // 교수님의 질문
                    if(find(a) == find(b)){
                        answer = weightDiff[b] - weightDiff[a];
                        bw.write(answer + "\n");
                    } else{
                        bw.write("UNKNOWN\n");
                    }
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void union(int a, int b, long diff) {
        int aGroup = find(a);
        int bGroup = find(b);

        if(aGroup == bGroup)
            return;

        // a의 부모를 b의 부모로 삼음
        weightDiff[bGroup] = weightDiff[a] - weightDiff[b] + diff;  // 관계 잘 생각하기
        group[bGroup] = aGroup;
    }

    static int find(int a) {
        if(group[a] == a)
            return a;
        else {
            int parentIdx = find(group[a]);
            weightDiff[a] += weightDiff[group[a]];
            return group[a] =  parentIdx;
        }
    }
}
