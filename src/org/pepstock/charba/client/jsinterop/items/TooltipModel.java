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
package org.pepstock.charba.client.jsinterop.items;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip custom callback.<br>
 * The tooltip label configuration is nested below the tooltip configuration using the callbacks key. The tooltip has the
 * following callbacks for providing text.<br>
 * All methods must return either a string or an array of strings. Arrays of strings are treated as multiple lines of text.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TooltipCustomCallback
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
public final class TooltipModel extends NativeObject {
	
	@JsProperty (name ="dataPoints")
	native ArrayObject<TooltipItem> getNativeDataPoints();

	@JsProperty (name ="xPadding")
	native int getNativeXPadding();

	@JsProperty (name ="yPadding")
	native int getNativeYPadding();

	@JsProperty (name ="xAlign")
	native String getNativeXAlign();

	@JsProperty (name ="yAlign")
	native String getNativeYAlign();

	@JsProperty (name ="x")
	native int getNativeX();

	@JsProperty (name ="y")
	native int getNativeY();

	@JsProperty (name ="width")
	native int getNativeWidth();

	@JsProperty (name ="height")
	native int getNativeHeight();

	@JsProperty (name ="caretX")
	native int getNativeCaretX();

	@JsProperty (name ="caretY")
	native int getNativeCaretY();

	@JsProperty (name ="body")
	native ArrayObject<TooltipBodyItem> getNativeBody();

	@JsProperty (name ="beforeBody")
	native ArrayString getNativeBeforeBody();

	@JsProperty (name ="afterBody")
	native ArrayString getNativeAfterBody();

	@JsProperty (name ="bodyFontColor")
	native String getNativeBodyFontColor();

	@JsProperty (name ="_bodyFontFamily")
	native String getNativeBodyFontFamily();

	@JsProperty (name ="_bodyFontStyle")
	native String getNativeBodyFontStyle();

	@JsProperty (name ="_bodyAlign")
	native String getNativeBodyAlign();

	@JsProperty (name ="bodyFontSize")
	native int getNativeBodyFontSize();

	@JsProperty (name ="bodySpacing")
	native int getNativeBodySpacing();

	@JsProperty (name ="title")
	native ArrayString getNativeTitle();

	@JsProperty (name ="titleFontColor")
	native String getNativeTitleFontColor();

	@JsProperty (name ="_titleFontFamily")
	native String getNativeTitleFontFamily();

	@JsProperty (name ="_titleFontStyle")
	native String getNativeTitleFontStyle();

	@JsProperty (name ="titleFontSize")
	native int getNativeTitleFontSize();

	@JsProperty (name ="_titleAlign")
	native String getNativeTitleAlign();

	@JsProperty (name ="titleSpacing")
	native int getNativeTitleSpacing();

	@JsProperty (name ="titleMarginBottom")
	native int getNativeTitleMarginBottom();

	@JsProperty (name ="footer")
	native ArrayString getNativeFooter();

	@JsProperty (name ="footerFontColor")
	native String getNativeFooterFontColor();

	@JsProperty (name ="_footerFontFamily")
	native String getNativeFooterFontFamily();

	@JsProperty (name ="_footerFontStyle")
	native String getNativeFooterFontStyle();

	@JsProperty (name ="footerFontSize")
	native int getNativeFooterFontSize();

	@JsProperty (name ="_footerAlign")
	native String getNativeFooterAlign();

	@JsProperty (name ="footerSpacing")
	native int getNativeFooterSpacing();

	@JsProperty (name ="footerMarginTop")
	native int getNativeFooterMarginTop();

	@JsProperty (name ="caretSize")
	native int getNativeCaretSize();

	@JsProperty (name ="cornerRadius")
	native double getNativeCornerRadius();

	@JsProperty (name ="backgroundColor")
	native String getNativeBackgroundColor();

	@JsProperty (name ="labelColors")
	native ArrayObject<TooltipLabelColor> getNativeLabelColors();

	@JsProperty (name ="opacity")
	native double getNativeOpacity();

	@JsProperty (name ="legendColorBackground")
	native String getNativeLegendColorBackground();

	@JsProperty (name ="displayColors")
	native boolean isNativeDisplayColors();

