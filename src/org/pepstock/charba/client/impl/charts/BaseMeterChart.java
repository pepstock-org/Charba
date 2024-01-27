/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
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
import org.pepstock.charba.client.enums.BorderAlign;
import org.pepstock.charba.client.enums.DefaultTransitionMode;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.options.AnimationTransition;

/**
 * This is an abstract meter chart, inherited by a meter and gauge charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <D> data set type for the specific chart, extends MeterDataset
 */
abstract class BaseMeterChart<D extends MeterDataset> extends AbstractChart implements IsDatasetCreator<D> {

	/**
	 * Default of maximum value of data in the a dataset (percentage based),
	 * <b>{@value DEFAULT_MAX}</b>.
	 */
	public static final double DEFAULT_MAX = 100D;
	// maximum amount of datasets
	private static final int MAXIMUM_DATASETS_COUNT = 1;
	// controller instance
	private BaseMeterController meterController = null;

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of
	 * chart.
	 * 
	 * @param type type of chart
	 */
	BaseMeterChart(Type type) {
		super(type);
	}

	/**
	 * Returns a data set with a maximum value.
	 * 
	 * @param max maximum value of data set
	 * @return data set instance
	 */
	public abstract D newDataset(double max);

	/**
	 * Returns the controller type
	 * 
	 * @return the controller type
	 */
	abstract ControllerType getControllerType();

	/**
	 * Returns the controller instance or <code>null</code> if chart not
	 * initialized.
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
			Checker.assertCheck(controllerInstance instanceof BaseMeterController,
					"Controller stored for " + getControllerType().value() + " is not a BaseMeterController");
			// casts to meter controller
			meterController = (BaseMeterController) controllerInstance;
		}
		// gets meter options
		MeterOptions options;
		// checks if options is a meter one
		Checker.assertCheck(getOptions() instanceof MeterOptions, "Chart options are not a MeterOptions instance");
		// cats and stores
		options = (MeterOptions) getOptions();
		// disable auto colors
		options.setAutoColors(false);
		// disables legend
		options.getLegend().setDisplay(false);
		// disables tooltips
		options.getTooltips().setEnabled(false);
		// disables tooltips external callback
		options.getTooltips().setExternalCallback(null);
		// overrides arc element options
		options.getElements().getArc().setOffset(0);
		options.getElements().getArc().setHoverOffset(0);
		options.getElements().getArc().setBorderAlign(BorderAlign.CENTER);
		options.getElements().getArc().setSpacing(0);
		options.getElements().getArc().setBackgroundColor(MeterDataset.DEFAULT_EMPTY_VALUE_COLOR);
		options.getElements().getArc().setHoverBackgroundColor(MeterDataset.DEFAULT_EMPTY_VALUE_COLOR);
		// creates a new mode every time
		// because once it has been added to the options
		// it could be changed by user
		AnimationTransition disabledActiveMode = options.getTransitions().create(DefaultTransitionMode.ACTIVE);
		// disables the animation mode
		disabledActiveMode.getAnimation().setDuration(0);
		// creates a new mode every time
		// because once it has been added to the options
		// it could be changed by user
		AnimationTransition disabledResizeMode = options.getTransitions().create(DefaultTransitionMode.RESIZE);
		// disables the animation mode
		disabledResizeMode.getAnimation().setDuration(0);
		// scans all data sets
		for (Dataset dataset : getData().getDatasets()) {
			// disables animation mode active
			dataset.getTransitions().create(DefaultTransitionMode.ACTIVE).getAnimation().setDuration(0);
			// disables animation mode resize
			dataset.getTransitions().create(DefaultTransitionMode.RESIZE).getAnimation().setDuration(0);
			// ---
			// resets font items
			// ---
			// casts to meter data set
			MeterDataset meterDataset = (MeterDataset) dataset;
			// gets elements
			ValueLabel valueLabel = meterDataset.getValueLabel();
			DescriptionLabel descriptionLabel = meterDataset.getDescriptionLabel();
			// creating new font item
			FontItem valueFontItem = valueLabel.getFont().create();
			FontItem descriptionFontItem = descriptionLabel.getFont().create();
			// stores new font items
			valueLabel.setFontItem(valueFontItem);
			descriptionLabel.setFontItem(descriptionFontItem);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#getDatasetsCount()
	 */
	@Override
	protected final int getMaximumDatasetsCount() {
		// maximum data sets
		return MAXIMUM_DATASETS_COUNT;
	}
}