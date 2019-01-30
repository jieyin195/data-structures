package AVL;

/**
 * Created by Jline on 2019/1/29.
 */
public class AVLTree<K extends Comparable<K> ,V> {
    private class Node{
        public K key;
        public V value;
        public Node left , right;
        public int height;

        public Node(K key , V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height =1 ;
        }

    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 获得节点node的高度
    public int getHeight(Node node){
        if(node == null)
            return 0;
        return node.height;
    }

    // 获得节点node的平衡因子
    public int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key , V value){

    }
}
