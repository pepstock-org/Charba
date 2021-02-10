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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.CallbacksEnvelop;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.controllers.ControllersEnvelop;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a selected dataset.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DatasetElement extends NativeObjectContainer {

	// static instance for the dataset item factory
	static final DatasetItemFactory FACTORY = new DatasetItemFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// for all chart types
		ACTIVE("active"),
		X("x"),
		Y("y"),
		OPTIONS("options"),
		// for bar chart
		BASE("base"),
		HEIGHT("height"),
		HORIZONTAL("horizontal"),
		WIDTH("width"),
		// doughnut, pie, polar area
		CIRCUMFERENCE("circumference"),
		END_ANGLE("endAngle"),
		INNER_RADIUS("innerRadius"),
		OUTER_RADIUS("outerRadius"),
		START_ANGLE("startAngle"),
		// bubble, line, radar
		SKIP("skip"),
		// line
		CONTROL_POINT_PREVIOUS_X("controlPointPreviousX"),
		CONTROL_POINT_PREVIOUS_Y("controlPointPreviousY"),
		CONTROL_POINT_NEXT_X("controlPointNextX"),
		CONTROL_POINT_NEXT_Y("controlPointNextY"),
		// radar
		ANGLE("angle");

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

	// dataset item options instance
	private final DatasetElementOptions options;

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param envelop envelop of native java script object which contains all properties.
	 */
	public DatasetElement(CallbacksEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the item using an envelop of the native java script object which contains all properties.
	 * 
	 * @param envelop envelop of the nativeObject native java script object which contains all properties.
	 */
	protected DatasetElement(ControllersEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	DatasetElement(NativeObject nativeObject) {
		super(nativeObject);
		// sets the dataset item options
		options = new DatasetElementOptions(getValue(Property.OPTIONS));
	}

	/**
	 * Returns the dataset item options.
	 *
	 * @return the dataset item options.
	 */
	public final DatasetElementOptions getOptions() {
		return options;
	}

	/**
	 * Returns if is an horizontal view.
	 * 
	 * @return <code>true</code> if is an horizontal view.
	 */
	public final boolean isHorizontal() {
		return getValue(Property.HORIZONTAL, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the base value of dataset.
	 * 
	 * @return the base value of dataset.
	 */
	public final double getBase() {
		return getValue(Property.BASE, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the X location of dataset item in pixel.
	 * 
	 * @return the X location of dataset item in pixel.
	 */
	public final double getX() {
		return getValue(Property.X, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the Y location of dataset item in pixel.
	 * 
	 * @return the Y location of dataset item in pixel.
	 */
	public final double getY() {
		return getValue(Property.Y, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the width of dataset item in pixel.
	 * 
	 * @return the width of dataset item in pixel.
	 */
	public final double getWidth() {
		return getValue(Property.WIDTH, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the height of dataset item in pixel.
	 * 
	 * @return the height of dataset item in pixel.
	 */
	public final double getHeight() {
		return getValue(Property.HEIGHT, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns <code>true</code> if skipped.
	 * 
	 * @return <code>true</code> if skipped.
	 */
	public final boolean isSkipped() {
		return getValue(Property.SKIP, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the previous X control point of dataset item in pixel.
	 * 
	 * @return the previous X control point of dataset item in pixel.
	 */
	public final double getControlPointPreviousX() {
		return getValue(Property.CONTROL_POINT_PREVIOUS_X, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the previous Y control point of dataset item in pixel.
	 * 
	 * @return the previous Y control point of dataset item in pixel.
	 */
	public final double getControlPointPreviousY() {
		return getValue(Property.CONTROL_POINT_PREVIOUS_Y, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the next X control point of dataset item in pixel.
	 * 
	 * @return the next X control point of dataset item in pixel.
	 */
	public final double getControlPointNextX() {
		return getValue(Property.CONTROL_POINT_NEXT_X, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the next Y control point of dataset item in pixel.
	 * 
	 * @return the next Y control point of dataset item in pixel.
	 */
	public final double getControlPointNextY() {
		return getValue(Property.CONTROL_POINT_NEXT_Y, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the angle of dataset item.
	 * 
	 * @return the angle of dataset item.
	 */
	public final double getAngle() {
		return getValue(Property.ANGLE, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the start angle of dataset item.
	 * 
	 * @return the start angle of dataset item.
	 */
	public final double getStartAngle() {
		return getValue(Property.START_ANGLE, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the end angle of dataset item.
	 * 
	 * @return the end angle of dataset item.
	 */
	public double getEndAngle() {
		return getValue(Property.END_ANGLE, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the circumference of dataset item.
	 * 
	 * @return the circumference of dataset item.
	 */
	public double getCircumference() {
		return getValue(Property.CIRCUMFERENCE, Defaults.get().getGlobal().getCircumference());
	}

	/**
	 * Returns the outer radius of dataset item in pixel.
	 * 
	 * @return the outer radius of dataset item in pixel.
	 */
	public double getOuterRadius() {
		return getValue(Property.OUTER_RADIUS, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the inner radius of dataset item in pixel.
	 * 
	 * @return the inner radius of dataset item in pixel.
	 */
	public double getInnerRadius() {
		return getValue(Property.INNER_RADIUS, UndefinedValues.DOUBLE);
	}

	/**
	 * Inner class to create dataset item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class DatasetItemFactory implements NativeObjectContainerFactory<DatasetElement> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons. NativeObject)
		 */
		@Override
		public DatasetElement create(NativeObject nativeObject) {
			return new DatasetElement(nativeObject);
		}
	}

}