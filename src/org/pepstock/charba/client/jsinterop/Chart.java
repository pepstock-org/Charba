package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.defaults.DefaultOptions;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.items.ChartAreaItem;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.DatasetMetaItem;
import org.pepstock.charba.client.jsinterop.items.LegendNode;
import org.pepstock.charba.client.jsinterop.items.OptionsNode;
import org.pepstock.charba.client.jsinterop.items.ScalesNode;
import org.pepstock.charba.client.jsinterop.items.TitleNode;
import org.pepstock.charba.client.jsinterop.items.TooltipNode;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;
import org.pepstock.charba.client.jsinterop.options.NativeOptions;

import com.google.gwt.canvas.dom.client.Context2d;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Chart", namespace = JsPackage.GLOBAL)
public final class Chart{
	
	@JsProperty
	static native NativeDefaults getDefaults();

	@JsProperty
	static native NativeHelpers getHelpers();
	
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
	native void update(UpdateConfiguration config);

	@JsMethod
	native void render();

	@JsMethod
	native void render(UpdateConfiguration config);

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
	native ArrayObject<DatasetItem> getElementAtEvent(ChartNativeEvent event);

	@JsMethod
	native ArrayObject<DatasetItem> getElementsAtEvent(ChartNativeEvent event);
	
	@JsMethod
	native DatasetMetaItem getDatasetMeta(int index);
	
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
	
	@JsProperty
	public native ChartAreaItem getChartArea();

	@JsProperty
	public native LegendNode getLegend();

	@JsProperty(name ="titleBlock")
	public native TitleNode getTitle();
	
	@JsProperty(name ="options")
	native NativeOptions getNativeOptions();
	
	@JsProperty
	public native ScalesNode getScales();

	@JsProperty
	public native TooltipNode getTooltip();

	@JsOverlay
	public final OptionsNode getOptions() {
		// FIXME default to be fixed
		return new OptionsNode(DefaultOptions.get(), getNativeOptions());
	}
	



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
