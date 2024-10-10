import java.util.ArrayList;
import java.util.List;

public class HashCard<K,V> implements Card<K,V>{
    private List<MapNode<K,V>> bucket;
    private int capacity;
    private int size;
    private final float loadFactor= 0.75f;
    private final int initialCapacity = 5;


    public HashCard(){
        bucket = new ArrayList<>();
        capacity = initialCapacity;
        for (int i = 0; i < capacity; i++) {{
            bucket.add(null);
        }
            
        }
        

    }


    private class MapNode<K,V>{
        K key;
        V value;
        MapNode<K,V> next;

        public MapNode(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    



    
  

    @Override
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K,V> head = bucket.get(bucketIndex);
        while(head!=null){
            if(head.key.equals(key)){
                head.value = value;
            }

            head = head.next;
        }


        size++;
        MapNode<K,V> newEntry = new MapNode<K,V>(key,value);
        head = bucket.get(bucketIndex);
        newEntry.next = head;
        bucket.set(bucketIndex,newEntry);
        


    
        
    }
    
    @Override
    public V get(K key) {
        // TODO Auto-generated method stub
        int bucketIndex = getBucketIndex(key);
        MapNode<K,V> head = bucket.get(bucketIndex);
        while(head!=null){
            if(head.key.equals(key)){
                return head.value;
            }
            head=head.next;

        }
        return null;
    
    
        
    }

    private int getBucketIndex(Object key) {
        // TODO Auto-generated method stub
        int hash =  key.hashCode();
        return hash%capacity;

    }
    
    @Override
    public void remove(K key) {
        // TODO Auto-generated method stub
        int bucketIndex = getBucketIndex(key);
        MapNode<K,V> head = bucket.get(bucketIndex);
        MapNode<K,V> prev = null;
        while(head!=null){
            if(head.key.equals(key)){
                if(prev == null){
                    bucket.set(bucketIndex,head.next);  

                }
                else{
                    prev.next = head.next;

                }
                head.next = null;
                size--;
                break;
            }
            prev = head;
            head = head.next;
        }

    }

    public List<MapNode<K, V>> getAllElements() {
        List<MapNode<K, V>> allElements = new ArrayList<>();

        // Iterate over each bucket
        for (int i = 0; i < capacity; i++) {
            MapNode<K, V> head = bucket.get(i);

            // Traverse the linked list at each bucket
            while (head != null) {
                allElements.add(head); // Add the node to the list
                head = head.next;
            }
        }
        return allElements; // Return the list of all elements
    }

    public void printAllElements() {
        List<MapNode<K, V>> elements = getAllElements();
        for (MapNode<K, V> node : elements) {
            System.out.println("Key: " + node.key + ", Value: " + node.value);
        }
    }
    
    
}
