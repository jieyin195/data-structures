package Array;

/**
 * Created by Jline on 2018/10/30.
 */
public class Array<E> {
    private E data[];
    private int size;

    public Array(int capacity){
        data =(E[]) new Object[capacity];
        size=0;
    }

    public Array(){
        this(10);
    }

    /**
     * 获取数组的容量
     * @return 数组长度
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 获取数组中元素的个数
     * @return 数组中元素的个数
     */
    public int getSize(){
        return size;
    }

    /**
     * 判断数组是否为空
     * @return true or false
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 在数组开头添加元素
     * @param e 要插入的元素
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 在数组末尾添加元素
     * @param e 要插入的元素
     */
    public void addLast(E e){
        add(size,e);
    }
    /**
     * 添加元素
     * @param index 位置
     * @param e 要插入的元素
     */
    public void add(int index,E e)  {

        if(index<0 || index>size){
            throw new IllegalArgumentException("Add failed, Require index >=0 and index <=size");
        }

        if(size == data.length){
            resize(2 * data.length);
        }

        for(int i=size-1;i>=index;i--){
            data[i+1]=data[i];
        }
        data[index]=e;
        size++;
    }

    /**
     * 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
     * @param e 要查找的元素
     * @return i or -1
     */
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }

    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        if(index<0 || index>= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    public E getLast(){
        return get(size-1);
    }

    public E getFirst(){
        return get(0);
    }
    /**
     * 修改index索引位置的元素为e
     * @param index
     * @param e
     */
    public void set(int index,E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    /**
     * 从数组中删除index位置的元素, 返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if(index<0 || index>=size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        E ret = data[index];
        for (int i = index; i < size-1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size]=null;

        if(size == data.length/4 && data.length/2 !=0)
            resize(data.length/2);
        return ret;
    }

    /**
     * 从数组中删除第一个元素, 返回删除的元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素, 返回删除的元素
     * @return
     */
    public E removeLast(){
        return remove(size-1);
    }

    /**
     * 从数组中删除最靠前的一个元素e
     * @param e
     */
    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(index);
    }

    /**
     * 从数组中删除所有的元素e
     * @param e
     */
    public void removeElementAll(E e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)) {
                remove(i);
                i--;
            }
        }
    }
    /**
     * 查找数组中是否有元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e))
                return true;
        }
        return false;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size = %d , capacity = %d\n",size,data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if(i <size -1)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private void resize( int newCapacity ) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i]= data[i];
        }
        data = newData;
    }
}

