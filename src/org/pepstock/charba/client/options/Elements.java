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

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultElements;

/**
 * Options can be configured for four different types of elements: arc, lines, points, and bars.<br>
 * When set, these options apply to the configuration attached to a dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Elements extends AbstractModel<Options, IsDefaultElements> implements IsDefaultElements {

	private final Arc arc;

	private final Line line;

	private final Point point;

	private final Bar bar;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POINT("point"),
		LINE("line"),
		BAR("bar"),
		ARC("arc");

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
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Elements(Options options, Key childKey, IsDefaultElements defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
		// gets all sub elements
		arc = new Arc(this, Property.ARC, defaultValues.getArc(), getValue(Property.ARC));
		line = new Line(this, Property.LINE, defaultValues.getLine(), getValue(Property.LINE));
		point = new Point(this, Property.POINT, defaultValues.getPoint(), getValue(Property.POINT));
		bar = new Bar(this, Property.BAR, defaultValues.getBar(), getValue(Property.BAR));
	}

	/**
	 * Returns the arc element.
	 * 
	 * @return the arc element
	 */
	@Override
	public Arc getArc() {
		return arc;
	}

	/**
	 * Returns the line element.
	 * 
	 * @return the line element
	 */
	@Override
	public Line getLine() {
		return line;
	}

	/**
	 * Returns the point element.
	 * 
	 * @return the point element
	 */
	@Override
	public Point getPoint() {
		return point;
	}

	/**
	 * Returns the bar element.
	 * 
	 * @return the bar element
	 */
	@Override
	public Bar getBar() {
		return bar;
	}

	/**
	 * Returns the options defined for a custom element.
	 * 
	 * @param <T> type of the options
	 * @param factory factory instance to create the element
	 * @return the options instance defined for a custom element.
	 */
	public <T extends NativeObjectContainer> T getElement(ElementFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// checks of element key is consistent
			Key elementKey = Key.checkAndGetIfValid(factory.getElementKey());
			// the element key MUST NOT be an existing element, like bar, line, arc and point
			Checker.assertCheck(!Key.hasKeyByValue(Property.values(), elementKey.value()), "Getting '" + elementKey.value() + "' element is not allowed by a factory");
			// gets the element options
			T result = factory.create(getValue(elementKey));
			// checks if the property doesn't exist because
			// it has to be added
			// if factory returns a null object,
			// it must not be stored
			if (result != null && !has(elementKey)) {
				// stored in the object
				setValueAndAddToParent(elementKey, result);
			}
			// returns element
			return result;
		}
		// if here the factory is not consistent
		// then returns null
		return null;
	}

}