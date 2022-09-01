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

import org.pepstock.charba.client.callbacks.CallbacksEnvelop;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * FIXME Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a selected data set.<br>
 * This object has been created and passed to event handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class PointElement extends ChartElement {

	/**
	 * POINT element type.
	 */
	public static final String TYPE = "point";
	/**
	 * Static instance for the POINT element factory
	 */
	public static final ChartElementFactory<PointElement> FACTORY = new PointElementFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANGLE("angle"),
		CONTROL_POINT_PREVIOUS_X("cp1x"),
		CONTROL_POINT_PREVIOUS_Y("cp1y"),
		CONTROL_POINT_NEXT_X("cp2x"),
		CONTROL_POINT_NEXT_Y("cp2y"),
		PARSED("parsed");

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

	// parsed instance
	private final Parsed parsed;

	/**
	 * Creates the item using an envelop of the native java script object which contains all properties.
	 * 
	 * @param envelop envelop of the nativeObject native java script object which contains all properties.
	 */
	protected PointElement(CallbacksEnvelop<NativeObject> envelop) {
		this(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	PointElement(NativeObject nativeObject) {
		super(nativeObject);
		// gets parsed
		this.parsed = new Parsed(getValue(Property.PARSED));
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
	 * Inner class to create data set item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class PointElementFactory implements ChartElementFactory<PointElement> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public PointElement create(NativeObject nativeObject) {
			return new PointElement(nativeObject);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ElementFactory#getType()
		 */
		@Override
		public String getType() {
			return TYPE;
		}
	}

}