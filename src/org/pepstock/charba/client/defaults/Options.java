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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsEnumValueArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.global.Animation;
import org.pepstock.charba.client.defaults.global.Elements;
import org.pepstock.charba.client.defaults.global.Hover;
import org.pepstock.charba.client.defaults.global.Layout;
import org.pepstock.charba.client.defaults.global.Legend;
import org.pepstock.charba.client.defaults.global.Title;
import org.pepstock.charba.client.defaults.global.Tooltips;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Base object which maps chart options.<br>
 * Important topics to take care:<br>
 * <b> Responsive </b><br>
 * When it comes to change the chart size based on the window size, a major limitation is that the canvas render size
 * (canvas.width and .height) can not be expressed with relative values, contrary to the display size (canvas.style.width and
 * .height). Furthermore, these sizes are independent from each other and thus the canvas render size does not adjust
 * automatically based on the display size, making the rendering inaccurate.<br>
 * It provides a few options to enable responsiveness and control the resize behavior of charts by detecting when the canvas
 * display size changes and update the render size accordingly.<br>
 * <b> Legend </b><br>
 * Sometimes you need a very complex legend. In these cases, it makes sense to generate an HTML legend. Charts provide a
 * generateLegend() method on their prototype that returns an HTML string for the legend. To configure how this legend is
 * generated, you can set the legendCallback.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Options extends AbstractDefaultsItem {

	private static final boolean DEFAULT_RESPONSIVE = true;

	private static final int DEFAULT_RESPONSIVE_ANIMATION_DURATION = 0;

	private static final boolean DEFAULT_MAINTAIN_ASPECT_RATIO = true;

	private static final String DEFAULT_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_FONT_SIZE = 12;

	private static final String DEFAULT_FONT_COLOR = "#666";

	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private static final boolean DEFAULT_SHOW_LINES = true;

	private final Hover hover;

	private final Elements elements;

	private final Layout layout;

	private final Animation animation;

	private final Tooltips tooltips;

	private final Title title;

	private final Legend legend;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		responsive,
		responsiveAnimationDuration,
		maintainAspectRatio,
		events,
		defaultColor,
		defaultFontColor,
		defaultFontFamily,
		defaultFontSize,
		defaultFontStyle,
		showLines,
		hover,
		elements,
		layout,
		animation,
		tooltips,
		legend,
		title
	}

	public Options(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		hover = new Hover(load(Property.hover));
		elements = new Elements(load(Property.elements));
		layout = new Layout(load(Property.layout));
		animation = new Animation(load(Property.animation));
		tooltips = new Tooltips(load(Property.tooltips));
		legend = new Legend(load(Property.legend));
		title = new Title(load(Property.title));
	}

	/**
	 * @return the hover
	 */
	public Hover getHover() {
		return hover;
	}

	/**
	 * @return the elements
	 */
	public Elements getElements() {
		return elements;
	}

	/**
	 * @return the layout
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @return the tooltips
	 */
	public Tooltips getTooltips() {
		return tooltips;
	}
	
	/**
	 * @return the title
	 */
	public Title getTitle() {
		return title;
	}
	
	/**
	 * @return the legend
	 */
	public Legend getLegend() {
		return legend;
	}

	/**
	 * Sets the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @param events the browser events that the chart should listen to for tooltips and hovering.
	 */
	public void setEvents(Event... events) {
		// checks the events passed
		// if empty
		if (events == null || events.length == 0) {
			// remove java script property
			remove(Property.events);
		} else {
			// sets the events java script property
			setEnumValueArray(Property.events, ArrayListHelper.build(Event.class, events));
		}
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering.
	 */
	public JsEnumValueArrayList<Event> getEvents() {
		// checks if the java script property is set
		if (has(Property.events)) {
			// loads the array of events
			JsStringArrayList value = getStringArray(Property.events);
			return ArrayListHelper.build(Event.class, value);
		} else {
			// returns all events
			return ArrayListHelper.build(Event.class, Event.values());
		}
	}

	/**
	 * Returns if should chart be animated or not Default value is <code>true</code>.
	 * 
	 * @return if should chart be animated or not Default value is <code>true</code>.
	 */
	public boolean isAnimationEnable() {
		return has(Property.animation);
	}

	/**
	 * Sets the resizing of the chart canvas when its container does.
	 * 
	 * @param responsive the resizing of the chart canvas when its container does.
	 */
	public void setResponsive(boolean responsive) {
		setValue(Property.responsive, responsive);
	}

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does. Default is true.
	 */
	public boolean isResponsive() {
		return getValue(Property.responsive, DEFAULT_RESPONSIVE);
	}

	/**
	 * Sets the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @param milliseconds the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	public void setResponsiveAnimationDuration(int milliseconds) {
		setValue(Property.responsiveAnimationDuration, milliseconds);
	}

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event. Default is 0.
	 */
	public int getResponsiveAnimationDuration() {
		return getValue(Property.responsiveAnimationDuration, DEFAULT_RESPONSIVE_ANIMATION_DURATION);
	}

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		setValue(Property.maintainAspectRatio, maintainAspectRatio);
	}

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing. Default is true.
	 */
	public boolean isMaintainAspectRatio() {
		return getValue(Property.maintainAspectRatio, DEFAULT_MAINTAIN_ASPECT_RATIO);
	}

	/**
	 */
	public void setDefaultColor(String defaultColor) {
		setValue(Property.defaultColor, defaultColor);
	}

	/**
	 */
	public String getDefaultColor() {
		return getValue(Property.defaultColor, DEFAULT_COLOR);
	}

	/**
	 */
	public void setDefaultFontColor(String defaultFontColor) {
		setValue(Property.defaultFontColor, defaultFontColor);
	}

	/**
	 */
	public String getDefaultFontColor() {
		return getValue(Property.defaultFontColor, DEFAULT_FONT_COLOR);
	}

	/**
	 * Sets the font size for label.
	 * 
	 * @param fontSize Font size for label.
	 */
	public void setDefaultFontSize(int fontSize) {
		setValue(Property.defaultFontSize, fontSize);
	}

	/**
	 * Returns the font size for label.
	 * 
	 * @return Font size for label.. Default is 12.
	 */
	public int getDefaultFontSize() {
		return getValue(Property.defaultFontSize, DEFAULT_FONT_SIZE);
	}

	/**
	 * Sets the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setDefaultFontStyle(FontStyle fontStyle) {
		setValue(Property.defaultFontStyle, fontStyle);
	}

	/**
	 * Returns the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 *         Default is normal
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getDefaultFontStyle() {
		return getValue(Property.defaultFontStyle, FontStyle.class, FontStyle.normal);
	}

	/**
	 * Sets the font family for the label, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for the label, follows CSS font-family options.
	 */
	public void setDefaultFontFamily(String fontFamily) {
		setValue(Property.defaultFontFamily, fontFamily);
	}

	/**
	 * Returns the font family for the label, follows CSS font-family options.
	 * 
	 * @return Font family for the label, follows CSS font-family options. Default is 'Helvetica Neue', 'Helvetica', 'Arial',
	 *         sans-serif
	 */
	public String getDefaultFontFamily() {
		return getValue(Property.defaultFontFamily, DEFAULT_FONT_FAMILY);
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @param showLine If false, the lines between points are not drawn.
	 */
	public void setShowLines(boolean showLine) {
		setValue(Property.showLines, showLine);
	}

	/**
	 * If false, the lines between points are not drawn.
	 * 
	 * @return If false, the lines between points are not drawn.. Default is true.
	 */
	public boolean isShowLines() {
		return getValue(Property.showLines, DEFAULT_SHOW_LINES);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Options [isAnimationEnable()=" + isAnimationEnable() + ", isResponsive()=" + isResponsive() + ", getResponsiveAnimationDuration()=" + getResponsiveAnimationDuration() + ", isMaintainAspectRatio()=" + isMaintainAspectRatio()
				+ ", getDefaultColor()=" + getDefaultColor() + ", getDefaultFontColor()=" + getDefaultFontColor() + ", getDefaultFontSize()=" + getDefaultFontSize() + ", getDefaultFontStyle()=" + getDefaultFontStyle() + ", getDefaultFontFamily()="
				+ getDefaultFontFamily() + ", isShowLines()=" + isShowLines() + "]";
	}
	
	

}