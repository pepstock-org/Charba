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
public final class MatrixChart extends AbstractChart implements IsDatasetCreator<MatrixDataset> {

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
		// disables plugins which can not work with this controller.
		getOptions().getPlugins().setEnabled(ResourceName.DATALABELS_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(ResourceName.LABELS_PLUGIN.value(), false);
		// checks if there is a data set
		if (!getData().getDatasets().isEmpty()) {
			// a matrix chart must have only 1 data set
			Checker.checkIfEqualTo(getData().getDatasets().size(), 1, "Datasets size");
			// gets the data set (only 1)
			Dataset dataset = getData().getDatasets().get(0);
			// checks if is a matrix data set
			Checker.assertCheck(dataset instanceof MatrixDataset, "Dataset is not a MatrixDataset");
		}
		// checks all defined scales
		for (Axis axis : getOptions().getScales().getAxes()) {
			// checks type of axis
			// only cartesian axes are accepted
			Checker.assertCheck(axis instanceof CartesianAxis, "Axis is not a CartesianAxis");
		}
	}

}