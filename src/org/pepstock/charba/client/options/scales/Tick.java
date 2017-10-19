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

import org.pepstock.charba.client.commons.Key;

/**
 * 
 */
abstract class Tick extends BaseTick{
	
	private static final boolean DEFAULT_DISPLAY = true;
	
	private static final boolean DEFAULT_REVERSE = false;
	
	private final BaseTick minor = new BaseTick();
	
	private final BaseTick major = new BaseTick();
	
	enum Property implements Key {
		display,
		reverse,
		minor,
		major
	}
	
	
//	display 	Boolean 	true 	If true, show tick marks
//	reverse 	Boolean 	false 	Reverses order of tick labels.
//	minor 	object 	{} 	Minor ticks configuration. Ommited options are inherited from options above.
//	major 	object 	{} 	Major ticks configuration. Ommited options are inherited from options above.
//	
	
	Tick() {
		setValue(Property.minor, minor);
		setValue(Property.major, major);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.options.scales.BaseTick#setAxis(org.pepstock.charba.client.options.scales.Axis)
	 */
	@Override
	void setAxis(Axis axis) {
		super.setAxis(axis);
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

	public void setDisplay(boolean display) {
		setValue(Property.display, display);
	}

	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}
    
	public void setReverse(boolean reverse) {
		setValue(Property.reverse, reverse);
	}

	public boolean isReverse() {
		return getValue(Property.reverse, DEFAULT_REVERSE);
	}

}