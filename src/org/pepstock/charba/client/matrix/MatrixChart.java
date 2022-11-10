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
package org.pepstock.charba.client.matrix;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.HasCartesianAxes;
import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.configuration.CartesianAxis;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * A matrix chart shows magnitude of a phenomenon as color in two dimensions.<br>
 * The variation in color may be by hue or intensity, giving obvious visual cues to the reader about how the phenomenon is clustered or varies over space.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class MatrixChart extends AbstractChart implements IsDatasetCreator<MatrixDataset>, HasCartesianAxes {

	// extending chart type
	// this is a custom implementation because
	// Matrix controllers don't extend any existing chart type
	private static final MatrixExtendedChartType MATRIX_EXTENDED_CHART_TYPE = new MatrixExtendedChartType();

	/**
	 * Name of chart type <b>{@value TYPE}</b>
	 */
	public static final String TYPE = "matrix";
	/**
	 * <b>Matrix</b> controller type.
	 */
	public static final ControllerType CONTROLLER_TYPE = new ControllerType(TYPE, MATRIX_EXTENDED_CHART_TYPE, MatrixController.PROVIDER);
	// maximum amount of datasets
	private static final int MAXIMUM_DATASETS_COUNT = 1;
	// maximum amount of axes
	private static final int MAXIMUM_AXES_COUNT = 2;
	// controller instance
	private MatrixController matrixController = null;
	// chart options
	private final MatrixOptions options;

	/**
	 * Builds the object.
	 */
	public MatrixChart() {
		super(CONTROLLER_TYPE);
		// creates options
		options = new MatrixOptions(this, getDefaultChartOptions());
	}

	/**
	 * Returns the controller instance or <code>null</code> if chart not initialized.
	 * 
	 * @return the controller instance
	 */
	MatrixController getController() {
		return matrixController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public MatrixOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset(boolean)
	 */
	@Override
	public MatrixDataset newDataset(boolean hidden) {
		// hidden is ignored because a matrix chart has got only 1 data set
		return new MatrixDataset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#applyConfiguration()
	 */
	@Override
	protected final void applyConfiguration() {
		// registers the controller
		// if not register
		// checks for matrix controller
		if (CONTROLLER_TYPE.register() && matrixController == null) {
			// if here the controller is registered
			// then gets it
			Controller controllerInstance = Defaults.get().getControllers().getController(CONTROLLER_TYPE);
			// checks if controller is a matrix controller
			Checker.assertCheck(controllerInstance instanceof MatrixController, "Controller stored for " + CONTROLLER_TYPE.value() + " is not a MatrixController");
			// casts to matrix controller
			matrixController = (MatrixController) controllerInstance;
		}
		// disables legend
		// disables the legend at the beginning
		getOptions().getLegend().setDisplay(false);
		// disables plugins which can not work with this controller.
		getOptions().getPlugins().setEnabled(ResourceName.LABELS_PLUGIN.value(), false);
		// checks all defined scales
		for (Axis axis : getOptions().getScales().getAxes()) {
			// checks type of axis
			// only cartesian axes are accepted
			Checker.assertCheck(axis instanceof CartesianAxis, "Axis is not a CartesianAxis");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#getDatasetsCount()
	 */
	@Override
	protected int getMaximumDatasetsCount() {
		// maximum datasets
		return MAXIMUM_DATASETS_COUNT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#checkDataset(org.pepstock.charba.client.data.Dataset)
	 */
	@Override
	protected boolean checkDataset(Dataset dataset) {
		return dataset instanceof MatrixDataset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#getMaximumAxesCount()
	 */
	@Override
	protected final int getMaximumAxesCount() {
		return MAXIMUM_AXES_COUNT;
	}

	/**
	 * Registers the MATRIX controller in CHART.JS.
	 */
	public static void register() {
		// registers the controller
		CONTROLLER_TYPE.register();
	}
}