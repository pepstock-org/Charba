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
package org.pepstock.charba.client.sankey;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.configuration.CartesianLinearAxis;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.impl.plugins.ColorSchemes;
import org.pepstock.charba.client.impl.plugins.DatasetsItemsSelector;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Sankey charts are a type of flow diagram in which the width of the arrows is proportional to the flow rate.<br>
 * Sankey diagrams emphasize the major transfers or flows within a system.<br>
 * They help locate the most important contributions to a flow.<br>
 * They often show conserved quantities within defined system boundaries.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class SankeyChart extends AbstractChart implements IsDatasetCreator<SankeyDataset> {

	// extending chart type
	// this is a custom implementation because
	// Sankey controllers don't extend any existing chart type
	private static final SankeyExtendedChartType SANKEY_EXTENDED_CHART_TYPE = new SankeyExtendedChartType();

	/**
	 * Name of chart type <b>{@value TYPE}</b>
	 */
	public static final String TYPE = "sankey";
	/**
	 * <b>Sankey</b> controller type.
	 */
	public static final ControllerType CONTROLLER_TYPE = new ControllerType(TYPE, SANKEY_EXTENDED_CHART_TYPE, SankeyController.PROVIDER);
	// controller instance
	private SankeyController sankeyController = null;
	// chart options
	private final SankeyOptions options;

	/**
	 * Builds the object.
	 */
	public SankeyChart() {
		super(CONTROLLER_TYPE);
		// creates options
		options = new SankeyOptions(this, getDefaultChartOptions());
	}

	/**
	 * Returns the controller instance or <code>null</code> if chart not initialized.
	 * 
	 * @return the controller instance
	 */
	SankeyController getController() {
		return sankeyController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public SankeyOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset(boolean)
	 */
	@Override
	public SankeyDataset newDataset(boolean hidden) {
		// hidden is ignored because a sankey chart has got only 1 data set
		return new SankeyDataset();
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
		// checks for sankey controller
		if (CONTROLLER_TYPE.register() && sankeyController == null) {
			// if here the controller is registered
			// then gets it
			Controller controllerInstance = Defaults.get().getControllers().getController(CONTROLLER_TYPE);
			// checks if controller is a sankey controller
			Checker.assertCheck(controllerInstance instanceof SankeyController, "Controller stored for " + CONTROLLER_TYPE.value() + " is not a SankeyController");
			// casts to sankey controller
			sankeyController = (SankeyController) controllerInstance;
		}
		// disables plugins which can not work with this controller.
		getOptions().getPlugins().setEnabled(ResourceName.DATALABELS_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(ResourceName.LABELS_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(ResourceName.ZOOM_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(ResourceName.ANNOTATION_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(HtmlLegend.ID, false);
		getOptions().getPlugins().setEnabled(DatasetsItemsSelector.ID, false);
		getOptions().getPlugins().setEnabled(ColorSchemes.ID, false);
		// checks if there is a data set
		if (!getData().getDatasets().isEmpty()) {
			// a sankey chart must have only 1 data set
			Checker.checkIfEqualTo(getData().getDatasets().size(), 1, "Datasets size");
			// gets the data set (only 1)
			Dataset dataset = getData().getDatasets().get(0);
			// checks if is a sankey data set
			Checker.assertCheck(dataset instanceof SankeyDataset, "Dataset is not a SankeyDataset");
		}
		// hides all defined scales
		for (Axis axis : getOptions().getScales().getAxes()) {
			// checks type of axis
			// only cartesian linear axes are accepted
			Checker.assertCheck(axis instanceof CartesianLinearAxis, "Axis is not a CartesianLinearAxis");
			// forces hiding of axis
			axis.setDisplay(false);
		}
	}

}