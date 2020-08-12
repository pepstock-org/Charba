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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.callbacks.TooltipsAnimationCallback;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.data.DatasetAnimationOptions;
import org.pepstock.charba.client.defaults.IsDefaultDatasets;

/**
 * Contains the options for the datasets.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Datasets extends AbstractModel<Options, IsDefaultDatasets> implements IsDefaultDatasets, HasBarDatasetOptions, HasAnimation {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// lining datasets
		SHOW_LINE("showLine");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// bar options handler instance
	private final BarDatasetOptionsHandler barOptionsHandler;
	// animation container
	private final AnimationContainer animationContainer;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Datasets(Options options, Key childKey, IsDefaultDatasets defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// creates the properties handlers
		this.barOptionsHandler = new BarDatasetOptionsHandler(getNativeObject(), getDefaultValues());
		// sets animation container
		this.animationContainer = new AnimationContainer(getDefaultValues().getAnimation(), getNativeObject());
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	@Override
	public Animation getAnimation() {
		return animationContainer.getAnimation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasAnimation#getAnimationContainer()
	 */
	@Override
	public final AnimationContainer getAnimationContainer() {
		return animationContainer;
	}

	/**
	 * Creates an animations options to use into chart dataset animation callback.
	 * 
	 * @return an animations options to use into chart dataset animation callback
	 * @see TooltipsAnimationCallback
	 */
	public final DatasetAnimationOptions createAnimationOptions() {
		// clones the current animation options and
		// creates and returns a configuration animation
		return new DatasetAnimationOptions(getAnimation(), new OptionsEnvelop<>(Helpers.get().clone(getAnimation().nativeObject())));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasBarDatasetOptions#getDatasetOptionsHandler()
	 */
	@Override
	public BarDatasetOptionsHandler getDatasetOptionsHandler() {
		return barOptionsHandler;
	}

	/**
	 * Sets the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 * 
	 * @param barPercentage percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to
	 *            each other.
	 */
	@Override
	public void setBarPercentage(double barPercentage) {
		HasBarDatasetOptions.super.setBarPercentage(barPercentage);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each
	 * other.
	 * 
	 * @return percent (0-1) of the available width each bar should be within the category width. 1.0 will take the whole category width and put the bars right next to each other.
	 */
	@Override
	public double getBarPercentage() {
		return HasBarDatasetOptions.super.getBarPercentage();
	}

	/**
	 * Sets the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @param categoryPercentage percent (0-1) of the available width each category should be within the sample width.
	 */
	@Override
	public void setCategoryPercentage(double categoryPercentage) {
		HasBarDatasetOptions.super.setCategoryPercentage(categoryPercentage);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the percent (0-1) of the available width each category should be within the sample width.
	 * 
	 * @return the percent (0-1) of the available width each category should be within the sample width.
	 */
	@Override
	public double getCategoryPercentage() {
		return HasBarDatasetOptions.super.getCategoryPercentage();
	}

	/**
	 * Sets the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths are
	 * calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @param barThickness width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap.
	 *            Then, the bars are sized using barPercentage and categoryPercentage.
	 */
	@Override
	public void setBarThickness(int barThickness) {
		HasBarDatasetOptions.super.setBarThickness(barThickness);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the width of each bar in pixels. If set to 'flex', it computes "optimal" sample widths that globally arrange bars side by side. If not set, the base sample widths
	 * are calculated automatically so that they take the full available widths without overlap. Then, the bars are sized using barPercentage and categoryPercentage.
	 * 
	 * @return width of each bar in pixels. If not set, the base sample widths are calculated automatically so that they take the full available widths without overlap. Then, the
	 *         bars are sized using barPercentage and categoryPercentage.
	 */
	@Override
	public int getBarThickness() {
		return HasBarDatasetOptions.super.getBarThickness();
	}

	/**
	 * Sets the maximum bar thickness, to ensure that bars are not sized thicker than this
	 * 
	 * @param maxBarThickness the maximum bar thickness.
	 */
	@Override
	public void setMaxBarThickness(int maxBarThickness) {
		HasBarDatasetOptions.super.setMaxBarThickness(maxBarThickness);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum bar thickness.
	 * 
	 * @return the maximum bar thickness.
	 */
	@Override
	public int getMaxBarThickness() {
		return HasBarDatasetOptions.super.getMaxBarThickness();
	}

	/**
	 * Set this to ensure that bars have a minimum length in pixels.
	 * 
	 * @param minBarLength a minimum length in pixels.
	 */
	@Override
	public void setMinBarLength(int minBarLength) {
		HasBarDatasetOptions.super.setMinBarLength(minBarLength);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns a minimum length in pixels.
	 * 
	 * @return a minimum length in pixels.
	 */
	@Override
	public int getMinBarLength() {
		return HasBarDatasetOptions.super.getMinBarLength();
	}

	/**
	 * Sets if the line is not drawn for this dataset.
	 * 
	 * @param showLine <code>false</code> if the line is not drawn for this dataset.
	 */
	public void setShowLine(boolean showLine) {
		setValue(Property.SHOW_LINE, showLine);
	}

	/**
	 * Returns if the line is not drawn for this dataset.
	 * 
	 * @return <code>false</code> if the line is not drawn for this dataset.
	 */
	@Override
	public boolean isShowLine() {
		return getValue(Property.SHOW_LINE, getDefaultValues().isShowLine());
	}
}