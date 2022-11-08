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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.treemap.callbacks.OverflowCallback;
import org.pepstock.charba.client.treemap.callbacks.PositionCallback;
import org.pepstock.charba.client.treemap.enums.Overflow;
import org.pepstock.charba.client.treemap.enums.Position;

/**
 * The labels can control if and how a label, to represent the data, can be shown in the rectangle.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Labels extends AbstractLabels {

	/**
	 * Default labels display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = false;

	/**
	 * Default labels position, <b>{@link Position#MIDDLE}</b>.
	 */
	public static final Position DEFAULT_POSITION = Position.MIDDLE;

	/**
	 * Default labels overflow, <b>{@link Overflow#CUT}</b>.
	 */
	public static final Overflow DEFAULT_OVERFLOW = Overflow.CUT;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POSITION("position"),
		OVERFLOW("overflow");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyStringCallback> positionCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the overflow function
	private final CallbackProxy<ProxyStringCallback> overflowCallbackProxy = JsHelper.get().newCallbackProxy();

	// position callback instance
	private PositionCallback positionCallback = null;
	// overflow callback instance
	private OverflowCallback overflowCallback = null;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default options of the controller
	 * @param nativeObject native object to map java script properties
	 */
	Labels(AbstractNode parent, Key childKey, IsDefaultOptions defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject, DEFAULT_DISPLAY);
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsString(new DatasetContext(context), getPositionCallback(), DEFAULT_POSITION).value());
		// sets function to proxy callback in order to invoke the java interface
		this.overflowCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsString(new DatasetContext(context), getOverflowCallback(), DEFAULT_OVERFLOW).value());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.treemap.AbstractLabels#isMultilineLabel()
	 */
	@Override
	boolean isMultilineLabel() {
		return true;
	}

	/**
	 * Sets the text vertical alignment used when drawing the label.
	 * 
	 * @param position the text vertical alignment used when drawing the label
	 */
	public void setPosition(Position position) {
		// resets callback
		setPosition((PositionCallback) null);
		// stores the value
		setValueAndAddToParent(Property.POSITION, position);
	}

	/**
	 * Returns the text vertical alignment used when drawing the label.
	 * 
	 * @return the text vertical alignment used when drawing the label
	 */
	public Position getPosition() {
		return getValue(Property.POSITION, Position.values(), DEFAULT_POSITION);
	}

	/**
	 * Sets the control what happens to a label that is too big to fit into a rectangle.
	 * 
	 * @param overflow the control what happens to a label that is too big to fit into a rectangle
	 */
	public void setOverflow(Overflow overflow) {
		// resets callback
		setOverflow((OverflowCallback) null);
		// stores the value
		setValueAndAddToParent(Property.OVERFLOW, overflow);
	}

	/**
	 * Returns the control what happens to a label that is too big to fit into a rectangle.
	 * 
	 * @return the control what happens to a label that is too big to fit into a rectangle
	 */
	public Overflow getOverflow() {
		return getValue(Property.OVERFLOW, Overflow.values(), DEFAULT_OVERFLOW);
	}

	// ---------------------------
	// CALLBACKS METHODS
	// ---------------------------

	/**
	 * Returns the position callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the position callback, if set, otherwise <code>null</code>.
	 */
	public PositionCallback getPositionCallback() {
		return positionCallback;
	}

	/**
	 * Sets the position callback.
	 * 
	 * @param positionCallback the position callback.
	 */
	public void setPosition(PositionCallback positionCallback) {
		// sets the callback
		this.positionCallback = positionCallback;
		// checks if callback is consistent
		if (positionCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(Property.POSITION, positionCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POSITION);
		}
	}

	/**
	 * Sets the position callback.
	 * 
	 * @param positionCallback the position callback.
	 */
	public void setPosition(NativeCallback positionCallback) {
		// resets callback
		setPosition((PositionCallback) null);
		// stores value
		setValueAndAddToParent(Property.POSITION, positionCallback);
	}

	/**
	 * Returns the overflow callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the overflow callback, if set, otherwise <code>null</code>.
	 */
	public OverflowCallback getOverflowCallback() {
		return overflowCallback;
	}

	/**
	 * Sets the overflow callback.
	 * 
	 * @param overflowCallback the overflow callback.
	 */
	public void setOverflow(OverflowCallback overflowCallback) {
		// sets the callback
		this.overflowCallback = overflowCallback;
		// checks if callback is consistent
		if (overflowCallback != null) {
			// adds the callback proxy function to java script object
			setValueAndAddToParent(Property.OVERFLOW, overflowCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.OVERFLOW);
		}
	}

	/**
	 * Sets the overflow callback.
	 * 
	 * @param overflowCallback the overflow callback.
	 */
	public void setOverflow(NativeCallback overflowCallback) {
		// resets callback
		setOverflow((OverflowCallback) null);
		// stores value
		setValueAndAddToParent(Property.OVERFLOW, overflowCallback);
	}
}