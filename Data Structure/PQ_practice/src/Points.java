import java.awt.*;

public class Points {
    public static void main(String[] args) {
        MyPQ.IntComparator comp = new MyPQ.IntComparator();
        MyPQ pq = new MyPQ(comp);

        Point a = new Point(5,4);
        pq.insert((int)Math.sqrt(Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)), "a (5,4)");

        Point b = new Point(2,7);
        pq.insert((int)Math.sqrt(Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2)), "b (2,7)");

        Point c = new Point(9,5);
        pq.insert((int)Math.sqrt(Math.pow(c.getX(), 2) + Math.pow(c.getY(), 2)), "c (9,5)");

        Point d = new Point(3,1);
        pq.insert((int)Math.sqrt(Math.pow(d.getX(), 2) + Math.pow(d.getY(), 2)), "d (3,1)");

        Point e = new Point(7,2);
        pq.insert((int)Math.sqrt(Math.pow(e.getX(), 2) + Math.pow(e.getY(), 2)), "e (7,2)");

        Point f = new Point(9,7);
        pq.insert((int)Math.sqrt(Math.pow(f.getX(), 2) + Math.pow(f.getY(), 2)), "f (9,7)");

        Point g = new Point(1,4);
        pq.insert((int)Math.sqrt(Math.pow(g.getX(), 2) + Math.pow(g.getY(), 2)), "g (1,4)");

        Point h = new Point(4,3);
        pq.insert((int)Math.sqrt(Math.pow(h.getX(), 2) + Math.pow(h.getY(), 2)), "h (4,3)");

        Point i = new Point(8,2);
        pq.insert((int)Math.sqrt(Math.pow(i.getX(), 2) + Math.pow(i.getY(), 2)), "i (8,2)");

        Point j = new Point(4,8);
        pq.insert((int)Math.sqrt(Math.pow(j.getX(), 2) + Math.pow(j.getY(), 2)), "j (4,8)");

        System.out.println("[가까운 순서]");
        int len = pq.size();
        for(int n=0 ; n<len ; n++){
            System.out.println(pq.removeMin().getValue());
        }

        System.out.println();

        MyPQ.IntComparator2 comp2 = new MyPQ.IntComparator2();
        MyPQ pq2 = new MyPQ(comp2);

        pq2.insert((int)Math.sqrt(Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)), "a (5,4)");
        pq2.insert((int)Math.sqrt(Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2)), "b (2,7)");
        pq2.insert((int)Math.sqrt(Math.pow(c.getX(), 2) + Math.pow(c.getY(), 2)), "c (9,5)");
        pq2.insert((int)Math.sqrt(Math.pow(d.getX(), 2) + Math.pow(d.getY(), 2)), "d (3,1)");
        pq2.insert((int)Math.sqrt(Math.pow(e.getX(), 2) + Math.pow(e.getY(), 2)), "e (7,2)");
        pq2.insert((int)Math.sqrt(Math.pow(f.getX(), 2) + Math.pow(f.getY(), 2)), "f (9,7)");
        pq2.insert((int)Math.sqrt(Math.pow(g.getX(), 2) + Math.pow(g.getY(), 2)), "g (1,4)");
        pq2.insert((int)Math.sqrt(Math.pow(h.getX(), 2) + Math.pow(h.getY(), 2)), "h (4,3)");
        pq2.insert((int)Math.sqrt(Math.pow(i.getX(), 2) + Math.pow(i.getY(), 2)), "i (8,2)");
        pq2.insert((int)Math.sqrt(Math.pow(j.getX(), 2) + Math.pow(j.getY(), 2)), "j (4,8)");

        System.out.println("[멀리 있는 순서]");
        int len2 = pq2.size();
        for(int n=0 ; n<len2 ; n++){
            System.out.println(pq2.removeMin().getValue());
        }







    }
}
