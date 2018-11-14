package LinkedList;

/**
 * Created by Jline on 2018/11/12.
 */
public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);
//        for (int i = 0; i < linkedList.getSize(); i++) {
//            System.out.println(linkedList.get(i));
//        }
//        System.out.println(linkedList.contains(666));

        linkedList.removeElement(666);
        System.out.println(linkedList);
        for (int i = linkedList.getSize()-1; i >=0; i--) {
            linkedList.remove(i);
        }
        System.out.println(linkedList);
        System.out.println(linkedList.isEmpty());
    }
}