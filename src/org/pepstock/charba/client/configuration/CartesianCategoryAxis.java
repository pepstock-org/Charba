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
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.data.Labels;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.ChartAxisType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.options.ScaleId;

/**
 * This object is used to map defined axis as category.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class CartesianCategoryAxis extends CartesianAxis<CartesianCategoryTick> {

	private final CartesianCategoryTick ticks;

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianCategoryAxis(IsChart chart) {
		// uses X as axis id
		this(chart, ChartAxisType.CATEGORY.getDefaultScaleId());
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianCategoryAxis(IsChart chart, String id) {
		this(chart, ScaleId.create(id));
	}

	/**
	 * Builds the object storing the chart instance. Axis type is X by default.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 */
	public CartesianCategoryAxis(IsChart chart, ScaleId id) {
		this(chart, id, null);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param kind axis kind.
	 */
	public CartesianCategoryAxis(IsChart chart, AxisKind kind) {
		this(chart, DefaultScaleId.getByAxisKind(kind, ChartAxisType.CATEGORY.getDefaultScaleId()), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind.
	 */
	public CartesianCategoryAxis(IsChart chart, String id, AxisKind kind) {
		this(chart, ScaleId.create(id), kind);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param kind axis kind.
	 */
	public CartesianCategoryAxis(IsChart chart, ScaleId id, AxisKind kind) {
		this(chart, id, ChartAxisType.CATEGORY, Key.isValid(kind) ? kind : DefaultScaleId.getAxisKindByScaleId(id, AxisKind.X));
	}
	
	/**
	 * Builds the object storing the chart instance and cartesian axis type, to use to extend the axis.
	 * 
	 * @param chart chart instance
	 * @param id axis id
	 * @param type axis type
	 * @param kind axis kind
	 */
	protected CartesianCategoryAxis(IsChart chart, ScaleId id, AxisType type, AxisKind kind) {
		super(chart, id, type, kind);
		// creates the ticks instance
		this.ticks = new CartesianCategoryTick(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.scales.CartesianAxis#getTicks()
	 */
	@Override
	public CartesianCategoryTick getTicks() {
		return ticks;
	}

	/**
	 * Sets an array of labels to display.
	 * 
	 * @param labels An array of labels to display.
	 */
	public void setLabels(String... labels) {
		getConfiguration().setLabels(labels);
	}

	/**
	 * Sets an array of labels to display.
	 * 
	 * @param labels An array of labels to display.
	 */
	public void setLabels(List<String> labels) {
		getConfiguration().setLabels(labels);
	}

	/**
	 * Sets the labels of the data.
	 * 
	 * @param labels labels object to manage also multi-line labels
	 */
	public void setLabels(Labels labels) {
		getConfiguration().setLabels(labels);
	}

	/**
	 * Returns the labels.
	 * 
	 * @return the labels
	 */
	public Labels getLabels() {
		return getConfiguration().getLabels();
	}

	/**
	 * Returns the labels for axes.
	 * 
	 * @param binding if <code>true</code> binds the new labels in the container
	 * @return the labels for axes
	 */
	public Labels getLabels(boolean binding) {
		return getConfiguration().getLabels(binding);
	}

	/**
	 * Sets the minimum item to display.
	 * 
	 * @param min The minimum item to display
	 */
	public void setMin(String min) {
		getScale().setMin(min);
	}

	/**
	 * Returns the minimum item to display
	 * 
	 * @return The minimum item to display
	 */
	public String getMin() {
		return getScale().getMinAsString();
	}

	/**
	 * Returns the minimum item at passed index to display
	 * 
	 * @return The minimum item at passed index to display
	 */
	public int getMinIndex() {
		return getScale().getMinIndex();
	}

	/**
	 * Sets the minimum item at passed index to display.
	 * 
	 * @param min The minimum item at passed index to display
	 */
	public void setMinIndex(int min) {
		getScale().setMinIndex(min);
	}

	/**
	 * Sets the maximum item to display.
	 * 
	 * @param max the maximum item to display.
	 */
	public void setMax(String max) {
		getScale().setMax(max);
	}

	/**
	 * Returns the maximum item to display.
	 * 
	 * @return the maximum item to display.
	 */
	public String getMax() {
		return getScale().getMaxAsString();
	}

	/**
	 * Sets the maximum item at passed index to display.
	 * 
	 * @param max the maximum item at passed index to display.
	 */
	public void setMaxIndex(int max) {
		getScale().setMaxIndex(max);
	}

	/**
	 * Returns the maximum item at passed index to display.
	 * 
	 * @return the maximum item at passed index to display.
	 */
	public int getMaxIndex() {
		return getScale().getMaxIndex();
	}

}