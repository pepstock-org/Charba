package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.JsFactory;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

public abstract class BaseModel<P extends BaseModel<?,?,?>, D,  O extends NativeObject> extends NativeObjectContainer<O> {
	
	private final D defaultValues;
	
	private final P parent;
	
	/**
	 * @param delegated
	 * @param defaultValues
	 */
	protected BaseModel(D defaultValues, O delegated) {
		this(null, defaultValues, delegated);
	}
	
	/**
	 * @param delegated
	 * @param defaultValues
	 */
	protected BaseModel(P parent, D defaultValues, O delegated) {
		super(delegated);
		this.parent = parent;
		this.defaultValues = defaultValues;
	}
	
	/**
	 * @return the parent
	 */
	protected final P getParent() {
		return parent;
	}

	protected final void remove(Key key) {
		JsFactory.remove(getNativeObject(), key.name());
	}

	protected final O getDelegated() {
		return getNativeObject();
	}
	
	protected final D getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Called recursively when a property has been set in the item.<br>
	 * This is mandatory because it could happen that the parent item is not present, therefore it must be added.
	 */
	protected void checkAndAddToParent() {
		// checks if we are at root element
		// or if the parent hasn't go thte key
		if (parent != null) {
			addToParent();
			// recursively call to parent of parent
			parent.checkAndAddToParent();
		}
	}
	
	/**
	 * Called recursively when a property has been set in the item.<br>
	 * This is mandatory because it could happen that the parent item is not present, therefore it must be added.
	 */
	protected abstract void addToParent();
}
