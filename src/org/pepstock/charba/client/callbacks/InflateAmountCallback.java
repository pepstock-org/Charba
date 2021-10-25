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

import org.pepstock.charba.client.configuration.Bar;
import org.pepstock.charba.client.data.BarDataset;
import org.pepstock.charba.client.items.Undefined;

/**
 * Callback interface to set <code>inflateAmount</code> property at runtime, for {@link BarDataset} or {@link Bar} element instances.<br>
 * Returning {@link Undefined#INTEGER}, the chart will automatically calculate the amount of pixels to inflate the bar rectangles, when drawing.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public interface InflateAmountCallback extends Scriptable<Integer, DatasetContext> {

}
