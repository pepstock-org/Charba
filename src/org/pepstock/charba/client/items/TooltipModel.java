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

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.TooltipBodyItem.TooltipBodyItemFactory;
import org.pepstock.charba.client.items.TooltipItem.TooltipItemFactory;
import org.pepstock.charba.client.items.TooltipLabelColor.TooltipLabelColorFactory;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip custom callback.<br>
 * The tooltip label configuration is nested below the tooltip configuration using the callbacks key.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TooltipModel extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{		
		DATA_POINTS("dataPoints"),
		X_ALIGN("xAlign"),
		Y_ALIGN("yAlign"),
		X("x"),
		Y("y"),
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
		LABEL_TEXT_COLORS("labelTextColors"),
		OPACITY("opacity");
		
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

	// instance of tooltip items factory
	private final TooltipItemFactory tooltipItemFactory = new TooltipItemFactory();
	// instance of tooltip body items factory
	private final TooltipBodyItemFactory tooltipBodyItemFactory = new TooltipBodyItemFactory();
	// instance of tooltip label color factory
	private final TooltipLabelColorFactory tooltipLabelColorFactory = new TooltipLabelColorFactory();

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public TooltipModel(NativeObject nativeObject) {
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
		return ArrayListHelper.unmodifiableList(array, tooltipItemFactory);
	}

	/**
	 * Returns the X align location.
	 * 
	 * @return the X align location. Default is {@link UndefinedValues#STRING}.
	 */
	public String getXAlign() {
		return getValue(Property.X_ALIGN, UndefinedValues.STRING);
	}

	/**
	 * Returns the Y align location.
	 * 
	 * @return the Y align location. Default is {@link UndefinedValues#STRING}.
	 */
	public String getYAlign() {
		return getValue(Property.Y_ALIGN, UndefinedValues.STRING);
	}

	/**
	 * Returns the X location of tooltip.
	 * 
	 * @return the X location of tooltip. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getX() {
		return getValue(Property.X, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of tooltip.
	 * 
	 * @return the Y location of tooltip. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getY() {
		return getValue(Property.Y, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the width of tooltip.
	 * 
	 * @return the width of tooltip. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getWidth() {
		return getValue(Property.WIDTH, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height of tooltip.
	 * 
	 * @return the height of tooltip. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getHeight() {
		return getValue(Property.HEIGHT, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X location of the tooltip arrow.
	 * 
	 * @return the X location of the tooltip arrow. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getCaretX() {
		return getValue(Property.CARET_X, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of the tooltip arrow.
	 * 
	 * @return the Y location of the tooltip arrow. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getCaretY() {
		return getValue(Property.CARET_Y, UndefinedValues.INTEGER);
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
		return ArrayListHelper.unmodifiableList(array, tooltipBodyItemFactory);
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
		return ArrayListHelper.unmodifiableList(array, tooltipLabelColorFactory);
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
		return ArrayListHelper.unmodifiableList(array, tooltipLabelColorFactory);
	}

	/**
	 * Returns the opacity of tooltip.
	 * 
	 * @return the opacity of tooltip. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getOpacity() {
		return getValue(Property.OPACITY, UndefinedValues.DOUBLE);
	}

}