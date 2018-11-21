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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Configuration element to set all attributes and features of the default tooltip.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
final class NativeTooltips extends NativeObject {
	
	@JsProperty
	native void setCallbacks(NativeTooltipsCallbacks callbacks);

	@JsProperty
	native NativeTooltipsCallbacks getCallbacks();

	/**
	 * Sets if tooltips are enabled.
	 * 
	 * @param enabled if tooltips are enabled.
	 */
	@JsProperty
	native void setEnabled(boolean enabled);

	/**
	 * Returns if tooltips are enabled.
	 * 
	 * @return if tooltips are enabled.
	 */
	@JsProperty
	native boolean isEnabled();

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 */
	@JsProperty
	native void setMode(String mode);

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip. ThisInterceptor
	 */
	@JsProperty
	native String getMode();

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @param intersect if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the
	 *            mode will be applied at all times.
	 */
	@JsProperty
	native void setIntersect(boolean intersect);

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be
	 * applied at all times.
	 * 
	 * @return if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode
	 *         will be applied at all times. 
	 */
	@JsProperty
	native boolean isIntersect();

	/**
	 * Sets the mode for positioning the tooltip.
	 * 
	 * @param position the mode for positioning the tooltip.
	 */
	@JsProperty
	native void setPosition(String position);

	/**
	 * Returns the mode for positioning the tooltip.
	 * 
	 * @return mode for positioning the tooltip. 
	 */
	@JsProperty
	native String getPosition();

	/**
	 * Sets the background color of the tooltip.
	 * 
	 * @param backgroundColor background color of the tooltip.
	 */
	@JsProperty
	native void setBackgroundColor(String backgroundColor);


	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return Background color of the tooltip. 
	 */
	@JsProperty
	native String getBackgroundColor();

	/**
	 * Sets the title font.
	 * 
	 * @param titleFontFamily title font.
	 */
	@JsProperty
	native void setTitleFontFamily(String titleFontFamily);

	/**
	 * Returns the title font.
	 * 
	 * @return the title font. 
	 */
	@JsProperty
	native String getTitleFontFamily();

	/**
	 * Sets the title font size.
	 * 
	 * @param titleFontSize Title font size.
	 */
	@JsProperty
	native void setTitleFontSize(int titleFontSize);
	
	/**
	 * Returns the title font size.
	 * 
	 * @return Title font size.
	 */
	@JsProperty
	native int getTitleFontSize();

	/**
	 * Sets the title font style.
	 * 
	 * @param titleFontStyle title font style.
	 */
	@JsProperty
	native void setTitleFontStyle(String titleFontStyle);

	/**
	 * Returns the title font style.
	 * 
	 * @return title font style.
	 */
	@JsProperty
	native String getTitleFontStyle();
	
	/**
	 * Sets the title alignment.
	 * 
	 * @param align title alignment.
	 */
	@JsProperty
	native void setTitleAlign(String align);
	/**
	 * Returns the title alignment.
	 * 
	 * @return title alignment. 
	 */
	@JsProperty
	native String getTitleAlign();

	/**
	 * Sets the title font color.
	 * 
	 * @param titleFontColor title font color.
	 */
	@JsProperty
	native void setTitleFontColor(String titleFontColor);

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color.
	 */
	@JsProperty
	native String getTitleFontColor();

	/**
	 * Sets the spacing to add to top and bottom of each title line.
	 * 
	 * @param titleSpacing spacing to add to top and bottom of each title line.
	 */
	@JsProperty
	native void setTitleSpacing(int titleSpacing);

	/**
	 * Returns the spacing to add to top and bottom of each title line.
	 * 
	 * @return spacing to add to top and bottom of each title line. 
	 */
	@JsProperty
	native int getTitleSpacing();

	/**
	 * Sets the margin to add on bottom of title section.
	 * 
	 * @param titleMarginBottom margin to add on bottom of title section.
	 */
	@JsProperty
	native void setTitleMarginBottom(int titleMarginBottom);
	
