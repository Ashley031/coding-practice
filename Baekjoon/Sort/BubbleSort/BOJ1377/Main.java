package BOJ1377;

import java.io.*;
import java.util.Arrays;

public class Main {

    /*
    * 버블 소트 알고리즘 정리부터 하고 문제풀이 들어감.
    *
    *  < 문제 풀이 >
    * 문제의 C++ 코드 분석부터 시작
    * Q. 해당 코드에서 출력시키는 값은?
    * A. 오름차순을 만족하고 있을 경우
    * "해당 회전이 몇회전차인지" 출력하고 반복문 중단
    *
    * 1. 문제의 소스 코드를 그대로 자바로 옮겼더니 당연히 시간초과...
    * 버블 정렬 시간복잡도가 굉장히 비효율적인 걸 방금 정리해놓고 잊고 있었음...
    * 그렇다면 이 문제는 버블 정렬을 순수히 구현하는 것이 아닌, 버블 정렬 과정에서의 규칙을 이용해야한다.
    * (괜히 골드 3이 아니었다)
    *
    * 2. 버블 정렬 거의 안 쓴다는데 이렇게까지 해야하나...
    * 하지만 이미 시작했으니 해야지 뭐...
    * 규칙: 특정값이 몇칸 "앞"으로 이동했는지 구하고, 그 값 중 최대값 + 1이 정답이 된다.
    * Q. 왜 뒤로 간 횟수는 안 구해?
    * A. 앞쪽으로 이동하는 요소는 1회전에 1칸이 최대지만,
    * 뒤로 이동하는 요소는 1회전에 얼마든지 이동할 수 있으므로
    * 몇회전차인지 세려면 "앞쪽으로 이동하는 요소"들을 중심으로 비교해야한다.
    * */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Point[] A = new Point[n+1];

        for(int i=1 ; i<=n ; i++){
            int tmp = Integer.parseInt(br.readLine());
            A[i] = new Point(tmp, i);
        }

        Arrays.sort(A,1,n+1);

        int max = 0;
        for(int i=1 ; i<=n ; i++){
            if(max < A[i].index - i) max = A[i].index - i;
        }

        System.out.println((max+1) + "\n");
        br.close();
    }
}

class Point implements Comparable<Point>{

    int num;
    int index;

    Point(int num, int idx){
        this.num = num;
        this.index = idx;
    }

    // Arrays.sort를 위한 compare 함수
    @Override
    public int compareTo(Point o) {
        return num - o.num;
    }
}
