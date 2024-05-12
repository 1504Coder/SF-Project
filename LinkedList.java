package project3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * This class represents a singly Linked List that implements the Collection<E> and Iterable<E> interface.
 * It takes in values of type E and provides methods that should be implemented by these interfaces, and methods that are not in the interfaces.
 *
 *
 * @param <E> – the type of elements held in this collection.
 * @author Elijah Philip
 */


public class LinkedList<E> implements Collection<E>, Iterable<E> {
    private Node<E> head;

    /***
     * This nested class represents a node in the linked list.
     * Each contains a value of type E that can be stored and a reference to a new node or null.
     *
     * @param <E> – the type of elements held in this Node.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public LinkedList() {
        this.head = null;
    }

    /**
     * This method counts the number of nodes in the linked list.
     *
     * @return number of nodes in this collection.
     */

    @Override
    public int size() {
        int counter = 0;
        Node<E> cNode = head;
        while (cNode != null) {
            cNode = cNode.next;
            counter++;
        }
        return counter;
    }

    /***
     * This method checks whether this collection has zero nodes by checking the size.
     *
     * @return true if the size=0, false if size>0.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /***
     * This method checks whether the given object can be found within the linked list by iterating through the collection
     * and using the equals' method.
     *
     * @param o element whose presence in this collection is to be tested
     * @return true if the given object is found within this collection. False if not found.
     */
    @Override
    public boolean contains(Object o) {
        Node<E> cNode = head;
        while (cNode != null) {
            if (cNode.data.equals(o)) return true;
            cNode = cNode.next;
        }
        return false;
    }


    /***
     * This method converts the linked-list to another type of Collection.
     * Specifically to an array of Objects by adding the data from each node
     *
     * @return an array consisting of elements with type Object that were added from this collection.
     */
    @Override
    public Object[] toArray() {
        ArrayList<Object> n = new ArrayList<>();
        Node<E> cNode = head;
        while (cNode.next != head) {
            n.add(cNode);
            cNode = cNode.next;
        }
        return n.toArray();
    }


    /***
     * This method will add a new node to the end of the LinkedList by iterating to the last node. If there are no nodes. Creates a new node.
     *
     * @param e element that will be appended to this collection.
     * @return true if the value has been added.
     */
    @Override
    public boolean add(E e) {
        if (isEmpty()) {
            head = new Node<>(e, null);
        } else {
            Node<E> cNode = head;
            while (cNode.next != null) {
                cNode = cNode.next;
            }
            cNode.next = new Node<>(e, null);
        }
        return true;
    }

    /***
     * This method will remove the given Object in the LinkedList if a matching node is found by iterating through each node.
     *
     * @param o element to be removed from this collection, if present.
     * @return true, if value is found and removed. False if no such value exists in this collection.
     */
    @Override
    public boolean remove(Object o) {
        Node<E> nNode = head;
        Node<E> pNode = null;
        while (nNode != null) {
            if (nNode.data.equals(o)) {
                //If first node, just set head equal to next node.
                if (pNode == null) {
                    nNode = nNode.next;
                    head = nNode;
                    return true;
                }
                //If last node just make last node null.
                if (nNode.next == null) {
                    pNode.next = null;
                    return true;
                }
                //If node is in between just point the previous pointer to the next pointer.
                pNode.next = nNode.next;
                return true;
            }
            pNode = nNode;
            nNode = nNode.next;
            // pNode = cNode;

        }
        return false;
    }

    /***
     * removes all values in this collection
     */
    @Override
    public void clear() {
        head = null;
    }

    /***
     * This method checks if every element in this collection contains every non-duplicate element in the other collection.
     *
     * @param c collection to be checked for containment in this collection
     * @return true if every non-duplicate element in Collection c belongs in this object's collection. False if element isn't found.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> cIter = c.iterator();
        while (cIter.hasNext()) {
            if (!this.contains(cIter.next())) return false;
        }
        return true;
    }

    /***
     * This method
     *
     * @param a the array into which the elements of this collection are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return the array T[] a.
     * @param <T> the component type of the array to contain the collection
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        int i = 0;
        while (head != null) {
            a[i] = (T) head.data;
            i++;
            head = head.next;
        }
        return a;
    }

    /***
     * This method checks if this linked-list and the given object are equal by checking if the given object is a linked list.
     * It also checks if the two collections have the same size. Then it checks if the elements in each collection are equal.
     *
     * @param o object to be compared for equality with this collection
     * @return true if o is an instance of this collection, has the same size, and has the same elements in order. False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedList<?> ll) {
            if (ll.size() == size()) {
                for (int i = 0; i < size(); i++) {
                    if (ll.get(i) != get(i)) return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }


    /***
     * This method is not being used.
     * @throws UnsupportedOperationException if the user tries to use this method.
     * @param c collection containing elements to be added to this collection.
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    /***
     * This method is not being used.
     * @throws UnsupportedOperationException if the user tries to use this method.
     * @param c collection containing elements to be added to this collection.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /***
     * This method is not being used.
     * @throws UnsupportedOperationException if the user tries to use this method.
     * @param c collection containing elements to be added to this collection.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }


    /**
     * This method finds where the given Object is numerically within this collection.
     * If no such object is found, then -1 is returned.
     *
     * @param o element to search for.
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     */
    int indexOf(Object o) {
        int index = 0;
        Node<E> cNode = head;
        if (size() != 0) {
            while (cNode != null) {
                if (cNode.data.equals(o)) return index;
                index++;
                cNode = cNode.next;
            }
        }
        return -1;
    }

    /***
     * This method gets the Node at which the index can be found.
     *
     * @param index numerical location of this collection.
     * @throws IndexOutOfBoundsException() if index is less than 0 and greater than the size of this collection minus 1.
     * @return the value of the element at a valid index
     */
    E get(int index) {
        int counter = 0;
        String error = String.format("%s%d", "The given index should be between 0 and ", size() - 1);
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException(error);
        Node<E> cNode = head;
        while (cNode.next != null && counter != index) {
            counter++;
            cNode = cNode.next;
        }
        return cNode.data;
    }

    /***
     * This method returns a String representation of this LinkedList object.
     *
     * @return a string format of this collection.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node<E> cNode = head;
        while (cNode != null) {
            s.append(cNode.data).append(",");
            cNode = cNode.next;
        }
        s.delete(s.length() - 1, s.length());
        return String.format("%s%s%s", "[", s, "]");
    }

    /**
     * This method sorts this collection.
     */
    public void sort() {
        Object[] array = toArray();
        Arrays.sort(array);
        this.clear();
        for (Object o : array) {
            this.add((E)o);
        }


    }

    /***
     * This class implements the Iterable<E> class and uses the implemented methods so that the iterator() method can be used.
     * @param <E> - – the type of elements held in the Iterator object.
     */
    private class Iter implements Iterator<E> {

        private Node<E> cNode = head;

        /**
         * This method checks if there are any remaining elements to be iterated over.
         * @return true if cNode is not null, false if it is.
         */
        @Override
        public boolean hasNext() {
            return cNode != null;
        }

        @Override
        public E next() {
            E element = cNode.data;
            if (this.hasNext()) cNode = cNode.next;
            return element;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }

    }

    /**
     * This method uses the Iter class to make the function work.
     * @return a new Iterator<E> object.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }
}
