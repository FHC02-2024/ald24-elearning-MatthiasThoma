package A02_Queue;

import A01_Stack.StackEmptyException;

public class Queue<T>
{
    private Node<T> first;
    
    private Node<T> last;

    private int counter;
    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zur�ckliefern.
     * Existiert kein Element, wird eine Exception ausgel�st.
     * @throws QueueEmptyException 
     */
    public T dequeue() throws QueueEmptyException {

        if (counter == 0)
            throw new QueueEmptyException();
        Node<T> top = first;
        first = first.getNext();
        counter--;
        return top.getData();

    }
    
    
    
    /**
     * �bergebenen Integer am Ende der Queue anh�ngen.
     * @param i Zahl
     */
    public void enqueue(T i) {

        Node<T> newNode = new Node<>(i);

        if (first == null)
            first = newNode;
        else{
            last.setNext(newNode);
        }

        last = newNode;
        last.setNext(null);
        counter++;

    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
    	return counter;
    }
}
