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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.options.TickMajor;
import org.pepstock.charba.client.options.TickMinor;
import org.pepstock.charba.client.options.Ticks;

/**
 * Specific tick with minimum and maximum sub ticks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class Tick extends BaseTick<Ticks> {

	private final BaseTickMinor minor;

	private final BaseTickMajor major;

	/**
	 * Builds the object storing the axis which this tick belongs to.
	 * 
	 * @param axis axis which this tick belongs to.
	 */
	Tick(Axis axis) {
		super(axis, axis.getScale().getTicks());
		// creates sub element, min and max
		minor = new BaseTickMinor(axis, axis.getScale().getTicks().getMinor());
		major = new BaseTickMajor(axis, axis.getScale().getTicks().getMajor());
	}

	/**
	 * Returns minor tick element.
	 * 
	 * @return the minor
	 */
	public BaseTick<TickMinor> getMinor() {
		return minor;
	}

	/**
	 * Returns major tick element.
	 * 
	 * @return the major
	 */
	public BaseTick<TickMajor> getMajor() {
		return major;
	}

	/**
	 * If true, show tick marks.
	 * 
	 * @param display if true, show tick marks
	 */
	public void setDisplay(boolean display) {
		getConfiguration().setDisplay(display);
	}

	/**
	 * If true, show tick marks
	 * 
	 * @return if true, show tick marks.
	 */
	public boolean isDisplay() {
		return getConfiguration().isDisplay();
	}

	/**
	 * Sets the reverses order of tick labels.
	 * 
	 * @param reverse reverses order of tick labels.
	 */
	public void setReverse(boolean reverse) {
		getConfiguration().setReverse(reverse);
	}

	/**
	 * Returns the reverses order of tick labels.
	 * 
	 * @return reverses order of tick labels.
	 */
	public boolean isReverse() {
		return getConfiguration().isReverse();
	}
	
	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		getConfiguration().setLineHeight(lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		return getConfiguration().getLineHeight();
	}

	
}