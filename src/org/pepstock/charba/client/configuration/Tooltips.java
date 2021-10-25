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
package org.pepstock.charba.client.configuration;

import java.util.Set;

import org.pepstock.charba.client.callbacks.TooltipExternalCallback;
import org.pepstock.charba.client.callbacks.TooltipFilterCallback;
import org.pepstock.charba.client.callbacks.TooltipItemSortCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TextDirection;
import org.pepstock.charba.client.enums.TooltipAlign;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.TooltipModel;

import jsinterop.annotations.JsFunction;

/**
 * Configuration element to set all attributes and features of the tooltip.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Tooltips extends ConfigurationOptionsContainer implements HasAnimation {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to hook in the the tooltip rendering process so that you can render the tooltip in your own custom way.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyExternalCallback {

		/**
		 * Method of function to be called to hook in the the tooltip rendering process so that you can render the tooltip in your own custom way.
		 * 
		 * @param tooltipContext all info about tooltip to create own HTML tooltip.
		 */
		void call(NativeObject tooltipContext);
	}

	/**
	 * Java script FUNCTION callback called to filter tooltip items.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyFilterCallback {

		/**
		 * Method of function to be called to filter tooltip items
		 * 
		 * @param item tooltip item to check.
		 * @return <code>true</code> to maintain the item, otherwise <code>false</code> to hide it.
		 */
		boolean call(NativeObject item);
	}

	/**
	 * Java script FUNCTION callback called to allow sorting of tooltip items.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyItemSortCallback {

		/**
		 * Method of function to be called to allow sorting of tooltip items.
		 * 
		 * @param item1 the first object to be compared.
		 * @param item2 the second object to be compared.
		 * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
		 */
		int call(NativeObject item1, NativeObject item2);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the external function
	private final CallbackProxy<ProxyExternalCallback> externalCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the item sort function
	private final CallbackProxy<ProxyItemSortCallback> itemSortCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the filter function
	private final CallbackProxy<ProxyFilterCallback> filterCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callback implementation for external tooltip
	private TooltipExternalCallback externalCallback = null;
	// user callback implementation for item sort tooltip
	private TooltipItemSortCallback itemSortCallback = null;
	// user callback implementation for filtering tooltip items
	private TooltipFilterCallback filterCallback = null;

	// callback sub element of tooltip
	private final TooltipsCallbacks callbacks;
	// title font instance
	private final Font titleFont;
	// body font instance
	private final Font bodyFont;
	// footer font instance
	private final Font footerFont;
	// padding instance
	private final Padding padding;
	// animation container instance
	private final AnimationContainer animationContainer;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		EXTERNAL("external"),
		ITEM_SORT("itemSort"),
		FILTER("filter");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param options root options element.
	 */
	protected Tooltips(ConfigurationOptions options) {
		super(options);
		// sets callbacks sub element
		this.callbacks = new TooltipsCallbacks(getOptions());
		// sets the padding object
		this.padding = new Padding(() -> getConfiguration().getTooltips().getPadding());
		// creates animation configuration manager
		this.animationContainer = new AnimationContainer(getChart(), () -> getConfiguration().getTooltips());
		// gets the fonts subelements
		this.titleFont = new Font(() -> getConfiguration().getTooltips().getTitleFont());
		this.bodyFont = new Font(() -> getConfiguration().getTooltips().getBodyFont());
		this.footerFont = new Font(() -> getConfiguration().getTooltips().getFooterFont());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		this.externalCallbackProxy.setCallback(tooltipContext -> {
			// gets callback
			TooltipExternalCallback externalCallback = getExternalCallback();
			// checks if callback is consistent
			if (externalCallback != null) {
				// creates tootltip context
				TooltipContext tempContext = new TooltipContext(tooltipContext);
				// calls callback
				externalCallback.onExternal(getChart(), tempContext.getModel());
			}
		});
		this.itemSortCallbackProxy.setCallback((item1, item2) -> {
			// gets callback
			TooltipItemSortCallback itemSortCallback = getItemSortCallback();
			// checks if callback is consistent
			if (itemSortCallback != null) {
				// calls callback
				return itemSortCallback.onItemSort(getChart(), TooltipItem.FACTORY.create(item1), TooltipItem.FACTORY.create(item2));
			}
			// default is 0 - equals
			return 0;
		});
		this.filterCallbackProxy.setCallback(item -> {
			// gets callback
			TooltipFilterCallback filterCallback = getFilterCallback();
			// checks if callback is consistent
			if (filterCallback != null) {
				// calls callback
				return filterCallback.onFilter(getChart(), TooltipItem.FACTORY.create(item));
			}
			// default is true
			return true;
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.HasAnimation#getAnimationContainer()
	 */
	@Override
	public AnimationContainer getAnimationContainer() {
		return animationContainer;
	}

	/**
	 * Returns the object where all tooltips callbacks are stored.
	 * 
	 * @return the object where all tooltips callbacks are stored
	 */
	public final TooltipsCallbacks getCallbacks() {
		return callbacks;
	}

	/**
	 * Returns the object where all tooltips padding is stored.
	 * 
	 * @return the object where all tooltips padding is stored
	 */
	public final Padding getPadding() {
		return padding;
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
	 * @return if tooltips are enabled.
	 */
	public boolean isEnabled() {
		return getConfiguration().getTooltips().isEnabled();
	}

	/**
	 * Sets the browser events that the tooltip should listen to.
	 * 
	 * @param events the browser events that the tooltip should listen to.
	 */
	public void setEvents(Event... events) {
		getConfiguration().getTooltips().setEvents(events);
	}

	/**
	 * Returns the browser events that the tooltip should listen to.
	 * 
	 * @return the browser events that the tooltip should listen to.
	 */
	public Set<Event> getEvents() {
		return getConfiguration().getTooltips().getEvents();
	}

	/**
	 * Sets which elements appear in the tooltip.
	 * 
	 * @param mode which elements appear in the tooltip.
	 */
	public void setMode(InteractionMode mode) {
		getConfiguration().getTooltips().setMode(mode);
	}

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip.
	 */
	public InteractionMode getMode() {
		return getConfiguration().getTooltips().getMode();
	}

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be applied at all times.
	 * 
	 * @param intersect if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be applied at all times.
	 */
	public void setIntersect(boolean intersect) {
		getConfiguration().getTooltips().setIntersect(intersect);
	}

	/**
	 * if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be applied at all times.
	 * 
	 * @return if true, the tooltip mode applies only when the mouse position intersects with an element. If false, the mode will be applied at all times.
	 */
	public boolean isIntersect() {
		return getConfiguration().getTooltips().isIntersect();
	}

	/**
	 * Sets the mode for positioning the tooltip.
	 * 
	 * @param position the mode for positioning the tooltip.
	 */
	public void setPosition(IsTooltipPosition position) {
		getConfiguration().getTooltips().setPosition(position);
	}

	/**
	 * Returns the mode for positioning the tooltip.
	 * 
	 * @return mode for positioning the tooltip.
	 */
	public IsTooltipPosition getPosition() {
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
	 * @return Background color of the tooltip.
	 */
	public String getBackgroundColorAsString() {
		return getConfiguration().getTooltips().getBackgroundColorAsString();
	}

	/**
	 * Returns the background color of the tooltip.
	 * 
	 * @return Background color of the tooltip.
	 */
	public IsColor getBackgroundColor() {
		return getConfiguration().getTooltips().getBackgroundColor();
	}

	/**
	 * Returns the title font element.
	 * 
	 * @return the title font
	 */
	public Font getTitleFont() {
		return titleFont;
	}

	/**
	 * Sets the title font color.
	 * 
	 * @param color title font color.
	 */
	public void setTitleColor(IsColor color) {
		getConfiguration().getTooltips().setTitleColor(color);
	}

	/**
	 * Sets the title font color.
	 * 
	 * @param color title font color.
	 */
	public void setTitleColor(String color) {
		getConfiguration().getTooltips().setTitleColor(color);
	}

	/**
	 * Returns the title font color as string.
	 * 
	 * @return title font color as string
	 */
	public String getTitleColorAsString() {
		return getConfiguration().getTooltips().getTitleColorAsString();
	}

	/**
	 * Returns the title font color.
	 * 
	 * @return title font color
	 */
	public IsColor getTitleColor() {
		return ColorBuilder.parse(getTitleColorAsString());
	}

	/**
	 * Sets the title alignment.
	 * 
	 * @param align title alignment.
	 */
	public void setTitleAlign(TextAlign align) {
		getConfiguration().getTooltips().setTitleAlign(align);
	}

	/**
	 * Returns the title alignment.
	 * 
	 * @return title alignment.
	 */
	public TextAlign getTitleAlign() {
		return getConfiguration().getTooltips().getTitleAlign();
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
	 * @return spacing to add to top and bottom of each title line.
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
	 * @return margin to add on bottom of title section.
	 */
	public int getTitleMarginBottom() {
		return getConfiguration().getTooltips().getTitleMarginBottom();
	}

	/**
	 * Returns the body font element.
	 * 
	 * @return the body font
	 */
	public Font getBodyFont() {
		return bodyFont;
	}

	/**
	 * Sets the body font color.
	 * 
	 * @param color body font color.
	 */
	public void setBodyColor(IsColor color) {
		getConfiguration().getTooltips().setBodyColor(color);
	}

	/**
	 * Sets the body font color.
	 * 
	 * @param color body font color.
	 */
	public void setBodyColor(String color) {
		getConfiguration().getTooltips().setBodyColor(color);
	}

	/**
	 * Returns the body font color as string.
	 * 
	 * @return body font color as string
	 */
	public String getBodyColorAsString() {
		return getConfiguration().getTooltips().getBodyColorAsString();
	}

	/**
	 * Returns the body font color.
	 * 
	 * @return body font color
	 */
	public IsColor getBodyColor() {
		return ColorBuilder.parse(getBodyColorAsString());
	}

	/**
	 * Sets the body alignment.
	 * 
	 * @param align body alignment.
	 */
	public void setBodyAlign(TextAlign align) {
		getConfiguration().getTooltips().setBodyAlign(align);
	}

	/**
	 * Returns the body alignment.
	 * 
	 * @return body alignment.
	 */
	public TextAlign getBodyAlign() {
		return getConfiguration().getTooltips().getBodyAlign();
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
	 * @return spacing to add to top and bottom of each tooltip item.
	 */
	public int getBodySpacing() {
		return getConfiguration().getTooltips().getBodySpacing();
	}

	/**
	 * Returns the footer font element.
	 * 
	 * @return the footer font
	 */
	public Font getFooterFont() {
		return footerFont;
	}

	/**
	 * Sets the footer font color.
	 * 
	 * @param color footer font color.
	 */
	public void setFooterColor(IsColor color) {
		getConfiguration().getTooltips().setFooterColor(color);
	}

	/**
	 * Sets the footer font color.
	 * 
	 * @param color footer font color.
	 */
	public void setFooterColor(String color) {
		getConfiguration().getTooltips().setFooterColor(color);
	}

	/**
	 * Returns the footer font color as string.
	 * 
	 * @return footer font color as string
	 */
	public String getFooterColorAsString() {
		return getConfiguration().getTooltips().getFooterColorAsString();
	}

	/**
	 * Returns the footer font color.
	 * 
	 * @return footer font color
	 */
	public IsColor getFooterColor() {
		return ColorBuilder.parse(getFooterColorAsString());
	}

	/**
	 * Sets the footer alignment.
	 * 
	 * @param align footer alignment.
	 */
	public void setFooterAlign(TextAlign align) {
		getConfiguration().getTooltips().setFooterAlign(align);
	}

	/**
	 * Returns the footer alignment.
	 * 
	 * @return footer alignment.
	 */
	public TextAlign getFooterAlign() {
		return getConfiguration().getTooltips().getFooterAlign();
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
	 * @return spacing to add to top and bottom of each footer line.
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
	 * @return margin to add before drawing the footer.
	 */
	public int getFooterMarginTop() {
		return getConfiguration().getTooltips().getFooterMarginTop();
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
	 * @return extra distance to move the end of the tooltip arrow away from the tooltip point.
	 */
	public int getCaretPadding() {
		return getConfiguration().getTooltips().getCaretPadding();
	}

	/**
	 * Sets the size of the tooltip arrow, in pixels.
	 * 
	 * @param caretSize size of the tooltip arrow, in pixels.
	 */
	public void setCaretSize(int caretSize) {
		getConfiguration().getTooltips().setCaretSize(caretSize);
	}

	/**
	 * Returns the size of the tooltip arrow, in pixels.
	 * 
	 * @return size of the tooltip arrow, in pixels
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
	 * Sets the radius of tooltip corner curves.
	 * 
	 * @param cornerRadius radius of tooltip corner curves.
	 */
	public void setCornerRadius(BarBorderRadius cornerRadius) {
		getConfiguration().getTooltips().setCornerRadius(cornerRadius);
	}

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves.
	 */
	public int getCornerRadius() {
		return getConfiguration().getTooltips().getCornerRadius();
	}

	/**
	 * Returns the radius of tooltip corner curves.
	 * 
	 * @return radius of tooltip corner curves.
	 */
	public BarBorderRadius getCornerRadiusAsObject() {
		return getConfiguration().getTooltips().getCornerRadiusAsObject();
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
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip.
	 */
	public String getMultiKeyBackgroundAsString() {
		return getConfiguration().getTooltips().getMultiKeyBackgroundAsString();
	}

	/**
	 * Returns the color to draw behind the colored boxes when multiple items are in the tooltip.
	 * 
	 * @return color to draw behind the colored boxes when multiple items are in the tooltip.
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
	 * @return if true, color boxes are shown in the tooltip.
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
	 * @return color of the border.
	 */
	public String getBorderColorAsString() {
		return getConfiguration().getTooltips().getBorderColorAsString();
	}

	/**
	 * Returns the color of the border.
	 * 
	 * @return color of the border.
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
	 * @return size of the border.
	 */
	public int getBorderWidth() {
		return getConfiguration().getTooltips().getBorderWidth();
	}

	/**
	 * Sets the width of of the colored box if displayColors is <code>true</code>..
	 * 
	 * @param boxWidth width of colored box.
	 */
	public void setBoxWidth(int boxWidth) {
		getConfiguration().getTooltips().setBoxWidth(boxWidth);
	}

	/**
	 * Returns the width of the colored box if displayColors is <code>true</code>..
	 * 
	 * @return width of colored box.
	 */
	public int getBoxWidth() {
		return getConfiguration().getTooltips().getBoxWidth();
	}

	/**
	 * Sets the height of the colored box if displayColors is <code>true</code>..
	 * 
	 * @param boxHeight width of colored box.
	 */
	public void setBoxHeight(int boxHeight) {
		getConfiguration().getTooltips().setBoxHeight(boxHeight);
	}

	/**
	 * Returns the height of the colored box if displayColors is <code>true</code>..
	 * 
	 * @return height of colored box.
	 */
	public int getBoxHeight() {
		return getConfiguration().getTooltips().getBoxHeight();
	}

	/**
	 * Sets <code>true</code> for rendering the tooltips from right to left.
	 * 
	 * @param rtl <code>true</code> for rendering the tooltips from right to left
	 */
	public void setRtl(boolean rtl) {
		getConfiguration().getTooltips().setRtl(rtl);
	}

	/**
	 * Returns <code>true</code> for rendering the tooltips from right to left.
	 * 
	 * @return <code>true</code> for rendering the tooltips from right to left.
	 */
	public boolean isRtl() {
		return getConfiguration().getTooltips().isRtl();
	}

	/**
	 * Sets the text direction of the tooltip that will force the text direction on the canvas for rendering the tooltip, regardless of the CSS specified on the canvas.
	 * 
	 * @param textDirection the text direction of the tooltip.
	 */
	public void setTextDirection(TextDirection textDirection) {
		getConfiguration().getTooltips().setTextDirection(textDirection);
	}

	/**
	 * Returns the text direction of the tooltip that will force the text direction on the canvas for rendering the tooltip, regardless of the CSS specified on the canvas.
	 * 
	 * @return the text direction of the tooltip.
	 */
	public TextDirection getTextDirection() {
		return getConfiguration().getTooltips().getTextDirection();
	}

	/**
	 * Sets if to use the corresponding point style (from data set options) instead of color boxes (size is based on the minimum value between boxWidth and boxHeight).
	 * 
	 * @param usePointStyle <code>true</code> if to use the corresponding point style (from data set options) instead of color boxes (size is based on the minimum value between
	 *            boxWidth and boxHeight)
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		getConfiguration().getTooltips().setUsePointStyle(usePointStyle);
	}

	/**
	 * Returns if to use the corresponding point style (from data set options) instead of color boxes (size is based on the minimum value between boxWidth and boxHeight).
	 * 
	 * @return <code>true</code> if to use the corresponding point style (from data set options) instead of color boxes (size is based on the minimum value between boxWidth and
	 *         boxHeight)
	 */
	public boolean isUsePointStyle() {
		return getConfiguration().getTooltips().isUsePointStyle();
	}

	/**
	 * Sets the alignment of the tooltip caret in the X direction.
	 * 
	 * @param align the alignment of the tooltip caret in the X direction.
	 */
	public void setXAlign(TooltipAlign align) {
		getConfiguration().getTooltips().setXAlign(align);
	}

	/**
	 * Returns the alignment of the tooltip caret in the X direction.
	 * 
	 * @return the alignment of the tooltip caret in the X direction
	 */
	public TooltipAlign getXAlign() {
		return getConfiguration().getTooltips().getXAlign();
	}

	/**
	 * Sets the alignment of the tooltip caret in the Y direction.
	 * 
	 * @param align the alignment of the tooltip caret in the Y direction.
	 */
	public void setYAlign(TooltipAlign align) {
		getConfiguration().getTooltips().setYAlign(align);
	}

	/**
	 * Returns the alignment of the tooltip caret in the Y direction.
	 * 
	 * @return the alignment of the tooltip caret in the Y direction
	 */
	public TooltipAlign getYAlign() {
		return getConfiguration().getTooltips().getYAlign();
	}

	// ---------------------------
	// CALLBACKS
	// ---------------------------

	/**
	 * Returns the user external callback instance.
	 * 
	 * @return the user external callback instance
	 */
	public TooltipExternalCallback getExternalCallback() {
		return externalCallback;
	}

	/**
	 * Sets the user external callback instance.
	 * 
	 * @param externalCallback the user external callback instance
	 */
	public void setExternalCallback(TooltipExternalCallback externalCallback) {
		// sets the callback
		this.externalCallback = externalCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips(), Property.EXTERNAL, externalCallback, externalCallbackProxy);
	}

	/**
	 * Returns the user item sort callback instance.
	 * 
	 * @return the user item sort callback instance
	 */
	public TooltipItemSortCallback getItemSortCallback() {
		return itemSortCallback;
	}

	/**
	 * Sets the user item sort callback instance.
	 * 
	 * @param itemSortCallback the user item sort callback instance
	 */
	public void setItemSortCallback(TooltipItemSortCallback itemSortCallback) {
		// sets the callback
		this.itemSortCallback = itemSortCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips(), Property.ITEM_SORT, itemSortCallback, itemSortCallbackProxy);
	}

	/**
	 * Returns the user filter callback instance.
	 * 
	 * @return the user filter callback instance
	 */
	public TooltipFilterCallback getFilterCallback() {
		return filterCallback;
	}

	/**
	 * Sets the user filter callback instance.
	 * 
	 * @param filterCallback the user filter callback instance
	 */
	public void setFilterCallback(TooltipFilterCallback filterCallback) {
		// sets the callback
		this.filterCallback = filterCallback;
		// stores and manages callback
		getOptions().setCallback(getConfiguration().getTooltips(), Property.FILTER, filterCallback, filterCallbackProxy);
	}

	/**
	 * Internal class to map the custom callback function argument, which is an object with the chart instance and the tooltip model.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class TooltipContext extends NativeObjectContainer {

		/**
		 * Name of properties of native object.
		 */
		private enum Property implements Key
		{
			TOOLTIP("tooltip");

			// name value of property
			private final String value;

			/**
			 * Creates with the property value to use in the native object.
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

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		TooltipContext(NativeObject nativeObject) {
			super(nativeObject);
		}

		TooltipModel getModel() {
			// checks if model is inside the context
			if (isType(Property.TOOLTIP, ObjectType.OBJECT)) {
				return new TooltipModel(new ConfigurationEnvelop<>(getValue(Property.TOOLTIP), true));
			}
			// if here the context is not consistent
			// returns null
			return null;
		}
	}

}