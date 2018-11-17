package Stack;

import LinkedList.LinkedList;

/**
 * Created by Jline on 2018/11/14.
 */
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push( E e ) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListStack : ");
        for (int i = 0; i < list.getSize(); i++) {
            if(i<list.getSize()-1)
                sb.append(list.get(i)+"->");
        }
        sb.append(list.get(list.getSize()-1)+"->NULL ");
        return sb.toString();
    }

    public static void main( String[] args ) {
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<Integer>();
        for (int i = 0; i < 10; i++) {
            linkedListStack.push(i);
            System.out.println(linkedListStack);
            if(i%3==2)
                linkedListStack.pop();
        }

    }

}
