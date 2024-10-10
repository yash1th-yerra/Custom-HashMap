import java.util.ArrayList;
import java.util.List;

public class HashCard<K,V> implements Card<K,V>{
    private List<MapNode<K,V>> bucket;      // contains list of nodes/linkedlists to avoid collosion of multiple keys with same hashcode
    private int capacity;  // bucket capacity
    private int size;   // hashmap size
    private final float loadFactor= 0.75f;  // tradeoff between size and bucket (to limit linkedlists)
    private final int initialCapacity = 5;  //initial capacity of bucket

    // initializing hashcard (aka hashmap)

    public HashCard(){
        bucket = new ArrayList<>();
        capacity = initialCapacity;
        for (int i = 0; i < capacity; i++) {{
            bucket.add(null);
        }
            
        }
        

    }
    

    // to store key value pair

    private class MapNode<K,V>{
        K key;
        V value;
        MapNode<K,V> next;
        

        // initializing key and value
        public MapNode(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    



    
  

    @Override
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);  // getting index of specific key 
        MapNode<K,V> head = bucket.get(bucketIndex);    // getting head of linkedlist at that key //each node of linkedlist contains key value pair 
        while(head!=null){
            if(head.key.equals(key)){           // if key already exists just update value
                head.value = value;
            }

            head = head.next;
        }

        // if key doesn't exist create new entry and add value 
        size++;
        MapNode<K,V> newEntry = new MapNode<K,V>(key,value);
        head = bucket.get(bucketIndex);
        newEntry.next = head;      // add new entry of key value pair at head of linkedlist
        bucket.set(bucketIndex,newEntry); // update new head with value of newentry
        


    
        
    }
    
    @Override
    public V get(K key) {
        // TODO Auto-generated method stub
        int bucketIndex = getBucketIndex(key);      // get index of key
        MapNode<K,V> head = bucket.get(bucketIndex);
        while(head!=null){
            if(head.key.equals(key)){
                return head.value;          // return if exxists
            }
            head=head.next;

        }
        return null;  //return null if not exists
    
    
        
    }

    private int getBucketIndex(Object key) {        // getting compressed hashcode as index for each bucket node
        // TODO Auto-generated method stub
        int hash =  key.hashCode();
        return hash%capacity;

    }
    
    @Override
    public void remove(K key) {
        // TODO Auto-generated method stub
        int bucketIndex = getBucketIndex(key);
        MapNode<K,V> head = bucket.get(bucketIndex);
        MapNode<K,V> prev = null;       // maintain prev node to delete 
        while(head!=null){
            if(head.key.equals(key)){
                if(prev == null){       // if prev is null means only one element exists at that index if that key matches delete it
                    bucket.set(bucketIndex,head.next);  

                }
                else{
                    prev.next = head.next;  // if prev exists set that to next of head and delete head

                }
                head.next = null;
                size--;
                break;
            }
            prev = head;
            head = head.next;       // moving to next node
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
    

    @Override
    public void printAllElements() {        // printing all elements of hashcard
        List<MapNode<K, V>> elements = getAllElements();
        for (MapNode<K, V> node : elements) {
            System.out.println("Key: " + node.key + ", Value: " + node.value);
        }
    }
    
    
}