	/**
	 * Returns the margin to add on bottom of title section.
	 * 
	 * @return margin to add on bottom of title section. 
	 */
	@JsProperty
	native int getTitleMarginBottom();
	/**
	 * Sets the body line font.
	 * 
	 * @param bodyFontFamily body line font.
	 */
	@JsProperty
	native void setBodyFontFamily(String bodyFontFamily);

	/**
	 * Returns the body line font.
	 * 
	 * @return body line font. 
	 */
	@JsProperty
	native String getBodyFontFamily();

	/**
	 * Sets the body font size.
	 * 
	 * @param bodyFontSize body font size.
	 */
	@JsProperty
	native void setBodyFontSize(int bodyFontSize);

	/**
	 * Returns the body font size.
	 * 
	 * @return body font size. 
	 */
	@JsProperty
	native int getBodyFontSize();

	/**
	 * Sets the body font style.
	 * 
	 * @param bodyFontStyle body font style.
	 */
	@JsProperty
	native void setBodyFontStyle(String bodyFontStyle);

	/**
	 * Returns the body font style.
	 * 
	 * @return body font style. 
	 */
	@JsProperty
	native String getBodyFontStyle();

	/**
	 * Sets the body alignment.
	 * 
	 * @param align body alignment.
	 */
	@JsProperty
	native void setBodyAlign(String align);

	/**
	 * Returns the body alignment.
	 * 
	 * @return body alignment. 
	 */
	@JsProperty
	native String getBodyAlign();

	/**
	 * Sets the body font color.
	 * 
	 * @param bodyFontColor body font color.
	 */
	@JsProperty
	native void setBodyFontColor(String bodyFontColor);

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color.
	 */
	@JsProperty
	native String getBodyFontColor();
	
	/**
	 * Sets the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @param bodySpacing spacing to add to top and bottom of each tooltip item.
	 */
	@JsProperty
	native void setBodySpacing(int bodySpacing);

	/**
	 * Returns the spacing to add to top and bottom of each tooltip item.
	 * 
	 * @return spacing to add to top and bottom of each tooltip item. 
	 */
	@JsProperty
	native int getBodySpacing();

	/**
	 * Sets the footer font.
	 * 
	 * @param footerFontFamily footer font.
	 */
	@JsProperty
	native void setFooterFontFamily(String footerFontFamily);

	/**
	 * Returns the footer font.
	 * 
	 * @return footer font. 
	 */
	@JsProperty
	native String getFooterFontFamily();

	/**
	 * Sets the footer font size.
	 * 
	 * @param footerFontSize footer font size.
	 */
	@JsProperty
	native void setFooterFontSize(int footerFontSize);

	/**
	 * Returns the footer font size.
	 * 
	 * @return footer font size.
	 */
	@JsProperty
	native int getFooterFontSize();

	/**
	 * Sets the footer font style.
	 * 
	 * @param footerFontStyle the footer font style.
	 */
	@JsProperty
	native void setFooterFontStyle(String footerFontStyle);

	/**
	 * Returns the footer font style.
	 * 
	 * @return footer font style.
	 */
	@JsProperty
	native String getFooterFontStyle();

	/**
	 * Sets the footer alignment.
	 * 
	 * @param align footer alignment.
	 */
	@JsProperty
	native void setFooterAlign(String align);

	/**
	 * Returns the body alignment.
	 * 
	 * @return footer alignment.
	 */
	@JsProperty
	native String getFooterAlign();

	/**
	 * Sets the footer font color.
	 * 
	 * @param footerFontColor footer font color.
	 */
	@JsProperty
	native void setFooterFontColor(String footerFontColor);

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color.
	 */
	@JsProperty
	native String getFooterFontColor();
	
	/**
	 * Sets the spacing to add to top and bottom of each footer line.
	 * 
	 * @param footerSpacing spacing to add to top and bottom of each footer line.
	 */
	@JsProperty
	native void setFooterSpacing(int footerSpacing);

