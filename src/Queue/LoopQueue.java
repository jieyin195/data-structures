package Queue;

/**
 * Created by Jline on 2018/11/6.
 */
public class LoopQueue<E> implements Queue<E>{

    private E[] data;
    private int front,rear;
    private int size;


    public LoopQueue( int capacity ) {
        data =(E[]) new Object[capacity+1];
        front=0;
        rear=0;
        size=0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front==rear;
    }

    @Override
    public void enqueue( E e ) {
        if((rear+1)%data.length==front)
            resize(getCapacity()*2);
        data[rear] = e;
        rear = (rear+1)%data.length;
        size++;
    }

    private void resize( int capacity ) {
        E[] newData = (E[])new Object[capacity+1];
        for (int i = 0; i < size; i++) {
            newData[i]=data[(i+front)%data.length];
        }
        data = newData;
        front=0;
        rear = size;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        E ret = data[front];
        data[front]=null;
        front = (front+1)%data.length;
        size--;
        if(size == getCapacity()/4 && getCapacity()/2 !=0)
            resize(getCapacity()/2);
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue: size: = %d , capacity = %d\n",size,getCapacity()));
        sb.append("front [");
        for(int i = front ; i != rear ; i=(i+1)% data.length){
            sb.append(data[i]);
            if((i+1)%data.length != rear)
                sb.append(",");
        }
        sb.append("] rear");
        return sb.toString();
    }
    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
