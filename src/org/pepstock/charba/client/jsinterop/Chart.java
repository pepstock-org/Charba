package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.Id;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.plugins.NativePlugins;

import com.google.gwt.canvas.dom.client.Context2d;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = NativeName.CHART, namespace = JsPackage.GLOBAL)
public final class Chart{
	
	@JsProperty
	static native NativeDefaults getDefaults();

	@JsProperty
	static native NativeHelpers getHelpers();
	
	@JsProperty
	static native NativePlugins getPlugins();
	
	/**
	 * 
	 * @param context
	 * @param config
	 */
	protected Chart(Context2d context, Configuration config) {}
	
	@JsMethod
	native void resize();

	@JsMethod
	native void update();
	
	@JsMethod
	native void update(NativeObject config);

	@JsMethod
	native void render();

	@JsMethod
	native void render(NativeObject config);

	@JsMethod
	native void destroy();

	@JsMethod
	native void stop();
	
	@JsMethod
	native void clear();

	@JsMethod
	native void reset();
	
	@JsMethod
	native String toBase64Image();
	
	@JsMethod
	native String generateLegend();
	
	@JsMethod
	native ArrayObject getElementAtEvent(ChartNativeEvent event);

	@JsMethod
	native ArrayObject getElementsAtEvent(ChartNativeEvent event);
	
	@JsMethod
	native NativeObject getDatasetMeta(int index);
	
	/**
	 * Returns the CHART JS chart ID.
	 * 
	 * @return the CHART JS chart ID. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty
	native int getId();

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty
	native int getWidth();

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel. Default isNative {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty
	native int getHeight();

	/**
	 * Returns the aspect ratio.
	 * 
	 * @return the aspect ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty
	native double getAspectRatio();

	/**
	 * Returns the current device pixel ratio.
	 * 
	 * @return the current device pixel ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty
	native double getCurrentDevicePixelRatio();

	/**
	 * Returns if the chart is animating or not.
	 * 
	 * @return if the chart is animating or not. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsProperty
	native boolean isAnimating();

	/**
	 * Returns the border width value.
	 * 
	 * @return the border width value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty
	native int getBorderWidth();

	/**
	 * Returns the outer radius value.
	 * 
	 * @return the outer radius value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty
	native double getOuterRadius();

	/**
	 * Returns the inner radius value.
	 * 
	 * @return the inner radius value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty
	native double getInnerRadius();

	/**
	 * Returns the radius length value.
	 * 
	 * @return the radius length value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsProperty
	native double getRadiusLength();

	/**
	 * Returns the offset X value.
	 * 
	 * @return the offset X value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty
	native int getOffsetX();

	/**
	 * Returns the offset Y value.
	 * 
	 * @return the offset Y value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsProperty
	native int getOffsetY();
	
	@JsProperty
	native NativeObject getChartArea();

	@JsProperty
	native NativeObject getLegend();

	@JsProperty
	native NativeObject getTitleBlock();
	
	@JsProperty
	native NativeObject getOptions();
	
	@JsProperty
	native NativeObject getScales();

	@JsProperty
	native NativeObject getTooltip();
	
	@JsOverlay
	public String getCharbaId() {
		return Id.get(getOptions());
	}

}
