package org.pepstock.charba.client.jsinterop.options.elements.arc;

import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Arcs are used in the polar area, doughnut and pie charts.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the
 * same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a
 * dataset.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public class NativeArc extends NativeObject {
	
	/**
	 * 
	 */
	protected NativeArc() {
	}

	/**
	 * Sets the background color.
	 * 
	 * @param backgroundColor the background color.
	 */
	@JsProperty
	public native void setBackgroundColor(String backgroundColor);

	/**
	 * Returns the background color.
	 * 
	 * @return the background color. 
	 */
	@JsProperty
	public native String getBackgroundColor();

	/**
	 * Sets the border width.
	 * 
	 * @param borderWidth the border width.
	 */
	@JsProperty
	public native void setBorderWidth(int borderWidth);

	/**
	 * Returns the border width.
	 * 
	 * @return the border width. 
	 */
	@JsProperty
	public native int getBorderWidth();

	/**
	 * Sets the border color.
	 * 
	 * @param borderColor the border color.
	 */
	@JsProperty
	public native void setBorderColor(String borderColor);

	/**
	 * Returns the border color.
	 * 
	 * @return the border color. 
	 */
	@JsProperty
	public native String getBorderColor();
}
