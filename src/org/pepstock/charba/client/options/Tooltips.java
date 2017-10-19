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
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.TooltipPosition;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.TooltipModel;

/**
 * 
 */
public final class Tooltips extends AbstractLabel{
	
	private static final boolean DEFAULT_ENABLED = true;

	private static final boolean DEFAULT_INTERSECT = true;

	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.8)";

	private static final String DEFAULT_TITLE_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private static final int DEFAULT_TITLE_FONT_SIZE = 12;

	private static final String DEFAULT_TITLE_FONT_COLOR = "#fff";

	private static final int DEFAULT_TITLE_SPACING = 2;

	private static final int DEFAULT_TITLE_MARGIN_BOTTOM = 6;

	private static final String DEFAULT_BODY_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private static final int DEFAULT_BODY_FONT_SIZE = 12;

	private static final String DEFAULT_BODY_FONT_COLOR = "#fff";

	private static final int DEFAULT_BODY_SPACING = 2;

	private static final String DEFAULT_FOOTER_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private static final int DEFAULT_FOOTER_FONT_SIZE = 12;

	private static final String DEFAULT_FOOTER_FONT_COLOR = "#fff";

	private static final int DEFAULT_FOOTER_SPACING = 2;

	private static final int DEFAULT_FOOTER_MARGIN_TOP = 6;

	private static final int DEFAULT_X_PADDING = 6;

	private static final int DEFAULT_Y_PADDING = 6;

	private static final int DEFAULT_CARET_PADDING = 2;

	private static final int DEFAULT_CARET_SIZE = 5;

	private static final int DEFAULT_CORNER_RADIUS = 6;

	private static final String DEFAULT_MULTI_KEY_BACKGROUND = "#fff";

	private static final boolean DEFAULT_DISPLAY_COLORS = true;

	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0)";

	private static final int DEFAULT_BORDER_WIDTH = 0;
	
	private final TooltipsCallbacks callbacks;
	
	private TooltipCustomCallback customCallback = null;

	private TooltipItemSortHandler itemSortHandler = null;
	
	private TooltipFilterHandler filterHandler = null;
	
	private enum Property implements Key {
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
	
	Tooltips(AbstractChart<?, ?> chart) {
		super(chart);
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
		if (hasToBeRegistered(customCallback, Property.custom)){
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
		if (hasToBeRegistered(itemSortHandler, Property.itemSort)){
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
		if (hasToBeRegistered(filterHandler, Property.filter)){
			registerNativeFilterHandlers(getJavaScriptObject());
		}
		this.filterHandler = filterHandler;
	}

	public void setEnabled(boolean enabled) {
		setValue(Property.enabled, enabled);
	}

	public boolean isEnabled() {
		return getValue(Property.enabled, DEFAULT_ENABLED);
	}

	public void setMode(InteractionMode mode) {
		setValue(Property.mode, mode);
	}

	public InteractionMode getMode() {
		return getValue(Property.mode, InteractionMode.class, InteractionMode.nearest);
	}

	public void setIntersect(boolean intersect) {
		setValue(Property.intersect, intersect);
	}

	public boolean isIntersect() {
		return getValue(Property.intersect, DEFAULT_INTERSECT);
	}

	public void setPosition(TooltipPosition position) {
		setValue(Property.position, position);
	}

	public TooltipPosition getPosition() {
		return getValue(Property.position, TooltipPosition.class, TooltipPosition.average);
	}

	public void setBackgroundColor(String backgroundColor) {
		setValue(Property.backgroundColor, backgroundColor);
	}

	public String getBackgroundColor() {
		return getValue(Property.backgroundColor, DEFAULT_BACKGROUND_COLOR);
	}

	public void setTitleFontFamily(String titleFontFamily) {
		setValue(Property.titleFontFamily, titleFontFamily);
	}

	public String getTitleFontFamily() {
		return getValue(Property.titleFontFamily, DEFAULT_TITLE_FONT_FAMILY);
	}

	public void setTitleFontSize(int titleFontSize) {
		setValue(Property.titleFontSize, titleFontSize);
	}

	public int getTitleFontSize() {
		return getValue(Property.titleFontSize, DEFAULT_TITLE_FONT_SIZE);
	}

	public void setTitleFontStyle(FontStyle titleFontStyle) {
		setValue(Property.titleFontStyle, titleFontStyle);
	}

	public FontStyle getTitleFontStyle() {
		return getValue(Property.titleFontStyle, FontStyle.class, FontStyle.bold);
	}

	public void setTitleFontColor(String titleFontColor) {
		setValue(Property.titleFontColor, titleFontColor);
	}

	public String getTitleFontColor() {
		return getValue(Property.titleFontColor, DEFAULT_TITLE_FONT_COLOR);
	}

	public void setTitleSpacing(int titleSpacing) {
		setValue(Property.titleSpacing, titleSpacing);
	}

	public int getTitleSpacing() {
		return getValue(Property.titleSpacing, DEFAULT_TITLE_SPACING);
	}

	public void setTitleMarginBottom(int titleMarginBottom) {
		setValue(Property.titleMarginBottom, titleMarginBottom);
	}

	public int getTitleMarginBottom() {
		return getValue(Property.titleMarginBottom, DEFAULT_TITLE_MARGIN_BOTTOM);
	}

	public void setBodyFontFamily(String bodyFontFamily) {
		setValue(Property.bodyFontFamily, bodyFontFamily);
	}

	public String getBodyFontFamily() {
		return getValue(Property.bodyFontFamily, DEFAULT_BODY_FONT_FAMILY);
	}

	public void setBodyFontSize(int bodyFontSize) {
		setValue(Property.bodyFontSize, bodyFontSize);
	}

	public int getBodyFontSize() {
		return getValue(Property.bodyFontSize, DEFAULT_BODY_FONT_SIZE);
	}

	public void setBodyFontStyle(FontStyle bodyFontStyle) {
		setValue(Property.bodyFontStyle, bodyFontStyle);
	}

	public FontStyle getBodyFontStyle() {
		return getValue(Property.bodyFontStyle, FontStyle.class, FontStyle.normal);
	}

	public void setBodyFontColor(String bodyFontColor) {
		setValue(Property.bodyFontColor, bodyFontColor);
	}

	public String getBodyFontColor() {
		return getValue(Property.bodyFontColor, DEFAULT_BODY_FONT_COLOR);
	}

	public void setBodySpacing(int bodySpacing) {
		setValue(Property.bodySpacing, bodySpacing);
	}

	public int getBodySpacing() {
		return getValue(Property.bodySpacing, DEFAULT_BODY_SPACING);
	}

	public void setFooterFontFamily(String footerFontFamily) {
		setValue(Property.footerFontFamily, footerFontFamily);
	}

	public String getFooterFontFamily() {
		return getValue(Property.footerFontFamily, DEFAULT_FOOTER_FONT_FAMILY);
	}

	public void setFooterFontSize(int footerFontSize) {
		setValue(Property.footerFontSize, footerFontSize);
	}

	public int getFooterFontSize() {
		return getValue(Property.footerFontSize, DEFAULT_FOOTER_FONT_SIZE);
	}

	public void setFooterFontStyle(FontStyle footerFontStyle) {
		setValue(Property.footerFontStyle, footerFontStyle);
	}

	public FontStyle getFooterFontStyle() {
		return getValue(Property.footerFontStyle, FontStyle.class, FontStyle.bold);
	}

	public void setFooterFontColor(String footerFontColor) {
		setValue(Property.footerFontColor, footerFontColor);
	}

	public String getFooterFontColor() {
		return getValue(Property.footerFontColor, DEFAULT_FOOTER_FONT_COLOR);
	}

	public void setFooterSpacing(int footerSpacing) {
		setValue(Property.footerSpacing, footerSpacing);
	}

	public int getFooterSpacing() {
		return getValue(Property.footerSpacing, DEFAULT_FOOTER_SPACING);
	}

	public void setFooterMarginTop(int footerMarginTop) {
		setValue(Property.footerMarginTop, footerMarginTop);
	}

	public int getFooterMarginTop() {
		return getValue(Property.footerMarginTop, DEFAULT_FOOTER_MARGIN_TOP);
	}

	public void setXPadding(int xPadding) {
		setValue(Property.xPadding, xPadding);
	}

	public int getXPadding() {
		return getValue(Property.xPadding, DEFAULT_X_PADDING);
	}

	public void setYPadding(int yPadding) {
		setValue(Property.yPadding, yPadding);
	}

	public int getYPadding() {
		return getValue(Property.yPadding, DEFAULT_Y_PADDING);
	}

	public void setCaretPadding(int caretPadding) {
		setValue(Property.caretPadding, caretPadding);
	}

	public int getCaretPadding() {
		return getValue(Property.caretPadding, DEFAULT_CARET_PADDING);
	}

	public void setCaretSize(int caretSize) {
		setValue(Property.caretSize, caretSize);
	}

	public int getCaretSize() {
		return getValue(Property.caretSize, DEFAULT_CARET_SIZE);
	}

	public void setCornerRadius(int cornerRadius) {
		setValue(Property.cornerRadius, cornerRadius);
	}

	public int getCornerRadius() {
		return getValue(Property.cornerRadius, DEFAULT_CORNER_RADIUS);
	}

	public void setMultiKeyBackground(String multiKeyBackground) {
		setValue(Property.multiKeyBackground, multiKeyBackground);
	}

	public String getMultiKeyBackground() {
		return getValue(Property.multiKeyBackground, DEFAULT_MULTI_KEY_BACKGROUND);
	}

	public void setDisplayColors(boolean displayColors) {
		setValue(Property.displayColors, displayColors);
	}

	public boolean isDisplayColors() {
		return getValue(Property.displayColors, DEFAULT_DISPLAY_COLORS);
	}

	public void setBorderColor(String borderColor) {
		setValue(Property.borderColor, borderColor);
	}

	public String getBorderColor() {
		return getValue(Property.borderColor, DEFAULT_BORDER_COLOR);
	}

	public void setBorderWidth(int borderWidth) {
		setValue(Property.borderWidth, borderWidth);
	}

	public int getBorderWidth() {
		return getValue(Property.borderWidth, DEFAULT_BORDER_WIDTH);
	}
	
	protected void onCustom(TooltipModel model){
		if (customCallback != null){
			customCallback.onCustom(getChart(), model);
		}
	}
	
	protected int onItemSort(TooltipItem o1, TooltipItem o2){
		if (itemSortHandler != null){
			return itemSortHandler.onItemSort(getChart(), o1, o2);
		}
		return 0;
	}

	protected boolean onFilter(TooltipItem item){
		if (filterHandler != null){
			return filterHandler.onFilter(getChart(), item);
		}
		return true;
	}

	
    private native void registerNativeCustomHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.custom = function(tooltipModel){
        	self.@org.pepstock.charba.client.options.Tooltips::onCustom(Lorg/pepstock/charba/client/items/TooltipModel;)(tooltipModel);
	    }
	}-*/;
    
    private native void registerNativeItemSortHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.itemSort = function(item1, item2, data){
        	return self.@org.pepstock.charba.client.options.Tooltips::onItemSort(Lorg/pepstock/charba/client/items/TooltipItem;Lorg/pepstock/charba/client/items/TooltipItem;)(item1, item2);
	    }
	}-*/;

    private native void registerNativeFilterHandlers(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.filter = function(item){
        	return self.@org.pepstock.charba.client.options.Tooltips::onFilter(Lorg/pepstock/charba/client/items/TooltipItem;)(item);
	    }
	}-*/;

}