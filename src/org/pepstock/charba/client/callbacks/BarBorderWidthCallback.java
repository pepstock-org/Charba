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

import org.pepstock.charba.client.data.BarDataset;

/**
 * Callback interface to set <code>borderWidth</code>, <code>hoverBorderWidth</code>, <code>pointBorderWidth</code> or <code>pointHoverBorderWidth</code> property at runtime, using
 * the chart instance and the context, Only for {@link BarDataset}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface BarBorderWidthCallback extends Scriptable<Object, DatasetContext> {

}