	/**
	 * Returns the spacing to add to top and bottom of each footer line.
	 * 
	 * @return spacing to add to top and bottom of each footer line. 
	 */
	@JsProperty
	native int getFooterSpacing();

	/**
	 * Sets the margin to add before drawing the footer.
	 * 
	 * @param footerMarginTop margin to add before drawing the footer.
	 */
	@JsProperty
	native void setFooterMarginTop(int footerMarginTop);

	/**
	 * Returns the margin to add before drawing the footer.
	 * 
	 * @return margin to add before drawing the footer.
	 */
	@JsProperty
	native int getFooterMarginTop();
	/**
	 * Sets the padding to add on left and right of tooltip.
	 * 
	 * @param xPadding padding to add on left and right of tooltip.
	 */
	@JsProperty(name = "xPadding")
	native void setXPadding(int xPadding);

	/**
	 * Returns the padding to add on left and right of tooltip.
	 * 
	 * @return padding to add on left and right of tooltip.
	 */
	@JsProperty(name = "xPadding")
	native int getXPadding();

	/**
	 * Sets the padding to add on top and bottom of tooltip.
	 * 
	 * @param yPadding padding to add on top and bottom of tooltip.
	 */
	@JsProperty(name = "yPadding")
	native void setYPadding(int yPadding);

	/**
	 * Returns the padding to add on top and bottom of tooltip.
	 * 
	 * @return padding to add on top and bottom of tooltip.
	 */
	@JsProperty(name = "yPadding")
	native int getYPadding();

	/**
	 * Sets the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @param caretPadding extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	@JsProperty
	native void setCaretPadding(int caretPadding);

	/**
	 * Returns the extra distance to move the end of the tooltip arrow away from the tooltip point.
	 * 
	 * @return extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	@JsProperty
	native int getCaretPadding();

	/**
	 * Sets the size, in px, of the tooltip arrow.
	 * 
	 * @param caretSize size, in px, of the tooltip arrow.
	 */
	@JsProperty
	native void setCaretSize(int caretSize);

	/**
	 * Returns the size, in px, of the tooltip arrow.
	 * 
	 * @return size, in px, of the tooltip arrow.
	 */
	@JsProperty
	native int getCaretSize();
	/**
	 * Sets the radius of tooltip corner curves.
	 * 
	 * @param cornerRadius radius of tooltip corner curves.
	 */
	@JsProperty
	native void setCornerRadius(int cornerRadius);

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves.
	 */
	@JsProperty
	native int getCornerRadius();

	/**
	 * Sets the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @param multiKeyBackground color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	@JsProperty
	native void setMultiKeyBackground(String multiKeyBackground);

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	@JsProperty
	native String getMultiKeyBackground();

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @param displayColors if true, color boxes are shown in the tooltip.
	 */
	@JsProperty
	native void setDisplayColors(boolean displayColors);

	/**
	 * If true, color boxes are shown in the tooltip.
	 * 
	 * @return if true, color boxes are shown in the tooltip.
	 */
	@JsProperty
	native boolean isDisplayColors();

	/**
	 * Sets the color of the border.
	 * 
	 * @param borderColor color of the border.
	 */
	@JsProperty
	native void setBorderColor(String borderColor);

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border.
	 */
	@JsProperty
	native String getBorderColor();

	/**
	 * Sets the size of the border.
	 * 
	 * @param borderWidth size of the border.
	 */
	@JsProperty
	native void setBorderWidth(int borderWidth);

	/**
	 * Returns the size of the border.
	 * 
	 * @return size of the border.
	 */
	@JsProperty
	native int getBorderWidth();
	
	@JsProperty
	native void setCustom(CallbackProxy.Proxy proxy);

	@JsProperty
	native void setItemSort(CallbackProxy.Proxy proxy);

	@JsProperty
	native void setFilter(CallbackProxy.Proxy proxy);
}