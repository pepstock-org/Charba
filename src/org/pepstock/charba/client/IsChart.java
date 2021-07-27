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
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.enums.ImageMimeType;
import org.pepstock.charba.client.events.Event;
import org.pepstock.charba.client.events.EventHandler;
import org.pepstock.charba.client.events.EventType;
import org.pepstock.charba.client.events.HandlerRegistration;
import org.pepstock.charba.client.items.ActiveDatasetElement;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetReference;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.TransitionKey;
import org.pepstock.charba.client.plugins.Plugins;
import org.pepstock.charba.client.utils.CTimer;

/**
 * Interface which defines a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsChart {

	/**
	 * Returns <code>true</code> if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as well, and if mandatory methods of interface will return
	 * consistent instances.
	 * 
	 * @param chart chart to be checked
	 * @return <code>true</code> if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as well, and if mandatory methods of interface will return
	 *         consistent instances.
	 */
	static boolean isConsistent(IsChart chart) {
		// checks if chart is consistent
		if (isValid(chart)) {
			// here checks all mandatory methods of interface to check
			// if results are consistent
			return chart.getChartElement() != null && chart.getOptions() != null && Type.isValid(chart.getType()) && chart.getCanvas() != null && chart.getNode() != null && chart.getData() != null && chart.getPlugins() != null
					&& chart.getDefaultChartOptions() != null;
		}
		return false;
	}

	/**
	 * Check if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as well, and if mandatory methods of interface will return consistent
	 * instances.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param chart chart to be checked
	 */
	static void checkIfConsistent(IsChart chart) {
		// checks if chart is consistent
		if (!isConsistent(chart)) {
			throw new IllegalArgumentException("Chart is not consistent");
		}
	}

	/**
	 * Check if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as well, and if mandatory methods of interface will return consistent
	 * instances.<br>
	 * If not, throw a {@link IllegalArgumentException} or returns the chart instance.
	 * 
	 * @param chart chart to be checked
	 * @param <T> type of the chart instance
	 * @return the chart instance passed as argument
	 */
	static <T extends IsChart> T checkAndGetIfConsistent(T chart) {
		// checks if chart is consistent
		checkIfConsistent(chart);
		// if here is consistent then return it
		return chart;
	}

	/**
	 * Returns <code>true</code> if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as well.
	 * 
	 * @param chart chart to be checked
	 * @return <code>true</code> if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as well
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
			throw new IllegalArgumentException("Chart is null or not consistent");
		}
	}

	/**
	 * Check if chart passed as argument is not <code>null</code> and its id is not <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException} or returns the chart instance.
	 * 
	 * @param chart chart to be checked
	 * @return the chart instance passed as argument
	 */
	static IsChart checkAndGetIfValid(IsChart chart) {
		// checks if chart is consistent
		checkIfValid(chart);
		// if here is consistent then return it
		return chart;
	}

	/**
	 * Returns <code>true</code> if chart passed as argument is an abstract chart instance.
	 * 
	 * @param chart chart to be checked
	 * @return <code>true</code> if chart passed as argument is an abstract chart instance
	 */
	static boolean isAbstractChart(IsChart chart) {
		return (chart instanceof AbstractChart);
	}

	/**
	 * Adds this handler to the widget.
	 *
	 * @param type the event type
	 * @param handler the handler
	 * @return handler registration used to remove the handler
	 */
	HandlerRegistration addHandler(final EventHandler handler, EventType type);

	/**
	 * Fires the event to the handlers.
	 *
	 * @param event the event to fire
	 */
	void fireEvent(Event event);

	/**
	 * Returns the amount of handlers for a specific event type.
	 * 
	 * @param type event type to use to get the amount of handlers
	 * @return the amount of handlers for a specific event type
	 */
	int getHandlerCount(EventType type);

	/**
	 * Returns <code>true</code> if there is any event handler for event type passed as argument.
	 * 
	 * @param type event type to check
	 * @return <code>true</code> if there is any event handler for event type passed as argument
	 */
	boolean isEventHandled(EventType type);

	/**
	 * Gets a handle to the object's underlying DOM element.
	 * 
	 * 
	 * @return the object's browser element
	 */
	Div getChartElement();

	/**
	 * Returns <code>true</code> whether this chart is currently attached to the browser's document.
	 * 
	 * @return <code>true</code> whether this chart is currently attached to the browser's document
	 */
	boolean isChartAttached();

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
	 * Returns the base type of chart that in case of {@link ChartType} is the same of {@link IsChart#getType()} otherwise, in case the type of the chart is a
	 * {@link ControllerType} is the chart type extension if there is or <code>null</code>.
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
	CursorType getInitialCursor();

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
	IsDefaultScaledOptions getDefaultChartOptions();

	/**
	 * Returns the default options by a chart instance, merging global, chart type global and chart options.
	 * 
	 * @return the default options by a chart instance, merging global, chart type global and chart options
	 */
	IsDefaultScaledOptions getWholeOptions();

	/**
	 * Returns a {@link CTimer} instance inside chart.
	 * 
	 * @return the timer instance of the chart
	 */
	CTimer getTimer();

	/**
	 * Creates a {@link CTimer} instance inside chart.<br>
	 * It can be created only once during the life cycle of the chart.
	 * 
	 * @param task the task to be executed repeatedly
	 * @param interval the time, in milliseconds (thousands of a second), the timer should delay in between executions of the specified task.<br>
	 *            Must be greater than 0.
	 */
	void createAndSetTimer(ChartTimerTask task, int interval);

	/**
	 * Returns <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
	 */
	boolean isDrawOnAttach();

	/**
	 * Sets <code>true</code> if the chart is configured to be draw on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @param drawOnAttach the drawOnAttach to set
	 */
	void setDrawOnAttach(boolean drawOnAttach);

	/**
	 * Returns <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise <code>false</code>.
	 */
	boolean isDestroyOnDetach();

	/**
	 * Sets <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @param destroyOnDetach the destroyOnDetach to set
	 */
	void setDestroyOnDetach(boolean destroyOnDetach);

	/**
	 * Sets the active (hovered) elements for the chart.
	 * 
	 * @param elements list of active elements
	 */
	void setActiveElements(List<ActiveDatasetElement> elements);

	/**
	 * Sets the active (hovered) elements for the chart.
	 * 
	 * @param elements array of active elements
	 */
	void setActiveElements(ActiveDatasetElement... elements);

	/**
	 * Resets the active (hovered) elements for the chart.
	 */
	void resetActiveElements();

	/**
	 * Returns the active (hovered) elements for the chart.
	 * 
	 * @return the list of active elements
	 */
	List<ActiveDatasetElement> getActiveElements();

	/**
	 * Sets the active tooltip elements for the chart.
	 * 
	 * @param elements list of active tooltip elements
	 */
	void setTooltipActiveElements(List<ActiveDatasetElement> elements);

	/**
	 * Resets the active tooltip elements for the chart.
	 */
	void resetTooltipActiveElements();

	/**
	 * Sets the active tooltip elements for the chart.
	 * 
	 * @param elements array of active tooltip elements
	 */
	void setTooltipActiveElements(ActiveDatasetElement... elements);

	/**
	 * Returns the active tooltip elements for the chart.
	 * 
	 * @return the array of active tooltip elements
	 */
	List<ActiveDatasetElement> getTooltipActiveElements();

	/**
	 * Use this to destroy any chart instances that are created. This will clean up any references stored to the chart object within Chart.js, along with any associated event
	 * listeners attached by Chart.js.
	 */
	void destroy();

	/**
	 * Use this to stop any current animation loop. This will pause the chart during any current animation frame. Call <code>.render()</code> to re-animate.
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
	 * Returns a data-URL containing a representation of the image in the PNG format and the image quality value is 0.92.
	 * 
	 * @return a data-URL containing a representation of the image in the PNG format
	 */
	default String toBase64Image() {
		return toBase64Image(ImageMimeType.PNG);
	}

	/**
	 * Returns a data-URL containing a representation of the image in the PNG format.<br>
	 * The returned image is in a resolution of 96dpi.
	 * 
	 * @param encoderOptions between 0 and 1 indicating the image quality to use for image formats that use lossy compression.<br>
	 *            If this argument is anything else, the default value for image quality is used. The default value is 0.92.
	 * @return a data-URL containing a representation of the image in the PNG format
	 */
	default String toBase64Image(double encoderOptions) {
		return toBase64Image(ImageMimeType.PNG, encoderOptions);
	}

	/**
	 * Returns a data-URL containing a representation of the image format, passed as argument.<br>
	 * The returned image is in a resolution of 96dpi and the image quality value is 0.92.
	 * 
	 * @param type indicating the image format
	 * @return a data-URL containing a representation of the image format, passed as argument
	 */
	default String toBase64Image(ImageMimeType type) {
		return toBase64Image(type, Canvas.DEFAULT_ENCODER_OPTIONS);
	}

	/**
	 * Returns a base 64 encoded string of the chart in it's current state.
	 * 
	 * @param type indicating the image format
	 * @param encoderOptions between 0 and 1 indicating the image quality to use for image formats that use lossy compression.<br>
	 *            If this argument is anything else, the default value for image quality is used. The default value is 0.92.
	 * @return base 64 image or {@link Undefined#STRING} if chart is not initialized.
	 */
	String toBase64Image(ImageMimeType type, double encoderOptions);

	/**
	 * Use this to manually resize the canvas element. This is run each time the canvas container is resized, but can be called this method manually if you change the size of the
	 * canvas nodes container element.
	 */
	void resize();

	/**
	 * Use this to manually resize the canvas element.<br>
	 * This is run each time the canvas container is resized, but can be called this method manually if you change the size of the canvas nodes container element.
	 * 
	 * @param width width size of resize
	 * @param height height size of resize
	 */
	void resize(int width, int height);

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update all scales, legends, and then re-render the chart.
	 */
	void update();

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update all scales, legends, and then re-render the chart.<br>
	 * A config object can be provided with additional configuration for the update process.<br>
	 * This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param mode an animation mode can be provided to indicate what should be updated and what animation configuration should be used
	 */
	void update(TransitionKey mode);

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
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update the options, mutating the options property in place.
	 */
	void reconfigure();

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update the options, mutating the options property in place.<br>
	 * A animation mode key can be provided for the update process using a specific animation configuration.<br>
	 * This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param mode an animation mode can be provided to indicate what should be updated and what animation configuration should be used
	 */
	void reconfigure(TransitionKey mode);

	/**
	 * Triggers an update of the chart.<br>
	 * This can be safely called after updating the data object.<br>
	 * This will update the options, mutating the options property in place.<br>
	 * A configuration object can be provided with additional configuration for the update process.<br>
	 * This is useful when update is manually called inside an event handler and some different animation is desired.
	 * 
	 * @param config a configuration object can be provided with additional configuration for the update process
	 */
	void reconfigure(UpdateConfiguration config);

	/**
	 * Triggers a redraw of all chart elements.<br>
	 * Note, this does not update elements for new data. Use <code>.update()</code> in that case.
	 */
	void render();

	/**
	 * Looks for the dataset that matches the current index and returns.
	 * 
	 * @param index dataset index
	 * @return dataset item.
	 */
	DatasetItem getDatasetItem(int index);

	/**
	 * Looks for the dataset that matches the event.
	 * 
	 * @param event event of chart.
	 * @return dataset item.
	 */
	List<DatasetReference> getDatasetAtEvent(BaseNativeEvent event);

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
	 * @return the amount of datasets which are visible. If chart is not initialized, return {@link Undefined#INTEGER}.
	 */
	int getVisibleDatasetCount();

	/**
	 * Sets the visibility for a given dataset.<br>
	 * This can be used to build a chart legend in HTML.<br>
	 * During click on one of the HTML items, you can call it to change the appropriate dataset.
	 * 
	 * @param datasetIndex dataset index
	 * @param visibility if <code>true</code> enables the visibility otherwise <code>false</code>
	 */
	void setDatasetVisibility(int datasetIndex, boolean visibility);

	/**
	 * Toggles the visibility of an item in all datasets.<br>
	 * A dataset needs to explicitly support this feature for it to have an effect.<br>
	 * From internal chart types, doughnut / pie and polar area use this.
	 * 
	 * @param index data index
	 */
	void toggleDataVisibility(int index);

	/**
	 * Returns the stored visibility state of an data index for all datasets.
	 * 
	 * @param index data index
	 * @return <code>true</code> if the data item is visible
	 */
	boolean isDataVisible(int index);

	/**
	 * Sets the visibility for the given dataset to false.<br>
	 * Updates the chart and animates the dataset with 'hide' mode.<br>
	 * This animation can be configured under the hide key in animation options.
	 * 
	 * @param datasetIndex dataset index
	 */
	void hide(int datasetIndex);
	
	/**
	 * Sets the hidden flag of that element index to <code>true</code> and updates the chart.
	 * 
	 * @param datasetIndex dataset index
	 * @param dataIndex data index
	 */
	void hide(int datasetIndex, int dataIndex);

	/**
	 * Sets the visibility for the given dataset to true.<br>
	 * Updates the chart and animates the dataset with 'show' mode.<br>
	 * This animation can be configured under the show key in animation options.
	 * 
	 * @param datasetIndex dataset index
	 */
	void show(int datasetIndex);
	
	/**
	 * Sets the hidden flag of that element index to <code>false</code> and updates the chart.
	 * 
	 * @param datasetIndex dataset index
	 * @param dataIndex data index
	 */
	void show(int datasetIndex, int dataIndex);

	/**
	 * Calling on your chart instance passing an argument of an event, will return the single element at the event position.<br>
	 * If there are multiple items within range, only the first is returned.
	 * 
	 * @param event event of chart.
	 * @return single element at the event position or null.
	 */
	DatasetReference getElementAtEvent(BaseNativeEvent event);

	/**
	 * Looks for the element under the event point, then returns all elements at the same data index.<br>
	 * Calling it on your chart instance passing an argument of an event, will return the point elements that are at that the same position of that event.
	 * 
	 * @param event event of chart.
	 * @return all elements at the same data index or an empty list.
	 */
	List<DatasetReference> getElementsAtEvent(BaseNativeEvent event);

	/**
	 * Draws the chart
	 */
	void draw();

}