	/**
	 * Returns the list of tooltip items related to data points.
	 * 
	 * @return the list of tooltip items related to data points.
	 * @see org.pepstock.charba.client.items.TooltipItem
	 */
	@JsOverlay
	public List<TooltipItem> getDataPoints() {
		return ArrayListHelper.unmodifiableList(getNativeDataPoints());
	}

	/**
	 * Returns the X padding location.
	 * 
	 * @return the X padding location.
	 */
	@JsOverlay
	public int getXPadding() {
		return Checker.check(getNativeXPadding(), Defaults.getGlobal().getTooltips().getXPadding());
	}

	/**
	 * Returns the Y padding location.
	 * 
	 * @return the Y padding location.
	 */
	@JsOverlay
	public int getYPadding() {
		return Checker.check(getNativeYPadding(), Defaults.getGlobal().getTooltips().getYPadding());
	}

	/**
	 * Returns the X align location.
	 * 
	 * @return the X align location. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public String getXAlign() {
		return Checker.check(getNativeXAlign(), UndefinedValues.STRING);
	}

	/**
	 * Returns the Y align location.
	 * 
	 * @return the Y align location. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public String getYAlign() {
		return Checker.check(getNativeYAlign(), UndefinedValues.STRING);
	}

	/**
	 * Returns the X location of tooltip.
	 * 
	 * @return the X location of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getX() {
		return Checker.check(getNativeX(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of tooltip.
	 * 
	 * @return the Y location of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getY() {
		return Checker.check(getNativeY(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the width of tooltip.
	 * 
	 * @return the width of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getWidth() {
		return Checker.check(getNativeWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height of tooltip.
	 * 
	 * @return the height of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getHeight() {
		return Checker.check(getNativeHeight(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X location of the tooltip arrow.
	 * 
	 * @return the X location of the tooltip arrow. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getCaretX() {
		return Checker.check(getNativeCaretX(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of the tooltip arrow.
	 * 
	 * @return the Y location of the tooltip arrow. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getCaretY() {
		return Checker.check(getNativeCaretY(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the list of items which belong to the tooltip body section.
	 * 
	 * @return the list of items which belong to the tooltip body section.
	 * @see org.pepstock.charba.client.items.TooltipBodyItem
	 */
	@JsOverlay
	public List<TooltipBodyItem> getBody() {
		return ArrayListHelper.unmodifiableList(getNativeBody());
	}

	/**
	 * Returns the list of lines before body section.
	 * 
	 * @return the list of lines before body section.
	 */
	@JsOverlay
	public List<String> getBeforeBody() {
		return ArrayListHelper.unmodifiableList(getNativeBeforeBody());
	}

	/**
	 * Returns the list of lines after body section.
	 * 
	 * @return the list of lines after body section.
	 */
	@JsOverlay
	public List<String> getAfterBody() {
		return ArrayListHelper.unmodifiableList(getNativeAfterBody());
	}

	/**
	 * Returns the color of the tooltip body section.
	 * 
	 * @return the color of the tooltip body section.
	 */
	@JsOverlay
	public String getBodyFontColorAsString() {
		return Checker.check(getNativeBodyFontColor(), Defaults.getGlobal().getTooltips().getBodyFontColorAsString());
	}

	/**
	 * Returns the color of the tooltip body section.
	 * 
	 * @return the color of the tooltip body section.
	 */
	@JsOverlay
	public IsColor getBodyFontColor() {
		return ColorBuilder.parse(getBodyFontColorAsString());
	}

	/**
	 * Returns the font family of the tooltip body section.
	 * 
	 * @return the font family of the tooltip body section.
	 */
	@JsOverlay
	public String getBodyFontFamily() {
		return Checker.check(getNativeBodyFontFamily(), Defaults.getGlobal().getTooltips().getBodyFontFamily());
	}

	/**
	 * Returns the font style of the tooltip body section.
	 * 
	 * @return the font style of the tooltip body section.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	@JsOverlay
	public FontStyle getBodyFontStyle() {
		return Enumer.deserialize(getNativeBodyFontStyle(), FontStyle.class, Defaults.getGlobal().getTooltips().getBodyFontStyle());
	}

	/**
	 * Returns the alignment of the tooltip body section.
	 * 
	 * @return the alignment of the tooltip body section.
	 */
	@JsOverlay
	public TextAlign getBodyAlign() {
		return Enumer.deserialize(getNativeBodyAlign(), TextAlign.class, Defaults.getGlobal().getTooltips().getBodyAlign());
	}

