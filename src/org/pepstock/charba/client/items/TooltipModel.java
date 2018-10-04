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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.TextAlign;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip custom callback.<br>
 * The tooltip label configuration is nested below the tooltip configuration using the callbacks key. The tooltip has the
 * following callbacks for providing text.<br>
 * All methods must return either a string or an array of strings. Arrays of strings are treated as multiple lines of text.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TooltipCustomCallback
 */
public final class TooltipModel extends JavaScriptObjectContainer {

	/**
	 * Needed for GWt injection
	 */
	enum Property implements Key
	{
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
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	public TooltipModel(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	/**
	 * Returns the list of tooltip items related to data points.
	 * 
	 * @return the list of tooltip items related to data points.
	 * @see org.pepstock.charba.client.items.TooltipItem
	 */
	public List<TooltipItem> getDataPoints() {
		List<TooltipItem> result = new LinkedList<>();
		if (has(Property.dataPoints)) {
			for (GenericJavaScriptObject object : getObjectArray(Property.dataPoints)) {
				TooltipItem item = new TooltipItem(object);
				result.add(item);
			}
		}
		return result;
	}

	/**
	 * Returns the X padding location.
	 * 
	 * @return the X padding location.
	 */
	public int getXPadding() {
		return getValue(Property.xPadding, Defaults.getGlobal().getTooltips().getXPadding());
	}

	/**
	 * Returns the Y padding location.
	 * 
	 * @return the Y padding location.
	 */
	public int getYPadding() {
		return getValue(Property.yPadding, Defaults.getGlobal().getTooltips().getYPadding());
	}

	/**
	 * Returns the X align location.
	 * 
	 * @return the X align location. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public String getXAlign() {
		return getValue(Property.xAlign, UndefinedValues.STRING);
	}

	/**
	 * Returns the Y align location.
	 * 
	 * @return the Y align location. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	public String getYAlign() {
		return getValue(Property.yAlign, UndefinedValues.STRING);
	}

	/**
	 * Returns the X location of tooltip.
	 * 
	 * @return the X location of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getX() {
		return getValue(Property.x, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of tooltip.
	 * 
	 * @return the Y location of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getY() {
		return getValue(Property.y, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the width of tooltip.
	 * 
	 * @return the width of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getWidth() {
		return getValue(Property.width, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height of tooltip.
	 * 
	 * @return the height of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getHeight() {
		return getValue(Property.height, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X location of the tooltip arrow.
	 * 
	 * @return the X location of the tooltip arrow. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getCaretX() {
		return getValue(Property.caretX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of the tooltip arrow.
	 * 
	 * @return the Y location of the tooltip arrow. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getCaretY() {
		return getValue(Property.caretY, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the list of items which belong to the tooltip body section.
	 * 
	 * @return the list of items which belong to the tooltip body section.
	 * @see org.pepstock.charba.client.items.TooltipBodyItem
	 */
	public List<TooltipBodyItem> getBody() {
		// creates result
		List<TooltipBodyItem> result = new LinkedList<>();
		// checks if the property exists
		if (has(Property.body)) {
			// gets and scans all java script objects
			for (GenericJavaScriptObject object : getObjectArray(Property.body)) {
				// creates body item
				TooltipBodyItem item = new TooltipBodyItem(object);
				result.add(item);
			}
		}
		return result;
	}

	/**
	 * Returns the list of lines before body section.
	 * 
	 * @return the list of lines before body section.
	 */
	public List<String> getBeforeBody() {
		return getStringArray(Property.beforeBody);
	}

	/**
	 * Returns the list of lines after body section.
	 * 
	 * @return the list of lines after body section.
	 */
	public List<String> getAfterBody() {
		return getStringArray(Property.afterBody);
	}

	/**
	 * Returns the color of the tooltip body section.
	 * 
	 * @return the color of the tooltip body section.
	 */
	public String getBodyFontColorAsString() {
		return getValue(Property.bodyFontColor, Defaults.getGlobal().getTooltips().getBodyFontColorAsString());
	}

	/**
	 * Returns the color of the tooltip body section.
	 * 
	 * @return the color of the tooltip body section.
	 */
	public IsColor getBodyFontColor() {
		return ColorBuilder.parse(getBodyFontColorAsString());
	}

	/**
	 * Returns the font family of the tooltip body section.
	 * 
	 * @return the font family of the tooltip body section.
	 */
	public String getBodyFontFamily() {
		return getValue(Property._bodyFontFamily, Defaults.getGlobal().getTooltips().getBodyFontFamily());
	}

	/**
	 * Returns the font style of the tooltip body section.
	 * 
	 * @return the font style of the tooltip body section.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getBodyFontStyle() {
		return getValue(Property._bodyFontStyle, FontStyle.class, Defaults.getGlobal().getTooltips().getBodyFontStyle());
	}

	/**
	 * Returns the alignment of the tooltip body section.
	 * 
	 * @return the alignment of the tooltip body section.
	 */
	public TextAlign getBodyAlign() {
		return getValue(Property._bodyAlign, TextAlign.class, Defaults.getGlobal().getTooltips().getBodyAlign());
	}

	/**
	 * Returns the font size of the tooltip body section.
	 * 
	 * @return the font size of the tooltip body section.
	 */
	public int getBodyFontSize() {
		return getValue(Property.bodyFontSize, Defaults.getGlobal().getTooltips().getBodyFontSize());
	}

	/**
	 * Returns the spacing of the tooltip body section.
	 * 
	 * @return the spacing of the tooltip body section.
	 */
	public int getBodySpacing() {
		return getValue(Property.bodySpacing, Defaults.getGlobal().getTooltips().getBodySpacing());
	}

	/**
	 * Returns the title of the tooltip.
	 * 
	 * @return the title of the tooltip.
	 */
	public List<String> getTitle() {
		return getStringArray(Property.title);
	}

	/**
	 * Returns the color of the tooltip title section.
	 * 
	 * @return the color of the tooltip title section.
	 */
	public String getTitleFontColorAsString() {
		return getValue(Property.titleFontColor, Defaults.getGlobal().getTooltips().getTitleFontColorAsString());
	}

	/**
	 * Returns the color of the tooltip title section.
	 * 
	 * @return the color of the tooltip title section.
	 */
	public IsColor getTitleFontColor() {
		return ColorBuilder.parse(getTitleFontColorAsString());
	}

	/**
	 * Returns the font family of the tooltip title section.
	 * 
	 * @return the font family of the tooltip title section.
	 */
	public String getTitleFontFamily() {
		return getValue(Property._titleFontFamily, Defaults.getGlobal().getTooltips().getTitleFontFamily());
	}

	/**
	 * Returns the font style of the tooltip title section.
	 * 
	 * @return the font style of the tooltip title section.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getTitleFontStyle() {
		return getValue(Property._titleFontStyle, FontStyle.class, Defaults.getGlobal().getTooltips().getTitleFontStyle());
	}

	/**
	 * Returns the font size of the tooltip title section.
	 * 
	 * @return the font size of the tooltip title section.
	 */
	public int getTitleFontSize() {
		return getValue(Property.titleFontSize, Defaults.getGlobal().getTooltips().getTitleFontSize());
	}

	/**
	 * Returns the alignment of the tooltip title section.
	 * 
	 * @return the alignment of the tooltip title section.
	 */
	public TextAlign getTitleAlign() {
		return getValue(Property._titleAlign, TextAlign.class, Defaults.getGlobal().getTooltips().getTitleAlign());
	}

	/**
	 * Returns the spacing of the tooltip title section.
	 * 
	 * @return the spacing of the tooltip title section.
	 */
	public int getTitleSpacing() {
		return getValue(Property.titleSpacing, Defaults.getGlobal().getTooltips().getTitleSpacing());
	}

	/**
	 * Returns the bottom margin of the tooltip title section.
	 * 
	 * @return the bottom margin of the tooltip title section.
	 */
	public int getTitleMarginBottom() {
		return getValue(Property.titleMarginBottom, Defaults.getGlobal().getTooltips().getTitleMarginBottom());
	}

	/**
	 * Returns the footer of the tooltip.
	 * 
	 * @return the footer of the tooltip.
	 */
	public List<String> getFooter() {
		return getStringArray(Property.footer);
	}

	/**
	 * Returns the color of the tooltip footer section.
	 * 
	 * @return the color of the tooltip footer section.
	 */
	public String getFooterFontColorAsString() {
		return getValue(Property.footerFontColor, Defaults.getGlobal().getTooltips().getFooterFontColorAsString());
	}

	/**
	 * Returns the color of the tooltip footer section.
	 * 
	 * @return the color of the tooltip footer section.
	 */
	public IsColor getFooterFontColor() {
		return ColorBuilder.parse(getFooterFontColorAsString());
	}

	/**
	 * Returns the font family of the tooltip footer section.
	 * 
	 * @return the font family of the tooltip footer section.
	 */
	public String getFooterFontFamily() {
		return getValue(Property._footerFontFamily, Defaults.getGlobal().getTooltips().getFooterFontFamily());
	}

	/**
	 * Returns the font style of the tooltip footer section.
	 * 
	 * @return the font style of the tooltip footer section.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFooterFontStyle() {
		return getValue(Property._footerFontStyle, FontStyle.class, Defaults.getGlobal().getTooltips().getFooterFontStyle());
	}

	/**
	 * Returns the font size of the tooltip footer section.
	 * 
	 * @return the font size of the tooltip footer section.
	 */
	public int getFooterFontSize() {
		return getValue(Property.footerFontSize, Defaults.getGlobal().getTooltips().getFooterFontSize());
	}

	/**
	 * Returns the alignment of the tooltip footer section.
	 * 
	 * @return the alignment of the tooltip footer section.
	 */
	public TextAlign getFooterAlign() {
		return getValue(Property._footerAlign, TextAlign.class, Defaults.getGlobal().getTooltips().getFooterAlign());
	}

	/**
	 * Returns the spacing of the tooltip footer section.
	 * 
	 * @return the spacing of the tooltip footer section.
	 */
	public int getFooterSpacing() {
		return getValue(Property.footerSpacing, Defaults.getGlobal().getTooltips().getFooterSpacing());
	}

	/**
	 * Returns the margin top of the tooltip footer section.
	 * 
	 * @return the margin top of the tooltip footer section.
	 */
	public int getFooterMarginTop() {
		return getValue(Property.footerMarginTop, Defaults.getGlobal().getTooltips().getFooterMarginTop());
	}

	/**
	 * Returns the size, in px, of the tooltip arrow of footer section.
	 * 
	 * @return the size, in px, of the tooltip arrow of footer section.
	 */
	public int getCaretSize() {
		return getValue(Property.caretSize, Defaults.getGlobal().getTooltips().getCaretSize());
	}

	/**
	 * Returns the radius of tooltip corner curves of footer section.
	 * 
	 * @return the radius of tooltip corner curves of footer section.
	 */
	public double getCornerRadius() {
		return getValue(Property.cornerRadius, Defaults.getGlobal().getTooltips().getCornerRadius());
	}

	/**
	 * Returns the background color of tooltip.
	 * 
	 * @return the background color of tooltip.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.backgroundColor, Defaults.getGlobal().getTooltips().getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of tooltip.
	 * 
	 * @return the background color of tooltip.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the list of labels color of tooltip.
	 * 
	 * @return the list of labels color of tooltip.
	 * @see org.pepstock.charba.client.items.TooltipLabelColor
	 */
	public List<TooltipLabelColor> getLabelColors() {
		// creates result
		List<TooltipLabelColor> result = new LinkedList<>();
		// checks if the property exists
		if (has(Property.labelColors)) {
			// gets and scans all java script objects
			for (GenericJavaScriptObject object : getObjectArray(Property.labelColors)) {
				// creates label color
				TooltipLabelColor item = new TooltipLabelColor(object);
				result.add(item);
			}
		}
		return result;
	}

	/**
	 * Returns the opacity of tooltip.
	 * 
	 * @return the opacity of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getOpacity() {
		return getValue(Property.opacity, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the legend color of tooltips.
	 * 
	 * @return the legend color of tooltips.
	 */
	public String getLegendColorBackgroundAsString() {
		return getValue(Property.legendColorBackground,  Defaults.getGlobal().getDefaultColorAsString());
	}

	/**
	 * Returns the legend color of tooltips.
	 * 
	 * @return the legend color of tooltips.
	 */
	public IsColor getLegendColorBackground() {
		return ColorBuilder.parse(getLegendColorBackgroundAsString());
	}
	/**
	 * Returns the display of colors of tooltip.
	 * 
	 * @return the display of colors of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public Boolean getDisplayColors() {
		return getValue(Property.displayColors, UndefinedValues.BOOLEAN);
	}

}