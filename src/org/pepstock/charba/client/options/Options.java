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
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.enums.Event;
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

	private final Font font;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// object properties
		FONT("font"),
		HOVER("hover"),
		ELEMENTS("elements"),
		LAYOUT("layout"),
		ANIMATION("animation"),
		TOOLTIPS("tooltips"),
		LEGEND("legend"),
		TITLE("title"),
		PLUGINS("plugins"),
		// global options
		COLOR("color"),
		// simple properties
		RESPONSIVE("responsive"),
		MAINTAIN_ASPECT_RATIO("maintainAspectRatio"),
		ASPECT_RATIO("aspectRatio"),
		DEVICE_PIXEL_RATIO("devicePixelRatio"),
		EVENTS("events"),
		// specific for chart type
		SHOW_LINES("showLines"),
		SPAN_GAPS("spanGaps"),
		CUTOUT_PERCENTAGE("cutoutPercentage"),
		ROTATION("rotation"),
		CIRCUMFERENCE("circumference"),
		START_ANGLE("startAngle"),
		// internal key to store draw and destroy chart options
		CHARBA_DRAW_ON_ATTACH("_charbaDrawOnAttach"),
		CHARBA_DESTROY_ON_DETACH("_charbaDestroyOnDetach");

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
		plugins = new Plugins(this, Property.PLUGINS, getDefaultValues().getPlugins(), getValue(Property.PLUGINS));
		font = new Font(this, Property.FONT, DefaultsBuilder.get().getOptions().getDefaultsFont(), getValue(Property.FONT));
	}

	/**
	 * Returns the font element.<br>
	 * It contains the global defaults for font.
	 * 
	 * @return the font
	 */
	protected final Font getDefaultsFont() {
		return font;
	}

	/**
	 * Returns the animation element.
	 * 
	 * @return the animation
	 */
	@Override
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Returns the legend element.
	 * 
	 * @return the legend
	 */
	@Override
	public Legend getLegend() {
		return legend;
	}

	/**
	 * Returns the hover element.
	 * 
	 * @return the hover
	 */
	@Override
	public final Hover getHover() {
		return hover;
	}

	/**
	 * Returns the layout element.
	 * 
	 * @return the layout
	 */
	@Override
	public final Layout getLayout() {
		return layout;
	}

	/**
	 * Returns the elements element.
	 * 
	 * @return the elements
	 */
	@Override
	public final Elements getElements() {
		return elements;
	}

	/**
	 * Returns the title element.
	 * 
	 * @return the title
	 */
	@Override
	public final Title getTitle() {
		return title;
	}

	/**
	 * Returns the tooltips element.
	 * 
	 * @return the tooltips
	 */
	@Override
	public final Tooltips getTooltips() {
		return tooltips;
	}

	/**
	 * Returns the plugins element.
	 * 
	 * @return the plugins
	 */
	@Override
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
		return ArrayListHelper.list(Event.values(), array);
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
	@Override
	public boolean isResponsive() {
		return getValue(Property.RESPONSIVE, getDefaultValues().isResponsive());
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
	@Override
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
	@Override
	public double getAspectRatio() {
		return getValue(Property.ASPECT_RATIO, getDefaultValues().getAspectRatio());
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays). Setting devicePixelRatio to a value other than 1
	 * will force the canvas size to be scaled by that amount.
	 * 
	 * @param ratio the pixel ratio.
	 */
	public void setDevicePixelRatio(double ratio) {
		setValue(Property.DEVICE_PIXEL_RATIO, ratio);
	}

	/**
	 * The chart's canvas will use a 1:1 pixel ratio, unless the physical display has a higher pixel ratio (e.g. Retina displays). Setting devicePixelRatio to a value other than 1
	 * will force the canvas size to be scaled by that amount. Returns the pixel ratio.
	 * 
	 * @return the pixel ratio.
	 */
	@Override
	public double getDevicePixelRatio() {
		return getValue(Property.DEVICE_PIXEL_RATIO, getDefaultValues().getDevicePixelRatio());
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @return the drawOnAttach <code>true</code> if the chart is configured to be drawn on the attach of DIV element, otherwise <code>false</code>. Default is <code>true</code>.
	 */
	@Override
	public boolean isDrawOnAttach() {
		return getValue(Property.CHARBA_DRAW_ON_ATTACH, getDefaultValues().isDrawOnAttach());
	}

	/**
	 * Sets <code>true</code> if the chart is configured to be draw on the attach of DIV element, otherwise <code>false</code>.
	 * 
	 * @param drawOnAttach the drawOnAttach to set
	 */
	public void setDrawOnAttach(boolean drawOnAttach) {
		setValue(Property.CHARBA_DRAW_ON_ATTACH, drawOnAttach);
	}

	/**
	 * Returns <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>.
	 * 
	 * @return the destroyOnDetach <code>true</code> if the chart is configured to be destroyed on the attach of DIV element, otherwise <code>false</code>. Default is
	 *         <code>true</code>.
	 */
	@Override
	public boolean isDestroyOnDetach() {
		return getValue(Property.CHARBA_DESTROY_ON_DETACH, getDefaultValues().isDestroyOnDetach());
	}

	/**
	 * Sets <code>true</code> if the chart is configured to be destroyed on the detach from DIV element, otherwise <code>false</code>.
	 * 
	 * @param destroyOnDetach the destroyOnDetach to set
	 */
	public void setDestroyOnDetach(boolean destroyOnDetach) {
		setValue(Property.CHARBA_DESTROY_ON_DETACH, destroyOnDetach);
	}

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param defaultColor color to use into chart.
	 */
	public void setColor(IsColor defaultColor) {
		setColor(IsColor.checkAndGetValue(defaultColor));
	}

	/**
	 * Sets the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @param defaultColor color to use into chart.
	 */
	public void setColor(String defaultColor) {
		setValue(Property.COLOR, defaultColor);
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use into chart.
	 */
	@Override
	public String getColorAsString() {
		return getValue(Property.COLOR, getDefaultValues().getColorAsString());
	}

	/**
	 * Returns the default color to use in the chart, on all objects, if not override by the specific configuration.
	 * 
	 * @return color to use into chart.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public double getStartAngle() {
		return getValue(Property.START_ANGLE, getDefaultValues().getStartAngle());
	}
}
