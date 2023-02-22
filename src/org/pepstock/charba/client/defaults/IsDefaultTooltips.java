/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.IsInteractionMode;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TooltipAlign;

/**
 * Interface to define tooltips object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultTooltips extends IsDefaultTextDirectionHandler, IsDefaultBoxHandler, IsDefaultAnimationContainer, IsDefaultEventHandler {

	/**
	 * Returns padding object defaults.
	 * 
	 * @return padding object defaults.
	 */
	IsDefaultPadding getPadding();

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
	IsInteractionMode getMode();

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be applied at all times.
	 * 
	 * @return if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be applied at all times.
	 */
	boolean isIntersect();

	/**
	 * Returns the mode for positioning the tooltip.
	 * 
	 * @return mode for positioning the tooltip.
	 */
	IsTooltipPosition getPosition();

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
	IsDefaultFont getTitleFont();

	/**
	 * Returns the title font color as string.
	 * 
	 * @return title font color as string
	 */
	String getTitleColorAsString();

	/**
	 * Returns the title alignment.
	 * 
	 * @return title alignment.
	 */
	TextAlign getTitleAlign();

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
	IsDefaultFont getBodyFont();

	/**
	 * Returns the body font color as string.
	 * 
	 * @return body font color as string
	 */
	String getBodyColorAsString();

	/**
	 * Returns the body alignment.
	 * 
	 * @return body alignment.
	 */
	TextAlign getBodyAlign();

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
	IsDefaultFont getFooterFont();

	/**
	 * Returns the footer font color as string.
	 * 
	 * @return footer font color as string
	 */
	String getFooterColorAsString();

	/**
	 * Returns the footer alignment.
	 * 
	 * @return footer alignment.
	 */
	TextAlign getFooterAlign();

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

	/**
	 * Returns if to use the corresponding point style (from data set options) instead of color boxes (size is based on the minimum value between boxWidth and boxHeight).
	 * 
	 * @return <code>true</code> if to use the corresponding point style (from data set options) instead of color boxes (size is based on the minimum value between boxWidth and
	 *         boxHeight)
	 */
	boolean isUsePointStyle();

	/**
	 * Returns the alignment of the tooltip caret in the X direction.
	 * 
	 * @return the alignment of the tooltip caret in the X direction
	 */
	TooltipAlign getXAlign();

	/**
	 * Returns the alignment of the tooltip caret in the Y direction.
	 * 
	 * @return the alignment of the tooltip caret in the Y direction
	 */
	TooltipAlign getYAlign();

	/**
	 * Returns the padding between the color box and the text.
	 * 
	 * @return padding between the color box and the text
	 */
	int getBoxPadding();

}