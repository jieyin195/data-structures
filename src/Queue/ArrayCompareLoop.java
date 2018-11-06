package Queue;

import java.util.Random;

/**
 * Created by Jline on 2018/11/6.
 */
public class ArrayCompareLoop {
    public static double compare(Queue queue,int opCount){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < opCount; i++) {
            queue.enqueue(new Random().nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            queue.dequeue();
        }
        long endTime = System.currentTimeMillis();
        return (endTime-startTime)/1000.0;
    }

    public static void main( String[] args ) {
        int opCount = 100000;
        Queue<Integer> arrayQueue = new ArrayQueue<Integer>();
        System.out.println("arrayQueue:"+compare(arrayQueue,opCount)+"s");

        Queue<Integer> loopQueue = new LoopQueue<Integer>();
        System.out.println("loopQueue:"+compare(loopQueue,opCount)+"s");
    }
}
