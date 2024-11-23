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
            //current = first;
            last = first;

        }
        else {
            last.setNext(newNode);
            newNode.setPrevious(last);
            newNode.setNext(null);
            last = newNode;
            //current = last;
        }
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

        current = last;
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

        Node<T> temp;

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

        Node<T> temp;

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

        if (current == null) {
        }
        else if (current.getNext() == null)
            current = null;
        else
            current = current.getNext();

        }

    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {

        if (current == null) {
        }
        else if (current.getPrevious() == null)
            current = null;
        else
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
        return current.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {

        Node<T> tempPointer;
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
        else if (tempPointer.getNext() == null && tempPointer.getPrevious() != null){
            last = tempPointer.getPrevious();
            last.setNext(null);
        }

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

        if (current == first && current.getNext() == null){
           first = null;
           current = null;
        }
        else if (current == first) {
            first = current.getNext();
            first.setPrevious(null);
            current = first;
        }
        else if (current.getNext() == null ){
            current = current.getPrevious();
            current.setNext(null);
            last = current;
        }
        else {
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
            current = current.getNext();
        }
        counter--;
    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {

        Node<T> newNode = new Node<>(a);
        if (current == null)
            throw new CurrentNotSetException();

        if (current.getNext() == null){
            current.setNext(newNode);
            newNode.setPrevious(current);
            last = newNode;
        }
        else {
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
        }
        current = newNode;
        counter++;
    }
}
