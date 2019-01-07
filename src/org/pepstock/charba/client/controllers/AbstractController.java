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
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Abstract implementation of a controller. If the chart type is implemented without returning a <code>null</code>, every method will invoke
 * the default implementation of parent chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractController implements Controller {
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#addElements(org.pepstock.charba.client.AbstractChart)
	 */
	@Override
	public void addElements(Context jsThis, AbstractChart<?, ?> chart) {
		// if chart type is consistent
		if (getChartType() != null) {
			// invokes default 
			invokeAddElements(getChartType().name(), jsThis.getObject());
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.Controller#initialize(org.pepstock.charba.client.controllers.Context, org.pepstock.charba.client.AbstractChart, int)
	 */
	@Override
	public void initialize(Context jsThis, AbstractChart<?, ?> chart, int datasetIndex) {
		// if chart type is consistent
		if (getChartType() != null) {
			// invokes default 
			invokeInitialize(getChartType().name(), jsThis.getObject(), datasetIndex);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#addElementAndReset(org.pepstock.charba.client.AbstractChart, int)
	 */
	@Override
	public void addElementAndReset(Context jsThis, AbstractChart<?, ?> chart, int index) {
		// if chart type is consistent
		if (getChartType() != null) {
			// invokes default 
			invokeAddElementAndReset(getChartType().name(), jsThis.getObject(), index);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#draw(org.pepstock.charba.client.AbstractChart, double)
	 */
	@Override
	public void draw(Context jsThis, AbstractChart<?, ?> chart, double ease) {
		// if chart type is consistent
		if (getChartType() != null) {
			// invokes default 
			invokeDraw(getChartType().name(), jsThis.getObject(), ease);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#removeHoverStyle(org.pepstock.charba.client.AbstractChart, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void removeHoverStyle(Context jsThis, AbstractChart<?, ?> chart, StyleElement element) {
		// if chart type is consistent
		if (getChartType() != null) {
			// invokes default 
			invokeRemoveHoverStyle(getChartType().name(), jsThis.getObject(), element.getObject());
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#setHoverStyle(org.pepstock.charba.client.AbstractChart, com.google.gwt.core.client.JavaScriptObject)
	 */
	@Override
	public void setHoverStyle(Context jsThis, AbstractChart<?, ?> chart, StyleElement element) {
		// if chart type is consistent
		if (getChartType() != null) {
			// invokes default 
			invokeSetHoverStyle(getChartType().name(), jsThis.getObject(), element.getObject());
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.controllers.AbstractController#update(org.pepstock.charba.client.AbstractChart, boolean)
	 */
	@Override
	public void update(Context jsThis, AbstractChart<?, ?> chart, boolean reset) {
		// if chart type is consistent
		if (getChartType() != null) {
			// invokes default 
			invokeUpdate(getChartType().name(), jsThis.getObject(), reset);
		}
	}

	/**
	 * Invokes the default <code>initialize</code> method.
	 * @param controllerType extended chart type 
	 * @param jsThis context of controller
	 * @param datasetIndex dataset index
	 */
	private native void invokeInitialize(String controllerType, GenericJavaScriptObject jsThis, int datasetIndex)/*-{
		$wnd.Chart.controllers[controllerType].prototype.initialize.call(jsThis, jsThis.chart, datasetIndex);
	}-*/;

	/**
	 * Invokes the default <code>AddElements</code> method.
	 * @param controllerType extended chart type 
	 * @param jsThis context of controller
	 */
	private native void invokeAddElements(String controllerType, GenericJavaScriptObject jsThis)/*-{
		$wnd.Chart.controllers[controllerType].prototype.addElements.call(jsThis);
		console.log(jsThis);
		jsThis.pippo.pluto();
	}-*/;
	
	/**
	 * Invokes the default <code>addElementAndReset</code> method.
	 * @param controllerType extended chart type 
	 * @param jsThis context of controller
	 * @param index dataset index
	 */
	private native void invokeAddElementAndReset(String controllerType, GenericJavaScriptObject jsThis, int index)/*-{
		$wnd.Chart.controllers[controllerType].prototype.addElementAndReset.call(jsThis, index);
	}-*/;

	/**
	 * Invokes the default <code>draw</code> method.
	 * @param controllerType extended chart type 
	 * @param jsThis context of controller
	 * @param ease if specified, this number represents how far to transition elements.
	 */
	private native void invokeDraw(String controllerType, GenericJavaScriptObject jsThis, double ease)/*-{
		$wnd.Chart.controllers[controllerType].prototype.draw.call(jsThis, ease);
	}-*/;

	/**
	 * Invokes the default <code>removeHoverStyle</code> method.
	 * @param controllerType extended chart type 
	 * @param jsThis context of controller
	 * @param element element to be remove.
	 */
	private native void invokeRemoveHoverStyle(String controllerType, GenericJavaScriptObject jsThis, JavaScriptObject element)/*-{
		$wnd.Chart.controllers[controllerType].prototype.removeHoverStyle.call(jsThis, element);
	}-*/;

	/**
	 * Invokes the default <code>setHoverStyle</code> method.
	 * @param controllerType extended chart type 
	 * @param jsThis context of controller
	 * @param element element to be set.
	 */
	private native void invokeSetHoverStyle(String controllerType, GenericJavaScriptObject jsThis, JavaScriptObject element)/*-{
		$wnd.Chart.controllers[controllerType].prototype.setHoverStyle.call(jsThis, element);
	}-*/;

	/**
	 * Invokes the default <code>update</code> method.
	 * @param controllerType extended chart type 
	 * @param jsThis context of controller
	 * @param reset if true, put the elements into a reset state so they can animate to their final values 
	 */
	private native void invokeUpdate(String controllerType, GenericJavaScriptObject jsThis, boolean reset)/*-{
		$wnd.Chart.controllers[controllerType].prototype.update.call(jsThis, reset);
	}-*/;

}
