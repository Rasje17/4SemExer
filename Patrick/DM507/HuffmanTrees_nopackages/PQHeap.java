/**
 *
 * @author Patrick Skaarup Vie Christoffersen - pachr16@student.sdu.dk
 * @author Rasmus Jensen - rasje17@student.sdu.dk
 * 
 * It has been decided to always build the heap whenever a value is extracted from the queue - not every time something is inserted into it.
 * This should be the most efficient way to always extract the minimum value, compared to sorting every time something new is put into the queue, if the operation consists off a random order of inserts and extracts.
 */
public class PQHeap implements PQ {

    private Element[] pq;
    private int lastIndexInUse = -1;  // initialized at -1 to accomodate for 0-indexing.

    /**
     * Constructor.
     * @param maxElms - the maximum number of elements the queue can hold at any one time.
     */
    public PQHeap(int maxElms) {
        pq = new Element[maxElms];
    }

    /**
     * Extracts the Element with the minimum key-value currently in the queue.
     * @return Element.
     */
    @Override
    public Element extractMin() {
        Element tempE = pq[0];  // holds the minimum value.
        pq[0] = pq[lastIndexInUse];   // replaces the first element in the array with the last.
        pq[lastIndexInUse] = null;    // removes the value of the moved element from the array.
        lastIndexInUse--;             // counts heapsize down.
        minHeapify(0);          // sorts the queue to heaporder.
        return tempE;           // returns the minimum value.
    }

    /**
     * Adds an Element to the queue.
     * @param e - the Element that should be inserted into the queue.
     */
    @Override
    public void insert(Element e) {
        lastIndexInUse++;
        pq[lastIndexInUse] = e;
        int i = lastIndexInUse;
        while(i > 0 && pq[parent(i)].getKey() > pq[i].getKey()) {   // maintains heaporder.
            Element tempE = pq[parent(i)];
            pq[parent(i)] = pq[i];
            pq[i] = tempE;
            i = parent(i);
        }
    }

    /**
     * Method for sorting a nodes position in the heap.
     * @param parent - the node at which the sorting is initiated.
     */
    private void minHeapify(int parent) {
        int smallest;
        int left = leftChild(parent), right = rightChild(parent);           // find indexes of the children of the current node.
        if (left <= lastIndexInUse && pq[left].getKey() < pq[parent].getKey()) {  // if the left child exists, and has a key-value that is smaller than the key of the parent node
            smallest = left;                                                // the index of the smallest node is set to be the left
        } else {
            smallest = parent;                                              // otherwise, it is the parent.
        }
        if (right <= lastIndexInUse && pq[right].getKey() < pq[smallest].getKey()) {  // this process is repeated for the right child (if one exists), valued against the smallest value.
            smallest = right;
        }
        if (smallest != parent) {                                           // if the smallest value is not already the parent, the parent and the smallest value swaps places.
            Element tempE = pq[smallest];
            pq[smallest] = pq[parent];
            pq[parent] = tempE;
            minHeapify(smallest);                                           // this method is called recursively.
        }
    }

    /**
     * Method for finding the parent of a given node.
     * @param i - the node-index.
     * @return index of the parent-node.
     */
    private int parent(int i) {
        return (int) (i - 1) / 2;
    }

    /**
     * Method for finding the left child-node of a given node.
     * @param i - the parent node-index.
     * @return index of the left child-node.
     */
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Method for finding the right child-node of a given node.
     * @param i - the parent node-index.
     * @return index of the right child-node.
     */
    private int rightChild(int i) {
        return 2 * i + 2;
    }
}