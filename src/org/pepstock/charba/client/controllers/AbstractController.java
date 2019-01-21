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

/**
 * Abstract implementation of a controller. If the chart type is implemented without returning a <code>null</code>, every method
 * will invoke the default implementation of parent chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractController implements Controller {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.Controller#initialize(org.pepstock.charba.client.jsinterop.controllers.Context,
	 * org.pepstock.charba.client.jsinterop.AbstractChart, int)
	 */
	@Override
	public void initialize(Context context, AbstractChart<?, ?> chart, int datasetIndex) {
		// if chart type is consistent
		if (getType().isExtended()) {
			// invokes default
			JsControllerHelper.get().initialize(getType().getChartType(), context, datasetIndex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.jsinterop.Controller#addElements(org.pepstock.charba.client.jsinterop.controllers.Context,
	 * org.pepstock.charba.client.jsinterop.AbstractChart)
	 */
	@Override
	public void addElements(Context context, AbstractChart<?, ?> chart) {
		// if chart type is consistent
		if (getType().isExtended()) {
			// invokes default
			JsControllerHelper.get().addElements(getType().getChartType(), context);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.Controller#addElementAndReset(org.pepstock.charba.client.jsinterop.controllers.
	 * Context, org.pepstock.charba.client.jsinterop.AbstractChart, int)
	 */
	@Override
	public void addElementAndReset(Context context, AbstractChart<?, ?> chart, int index) {
		// if chart type is consistent
		if (getType().isExtended()) {
			// invokes default
			JsControllerHelper.get().addElementAndReset(getType().getChartType(), context, index);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.Controller#draw(org.pepstock.charba.client.jsinterop.controllers.Context,
	 * org.pepstock.charba.client.jsinterop.AbstractChart, double)
	 */
	@Override
	public void draw(Context context, AbstractChart<?, ?> chart, double ease) {
		// if chart type is consistent
		if (getType().isExtended()) {
			// invokes default
			JsControllerHelper.get().draw(getType().getChartType(), context, ease);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.Controller#removeHoverStyle(org.pepstock.charba.client.jsinterop.controllers.
	 * Context, org.pepstock.charba.client.jsinterop.AbstractChart,
	 * org.pepstock.charba.client.jsinterop.controllers.StyleElement)
	 */
	@Override
	public void removeHoverStyle(Context context, AbstractChart<?, ?> chart, StyleElement element) {
		// if chart type is consistent
		if (getType().isExtended()) {
			// invokes default
			JsControllerHelper.get().removeHoverStyle(getType().getChartType(), context, element.getObject());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.jsinterop.Controller#setHoverStyle(org.pepstock.charba.client.jsinterop.controllers.Context,
	 * org.pepstock.charba.client.jsinterop.AbstractChart, org.pepstock.charba.client.jsinterop.controllers.StyleElement)
	 */
	@Override
	public void setHoverStyle(Context context, AbstractChart<?, ?> chart, StyleElement element) {
		// if chart type is consistent
		if (getType().isExtended()) {
			// invokes default
			JsControllerHelper.get().setHoverStyle(getType().getChartType(), context, element.getObject());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.Controller#update(org.pepstock.charba.client.jsinterop.controllers.Context,
	 * org.pepstock.charba.client.jsinterop.AbstractChart, boolean)
	 */
	@Override
	public void update(Context context, AbstractChart<?, ?> chart, boolean reset) {
		// if chart type is consistent
		if (getType().isExtended()) {
			// invokes default
			JsControllerHelper.get().update(getType().getChartType(), context, reset);
		}
	}
}
