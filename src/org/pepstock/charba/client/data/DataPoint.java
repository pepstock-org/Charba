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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * Used for sparse datasets, such as those in scatter charts. Each data point is specified using an object containing x and y properties.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public final class DataPoint extends JavaScriptObjectContainer{
	
	// default value for X
	private static final double DEFAULT_X = Double.MIN_VALUE;
	// default value for Y
	private static final double DEFAULT_Y = Double.MIN_VALUE;

	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key {
		x,
		y
	}
	
	/**
	 * Sets X value.
	 * @param x X value.
	 */
	public void setX(double x){
		  setValue(Property.x, x);
	}

	/**
	 * Returns X value.
	 * @return X value.
	 */
	public double getX(){
		return getValue(Property.x, DEFAULT_X);
	}

	/**
	 * Sets Y value.
	 * @param y Y value.
	 */
	public void setY(double y){
		  setValue(Property.y, y);
	}

	/** 
	 * Returns Y value.
	 * @return Y value.
	 */
	public double getY(){
		return getValue(Property.y, DEFAULT_Y);
	}

}