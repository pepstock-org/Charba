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
		hover,
		elements,
		layout,
		animation,
		tooltips,
		legend,
		title,
		plugins,
		responsive,
		responsiveAnimationDuration,
		maintainAspectRatio,
		aspectRatio,
		events,
		defaultColor,
		defaultFontColor,
		defaultFontFamily,
		defaultFontSize,
		defaultFontStyle,
		devicePixelRatio,
		showLines,
		spanGaps,
		cutoutPercentage,
		rotation,
		circumference,
		startAngle;
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
		animation = new Animation(this, Property.animation, getDefaultValues().getAnimation(), getValue(Property.animation));
		legend = new Legend(this, Property.legend, getDefaultValues().getLegend(), getValue(Property.legend));
		elements = new Elements(this, Property.elements, defaultValues.getElements(), getValue(Property.elements));
		hover = new Hover(this, Property.hover, getDefaultValues().getHover(), getValue(Property.hover));
		layout = new Layout(this, Property.layout, defaultValues.getLayout(), getValue(Property.layout));
		title = new Title(this, Property.title, getDefaultValues().getTitle(), getValue(Property.title));
		tooltips = new Tooltips(this, Property.tooltips, getDefaultValues().getTooltips(), getValue(Property.tooltips));
		plugins = new Plugins(this, Property.plugins, getValue(Property.plugins));
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
	 * @return the CHARBA id when the options are related to a chart instance otherwise
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
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
		// checks the events passed
		// if empty
		if (events == null || events.length == 0) {
			// remove java script property
			remove(Property.events);
		} else {
			// sets the array of events
			setArrayValue(Property.events, ArrayString.of(events));
		}
	}

	/**
	 * Returns the browser events that the chart should listen to for tooltips and hovering.
	 * 
	 * @return the browser events that the chart should listen to for tooltips and hovering.
	 */
	public List<Event> getEvents() {
		ArrayString array = getArrayValue(Property.events);
		return ArrayListHelper.list(Event.class, array);
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
	 * @return the resizing of the chart canvas when its container does.
	 */
	public boolean isResponsive() {
		return getValue(Property.responsive, getDefaultValues().isResponsive());
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
	 * @return the duration in milliseconds it takes to animate to new size after a resize event.
	 */
	public int getResponsiveAnimationDuration() {
		return getValue(Property.responsiveAnimationDuration, getDefaultValues().getResponsiveAnimationDuration());
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
	 * @return the maintaining of the original canvas aspect ratio (width / height) when resizing.
	 */
	public boolean isMaintainAspectRatio() {
		return getValue(Property.maintainAspectRatio, getDefaultValues().isMaintainAspectRatio());
	}

	/**
	 * Canvas aspect ratio (i.e. width / height, a value of 1 representing a square canvas).<br>
	 * Note that this option is ignored if the height is explicitly defined either as attribute or via the style.
	 * 
	 * @param ratio the aspect ratio.
	 */
	public void setAspectRatio(double ratio) {
		setValue(Property.aspectRatio, ratio);
	}

	/**
	 * Canvas aspect ratio (i.e. width / height, a value of 1 representing a square canvas).<br>
	 * Note that this option is ignored if the height is explicitly defined either as attribute or via the style.
	 * 
	 * @return the aspect ratio.
	 */
	public double getAspectRatio() {
		return getValue(Property.aspectRatio, getDefaultValues().getAspectRatio());
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina
	 * displays). Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		setValue(Property.devicePixelRatio, ratio);
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina
	 * displays). Setting devicePixelRatio to a value other than 1 will force the canvas size to be scaled by that amount.
	 * Returns the pixel ratio.
	 * 
	 * @return the pixel ratio.
	 */
	public double getDevicePixelRatio() {
		return getValue(Property.devicePixelRatio, getDefaultValues().getDevicePixelRatio());
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
		setValue(Property.defaultColor, defaultColor);
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use into chart.
	 */
	public String getDefaultColorAsString() {
		return getValue(Property.defaultColor, getDefaultValues().getDefaultColorAsString());
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
		setValue(Property.defaultFontColor, defaultFontColor);
	}

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return font color to use into chart.
	 */
	public String getDefaultFontColorAsString() {
		return getValue(Property.defaultFontColor, getDefaultValues().getDefaultFontColorAsString());
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
		setValue(Property.defaultFontSize, fontSize);
	}

	/**
	 * Returns the font size to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return font size into chart.
	 */
	public int getDefaultFontSize() {
		return getValue(Property.defaultFontSize, getDefaultValues().getDefaultFontSize());
	}

	/**
	 * Sets the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 * font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle font style to use in the chart, on all objects, if not override by the specific configuration, follows
	 *            CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public void setDefaultFontStyle(FontStyle fontStyle) {
		setValue(Property.defaultFontStyle, fontStyle.name());
	}

	/**
	 * Returns the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 * font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 *         font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getDefaultFontStyle() {
		return getValue(Property.defaultFontStyle, FontStyle.class, getDefaultValues().getDefaultFontStyle());
	}

	/**
	 * Sets the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 * font-family options.
	 * 
	 * @param fontFamily Font family to use in the chart, on all objects, if not override by the specific configuration, follows
	 *            CSS font-family options.
	 */
	public void setDefaultFontFamily(String fontFamily) {
		setValue(Property.defaultFontFamily, fontFamily);
	}

	/**
	 * Returns the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 * font-family options.
	 * 
	 * @return Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS
	 *         font-family options.
	 */
	public String getDefaultFontFamily() {
		return getValue(Property.defaultFontFamily, getDefaultValues().getDefaultFontFamily());
	}

	/**
	 * If <code>false</code>, the lines between points are not drawn.
	 * 
	 * @param showLine if <code>false</code>, the lines between points are not drawn.
	 */
	public void setShowLines(boolean showLine) {
		setValue(Property.showLines, showLine);
	}

	/**
	 * If <code>false</code>, the lines between points are not drawn.
	 * 
	 * @return if <code>false</code>, the lines between points are not drawn..
	 */
	public boolean isShowLines() {
		return getValue(Property.showLines, getDefaultValues().isShowLines());
	}

	/**
	 * If <code>false</code>, <code>NaN</code> data causes a break in the line.
	 * 
	 * @param spanGaps if <code>false</code>, <code>NaN</code> data causes a break in the line.
	 */
	public void setSpanGaps(boolean spanGaps) {
		setValue(Property.spanGaps, spanGaps);
	}

	/**
	 * If <code>false</code>, <code>NaN</code> data causes a break in the line.
	 * 
	 * @return if <code>false</code>, <code>NaN</code> data causes a break in the line.
	 */
	public boolean isSpanGaps() {
		return getValue(Property.spanGaps, getDefaultValues().isSpanGaps());
	}

	/**
	 * Sets the percentage of the chart that is cut out of the middle.
	 * 
	 * @param cutoutPercentage the percentage of the chart that is cut out of the middle.
	 */
	public void setCutoutPercentage(double cutoutPercentage) {
		setValue(Property.cutoutPercentage, cutoutPercentage);
	}

	/**
	 * Returns the the percentage of the chart that is cut out of the middle.
	 * 
	 * @return the percentage of the chart that is cut out of the middle.
	 */
	public double getCutoutPercentage() {
		return getValue(Property.cutoutPercentage, getDefaultValues().getCutoutPercentage());
	}

	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	public void setRotation(double rotation) {
		setValue(Property.rotation, rotation);
	}

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from.
	 */
	public double getRotation() {
		return getValue(Property.rotation, getDefaultValues().getRotation());
	}

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	public void setCircumference(double circumference) {
		setValue(Property.circumference, circumference);
	}

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover.
	 */
	public double getCircumference() {
		return getValue(Property.circumference, getDefaultValues().getCircumference());
	}

	/**
	 * Sets the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @param startAngle starting angle to draw arcs for the first item in a dataset.
	 */
	public void setStartAngle(double startAngle) {
		setValue(Property.startAngle, startAngle);
	}

	/**
	 * Returns the starting angle to draw arcs for the first item in a dataset.
	 * 
	 * @return starting angle to draw arcs for the first item in a dataset.
	 */
	public double getStartAngle() {
		return getValue(Property.startAngle, getDefaultValues().getStartAngle());
	}
}
