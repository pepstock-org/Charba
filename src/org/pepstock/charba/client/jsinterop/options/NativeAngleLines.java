package org.pepstock.charba.client.jsinterop.options;


import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * It is used to configure angled lines that radiate from the center of the chart to the point labels.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
final class NativeAngleLines extends NativeObject {

	/**
	 * If true, angle lines are shown
	 * 
	 * @param display if true, angle lines are shown
	 */
	@JsProperty
	native void setDisplay(boolean display);

	/**
	 * If true, angle lines are shown
	 * 
	 * @return if true, angle lines are shown. 
	 */
	@JsProperty
	native boolean isDisplay();
	
	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	@JsProperty
	native void setColor(String color);

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines. 
	 */
	@JsProperty
	native String getColor();

	/**
	 * Sets the width of angled lines.
	 * 
	 * @param lineWidth width of angled lines.
	 */
	@JsProperty
	native void setLineWidth(int lineWidth);
	
	/**
	 * Returns the width of angled lines.
	 * 
	 * @return width of angled lines.
	 */
	@JsProperty
	native int getLineWidth();

}
