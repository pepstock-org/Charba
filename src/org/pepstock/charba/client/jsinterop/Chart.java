package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.events.ChartNativeEvent;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.DatasetMetaItem;

import com.google.gwt.canvas.dom.client.Context2d;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Chart", namespace = JsPackage.GLOBAL)
public final class Chart {
	@JsProperty
	static native NativeDefaults getDefaults();

	@JsProperty(name = "helpers")
	public static native Helpers helpers();
	
	@JsOverlay
	public static Defaults defaults() {
		return Defaults.get(getDefaults());
	}
	
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

}
