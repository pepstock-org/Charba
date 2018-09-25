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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Wrapper of legend node of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public final class LegendNode extends BaseBoxNodeItem {
	
	/**
	 * Returns if it is in doughnut mode.
	 * 
	 * @return <code>true</code> it is in doughnut mode. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsProperty(name = "doughnutMode")
	native boolean isNativeDoughnutMode();
	/**
	 * Returns the list of line widths.
	 * 
	 * @return the list of line widths.
	 */
	@JsProperty(name = "lineWidths")
	native ArrayInteger getNativeLineWidths();
	/**
	 * Returns the list of columns widths.
	 * 
	 * @return the list of columns widths.
	 */
	@JsProperty(name = "columnWidths")
	native ArrayInteger getNativeColumnWidths();

	/**
	 * Returns the list of hit boxes of the legend.
	 * 
	 * @return the list of hit boxes of the legend.
	 */
	@JsProperty(name = "legendHitBoxes")
	native ArrayObject<LegendHitBoxItem> getNativeHitBoxes();
	/**
	 * Returns the list of items of the legend.
	 * 
	 * @return the list of items of the legend.
	 */
	@JsProperty(name = "legendItems")
	native ArrayObject<LegendItem> getNativeItems();

	/**
	 * Returns if it is in doughnut mode.
	 * 
	 * @return <code>true</code> it is in doughnut mode. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public boolean isDoughnutMode() {
		return AssignHelper.check(isNativeDoughnutMode(), UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the list of line widths.
	 * 
	 * @return the list of line widths.
	 */
	@JsOverlay
	public List<Integer> getLineWidths() {
		List<Integer> result = ArrayListHelper.build(getNativeLineWidths());
		return result == null ? Collections.unmodifiableList(new ArrayList<Integer>()) : Collections.unmodifiableList(result);
	}

	/**
	 * Returns the list of columns widths.
	 * 
	 * @return the list of columns widths.
	 */
	@JsOverlay
	public List<Integer> getColumnWidths() {
		List<Integer> result = ArrayListHelper.build(getNativeColumnWidths());
		return result == null ? Collections.unmodifiableList(new ArrayList<Integer>()) : Collections.unmodifiableList(result);
	}

	/**
	 * Returns the list of hit boxes of the legend.
	 * 
	 * @return the list of hit boxes of the legend.
	 */
	@JsOverlay
	public List<LegendHitBoxItem> getHitBoxes() {
		List<LegendHitBoxItem> result = ArrayListHelper.build(getNativeHitBoxes());
		return result == null ? Collections.unmodifiableList(new ArrayList<LegendHitBoxItem>()) : Collections.unmodifiableList(result);
	}

	/**
	 * Returns the list of items of the legend.
	 * 
	 * @return the list of items of the legend.
	 */
	@JsOverlay
	public List<LegendItem> getItems() {
		List<LegendItem> result = ArrayListHelper.build(getNativeItems());
		return result == null ? Collections.unmodifiableList(new ArrayList<LegendItem>()) : Collections.unmodifiableList(result);
	}
}