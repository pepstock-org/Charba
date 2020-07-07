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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.plugins.NativePlugins;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Native object which import the CHART java script object of CHART.JS.<br>
 * The <code>Chart</code> the entry point of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.CHART, namespace = JsPackage.GLOBAL)
public final class Chart {

	/**
	 * Returns the <code>defaults</code> property by native object.
	 * 
	 * @return the <code>defaults</code> property by native object.
	 */
	@JsProperty
	static native NativeObject getDefaults();

	/**
	 * Returns the <code>helpers</code> property by native object.
	 * 
	 * @return the <code>helpers</code> property by native object.
	 */
	@JsProperty
	static native NativeHelpers getHelpers();

	/**
	 * Returns the <code>plugins</code> property by native object.
	 * 
	 * @return the <code>plugins</code> property by native object.
	 */
	@JsProperty
	static native NativePlugins getPlugins();

	/**
	 * Builds CHART object at CHART.JS level.<br>
	 * This constructor MUST be empty.
	 * 
	 * @param context represents a flat cartesian surface whose origin (0,0) is at the top left corner, with the coordinate space having x values increasing when going right, and y
	 *            values increasing when going down.
	 * @param configuration configuration of CHART (native object).
	 */
	protected Chart(Context2dItem context, NativeObject configuration) {
	}

	/**
	 * Use this to manually resize the canvas element. This is run each time the canvas container is resized, but can be called this method manually if you change the size of the
	 * canvas nodes container element.
	 */
	@JsMethod
	native void resize();

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update all scales, legends, and then re-render the chart.
	 */
	@JsMethod
	native void update();

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update all scales, legends, and then re-render the chart. A config
	 * object can be provided with additional configuration for the update process. This is useful when update is manually called inside an event handler and some different
	 * animation is desired.
	 * 
	 * @param config a config object can be provided with additional configuration for the update process
	 */
	@JsMethod
	native void update(NativeObject config);

	/**
	 * Triggers a redraw of all chart elements. Note, this does not update elements for new data. Use <code>.update()</code> in that case.
	 */
	@JsMethod
	native void render();

	/**
	 * Triggers a redraw of all chart elements. Note, this does not update elements for new data. Use <code>.update()</code> in that case. A config object can be provided with
	 * additional configuration for the render process. This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param config a config object can be provided with additional configuration for the render process
	 */
	@JsMethod
	native void render(NativeObject config);

	/**
	 * Use this to destroy any chart instances that are created. This will clean up any references stored to the chart object within Chart.js, along with any associated event
	 * listeners attached by Chart.js.
	 */
	@JsMethod
	native void destroy();

	/**
	 * Use this to stop any current animation loop. This will pause the chart during any current animation frame. Call <code>.render()</code> to re-animate.
	 */
	@JsMethod
	native void stop();

	/**
	 * Will clear the chart canvas. Used extensively internally between animation frames.
	 */
	@JsMethod
	native void clear();

	/**
	 * Reset the chart to it's state before the initial animation. A new animation can then be triggered using update.
	 */
	@JsMethod
	native void reset();

	/**
	 * Returns a base 64 encoded string of the chart in it's current state.
	 * 
	 * @return base 64 image
	 */
	@JsMethod
	native String toBase64Image();

	/**
	 * Returns an HTML string of a legend for that chart. The legend is generated from the legendCallback in the options.
	 * 
	 * @return the HTML legend.
	 */
	@JsMethod
	native String generateLegend();

	/**
	 * Calling on your chart instance passing an argument of an event, will return the single element at the event position.<br>
	 * If there are multiple items within range, only the first is returned.
	 * 
	 * @param event event of chart.
	 * @return single element at the event position, as array of native object.
	 */
	@JsMethod
	native ArrayObject getElementAtEvent(BaseNativeEvent event);

	/**
	 * Looks for the element under the event point, then returns all elements at the same data index.<br>
	 * Calling it on your chart instance passing an argument of an event, will return the point elements that are at that the same position of that event.
	 * 
	 * @param event event of chart.
	 * @return all elements at the same data index, as array of native object.
	 */
	@JsMethod
	native ArrayObject getElementsAtEvent(BaseNativeEvent event);

	/**
	 * Looks for the dataset that matches the current index and returns that metadata.
	 * 
	 * @param index dataset index
	 * @return dataset meta data item, as native object
	 */
	@JsMethod
	native NativeObject getDatasetMeta(int index);

	/**
	 * Gets if the dataset is visible or not, selected by index.
	 * 
	 * @param index dataset index
	 * @return <code>true</code> if dataset is visible otherwise <code>false</code>.
	 */
	@JsMethod
	native boolean isDatasetVisible(int index);

