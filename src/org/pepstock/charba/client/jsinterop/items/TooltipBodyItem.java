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

import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip body.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.items.TooltipModel
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
public final class TooltipBodyItem extends NativeObject {
	
	@JsProperty(name = "before")
	native ArrayString getNativeBefore();
	
	@JsProperty(name = "lines")
	native ArrayString getNativeLines();
	
	@JsProperty(name = "after")
	native ArrayString getNativeAfter();

	/**
	 * Returns text to render before the body section.
	 * 
	 * @return text to render before the body section.
	 */
	@JsOverlay
	public List<String> getBefore() {
		return ArrayListHelper.unmodifiableList(getNativeBefore());
	}

	/**
	 * Returns all lines of body section.
	 * 
	 * @return all lines of body section.
	 */
	@JsOverlay
	public List<String> getLines() {
		return ArrayListHelper.unmodifiableList(getNativeLines());
	}

	/**
	 * Returns text to render after the body section
	 * 
	 * @return text to render after the body section
	 */
	@JsOverlay
	public List<String> getAfter() {
		return ArrayListHelper.unmodifiableList(getNativeAfter());
	}

}