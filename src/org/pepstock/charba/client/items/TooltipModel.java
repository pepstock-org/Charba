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

public final class TooltipModel  extends BaseItem {
 
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
	}

    public final List<TooltipItem> getDataPoints() {
    	return getObjectArray(Property.dataPoints.name());
//    	GenericJavaScriptObject[] objects = getJavaScriptObjectArray();
//    	if (objects == null || objects.length == 0){
//    		return new TooltipItem[0]; 
//    	} 
//    	TooltipItem[] items = new TooltipItem[objects.length]; 
//    	for (int i=0; i<objects.length; i++){
//    		items[i] = (TooltipItem)objects[i];
//    	}
//    	return items;
    }

    public final int getXPadding() {
        return getInt(Property.xPadding.name());
    }

    public final int getYPadding() {
        return getInt(Property.yPadding.name());
    }

    public final String getXAlign() {
        return getString(Property.xAlign.name());
    }

    public final String getYAlign() {
        return getString(Property.yAlign.name());
    }

    public final int getX() {
        return getInt(Property.x.name());
    }

    public final int getY() {
        return getInt(Property.y.name());
    }

    public final int getWidth() {
        return getInt(Property.width.name());
    }

    public final int getHeight() {
        return getInt(Property.height.name());
    }

    public final int getCaretX() {
        return getInt(Property.caretX.name());
    }

    public final int getCaretY() {
        return getInt(Property.caretY.name());
    }

    public final List<TooltipBodyItem> getBody() {
    	return getObjectArray(Property.body.name());
//    	GenericJavaScriptObject[] objects = getJavaScriptObjectArray(Property.body.name());
//    	if (objects == null || objects.length == 0){
//    		return new TooltipBodyItem[0]; 
//    	} 
//    	TooltipBodyItem[] items = new TooltipBodyItem[objects.length]; 
//    	for (int i=0; i<objects.length; i++){
//    		items[i] = (TooltipBodyItem)objects[i];
//    	}
//    	return items;
    }

    public final List<String> getBeforeBody() {
        return getStringArray(Property.beforeBody.name());
    }

    public final List<String> getAfterBody() {
        return getStringArray(Property.afterBody.name());
    }

    public final String getBodyFontColor() {
        return getString(Property.bodyFontColor.name());
    }

    public final String getBodyFontFamily() {
        return getString(Property._bodyFontFamily.name());
    }

    public final FontStyle getBodyFontStyle() {
        return getValue(Property._bodyFontStyle, FontStyle.class, FontStyle.normal);
    }

    public final String getBodyAlign() {
        return getString(Property._bodyAlign.name());
    }

    public final int getBodyFontSize() {
        return getInt(Property.bodyFontSize.name());
    }

    public final int getBodySpacing() {
        return getInt(Property.bodySpacing.name());
    }

    public final List<String> getTitle() {
        return getStringArray(Property.title.name());
    }

    public final String getTitleFontColor() {
        return getString(Property.titleFontColor.name());
    }

    public final String getTitleFontFamily() {
        return getString(Property._titleFontFamily.name());
    }

    public final FontStyle getTitleFontStyle() {
    	return getValue(Property._titleFontStyle, FontStyle.class, FontStyle.normal);
    }

    public final int getTitleFontSize() {
        return getInt(Property.titleFontSize.name());
    }

    public final String getTitleAlign() {
        return getString(Property._titleAlign.name());
    }

    public final int getTitleSpacing() {
        return getInt(Property.titleSpacing.name());
    }

    public final int getTitleMarginBottom() {
        return getInt(Property.titleMarginBottom.name());
    }

    public final List<String> getFooter() {
        return getStringArray(Property.footer.name());
    }

    public final String getFooterFontColor() {
        return getString(Property.footerFontColor.name());
    }

    public final String getFooterFontFamily() {
        return getString(Property._footerFontFamily.name());
    }

    public final FontStyle getFooterFontStyle() {
    	return getValue(Property._footerFontStyle, FontStyle.class, FontStyle.normal);
    }

    public final int getFooterFontSize() {
        return getInt(Property.footerFontSize.name());
    }

    public final String getFooterAlign() {
        return getString(Property._footerAlign.name());
    }

    public final int getFooterSpacing() {
        return getInt(Property.footerSpacing.name());
    }

    public final int getFooterMarginTop() {
        return getInt(Property.footerMarginTop.name());
    }

    public final int getCaretSize() {
        return getInt(Property.caretSize.name());
    }

    public final double getCornerRadius() {
        return getDouble(Property.cornerRadius.name());
    }

    public final String getBackgroundColor() {
        return getString(Property.backgroundColor.name());
    }

    public final List<TooltipLabelColor> getLabelColors() {
    	return getObjectArray(Property.labelColors.name());
    }

    public final double getOpacity() {
        return getDouble(Property.opacity.name());
    }

    public final String getLegendColorBackground() {
        return getString(Property.legendColorBackground.name());
    }

    public final Boolean getDisplayColors() {
        return getBoolean(Property.displayColors.name());
    }

    public String toContentString()  {
    	return "TooltipModel [getDataPoints()=" + getDataPoints() + ", getXPadding()=" + getXPadding() + ", getYPadding()=" + getYPadding() + ", getXAlign()=" + getXAlign() + ", getYAlign()=" + getYAlign() + ", getX()=" + getX()
    	+ ", getY()=" + getY() + ", getWidth()=" + getWidth() + ", getHeight()=" + getHeight() + ", getCaretX()=" + getCaretX() + ", getCaretY()=" + getCaretY() + ", getBody()=" + getBody() + ", getBeforeBody()="
    	+ getBeforeBody() + ", getAfterBody()=" + getAfterBody() + ", getBodyFontColor()=" + getBodyFontColor() + ", getBodyFontFamily()=" + getBodyFontFamily() + ", getBodyFontStyle()=" + getBodyFontStyle()
    	+ ", getBodyAlign()=" + getBodyAlign() + ", getBodyFontSize()=" + getBodyFontSize() + ", getBodySpacing()=" + getBodySpacing() + ", getTitle()=" + getTitle() + ", getTitleFontColor()=" + getTitleFontColor()
    	+ ", getTitleFontFamily()=" + getTitleFontFamily() + ", getTitleFontStyle()=" + getTitleFontStyle() + ", getTitleFontSize()=" + getTitleFontSize() + ", getTitleAlign()=" + getTitleAlign() + ", getTitleSpacing()=" + getTitleSpacing()
    	+ ", getTitleMarginBottom()=" + getTitleMarginBottom() + ", getFooter()=" +getFooter() + ", getFooterFontColor()=" + getFooterFontColor() + ", getFooterFontFamily()=" + getFooterFontFamily() + ", getFooterFontStyle()="
    	+ getFooterFontStyle() + ", getFooterFontSize()=" + getFooterFontSize() + ", getFooterAlign()=" + getFooterAlign() + ", getFooterSpacing()=" + getFooterSpacing() + ", getFooterMarginTop()=" + getFooterMarginTop() + ", getCaretSize()="
    	+ getCaretSize() + ", getCornerRadius()=" + getCornerRadius() + ", getBackgroundColor()=" + getBackgroundColor() + ", getLabelColors()=" + getLabelColors() + ", getOpacity()=" + getOpacity()
    	+ ", getLegendColorBackground()=" + getLegendColorBackground() + ", getDisplayColors()=" + getDisplayColors() + "]";
    }

}