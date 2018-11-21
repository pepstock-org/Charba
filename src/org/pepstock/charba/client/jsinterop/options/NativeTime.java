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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import com.google.gwt.core.client.JsDate;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The following display formats are used to configure how different time units are formed into strings for the axis tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
final class NativeTime extends NativeObject {

	/**
	 * FIXME
	 */
	@JsProperty
	native NativeDisplayFormats getDisplayFormats();

	/**
	 * FIXME
	 */
	@JsProperty
	native void setDisplayFormats(NativeDisplayFormats displayFormats);

	/**
	 * If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 * 
	 * @param isoWeekday If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it
	 *            will be Sunday.
	 */
	@JsProperty
	native void setIsoWeekday(boolean isoWeekday);

	/**
	 * If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be Sunday.
	 * 
	 * @return If true and the unit is set to 'week', then the first day of the week will be Monday. Otherwise, it will be
	 *         Sunday.
	 */
	@JsProperty
	native boolean isIsoWeekday();

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @param max If defined, this will override the data maximum.
	 */
	@JsProperty
	native void setMax(JsDate max);

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @return If defined, this will override the data maximum.
	 */
	@JsProperty
	native JsDate getMax();

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @param min If defined, this will override the data minimum.
	 */
	@JsProperty
	native void setMin(JsDate min);

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @return If defined, this will override the data minimum.
	 */
	@JsProperty
	native JsDate getMin();

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @param round If defined, this will override the data minimum.
	 */
	@JsProperty
	native void setRound(String round);
	
	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @param round If defined, this will override the data minimum.
	 */
	@JsOverlay
	final void removeRound() {
		removeProperty(Time.Property.round);
	}

	/**
	 * If defined, dates will be rounded to the start of this unit.
	 * 
	 * @return If defined, dates will be rounded to the start of this unit.
	 */
	@JsProperty
	native String getRound();

	/**
	 * The moment js format string to use for the tooltip.
	 * 
	 * @param tooltipFormat The moment js format string to use for the tooltip.
	 */
	@JsProperty
	native void setTooltipFormat(String tooltipFormat);

	/**
	 * The moment js format string to use for the tooltip.
	 * 
	 * @return The moment js format string to use for the tooltip.
	 */
	@JsProperty
	native String getTooltipFormat();

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @param unit If defined, will force the unit to be a certain type.
	 */
	@JsProperty
	native void setUnit(String unit);
	
	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @param unit If defined, will force the unit to be a certain type..
	 */
	@JsOverlay
	final void removeUnit() {
		removeProperty(Time.Property.unit);
	}

	/**
	 * If defined, will force the unit to be a certain type.
	 * 
	 * @return If defined, will force the unit to be a certain type.
	 */
	@JsProperty
	native String getUnit();

	/**
	 * The number of units between grid lines.
	 * 
	 * @param stepSize The number of units between grid lines.
	 */
	@JsProperty
	native void setStepSize(int stepSize);

	/**
	 * The number of units between grid lines.
	 * 
	 * @return The number of units between grid lines.
	 */
	@JsProperty
	native int getStepSize();

	/**
	 * The minimum display format to be used for a time unit.
	 * 
	 * @param unit The minimum display format to be used for a time unit.
	 */
	@JsProperty
	native void setMinUnit(String unit);

	/**
	 * The minimum display format to be used for a time unit.
	 * 
	 * @return The minimum display format to be used for a time unit. 
	 */
	@JsProperty
	native String getMinUnit();

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @param parser Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 */
	@JsProperty
	native void setParser(String parser);

	/**
	 * Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 * 
	 * @return Defined as a string, it is interpreted as a custom format to be used by moment to parse the date.
	 */
	@JsProperty
	native String getParser();
}