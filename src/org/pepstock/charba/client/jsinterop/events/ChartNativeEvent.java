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
package org.pepstock.charba.client.jsinterop.events;

import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;

import com.google.gwt.dom.client.NativeEvent;

/**
 * This event extends the native DOM event of GWT.<br>
 * It has been created mapping the native event provided by CHART.JS interfaces.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public class ChartNativeEvent extends NativeEvent {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		layerX,
		layerY
	}

	/**
	 * Needed for GWt injection
	 */
	protected ChartNativeEvent() {
		// do nothing
	}

	/**
	 * Returns the layer X property
	 * 
	 * @return the layer X property
	 */
	public final int getLayerX() {
		return JsHelper.get().propertyAsInt(this, Property.layerX.name());
	}

	/**
	 * Returns the layer Y property
	 * 
	 * @return the layer Y property
	 */
	public final int getLayerY() {
		return JsHelper.get().propertyAsInt(this, Property.layerY.name());
	}

}