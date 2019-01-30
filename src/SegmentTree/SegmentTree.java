package SegmentTree;

/**
 * Created by Jline on 2019/1/10.
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E>  merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;

        data =(E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[arr.length*4];
        buildSegmentTree(0,0,arr.length-1);
    }

    private void buildSegmentTree( int treeIndex, int l, int r ) {
        if(l==r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l+(r-l)/2;
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public E query(int queryL, int queryR){
        if(queryL < 0 || queryL >=data.length || queryR <0 || queryR>= data.length||queryL>queryR)
            throw new IllegalArgumentException("this is illegal!");
        return query(0,0,data.length-1,queryL,queryR);
    }

    private E query( int treeIndex, int l, int r, int queryL, int queryR ) {
        if(l == queryL && r ==queryR)
            return tree[treeIndex];

        int mid = l+(r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if(queryL >= mid +1)
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        if(queryR<=mid)
            return query(leftTreeIndex,l,mid,queryL,queryR);

        E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
        E rightResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);

        return merger.merge(leftResult,rightResult);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