	/**
	 * Returns the font size of the tooltip body section.
	 * 
	 * @return the font size of the tooltip body section.
	 */
	@JsOverlay
	public int getBodyFontSize() {
		return Checker.check(getNativeBodyFontSize(), Defaults.getGlobal().getTooltips().getBodyFontSize());
	}

	/**
	 * Returns the spacing of the tooltip body section.
	 * 
	 * @return the spacing of the tooltip body section.
	 */
	@JsOverlay
	public int getBodySpacing() {
		return Checker.check(getNativeBodySpacing(), Defaults.getGlobal().getTooltips().getBodySpacing());
	}

	/**
	 * Returns the title of the tooltip.
	 * 
	 * @return the title of the tooltip.
	 */
	@JsOverlay
	public List<String> getTitle() {
		return ArrayListHelper.unmodifiableList(getNativeTitle());
	}

	/**
	 * Returns the color of the tooltip title section.
	 * 
	 * @return the color of the tooltip title section.
	 */
	@JsOverlay
	public String getTitleFontColorAsString() {
		return Checker.check(getNativeTitleFontColor(), Defaults.getGlobal().getTooltips().getTitleFontColorAsString());
	}

	/**
	 * Returns the color of the tooltip title section.
	 * 
	 * @return the color of the tooltip title section.
	 */
	@JsOverlay
	public IsColor getTitleFontColor() {
		return ColorBuilder.parse(getTitleFontColorAsString());
	}

	/**
	 * Returns the font family of the tooltip title section.
	 * 
	 * @return the font family of the tooltip title section.
	 */
	@JsOverlay
	public String getTitleFontFamily() {
		return Checker.check(getNativeTitleFontFamily(), Defaults.getGlobal().getTooltips().getTitleFontFamily());
	}

	/**
	 * Returns the font style of the tooltip title section.
	 * 
	 * @return the font style of the tooltip title section.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	@JsOverlay
	public FontStyle getTitleFontStyle() {
		return Enumer.deserialize(getNativeTitleFontStyle(), FontStyle.class, Defaults.getGlobal().getTooltips().getTitleFontStyle());
	}

	/**
	 * Returns the font size of the tooltip title section.
	 * 
	 * @return the font size of the tooltip title section.
	 */
	@JsOverlay
	public int getTitleFontSize() {
		return Checker.check(getNativeTitleFontSize(), Defaults.getGlobal().getTooltips().getTitleFontSize());
	}

	/**
	 * Returns the alignment of the tooltip title section.
	 * 
	 * @return the alignment of the tooltip title section.
	 */
	@JsOverlay
	public TextAlign getTitleAlign() {
		return Enumer.deserialize(getNativeTitleAlign(), TextAlign.class, Defaults.getGlobal().getTooltips().getTitleAlign());
	}

	/**
	 * Returns the spacing of the tooltip title section.
	 * 
	 * @return the spacing of the tooltip title section.
	 */
	@JsOverlay
	public int getTitleSpacing() {
		return Checker.check(getNativeTitleSpacing(), Defaults.getGlobal().getTooltips().getTitleSpacing());
	}

	/**
	 * Returns the bottom margin of the tooltip title section.
	 * 
	 * @return the bottom margin of the tooltip title section.
	 */
	@JsOverlay
	public int getTitleMarginBottom() {
		return Checker.check(getNativeTitleMarginBottom(), Defaults.getGlobal().getTooltips().getTitleMarginBottom());
	}

	/**
	 * Returns the footer of the tooltip.
	 * 
	 * @return the footer of the tooltip.
	 */
	@JsOverlay
	public List<String> getFooter() {
		return ArrayListHelper.unmodifiableList(getNativeFooter());
	}

	/**
	 * Returns the color of the tooltip footer section.
	 * 
	 * @return the color of the tooltip footer section.
	 */
	@JsOverlay
	public String getFooterFontColorAsString() {
		return Checker.check(getNativeFooterFontColor(), Defaults.getGlobal().getTooltips().getFooterFontColorAsString());
	}

	/**
	 * Returns the color of the tooltip footer section.
	 * 
	 * @return the color of the tooltip footer section.
	 */
	@JsOverlay
	public IsColor getFooterFontColor() {
		return ColorBuilder.parse(getFooterFontColorAsString());
	}

