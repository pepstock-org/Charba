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

	private final TextDirectionHandler textDirectionHandler;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LABELS("labels"),
		TITLE("title"),
		// simple properties
		MAX_WIDTH("maxWidth"),
		MAX_HEIGHT("maxWidth"),
		FULL_SIZE("fullSize"),
		REVERSE("reverse"),
		RTL("rtl"),
		TEXT_DIRECTION("textDirection");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
	 * @param options plugins options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Legend(Plugins options, Key childKey, IsDefaultLegend defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// gets sub element
		this.labels = new LegendLabels(this, Property.LABELS, getDefaultValues().getLabels(), getValue(Property.LABELS));
		this.title = new LegendTitle(this, Property.TITLE, getDefaultValues().getTitle(), getValue(Property.TITLE));
		// creates text direction handler
		this.textDirectionHandler = new TextDirectionHandler(this, getDefaultValues(), getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasTextDirection#getTextDirectionHandler()
	 */
	@Override
	public TextDirectionHandler getTextDirectionHandler() {
		return textDirectionHandler;
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
	 * Sets the maximum width of the legend, in pixels.
	 * 
	 * @param maxWidth the maximum width of the legend, in pixels
	 */
	public void setMaxWidth(int maxWidth) {
		setValueAndAddToParent(Property.MAX_WIDTH, maxWidth);
	}

	/**
	 * Returns the maximum width of the legend, in pixels.
	 * 
	 * @return the maximum width of the legend, in pixels
	 */
	@Override
	public int getMaxWidth() {
		return getValue(Property.MAX_WIDTH, getDefaultValues().getMaxWidth());
	}

	/**
	 * Sets the maximum height of the legend, in pixels.
	 * 
	 * @param maxHeight the maximum height of the legend, in pixels
	 */
	public void setMaxHeight(int maxHeight) {
		setValueAndAddToParent(Property.MAX_HEIGHT, maxHeight);
	}

	/**
	 * Returns the maximum width of the legend, in pixels.
	 * 
	 * @return the maximum width of the legend, in pixels
	 */
	@Override
	public int getMaxHeight() {
		return getValue(Property.MAX_HEIGHT, getDefaultValues().getMaxWidth());
	}

	/**
	 * Marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @param fullSize Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	public void setFullSize(boolean fullSize) {
		setValueAndAddToParent(Property.FULL_SIZE, fullSize);
	}

	/**
	 * Returns if that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return <code>true</code> if that this box should take the full width/height of the canvas (moving other boxes).
	 */
	@Override
	public boolean isFullSize() {
		return getValue(Property.FULL_SIZE, getDefaultValues().isFullSize());
	}

	/**
	 * Sets the legend will show datasets in reverse order.
	 * 
	 * @param reverse legend will show datasets in reverse order.
	 */
	public void setReverse(boolean reverse) {
		setValueAndAddToParent(Property.REVERSE, reverse);
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