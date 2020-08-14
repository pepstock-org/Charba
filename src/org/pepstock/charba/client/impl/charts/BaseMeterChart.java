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
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.enums.DefaultAnimationModeKey;
import org.pepstock.charba.client.events.AnimationCompleteEvent;
import org.pepstock.charba.client.events.AnimationCompleteEventHandler;
import org.pepstock.charba.client.events.AnimationProgressEvent;
import org.pepstock.charba.client.events.AnimationProgressEventHandler;
import org.pepstock.charba.client.options.AnimationMode;

/**
 * This is an abstract meter chart, inherited by a meter and gauge charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <D> Dataset type for the specific chart, extends MeterDataset
 */
abstract class BaseMeterChart<D extends MeterDataset> extends AbstractChart implements IsDatasetCreator<D> {

	/**
	 * Default of maximum value of data into a dataset (percentage based), <b>{@value DEFAULT_MAX}</b>.
	 */
	public static final double DEFAULT_MAX = 100D;
	// creates internal animation handler
	private final InternalAnimationEventHandler eventHandler = new InternalAnimationEventHandler();
	// flag is the handlers has been set
	private boolean handlersadded = false;
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
		// checks if already registered
		if (!Defaults.get().getControllers().isRegistered(type.value())) {
			// creates and store the controller
			meterController = new BaseMeterController(type);
			// if not, adds a controller
			Defaults.get().getControllers().register(meterController);
		} else if (meterController == null){
			// if here the controller is registered
			// then gets it
			Controller controllerInstance = Defaults.get().getControllers().getController(type);
			// checks if controller is a base meter controller
			if (controllerInstance instanceof BaseMeterController) {
				// casts to meter controller
				meterController = (BaseMeterController)controllerInstance;
			} else {
				// if here is not a meter controller
				// then exception
				throw new IllegalArgumentException("Controller stored for "+getControllerType().value()+" is not a "+BaseMeterController.class.getName());
			}
		}
		// disables legend
		getOptions().getLegend().setDisplay(false);
		// disables tooltips
		getOptions().getTooltips().setEnabled(false);
		// disables tooltips custom callback
		getOptions().getTooltips().setCustomCallback(null);
		// checks if handlers of animation has been added
		if (!handlersadded) {
			// adds animations handlers to chart
			addHandler(eventHandler, AnimationProgressEvent.TYPE);
			addHandler(eventHandler, AnimationCompleteEvent.TYPE);
			// changes flag
			handlersadded = true;
		}
		// creates a new mode every time
		// because once it has been added to the options
		// it could be changed by user
		AnimationMode disabledActiveMode = new AnimationMode(DefaultAnimationModeKey.ACTIVE);
		// disables the animation mode
		disabledActiveMode.setDuration(0);
		// disables animation active
		getOptions().getAnimation().setMode(disabledActiveMode);
		// scans all datasets
		for (Dataset dataset : getData().getDatasets()) {
			// disable animation mode active
			dataset.getAnimation().setMode(disabledActiveMode);
		}
	}

	/**
	 * Animation handlers (progress and complete) in order to animate the labels drawing on meter or gauge charts. 
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private class InternalAnimationEventHandler implements AnimationProgressEventHandler, AnimationCompleteEventHandler {

		// modulo to check if the labels must be draw or not
		private static final double MODULO = 5D;
		// counter to use to check if draws the label
		private int counter = 0;
		// stores the easing value
		private double easingValue = 0D;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.events.AnimationProgressEventHandler#onProgress(org.pepstock.charba.client.events.AnimationProgressEvent)
		 */
		@Override
		public void onProgress(AnimationProgressEvent event) {
			// checks if labels must be drawn base on how many drawing has been done
			if (counter % MODULO == 0) {
				// calculates the easing value
				easingValue = event.getItem().getCurrentStep() / event.getItem().getNumSteps();
			}
			// draws labels
			drawLabelsByController(easingValue);
			// increments counter
			counter++;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.events.AnimationCompleteEventHandler#onComplete(org.pepstock.charba.client.events.AnimationCompleteEvent)
		 */
		@Override
		public void onComplete(AnimationCompleteEvent event) {
			// draws labels
			drawLabelsByController(1D);
			// resets counter and easing value
			counter = 0;
			easingValue = 0D;
		}

		/**
		 * Draws the labels on chart by controller instance.
		 * 
		 * @param easing easing value used to animate the labels drawing if required.
		 */
		private void drawLabelsByController(double easing) {
			// checks if animation is consistent for the controller
			if (meterController.isAnimationConsistetForDrawingByEasing(BaseMeterChart.this)) {
				// invokes the draw labels on controller
				meterController.drawLabels(BaseMeterChart.this, getNode(), easing);
			}
		}

	}

}