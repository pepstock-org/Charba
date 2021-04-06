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

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;

/**
 * Callback interface to set <br>
 * <ul>
 * <li><code>borderColor</code>
 * <li><code>hoverBorderColor</code>
 * <li><code>pointBorderColor</code>
 * <li><code>pointHoverBorderColor</code>
 * <li><code>textStrokeColor</code>
 * <li><code>backgroundColor</code>
 * <li><code>hoverBackgroundColor</code>
 * <li><code>pointBackgroundColor</code>
 * <li><code>pointHoverBackgroundColor</code>
 * </ul>
 * property at runtime, using the chart instance and the context.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @see IsColor
 * @see Pattern
 * @see Gradient
 * 
 * @param <C> type of context to pass to the callback.
 */
public interface ColorCallback<C extends ChartContext> extends Scriptable<Object, C> {

}
