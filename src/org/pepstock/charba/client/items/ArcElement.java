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
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Maps the out-of-the-box CHART.JS element used to represents arcs on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ArcElement extends ChartElement {

	/**
	 * ARC element type.
	 */
	public static final String TYPE = "arc";
	/**
	 * Static instance for the ARC element factory
	 */
	public static final ChartElementFactory<ArcElement, ArcElementOptions> FACTORY = new ArcElementFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CIRCUMFERENCE("circumference"),
		END_ANGLE("endAngle"),
		INNER_RADIUS("innerRadius"),
		OUTER_RADIUS("outerRadius"),
		START_ANGLE("startAngle"),
		// bubble, line, radar
		SKIP("skip"),
		STOP("stop");

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
	 * Creates the elemet using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ArcElement(NativeObject nativeObject) {
		super(TYPE, nativeObject);
	}

	/**
	 * Returns the element options.
	 *
	 * @return the element options.
	 */
	@Override
	public ArcElementOptions getOptions() {
		return (ArcElementOptions) super.getOptions();
	}

	/**
	 * Returns the start angle of data set item.
	 * 
	 * @return the start angle of data set item.
	 */
	public double getStartAngle() {
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
	 * Inner class to create ARC data element by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class ArcElementFactory implements ChartElementFactory<ArcElement, ArcElementOptions> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ArcElement create(NativeObject nativeObject) {
			return new ArcElement(nativeObject);
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ChartElementFactory#createOptions(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ArcElementOptions createOptions(NativeObject nativeObject) {
			return new ArcElementOptions(nativeObject);
		}

	}

}