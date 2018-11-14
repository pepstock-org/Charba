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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TooltipPosition;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipCustomCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipFilterCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipItemSortCallback;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipCustomHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipFilterHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipItemSortHandler;
import org.pepstock.charba.client.jsinterop.items.TooltipItem;
import org.pepstock.charba.client.jsinterop.items.TooltipModel;
import org.pepstock.charba.client.jsinterop.options.EventableOptions;

/**
 * Configuration element to set all attributes and features of the tooltip.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Tooltips extends ConfigurationContainer<EventableOptions> implements TooltipCustomHandler, TooltipItemSortHandler, TooltipFilterHandler{
	
	private final TooltipsCallbacks callbacks;

	/**
	 * Builds the object storing the chart instance.<br>
	 * Sets also the internal parts of options.
	 * 
	 * @param chart chart instance
	 */
	Tooltips(AbstractChart<?, ?> chart, EventableOptions options) {
		super(chart, options);
		// sets callbacks container
		callbacks = new TooltipsCallbacks(chart, options);
		if (hasGlobalCustomCallback()) {
			getConfiguration().setTooltipCustomHandler(this);
		}
		if (hasGlobalItemSortCallback()) {
			getConfiguration().setTooltipItemSortHandler(this);
		}
		if (hasGlobalFilterCallback()) {
			getConfiguration().setTooltipFilterHandler(this);
		}
	}

	/**
	 * @return the callbacks
	 */
	public TooltipsCallbacks getCallbacks() {
		return callbacks;
	}

	
	// ------------
	// CUSTOM
	// ------------
	private boolean hasGlobalCustomCallback() {
		return Defaults.getGlobal().getTooltips().getCustomCallback() != null || 
				Defaults.chart(getChart()).getTooltips().getCustomCallback() != null;
	}

	/**
	 * @return the customCallback
	 */
	public TooltipCustomCallback getCustomCallback() {
		return getConfiguration().getTooltips().getCustomCallback();
	}

	/**
	 * @param customCallback the customCallback to set
	 */
	public void setCustomCallback(TooltipCustomCallback customCallback) {
		getConfiguration().getTooltips().setCustomCallback(customCallback);
		if (!hasGlobalCustomCallback()) {
			if (customCallback == null) {
				getConfiguration().setTooltipCustomHandler(null);
				;
			} else {
				getConfiguration().setTooltipCustomHandler(this);
			}
		}
	}

	// ------------
	// ITEM SORTER
	// ------------

	private boolean hasGlobalItemSortCallback() {
		return Defaults.getGlobal().getTooltips().getItemSortCallback() != null || 
				Defaults.chart(getChart()).getTooltips().getItemSortCallback() != null;
	}
	
	/**
	 * @return the itemSortHandler
	 */
	public TooltipItemSortCallback getItemSortCallback() {
		return getConfiguration().getTooltips().getItemSortCallback();
	}

	/**
	 * @param itemSortHandler the itemSortHandler to set
	 */
	public void setItemSortCallback(TooltipItemSortCallback itemSortCallback) {
		getConfiguration().getTooltips().setItemSortCallback(itemSortCallback);
		if (!hasGlobalItemSortCallback()) {
			if (itemSortCallback == null) {
				getConfiguration().setTooltipItemSortHandler(null);
				;
			} else {
				getConfiguration().setTooltipItemSortHandler(this);
			}
		}
	}
	
	// ------------
	// FILTER
	// ------------

	private boolean hasGlobalFilterCallback() {
		return Defaults.getGlobal().getTooltips().getFilterCallback() != null || 
				Defaults.chart(getChart()).getTooltips().getFilterCallback() != null;
	}

	/**
	 * @return the filterHandler
	 */
	public TooltipFilterCallback getFilterCallback() {
		return getConfiguration().getTooltips().getFilterCallback();
	}

	/**
	 * @param filterHandler the filterHandler to set
	 */
	public void setFilterCallback(TooltipFilterCallback filterCallback) {
		getConfiguration().getTooltips().setFilterCallback(filterCallback);
		if (!hasGlobalFilterCallback()) {
			if (filterCallback == null) {
				getConfiguration().setTooltipFilterHandler(null);
			} else {
				getConfiguration().setTooltipFilterHandler(this);
			}
		}
	}

	/**
	 * Sets if tooltips are enabled.
	 * 
	 * @param enabled if tooltips are enabled.
	 */
	public void setEnabled(boolean enabled) {
		getConfiguration().getTooltips().setEnabled(enabled);
	}

	/**
	 * Returns if tooltips are enabled.
	 * 
	 * @return if tooltips are enabled. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public boolean isEnabled() {
		return getConfiguration().getTooltips().isEnabled();
	}

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public void setMode(InteractionMode mode) {
		getConfiguration().getTooltips().setMode(mode);
	}

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public InteractionMode getMode() {
		return getConfiguration().getTooltips().getMode();
	}

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @param intersect if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the
	 *            mode will be applied at all times.
	 */
	public void setIntersect(boolean intersect) {
		getConfiguration().getTooltips().setIntersect(intersect);
	}

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @return if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode
	 *         will be applied at all times. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public boolean isIntersect() {
		return getConfiguration().getTooltips().isIntersect();
	}

	/**
	 * Sets the mode for positioning the tooltip.
	 * 
	 * @param position the mode for positioning the tooltip.
	 * @see org.pepstock.charba.client.enums.TooltipPosition
	 */
	public void setPosition(TooltipPosition position) {
		getConfiguration().getTooltips().setPosition(position);
	}

	/**
	 * Returns the mode for positioning the tooltip.
	 * 
	 * @return mode for positioning the tooltip. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 * @see org.pepstock.charba.client.enums.TooltipPosition
	 */
	public TooltipPosition getPosition() {
		return getConfiguration().getTooltips().getPosition();
	}

	/**
	 * Sets the background color of the tooltip.
	 * 
	 * @param backgroundColor background color of the tooltip.
	 */
	public void setBackgroundColor(IsColor backgroundColor) {
		getConfiguration().getTooltips().setBackgroundColor(backgroundColor);
	}

	/**
	 * Sets the background color of the tooltip.
	 * 
	 * @param backgroundColor background color of the tooltip.
	 */
	public void setBackgroundColor(String backgroundColor) {
		getConfiguration().getTooltips().setBackgroundColor(backgroundColor);
	}

	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return Background color of the tooltip. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public String getBackgroundColorAsString() {
		return getConfiguration().getTooltips().getBackgroundColorAsString();
	}

	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return Background color of the tooltip. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public IsColor getBackgroundColor() {
		return getConfiguration().getTooltips().getBackgroundColor();
	}
	/**
	 * Sets the title font.
	 * 
	 * @param titleFontFamily title font.
	 */
	public void setTitleFontFamily(String titleFontFamily) {
		getConfiguration().getTooltips().setTitleFontFamily(titleFontFamily);
	}

	/**
	 * Returns the title font.
	 * 
	 * @return the title font. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public String getTitleFontFamily() {
		return getConfiguration().getTooltips().getTitleFontFamily();
	}

	/**
	 * Sets the title font size.
	 * 
	 * @param titleFontSize Title font size.
	 */
	public void setTitleFontSize(int titleFontSize) {
		getConfiguration().getTooltips().setTitleFontSize(titleFontSize);
	}

	/**
	 * Returns the title font size.
	 * 
	 * @return Title font size. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getTitleFontSize() {
		return getConfiguration().getTooltips().getTitleFontSize();
	}

	/**
	 * Sets the title font style.
	 * 
	 * @param titleFontStyle title font style.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setTitleFontStyle(FontStyle titleFontStyle) {
		getConfiguration().getTooltips().setTitleFontStyle(titleFontStyle);
	}

	/**
	 * Returns the title font style.
	 * 
	 * @return title font style. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getTitleFontStyle() {
		return getConfiguration().getTooltips().getTitleFontStyle();
	}
	
	/**
	 * Sets the title alignment.
	 * 
	 * @param align title alignment.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public void setTitleAlign(TextAlign align) {
		getConfiguration().getTooltips().setTitleAlign(align);
	}

	/**
	 * Returns the title alignment.
	 * 
	 * @return title alignment. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public TextAlign getTitleAlign() {
		return getConfiguration().getTooltips().getTitleAlign();
	}

	/**
	 * Sets the title font color.
	 * 
	 * @param titleFontColor title font color.
	 */
	public void setTitleFontColor(IsColor titleFontColor) {
		getConfiguration().getTooltips().setTitleFontColor(titleFontColor);
	}
	
	/**
	 * Sets the title font color.
	 * 
	 * @param titleFontColor title font color.
	 */
	public void setTitleFontColor(String titleFontColor) {
		getConfiguration().getTooltips().setTitleFontColor(titleFontColor);
	}

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public String getTitleFontColorAsString() {
		return getConfiguration().getTooltips().getTitleFontColorAsString();
	}

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public IsColor getTitleFontColor() {
		return getConfiguration().getTooltips().getTitleFontColor();
	}

	/**
	 * Sets the spacing to add to top and bottom of each title line.
	 * 
	 * @param titleSpacing spacing to add to top and bottom of each title line.
	 */
	public void setTitleSpacing(int titleSpacing) {
		getConfiguration().getTooltips().setTitleSpacing(titleSpacing);
	}

	/**
	 * Returns the spacing to add to top and bottom of each title line.
	 * 
	 * @return spacing to add to top and bottom of each title line. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getTitleSpacing() {
		return getConfiguration().getTooltips().getTitleSpacing();
	}

	/**
	 * Sets the margin to add on bottom of title section.
	 * 
	 * @param titleMarginBottom margin to add on bottom of title section.
	 */
	public void setTitleMarginBottom(int titleMarginBottom) {
		getConfiguration().getTooltips().setTitleMarginBottom(titleMarginBottom);
	}

	/**
	 * Returns the margin to add on bottom of title section.
	 * 
	 * @return margin to add on bottom of title section. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getTitleMarginBottom() {
		return getConfiguration().getTooltips().getTitleMarginBottom();
	}

	/**
	 * Sets the body line font.
	 * 
	 * @param bodyFontFamily body line font.
	 */
	public void setBodyFontFamily(String bodyFontFamily) {
		getConfiguration().getTooltips().setBodyFontFamily(bodyFontFamily);
	}

	/**
	 * Returns the body line font.
	 * 
	 * @return body line font. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public String getBodyFontFamily() {
		return getConfiguration().getTooltips().getBodyFontFamily();
	}

	/**
	 * Sets the body font size.
	 * 
	 * @param bodyFontSize body font size.
	 */
	public void setBodyFontSize(int bodyFontSize) {
		getConfiguration().getTooltips().setBodyFontSize(bodyFontSize);
	}

	/**
	 * Returns the body font size.
	 * 
	 * @return body font size. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getBodyFontSize() {
		return getConfiguration().getTooltips().getBodyFontSize();
	}

	/**
	 * Sets the body font style.
	 * 
	 * @param bodyFontStyle body font style.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setBodyFontStyle(FontStyle bodyFontStyle) {
		getConfiguration().getTooltips().setBodyFontStyle(bodyFontStyle);
	}

	/**
	 * Returns the body font style.
	 * 
	 * @return body font style. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getBodyFontStyle() {
		return getConfiguration().getTooltips().getBodyFontStyle();
	}

	/**
	 * Sets the body alignment.
	 * 
	 * @param align body alignment.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public void setBodyAlign(TextAlign align) {
		getConfiguration().getTooltips().setBodyAlign(align);
	}

	/**
	 * Returns the body alignment.
	 * 
	 * @return body alignment. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public TextAlign getBodyAlign() {
		return getConfiguration().getTooltips().getBodyAlign();
	}

	/**
	 * Sets the body font color.
	 * 
	 * @param bodyFontColor body font color.
	 */
	public void setBodyFontColor(IsColor bodyFontColor) {
		getConfiguration().getTooltips().setBodyFontColor(bodyFontColor);
	}
	
	/**
	 * Sets the body font color.
	 * 
	 * @param bodyFontColor body font color.
	 */
	public void setBodyFontColor(String bodyFontColor) {
		getConfiguration().getTooltips().setBodyFontColor(bodyFontColor);
	}

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public String getBodyFontColorAsString() {
		return getConfiguration().getTooltips().getBodyFontColorAsString();
	}

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public IsColor getBodyFontColor() {
		return getConfiguration().getTooltips().getBodyFontColor();
	}
	
	/**
	 * Sets the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @param bodySpacing spacing to add to top and bottom of each tooltip item.
	 */
	public void setBodySpacing(int bodySpacing) {
		getConfiguration().getTooltips().setBodySpacing(bodySpacing);
	}

	/**
	 * Returns the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @return spacing to add to top and bottom of each tooltip item. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getBodySpacing() {
		return getConfiguration().getTooltips().getBodySpacing();
	}

	/**
	 * Sets the footer font.
	 * 
	 * @param footerFontFamily footer font.
	 */
	public void setFooterFontFamily(String footerFontFamily) {
		getConfiguration().getTooltips().setFooterFontFamily(footerFontFamily);
	}

	/**
	 * Returns the footer font.
	 * 
	 * @return footer font. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public String getFooterFontFamily() {
		return getConfiguration().getTooltips().getBodyFontFamily();
	}

	/**
	 * Sets the footer font size.
	 * 
	 * @param footerFontSize footer font size.
	 */
	public void setFooterFontSize(int footerFontSize) {
		getConfiguration().getTooltips().setFooterFontSize(footerFontSize);
	}

	/**
	 * Returns the footer font size.
	 * 
	 * @return footer font size. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getFooterFontSize() {
		return getConfiguration().getTooltips().getFooterFontSize();
	}

	/**
	 * Sets the footer font style.
	 * 
	 * @param footerFontStyle the footer font style.
	 */
	public void setFooterFontStyle(FontStyle footerFontStyle) {
		getConfiguration().getTooltips().setFooterFontStyle(footerFontStyle);
	}

	/**
	 * Returns the footer font style.
	 * 
	 * @return footer font style. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFooterFontStyle() {
		return getConfiguration().getTooltips().getFooterFontStyle();
	}

	/**
	 * Sets the footer alignment.
	 * 
	 * @param align footer alignment.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public void setFooterAlign(TextAlign align) {
		getConfiguration().getTooltips().setFooterAlign(align);
	}

	/**
	 * Returns the footer alignment.
	 * 
	 * @return footer alignment. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 * @see org.pepstock.charba.client.enums.TextAlign
	 */
	public TextAlign getFooterAlign() {
		return getConfiguration().getTooltips().getFooterAlign();
	}
	
	/**
	 * Sets the footer font color.
	 * 
	 * @param footerFontColor footer font color.
	 */
	public void setFooterFontColor(IsColor footerFontColor) {
		getConfiguration().getTooltips().setFooterFontColor(footerFontColor);
	}
	
	/**
	 * Sets the footer font color.
	 * 
	 * @param footerFontColor footer font color.
	 */
	public void setFooterFontColor(String footerFontColor) {
		getConfiguration().getTooltips().setFooterFontColor(footerFontColor);
	}

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public String getFooterFontColorAsString() {
		return getConfiguration().getTooltips().getFooterFontColorAsString();
	}

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public IsColor getFooterFontColor() {
		return getConfiguration().getTooltips().getFooterFontColor();
	}

	/**
	 * Sets the spacing to add to top and bottom of each footer line.
	 * 
	 * @param footerSpacing spacing to add to top and bottom of each footer line.
	 */
	public void setFooterSpacing(int footerSpacing) {
		getConfiguration().getTooltips().setFooterSpacing(footerSpacing);
	}

	/**
	 * Returns the spacing to add to top and bottom of each footer line.
	 * 
	 * @return spacing to add to top and bottom of each footer line. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getFooterSpacing() {
		return getConfiguration().getTooltips().getFooterSpacing();
	}

	/**
	 * Sets the margin to add before drawing the footer.
	 * 
	 * @param footerMarginTop margin to add before drawing the footer.
	 */
	public void setFooterMarginTop(int footerMarginTop) {
		getConfiguration().getTooltips().setFooterMarginTop(footerMarginTop);
	}

	/**
	 * Returns the margin to add before drawing the footer.
	 * 
	 * @return margin to add before drawing the footer. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getFooterMarginTop() {
		return getConfiguration().getTooltips().getFooterMarginTop();
	}

	/**
	 * Sets the padding to add on left and right of tooltip.
	 * 
	 * @param xPadding padding to add on left and right of tooltip.
	 */
	public void setXPadding(int xPadding) {
		getConfiguration().getTooltips().setXPadding(xPadding);
	}

	/**
	 * Returns the padding to add on left and right of tooltip.
	 * 
	 * @return padding to add on left and right of tooltip. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getXPadding() {
		return getConfiguration().getTooltips().getXPadding();
	}

	/**
	 * Sets the padding to add on top and bottom of tooltip.
	 * 
	 * @param yPadding padding to add on top and bottom of tooltip.
	 */
	public void setYPadding(int yPadding) {
		getConfiguration().getTooltips().setYPadding(yPadding);
	}

	/**
	 * Returns the padding to add on top and bottom of tooltip.
	 * 
	 * @return padding to add on top and bottom of tooltip. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getYPadding() {
		return getConfiguration().getTooltips().getYPadding();
	}

	/**
	 * Sets the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @param caretPadding extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	public void setCaretPadding(int caretPadding) {
		getConfiguration().getTooltips().setCaretPadding(caretPadding);
	}

	/**
	 * Returns the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @return extra distance to move the end of the tooltip arrow away from the tooltip point. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getCaretPadding() {
		return getConfiguration().getTooltips().getCaretPadding();
	}

	/**
	 * Sets the size, in px, of the tooltip arrow.
	 * 
	 * @param caretSize size, in px, of the tooltip arrow.
	 */
	public void setCaretSize(int caretSize) {
		getConfiguration().getTooltips().setCaretSize(caretSize);
	}

	/**
	 * Returns the size, in px, of the tooltip arrow.
	 * 
	 * @return size, in px, of the tooltip arrow. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getCaretSize() {
		return getConfiguration().getTooltips().getCaretSize();
	}

	/**
	 * Sets the radius of tooltip corner curves.
	 * 
	 * @param cornerRadius radius of tooltip corner curves.
	 */
	public void setCornerRadius(int cornerRadius) {
		getConfiguration().getTooltips().setCornerRadius(cornerRadius);
	}

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getCornerRadius() {
		return getConfiguration().getTooltips().getCornerRadius();
	}

	/**
	 * Sets the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @param multiKeyBackground color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	public void setMultiKeyBackground(IsColor multiKeyBackground) {
		getConfiguration().getTooltips().setMultiKeyBackground(multiKeyBackground);
	}

	/**
	 * Sets the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @param multiKeyBackground color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	public void setMultiKeyBackground(String multiKeyBackground) {
		getConfiguration().getTooltips().setMultiKeyBackground(multiKeyBackground);
	}

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public String getMultiKeyBackgroundAsString() {
		return getConfiguration().getTooltips().getMultiKeyBackgroundAsString();
	}

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public IsColor getMultiKeyBackground() {
		return getConfiguration().getTooltips().getMultiKeyBackground();
	}

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @param displayColors if true, color boxes are shown in the tooltip.
	 */
	public void setDisplayColors(boolean displayColors) {
		getConfiguration().getTooltips().setDisplayColors(displayColors);
	}

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @return if true, color boxes are shown in the tooltip. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public boolean isDisplayColors() {
		return getConfiguration().getTooltips().isDisplayColors();
	}

	/**
	 * Sets the color of the border.
	 * 
	 * @param borderColor color of the border.
	 */
	public void setBorderColor(IsColor borderColor) {
		getConfiguration().getTooltips().setBorderColor(borderColor);
	}
	
	/**
	 * Sets the color of the border.
	 * 
	 * @param borderColor color of the border.
	 */
	public void setBorderColor(String borderColor) {
		getConfiguration().getTooltips().setBorderColor(borderColor);
	}

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public String getBorderColorAsString() {
		return getConfiguration().getTooltips().getBorderColorAsString();
	}

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public IsColor getBorderColor() {
		return getConfiguration().getTooltips().getBorderColor();
	}

	/**
	 * Sets the size of the border.
	 * 
	 * @param borderWidth size of the border.
	 */
	public void setBorderWidth(int borderWidth) {
		getConfiguration().getTooltips().setBorderWidth(borderWidth);
	}

	/**
	 * Returns the size of the border.
	 * 
	 * @return size of the border. For default value, see {@link org.pepstock.charba.client.GlobalOptions#getTooltips()}.
	 */
	public int getBorderWidth() {
		return getConfiguration().getTooltips().getBorderWidth();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipFilterHandler#onFilter(org.pepstock.charba.client.jsinterop.items.TooltipItem)
	 */
	@Override
	public boolean onFilter(TooltipItem item) {
		// checks if callback is consistent
		if (getFilterCallback() != null) {
			// calls callback
			return getFilterCallback().onFilter(getChart(), item);
		} else if (Defaults.chart(getChart()).getTooltips().getFilterCallback() != null) {
			// calls callback
			return Defaults.chart(getChart()).getTooltips().getFilterCallback().onFilter(getChart(), item);
		} else if (Defaults.getGlobal().getTooltips().getFilterCallback() != null) {
			// calls callback
			return Defaults.getGlobal().getTooltips().getFilterCallback().onFilter(getChart(), item);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipItemSortHandler#onItemSort(org.pepstock.charba.client.jsinterop.items.TooltipItem, org.pepstock.charba.client.jsinterop.items.TooltipItem)
	 */
	@Override
	public int onItemSort(TooltipItem item1, TooltipItem item2) {
		// checks if callback is consistent
		if (getItemSortCallback() != null) {
			// calls callback
			return getItemSortCallback().onItemSort(getChart(), item1, item2);
		} else if (Defaults.chart(getChart()).getTooltips().getItemSortCallback() != null) {
			// calls callback
			return Defaults.chart(getChart()).getTooltips().getItemSortCallback().onItemSort(getChart(), item1, item2);
		} else if (Defaults.getGlobal().getTooltips().getItemSortCallback() != null) {
			// calls callback
			return Defaults.getGlobal().getTooltips().getItemSortCallback().onItemSort(getChart(), item1, item2);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipCustomHandler#onCustom(org.pepstock.charba.client.jsinterop.items.TooltipModel)
	 */
	@Override
	public void onCustom(TooltipModel model) {
		// checks if callback is consistent
		if (getCustomCallback() != null) {
			// calls callback
			getCustomCallback().onCustom(getChart(), model);
		} else if (Defaults.chart(getChart()).getTooltips().getCustomCallback() != null) {
			// calls callback
			Defaults.chart(getChart()).getTooltips().getCustomCallback().onCustom(getChart(), model);
		} else if (Defaults.getGlobal().getTooltips().getCustomCallback() != null) {
			// calls callback
			Defaults.getGlobal().getTooltips().getCustomCallback().onCustom(getChart(), model);
		}
	}

}