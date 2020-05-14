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

/**
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where in the list each element is inserted. <br>
 * The user can access elements by their integer index (position in the list), and search for elements in the list.<br>
 * This implementation uses a java script array as back-end to store objects (native object containers).<br>
 * Elements are instances of {@link NativeObjectContainer}.<br>
 * <br>
 * Some methods are annotated with <code>\u0040SuppressWarnings(&quot;unusable-by-js&quot;)</code> because J2CL transpiler emits warnings as not usable into javascript part but
 * this collection must not be passed to any java scriptcode.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <E> extension of {@link NativeObjectContainer}
 * 
 */
public final class ArrayObjectContainerList<E extends NativeObjectContainer> extends AbstractArrayNativeContainerList<E, ArrayObject> {

	// delegated array to store objects
	private final ArrayObject array;

	/**
	 * Internal constructor used to set an array instance as back-end of the list.
	 * 
	 * @param array java script array instance. If <code>null</code>, new empty array has been created
	 * @param factory factory instance to create the object from a native one.
	 */
	ArrayObjectContainerList(ArrayObject array, NativeObjectContainerFactory<E> factory) {
		// if null, creates a new array
		if (array == null) {
			this.array = new ArrayObject();
		} else if (factory == null) {
			// factory is not consistent and array is consistent EXCEPTION
			// factory is mandatory to initialize the list creating the elements from native object
			throw new IllegalArgumentException("Unable to create NativeObjectContainer without a factory which is null");
		} else {
			// uses an existing array
			this.array = array;
			// scans the array
			for (int i = 0; i < array.length(); i++) {
				// uses the factory to creates all elements
				getDelegate().add(factory.create(array.get(i)));
			}
		}
	}

	/**
	 * Creates an empty list
	 */
	public ArrayObjectContainerList() {
		this(null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.AbstractArrayList#getArray()
	 */
	@Override
	ArrayObject getArray() {
		return array;
	}

	/**
	 * Appends the specified element to the end of this list
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public boolean add(E element) {
		// invokes and get if element has been added
		// if added
		if (super.add(element)) {
			// adds to JS array
			array.push(element.getNativeObject());
			return true;
		}
		// if here, element is not consistent
		// and not added
		return false;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		super.clear();
		array.clear();
	}

	/**
	 * Replaces the element at the specified position in this list with the specified element. If index out of range, returns null
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public E set(int index, E element) {
		// invokes and get if element has been set
		E old = super.set(index, element);
		// checks element has been set
		if (old != null) {
			array.set(index, element.getNativeObject());
			// returns old value
			return old;
		}
		return null;
	}

	/**
	 * Inserts the specified element at the specified position in this list.<br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public void add(int index, E element) {
		// checks if element is consistent
		if (element != null) {
			// invokes to add
			super.add(index, element);
			// adds element
			array.insertAt(index, element.getNativeObject());
		}
	}

	/**
	 * Removes the element at the specified position in this list.<br>
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
	 */
	@SuppressWarnings("unusable-by-js")
	@Override
	public E remove(int index) {
		// invokes and get if element has been set
		E old = super.remove(index);
		// checks element has been set
		if (old != null) {
			// removes from JS array
			array.remove(index);
			// returns old value
			return old;
		}
		return null;
	}

}