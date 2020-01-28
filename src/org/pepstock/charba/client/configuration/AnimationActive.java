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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * It contains the animation options to set and get the duration in milliseconds it takes to animate hover style changes. 
 * 
 * @author Andrea "Stock" Stocchero
 */
public class AnimationActive extends ConfigurationContainer<ExtendedOptions> {

	/**
	 * Builds the object storing the chart instance and root options.
	 * 
	 * @param chart chart instance.
	 * @param options root options of chart.
	 */
	AnimationActive(IsChart chart, ExtendedOptions options) {
		super(chart, options);
	}

	/**
	 * Sets the number of milliseconds an animation takes.
	 * 
	 * @param milliseconds the number of milliseconds an animation takes.
	 */
	public void setDuration(int milliseconds) {
		getConfiguration().getAnimation().getActive().setDuration(milliseconds);
	}

	/**
	 * Returns the number of milliseconds an animation takes.
	 * 
	 * @return the number of milliseconds an animation takes.
	 */
	public int getDuration() {
		return getConfiguration().getAnimation().getActive().getDuration();
	}

}