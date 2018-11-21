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
package org.pepstock.charba.client.jsinterop.items;

import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Legend internal object to get data about ticks and their length in pixels.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class ScaleLongestTextCacheItem extends BaseBoxNodeItem {
	
	
	@JsProperty(name = "font")
	native String getNativeFont();
	
	@JsProperty(name = "garbageCollect")
	native ArrayString getNativeGarbageCollect();
	
	@JsProperty(name = "data")
	native NativeObject getNativeData();
	

	/**
	 * Returns the font of scale.
	 * 
	 * @return the font of scale. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}
	 */
	@JsOverlay
	public String getFont() {
		return Checker.check(getNativeFont(), UndefinedValues.STRING);
	}

	/**
	 * Returns the list of ticks in garbage collect item
	 * 
	 * @return the list of ticks in garbage collect item
	 */
	@JsOverlay
	public List<String> getGarbageCollect() {
		return ArrayListHelper.unmodifiableList(getNativeGarbageCollect());
	}

	/**
	 * Returns a map with all ticks and max lengths in pixel of ticks.<br>
	 * Key is the value of tick in string format, value is the max length in pixels.
	 * 
	 * @return a map with all ticks and max lengths in pixel of ticks.
	 */
	@JsOverlay
	public Map<String, Integer> getData() {
		// creates result
		return getNativeData().getObjectAsMap();
	}
}