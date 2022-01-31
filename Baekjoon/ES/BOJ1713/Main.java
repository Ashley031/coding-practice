package Baekjoon.ES.BOJ1713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Student implements Comparable<Student>{
    int num;
    int vote;
    int order;

    public Student(int num, int vote, int order) {
        this.num = num;
        this.vote = vote;
        this.order = order;
    }

    @Override
    public int compareTo(Student o) {
        int comp = Integer.compare(vote, o.vote);
        if(comp == 0)
            return order - o.order;
        else
            return comp;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int total = Integer.parseInt(br.readLine());
        ArrayList<Student> pic = new ArrayList<>();


        st = new StringTokenizer(br.readLine());
        for(int i=1 ; i<=total ; i++) {
            int now = Integer.parseInt(st.nextToken());
            boolean isExist = false;

            for(int j=0 ; j<pic.size() ; j++) {
                if(pic.get(j).num == now) {
                    pic.set(j, new Student(now, pic.get(j).vote+1, pic.get(j).order));
                    isExist = true;
                }
            }

            if(!isExist) {
                if(pic.size() == N) {
                    Collections.sort(pic);
                    pic.remove(0);
                }
                pic.add(new Student(now, 1, i));
            }
        }

        Collections.sort(pic, Comparator.comparingInt(o -> o.num));

        for(int i=0 ; i<pic.size() ; i++){
            System.out.print(pic.get(i).num + " ");
        }
        br.close();
    }
}
