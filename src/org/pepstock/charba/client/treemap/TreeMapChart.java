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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.impl.plugins.DatasetsItemsSelector;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * A treemap chart is used for displaying hierarchical data using nested rectangles.<br>
 * Treemaps display hierarchical (tree-structured) data as a set of nested rectangles.<br>
 * Each branch of the tree is given a rectangle, which is then tiled with smaller rectangles representing sub-branches.<br>
 * A leaf node's rectangle has an area proportional to a specified dimension of the data.<br>
 * When the color and size dimensions are correlated in some way with the tree structure, one can often easily see patterns that would be difficult to spot in other ways, such as
 * whether a certain color is particularly relevant.<br>
 * A second advantage of treemaps is that, by construction, they make efficient use of space. As a result, they can legibly display thousands of items on the screen simultaneously.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TreeMapChart extends AbstractChart implements IsDatasetCreator<TreeMapDataset> {

	// extending chart type
	// this is a custom implementation because
	// Treemap controllers don't extend any existing chart type
	private static final TreeMapExtendedChartType TREEMAP_EXTENDED_CHART_TYPE = new TreeMapExtendedChartType();
	/**
	 * Name of chart type <b>{@value TYPE}</b>
	 */
	public static final String TYPE = "treemap";
	/**
	 * <b>TreeMap</b> controller type.
	 */
	public static final ControllerType CONTROLLER_TYPE = new ControllerType(TYPE, TREEMAP_EXTENDED_CHART_TYPE, TreeMapController.PROVIDER);
	// maximum amount of dataset
	private static final int MAXIMUM_DATASETS_COUNT = 1;
	// controller instance
	private TreeMapController treeMapController = null;
	// chart options
	private final TreeMapOptions options;

	/**
	 * Builds the object.
	 */
	public TreeMapChart() {
		super(CONTROLLER_TYPE);
		// creates options
		options = new TreeMapOptions(this, getDefaultChartOptions());
	}

	/**
	 * Returns the controller instance or <code>null</code> if chart not initialized.
	 * 
	 * @return the controller instance
	 */
	TreeMapController getController() {
		return treeMapController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public TreeMapOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset(boolean)
	 */
	@Override
	public TreeMapDataset newDataset(boolean hidden) {
		// hidden is ignored because a treemap chart has got only 1 data set
		return new TreeMapDataset();
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
		// checks for treemap controller
		if (CONTROLLER_TYPE.register() && treeMapController == null) {
			// if here the controller is registered
			// then gets it
			Controller controllerInstance = Defaults.get().getControllers().getController(CONTROLLER_TYPE);
			// checks if controller is a treemap controller
			Checker.assertCheck(controllerInstance instanceof TreeMapController, "Controller stored for " + CONTROLLER_TYPE.value() + " is not a TreeMapController");
			// casts to treemap controller
			treeMapController = (TreeMapController) controllerInstance;
		}
		// disables plugins which can not work with this controller.
		getOptions().getPlugins().setEnabled(ResourceName.DATALABELS_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(ResourceName.LABELS_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(ResourceName.ZOOM_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(ResourceName.ANNOTATION_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(HtmlLegend.ID, false);
		getOptions().getPlugins().setEnabled(DatasetsItemsSelector.ID, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#getDatasetsCount()
	 */
	@Override
	protected int getDatasetsCount() {
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
		return dataset instanceof TreeMapDataset;
	}

}