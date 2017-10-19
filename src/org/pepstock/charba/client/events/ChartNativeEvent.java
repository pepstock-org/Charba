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
package org.pepstock.charba.client.events;

import org.pepstock.charba.client.commons.Key;

import com.google.gwt.dom.client.NativeEvent;

/**
 * This event extends the native DOM event of GWT.<br>
 * It has been created mapping the native event provided by CHART.JS interfaces.
 * 
 * @author Andrea "Stock" Stocchero
 * @see com.google.gwt.dom.client.NativeEvent
 */
public class ChartNativeEvent extends NativeEvent {

	/**
	 * Name of fields of JavaScript object. 
	 */
	private enum Property implements Key{
		layerX,
		layerY
	}
    /** 
     * Needed for GWt injection
     */
	protected ChartNativeEvent() {
		// do notnihg
	}

	/**
	 * Returns the layer X property
	 * @return the layer X property
	 */
	public final int getLayerX() {
		return getInt(Property.layerX.name());
	}

	/**
	 * Returns the layer Y property
	 * @return the layer Y property
	 */
	public final int getLayerY() {
		return getInt(Property.layerY.name());
	}

	/**
	 * Returns the string representation of the object content.
	 * @return the string representation of the object content.
	 */
	public final String toContentString() {
		return "ChartEvent [getLayerX()=" + getLayerX() + ", getLayerY()=" + getLayerY() + ", toString()=" + super.toString() + "]";
	}

	/**
	 * Native method to get the integer value of layers properties.
	 * @param key name of JavaScript object field
	 * @return integer value of the property
	 */
	private final native int getInt(String key)/*-{
    	return this[key];
	}-*/;
	
}