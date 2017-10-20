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
package org.pepstock.charba.client.items;

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip custom callback.<br>
 * The tooltip label configuration is nested below the tooltip configuration using the callbacks key. The tooltip has the following callbacks for providing text.<br> 
 * All methods must return either a string or an array of strings. Arrays of strings are treated as multiple lines of text.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TooltipCustomCallback
 */
public final class TooltipModel  extends BaseItem {
 
    /** 
     * Needed for GWt injection
     */
    private enum Property implements Key{
    	dataPoints,
    	xPadding,
    	yPadding,
    	xAlign,
    	yAlign,
    	x,
    	y,
    	width,
    	height,
    	caretX,
    	caretY,
    	body,
    	beforeBody,
    	afterBody,
    	bodyFontColor,
    	_bodyFontFamily,
    	_bodyFontStyle,
    	_bodyAlign,
    	bodyFontSize,
    	bodySpacing,
    	title,
    	titleFontColor,
    	_titleFontFamily,
    	_titleFontStyle,
    	titleFontSize,
    	_titleAlign,
    	titleSpacing,
    	titleMarginBottom,
    	footer,
    	footerFontColor,
    	_footerFontFamily,
    	_footerFontStyle,
    	footerFontSize,
    	_footerAlign,
    	footerSpacing,
    	footerMarginTop,
    	caretSize,
    	cornerRadius,
    	backgroundColor,
    	labelColors,
    	opacity,
    	legendColorBackground,
    	displayColors
    }

    /** 
     * Needed for GWt injection
     */
    protected TooltipModel() {
    	// do nothing
	}

    /**
     * Returns the list of tooltip items related to data points.
     * @return the list of tooltip items related to data points.
     * @see org.pepstock.charba.client.items.TooltipItem
     */
    public final List<TooltipItem> getDataPoints() {
    	return getObjectArray(Property.dataPoints.name());
    }

    /**
     * Returns the X padding location.
     * @return the X padding location.
     */
    public final int getXPadding() {
        return getInt(Property.xPadding.name());
    }

    /**
     * Returns the Y padding location.
     * @return the Y padding location.
     */
    public final int getYPadding() {
        return getInt(Property.yPadding.name());
    }

    /**
     * Returns the X align location.
     * @return the X align location.
     */
    public final String getXAlign() {
        return getString(Property.xAlign.name());
    }

    /**
     * Returns the Y align location.
     * @return the Y align location.
     */
    public final String getYAlign() {
        return getString(Property.yAlign.name());
    }

    /**
     * Returns the X location of tooltip.
     * @return the X location of tooltip.
     */
    public final int getX() {
        return getInt(Property.x.name());
    }

    /**
     * Returns the Y location of tooltip.
     * @return the Y location of tooltip.
     */
    public final int getY() {
        return getInt(Property.y.name());
    }

    /**
     * Returns the width of tooltip.
     * @return the width of tooltip.
     */
    public final int getWidth() {
        return getInt(Property.width.name());
    }

    /**
     * Returns the height of tooltip.
     * @return the height of tooltip.
     */
    public final int getHeight() {
        return getInt(Property.height.name());
    }

    /**
     * Returns the X location of the tooltip arrow.
     * @return the X location of the tooltip arrow.
     */
    public final int getCaretX() {
        return getInt(Property.caretX.name());
    }

    /**
     * Returns the Y location of the tooltip arrow.
     * @return the Y location of the tooltip arrow.
     */
    public final int getCaretY() {
        return getInt(Property.caretY.name());
    }

    /**
     * Returns the list of items which belong to the tooltip body section. 
     * @return the list of items which belong to the tooltip body section.
     * @see org.pepstock.charba.client.items.TooltipBodyItem
     */
    public final List<TooltipBodyItem> getBody() {
    	return getObjectArray(Property.body.name());
    }

    /**
     * Returns the list of lines before body section.
     * @return the list of lines before body section.
     */
    public final List<String> getBeforeBody() {
        return getStringArray(Property.beforeBody.name());
    }

    /**
     * Returns the list of lines after body section.
     * @return the list of lines after body section.
     */
    public final List<String> getAfterBody() {
        return getStringArray(Property.afterBody.name());
    }

    /**
     * Returns the color of the tooltip body section.
     * @return the color of the tooltip body section.
     */
    public final String getBodyFontColor() {
        return getString(Property.bodyFontColor.name());
    }

    /**
     * Returns the font family of the tooltip body section.
     * @return the font family of the tooltip body section.
     */
    public final String getBodyFontFamily() {
        return getString(Property._bodyFontFamily.name());
    }

    /**
     * Returns the font style of the tooltip body section.
     * @return the font style of the tooltip body section.
     * @see org.pepstock.charba.client.enums.FontStyle
     */
    public final FontStyle getBodyFontStyle() {
        return getValue(Property._bodyFontStyle, FontStyle.class, FontStyle.normal);
    }

    /**
     * Returns the alignment of the tooltip body section.
     * @return the alignment of the tooltip body section.
     */
    public final String getBodyAlign() {
        return getString(Property._bodyAlign.name());
    }

    /**
     * Returns the font size of the tooltip body section.
     * @return the font size of the tooltip body section.
     */
    public final int getBodyFontSize() {
        return getInt(Property.bodyFontSize.name());
    }

