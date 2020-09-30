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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultLegend;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Legend extends AbstractDefaultPluginElement<IsDefaultLegend> implements IsDefaultLegend, HasTextDirection {

	private final LegendLabels labels;

	private final LegendTitle title;

	private final TextDirectioner textDirectioner;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LABELS("labels"),
		TITLE("title"),
		// simple properties
		FULL_WIDTH("fullWidth"),
		REVERSE("reverse"),
		RTL("rtl"),
		TEXT_DIRECTION("textDirection");

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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Legend(Options options, Key childKey, IsDefaultLegend defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// gets sub element
		this.labels = new LegendLabels(this, Property.LABELS, getDefaultValues().getLabels(), getValue(Property.LABELS));
		this.title = new LegendTitle(this, Property.TITLE, getDefaultValues().getTitle(), getValue(Property.TITLE));
		// creates text directioner
		this.textDirectioner = new TextDirectioner(getNativeObject(), this, defaultValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasTextDirection#getTextDirectioner()
	 */
	@Override
	public TextDirectioner getTextDirectioner() {
		return textDirectioner;
	}

	/**
	 * Returns the legend labels element.
	 * 
	 * @return the labels
	 */
	@Override
	public LegendLabels getLabels() {
		return labels;
	}

	/**
	 * Returns the legend title element.
	 * 
	 * @return the title
	 */
	@Override
	public LegendTitle getTitle() {
		return title;
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		setValue(Property.FULL_WIDTH, fullWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return <code>true</code> if marks that this box should take the full width of the canvas (pushing down other boxes).
	 */
	@Override
	public boolean isFullWidth() {
		return getValue(Property.FULL_WIDTH, getDefaultValues().isFullWidth());
	}

	/**
	 * Sets the legend will show datasets in reverse order.
	 * 
	 * @param reverse legend will show datasets in reverse order.
	 */
	public void setReverse(boolean reverse) {
		setValue(Property.REVERSE, reverse);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return <code>true</code> if legend will show datasets in reverse order.
	 */
	@Override
	public boolean isReverse() {
		return getValue(Property.REVERSE, getDefaultValues().isReverse());
	}

}