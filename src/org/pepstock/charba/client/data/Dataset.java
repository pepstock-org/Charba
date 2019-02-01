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
package org.pepstock.charba.client.data;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.PluginIdChecker;
import org.pepstock.charba.client.utils.JSON;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * This is the base implementation for all datasets with common fields.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class Dataset extends NativeObjectContainer {
	// default for hidden property
	private static final boolean DEFAULT_HIDDEN = false;
	// patterns container
	private final PatternsContainer patternsContainer = new PatternsContainer();
	// gradients container
	private final GradientsContainer gradientsContainer = new GradientsContainer();
	// default options values
	private final IsDefaultOptions defaultValues;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		label,
		data,
		type,
		hidden,
		// internal key to store patterns and gradients
		_charbaPatterns,
		_charbaGradients
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	Dataset() {
		// creates object with global default
		this(Defaults.get().getGlobal());
	}

	/**
	 * Creates the dataset using a default, adding patterns and gradients element.
	 * 
	 * @param defaultValues default options
	 */
	Dataset(IsDefaultOptions defaultValues) {
		this.defaultValues = defaultValues;
		// sets the Charba containers into dataset java script configuration
		setValue(Property._charbaPatterns, patternsContainer);
		setValue(Property._charbaGradients, gradientsContainer);
	}
	
	/**
	 * Returns the patterns container element.
	 * 
	 * @return the patterns container
	 */
	final PatternsContainer getPatternsContainer() {
		return patternsContainer;
	}

	/**
	 * Returns the gradients container element.
	 * 
	 * @return the gradients container
	 */
	final GradientsContainer getGradientsContainer() {
		return gradientsContainer;
	}

	/**
	 * Returns the default options instance.
	 * 
	 * @return the default options instance.
	 */
	final IsDefaultOptions getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Returns <code>true</code> if the color (selected by its property name) is not both a gradient not a pattern, otherwise
	 * <code>false</code>.
	 * 
	 * @param key property name to check
	 * @return <code>true</code> if the color (selected by its property name) is not both a gradient not a pattern.
	 */
	final boolean hasColors(Key key) {
		return !getPatternsContainer().hasObjects(key) && !getGradientsContainer().hasObjects(key);
	}

	/**
	 * Returns <code>true</code> if the color (selected by its property name) is a pattern, otherwise <code>false</code>.
	 * 
	 * @param key property name to check
	 * @return <code>true</code> if the color (selected by its property name) is a pattern.
	 */
	final boolean hasPatterns(Key key) {
		return getPatternsContainer().hasObjects(key);
	}

	/**
	 * Returns <code>true</code> if the color (selected by its property name) is a gradient, otherwise <code>false</code>.
	 * 
	 * @param key property name to check
	 * @return <code>true</code> if the color (selected by its property name) is a gradient.
	 */
	final boolean hasGradients(Key key) {
		return getGradientsContainer().hasObjects(key);
	}

	/**
	 * Removes the property key related to the color from pattern and gradient container if color is selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingColors(Key key) {
		// remove from patterns
		getPatternsContainer().removeObjects(key);
		// remove from gradients
		getGradientsContainer().removeObjects(key);
	}

	/**
	 * Removes the property key related to the color from dataset object and gradient container if pattern is selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingPatterns(Key key) {
		// removes color key from dataset object
		removeIfExists(key);
		// remove from gradients
		getGradientsContainer().removeObjects(key);
	}

	/**
	 * Removes the property key related to the color from dataset object and pattern container if gradient is selected.
	 * 
	 * @param key key property name to remove.
	 */
	final void resetBeingGradients(Key key) {
		// removes color key from dataset object
		removeIfExists(key);
		// remove from patterns
		getPatternsContainer().removeObjects(key);
	}

	/**
	 * It applies all canvas patterns defined into dataset. The canvas pattern needs to be created a context 2d of canvas
	 * therefore must be created by a chart.<br>
	 * This is called by {@link CanvasObjectHandler}.
	 * 
	 * @param chart chart instance
	 * @see CanvasObjectHandler
	 */
	final void applyPatterns(AbstractChart<?, ?> chart) {
		// checks if there is any pattern
		if (!getPatternsContainer().isEmpty()) {
			// gets all keys of pattern containers.
			// the key is the CHART.JS dataset property to set
			for (Key key : getPatternsContainer().getKeys()) {
				// gets list of all patterns
				List<Pattern> patterns = getPatternsContainer().getObjects(key);
				// creates the list of canvas pattern
				final List<CanvasPattern> canvasPatternsList = new LinkedList<CanvasPattern>();
				// scans the patterns
				for (Pattern pattern : patterns) {
					// creates the canvas pattern and adds into list
					canvasPatternsList.add(CanvasObjectFactory.createPattern(chart, pattern));
				}
				// asks to dataset implementation to set the property
				// applying the logic it wants
				applyPattern(key, canvasPatternsList);
			}
		}
	}

	/**
	 * Stores the canvas patterns into dataset object by property name passed as key.
	 * 
	 * @param key key property name to use to store canvas patterns into dataset object.
	 * @param canvasPatternsList list of canvas patterns
	 */
	abstract void applyPattern(Key key, List<CanvasPattern> canvasPatternsList);

	/**
	 * It applies all canvas gradients defined into dataset. The canvas gradients needs to be created a context 2d of canvas
	 * therefore must be created by a chart.<br>
	 * This is called by {@link CanvasObjectHandler}.
	 * 
	 * @param chart chart instance
	 * @param datasetIndex dataset index related to dataset to be checked
	 * @see CanvasObjectHandler
	 */
	final void applyGradients(AbstractChart<?, ?> chart, int datasetIndex) {
		// checks if there is any gradient to be created
		if (!getGradientsContainer().isEmpty()) {
			// scans all key of all created gradients
			for (Key key : getGradientsContainer().getKeys()) {
				// gets all gradients for the key
				List<Gradient> gradients = getGradientsContainer().getObjects(key);
				// creates a temporary list of gradients
				List<CanvasGradient> canvasGradientsList = new LinkedList<CanvasGradient>();
				// sets internal dataset index
				int index = 0;
				// scans all gradients
				for (Gradient gradient : gradients) {
					// creates gradient and adds to the temporary list
					canvasGradientsList.add(CanvasObjectFactory.createGradient(chart, gradient, datasetIndex, index));
					// increments the index
					index++;
				}
				// asks to dataset implementation to set the property
				// applying the logic it wants
				applyGradient(key, canvasGradientsList);
			}
		}
	}

	/**
	 * Stores the canvas gradients into dataset object by property name passed as key.
	 * 
	 * @param key key property name to use to store canvas gradients into dataset object.
	 * @param canvasGradientsList list of canvas gradients
	 */
	abstract void applyGradient(Key key, List<CanvasGradient> canvasGradientsList);

	/**
	 * Sets if the dataset will appear or not.
	 * 
	 * @param hidden if the dataset will appear or not.
	 */
	public void setHidden(boolean hidden) {
		// checks if is hidden
		if (hidden) {
			// then sets it
			setValue(Property.hidden, hidden);
		} else {
			// if is not hidden
			// remove the property
			remove(Property.hidden);
		}
	}

	/**
	 * Returns if the dataset will appear or not.
	 * 
	 * @return if the dataset will appear or not. Default is <code>false</code>
	 */
	public boolean isHidden() {
		return getValue(Property.hidden, DEFAULT_HIDDEN);
	}

	/**
	 * Sets the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @param label the label for the dataset which appears in the legend and tooltips.
	 */
	public void setLabel(String label) {
		setValue(Property.label, label);
	}

	/**
	 * Returns the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @return the label for the dataset which appears in the legend and tooltips.
	 */
	public String getLabel() {
		return getValue(Property.label, UndefinedValues.STRING);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param values an array of numbers
	 */
	public void setData(double... values) {
		setArrayValue(Property.data, ArrayDouble.of(values));
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param values list of numbers.
	 */
	public void setData(List<Double> values) {
		setArrayValue(Property.data, ArrayDouble.of(values));
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of numbers. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @return list of numbers.
	 */
	public List<Double> getData() {
		ArrayDouble array = getArrayValue(Property.data);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the type of dataset based on type of chart.
	 * 
	 * @param type type of dataset.
	 */
	public final void setType(Type type) {
		setValue(Property.type, type);
	}

	/**
	 * Returns the type of dataset, based on type of chart.
	 * 
	 * @return type of dataset or null if not set. If not set or invalid, the default is
	 *         {@link org.pepstock.charba.client.ChartType#bar}.
	 */
	public final Type getType() {
		// gets string value from java script object
		String value = getValue(Property.type, ChartType.bar.name());
		// checks if consistent with out of the box chart types
		Type type = ChartType.get(value);
		// if not, creates new type being a controller.
		if (type == null) {
			// gets type from controllers
			type = Defaults.get().getControllers().getTypeByString(value);
		}
		return type == null ? ChartType.bar : type;
	}

	/**
	 * Sets the plugin dataset configuration. If dataset configuration options is null, the configuration of plugin will be
	 * removed.
	 * 
	 * @param pluginId plugin id.
	 * @param options java script object used to configure the plugin. Pass <code>null</code> to remove the configuration if
	 *            exist.
	 * @param <T> type of native object container to store
	 */
	public final <T extends NativeObjectContainer> void setOptions(String pluginId, T options) {
		// if null, removes the configuration
		if (options == null) {
			// removes configuration if exists
			remove(PluginIdChecker.key(pluginId));
		} else {
			// stores configuration
			setValue(PluginIdChecker.key(pluginId), options);
		}
	}

	/**
	 * Checks if there is any dataset configuration for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	public final boolean hasOptions(String pluginId) {
		return has(PluginIdChecker.key(pluginId));
	}

	/**
	 * Returns the plugin dataset configuration, if exist. It uses a factory instance to create a native object container.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return java script object used to configure the plugin or <code>null</code> if not exist.
	 */
	public final <T extends NativeObjectContainer> T getOptions(String pluginId, NativeObjectContainerFactory<T> factory) {
		return factory.create(getValue(PluginIdChecker.key(pluginId)));
	}

	/**
	 * Returns the data property in JSON format.
	 * 
	 * @return the data property in JSON format.
	 */
	final String getDataAsString() {
		return JSON.stringify(getArrayValue(Property.data));
	}

}