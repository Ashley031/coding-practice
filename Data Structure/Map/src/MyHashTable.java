import java.util.ArrayList;

public class MyHashTable {
    private ArrayList bucketArray;
    private int bucketCapacity;
    private int totalSize;
    private float loadFactor;

    MyHashTable(int initialCapacity) {
        this.bucketCapacity = initialCapacity;
        this.totalSize = 0;
        this.loadFactor = 0;
        this.bucketArray = new ArrayList();
        for(int i=0 ; i<initialCapacity ; i++)
            this.bucketArray.add(null);
    }

    MyHashTable(int initialCapacity, float loadFactor){
        this.loadFactor = loadFactor;
        this.bucketCapacity = initialCapacity;
        this.totalSize = 0;
        this.bucketArray = new ArrayList();
        for(int i=0 ; i<initialCapacity ; i++)
            this.bucketArray.add(null);
    }

    private int hashFunc(String k) {
        int h = 0;
        int a = 33;

        for(int i=0 ; i<k.length() ; i++){
            h = (h << 5) | (h >>> 27);
            h = (int)(k.charAt(i) * Math.pow(a, k.length()-i-1));
        }

        h = h % this.bucketCapacity;

        return h;
    }

    private void rehash(int capacity){
        ArrayList oldList = this.bucketArray;

        this.bucketCapacity = capacity;
        this.bucketArray = new ArrayList();
        this.totalSize = 0;
        for(int i=0 ; i<capacity ; i++)
            this.bucketArray.add(null);

        for(int i=0 ; i<oldList.size() ; i++){
            ArrayList tempList = (ArrayList) oldList.get(i);
            if(tempList != null){
                for(int j=0 ; j< tempList.size() ; j++){
                    StudentInfo tempStudent = (StudentInfo) tempList.get(j);
                    this.put(tempStudent.getStudentID(), tempStudent.getStudentName());
                }
            }
        }

    }
    public float getLoadFactor() {
        return (float)totalSize / (float)bucketCapacity;
    }
    public int size() { return this.totalSize; }

    public String get(String k) {
        int key = this.hashFunc(k);
        String result = null;

        ArrayList tempList = (ArrayList) this.bucketArray.get(key);

        if(tempList == null)
            System.out.println("this key is empty...");
        else{
            for(int i=0 ; i<tempList.size() ; i++){
                StudentInfo student = (StudentInfo) tempList.get(i);
                if(k.equals(student.getStudentID()))
                    result = student.getStudentName();
            }
        }

        return result;

    }
    public String put(String k, String v) {

        if(this.loadFactor < this.getLoadFactor())
            this.rehash(this.bucketCapacity*2);

        StudentInfo newStudent = new StudentInfo(k, v);
        int key = this.hashFunc(k);
        ArrayList tempList = (ArrayList) this.bucketArray.get(key);

        this.totalSize++;

        if(tempList == null){
            ArrayList newList = new ArrayList();
            newList.add(newStudent);
            this.bucketArray.set(key, newList);
            return null;
        }
        else{
            StudentInfo temp = (StudentInfo) tempList.get(tempList.size()-1);
            tempList.add(newStudent);
            return temp.getStudentName();
        }

    }
    public String remove(String k){
        int key = this.hashFunc(k);
        String result = null;

        ArrayList tempList = (ArrayList) this.bucketArray.get(key);

        if(tempList == null)
            System.out.println("this key is empty...");
        else{
            for(int i=0 ; i<tempList.size() ; i++){
                StudentInfo student = (StudentInfo) tempList.get(i);
                if(k.equals(student.getStudentID())) {
                    result = student.getStudentName();
                    this.totalSize--;
                    tempList.remove(i);
                }
            }
        }

        return result;
    }

    public void printHashTable(){
        System.out.println("======== HASH TABLE ========");
        System.out.println("Capacity : " + this.bucketCapacity + ", Size : " + this.totalSize + ", LoadFactor : " + this.loadFactor);
        for(int i=0 ; i<this.bucketArray.size() ; i++){
            ArrayList tempList = (ArrayList) this.bucketArray.get(i);
            if(tempList == null)
                System.out.println("[ 0 ]");
            else{
                System.out.print("[ " + tempList.size() + " ] - ");
                for(int j=0 ; j<tempList.size() ; j++){
                    StudentInfo temp = (StudentInfo) tempList.get(j);
                    System.out.print(temp.getStudentName() + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        MyHashTable ht = new MyHashTable(13, 0.9F);

        System.out.println("=========== PUT ===========");
        System.out.println(ht.put("201811111", "가가가"));
        System.out.println(ht.put("201811111", "나나나"));
        System.out.println(ht.put("201833333", "다다다"));
        System.out.println(ht.put("201844444", "라라라"));
        System.out.println(ht.put("201855555", "마마마"));
        System.out.println(ht.put("201866666", "바바바"));
        System.out.println("현재 load factor : " + ht.getLoadFactor());

        System.out.println(ht.put("201877777", "사사사"));
        System.out.println(ht.put("201888888", "아아아"));
        System.out.println(ht.put("201899999", "자자자"));
        System.out.println(ht.put("201900000", "차차차"));
        System.out.println(ht.put("201911111", "카카카"));
        System.out.println("현재 load factor : " + ht.getLoadFactor());


        System.out.println("=========== GET ===========");
        System.out.println("get 201811111 " + ht.get("201811111"));
        System.out.println("get 201877777 " + ht.get("201877777"));

        ht.printHashTable();

        System.out.println("=========== REHASH ===========");
        System.out.println(ht.put("201922222", "고고고"));
        System.out.println(ht.put("201933333", "노노노"));
        System.out.println(ht.put("201944444", "도도도"));
        System.out.println(ht.put("201955555", "로로로"));
        System.out.println(ht.put("201966666", "모모모"));
        System.out.println("현재 load factor : " + ht.getLoadFactor());

        ht.printHashTable();

        System.out.println("=========== REMOVE ===========");
        System.out.println("remove 201855555 " + ht.remove("201855555"));
        System.out.println("remove 201966666 " + ht.remove("201966666"));

        ht.printHashTable();


    }
}
