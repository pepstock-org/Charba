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

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Base object which maps chart options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Options extends AbstractModel<Options, IsDefaultOptions> implements IsDefaultOptions {

	// all sub elements
	private final Animation animation;

	private final Legend legend;

	private final Hover hover;

	private final Layout layout;

	private final Elements elements;

	private final Title title;

	private final Tooltips tooltips;

	private final Plugins plugins;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		HOVER("hover"),
		ELEMENTS("elements"),
		LAYOUT("layout"),
		ANIMATION("animation"),
		TOOLTIPS("tooltips"),
		LEGEND("legend"),
		TITLE("title"),
		PLUGINS("plugins"),
		RESPONSIVE("responsive"),
		RESPONSIVE_ANIMATION_DURATION("responsiveAnimationDuration"),
		MAINTAIN_ASPECT_RATIO("maintainAspectRatio"),
		ASPECT_RATIO("aspectRatio"),
		EVENTS("events"),
		DEFAULT_COLOR("defaultColor"),
		DEFAULT_FONT_COLOR("defaultFontColor"),
		DEFAULT_FONT_FAMILY("defaultFontFamily"),
		DEFAULT_FONT_SIZE("defaultFontSize"),
		DEFAULT_FONT_STYLE("defaultFontStyle"),
		DEVICE_PIXEL_RATIO("devicePixelRatio"),
		SHOW_LINES("showLines"),
		SPAN_GAPS("spanGaps"),
		CUTOUT_PERCENTAGE("cutoutPercentage"),
		ROTATION("rotation"),
		CIRCUMFERENCE("circumference"),
		START_ANGLE("startAngle");

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

	/**
	 * Creates the object only with default provider. This is used as the root element.<br>
	 * New native java script object is created and it's empty.
	 * 
	 * @param defaultValues default provider instance.
	 */
	protected Options(IsDefaultOptions defaultValues) {
		this(defaultValues, null);
	}

	/**
	 * Creates the object only with default provider and native object. This is used as the root element.
	 * 
	 * @param defaultValues default provider instance.
	 * @param nativeObject native object to store properties.
	 */
	protected Options(IsDefaultOptions defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
		// gets all sub elements
		animation = new Animation(this, Property.ANIMATION, getDefaultValues().getAnimation(), getValue(Property.ANIMATION));
		legend = new Legend(this, Property.LEGEND, getDefaultValues().getLegend(), getValue(Property.LEGEND));
		elements = new Elements(this, Property.ELEMENTS, defaultValues.getElements(), getValue(Property.ELEMENTS));
		hover = new Hover(this, Property.HOVER, getDefaultValues().getHover(), getValue(Property.HOVER));
		layout = new Layout(this, Property.LAYOUT, defaultValues.getLayout(), getValue(Property.LAYOUT));
		title = new Title(this, Property.TITLE, getDefaultValues().getTitle(), getValue(Property.TITLE));
		tooltips = new Tooltips(this, Property.TOOLTIPS, getDefaultValues().getTooltips(), getValue(Property.TOOLTIPS));
		plugins = new Plugins(this, Property.PLUGINS, getValue(Property.PLUGINS));
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Returns the legend element.
	 * 
	 * @return the legend
	 */
	public Legend getLegend() {
		return legend;
	}

	/**
	 * Returns the hover element.
	 * 
	 * @return the hover
	 */
	public final Hover getHover() {
		return hover;
	}

	/**
	 * Returns the layout element.
	 * 
	 * @return the layout
	 */
	public final Layout getLayout() {
		return layout;
	}

	/**
	 * Returns the elements element.
	 * 
	 * @return the elements
	 */
	public final Elements getElements() {
		return elements;
	}

	/**
	 * Returns the title element.
	 * 
	 * @return the title
	 */
	public final Title getTitle() {
		return title;
	}

	/**
	 * Returns the tooltips element.
	 * 
	 * @return the tooltips
	 */
	public final Tooltips getTooltips() {
		return tooltips;
	}

	/**
	 * Returns the plugins element.
	 * 
	 * @return the plugins
	 */
	public final Plugins getPlugins() {
		return plugins;
	}

	/**
	 * Returns the CHARBA id when the options are related to a chart instance.
	 * 
	 * @return the CHARBA id when the options are related to a chart instance otherwise {@link UndefinedValues#STRING}.
	 */
	public String getCharbaId() {
		return Id.get(this);
	}

	/**
	 * Sets the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @param events the browser events that the chart should listen to for tooltips and hovering.
	 */
	public void setEvents(Event... events) {
		// sets the array of events
		setArrayValue(Property.EVENTS, ArrayString.fromOrNull(events));
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering.
	 */
	public List<Event> getEvents() {
		ArrayString array = getArrayValue(Property.EVENTS);
		return ArrayListHelper.list(Event.class, array);
	}

	/**
	 * Sets the resizing of the chart canvas when its container does.
	 * 
	 * @param responsive the resizing of the chart canvas when its container does.
	 */
	public void setResponsive(boolean responsive) {
		setValue(Property.RESPONSIVE, responsive);
	}

	/**
	 * Returns the resizing of the chart canvas when its container does.
	 * 
	 * @return the resizing of the chart canvas when its container does.
	 */
	public boolean isResponsive() {
		return getValue(Property.RESPONSIVE, getDefaultValues().isResponsive());
	}

	/**
	 * Sets the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @param milliseconds the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	public void setResponsiveAnimationDuration(int milliseconds) {
		setValue(Property.RESPONSIVE_ANIMATION_DURATION, milliseconds);
	}

	/**
	 * Returns the duration in milliseconds it takes to animate to new size after a resize event.
	 * 
	 * @return the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	public int getResponsiveAnimationDuration() {
		return getValue(Property.RESPONSIVE_ANIMATION_DURATION, getDefaultValues().getResponsiveAnimationDuration());
	}

	/**
	 * Sets the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @param maintainAspectRatio the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		setValue(Property.MAINTAIN_ASPECT_RATIO, maintainAspectRatio);
	}

	/**
	 * Returns the the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 * 
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public boolean isMaintainAspectRatio() {
		return getValue(Property.MAINTAIN_ASPECT_RATIO, getDefaultValues().isMaintainAspectRatio());
	}

	/**
	 * Canvas aspect ratio (i.e. width / height, a value of 1 representing a square canvas).<br>
	 * Note that this option is ignored if the height is explicitly defined either as attribute or via the style.
	 * 
	 * @param ratio the aspect ratio.
	 */
	public void setAspectRatio(double ratio) {
		setValue(Property.ASPECT_RATIO, ratio);
	}

	/**
	 * Canvas aspect ratio (i.e. width / height, a value of 1 representing a square canvas).<br>
	 * Note that this option is ignored if the height is explicitly defined either as attribute or via the style.
	 * 
	 * @return the aspect ratio.
	 */
	public double getAspectRatio() {
		return getValue(Property.ASPECT_RATIO, getDefaultValues().getAspectRatio());
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina
	 * displays). Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		setValue(Property.DEVICE_PIXEL_RATIO, ratio);
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina
	 * displays). Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns the pixel ratio.
	 * 
	 * @return the pixel ratio.
	 */
	public double getDevicePixelRatio() {
		return getValue(Property.DEVICE_PIXEL_RATIO, getDefaultValues().getDevicePixelRatio());
	}

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param defaultColor color to use into chart.
	 */
	public void setDefaultColor(IsColor defaultColor) {
		setDefaultColor(defaultColor.toRGBA());
	}

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param defaultColor color to use into chart.
	 */
	public void setDefaultColor(String defaultColor) {
		setValue(Property.DEFAULT_COLOR, defaultColor);
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use into chart.
	 */
	public String getDefaultColorAsString() {
		return getValue(Property.DEFAULT_COLOR, getDefaultValues().getDefaultColorAsString());
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use into chart.
	 */
	public IsColor getDefaultColor() {
		return ColorBuilder.parse(getDefaultColorAsString());
	}

	/**
	 * Sets the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param defaultFontColor font color to use into chart.
	 */
	public void setDefaultFontColor(IsColor defaultFontColor) {
		setDefaultFontColor(defaultFontColor.toRGBA());
	}

	/**
	 * Sets the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param defaultFontColor font color to use into chart.
	 */
	public void setDefaultFontColor(String defaultFontColor) {
		setValue(Property.DEFAULT_FONT_COLOR, defaultFontColor);
	}

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return font color to use into chart.
	 */
	public String getDefaultFontColorAsString() {
		return getValue(Property.DEFAULT_FONT_COLOR, getDefaultValues().getDefaultFontColorAsString());
	}

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return font color to use into chart.
	 */
	public IsColor getDefaultFontColor() {
		return ColorBuilder.parse(getDefaultFontColorAsString());
	}

	/**
	 * Sets the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param fontSize Font size into chart.
	 */
	public void setDefaultFontSize(int fontSize) {
		setValue(Property.DEFAULT_FONT_SIZE, fontSize);
	}

	/**
	 * Returns the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return font size into chart.
	 */
	public int getDefaultFontSize() {
		return getValue(Property.DEFAULT_FONT_SIZE, getDefaultValues().getDefaultFontSize());
	}

	/**
	 * Sets the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 * font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle font style to use in the chart, on all objects, if not override by the specific configuration, follows
	 *            CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setDefaultFontStyle(FontStyle fontStyle) {
		setValue(Property.DEFAULT_FONT_STYLE, fontStyle.value());
	}

	/**
	 * Returns the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 * font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 *         font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getDefaultFontStyle() {
		return getValue(Property.DEFAULT_FONT_STYLE, FontStyle.class, getDefaultValues().getDefaultFontStyle());
	}

	/**
	 * Sets the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 * font-family options.
	 * 
	 * @param fontFamily Font family to use in the chart, on all objects, if not override by the specific configuration, follows
	 *            CSS font-family options.
	 */
	public void setDefaultFontFamily(String fontFamily) {
		setValue(Property.DEFAULT_FONT_FAMILY, fontFamily);
	}

	/**
	 * Returns the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 * font-family options.
	 * 
	 * @return Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 *         font-family options.
	 */
	public String getDefaultFontFamily() {
		return getValue(Property.DEFAULT_FONT_FAMILY, getDefaultValues().getDefaultFontFamily());
	}

	/**
	 * If <code>false</code>, the lines between points are not drawn.
	 * 
	 * @param showLine if <code>false</code>, the lines between points are not drawn.
	 */
	public void setShowLines(boolean showLine) {
		setValue(Property.SHOW_LINES, showLine);
	}

	/**
	 * If <code>false</code>, the lines between points are not drawn.
	 * 
	 * @return if <code>false</code>, the lines between points are not drawn..
	 */
	public boolean isShowLines() {
		return getValue(Property.SHOW_LINES, getDefaultValues().isShowLines());
	}

	/**
	 * If <code>false</code>, <code>NaN</code> data causes a break in the line.
	 * 
	 * @param spanGaps if <code>false</code>, <code>NaN</code> data causes a break in the line.
	 */
	public void setSpanGaps(boolean spanGaps) {
		setValue(Property.SPAN_GAPS, spanGaps);
	}

	/**
	 * If <code>false</code>, <code>NaN</code> data causes a break in the line.
	 * 
	 * @return if <code>false</code>, <code>NaN</code> data causes a break in the line.
	 */
	public boolean isSpanGaps() {
		return getValue(Property.SPAN_GAPS, getDefaultValues().isSpanGaps());
	}

	/**
	 * Sets the percentage of the chart that is cut out of the middle.
	 * 
	 * @param cutoutPercentage the percentage of the chart that is cut out of the middle.
	 */
	public void setCutoutPercentage(double cutoutPercentage) {
		setValue(Property.CUTOUT_PERCENTAGE, cutoutPercentage);
	}

	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle.
	 */
	public double getCutoutPercentage() {
		return getValue(Property.CUTOUT_PERCENTAGE, getDefaultValues().getCutoutPercentage());
	}

	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	public void setRotation(double rotation) {
		setValue(Property.ROTATION, rotation);
	}

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from.
	 */
	public double getRotation() {
		return getValue(Property.ROTATION, getDefaultValues().getRotation());
	}

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	public void setCircumference(double circumference) {
		setValue(Property.CIRCUMFERENCE, circumference);
	}

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover.
	 */
	public double getCircumference() {
		return getValue(Property.CIRCUMFERENCE, getDefaultValues().getCircumference());
	}

	/**
	 * Sets the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @param startAngle starting angle to draw arcs for the first item in a dataset.
	 */
	public void setStartAngle(double startAngle) {
		setValue(Property.START_ANGLE, startAngle);
	}

	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset.
	 */
	public double getStartAngle() {
		return getValue(Property.START_ANGLE, getDefaultValues().getStartAngle());
	}
}
