package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.JsFactory;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.utils.JSON;

public abstract class BaseModel<O extends NativeObject, P extends BaseModel<?,?,?>, D> {
	
	private final O delegated;
	
	private final D defaultValues;
	
	private final P parent;
	
	/**
	 * @param delegated
	 * @param defaultValues
	 */
	protected BaseModel(O delegated, P parent, D defaultValues) {
		this.delegated = delegated;
		this.defaultValues = defaultValues;
		this.parent = parent;
	}
	
	/**
	 * @return the parent
	 */
	protected final P getParent() {
		return parent;
	}

	protected final void remove(Key key) {
		JsFactory.remove(getDelegated(), key.name());
	}

	protected final O getDelegated() {
		return delegated;
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
	
	public final String toJSON() {
		return JSON.stringify(delegated, 3);
	}
}
