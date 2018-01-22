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
package org.pepstock.charba.client.defaults.global;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsEnumValueArrayList;
import org.pepstock.charba.client.commons.JsStringArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;
import org.pepstock.charba.client.defaults.scale.Scale;
import org.pepstock.charba.client.defaults.scale.Scales;
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
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Options extends AbstractItem {

	// default values
	private static final boolean DEFAULT_RESPONSIVE = true;

	private static final int DEFAULT_RESPONSIVE_ANIMATION_DURATION = 0;

	private static final boolean DEFAULT_MAINTAIN_ASPECT_RATIO = true;

	private static final String DEFAULT_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_FONT_SIZE = 12;

	private static final String DEFAULT_FONT_COLOR = "#666";

	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private static final boolean DEFAULT_SHOW_LINES = true;
	
	private static final boolean DEFAULT_SPAN_GAPS = false;
	
	private static final double DEFAULT_CUTOUT_PERCENTAGE = 0D;

	private static final double DEFAULT_ROTATION = -0.5 * Math.PI;

	private static final double DEFAULT_CIRCUMFERENCE = 2 * Math.PI;
	
	private static final double DEFAULT_START_ANGLE = -0.5 * Math.PI;

	// internal children objects
	private Hover hover;

	private Elements elements;

	private Layout layout;

	private Animation animation;

	private Tooltips tooltips;

	private Title title;

	private Legend legend;
	
	private Scale scale;
	
	private Scales scales;

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
		spanGaps,
		hover,
		elements,
		layout,
		animation,
		tooltips,
		legend,
		title,
		cutoutPercentage,
		rotation,
		circumference,
		startAngle,
		scale,
		scales
	}

	/**
	 * Creates the object using the java script object with teh defaults provided by CHART.JS.
	 * 
	 * @param javaScriptObject the java script object with teh defaults provided by CHART.JS.
	 */
	public Options(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		// creates all children objects
		hover = new Hover(this, Property.hover);
		elements = new Elements(this, Property.elements);
		layout = new Layout(this, Property.layout);
		animation = new Animation(this, Property.animation);
		tooltips = new Tooltips(this, Property.tooltips);
		legend = new Legend(this, Property.legend);
		title = new Title(this, Property.title);
		scale = new ScaleImpl(this, Property.scale);
		scales = new ScalesImpl(this, Property.scales);
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
	 * @return the scale
	 */
	public Scale getScale() {
		return scale;
	}

	/**
	 * @return the scales
	 */
	public Scales getScales() {
		return scales;
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
	 * Returns if should chart be animated or not.
	 * 
	 * @return if should chart be animated or not. Default value is <code>true</code>.
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
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @param defaultColor color to use into chart. 
	 */
	public void setDefaultColor(String defaultColor) {
		setValue(Property.defaultColor, defaultColor);
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return color to use into chart. Default is "rgba(0,0,0,0.1)"
	 */
	public String getDefaultColor() {
		return getValue(Property.defaultColor, DEFAULT_COLOR);
	}

	/**
	 * Sets the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @param defaultFontColor font color to use into chart. 
	 */
	public void setDefaultFontColor(String defaultFontColor) {
		setValue(Property.defaultFontColor, defaultFontColor);
	}

	/**
	 * Returns the default font color to use in the chart, on all objects, if not override by the specific configuration.
	 * @return  font color to use into chart. Default is #666.
	 */
	public String getDefaultFontColor() {
		return getValue(Property.defaultFontColor, DEFAULT_FONT_COLOR);
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
	 * @return Font size into chart. Default is 12.
	 */
	public int getDefaultFontSize() {
		return getValue(Property.defaultFontSize, DEFAULT_FONT_SIZE);
	}

	/**
	 * Sets the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setDefaultFontStyle(FontStyle fontStyle) {
		setValue(Property.defaultFontStyle, fontStyle);
	}

	/**
	 * Returns the font style to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font styleto use in the chart, on all objects, if not override by the specific configuration, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 *         Default is {@link org.pepstock.charba.client.enums.FontStyle#normal}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getDefaultFontStyle() {
		return getValue(Property.defaultFontStyle, FontStyle.class, FontStyle.normal);
	}

	/**
	 * Sets the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 */
	public void setDefaultFontFamily(String fontFamily) {
		setValue(Property.defaultFontFamily, fontFamily);
	}

	/**
	 * Returns the font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options.
	 * 
	 * @return Font family to use in the chart, on all objects, if not override by the specific configuration, follows CSS font-family options. Default is 'Helvetica Neue', 'Helvetica', 'Arial',
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
	 * @return If false, the lines between points are not drawn. Default is true.
	 */
	public boolean isShowLines() {
		return getValue(Property.showLines, DEFAULT_SHOW_LINES);
	}
	
	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @param spanGaps If false, NaN data causes a break in the line.
	 */
	public void setSpanGaps(boolean spanGaps) {
		setValue(Property.spanGaps, spanGaps);
	}

	/**
	 * If false, NaN data causes a break in the line.
	 * 
	 * @return If false, NaN data causes a break in the line. Default is false.
	 */
	public boolean isSpanGaps() {
		return getValue(Property.spanGaps, DEFAULT_SPAN_GAPS);
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
	 * @return the percentage of the chart that is cut out of the middle. Default is 0.
	 */
	public double getCutoutPercentage() {
		return getValue(Property.cutoutPercentage, DEFAULT_CUTOUT_PERCENTAGE);
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
	 * @return starting angle to draw arcs from. Default is <code>-0.5 * Math.PI</code>.
	 */
	public double getRotation() {
		return getValue(Property.rotation, DEFAULT_ROTATION);
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
	 * @return the sweep to allow arcs to cover. Default is <code>2 * Math.PI</code>.
	 */
	public double getCircumference() {
		return getValue(Property.circumference, DEFAULT_CIRCUMFERENCE);
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
	 * @return starting angle to draw arcs for the first item in a dataset. Default is <code>-0.5 * Math.PI</code>.
	 */
	public double getStartAngle() {
		return getValue(Property.startAngle, DEFAULT_START_ANGLE);
	}

	/**
	 * Internal scale implementation to allow the access to protected methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @see org.pepstock.charba.client.defaults.scale.Scale
	 *
	 */
	private static class ScaleImpl extends Scale {

		/**
		 * Builds the object with parent item and child.
		 * 
		 * @param parent parent item
		 * @param childKey key of child
		 */
		protected ScaleImpl(AbstractItem parent, Key childKey) {
			super(parent, childKey);
		}
	}

	/**
	 * Internal scales implementation to allow the access to protected methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @see org.pepstock.charba.client.defaults.scale.Scale
	 */
	private static class ScalesImpl extends Scales {

		/**
		 * Builds the object with parent item and child.
		 * 
		 * @param parent parent item
		 * @param childKey key of child
		 */
		protected ScalesImpl(AbstractItem parent, Key childKey) {
			super(parent, childKey);
		}
	}
}