package A03_DoubleLinkedList;


public class DoubleLinkedList<T>
{

    private Node<T> first;

    private Node<T> last;

    private Node<T> current;

    private int counter;

    /**
     * Einfügen einer neuen <T>
     * @param a <T>
     */
    public void add(T a) {

        Node<T> newNode = new Node<>(a);

        if (first == null) {
            newNode.setPrevious(null);
            newNode.setNext(null);
            first = newNode;
            current = first;
            last = first;

        }/*
        else if (last == null){

            first.setNext(newNode);
            newNode.setPrevious(first);
            newNode.setNext(null);
            last = newNode;
            current = last;
        }
        */
        else {

            last.setNext(newNode);
            newNode.setPrevious(last);
            newNode.setNext(null);
            last = newNode;
            current = last;
        }
        //last.setNext(last);
        counter++;


    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {

        if (first != null)
            current = first;

    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {


        current = first;
        while (current.getNext() != null)
            current = current.getNext();

    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {

        if (first != null)
            return first;
        else
            return null;
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {

        if (last == null)
    	    return null;
        else
            return last;

    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {

        Node<T> temp = new Node<>(null);

        if (current == null)
            return null;
        else{
            temp = current;
            current = current.getNext();
            return temp.getData();
        }

    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {

        Node<T> temp = new Node<>(null);

        if (current == null)
            return null;
        else {
            temp = current;
            current = current.getPrevious();
            return temp.getData();
        }
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {

        current = current.getNext();


        }

    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {

        current = current.getPrevious();

    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();
        T data = current.getData();
        if (data == null)
            throw new CurrentNotSetException();
        else
    	    return data;
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {

        Node<T> tempPointer = new Node<>(null);
        tempPointer = first;

        if (pos > counter)
            return null;
        for (int i = 1; i < pos; i++) {
            tempPointer = tempPointer.getNext();
        }
        return tempPointer.getData();
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) {

        Node<T> tempPointer = first;

        if (pos > counter || pos < 1)
            return;


        for (int i = 1; i < pos; i++) {
            tempPointer = tempPointer.getNext();
        }

        if (tempPointer.getNext() != null && tempPointer.getPrevious() != null){
            tempPointer.getNext().setPrevious(tempPointer.getPrevious());
            tempPointer.getPrevious().setNext(tempPointer.getNext());
        }
        else if (tempPointer.getNext() == null && tempPointer.getPrevious() != null)
            tempPointer.getPrevious().setNext(null);
        else if (tempPointer.getNext() != null && tempPointer.getPrevious() == null){
            first = tempPointer.getNext();
            first.setPrevious(null);
        }
        else
            first = null;

        if (tempPointer == current)
            current = null;
        counter--;


    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();

        if (current.getNext() == null)
            throw new CurrentNotSetException();
        else
            current.getNext().setPrevious(current.getPrevious());

        if (current.getPrevious() != null)
            current.getPrevious().setNext(current.getNext());

        if (current.getNext() != null)
            current = current.getNext();
        else if (current.getPrevious() != null)
            current = current.getPrevious();
        else
            throw new CurrentNotSetException();

    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();
        Node<T> newNode = new Node<>(a);
        current.setNext(newNode);
        newNode.setPrevious(current);
        newNode.setNext(null);
        current = newNode;

    }
}
