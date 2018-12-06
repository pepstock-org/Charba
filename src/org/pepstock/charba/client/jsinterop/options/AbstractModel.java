package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

public abstract class AbstractModel<P extends AbstractModel<?,?>, D> extends NativeObjectContainer {
	
	private final D defaultValues;
	
	private final P parent;
	
	private final Key childKey;
	
	/**
	 * @param nativeObject
	 * @param defaultValues
	 */
	protected AbstractModel(D defaultValues) {
		this(null, null, defaultValues);
	}
	
	/**
	 * @param nativeObject
	 * @param defaultValues
	 */
	protected AbstractModel(P parent, Key childKey, D defaultValues) {
		this(parent, childKey, defaultValues, new NativeObject());
	}
	
	/**
	 * @param nativeObject
	 * @param defaultValues
	 */
	protected AbstractModel(D defaultValues, NativeObject nativeObject) {
		this(null, null, defaultValues, nativeObject);
	}
	
	/**
	 * @param nativeObject
	 * @param defaultValues
	 */
	protected AbstractModel(P parent, Key childKey, D defaultValues, NativeObject nativeObject) {
		super(nativeObject);
		this.childKey = childKey;
		this.parent = parent;
		this.defaultValues = defaultValues;
	}
	
	/**
	 * @return the childKey
	 */
	protected final Key getChildKey() {
		return childKey;
	}

	/**
	 * @return the parent
	 */
	protected final P getParent() {
		return parent;
	}

	protected final void remove(Key key) {
		JsHelper.get().remove(getNativeObject(), key.name());
	}
	
	protected final D getDefaultValues() {
		return defaultValues;
	}
	
	protected void setEventToModel(AbstractModel<?,?> model, Key key, CallbackProxy.Proxy proxy) {
		model.setValue(key, proxy);
		model.checkAndAddToParent();
	}
	
	protected void setCallbackToModel(AbstractModel<?,?> model, Key key, CallbackProxy.Proxy proxy) {
		model.setValue(key, proxy);
		model.checkAndAddToParent();
	}
	
	/**
	 * Called recursively when a property has been set in the item.<br>
	 * This is mandatory because it could happen that the parent item is not present, therefore it must be added.
	 */
	protected void checkAndAddToParent() {
		// checks if we are at root element
		// or if the parent hasn't go thte key
		if (parent != null && !parent.has(childKey)) {
			// sets the java script of this element into parent
			parent.setValue(childKey, getNativeObject());
			// recursively call to parent of parent
			parent.checkAndAddToParent();
		}
	}

}
