/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.gwt.core.client.JsArrayString;

/**
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where in the list each element is inserted. <br>
 * The user can access elements by their integer index (position in the list), and search for elements in the list.<br>
 * This implementation uses a GWT JsArray as backend to store objects (enum values).
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.commons.JsArrayStringImpl
 */
public final class JsEnumValueArrayList<E extends Key> implements List<E> {
	
	// delegated array to store objects
	private final JsArrayStringImpl array;
	// array of all enumeration
	private final E[] definedValues;

	/**
	 * Internal constructor used to load a JSArray already in another object.
	 * @param values all values of an enumeration
	 * @param array JS array instance
	 */
	JsEnumValueArrayList(E[] values, JsArrayStringImpl array) {
		// sets all enumeration values
		this.definedValues = values;
		// if null, creates a new JS array
		if (array == null){
			this.array = (JsArrayStringImpl)JsArrayString.createArray().cast();
		} else {
			// uses an existing array
			this.array = array;
		}
	}

	/**
	 * Creates an empty list
	 * @param vdefinedValues all values of an enumeration
	 */
	JsEnumValueArrayList(Class<E> clazz) {
		this(clazz.getEnumConstants(), null);
	}

	/**
	 * Returns the JS array to be able to store it in another object (internally used).
	 * @return the JS array
	 */
	JsArrayStringImpl getJsArray() {
		return array;
	}

	/**
	 * Loads an array of elements into the list
	 * @param values an array of elements to be loaded
	 */
	public void addAll(E[] values){
		// scans all elements 
		for (E val : values){
			// adds
			add(val);
		}
	}
	
	/**
	 * Returns the number of elements in this list.
	 */
	@Override
	public int size() {
		// returns the size of JS array
		return array.length();
	}

	/**
	 * Returns true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		// checks if the size of JS array is ZERO
		return size() == 0;
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	@Override
	public boolean contains(Object o) {
		// checks if index of the object in JS array is not equals to -1
		return indexOf(o.toString()) != -1;
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		return new IteratorImpl<E>(this);
	}

	/**
	 * Not implemented
	 */
	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("Unable to copy into an array");
	}

	/**
	 * Not implemented
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException("Unable to copy into an array");
	}

	/**
	 * Appends the specified element to the end of this list
	 */
	@Override
	public boolean add(E e) {
		array.push(e.name());
		return true;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it is present. If this list does not contain the element, it is unchanged.
	 */
	@Override
	public boolean remove(Object o) {
		// gets index of object
		int index = indexOf(o);
		// if is in the right range
		if (checkRange(index)){
			// removes by index
			remove(index);
			return true;
		}
		return false;
	}

	/**
	 * Returns true if this list contains all of the elements of the specified collection.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> e = c.iterator();
		// scans all elements
		while (e.hasNext()){
			// if does not contain return false
			if (!contains(e.next())){
				return false;
			}
		}
		// if here, all elements are in the list
		return true;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		Iterator<? extends E> e = c.iterator();
		// scans all elements
		while (e.hasNext()) {
			// if adds
			if (add(e.next())){
				// sets modified 
				modified = modified && true;
			} else {
				// sets false!
				modified = false;
			}
		}
		return modified;
	}
	
	/**
	 * Inserts all of the elements in the specified collection into this list at the specified position.<br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices). 
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		// checks if continue
		if (modified && checkRange(index)){
			// saves index
			int myIndex = index;
			Iterator<? extends E> e = c.iterator();
			// scans all elements
			while (e.hasNext()) {
				// adds to the stored new index
				add(myIndex, e.next());
				// increments new index
				myIndex++;
			}
			// sets modified
			modified = true;
		}
		return modified;
	}

	/**
	 * Removes from this list all of its elements that are contained in the specified collection.
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		Iterator<?> e = c.iterator();
		// scans all elements
		while (e.hasNext()) {
			// removes and checks if modified
			modified = modified && remove(e.next());
		}
		return modified;
	}

	/**
	 * Retains only the elements in this list that are contained in the specified collection.<br>
	 * In other words, removes from this list all of its elements that are not contained in the specified collection.
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		// set modified checking if collection is empty
		boolean modified = !c.isEmpty();
		if (modified){
			// creates a copy of elements
			List<E> contained = new ArrayList<E>();
			// scans all current elements
			for (int i=0; i<size(); i++){
				E value = get(i);
				// checks if not present into
				// passed collection
				if (!c.contains(get(i))){
					// adds to temporary list
					contained.add(value);
				}
			}
			// if temporary list is not empty
			if (!contained.isEmpty()){
				// scans all elements
				for (E toRemove : contained){
					// removes and checks if modified
					modified = modified && remove(toRemove);
				}
			}
		}
		return modified;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		array.clear();
	}

	/**
	 * Returns the element at the specified position in this list. If index out of range, returns null
	 */
	@Override
	public E get(int index) {
		// checks range
		if (checkRange(index)){
			String value = array.get(index);
			return getByName(value);
		}
		return null;
	}

	/**
	 * Replaces the element at the specified position in this list with the specified element. If index out of range, returns null
	 */
	@Override
	public E set(int index, E element) {
		// checks range
		if (checkRange(index)){
			// gets current element at that index
			String old = array.get(index);
			E oldValue = getByName(old);
			// replaces with new element
			array.set(index, element.name());
			// returns old
			return oldValue;
		}		
		return null;
	}

	/**
	 * Inserts the specified element at the specified position in this list.<br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 */
	@Override
	public void add(int index, E element) {
		array.insertAt(index, element.name());
	}

	/**
	 * Removes the element at the specified position in this list.<br>
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
	 */
	@Override
	public E remove(int index) {
		// checks range
		if (checkRange(index)){
			String value = array.remove(index);
			return getByName(value);
		}
		return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(Object o) {
		// checks if EnumValue
		if (o instanceof Key){
			// cast
			Key val = (Key) o;
			// search
			return array.indexOf(val.name());
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element. 
	 */
	@Override
	public int lastIndexOf(Object o) {
		// checks if EnumValue
		if (o instanceof Key){
			// cast
			Key val = (Key) o;
			// search
			return array.lastIndexOf(val.name());
		}
		return -1;
	}

	/**
	 * Returns a list iterator over the elements in this list
	 */
	@Override
	public ListIterator<E> listIterator() {
		return new ListIteratorImpl<E>(0, this);
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list.<br>
	 * The specified index indicates the first element that would be returned by an initial call to next.<br>
	 * An initial call to previous would return the element with the specified index minus one.
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		// if index is out of range, EXCEPTION
		if (!checkRange(index)){
            throw new IndexOutOfBoundsException("Index: "+index);
		}
		return new ListIteratorImpl<E>(index, this);
	}

	/**
	 * Not implemented
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Unable to copy into an array");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EnumvalueArrayList [array=" + array.toJSON() + "]";
	}
	
	/**
	 * Checks if the index is in the right range.
	 * @param index index to be checked
	 * @return <code>true</code> if the index is in the right range otherwise false
	 */
	private boolean checkRange(int index){
		return index >= 0 && index < array.length();
	}

	/**
	 * Gets EnumValue by its name
	 * @param name name to search
	 * @return EnumValue instance or null if not found
	 */
	private E getByName(String name){
		// scans all EnumValues
		for (E value : definedValues){
			// if equals returns it
			if (value.name().equalsIgnoreCase(name)){
				return value;
			}
		}
		return null;
	}
	

}