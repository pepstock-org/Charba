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
package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.jsinterop.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.enums.InteractionMode;
import org.pepstock.charba.client.jsinterop.enums.TextAlign;
import org.pepstock.charba.client.jsinterop.enums.TooltipPosition;

/**
 * Interface to define tooltips object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public interface IsDefaultTooltips {

	/**
	 * Returns if tooltips are enabled.
	 * 
	 * @return if tooltips are enabled.
	 */
	boolean isEnabled();

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip.
	 */
	InteractionMode getMode();

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @return if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode
	 *         will be applied at all times.
	 */
	boolean isIntersect();

	/**
	 * Returns the mode for positioning the tooltip.
	 * 
	 * @return mode for positioning the tooltip.
	 */
	TooltipPosition getPosition();

	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return Background color of the tooltip.
	 */
	String getBackgroundColorAsString();

	/**
	 * Returns the title font.
	 * 
	 * @return the title font.
	 */
	String getTitleFontFamily();

	/**
	 * Returns the title font size.
	 * 
	 * @return Title font size.
	 */
	int getTitleFontSize();

	/**
	 * Returns the title font style.
	 * 
	 * @return title font style.
	 */
	FontStyle getTitleFontStyle();

	/**
	 * Returns the title alignment.
	 * 
	 * @return title alignment.
	 */
	TextAlign getTitleAlign();

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color.
	 */
	String getTitleFontColorAsString();

	/**
	 * Returns the spacing to add to top and bottom of each title line.
	 * 
	 * @return spacing to add to top and bottom of each title line.
	 */
	int getTitleSpacing();

	/**
	 * Returns the margin to add on bottom of title section.
	 * 
	 * @return margin to add on bottom of title section.
	 */
	int getTitleMarginBottom();

	/**
	 * Returns the body line font.
	 * 
	 * @return body line font.
	 */
	String getBodyFontFamily();

	/**
	 * Returns the body font size.
	 * 
	 * @return body font size.
	 */
	int getBodyFontSize();

	/**
	 * Returns the body font style.
	 * 
	 * @return body font style.
	 */
	FontStyle getBodyFontStyle();

	/**
	 * Returns the body alignment.
	 * 
	 * @return body alignment.
	 */
	TextAlign getBodyAlign();

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color.
	 */
	String getBodyFontColorAsString();

	/**
	 * Returns the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @return spacing to add to top and bottom of each tooltip item.
	 */
	int getBodySpacing();

	/**
	 * Returns the footer font.
	 * 
	 * @return footer font.
	 */
	String getFooterFontFamily();

	/**
	 * Returns the footer font size.
	 * 
	 * @return footer font size.
	 */
	int getFooterFontSize();

	/**
	 * Returns the footer font style.
	 * 
	 * @return footer font style.
	 */
	FontStyle getFooterFontStyle();

	/**
	 * Returns the footer alignment.
	 * 
	 * @return footer alignment.
	 */
	TextAlign getFooterAlign();

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color.
	 */
	String getFooterFontColorAsString();

	/**
	 * Returns the spacing to add to top and bottom of each footer line.
	 * 
	 * @return spacing to add to top and bottom of each footer line.
	 */
	int getFooterSpacing();

	/**
	 * Returns the margin to add before drawing the footer.
	 * 
	 * @return margin to add before drawing the footer.
	 */
	int getFooterMarginTop();

	/**
	 * Returns the padding to add on left and right of tooltip.
	 * 
	 * @return padding to add on left and right of tooltip.
	 */
	int getXPadding();

	/**
	 * Returns the padding to add on top and bottom of tooltip.
	 * 
	 * @return padding to add on top and bottom of tooltip.
	 */
	int getYPadding();

	/**
	 * Returns the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @return extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	int getCaretPadding();

	/**
	 * Returns the size, in px, of the tooltip arrow.
	 * 
	 * @return size, in px, of the tooltip arrow.
	 */

	int getCaretSize();

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves.
	 */
	int getCornerRadius();

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	String getMultiKeyBackgroundAsString();

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @return if true, color boxes are shown in the tooltip.
	 */
	boolean isDisplayColors();

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border.
	 */
	String getBorderColorAsString();

	/**
	 * Returns the size of the border.
	 * 
	 * @return size of the border.
	 */
	int getBorderWidth();
}