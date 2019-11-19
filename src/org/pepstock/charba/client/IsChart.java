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

import java.util.List;

import org.pepstock.charba.client.configuration.ConfigurationOptions;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Data;
import org.pepstock.charba.client.events.ChartNativeEvent;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.Plugins;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Interface which defines a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsChart extends HasHandlers {

	/**
	 * Returns <code>true</code> if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as
	 * well, and if mandatory methods of interface will return consistent instances.
	 * 
	 * @param chart chart to be checked
	 * @return <code>true</code> if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as
	 *         well, and if mandatory methods of interface will return consistent instances.
	 */
	static boolean isConsistent(IsChart chart) {
		// checks if chart is consistent
		if (isValid(chart)) {
			// here checks all mandatory methods of interface to check
			// if results are consistent
			return chart.getElement() != null && chart.getOptions() != null && Type.isValid(chart.getType()) && chart.getCanvas() != null && chart.getNode() != null && chart.getData() != null && chart.getPlugins() != null
					&& chart.getDefaultChartOptions() != null;
		}
		return false;
	}

	/**
	 * Check if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as well, and if mandatory
	 * methods of interface will return consistent instances.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param chart chart to be checked
	 */
	static void checkIfConsistent(IsChart chart) {
		// checks if chart is consistent
		if (!isConsistent(chart)) {
			throw new IllegalArgumentException("Chart implementation instance is not consistent");
		}
	}

	/**
	 * Returns <code>true</code> if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as
	 * well.
	 * 
	 * @param chart chart to be checked
	 * @return <code>true</code> if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as
	 *         well
	 */
	static boolean isValid(IsChart chart) {
		return chart != null && chart.getId() != null;
	}

	/**
	 * Checks if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param chart chart to be checked
	 */
	static void checkIfValid(IsChart chart) {
		if (!isValid(chart)) {
			throw new IllegalArgumentException("Chart implementation instance is null or not consistent");
		}
	}

	/**
	 * Returns <code>true</code> if chart passed as argument is an abstract chart instance.
	 * 
	 * @param chart chart to be checked
	 * @return <code>true</code> if chart passed as argument is an abstract chart instance
	 */
	static boolean isAbstractChart(IsChart chart) {
		return (chart instanceof AbstractChart<?>);
	}

	/**
	 * Checks if chart passed as argument is an abstract chart instance.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param chart chart to be checked
	 */
	static void checkIfAbstractChart(IsChart chart) {
		if (!isAbstractChart(chart)) {
			throw new IllegalArgumentException("Chart implementation instance is not an AbstractChart");
		}
	}

	/**
	 * Adds this handler to the widget.
	 *
	 * @param <H> the type of handler to add
	 * @param type the event type
	 * @param handler the handler
	 * @return {@link HandlerRegistration} used to remove the handler
	 */
	<H extends EventHandler> HandlerRegistration addHandler(final H handler, GwtEvent.Type<H> type);

	/**
	 * Gets a handle to the object's underlying DOM element.
	 * 
	 * 
	 * @return the object's browser element
	 */
	Element getElement();

	/**
	 * Returns the options of chart.
	 * 
	 * @return the options of chart.
	 */
	ConfigurationOptions getOptions();

	/**
	 * Returns the type of chart.
	 * 
	 * @return the type of chart.
	 */
	Type getType();

	/**
	 * Returns the base type of chart that in case of {@link ChartType} is the same of {@link IsChart#getType()} otherwise, in
	 * case the type of the chart is a {@link ControllerType} is the chart type extension if there is or <code>null</code>.
	 * 
	 * @return the base type of chart.
	 */
	Type getBaseType();

	/**
	 * Returns the ID of chart.<br>
	 * It could be considered as chart unique ID.
	 * 
	 * @return the ID of chart
	 */
	String getId();

	/**
	 * Returns the canvas element used to draw the chart.
	 * 
	 * @return the canvas
	 */
	Canvas getCanvas();

	/**
	 * Remove the registration of prevent default mouse listener from canvas.<br>
	 * This is necessary when you will add your mouse down listener.
	 */
	void removeCanvasPreventDefault();

	/**
	 * Returns the initial cursor of the chart.
	 * 
	 * @return the initial cursor of the chart.
	 */
	Cursor getInitialCursor();

	/**
	 * Returns <code>true</code> if CHART.JS chart has been initialized, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if CHART.JS chart has been initialized, otherwise <code>false</code>.
	 */
	boolean isInitialized();

	/**
	 * Returns the chart node with runtime data.
	 * 
	 * @return the chart node.
	 */
	ChartNode getNode();

	/**
	 * Returns the data object with all passed datasets.
	 * 
	 * @return the data configuration object
	 */
	Data getData();

	/**
	 * Returns the plugins element to manage inline plugins.
	 * 
	 * @return the plugins configuration object
	 */
	Plugins getPlugins();

	/**
	 * Returns the default options created based on chart type.
	 * 
	 * @return the default options of the chart
	 */
	ChartOptions getDefaultChartOptions();

	/**
	 * Returns <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise
	 * <code>false</code>.
	 * 
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise
	 *         <code>false</code>. Default is <code>true</code>.
	 */
	boolean isDrawOnAttach();

	/**
	 * Sets <code>true</code> if the chart is configured to be draw on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @param drawOnAttach the drawOnAttach to set
	 */
	void setDrawOnAttach(boolean drawOnAttach);

	/**
	 * Returns <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise
	 * <code>false</code>.
	 * 
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the attach of DIV element,
	 *         otherwise <code>false</code>. Default is <code>true</code>.
	 */
	boolean isDestroyOnDetach();

	/**
	 * Sets <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise
	 * <code>false</code>.
	 * 
	 * @param destroyOnDetach the destroyOnDetach to set
	 */
	void setDestroyOnDetach(boolean destroyOnDetach);

	/**
	 * Use this to destroy any chart instances that are created. This will clean up any references stored to the chart object
	 * within Chart.js, along with any associated event listeners attached by Chart.js.
	 */
	void destroy();

	/**
	 * Use this to stop any current animation loop. This will pause the chart during any current animation frame. Call
	 * <code>.render()</code> to re-animate.
	 */
	void stop();

	/**
	 * Will clear the chart canvas. Used extensively internally between animation frames.
	 */
	void clear();

	/**
	 * Reset the chart to it's state before the initial animation. A new animation can then be triggered using update.
	 */
	void reset();

	/**
	 * Returns a base 64 encoded string of the chart in it's current state.
	 * 
	 * @return base 64 image or {@link UndefinedValues#STRING} if chart is not initialized.
	 */
	String toBase64Image();

	/**
	 * Returns an HTML string of a legend for that chart. The legend is generated from the legendCallback in the options.
	 * 
	 * @return the HTML legend or {@link UndefinedValues#STRING} if chart is not initialized.
	 */
	String generateLegend();

	/**
	 * Use this to manually resize the canvas element. This is run each time the canvas container is resized, but can be called
	 * this method manually if you change the size of the canvas nodes container element.
	 */
	void resize();

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update all scales,
	 * legends, and then re-render the chart.
	 */
	void update();

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update all scales, legends, and then re-render the chart.<br>
	 * A config object can be provided with additional configuration for the update process.<br>
	 * This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param config a config object can be provided with additional configuration for the update process
	 */
	void update(UpdateConfiguration config);

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update the options,
	 * mutating the options property in place.
	 */
	void updateOptions();

	/**
	 * Triggers an update of the chart. This can be safely called after updating the data object. This will update the options,
	 * mutating the options property in place. A configuration object can be provided with additional configuration for the
	 * update process. This is useful when update is manually called inside an event handler and some different animation is
	 * desired.
	 * 
	 * @param configuration a configuration object can be provided with additional configuration for the update process
	 */
	void updateOptions(UpdateConfiguration configuration);

	/**
	 * Triggers a redraw of all chart elements.<br>
	 * Note, this does not update elements for new data. Use <code>.update()</code> in that case.
	 */
	void render();

	/**
	 * Triggers a redraw of all chart elements. Note, this does not update elements for new data. Use <code>.update()</code> in
	 * that case. A config object can be provided with additional configuration for the render process. This is useful when
	 * update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param config a config object can be provided with additional configuration for the render process
	 */
	void render(UpdateConfiguration config);

	/**
	 * Looks for the dataset that matches the current index and returns that metadata.
	 * 
	 * @param index dataset index
	 * @return dataset meta data item.
	 */
	DatasetMetaItem getDatasetMeta(int index);

	/**
	 * Looks for the dataset that matches the event and returns that metadata.
	 * 
	 * @param event event of chart.
	 * @return dataset meta data item.
	 */
	DatasetMetaItem getDatasetAtEvent(ChartNativeEvent event);

	/**
	 * Looks for the dataset if it's visible or not, selected by index.
	 * 
	 * @param index dataset index
	 * @return <code>true</code> if dataset is visible otherwise <code>false</code>.
	 */
	boolean isDatasetVisible(int index);

	/**
	 * Returns the amount of datasets which are visible
	 * 
	 * @return the amount of datasets which are visible. If chart is not initialized, return {@link UndefinedValues#INTEGER}.
	 */
	int getVisibleDatasetCount();

	/**
	 * Calling on your chart instance passing an argument of an event, will return the single element at the event position.<br>
	 * If there are multiple items within range, only the first is returned.
	 * 
	 * @param event event of chart.
	 * @return single element at the event position or null.
	 */
	DatasetItem getElementAtEvent(ChartNativeEvent event);

	/**
	 * Looks for the element under the event point, then returns all elements at the same data index.<br>
	 * Calling it on your chart instance passing an argument of an event, will return the point elements that are at that the
	 * same position of that event.
	 * 
	 * @param event event of chart.
	 * @return all elements at the same data index or an empty list.
	 */
	List<DatasetItem> getElementsAtEvent(ChartNativeEvent event);

	/**
	 * Draws the chart
	 */
	void draw();

	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	String toJSON();

}