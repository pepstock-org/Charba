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
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.enums.DefaultTransitionKey;
import org.pepstock.charba.client.options.AnimationTransition;

/**
 * This is an abstract meter chart, inherited by a meter and gauge charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <D> Dataset type for the specific chart, extends MeterDataset
 */
abstract class BaseMeterChart<D extends MeterDataset> extends AbstractChart implements IsDatasetCreator<D> {

	/**
	 * Default of maximum value of data in the a dataset (percentage based), <b>{@value DEFAULT_MAX}</b>.
	 */
	public static final double DEFAULT_MAX = 100D;
	// controller instance
	private BaseMeterController meterController = null;

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param type type of chart
	 */
	BaseMeterChart(Type type) {
		super(type);
	}

	/**
	 * Returns a dataset with a maximum value.
	 * 
	 * @param max maximum value of dataset
	 * @return dataset instance
	 */
	public abstract D newDataset(double max);

	/**
	 * Returns the controller type
	 * 
	 * @return the controller type
	 */
	abstract ControllerType getControllerType();

	/**
	 * Returns the controller instance or <code>null</code> if chart not initialized.
	 * 
	 * @return the controller instance
	 */
	final BaseMeterController getController() {
		return meterController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#applyConfiguration()
	 */
	@Override
	protected final void applyConfiguration() {
		// Because before creating chart options
		// new chart (by controller) must be defined
		// gets the controller type
		// could be gauge or meter
		ControllerType type = getControllerType();
		// registers the controller
		// if not register
		// checks for meter controller
		if (type.register() && meterController == null) {
			// if here the controller is registered
			// then gets it
			Controller controllerInstance = Defaults.get().getControllers().getController(type);
			// checks if controller is a base meter controller
			Checker.assertCheck(controllerInstance instanceof BaseMeterController, "Controller stored for " + getControllerType().value() + " is not a " + BaseMeterController.class.getName());
			// casts to meter controller
			meterController = (BaseMeterController) controllerInstance;
		}
		// gets meter options
		MeterOptions options;
		// checks if options is a meter one
		Checker.assertCheck(getOptions() instanceof MeterOptions, "Chart options are " + getOptions().getClass().getName() + " and not a " + MeterOptions.class.getName());
		// cats and stores
		options = (MeterOptions) getOptions();
		// disables legend
		options.getLegend().setDisplay(false);
		// disables tooltips
		options.getTooltips().setEnabled(false);
		// disables tooltips external callback
		options.getTooltips().setExternalCallback(null);
		// creates a new mode every time
		// because once it has been added to the options
		// it could be changed by user
		AnimationTransition disabledActiveMode = options.getTransitions().create(DefaultTransitionKey.ACTIVE);
		// disables the animation mode
		disabledActiveMode.getAnimation().setDuration(0);
		// creates a new mode every time
		// because once it has been added to the options
		// it could be changed by user
		AnimationTransition disabledResizeMode = options.getTransitions().create(DefaultTransitionKey.RESIZE);
		// disables the animation mode
		disabledResizeMode.getAnimation().setDuration(0);
		// scans all data sets
		for (Dataset dataset : getData().getDatasets()) {
			// disables animation mode active
			dataset.getTransitions().create(DefaultTransitionKey.ACTIVE).getAnimation().setDuration(0);
			// disables animation mode resize
			dataset.getTransitions().create(DefaultTransitionKey.RESIZE).getAnimation().setDuration(0);
		}
		// creates font item
		options.resetFontItem();
	}
}