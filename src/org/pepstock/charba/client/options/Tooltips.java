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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.callbacks.TooltipCustomCallback;
import org.pepstock.charba.client.callbacks.TooltipFilterHandler;
import org.pepstock.charba.client.callbacks.TooltipItemSortHandler;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.TooltipPosition;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.TooltipModel;

/**
 * Configuration element to set all attributes and features of the tooltip.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Tooltips extends ChartContainer {

	private final TooltipsCallbacks callbacks;

	private TooltipCustomCallback customCallback = null;

	private TooltipItemSortHandler itemSortHandler = null;

	private TooltipFilterHandler filterHandler = null;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		enabled,
		custom,
		mode,
		intersect,
		position,
		callbacks,
		itemSort,
		filter,
		backgroundColor,
		titleFontFamily,
		titleFontSize,
		titleFontStyle,
		titleFontColor,
		titleSpacing,
		titleMarginBottom,
		bodyFontFamily,
		bodyFontSize,
		bodyFontStyle,
		bodyFontColor,
		bodySpacing,
		footerFontFamily,
		footerFontSize,
		footerFontStyle,
		footerFontColor,
		footerSpacing,
		footerMarginTop,
		xPadding,
		yPadding,
		caretPadding,
		caretSize,
		cornerRadius,
		multiKeyBackground,
		displayColors,
		borderColor,
		borderWidth,
	}

	/**
	 * Builds the object storing the chart instance.<br>
	 * Sets also the internal parts of options.
	 * 
	 * @param chart chart instance
	 */
	Tooltips(AbstractChart<?, ?> chart) {
		super(chart);
		// sets callbacks container
		callbacks = new TooltipsCallbacks(this);
		setValue(Property.callbacks, callbacks);
	}

	/**
	 * @return the callbacks
	 */
	public TooltipsCallbacks getCallbacks() {
		return callbacks;
	}

	/**
	 * @return the customCallback
	 */
	public TooltipCustomCallback getCustomCallback() {
		return customCallback;
	}

	/**
	 * @param customCallback the customCallback to set
	 */
	public void setCustomCallback(TooltipCustomCallback customCallback) {
		if (hasToBeRegistered(customCallback, Property.custom)) {
			registerNativeCustomHandlers(getJavaScriptObject());
		}
		this.customCallback = customCallback;
	}

	/**
	 * @return the itemSortHandler
	 */
	public TooltipItemSortHandler getItemSortHandler() {
		return itemSortHandler;
	}

	/**
	 * @param itemSortHandler the itemSortHandler to set
	 */
	public void setItemSortHandler(TooltipItemSortHandler itemSortHandler) {
		if (hasToBeRegistered(itemSortHandler, Property.itemSort)) {
			registerNativeItemSortHandlers(getJavaScriptObject());
		}
		this.itemSortHandler = itemSortHandler;
	}

	/**
	 * @return the filterHandler
	 */
	public TooltipFilterHandler getFilterHandler() {
		return filterHandler;
	}

	/**
	 * @param filterHandler the filterHandler to set
	 */
	public void setFilterHandler(TooltipFilterHandler filterHandler) {
		if (hasToBeRegistered(filterHandler, Property.filter)) {
			registerNativeFilterHandlers(getJavaScriptObject());
		}
		this.filterHandler = filterHandler;
	}

	/**
	 * Sets if tooltips are enabled.
	 * 
	 * @param enabled if tooltips are enabled.
	 */
	public void setEnabled(boolean enabled) {
		setValue(Property.enabled, enabled);
	}

	/**
	 * Returns if tooltips are enabled.
	 * 
	 * @return if tooltips are enabled.. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#isEnabled()}.
	 */
	public boolean isEnabled() {
		return getValue(Property.enabled, getChart().getGlobal().getTooltips().isEnabled());
	}

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public void setMode(InteractionMode mode) {
		setValue(Property.mode, mode);
	}

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getMode()}.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public InteractionMode getMode() {
		return getValue(Property.mode, InteractionMode.class, getChart().getGlobal().getTooltips().getMode());
	}

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @param intersect if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the
	 *            mode will be applied at all times.
	 */
	public void setIntersect(boolean intersect) {
		setValue(Property.intersect, intersect);
	}

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @return if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode
	 *         will be applied at all times. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#isIntersect()}.
	 */
	public boolean isIntersect() {
		return getValue(Property.intersect, getChart().getGlobal().getTooltips().isIntersect());
	}

	/**
	 * Sets the mode for positioning the tooltip.
	 * 
	 * @param position the mode for positioning the tooltip.
	 * @see org.pepstock.charba.client.enums.TooltipPosition
	 */
	public void setPosition(TooltipPosition position) {
		setValue(Property.position, position);
	}

	/**
	 * Returns the mode for positioning the tooltip.
	 * 
	 * @return mode for positioning the tooltip. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getPosition()}.
	 * @see org.pepstock.charba.client.enums.TooltipPosition
	 */
	public TooltipPosition getPosition() {
		return getValue(Property.position, TooltipPosition.class, getChart().getGlobal().getTooltips().getPosition());
	}

	/**
	 * Sets the background color of the tooltip.
	 * 
	 * @param backgroundColor background color of the tooltip.
	 */
	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return Background color of the tooltip. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getBackgroundColor()}.
	 */
	public String getBackgroundColor() {
		return getValue(Property.backgroundColor, getChart().getGlobal().getTooltips().getBackgroundColor());
	}

	/**
	 * Sets the title font.
	 * 
	 * @param titleFontFamily title font.
	 */
	public void setTitleFontFamily(String titleFontFamily) {
		setValue(Property.titleFontFamily, titleFontFamily);
	}

	/**
	 * Returns the title font.
	 * 
	 * @return the title font. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getTitleFontFamily()()}.
	 */
	public String getTitleFontFamily() {
		return getValue(Property.titleFontFamily, getChart().getGlobal().getTooltips().getTitleFontFamily());
	}

	/**
	 * Sets the title font size.
	 * 
	 * @param titleFontSize Title font size.
	 */
	public void setTitleFontSize(int titleFontSize) {
		setValue(Property.titleFontSize, titleFontSize);
	}

	/**
	 * Returns the title font size.
	 * 
	 * @return Title font size. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getTitleFontSize()}.
	 */
	public int getTitleFontSize() {
		return getValue(Property.titleFontSize, getChart().getGlobal().getTooltips().getTitleFontSize());
	}

	/**
	 * Sets the title font style.
	 * 
	 * @param titleFontStyle title font style.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setTitleFontStyle(FontStyle titleFontStyle) {
		setValue(Property.titleFontStyle, titleFontStyle);
	}

	/**
	 * Returns the title font style.
	 * 
	 * @return title font style. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getTitleFontStyle()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getTitleFontStyle() {
		return getValue(Property.titleFontStyle, FontStyle.class, getChart().getGlobal().getTooltips().getTitleFontStyle());
	}

	/**
	 * Sets the title font color.
	 * 
	 * @param titleFontColor title font color.
	 */
	public void setTitleFontColor(String titleFontColor) {
		setValue(Property.titleFontColor, titleFontColor);
	}

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color.Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getTitleFontColor()}.
	 */
	public String getTitleFontColor() {
		return getValue(Property.titleFontColor, getChart().getGlobal().getTooltips().getTitleFontColor());
	}

	/**
	 * Sets the spacing to add to top and bottom of each title line.
	 * 
	 * @param titleSpacing spacing to add to top and bottom of each title line.
	 */
	public void setTitleSpacing(int titleSpacing) {
		setValue(Property.titleSpacing, titleSpacing);
	}

	/**
	 * Returns the spacing to add to top and bottom of each title line.
	 * 
	 * @return spacing to add to top and bottom of each title line. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getTitleSpacing()}.
	 */
	public int getTitleSpacing() {
		return getValue(Property.titleSpacing, getChart().getGlobal().getTooltips().getTitleSpacing());
	}

	/**
	 * Sets the margin to add on bottom of title section.
	 * 
	 * @param titleMarginBottom margin to add on bottom of title section.
	 */
	public void setTitleMarginBottom(int titleMarginBottom) {
		setValue(Property.titleMarginBottom, titleMarginBottom);
	}

	/**
	 * Returns the margin to add on bottom of title section.
	 * 
	 * @return margin to add on bottom of title section. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getTitleMarginBottom()}.
	 */
	public int getTitleMarginBottom() {
		return getValue(Property.titleMarginBottom, getChart().getGlobal().getTooltips().getTitleMarginBottom());
	}

	/**
	 * Sets the body line font.
	 * 
	 * @param bodyFontFamily body line font.
	 */
	public void setBodyFontFamily(String bodyFontFamily) {
		setValue(Property.bodyFontFamily, bodyFontFamily);
	}

	/**
	 * Returns the body line font.
	 * 
	 * @return body line font. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getBodyFontFamily()}.
	 */
	public String getBodyFontFamily() {
		return getValue(Property.bodyFontFamily, getChart().getGlobal().getTooltips().getBodyFontFamily());
	}

	/**
	 * Sets the body font size.
	 * 
	 * @param bodyFontSize body font size.
	 */
	public void setBodyFontSize(int bodyFontSize) {
		setValue(Property.bodyFontSize, bodyFontSize);
	}

	/**
	 * Returns the body font size.
	 * 
	 * @return body font size. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getBodyFontSize()}.
	 */
	public int getBodyFontSize() {
		return getValue(Property.bodyFontSize, getChart().getGlobal().getTooltips().getBodyFontSize());
	}

	/**
	 * Sets the body font style.
	 * 
	 * @param bodyFontStyle body font style.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setBodyFontStyle(FontStyle bodyFontStyle) {
		setValue(Property.bodyFontStyle, bodyFontStyle);
	}

	/**
	 * Returns the body font style.
	 * 
	 * @return body font style. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getBodyFontSize()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getBodyFontStyle() {
		return getValue(Property.bodyFontStyle, FontStyle.class, getChart().getGlobal().getTooltips().getBodyFontStyle());
	}

	/**
	 * Sets the body font color.
	 * 
	 * @param bodyFontColor body font color.
	 */
	public void setBodyFontColor(String bodyFontColor) {
		setValue(Property.bodyFontColor, bodyFontColor);
	}

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getBodyFontColor()}.
	 */
	public String getBodyFontColor() {
		return getValue(Property.bodyFontColor, getChart().getGlobal().getTooltips().getBodyFontColor());
	}

	/**
	 * Sets the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @param bodySpacing spacing to add to top and bottom of each tooltip item.
	 */
	public void setBodySpacing(int bodySpacing) {
		setValue(Property.bodySpacing, bodySpacing);
	}

	/**
	 * Returns the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @return spacing to add to top and bottom of each tooltip item. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getBodySpacing()}.
	 */
	public int getBodySpacing() {
		return getValue(Property.bodySpacing, getChart().getGlobal().getTooltips().getBodySpacing());
	}

	/**
	 * Sets the footer font.
	 * 
	 * @param footerFontFamily footer font.
	 */
	public void setFooterFontFamily(String footerFontFamily) {
		setValue(Property.footerFontFamily, footerFontFamily);
	}

	/**
	 * Returns the footer font.
	 * 
	 * @return footer font. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getBodyFontFamily()}.
	 */
	public String getFooterFontFamily() {
		return getValue(Property.footerFontFamily, getChart().getGlobal().getTooltips().getBodyFontFamily());
	}

	/**
	 * Sets the footer font size.
	 * 
	 * @param footerFontSize footer font size.
	 */
	public void setFooterFontSize(int footerFontSize) {
		setValue(Property.footerFontSize, footerFontSize);
	}

	/**
	 * Returns the footer font size.
	 * 
	 * @return footer font size. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getBodyFontSize()}.
	 */
	public int getFooterFontSize() {
		return getValue(Property.footerFontSize, getChart().getGlobal().getTooltips().getFooterFontSize());
	}

	/**
	 * Sets the footer font style.
	 * 
	 * @param footerFontStyle the footer font style.
	 */
	public void setFooterFontStyle(FontStyle footerFontStyle) {
		setValue(Property.footerFontStyle, footerFontStyle);
	}

	/**
	 * Returns the footer font style.
	 * 
	 * @return footer font style. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getFooterFontStyle()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFooterFontStyle() {
		return getValue(Property.footerFontStyle, FontStyle.class, getChart().getGlobal().getTooltips().getFooterFontStyle());
	}

	/**
	 * Sets the footer font color.
	 * 
	 * @param footerFontColor footer font color.
	 */
	public void setFooterFontColor(String footerFontColor) {
		setValue(Property.footerFontColor, footerFontColor);
	}

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getFooterFontColor()}.
	 */
	public String getFooterFontColor() {
		return getValue(Property.footerFontColor, getChart().getGlobal().getTooltips().getFooterFontColor());
	}

	/**
	 * Sets the spacing to add to top and bottom of each footer line.
	 * 
	 * @param footerSpacing spacing to add to top and bottom of each footer line.
	 */
	public void setFooterSpacing(int footerSpacing) {
		setValue(Property.footerSpacing, footerSpacing);
	}

	/**
	 * Returns the spacing to add to top and bottom of each footer line.
	 * 
	 * @return spacing to add to top and bottom of each footer line. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getFooterSpacing()}.
	 */
	public int getFooterSpacing() {
		return getValue(Property.footerSpacing, getChart().getGlobal().getTooltips().getFooterSpacing());
	}

	/**
	 * Sets the margin to add before drawing the footer.
	 * 
	 * @param footerMarginTop margin to add before drawing the footer.
	 */
	public void setFooterMarginTop(int footerMarginTop) {
		setValue(Property.footerMarginTop, footerMarginTop);
	}

	/**
	 * Returns the margin to add before drawing the footer.
	 * 
	 * @return margin to add before drawing the footer. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getFooterMarginTop()}.
	 */
	public int getFooterMarginTop() {
		return getValue(Property.footerMarginTop, getChart().getGlobal().getTooltips().getFooterMarginTop());
	}

	/**
	 * Sets the padding to add on left and right of tooltip.
	 * 
	 * @param xPadding padding to add on left and right of tooltip.
	 */
	public void setXPadding(int xPadding) {
		setValue(Property.xPadding, xPadding);
	}

	/**
	 * Returns the padding to add on left and right of tooltip.
	 * 
	 * @return padding to add on left and right of tooltip. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getXPadding()}.
	 */
	public int getXPadding() {
		return getValue(Property.xPadding, getChart().getGlobal().getTooltips().getXPadding());
	}

	/**
	 * Sets the padding to add on top and bottom of tooltip.
	 * 
	 * @param yPadding padding to add on top and bottom of tooltip.
	 */
	public void setYPadding(int yPadding) {
		setValue(Property.yPadding, yPadding);
	}

	/**
	 * Returns the padding to add on top and bottom of tooltip.
	 * 
	 * @return padding to add on top and bottom of tooltip. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getYPadding()}.
	 */
	public int getYPadding() {
		return getValue(Property.yPadding, getChart().getGlobal().getTooltips().getYPadding());
	}

	/**
	 * Sets the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @param caretPadding extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	public void setCaretPadding(int caretPadding) {
		setValue(Property.caretPadding, caretPadding);
	}

	/**
	 * Returns the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @return extra distance to move the end of the tooltip arrow away from the tooltip point. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getCaretPadding()}.
	 */
	public int getCaretPadding() {
		return getValue(Property.caretPadding, getChart().getGlobal().getTooltips().getCaretPadding());
	}

	/**
	 * Sets the size, in px, of the tooltip arrow.
	 * 
	 * @param caretSize size, in px, of the tooltip arrow.
	 */
	public void setCaretSize(int caretSize) {
		setValue(Property.caretSize, caretSize);
	}

	/**
	 * Returns the size, in px, of the tooltip arrow.
	 * 
	 * @return size, in px, of the tooltip arrow. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getCaretSize()}.
	 */
	public int getCaretSize() {
		return getValue(Property.caretSize, getChart().getGlobal().getTooltips().getCaretSize());
	}

	/**
	 * Sets the radius of tooltip corner curves.
	 * 
	 * @param cornerRadius radius of tooltip corner curves.
	 */
	public void setCornerRadius(int cornerRadius) {
		setValue(Property.cornerRadius, cornerRadius);
	}

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getCornerRadius()}.
	 */
	public int getCornerRadius() {
		return getValue(Property.cornerRadius, getChart().getGlobal().getTooltips().getCornerRadius());
	}

	/**
	 * Sets the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @param multiKeyBackground color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	public void setMultiKeyBackground(String multiKeyBackground) {
		setValue(Property.multiKeyBackground, multiKeyBackground);
	}

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getMultiKeyBackground()}.
	 */
	public String getMultiKeyBackground() {
		return getValue(Property.multiKeyBackground, getChart().getGlobal().getTooltips().getMultiKeyBackground());
	}

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @param displayColors if true, color boxes are shown in the tooltip.
	 */
	public void setDisplayColors(boolean displayColors) {
		setValue(Property.displayColors, displayColors);
	}

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @return if true, color boxes are shown in the tooltip. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#isDisplayColors()}.
	 */
	public boolean isDisplayColors() {
		return getValue(Property.displayColors, getChart().getGlobal().getTooltips().isDisplayColors());
	}

	/**
	 * Sets the color of the border.
	 * 
	 * @param borderColor color of the border.
	 */
	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getBorderColor()}.
	 */
	public String getBorderColor() {
		return getValue(Property.borderColor, getChart().getGlobal().getTooltips().getBorderColor());
	}

	/**
	 * Sets the size of the border.
	 * 
	 * @param borderWidth size of the border.
	 */
	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	/**
	 * Returns the size of the border.
	 * 
	 * @return size of the border. Default is {@link org.pepstock.charba.client.defaults.global.Tooltips#getBorderWidth()}.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, getChart().getGlobal().getTooltips().getBorderWidth());
	}

	/**
	 * Custom tooltips allow you to hook into the tooltip rendering process so that you can render the tooltip in your own
	 * custom way.<br>
	 * Generally this is used to create an HTML tooltip instead of an oncanvas one.
	 * 
	 * @param model tooltip model contains parameters that can be used to render the tooltip.
	 */
	protected void onCustom(TooltipModel model) {
		// checks if callback is consistent
		if (customCallback != null) {
			// calls callback
			customCallback.onCustom(getChart(), model);
		}
	}

	/**
	 * Called to allow sorting of tooltip items.
	 * 
	 * @param o1 first element of tooltip.
	 * @param o2 second element of tooltip.
	 * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the
	 *         second.
	 */
	protected int onItemSort(TooltipItem o1, TooltipItem o2) {
		// checks if callback is consistent
		if (itemSortHandler != null) {
			// calls callback
			return itemSortHandler.onItemSort(getChart(), o1, o2);
		}
		// default is 0
		return 0;
	}

	/**
	 * Called to allow filtering of tooltip items.
	 * 
	 * @param item element of tooltip
	 * @return <code>false</code> to remove the item from the container, otherwise <code>true</code>.
	 */
	protected boolean onFilter(TooltipItem item) {
		// checks if callback is consistent
		if (filterHandler != null) {
			// calls callback
			return filterHandler.onFilter(getChart(), item);
		}
		// default is true
		return true;
	}

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeCustomHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.custom = function(tooltipModel){
        	self.@org.pepstock.charba.client.options.Tooltips::onCustom(Lorg/pepstock/charba/client/items/TooltipModel;)(tooltipModel);
	    }
	}-*/;
    
	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeItemSortHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.itemSort = function(item1, item2, data){
        	return self.@org.pepstock.charba.client.options.Tooltips::onItemSort(Lorg/pepstock/charba/client/items/TooltipItem;Lorg/pepstock/charba/client/items/TooltipItem;)(item1, item2);
	    }
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeFilterHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.filter = function(item){
        	return self.@org.pepstock.charba.client.options.Tooltips::onFilter(Lorg/pepstock/charba/client/items/TooltipItem;)(item);
	    }
	}-*/;

}