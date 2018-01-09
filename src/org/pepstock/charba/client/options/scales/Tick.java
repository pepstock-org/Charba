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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.Key;

/**
 * Specific tick with min and max sub ticks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class Tick extends BaseTick {

	private final BaseTick minor = new BaseTick();

	private final BaseTick major = new BaseTick();

	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		display,
		reverse,
		minor,
		major
	}

	/**
	 * Builds the object
	 */
	Tick() {
		// sets java script properties
		setValue(Property.minor, minor);
		setValue(Property.major, major);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.scales.BaseTick#setAxis(org.pepstock.charba.client.options.scales.Axis)
	 */
	@Override
	void setAxis(Axis axis) {
		super.setAxis(axis);
		// stores the axis instance to sub tick
		minor.setAxis(axis);
		major.setAxis(axis);
	}

	/**
	 * @return the minor
	 */
	public BaseTick getMinor() {
		return minor;
	}

	/**
	 * @return the major
	 */
	public BaseTick getMajor() {
		return major;
	}

	/**
	 * If true, show tick marks.
	 * 
	 * @param display if true, show tick marks
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	/**
	 * If true, show tick marks
	 * 
	 * @return if true, show tick marks. Default is {@link org.pepstock.charba.client.defaults.scale.Ticks#isDisplay()}.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, Defaults.getScale().getTicks().isDisplay());
	}

	/**
	 * Sets the reverses order of tick labels.
	 * 
	 * @param reverse reverses order of tick labels.
	 */
	public void setReverse(boolean reverse) {
		setValue(Property.reverse, reverse);
	}

	/**
	 * Returns the reverses order of tick labels.
	 * 
	 * @return reverses order of tick labels. Default is {@link org.pepstock.charba.client.defaults.scale.Ticks#isReverse()}.
	 */
	public boolean isReverse() {
		return getValue(Property.reverse, Defaults.getScale().getTicks().isReverse());
	}

}