package LinkedList;

/**
 * Created by Jline on 2018/11/12.
 */
public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;

        public Node( E e, Node next ) {
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node virtualHead;
    private int size;

    public LinkedList() {
        this.virtualHead = new Node();
        this.size = 0;
    }


    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(int index,E e){
        //index可以等于size，在链表末尾添加.
        if(index<0||index>size)
            throw new IllegalArgumentException("add failed");

        Node prev = virtualHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;

        size++;
    }

    public void addFirst(E e){
        add(0  ,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public E get(int index){
        if(isEmpty())
            throw new IllegalArgumentException("The linkedList is empty!");
        if(index<0||index>=size)
            throw new IllegalArgumentException("get failed!");

        Node cur = virtualHead.next;
        for (int i = 0; i < index ; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public void set(int index , E e){
        if(isEmpty())
            throw new IllegalArgumentException("The linkedList is empty!");
        if(index<0||index>=size)
            throw new IllegalArgumentException("set failed!");
        Node cur =virtualHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e){
        Node prev = virtualHead;
        while(prev.next != null) {
            if (prev.next.e.equals(e))
                return true;
            prev = prev.next;
        }
        return false;
    }

    public E remove(int index){
        if(isEmpty())
            throw new IllegalArgumentException("Cannot remove an empty LinkedList!");
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("remove failed!");

        Node prev = virtualHead;
        for (int i = 0; i < index; i++) {
            prev =prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public void removeElement(E e){
        Node prev = virtualHead;
        while(prev.next != null){
            if(prev.next.e.equals(e)){
                Node retNode = prev.next;
                prev.next = retNode.next;
                retNode.next = null;
                size--;
            }else
                prev = prev.next;

        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList:  ");
        for (int i = 0; i < size; i++) {
            if(i<size-1)
                sb.append(get(i)+"->");
            else
                sb.append(get(i)+"->NULL");
        }
      return sb.toString();
    }
}
