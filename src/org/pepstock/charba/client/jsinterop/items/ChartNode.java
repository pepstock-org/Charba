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

import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Main object node which maps CHART.JS java script object in order to retrieve chart information (for instances
 * dimensions of all chart elements) at runtime.  
 *  
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public final class ChartNode extends NativeObject {

	@JsProperty
	public native LegendNode getLegend();
	
//	private final OptionsNode options;
//	
//	private final LegendNode legend;
//	
//	private final ScalesNode scales;
//	
//	private final ChartAreaItem chartArea;
//
//	private final TitleNode title;
//	
//	private final TooltipNode tooltip;
	
	/**
	 * Returns the CHART JS chart ID.
	 * 
	 * @return the CHART JS chart ID. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "id")
	native int getNativeId();

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "width")
	native int getNativeWidth();

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "height")
	native int getNativeHeight();

	/**
	 * Returns the aspect ratio.
	 * 
	 * @return the aspect ratio. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty(name = "aspectRatio")
	native double getNativeAspectRatio();

	/**
	 * Returns the current device pixel ratio.
	 * 
	 * @return the current device pixel ratio. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty(name = "currentDevicePixelRatio")
	native double getNativeCurrentDevicePixelRatio();

	/**
	 * Returns if the chart isNative animating or not.
	 * 
	 * @return if the chart isNative animating or not. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsProperty(name = "animating")
	native boolean isNativeAnimating();

	/**
	 * Returns the border width value.
	 * 
	 * @return the border width value. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "borderWidth")
	native int getNativeBorderWidth();

	/**
	 * Returns the outer radius value.
	 * 
	 * @return the outer radius value. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty(name = "outerRadius")
	native double getNativeOuterRadius();

	/**
	 * Returns the inner radius value.
	 * 
	 * @return the inner radius value. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty(name = "innerRadius")
	native double getNativeInnerRadius();

	/**
	 * Returns the radius length value.
	 * 
	 * @return the radius length value. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty(name = "radiusLength")
	native double getNativeRadiusLength();

	/**
	 * Returns the offset X value.
	 * 
	 * @return the offset X value. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty(name = "offsetX")
	native int getNativeOffsetX();

	/**
	 * Returns the offset Y value.
	 * 
	 * @return the offset Y value. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty	(name = "offsetY")
	native int getNativeOffsetY();


	/**
	 * Returns the CHART JS chart ID.
	 * 
	 * @return the CHART JS chart ID. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getId() {
		return Checker.check(getNativeId(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getWidth() {
		return Checker.check(getNativeWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getHeight() {
		return Checker.check(getNativeHeight(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the aspect ratio.
	 * 
	 * @return the aspect ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public double getAspectRatio() {
		return Checker.check(getNativeAspectRatio(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the current device pixel ratio.
	 * 
	 * @return the current device pixel ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public double getCurrentDevicePixelRatio() {
		return Checker.check(getNativeCurrentDevicePixelRatio(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns if the chart is animating or not.
	 * 
	 * @return if the chart is animating or not. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public boolean isAnimating() {
		return Checker.check(isNativeAnimating(), UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the border width value.
	 * 
	 * @return the border width value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getBorderWidth() {
		return Checker.check(getNativeBorderWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the outer radius value.
	 * 
	 * @return the outer radius value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public double getOuterRadius() {
		return Checker.check(getNativeOuterRadius(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the inner radius value.
	 * 
	 * @return the inner radius value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public double getInnerRadius() {
		return Checker.check(getNativeInnerRadius(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the radius length value.
	 * 
	 * @return the radius length value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public double getRadiusLength() {
		return Checker.check(getNativeRadiusLength(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the offset X value.
	 * 
	 * @return the offset X value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getOffsetX() {
		return Checker.check(getNativeOffsetX(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the offset Y value.
	 * 
	 * @return the offset Y value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getOffsetY() {
		return Checker.check(getNativeOffsetY(), UndefinedValues.INTEGER);
	}

}