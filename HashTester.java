import java.util.List;

public class HashTester {
    public static void main(String[] args) {
        HashCard<Integer,String> hashCard = new HashCard<>();
        hashCard.put(1,"a");
        hashCard.put(2,"b");
        hashCard.put(3,"c");
        hashCard.put(4,"f");
        hashCard.put(5,"d");

        System.out.println(hashCard.get(5));
        System.out.println(hashCard.get(2));
        System.out.println(hashCard.get(3));
        System.out.println(hashCard.get(4));
        System.out.println(hashCard.get(3));
        System.out.println(hashCard.get(1));

        hashCard.remove(2);

        // System.out.println(hashCard);

        hashCard.printAllElements();
    }
}
