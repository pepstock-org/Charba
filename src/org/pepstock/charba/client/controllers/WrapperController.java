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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * Wraps a controller, delegating the execution of all hooks to it.<br>
 * The wrapper is mandatory to able to catch all hooks of chart even if the controller implements just a part of the hooks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class WrapperController extends JavaScriptObjectContainer {

	private final Controller delegation;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		type
	}

	/**
	 * Builds the object with controller instance
	 * 
	 * @param delegation controller instance
	 */
	WrapperController(Controller delegation) {
		// stores the controller
		this.delegation = delegation;
		// sets the controller type
		setValue(Property.type, delegation.getType());
		// registers itself with all methods of controller definition
		registerNativeControllersHandler(getJavaScriptObject());
	}

	/**
	 * Returns the chart instances which must be consumed by controller.
	 * 
	 * @param chartId chart id.
	 * @return the chart instance or <code>null</code> if not found.
	 */
	AbstractChart<?, ?> getChart(String chartId) {
		return Charts.get(chartId);
	}

	/**
	 * Returns the controller type.
	 * 
	 * @return the controller type.
	 */
	final Type getType() {
		return delegation.getType();
	}
	
	/**
	 * Initializes the controller.
	 * @param jsThis context of controller 
	 * @param chartId chartId chart id.
	 * @param datasetIndex dataset index
	 */
	protected void onInitialize(GenericJavaScriptObject jsThis, String chartId, int datasetIndex) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.initialize(new Context(jsThis), chart, datasetIndex);
		}
	}

	/**
	 * Create elements for each piece of data in the dataset. Store elements in an array on the dataset.
	 * @param jsThis context of controller 
	 * @param chartId chartId chart id.
	 */
	protected void onAddElements(GenericJavaScriptObject jsThis, String chartId) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.addElements(new Context(jsThis), chart);
		}
	}

	/**
	 * Create a single element for the data at the given index and reset its state.
	 * @param jsThis context of controller 
	 * @param chartId chartId chart id.
	 * @param index dataset index
	 */
	protected void onAddElementAndReset(GenericJavaScriptObject jsThis, String chartId, int index) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.addElementAndReset(new Context(jsThis), chart, index);
		}
	}

	/**
	 * Draw the representation of the dataset.
	 * 
	 * @param jsThis context of controller 
	 * @param chartId chartId chart id.
	 * @param ease if specified, this number represents how far to transition elements.
	 */
	protected void onDraw(GenericJavaScriptObject jsThis, String chartId, String ease) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.draw(new Context(jsThis), chart, Double.valueOf(ease));
		}
	}

	/**
	 * Remove hover styling from the given element.
	 * 
	 * @param jsThis context of controller 
	 * @param chartId chartId chart id.
	 * @param object element to be removed.
	 */
	protected void onRemoveHoverStyle(GenericJavaScriptObject jsThis, String chartId, GenericJavaScriptObject object) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.removeHoverStyle(new Context(jsThis), chart, new StyleElement(object));
		}
	}

	/**
	 * Add hover styling to the given element.
	 * 
	 * @param jsThis context of controller 
	 * @param chartId chartId chart id.
	 * @param object element to be set.
	 */
	protected void onSetHoverStyle(GenericJavaScriptObject jsThis, String chartId, GenericJavaScriptObject object) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.setHoverStyle(new Context(jsThis), chart, new StyleElement(object));
		}
	}

	/**
	 * Update the elements in response to new data.
	 * 
	 * @param jsThis context of controller 
	 * @param chartId chartId chart id.
	 * @param reset if true, put the elements into a reset state so they can animate to their final values
	 */
	protected void onUpdate(GenericJavaScriptObject jsThis, String chartId, boolean reset) {
		// gets chart instance
		AbstractChart<?, ?> chart = getChart(chartId);
		// if consistent, calls controller
		if (chart != null) {
			delegation.update(new Context(jsThis), chart, reset);
		}
	}

	/**
	 * Wraps the protected method to get the java script object.
	 * 
	 * @return the java script object.
	 */
	GenericJavaScriptObject getObject() {
		return getJavaScriptObject();
	}
	
	/**
	 * Registers all hooks of the controller to the wrapper.
	 * @param config java script object where adding the new function and link of java code.
	 */
	private native void registerNativeControllersHandler(GenericJavaScriptObject config)/*-{
	var self = this;

	// Initializes the controller
    config.initialize = function(chart, datasetIndex) {
		this.chart = chart;
		this.datasetIndex = datasetIndex;
		self.@org.pepstock.charba.client.controllers.WrapperController::onInitialize(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;Ljava/lang/String;I)(this, chart.options.charbaId, datasetIndex);
	};
    // Create elements for each piece of data in the dataset. Store elements in an array on the dataset as dataset.metaData
    config.addElements = function() {
		self.@org.pepstock.charba.client.controllers.WrapperController::onAddElements(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;Ljava/lang/String;)(this, this.chart.options.charbaId);
	}
    // Create a single element for the data at the given index and reset its state
    config.addElementAndReset = function(index) {
		self.@org.pepstock.charba.client.controllers.WrapperController::onAddElementAndReset(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;Ljava/lang/String;I)(this, this.chart.options.charbaId, index);
	}
    // Draw the representation of the dataset
    // @param ease  = if specified this number represents how far to transition elements. See the implementation of draw() in any of the provided controllers to see how this should be used
    config.draw = function(ease) {
		self.@org.pepstock.charba.client.controllers.WrapperController::onDraw(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;Ljava/lang/String;Ljava/lang/String;)(this, this.chart.options.charbaId, ease);
	}
    // Remove hover styling from the given element
    config.removeHoverStyle = function(element) {
		self.@org.pepstock.charba.client.controllers.WrapperController::onRemoveHoverStyle(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;Ljava/lang/String;Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(this, this.chart.options.charbaId, element);
	}
    // Add hover styling to the given element
    config.setHoverStyle = function(element) {
		self.@org.pepstock.charba.client.controllers.WrapperController::onSetHoverStyle(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;Ljava/lang/String;Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(this, this.chart.options.charbaId, element);
	}
    // Update the elements in response to new data
    // @param reset  = if true put the elements into a reset state so they can animate to their final values
    config.update = function(reset) {
		self.@org.pepstock.charba.client.controllers.WrapperController::onUpdate(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;Ljava/lang/String;Z)(this, this.chart.options.charbaId, reset);
	}

	}-*/;

}