	/**
	 * Gets the amount of datasets which are visible
	 * 
	 * @return the amount of datasets which are visible
	 */
	@JsMethod
	native int getVisibleDatasetCount();

	/**
	 * Gets the dataset of the chart, selected by event.
	 * 
	 * @param event event of get the dataset.
	 * @return dataset meta data items.
	 */
	@JsMethod
	native ArrayObject getDatasetAtEvent(BaseNativeEvent event);

	/**
	 * Sets the visibility for a given dataset.<br>
	 * This can be used to build a chart legend in HTML.<br>
	 * During click on one of the HTML items, you can call it to change the appropriate dataset.
	 * 
	 * @param datasetIndex dataset index
	 * @param visibility if <code>true</code> enables the visibility otherwise <code>false</code>
	 */
	@JsMethod
	native void setDatasetVisibility(int datasetIndex, boolean visibility);

	/**
	 * Toggles the visibility of an item in all datasets.<br>
	 * A dataset needs to explicitly support this feature for it to have an effect.<br>
	 * From internal chart types, doughnut / pie and polar area use this.
	 * 
	 * @param index data index
	 */
	@JsMethod
	native void toggleDataVisibility(int index);

	/**
	 * Returns the stored visibility state of an data index for all datasets.
	 * 
	 * @param index data index
	 * @return <code>true</code> if the data item is visible
	 */
	@JsMethod
	native boolean getDataVisibility(int index);

	/**
	 * Sets the visibility for the given dataset to false.<br>
	 * Updates the chart and animates the dataset with 'hide' mode.<br>
	 * This animation can be configured under the hide key in animation options.
	 * 
	 * @param datasetIndex dataset index
	 */
	@JsMethod
	native void hide(int datasetIndex);

	/**
	 * Sets the visibility for the given dataset to true.<br>
	 * Updates the chart and animates the dataset with 'show' mode.<br>
	 * This animation can be configured under the show key in animation options.
	 * 
	 * @param datasetIndex dataset index
	 */
	@JsMethod
	native void show(int datasetIndex);

	/**
	 * Returns the CHART JS chart ID.
	 * 
	 * @return the CHART JS chart ID.
	 */
	@JsProperty
	native int getId();

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel.
	 */
	@JsProperty
	native int getWidth();

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel.
	 */
	@JsProperty
	native int getHeight();

	/**
	 * Returns the aspect ratio.
	 * 
	 * @return the aspect ratio.
	 */
	@JsProperty
	native double getAspectRatio();

	/**
	 * Returns the current device pixel ratio.
	 * 
	 * @return the current device pixel ratio.
	 */
	@JsProperty
	native double getCurrentDevicePixelRatio();

	/**
	 * Returns the chart area node, as native object.
	 * 
	 * @return the chart area node.
	 */
	@JsProperty
	native NativeObject getChartArea();

	/**
	 * Returns the legend node, as native object.
	 * 
	 * @return the legend node.
	 */
	@JsProperty
	native NativeObject getLegend();

	/**
	 * Returns the title node, as native object.
	 * 
	 * @return the title node.
	 */
	@JsProperty
	native NativeObject getTitleBlock();

	/**
	 * Returns the options node, as native object.
	 * 
	 * @return the options node.
	 */
	@JsProperty
	native NativeObject getOptions();

	/**
	 * Sets new options node, as native object, for updating a existing chart.
	 * 
	 * @param newOptions options node.
	 */
	@JsProperty
	native void setOptions(NativeObject newOptions);

	/**
	 * Returns the scales node, as native object.
	 * 
	 * @return the scales node.
	 */
	@JsProperty
	native NativeObject getScales();

	/**
	 * Returns the tooltip node, as native object.
	 * 
	 * @return the tooltip node.
	 */
	@JsProperty
	native NativeObject getTooltip();

	/**
	 * Returns the CHARBA ID, set to the chart.
	 * 
	 * @return the CHARBA ID
	 * @see org.pepstock.charba.client.commons.Id
	 */
	@JsOverlay
	public String getCharbaId() {
		return Id.get(getOptions());
	}

	/**
	 * Returns the CHARBA chart or <code>null</code> if CHARBA id is not present into CAHRT.JS chart options.
	 * 
	 * @return the CHARBA chart or <code>null</code> if CHARBA id is not present into CAHRT.JS chart options
	 */
	@JsOverlay
	public IsChart getChart() {
		return Charts.get(getCharbaId());
	}
}
