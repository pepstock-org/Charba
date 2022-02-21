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
package org.pepstock.charba.client.items;

import java.util.List;

import org.pepstock.charba.client.callbacks.CallbacksEnvelop;
import org.pepstock.charba.client.commons.AbstractReadOnlyPoint;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.enums.TooltipAlign;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip custom callback.<br>
 * The tooltip label configuration is nested below the tooltip configuration using the callbacks key.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TooltipModel extends AbstractReadOnlyPoint {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATA_POINTS("dataPoints"),
		X_ALIGN("xAlign"),
		Y_ALIGN("yAlign"),
		WIDTH("width"),
		HEIGHT("height"),
		CARET_X("caretX"),
		CARET_Y("caretY"),
		BODY("body"),
		BEFORE_BODY("beforeBody"),
		AFTER_BODY("afterBody"),
		TITLE("title"),
		FOOTER("footer"),
		LABEL_COLORS("labelColors"),
		LABEL_POINT_STYLES("labelPointStyles"),
		LABEL_TEXT_COLORS("labelTextColors"),
		OPACITY("opacity");

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
	 * Creates the item using envelop with the native java script object which contains all properties.
	 * 
	 * @param envelop envelop with the native java script object which contains all properties.
	 */
	public TooltipModel(ConfigurationEnvelop<NativeObject> envelop) {
		this(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the item using envelop with the native java script object which contains all properties.
	 * 
	 * @param envelop envelop with the native java script object which contains all properties.
	 */
	public TooltipModel(CallbacksEnvelop<NativeObject> envelop) {
		this(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	TooltipModel(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the list of tooltip items related to data points.
	 * 
	 * @return the list of tooltip items related to data points.
	 */
	public List<TooltipItem> getDataPoints() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.DATA_POINTS);
		// returns as list
		return ArrayListHelper.unmodifiableList(array, TooltipItem.FACTORY);
	}

	/**
	 * Returns the alignment of the tooltip caret in the X direction.
	 * 
	 * @return the alignment of the tooltip caret in the X direction
	 */
	public TooltipAlign getXAlign() {
		return getValue(Property.X_ALIGN, TooltipAlign.values(), TooltipAlign.AUTO);
	}

	/**
	 * Returns the alignment of the tooltip caret in the Y direction.
	 * 
	 * @return the alignment of the tooltip caret in the Y direction
	 */
	public TooltipAlign getYAlign() {
		return getValue(Property.Y_ALIGN, TooltipAlign.values(), TooltipAlign.AUTO);
	}

	/**
	 * Returns the width of tooltip.
	 * 
	 * @return the width of tooltip.
	 */
	public int getWidth() {
		return getValue(Property.WIDTH, Undefined.INTEGER);
	}

	/**
	 * Returns the height of tooltip.
	 * 
	 * @return the height of tooltip.
	 */
	public int getHeight() {
		return getValue(Property.HEIGHT, Undefined.INTEGER);
	}

	/**
	 * Returns the X location of the tooltip arrow.
	 * 
	 * @return the X location of the tooltip arrow.
	 */
	public int getCaretX() {
		return getValue(Property.CARET_X, Undefined.INTEGER);
	}

	/**
	 * Returns the Y location of the tooltip arrow.
	 * 
	 * @return the Y location of the tooltip arrow.
	 */
	public int getCaretY() {
		return getValue(Property.CARET_Y, Undefined.INTEGER);
	}

	/**
	 * Returns the list of items which belong to the tooltip body section.
	 * 
	 * @return the list of items which belong to the tooltip body section.
	 */
	public List<TooltipBodyItem> getBody() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.BODY);
		// returns as list
		return ArrayListHelper.unmodifiableList(array, TooltipBodyItem.FACTORY);
	}

	/**
	 * Returns the list of lines before body section.
	 * 
	 * @return the list of lines before body section.
	 */
	public List<String> getBeforeBody() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.BEFORE_BODY);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the list of lines after body section.
	 * 
	 * @return the list of lines after body section.
	 */
	public List<String> getAfterBody() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.AFTER_BODY);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the title of the tooltip.
	 * 
	 * @return the title of the tooltip.
	 */
	public List<String> getTitle() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.TITLE);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the footer of the tooltip.
	 * 
	 * @return the footer of the tooltip.
	 */
	public List<String> getFooter() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.FOOTER);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the list of labels color of tooltip.
	 * 
	 * @return the list of labels color of tooltip.
	 */
	public List<TooltipLabelColor> getLabelColors() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.LABEL_COLORS);
		// returns as list
		return ArrayListHelper.unmodifiableList(array, TooltipLabelColor.FACTORY);
	}

	/**
	 * Returns the list of labels point styles of tooltip.
	 * 
	 * @return the list of labels point styles of tooltip.
	 */
	public List<TooltipLabelPointStyle> getLabelPointStyles() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.LABEL_POINT_STYLES);
		// returns as list
		return ArrayListHelper.unmodifiableList(array, TooltipLabelPointStyle.FACTORY);
	}

	/**
	 * Returns the list of labels text color of tooltip.
	 * 
	 * @return the list of labels text color of tooltip.
	 */
	public List<TooltipLabelColor> getTextLabelColors() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.LABEL_TEXT_COLORS);
		// returns as list
		return ArrayListHelper.unmodifiableList(array, TooltipLabelColor.FACTORY);
	}

	/**
	 * Returns the opacity of tooltip.
	 * 
	 * @return the opacity of tooltip.
	 */
	public double getOpacity() {
		return getValue(Property.OPACITY, Undefined.DOUBLE);
	}

}