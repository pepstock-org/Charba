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

import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.events.ChartNativeEvent;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some
 * utilities to act on java script objects for CHARBA items.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsItemsHelper {
	// static instance for singleton
	private static final JsItemsHelper INSTANCE = new JsItemsHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsItemsHelper() {
		// to be sure that CHARBA java script object is injected
		// invoking the JsHelper
		JsHelper.get();
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return helper instance.
	 */
	static JsItemsHelper get() {
		return INSTANCE;
	}

	/**
	 * Returns <code>true</code> if the property into native object is a {@link CanvasPattern}.
	 * 
	 * @param object the legend item on which to check the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return <code>true</code> if the property into native object is a {@link CanvasPattern}
	 */
	boolean isCanvasPattern(LegendItem object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			return NativeJsItemsHelper.isCanvasPattern(object.nativeObject(), key);
		}
		// if here, arguments not consistent
		return false;
	}

	/**
	 * Returns <code>true</code> if the property into native object is a {@link CanvasGradient}.
	 * 
	 * @param object the legend item on which to check the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return <code>true</code> if the property into native object is a {@link CanvasGradient}
	 */
	boolean isCanvasGradient(LegendItem object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			return NativeJsItemsHelper.isCanvasGradient(object.nativeObject(), key);
		}
		// if here, arguments not consistent
		return false;
	}

	/**
	 * Returns a chart native event from CHART.JS event.
	 * 
	 * @param event CHART.JS event
	 * @param key key of java script object
	 * @return a chart native event
	 */
	ChartNativeEvent nativeEvent(NativeObject event, String key) {
		// checks consistency of arguments
		if (event != null && key != null) {
			return NativeJsItemsHelper.nativeEvent(event, key);
		}
		// if here, arguments not consistent
		return null;
	}

}
