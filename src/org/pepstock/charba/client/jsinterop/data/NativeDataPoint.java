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
package org.pepstock.charba.client.jsinterop.data;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeDescriptor;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import com.google.gwt.core.client.JsDate;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Used for sparse datasets, such as those in scatter charts. Each data point is specified using an object containing x and y properties.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public final class NativeDataPoint extends NativeObject{
	
	/**
	 * Sets X value.
	 * @param x X value.
	 */
	@JsProperty(name = "x")
	native void setX(double x);

	/**
	 * Returns X value.
	 * @return X value.
	 */
	@JsProperty(name = "x")
	native double getX();


	/**
	 * Sets Y value.
	 * @param y Y value.
	 */
	@JsProperty(name = "y")
	native void setY(double y);

	/**
	 * Returns Y value.
	 * @return Y value.
	 */
	@JsProperty(name = "y")
	native double getY();

	/**
	 * Sets the bubble radius in pixels (not scaled).<br>
	 * It is not scaled by the chart, it is the raw radius in pixels of the bubble that is drawn on the canvas.
	 * @param r the bubble radius in pixels (not scaled).
	 */
	@JsProperty(name = "r")
	native void setR(double r);

	/** 
	 * Returns the bubble radius in pixels (not scaled).
	 * @return the bubble radius in pixels (not scaled).
	 */
	@JsProperty(name = "r")
	native double getR();

	/**
	 * Sets T value, is the date for time series.
	 * @param t T value.
	 */
	@JsProperty(name = "t")
	native void setT(JsDate t);

	/** 
	 * Returns T value, is the date for time series.
	 * @return T value. <code>null</code> is not set.
	 */
	@JsProperty(name = "t")
	native JsDate getT();

	/**
	 * Sets a custom field to data point.
	 * @param key key of java script object to set.
	 * @param value value to set.
	 */
	@JsOverlay
	void setAttribute(Key key, double value){
		defineProperty(this, key, value);
	}

	/** 
	 * Returns a custom field value from data point.
	 * @param key key of java script object to get.
	 * @return custom field value from data point. Default is {@link java.lang.Double#MIN_VALUE}.
	 */
	@JsOverlay
	NativeDescriptor<Double> getAttribute(Key key){
		return getProperty(this, key);
	}
}