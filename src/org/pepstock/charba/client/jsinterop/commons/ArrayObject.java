package org.pepstock.charba.client.jsinterop.commons;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Array", namespace = JsPackage.GLOBAL)
public final class ArrayObject<T extends NativeObject> {
	
	public static native <T extends NativeObject> ArrayObject<T> of(T[] objects);

	public static native <T extends NativeObject> boolean isArray(T object);
	
	/**
	 * FIXME
	 * Creates a JavaScript array list of strings.
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance or <code>null</code> if the elements are null.
	 * @see org.pepstock.charba.client.commons.JsStringArrayList
	 */
	@JsOverlay
	public static <E extends NativeObjectContainer<O>, O extends NativeObject> ArrayObject<O> of(E[] values){
		// creates the list
		ArrayObject<O> result = new ArrayObject<>();
		// checks if array is null
		if (values == null){
			return result;
		}
		for (E value : values) {
			// adds all elements
			result.push(value.getNativeObject());
		}
		// returns the list
		return result;
	}

	@JsProperty(name = "length")
	public native int length();

	/**
	 * Returns the index of the last occurrence of the specified element in this object, or -1 if this object does not contain
	 * the element.
	 * 
	 * @param value element to search for
	 * @return the index of the last occurrence of the specified element in this object, or -1 if this object does not contain
	 *         the element
	 */
	native int lastIndexOf(Object value);

	/**
	 * Returns the index of the first occurrence of the specified element in this object, or -1 if this object does not contain
	 * the element.
	 * 
	 * @param value element to search for
	 * @return the index of the first occurrence of the specified element in this object, or -1 if this object does not contain
	 *         the element
	 */
	native int indexOf(Object value);

	native ArrayObject<T> slice(int start, int end);
	
	native ArrayObject<T> splice(int start);

	native ArrayObject<T> splice(int start, int deleteCounts);

	native ArrayObject<T> splice(int start, int deleteCounts, T item);

	/**
	 * Removes all of the elements from this object. The object will be empty after this call returns.
	 */
	@JsOverlay
	void clear() {
		splice(0, length());
	};

	/**
	 * Removes the element at the specified position in this object. Shifts any subsequent elements to the left (subtracts one
	 * from their indices). Returns the element that was removed from the object.
	 * 
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 */
	@JsOverlay
	T remove(int index) {
		return splice(index, 1).get(0);
	}

	/**
	 * Inserts the specified element at the specified position in this object. Shifts the element currently at that position (if
	 * any) and any subsequent elements to the right (adds one to their indices).
	 * 
	 * @param index index at which the specified element is to be inserted
	 * @param value element to be inserted
	 */
	@JsOverlay
	void insertAt(int index, T item) {
		splice(index, 0, item);
	};

	/**
	 * Gets the value at a given index.
	 * 
	 * If no value exists at the given index, a type-conversion error will occur in Development Mode and unpredictable behavior
	 * may occur in Production Mode. If the numeric value returned is non-integral, it will cause a warning in Development Mode,
	 * and may affect the results of mathematical expressions.
	 *
	 * @param index the index to be retrieved
	 * @return the value at the given index
	 */
	@JsOverlay
	public T get(int index) {
		return slice(index, index+1).pop();
	}

	/**
	 * Pushes the given integer onto the end of the array.
	 */
	native void fill(T item, int start, int end);

	/**
	 * Pushes the given integer onto the end of the array.
	 */
	native void push(T item);
	
	/**
	 * Pushes the given integer onto the end of the array.
	 */
	native T pop();

	/**
	 * Sets the value value at a given index.
	 * 
	 * If the index is out of bounds, the value will still be set. The array's length will be updated to encompass the bounds
	 * implied by the added value.
	 * 
	 * @param index the index to be set
	 * @param value the value to be stored
	 */
	@JsOverlay
	void set(int index, T item){
		fill(item, index, index+1);
	}

}

