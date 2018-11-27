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

import java.util.Date;

import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.NativeDoubleDescriptor;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * Used for sparse datasets, such as those in scatter charts. Each data point is specified using an object containing x and y properties.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public final class DataPoint extends NativeObjectContainer<NativeDataPoint>{
	
	// default value for X
	private static final double DEFAULT_X = Double.NaN;
	// default value for Y
	private static final double DEFAULT_Y = Double.NaN;
	// default value for R
	private static final double DEFAULT_R = Double.NaN;
	
	public DataPoint() {
		this(new NativeDataPoint());
	}
	
	/**
	 * @param nativeObject
	 */
	DataPoint(NativeDataPoint nativeObject) {
		super(nativeObject);
	}

	
	/**
	 * Sets X value.
	 * @param x X value.
	 */
	public void setX(double x){
		getNativeObject().setX(x);
	}

	/**
	 * Returns X value.
	 * @return X value.
	 */
	public double getX(){
		return Checker.check(getNativeObject().getX(), DEFAULT_X);
	}

	/**
	 * Sets Y value.
	 * @param y Y value.
	 */
	public void setY(double y){
		getNativeObject().setY(y);
	}

	/** 
	 * Returns Y value.
	 * @return Y value.
	 */
	public double getY(){
		return Checker.check(getNativeObject().getY(), DEFAULT_Y);
	}

	/**
	 * Sets the bubble radius in pixels (not scaled).<br>
	 * It is not scaled by the chart, it is the raw radius in pixels of the bubble that is drawn on the canvas.
	 * @param r the bubble radius in pixels (not scaled).
	 */
	public void setR(double r){
		getNativeObject().setR(r);
	}

	/** 
	 * Returns the bubble radius in pixels (not scaled).
	 * @return the bubble radius in pixels (not scaled).
	 */
	public double getR(){
		return Checker.check(getNativeObject().getR(), DEFAULT_R);
	}

	/**
	 * Sets T value, is the date for time series.
	 * @param t T value.
	 */
	public void setT(Date t){
		if (t != null) {
			getNativeObject().setT(Checker.fromDate(t));
		}
	}

	/** 
	 * Returns T value, is the date for time series.
	 * @return T value. <code>null</code> is not set.
	 */
	public Date getT(){
		return Checker.toDate(getNativeObject().getT());
	}
	
	/**
	 * Sets a custom field to data point.
	 * @param key key of java script object to set.
	 * @param value value to set.
	 */
	public void setAttribute(String key, double value){
		  getNativeObject().setAttribute(key, value);
	}

	/** 
	 * Returns a custom field value from data point.
	 * @param key key of java script object to get.
	 * @return custom field value from data point. Default is {@link java.lang.Double#MIN_VALUE}.
	 */
	public double getAttribute(String key){
		NativeDoubleDescriptor descriptor = getNativeObject().getAttribute(key);
		if (descriptor != null) {
			return descriptor.getValue();
		}
		return Double.NaN;
	}
}