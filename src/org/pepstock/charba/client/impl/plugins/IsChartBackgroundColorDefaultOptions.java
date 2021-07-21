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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.dom.enums.GlobalCompositeOperation;
import org.pepstock.charba.client.enums.ColorType;

/**
 * Maps the default configuration options of {@link ChartBackgroundColor#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsChartBackgroundColorDefaultOptions {

	/**
	 * Returns the type of background color has been set.
	 * 
	 * @return the type of background color has been set
	 */
	default ColorType getColorType() {
		return ColorType.COLOR;
	}

	/**
	 * Returns the background color as string.
	 * 
	 * @return the background color
	 */
	default String getBackgroundColorAsString() {
		return ChartBackgroundColor.DEFAULT_BACKGROUND_COLOR;
	}

	/**
	 * Returns the background gradient. If it has been set a color or pattern, returns <code>null</code>.
	 * 
	 * @return the background gradient. If it has been set a color or pattern, returns <code>null</code>
	 */
	default Gradient getBackgroundColorAsGradient() {
		return null;
	}

	/**
	 * Returns the background pattern. If it has been set a color or gradient, returns <code>null</code>.
	 * 
	 * @return the background pattern. If it has been set a color or pattern, returns <code>null</code>
	 */
	default Pattern getBackgroundColorAsPattern() {
		return null;
	}

	/**
	 * Returns the type of compositing operation to apply when drawing new shapes.
	 *
	 * @return which of the compositing or blending mode operations to use
	 */
	default GlobalCompositeOperation getGlobalCompositeOperation() {
		return GlobalCompositeOperation.SOURCE_OVER;
	}

}
