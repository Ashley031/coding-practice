public class MyEntry implements Entry{

    private Object key;
    private Object value;

    MyEntry() {
        this.key = null;
        this.value = null;
    }

    MyEntry(Object key, Object value){
        this.key = key;
        this.value = value;
    }

    @Override
    public void setKey(Object k) {
        this.key = k;
    }

    @Override
    public void setValue(Object v) {
        this.value = v;
    }

    @Override
    public Object getKey() {
        return this.key;
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}
