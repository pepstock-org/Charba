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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.jsinterop.options.TickMajor;
import org.pepstock.charba.client.jsinterop.options.TickMinor;
import org.pepstock.charba.client.jsinterop.options.Ticks;

/**
 * Specific tick with min and max sub ticks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class Tick extends AbstractTick<Ticks> {
	
//	private final Axis axis;

	private final BaseTickMinor minor;

	private final BaseTickMajor major;

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Tick(Axis axis) {
		super(axis, axis.getScale().getTicks());
		minor = new BaseTickMinor(axis, axis.getScale().getTicks().getMinor());
		major = new BaseTickMajor(axis, axis.getScale().getTicks().getMajor());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.configuration.AbstractTick#getTick()
	 */
	@Override
	Ticks getDefaultTick() {
		return getAxis().getDefaultScale().getTicks();
	}

	/**
	 * @return the minor
	 */
	public AbstractTick<TickMinor> getMinor() {
		return minor;
	}

	/**
	 * @return the major
	 */
	public AbstractTick<TickMajor> getMajor() {
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

}