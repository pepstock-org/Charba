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
import org.pepstock.charba.client.colors.CanvasObjectFactory;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientsContainer;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.colors.PatternsContainer;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.plugins.InvalidPluginIdException;
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

	private final PatternsContainer patternsContainer = new PatternsContainer();

	private final GradientsContainer gradientsContainer = new GradientsContainer();

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
	 * Creates a dataset, adding patterns and gradients element.
	 */
	Dataset() {
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
	 * FIXME
	 * 
	 * @param key
	 * @return
	 */
	final boolean hasColors(Key key) {
		return !getPatternsContainer().hasObjects(key) && !getGradientsContainer().hasObjects(key);
	}

	final boolean hasPatterns(Key key) {
		return getPatternsContainer().hasObjects(key);
	}

	final boolean hasGradients(Key key) {
		return getGradientsContainer().hasObjects(key);
	}

	final void resetBeingColors(Key key) {
		getPatternsContainer().removeObjects(key);
		getGradientsContainer().removeObjects(key);
	}

	final void resetBeingPatterns(Key key) {
		removeIfExists(key);
		getGradientsContainer().removeObjects(key);
	}

	final void resetBeingGradients(Key key) {
		removeIfExists(key);
		getPatternsContainer().removeObjects(key);
	}

	/**
	 * It applies all canvas patterns defined into dataset. The canvas pattern needs to be created a context 2d of canvas
	 * therefore must be created by a chart.
	 * 
	 * @param chart chart instance
	 */
	final void applyPatterns(AbstractChart<?, ?> chart) {
		if (!getPatternsContainer().isEmpty()) {
			for (Key key : getPatternsContainer().getKeys()) {
				List<Pattern> patterns = getPatternsContainer().getObjects(key);
				List<CanvasPattern> canvasPatternsList = new LinkedList<CanvasPattern>();
				for (Pattern pattern : patterns) {
					canvasPatternsList.add(CanvasObjectFactory.createPattern(chart, pattern));
				}
				applyPattern(key, canvasPatternsList);
			}
		}
	}

	/**
	 * FIXME
	 * @param key
	 * @param canvasPatternsList
	 */
	abstract void applyPattern(Key key, List<CanvasPattern> canvasPatternsList);

	/**
	 * It applies all canvas gradients defined into dataset. The canvas gradients needs to be created a context 2d of canvas
	 * therefore must be created by a chart.
	 * 
	 * @param chart chart instance
	 */
	final void applyGradients(AbstractChart<?, ?> chart) {
		// checks if there is any gradient to be created
		if (!getGradientsContainer().isEmpty()) {
			// scans all key of all created gradients
			for (Key key : getGradientsContainer().getKeys()) {
				// gets all gradients for the key
				List<Gradient> gradients = getGradientsContainer().getObjects(key);
				// creates a temporary list of gradients
				List<CanvasGradient> canvasGradientsList = new LinkedList<CanvasGradient>();
				// scans all gradients
				for (Gradient gradient : gradients) {
					// creates gradient and adds to the temporary list
					canvasGradientsList.add(CanvasObjectFactory.createGradient(chart, gradient));
				}
				applyGradient(key, canvasGradientsList);
			}
		}
	}

	/**
	 * FIXME
	 * @param key
	 * @param canvasGradientsList
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
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public final <T extends NativeObjectContainer> void setOptions(String pluginId, T options) throws InvalidPluginIdException {
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
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public final boolean hasOptions(String pluginId) throws InvalidPluginIdException {
		return has(PluginIdChecker.key(pluginId));
	}

	/**
	 * Returns the plugin dataset configuration, if exist. It uses a factory instance to create a native object container.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return java script object used to configure the plugin or <code>null</code> if not exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public final <T extends NativeObjectContainer> T getOptions(String pluginId, NativeObjectContainerFactory<T> factory) throws InvalidPluginIdException {
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