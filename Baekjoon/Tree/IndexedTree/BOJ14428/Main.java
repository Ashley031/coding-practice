package Tree.IndexedTree.BOJ14428;

/*
* < Indexed Tree Practice>

* query & update by
* - Top down
* - Bottom up
* */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, S;
    static long[] tree;
    static int[] minIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        S = 1;
        while(S < N)
            S *= 2;
        tree = new long[2*S];
        minIndex = new int[2*S];
        Arrays.fill(tree, Long.MAX_VALUE);
        Arrays.fill(minIndex, Integer.MIN_VALUE);

        // 1. Init Indexed tree
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=S ; i<S+N ; i++) {
            tree[i] = Long.parseLong(st.nextToken());
            minIndex[i] = i;
        }

        // 2. 내부 노드 순회
        for(int i=S-1 ; i>0 ; i--){
            setNode(i);
        }

        M = Integer.parseInt(br.readLine());

        // 2. Query & Update
        long command;
        for(int i=1 ; i<=M ; i++) {
            st = new StringTokenizer(br.readLine());
            command = Long.parseLong(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            if(command == 1) {  // Update
                long b = Long.parseLong(st.nextToken());
                updateBU(a, b);
//                updateTD(1, S, 1, a, b-a);
            } else {    // Query - 크기가 가장 작은 값의 인덱스 출력
                int b = Integer.parseInt(st.nextToken());
                long answer = queryBU(a, b);
//                long answer = queryTD(1,S, 1, a,b);
                bw.write((answer - S + 1) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // < Bottom up >
    static void updateBU(int target, long value) {
        int node = S + target - 1;
        tree[node] = value;
        node /= 2;

        while(node >= 1) {
            setNode(node);
            node /= 2;
        }
    }

    private static void setNode(int node) {
        tree[node] = Math.min(tree[node*2], tree[node*2+1]);
        if(tree[node*2] <= tree[node*2+1]){
            minIndex[node] = minIndex[node*2];
        } else{
            minIndex[node] = minIndex[node*2+1];
        }
    }

    static int queryBU(int left, int right) {
        int resultIdx = 0;
        int leftNode = S + left - 1;
        int rightNode = S + right - 1;

        while(leftNode <= rightNode) {
            if(leftNode % 2 == 1){
                if(tree[leftNode] < tree[resultIdx]){
                    resultIdx = minIndex[leftNode];
                }
                if(tree[leftNode] == tree[resultIdx]){
                    resultIdx = Math.min(resultIdx, minIndex[leftNode]);
                }
                leftNode++;
            }
            if(rightNode % 2 == 0){
                if(tree[rightNode] < tree[resultIdx]){
                    resultIdx = minIndex[rightNode];
                }
                if(tree[rightNode] == tree[resultIdx]){
                    resultIdx = Math.min(resultIdx, minIndex[rightNode]);
                }
                rightNode--;
            }

            leftNode /= 2;
            rightNode /= 2;
        }

        return resultIdx;
    }

    // < Top down > - 이 문제에서는 TD가 부적합
    static void updateTD(int left, int right, int node, int target, long diff){
        // 1. 노드가 target 미포함 -> 연관X
        if(target < left || right < target)
            return;
        // 2. 노드가 target 포함
        else {
            // 2-1. 현재 노드에 diff 반영
            tree[node] += diff;
            // 2-2. 자식이 있다면 update -> 왼쪽 자식이 있을 경우 무조건 오른쪽 자식도 존재
            if(left != right) {
                int mid = (left + right) / 2;
                updateTD(left, mid, node*2, target, diff);
                updateTD(mid+1, right, node*2+1, target, diff);
            }
        }


    }
    static int queryTD(int left, int right, int node, int queryLeft, int queryRight) {
        // 1. 노드가 쿼리 범위 밖일 경우 -> 연관 X
        if(left > queryRight || right < queryLeft) {
            return 0;
        }

        // 2. 리프 노드에 도달했을 경우
        if(left == right)
            return node;

        // 3. 범위에 걸쳐져 있을 경우
        else {
            int mid = (left + right) / 2;
            int leftNode = queryTD(left, mid, node*2, queryLeft, queryRight);
            int rightNode = queryTD(mid+1, right, node*2+1, queryLeft, queryRight);
            return tree[leftNode] > tree[rightNode] ? rightNode : leftNode;
        }
    }
}