    /**
     * Returns the spacing of the tooltip body section.
     * @return the spacing of the tooltip body section.
     */
    public final int getBodySpacing() {
        return getInt(Property.bodySpacing.name());
    }

    /**
     * Returns the title of the tooltip.
     * @return the title of the tooltip.
     */
    public final List<String> getTitle() {
        return getStringArray(Property.title.name());
    }

    /**
     * Returns the color of the tooltip title section.
     * @return the color of the tooltip title section.
     */
    public final String getTitleFontColor() {
        return getString(Property.titleFontColor.name());
    }

    /**
     * Returns the font family of the tooltip title section.
     * @return the font family of the tooltip title section.
     */
    public final String getTitleFontFamily() {
        return getString(Property._titleFontFamily.name());
    }

    /**
     * Returns the font style of the tooltip title section.
     * @return the font style of the tooltip title section.
     * @see org.pepstock.charba.client.enums.FontStyle
     */
    public final FontStyle getTitleFontStyle() {
    	return getValue(Property._titleFontStyle, FontStyle.class, FontStyle.normal);
    }

    /**
     * Returns the font size of the tooltip title section.
     * @return the font size of the tooltip title section.
     */
    public final int getTitleFontSize() {
        return getInt(Property.titleFontSize.name());
    }

    /**
     * Returns the alignment of the tooltip title section.
     * @return the alignment of the tooltip title section.
     */
    public final String getTitleAlign() {
        return getString(Property._titleAlign.name());
    }

    /**
     * Returns the spacing of the tooltip title section.
     * @return the spacing of the tooltip title section.
     */
    public final int getTitleSpacing() {
        return getInt(Property.titleSpacing.name());
    }

    /**
     * Returns the bottom margin of the tooltip title section.
     * @return the bottom margin of the tooltip title section.
     */
    public final int getTitleMarginBottom() {
        return getInt(Property.titleMarginBottom.name());
    }

    /**
     * Returns the footer of the tooltip.
     * @return the footer of the tooltip.
     */
    public final List<String> getFooter() {
        return getStringArray(Property.footer.name());
    }
    
    /**
     * Returns the color of the tooltip footer section.
     * @return the color of the tooltip footer section.
     */
    public final String getFooterFontColor() {
        return getString(Property.footerFontColor.name());
    }

    /**
     * Returns the font family of the tooltip footer section.
     * @return the font family of the tooltip footer section.
     */
    public final String getFooterFontFamily() {
        return getString(Property._footerFontFamily.name());
    }

    /**
     * Returns the font style of the tooltip footer section.
     * @return the font style of the tooltip footer section.
     * @see org.pepstock.charba.client.enums.FontStyle
     */
    public final FontStyle getFooterFontStyle() {
    	return getValue(Property._footerFontStyle, FontStyle.class, FontStyle.normal);
    }

    /**
     * Returns the font size of the tooltip footer section.
     * @return the font size of the tooltip footer section.
     */
    public final int getFooterFontSize() {
        return getInt(Property.footerFontSize.name());
    }

    /**
     * Returns the alignment of the tooltip footer section.
     * @return the alignment of the tooltip footer section.
     */
    public final String getFooterAlign() {
        return getString(Property._footerAlign.name());
    }

    /**
     * Returns the spacing of the tooltip footer section.
     * @return the spacing of the tooltip footer section.
     */
    public final int getFooterSpacing() {
        return getInt(Property.footerSpacing.name());
    }

    /**
     * Returns the margin top of the tooltip footer section.
     * @return the margin top of the tooltip footer section.
     */
    public final int getFooterMarginTop() {
        return getInt(Property.footerMarginTop.name());
    }

    /**
     * Returns the size, in px, of the tooltip arrow of footer section.
     * @return the size, in px, of the tooltip arrow of footer section.
     */
    public final int getCaretSize() {
        return getInt(Property.caretSize.name());
    }

    /**
     * Returns the radius of tooltip corner curves of footer section.
     * @return the radius of tooltip corner curves of footer section.
     */
    public final double getCornerRadius() {
        return getDouble(Property.cornerRadius.name());
    }

    /**
     * Returns the background color of tooltip.
     * @return the background color of tooltip.
     */
    public final String getBackgroundColor() {
        return getString(Property.backgroundColor.name());
    }

    /**
     * Returns the list of labels color of tooltip. 
     * @return the list of labels color of tooltip.
     * @see org.pepstock.charba.client.items.TooltipLabelColor
     */
    public final List<TooltipLabelColor> getLabelColors() {
    	return getObjectArray(Property.labelColors.name());
    }

    /**
     * Returns the opacity of tooltip.
     * @return the opacity of tooltip.
     */
    public final double getOpacity() {
        return getDouble(Property.opacity.name());
    }

    /**
     * Returns the legend color of tooltips. 
     * @return the legend color of tooltips.
     */
    public final String getLegendColorBackground() {
        return getString(Property.legendColorBackground.name());
    }

    /**
     * Returns the display of colors of tooltip.
     * @return the display of colors of tooltip.
     */
    public final Boolean getDisplayColors() {
        return getBoolean(Property.displayColors.name());
    }

}