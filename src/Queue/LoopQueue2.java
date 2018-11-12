package Queue;

/**
 * Created by Jline on 2018/11/6.
 */
public class LoopQueue2<E> implements Queue<E>  {
    private E[] data;
    private int front,rear;

    public LoopQueue2() {
        this(10);
    }

    public LoopQueue2(int capacity){
        data = (E[]) new Object[capacity+1];
        front=0;
        rear=0;
    }

    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public int getSize() {
        if(front<rear)
            return rear-front;
        if(front>rear)
            return data.length-front+rear;
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return front==rear;
    }

    @Override
    public void enqueue( E e ) {

        if((rear+1)%data.length==front)
            resize(getCapacity()*2);
        data[rear]=e;
        rear = (rear+1)%data.length;

    }

    private void resize( int capacity ) {
        E[] newData =(E[]) new Object[capacity+1];
        for (int i = 0; i < getSize(); i++) {
            newData[i] = data[(i+front)%data.length];
        }
        rear = getSize();
        data = newData;

        front=0;


    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        E ret = data[front];
        data[front]=null;
        front = (front+1)%data.length;
        if(getSize()==getCapacity()/4 && getCapacity()/2 !=0)
            resize(getCapacity()/2);
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("queue is an empty.");
        return data[front];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue: size = %d , capacity = %d\n",getSize(),getCapacity()));
        sb.append("front [");
        for(int i = front ; i != rear; i=(i+1)%data.length){
            sb.append(data[i]);
            if((i+1)%data.length != rear)
                sb.append(",");
        }
        sb.append("] rear");
        return sb.toString();
    }
    public static void main(String[] args){

        LoopQueue2<Integer> queue2 = new LoopQueue2<Integer>();
        for(int i = 0 ; i < 10 ; i ++){
            queue2.enqueue(i);
            System.out.println(queue2);

            if(i % 3 == 2){
                queue2.dequeue();
                System.out.println(queue2);
            }
        }
    }
}
