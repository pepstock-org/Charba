package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

public abstract class Root<P extends BaseModel<?,?,?>, D,  O extends NativeObject> extends BaseModel<P, D, O> {
	// FIXME serve?
	/**
	 * @param delegated
	 * @param defaultValues
	 */
	protected Root(D defaultValues, O delegated, boolean eventEnabled) {
		this(null, defaultValues, delegated, eventEnabled);
	}
	
	/**
	 * @param delegated
	 * @param defaultValues
	 */
	protected Root(P parent, D defaultValues, O delegated) {
		this(parent, defaultValues, delegated, false);
	}

	/**
	 * @param delegated
	 * @param defaultValues
	 */
	protected Root(P parent, D defaultValues, O delegated, boolean eventEnabled) {
		super(parent, defaultValues, delegated);
	}
	
	/**
	 * @return the parent
	 */
	public final O getNode() {
		return getNativeObject();
	}
}
