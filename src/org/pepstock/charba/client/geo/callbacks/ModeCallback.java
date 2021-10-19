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
package org.pepstock.charba.client.geo.callbacks;

import org.pepstock.charba.client.callbacks.ScaleContext;
import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.geo.BubbleMapChart;
import org.pepstock.charba.client.geo.SizeAxis;
import org.pepstock.charba.client.geo.enums.Mode;

/**
 * Callback interface to set <code>mode</code> property at runtime in {@link SizeAxis} for {@link BubbleMapChart}.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public interface ModeCallback extends Scriptable<Mode, ScaleContext> {

}
