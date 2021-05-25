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

import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.options.IsTransitionKey;

/**
 * Abstract implementation of a controller. If the chart type is implemented without returning a <code>null</code>, every method will invoke the default implementation of parent
 * chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractController implements Controller {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#initialize(org.pepstock.charba.client.controllers.ControllerContext, org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void initialize(ControllerContext context, IsChart chart) {
		// checks if arguments are consistent
		if (Controller.isConsistent(this, context, chart)) {
			// invokes default
			JsControllerHelper.get().initialize(getType(), context);
		}
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.Controller#parse(org.pepstock.charba.client.controllers.ControllerContext, org.pepstock.charba.client.IsChart, int, int)
	 */
	@Override
	public void parse(ControllerContext context, IsChart chart, int start, int count) {
		// checks if arguments are consistent
		if (Controller.isConsistent(this, context, chart)) {
			// invokes default
			JsControllerHelper.get().parse(getType(), context, start, count);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#draw(org.pepstock.charba.client.controllers.Context, org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void draw(ControllerContext context, IsChart chart) {
		// checks if arguments are consistent
		if (Controller.isConsistent(this, context, chart)) {
			// invokes default
			JsControllerHelper.get().draw(getType(), context);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#update(org.pepstock.charba.client.controllers.ControllerContext, org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.options.IsAnimationModeKey)
	 */
	@Override
	public void update(ControllerContext context, IsChart chart, IsTransitionKey mode) {
		// checks if arguments are consistent
		if (Controller.isConsistent(this, context, chart)) {
			// invokes default
			JsControllerHelper.get().update(getType(), context, IsTransitionKey.isValid(mode) ? mode.value() : null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#linkScales(org.pepstock.charba.client.controllers.ControllerContext, org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void linkScales(ControllerContext context, IsChart chart) {
		// checks if arguments are consistent
		if (Controller.isConsistent(this, context, chart)) {
			// invokes default
			JsControllerHelper.get().linkScales(getType(), context);
		}
	}

}
