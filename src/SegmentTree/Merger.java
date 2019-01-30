package SegmentTree;

/**
 * Created by Jline on 2019/1/10.
 */
public interface Merger<E> {
    E merge(E i, E j);
}
