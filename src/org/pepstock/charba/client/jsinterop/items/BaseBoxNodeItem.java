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
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Base object which maps the CHART.JS chart items and represents main nodes of chart java script object.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public abstract class BaseBoxNodeItem extends BaseBoxItem {

	/**
	 * Returns the margin item.
	 * 
	 * @return the margin item.
	 */
	@JsProperty
	public native MarginsItem getMargins();
	
	/**
	 * Returns the min size item.
	 * 
	 * @return the min size item.
	 */
	@JsProperty
	public native SizeItem getMinSize();
	
	/**
	 * Returns the full width in pixel.
	 * 
	 * @return the full width in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsProperty(name = "fullWidth")
	native boolean isNativeFullWidth();

	/**
	 * Returns the position of node.
	 * 
	 * @return the position of node.
	 */
	@JsProperty(name = "position")
	native String getNativePosition();

	/**
	 * Returns the weight.
	 * 
	 * @return the weight. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty(name = "weight")
	native double getNativeWeight();

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "width")
	native int getNativeWidth();

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "height")
	native int getNativeHeight();

	/**
	 * Returns the max width in pixel.
	 * 
	 * @return the max width in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty(name = "maxWidth")
	native double getNativeMaxWidth();

	/**
	 * Returns the max height in pixel.
	 * 
	 * @return the max height in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty(name = "maxHeight")
	native double getNativeMaxHeight();

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "paddingTop")
	native int getNativePaddingTop();

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "paddingRight")
	native int getNativePaddingRight();

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "paddingBottom")
	native int getNativePaddingBottom();

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "paddingLeft")
	native int getNativePaddingLeft();

	/**
	 * Returns the full width in pixel.
	 * 
	 * @return the full width in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public final boolean isFullWidth() {
		return AssignHelper.check(isNativeFullWidth(), UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the position of node.
	 * 
	 * @return the position of node.
	 */
	@JsOverlay
	public final Position getPosition() {
		return AssignHelper.deserialize(AssignHelper.check(getNativePosition(), Position.top.name()), Position.class, Position.top);
	}

	/**
	 * Returns the weight.
	 * 
	 * @return the weight. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getWeight() {
		return AssignHelper.check(getNativeWeight(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getWidth() {
		return AssignHelper.check(getNativeWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getHeight() {
		return AssignHelper.check(getNativeHeight(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the max width in pixel.
	 * 
	 * @return the max width in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getMaxWidth() {
		return AssignHelper.check(getNativeMaxWidth(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the max height in pixel.
	 * 
	 * @return the max height in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getMaxHeight() {
		return AssignHelper.check(getNativeMaxHeight(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getPaddingTop() {
		return AssignHelper.check(getNativePaddingTop(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getPaddingRight() {
		return AssignHelper.check(getNativePaddingRight(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getPaddingBottom() {
		return AssignHelper.check(getNativePaddingBottom(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getPaddingLeft() {
		return AssignHelper.check(getNativePaddingLeft(), UndefinedValues.INTEGER);
	}

}