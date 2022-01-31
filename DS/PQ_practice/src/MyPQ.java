package DS.PQ_practice.src;

import java.util.ArrayList;
import java.util.Comparator;

public class MyPQ {

    public static class IntComparator implements Comparator{
        public int compare(Object o1, Object o2){
            return (int)o1 - (int)o2;
        }
    }

    public static class IntComparator2 implements Comparator{
        public int compare(Object o1, Object o2){
            return (int)o2 - (int)o1;
        }
    }

    private ArrayList<MyEntry> pq;
    private Comparator comp;
    // Constructors
    MyPQ() {
        this.pq = null;
        this.comp = null;
    }

    MyPQ(Comparator comp) {
        this.pq = new ArrayList<>();
        this.comp = comp;
    }

    MyPQ(int initialCapacity, Comparator comp) {
        this.pq = new ArrayList<>(initialCapacity);
        this.comp = comp;
    }

    // Implement the following methods
    public int size() {
        return this.pq.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public MyEntry insert(Object k, Object v) {
        MyEntry newEnt = new MyEntry(k, v);

        int len = pq.size();

        /*
         * Scan thru the list 'pq' to find the proper position p
         * Insert newEnt into 'pq' at position p
         * */

        int position = 0;
        for(int i=0 ; i<len ; i++){
            MyEntry temp = pq.get(i);
            if(this.comp.compare(k, temp.getKey())>0){
                position++;
            }
        }
        pq.add(position, newEnt);

        return newEnt;
    }

    public MyEntry removeMin() {
        return pq.remove(0);
    }

    public MyEntry min() {
        return pq.get(0);
    }
}
