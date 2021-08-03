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
package org.pepstock.charba.client.dom.elements;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.Undefined;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Represents an array of 8-bit unsigned integers clamped to 0-255; if you specified a value that is out of the range of [0,255], 0 or 255 will be set instead; if you specify a
 * non-integer, the nearest integer will be set.<br>
 * The contents are initialized to 0.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
final class Uint8ClampedArray {

	// property key of iterator which contains the value
	@JsOverlay
	private static final Key VALUE = Key.create("value");
	// property key of iterator which provides if the iterator has been iterated
	@JsOverlay
	private static final Key DONE = Key.create("done");

	/**
	 * To avoid any instantiation
	 */
	private Uint8ClampedArray() {
		// do nothing
	}

	/**
	 * Returns a new array iterator object that contains the values for each index in the array.
	 * 
	 * @return a new array iterator object that contains the values for each index in the array
	 */
	@JsMethod
	private native JsIterator values();

	/**
	 * Iterator is an object which defines a sequence and potentially a return value upon its termination.<br>
	 * Specifically, an iterator is any object which implements the Iterator protocol by having a next() method that returns an object with two properties:<br>
	 * <ul>
	 * <li><b>value</b>: The next value in the iteration sequence.
	 * <li><b>done</b>: This is true if the last value in the sequence has already been consumed. If value is present alongside done, it is the iterator's return value.
	 * </ul>
	 * <br>
	 * Once created, an iterator object can be iterated explicitly by repeatedly calling next().<br>
	 * Iterating over an iterator is said to consume the iterator, because it is generally only possible to do once. After a terminating value has been yielded additional calls to
	 * next() should continue to return {done: true}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	@JsType(isNative = true, name = "Iterator", namespace = JsPackage.GLOBAL)
	private interface JsIterator {

		/**
		 * Returns an object with two properties done and value.
		 * 
		 * @return an object with two properties done and value
		 */
		NativeObject next();
	}

	/**
	 * Transforms a {@link Uint8ClampedArray} to an {@link ArrayInteger} instance in order to be managed.
	 * 
	 * @return an {@link ArrayInteger} instance in order to be managed.
	 */
	@JsOverlay
	ArrayInteger toArray() {
		// lists of integers to create the result array
		final List<Integer> result = new LinkedList<>();
		// gets iterator
		final JsIterator iterator = values();
		// flag to close the cycle
		boolean done = false;
		// scans iterator
		do {
			// gets object
			NativeObject element = iterator.next();
			// reads done and value property
			done = Id.getBooleanProperty(DONE, element);
			int value = Id.getIntegerProperty(VALUE, element);
			// checks if the properties are consistent to add new element
			// to result array
			if (!done && Undefined.isNot(value)) {
				result.add(value);
			}
		} while (!done);
		// returns the array
		return ArrayInteger.fromOrEmpty(result);
	}

}
