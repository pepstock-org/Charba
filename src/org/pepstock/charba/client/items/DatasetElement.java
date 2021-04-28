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
 * This is the CHART.JS item with all needed info about a selected data set.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DatasetElement extends NativeObjectContainer {

	// static instance for the data set item factory
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
		PARSED("parsed"),
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
		STOP("stop"),
		// line
		CONTROL_POINT_PREVIOUS_X("cp1x"),
		CONTROL_POINT_PREVIOUS_Y("cp1y"),
		CONTROL_POINT_NEXT_X("cp2x"),
		CONTROL_POINT_NEXT_Y("cp2y"),
		// radar
		ANGLE("angle");

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

	// data set item options instance
	private final DatasetElementOptions options;
	// parsed instance
	private final Parsed parsed;

	/**
	 * Creates the item using an envelop of the native java script object which contains all properties.
	 * 
	 * @param envelop envelop of the nativeObject native java script object which contains all properties.
	 */
	protected DatasetElement(CallbacksEnvelop<NativeObject> envelop) {
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
		// sets the data set item options
		this.options = new DatasetElementOptions(getValue(Property.OPTIONS));
		// gets parsed
		this.parsed = new Parsed(getValue(Property.PARSED));
	}

	/**
	 * Returns the data set item options.
	 *
	 * @return the data set item options.
	 */
	public final DatasetElementOptions getOptions() {
		return options;
	}

	/**
	 * Returns the parsed values.
	 *
	 * @return the parsed values.
	 */
	public final Parsed getParsed() {
		return parsed;
	}

	/**
	 * Returns if is an horizontal view.
	 * 
	 * @return <code>true</code> if is an horizontal view.
	 */
	public final boolean isHorizontal() {
		return getValue(Property.HORIZONTAL, Undefined.BOOLEAN);
	}

	/**
	 * Returns the base value of data set.
	 * 
	 * @return the base value of data set.
	 */
	public final double getBase() {
		return getValue(Property.BASE, Undefined.DOUBLE);
	}

	/**
	 * Returns the X location of data set item in pixel.
	 * 
	 * @return the X location of data set item in pixel.
	 */
	public final double getX() {
		return getValue(Property.X, Undefined.DOUBLE);
	}

	/**
	 * Returns the Y location of data set item in pixel.
	 * 
	 * @return the Y location of data set item in pixel.
	 */
	public final double getY() {
		return getValue(Property.Y, Undefined.DOUBLE);
	}

	/**
	 * Returns the width of data set item in pixel.
	 * 
	 * @return the width of data set item in pixel.
	 */
	public final double getWidth() {
		return getValue(Property.WIDTH, Undefined.DOUBLE);
	}

	/**
	 * Returns the height of data set item in pixel.
	 * 
	 * @return the height of data set item in pixel.
	 */
	public final double getHeight() {
		return getValue(Property.HEIGHT, Undefined.DOUBLE);
	}

	/**
	 * Returns <code>true</code> if skipped.
	 * 
	 * @return <code>true</code> if skipped.
	 */
	public final boolean isSkipped() {
		return getValue(Property.SKIP, Undefined.BOOLEAN);
	}

	/**
	 * Returns <code>true</code> if stopped.
	 * 
	 * @return <code>true</code> if stopped.
	 */
	public final boolean isStop() {
		return getValue(Property.STOP, Undefined.BOOLEAN);
	}

	/**
	 * Returns the previous X control point of data set item in pixel.
	 * 
	 * @return the previous X control point of data set item in pixel.
	 */
	public final double getControlPointPreviousX() {
		return getValue(Property.CONTROL_POINT_PREVIOUS_X, Undefined.DOUBLE);
	}

	/**
	 * Returns the previous Y control point of data set item in pixel.
	 * 
	 * @return the previous Y control point of data set item in pixel.
	 */
	public final double getControlPointPreviousY() {
		return getValue(Property.CONTROL_POINT_PREVIOUS_Y, Undefined.DOUBLE);
	}

	/**
	 * Returns the next X control point of data set item in pixel.
	 * 
	 * @return the next X control point of data set item in pixel.
	 */
	public final double getControlPointNextX() {
		return getValue(Property.CONTROL_POINT_NEXT_X, Undefined.DOUBLE);
	}

	/**
	 * Returns the next Y control point of data set item in pixel.
	 * 
	 * @return the next Y control point of data set item in pixel.
	 */
	public final double getControlPointNextY() {
		return getValue(Property.CONTROL_POINT_NEXT_Y, Undefined.DOUBLE);
	}

	/**
	 * Returns the angle of data set item.
	 * 
	 * @return the angle of data set item.
	 */
	public final double getAngle() {
		return getValue(Property.ANGLE, Undefined.DOUBLE);
	}

	/**
	 * Returns the start angle of data set item.
	 * 
	 * @return the start angle of data set item.
	 */
	public final double getStartAngle() {
		return getValue(Property.START_ANGLE, Undefined.DOUBLE);
	}

	/**
	 * Returns the end angle of data set item.
	 * 
	 * @return the end angle of data set item.
	 */
	public double getEndAngle() {
		return getValue(Property.END_ANGLE, Undefined.DOUBLE);
	}

	/**
	 * Returns the circumference of data set item.
	 * 
	 * @return the circumference of data set item.
	 */
	public double getCircumference() {
		return getValue(Property.CIRCUMFERENCE, Defaults.get().getGlobal().getCircumference());
	}

	/**
	 * Returns the outer radius of data set item in pixel.
	 * 
	 * @return the outer radius of data set item in pixel.
	 */
	public double getOuterRadius() {
		return getValue(Property.OUTER_RADIUS, Undefined.DOUBLE);
	}

	/**
	 * Returns the inner radius of data set item in pixel.
	 * 
	 * @return the inner radius of data set item in pixel.
	 */
	public double getInnerRadius() {
		return getValue(Property.INNER_RADIUS, Undefined.DOUBLE);
	}

	/**
	 * Inner class to create data set item by a native object.
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