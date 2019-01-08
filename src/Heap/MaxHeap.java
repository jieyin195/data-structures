package Heap;

/**
 * Created by Jline on 2019/1/8.
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> array;

    public MaxHeap(int capacity){
        array = new Array<>(capacity);
    }

    public MaxHeap(){
        array = new Array<>();
    }

    public int size(){
        return array.getSize();
    }

    public boolean isEmpty(){
        return array.isEmpty();
    }

    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("parent is not exist!");
        return (index-1)/2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        array.addLast(e);
        siftUp(array.getSize()-1);
    }

    private void siftUp( int k ) {
        while(k > 0 && array.get(k).compareTo(array.get(parent(k)))> 0){
            array.swap(k,parent(k));
            k=parent(k);
        }
    }

    //取出堆中最大的元素
    public E extractMax(){
        if(array.getSize() == 0)
            throw new IllegalArgumentException("array is empty");
        E e = array.get(0);
        array.swap(0,array.getSize()-1);
        array.removeLast();
        siftDown(0);

        return e;
    }

    private void siftDown( int k ) {
        while(leftChild(k)<array.getSize()){
            int j = leftChild(k);
            if(j+1 <array.getSize()&& array.get(j+1).compareTo(array.get(j))>0)
                j = rightChild(k);

            if(array.get(k).compareTo(array.get(j))>=0)
                break;;
            array.swap(k,j);
            k=j;
        }
    }
}
