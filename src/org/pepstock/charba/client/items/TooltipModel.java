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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.TooltipBodyItem.TooltipBodyItemFactory;
import org.pepstock.charba.client.items.TooltipItem.TooltipItemFactory;
import org.pepstock.charba.client.items.TooltipLabelColor.TooltipLabelColorFactory;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip custom callback.<br>
 * The tooltip label configuration is nested below the tooltip configuration using the callbacks key.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TooltipModel extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DATA_POINTS("dataPoints"),
		X_PADDING("xPadding"),
		Y_PADDING("yPadding"),
		X_ALIGN("xAlign"),
		Y_ALIGN("yAlign"),
		X("x"),
		Y("y"),
		WIDTH("width"),
		HEIGHT("height"),
		CARET_X("caretX"),
		CARET_Y("caretY"),
		BODY("body"),
		BEFORE_BODY("beforeBody"),
		AFTER_BODY("afterBody"),
		BODY_FONT_COLOR("bodyFontColor"),
		BODY_FONT_FAMILY("_bodyFontFamily"),
		BODY_FONT_STYLE("_bodyFontStyle"),
		BODY_ALIGN("_bodyAlign"),
		BODY_FONT_SIZE("bodyFontSize"),
		BODY_SPACING("bodySpacing"),
		TITLE("title"),
		TITLE_FONT_COLOR("titleFontColor"),
		TITLE_FONT_FAMILY("_titleFontFamily"),
		TITLE_FONT_STYLE("_titleFontStyle"),
		TITLE_FONT_SIZE("titleFontSize"),
		TITLE_ALIGN("_titleAlign"),
		TITLE_SPACING("titleSpacing"),
		TITLE_MARGIN_BOTTOM("titleMarginBottom"),
		FOOTER("footer"),
		FOOTER_FONT_COLOR("footerFontColor"),
		FOOTER_FONT_FAMILY("_footerFontFamily"),
		FOOTER_FONT_STYLE("_footerFontStyle"),
		FOOTER_FONT_SIZE("footerFontSize"),
		FOOTER_ALIGN("_footerAlign"),
		FOOTER_SPACING("footerSpacing"),
		FOOTER_MARGIN_TOP("footerMarginTop"),
		CARET_SIZE("caretSize"),
		CARET_PADDING("caretPadding"),
		CORNER_RADIUS("cornerRadius"),
		BACKGROUND_COLOR("backgroundColor"),
		LABEL_COLORS("labelColors"),
		LABEL_TEXT_COLORS("labelTextColors"),
		OPACITY("opacity"),
		LEGEND_COLOR_BACKGROUND("legendColorBackground"),
		DISPLAY_COLORS("displayColors"),
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	// instance of tooltip items factory
	private final TooltipItemFactory tooltipItemFactory = new TooltipItemFactory();
	// instance of tooltip body items factory
	private final TooltipBodyItemFactory tooltipBodyItemFactory = new TooltipBodyItemFactory();
	// instance of tooltip label color factory
	private final TooltipLabelColorFactory tooltipLabelColorFactory = new TooltipLabelColorFactory();

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public TooltipModel(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the list of tooltip items related to data points.
	 * 
	 * @return the list of tooltip items related to data points.
	 */
	public List<TooltipItem> getDataPoints() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.DATA_POINTS);
		// returns as list
		return ArrayListHelper.unmodifiableList(array, tooltipItemFactory);
	}

	/**
	 * Returns the X padding location.
	 * 
	 * @return the X padding location.
	 */
	public int getXPadding() {
		return getValue(Property.X_PADDING, Defaults.get().getGlobal().getTooltips().getXPadding());
	}

	/**
	 * Returns the Y padding location.
	 * 
	 * @return the Y padding location.
	 */
	public int getYPadding() {
		return getValue(Property.Y_PADDING, Defaults.get().getGlobal().getTooltips().getYPadding());
	}

	/**
	 * Returns the X align location.
	 * 
	 * @return the X align location. Default is {@link UndefinedValues#STRING}.
	 */
	public String getXAlign() {
		return getValue(Property.X_ALIGN, UndefinedValues.STRING);
	}

	/**
	 * Returns the Y align location.
	 * 
	 * @return the Y align location. Default is {@link UndefinedValues#STRING}.
	 */
	public String getYAlign() {
		return getValue(Property.Y_ALIGN, UndefinedValues.STRING);
	}

	/**
	 * Returns the X location of tooltip.
	 * 
	 * @return the X location of tooltip. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getX() {
		return getValue(Property.X, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of tooltip.
	 * 
	 * @return the Y location of tooltip. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getY() {
		return getValue(Property.Y, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the width of tooltip.
	 * 
	 * @return the width of tooltip. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getWidth() {
		return getValue(Property.WIDTH, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height of tooltip.
	 * 
	 * @return the height of tooltip. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getHeight() {
		return getValue(Property.HEIGHT, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X location of the tooltip arrow.
	 * 
	 * @return the X location of the tooltip arrow. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getCaretX() {
		return getValue(Property.CARET_X, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of the tooltip arrow.
	 * 
	 * @return the Y location of the tooltip arrow. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getCaretY() {
		return getValue(Property.CARET_Y, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the list of items which belong to the tooltip body section.
	 * 
	 * @return the list of items which belong to the tooltip body section.
	 */
	public List<TooltipBodyItem> getBody() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.BODY);
		// returns as list
		return ArrayListHelper.unmodifiableList(array, tooltipBodyItemFactory);
	}

	/**
	 * Returns the list of lines before body section.
	 * 
	 * @return the list of lines before body section.
	 */
	public List<String> getBeforeBody() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.BEFORE_BODY);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the list of lines after body section.
	 * 
	 * @return the list of lines after body section.
	 */
	public List<String> getAfterBody() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.AFTER_BODY);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the color of the tooltip body section.
	 * 
	 * @return the color of the tooltip body section.
	 */
	public String getBodyFontColorAsString() {
		return getValue(Property.BODY_FONT_COLOR, Defaults.get().getGlobal().getTooltips().getBodyFont().getColorAsString());
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
		return getValue(Property.BODY_FONT_FAMILY, Defaults.get().getGlobal().getTooltips().getBodyFont().getFamily());
	}

	/**
	 * Returns the font style of the tooltip body section.
	 * 
	 * @return the font style of the tooltip body section.
	 */
	public FontStyle getBodyFontStyle() {
		return getValue(Property.BODY_FONT_STYLE, FontStyle.values(), Defaults.get().getGlobal().getTooltips().getBodyFont().getStyle());
	}

	/**
	 * Returns the alignment of the tooltip body section.
	 * 
	 * @return the alignment of the tooltip body section.
	 */
	public TextAlign getBodyAlign() {
		return getValue(Property.BODY_ALIGN, TextAlign.values(), Defaults.get().getGlobal().getTooltips().getBodyAlign());
	}

	/**
	 * Returns the font size of the tooltip body section.
	 * 
	 * @return the font size of the tooltip body section.
	 */
	public int getBodyFontSize() {
		return getValue(Property.BODY_FONT_SIZE, Defaults.get().getGlobal().getTooltips().getBodyFont().getSize());
	}

	/**
	 * Returns the spacing of the tooltip body section.
	 * 
	 * @return the spacing of the tooltip body section.
	 */
	public int getBodySpacing() {
		return getValue(Property.BODY_SPACING, Defaults.get().getGlobal().getTooltips().getBodySpacing());
	}

	/**
	 * Returns the title of the tooltip.
	 * 
	 * @return the title of the tooltip.
	 */
	public List<String> getTitle() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.TITLE);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the color of the tooltip title section.
	 * 
	 * @return the color of the tooltip title section.
	 */
	public String getTitleFontColorAsString() {
		return getValue(Property.TITLE_FONT_COLOR, Defaults.get().getGlobal().getTooltips().getTitleFont().getColorAsString());
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
		return getValue(Property.TITLE_FONT_FAMILY, Defaults.get().getGlobal().getTooltips().getTitleFont().getFamily());
	}

	/**
	 * Returns the font style of the tooltip title section.
	 * 
	 * @return the font style of the tooltip title section.
	 */
	public FontStyle getTitleFontStyle() {
		return getValue(Property.TITLE_FONT_STYLE, FontStyle.values(), Defaults.get().getGlobal().getTooltips().getTitleFont().getStyle());
	}

	/**
	 * Returns the font size of the tooltip title section.
	 * 
	 * @return the font size of the tooltip title section.
	 */
	public int getTitleFontSize() {
		return getValue(Property.TITLE_FONT_SIZE, Defaults.get().getGlobal().getTooltips().getTitleFont().getSize());
	}

	/**
	 * Returns the alignment of the tooltip title section.
	 * 
	 * @return the alignment of the tooltip title section.
	 */
	public TextAlign getTitleAlign() {
		return getValue(Property.TITLE_ALIGN, TextAlign.values(), Defaults.get().getGlobal().getTooltips().getTitleAlign());
	}

	/**
	 * Returns the spacing of the tooltip title section.
	 * 
	 * @return the spacing of the tooltip title section.
	 */
	public int getTitleSpacing() {
		return getValue(Property.TITLE_SPACING, Defaults.get().getGlobal().getTooltips().getTitleSpacing());
	}

	/**
	 * Returns the bottom margin of the tooltip title section.
	 * 
	 * @return the bottom margin of the tooltip title section.
	 */
	public int getTitleMarginBottom() {
		return getValue(Property.TITLE_MARGIN_BOTTOM, Defaults.get().getGlobal().getTooltips().getTitleMarginBottom());
	}

	/**
	 * Returns the footer of the tooltip.
	 * 
	 * @return the footer of the tooltip.
	 */
	public List<String> getFooter() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.FOOTER);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns the color of the tooltip footer section.
	 * 
	 * @return the color of the tooltip footer section.
	 */
	public String getFooterFontColorAsString() {
		return getValue(Property.FOOTER_FONT_COLOR, Defaults.get().getGlobal().getTooltips().getFooterFont().getColorAsString());
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
		return getValue(Property.FOOTER_FONT_FAMILY, Defaults.get().getGlobal().getTooltips().getFooterFont().getFamily());
	}

	/**
	 * Returns the font style of the tooltip footer section.
	 * 
	 * @return the font style of the tooltip footer section.
	 */
	public FontStyle getFooterFontStyle() {
		return getValue(Property.FOOTER_FONT_STYLE, FontStyle.values(), Defaults.get().getGlobal().getTooltips().getFooterFont().getStyle());
	}

	/**
	 * Returns the font size of the tooltip footer section.
	 * 
	 * @return the font size of the tooltip footer section.
	 */
	public int getFooterFontSize() {
		return getValue(Property.FOOTER_FONT_SIZE, Defaults.get().getGlobal().getTooltips().getFooterFont().getSize());
	}

	/**
	 * Returns the alignment of the tooltip footer section.
	 * 
	 * @return the alignment of the tooltip footer section.
	 */
	public TextAlign getFooterAlign() {
		return getValue(Property.FOOTER_ALIGN, TextAlign.values(), Defaults.get().getGlobal().getTooltips().getFooterAlign());
	}

	/**
	 * Returns the spacing of the tooltip footer section.
	 * 
	 * @return the spacing of the tooltip footer section.
	 */
	public int getFooterSpacing() {
		return getValue(Property.FOOTER_SPACING, Defaults.get().getGlobal().getTooltips().getFooterSpacing());
	}

	/**
	 * Returns the margin top of the tooltip footer section.
	 * 
	 * @return the margin top of the tooltip footer section.
	 */
	public int getFooterMarginTop() {
		return getValue(Property.FOOTER_MARGIN_TOP, Defaults.get().getGlobal().getTooltips().getFooterMarginTop());
	}

	/**
	 * Returns the size, in px, of the tooltip arrow of footer section.
	 * 
	 * @return the size, in px, of the tooltip arrow of footer section.
	 */
	public int getCaretSize() {
		return getValue(Property.CARET_SIZE, Defaults.get().getGlobal().getTooltips().getCaretSize());
	}

	/**
	 * Returns the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @return the extra distance to move the end of the tooltip arrow away from the tooltip point
	 */
	public int getCaretPadding() {
		return getValue(Property.CARET_PADDING, Defaults.get().getGlobal().getTooltips().getCaretPadding());
	}

	/**
	 * Returns the radius of tooltip corner curves of footer section.
	 * 
	 * @return the radius of tooltip corner curves of footer section.
	 */
	public double getCornerRadius() {
		return getValue(Property.CORNER_RADIUS, Defaults.get().getGlobal().getTooltips().getCornerRadius());
	}

	/**
	 * Returns the background color of tooltip.
	 * 
	 * @return the background color of tooltip.
	 */
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, Defaults.get().getGlobal().getTooltips().getBackgroundColorAsString());
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
	 */
	public List<TooltipLabelColor> getLabelColors() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.LABEL_COLORS);
		// returns as list
		return ArrayListHelper.unmodifiableList(array, tooltipLabelColorFactory);
	}

	/**
	 * Returns the list of labels text color of tooltip.
	 * 
	 * @return the list of labels text color of tooltip.
	 */
	public List<TooltipLabelColor> getTextLabelColors() {
		// gets array from native object
		ArrayObject array = getArrayValue(Property.LABEL_TEXT_COLORS);
		// returns as list
		return ArrayListHelper.unmodifiableList(array, tooltipLabelColorFactory);
	}

	/**
	 * Returns the opacity of tooltip.
	 * 
	 * @return the opacity of tooltip. Default is {@link UndefinedValues#DOUBLE}.
	 */
	public double getOpacity() {
		return getValue(Property.OPACITY, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the legend color of tooltips.
	 * 
	 * @return the legend color of tooltips.
	 */
	public String getLegendColorBackgroundAsString() {
		return getValue(Property.LEGEND_COLOR_BACKGROUND, Defaults.get().getGlobal().getColorAsString());
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
	 * Returns the border color of tooltips.
	 * 
	 * @return the border color of tooltips.
	 */
	public String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, Defaults.get().getGlobal().getTooltips().getBorderColorAsString());
	}

	/**
	 * Returns the border color of tooltips.
	 * 
	 * @return the border color of tooltips.
	 */
	public IsColor getBorderColorBackground() {
		return ColorBuilder.parse(getBorderColorAsString());
	}

	/**
	 * Returns the size of border.
	 * 
	 * @return the size of border
	 */
	public int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, Defaults.get().getGlobal().getTooltips().getBorderWidth());
	}

	/**
	 * Returns the display of colors of tooltip.
	 * 
	 * @return the display of colors of tooltip.
	 */
	public boolean isDisplayColors() {
		return getValue(Property.DISPLAY_COLORS, Defaults.get().getGlobal().getTooltips().isDisplayColors());
	}

}