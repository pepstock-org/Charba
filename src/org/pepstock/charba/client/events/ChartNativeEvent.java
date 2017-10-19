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

public class ChartNativeEvent extends NativeEvent {

//	click { target: <canvas>, buttons: 0, clientX: 400, clientY: 101, layerX: 137, layerY: 91 }
	
	private enum Property implements Key{
		layerX,
		layerY
	}
    /** 
     * Needed for GWt injection
     */
	protected ChartNativeEvent() {
		
	}

	public final int getLayerX() {
		return getInt(Property.layerX.name());
	}

	public final int getLayerY() {
		return getInt(Property.layerY.name());
	}

	public final String toContentString() {
		return "ChartEvent [getLayerX()=" + getLayerX() + ", getLayerY()=" + getLayerY() + ", toString()=" + super.toString() + "]";
	}

	protected final native int getInt(String key)/*-{
    	return this[key];
	}-*/;
	
}