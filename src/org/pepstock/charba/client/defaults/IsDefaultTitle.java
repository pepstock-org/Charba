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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.callbacks.ElementAlignCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.callbacks.FullSizeCallback;
import org.pepstock.charba.client.callbacks.PaddingCallback;
import org.pepstock.charba.client.callbacks.PositionCallback;
import org.pepstock.charba.client.callbacks.TextCallback;

/**
 * Interface to define title object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultTitle extends IsDefaultPluginElement, IsDefaultFontContainer {

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented.
	 */
	IsDefaultPadding getPadding();

	/**
	 * Returns if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return Marks that this box should take the full width/height of the canvas (moving other boxes)
	 */
	boolean isFullSize();
	
	/**
	 * Returns the callback to set if the title is shown.
	 * 
	 * @return the callback instance to use
	 */
	DisplayCallback<ChartContext> getDisplayCallback();

	/**
	 * Returns the callback to set if marks that this box should take the full width/height of the canvas (moving other boxes).
	 * 
	 * @return the callback instance to use
	 */
	FullSizeCallback<ChartContext> getFullSizeCallback();

	/**
	 * Returns the padding callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the padding callback, if set, otherwise <code>null</code>.
	 */
	PaddingCallback<ChartContext> getPaddingCallback();

	/**
	 * Returns the font callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the font callback, if set, otherwise <code>null</code>.
	 */
	FontCallback<ChartContext> getFontCallback();

	/**
	 * Returns the color callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the color callback, if set, otherwise <code>null</code>.
	 */
	ColorCallback<ChartContext> getColorCallback();

	/**
	 * Returns the text callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the text callback, if set, otherwise <code>null</code>.
	 */
	TextCallback<ChartContext> getTextCallback();

	/**
	 * Returns the position callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the position callback, if set, otherwise <code>null</code>.
	 */
	PositionCallback<ChartContext> getPositionCallback();

	/**
	 * Returns the align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the align callback, if set, otherwise <code>null</code>.
	 */
	ElementAlignCallback<ChartContext> getAlignCallback();

}