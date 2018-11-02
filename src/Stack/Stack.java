package Stack;

/**
 * Created by Jline on 2018/11/2.
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
