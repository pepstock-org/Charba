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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.impl.plugins.Crosshair;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScaleValueItem;

/**
 * This callback is the interface to defined customized representation of the labels in {@link Crosshair#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface CrosshairFormatterCallback {

	/**
	 * Returns the text to apply to the crosshair label.
	 * 
	 * @param chart chart instance
	 * @param scale scale instance
	 * @param value current value to draw in the label
	 * @return the text to apply to the crosshair label
	 */
	String format(IsChart chart, ScaleItem scale, ScaleValueItem value);

}