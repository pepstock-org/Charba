package org.pepstock.charba.client.commons;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Abstract list implementation which contains a couple of methods to help.<br>
 * It has been created to manage the objects inside of GWT JavaScript object (array).
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <E> type of element to manage in the list
 */
public abstract class AbstractList<E> implements List<E> {

	/**
	 * @return the array based on a JavaScript object. It must be visble only internally.
	 * @see com.google.gwt.core.client.JavaScriptObject
	 */
	abstract JavaScriptObject getJsArray();
	
	/**
	 * Loads a JAVA array of elements into the list.
	 * @param values array of elements.
	 */
	public void addAll(E[] values){
		for (E val : values){
			add(val);
		}
	}
	
}