	/**
	 * Returns the font family of the tooltip footer section.
	 * 
	 * @return the font family of the tooltip footer section.
	 */
	@JsOverlay
	public String getFooterFontFamily() {
		return Checker.check(getNativeFooterFontFamily(), Defaults.getGlobal().getTooltips().getFooterFontFamily());
	}

	/**
	 * Returns the font style of the tooltip footer section.
	 * 
	 * @return the font style of the tooltip footer section.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	@JsOverlay
	public FontStyle getFooterFontStyle() {
		return Enumer.deserialize(getNativeFooterFontStyle(), FontStyle.class, Defaults.getGlobal().getTooltips().getFooterFontStyle());
	}

	/**
	 * Returns the font size of the tooltip footer section.
	 * 
	 * @return the font size of the tooltip footer section.
	 */
	@JsOverlay
	public int getFooterFontSize() {
		return Checker.check(getNativeFooterFontSize(), Defaults.getGlobal().getTooltips().getFooterFontSize());
	}

	/**
	 * Returns the alignment of the tooltip footer section.
	 * 
	 * @return the alignment of the tooltip footer section.
	 */
	@JsOverlay
	public TextAlign getFooterAlign() {
		return Enumer.deserialize(getNativeFooterAlign(), TextAlign.class, Defaults.getGlobal().getTooltips().getFooterAlign());
	}

	/**
	 * Returns the spacing of the tooltip footer section.
	 * 
	 * @return the spacing of the tooltip footer section.
	 */
	@JsOverlay
	public int getFooterSpacing() {
		return Checker.check(getNativeFooterSpacing(), Defaults.getGlobal().getTooltips().getFooterSpacing());
	}

	/**
	 * Returns the margin top of the tooltip footer section.
	 * 
	 * @return the margin top of the tooltip footer section.
	 */
	@JsOverlay
	public int getFooterMarginTop() {
		return Checker.check(getNativeFooterMarginTop(), Defaults.getGlobal().getTooltips().getFooterMarginTop());
	}

	/**
	 * Returns the size, in px, of the tooltip arrow of footer section.
	 * 
	 * @return the size, in px, of the tooltip arrow of footer section.
	 */
	@JsOverlay
	public int getCaretSize() {
		return Checker.check(getNativeCaretSize(), Defaults.getGlobal().getTooltips().getCaretSize());
	}

	/**
	 * Returns the radius of tooltip corner curves of footer section.
	 * 
	 * @return the radius of tooltip corner curves of footer section.
	 */
	@JsOverlay
	public double getCornerRadius() {
		return Checker.check(getNativeCornerRadius(), Defaults.getGlobal().getTooltips().getCornerRadius());
	}

	/**
	 * Returns the background color of tooltip.
	 * 
	 * @return the background color of tooltip.
	 */
	@JsOverlay
	public String getBackgroundColorAsString() {
		return Checker.check(getNativeBackgroundColor(), Defaults.getGlobal().getTooltips().getBackgroundColorAsString());
	}

	/**
	 * Returns the background color of tooltip.
	 * 
	 * @return the background color of tooltip.
	 */
	@JsOverlay
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Returns the list of labels color of tooltip.
	 * 
	 * @return the list of labels color of tooltip.
	 * @see org.pepstock.charba.client.items.TooltipLabelColor
	 */
	@JsOverlay
	public List<TooltipLabelColor> getLabelColors() {
		return ArrayListHelper.unmodifiableList(getNativeLabelColors());
	}

	/**
	 * Returns the opacity of tooltip.
	 * 
	 * @return the opacity of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public double getOpacity() {
		return Checker.check(getNativeOpacity(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the legend color of tooltips.
	 * 
	 * @return the legend color of tooltips.
	 */
	@JsOverlay
	public String getLegendColorBackgroundAsString() {
		return Checker.check(getNativeLegendColorBackground(),  Defaults.getGlobal().getDefaultColorAsString());
	}

	/**
	 * Returns the legend color of tooltips.
	 * 
	 * @return the legend color of tooltips.
	 */
	@JsOverlay
	public IsColor getLegendColorBackground() {
		return ColorBuilder.parse(getLegendColorBackgroundAsString());
	}
	
	/**
	 * Returns the display of colors of tooltip.
	 * 
	 * @return the display of colors of tooltip. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public boolean isDisplayColors() {
		return Checker.check(isNativeDisplayColors(), UndefinedValues.BOOLEAN);
	}

}