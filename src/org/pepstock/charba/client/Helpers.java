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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.events.AbstractEvent;
import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.intl.NumberFormatOptions;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.items.IsArea;
import org.pepstock.charba.client.options.AbstractImmutableFont;
import org.pepstock.charba.client.options.IsImmutableFont;
import org.pepstock.charba.client.resources.ResourcesType;
import org.pepstock.charba.client.utils.Window;

/**
 * Singleton object to use the helpers utility of CHART.JS.<br>
 * It maps the java script object <code>chart.helpers</code>.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Helpers {

	// private default event point
	private static final EventPoint DEFAULT_EVENT_POINT = new EventPoint(null);
	// singleton instance
	private static final Helpers INSTANCE = new Helpers();
	// native object of CHART.JS which represents the helpers
	private final NativeHelpers nativeObject;

	/**
	 * To avoid any instantiation
	 */
	private Helpers() {
		// inject Chart.js and date library if not already loaded
		ResourcesType.getResources().inject();
		// gets native object from CHART.JS
		this.nativeObject = Chart.getHelpers();
	}

	/**
	 * Singleton method to get the instance
	 * 
	 * @return helper instance
	 */
	public static Helpers get() {
		return INSTANCE;
	}

	/**
	 * Recursively deep copies source properties in the target only if not defined in target.<br>
	 * IMPORTANT: target is not cloned and will be updated with source properties.
	 * 
	 * @param target the target object in which all sources are merged into.
	 * @param source object to merge in the target.
	 * @return the target object. If target and source are nulls, an empty native object is returned is returned.
	 */
	public NativeObject mergeIf(NativeObject target, NativeObject source) {
		// checks target is not null
		if (target != null) {
			// if source is not null
			if (source != null) {
				// merges the objects
				return nativeObject.mergeIf(target, source);
			} else {
				// otherwise returns the target
				return target;
			}
		} else if (source != null) {
			// if here, target is null and source not
			// therefore returns a clone of source
			return clone(source);
		}
		// arguments are either not consistent
		// returns an empty object
		return null;
	}

	/**
	 * Returns a deep copy of source without keeping references on objects and arrays.
	 * 
	 * @param source the object to clone.
	 * @return a clone of source object
	 */
	public NativeObject clone(NativeObject source) {
		// checks if argument is consistent
		if (source != null) {
			return nativeObject.clone(source);
		}
		// if here source is not consistent
		return null;
	}

	/**
	 * A common occurrence is taking an event, such as a click, and finding the data coordinates on the chart where the event occurred. It provides the relative point on canvas.
	 * 
	 * @param event native event to be used for getting the point.
	 * @return a point object
	 */
	public EventPoint getRelativePosition(AbstractEvent event) {
		// checks if even is consistent
		if (event != null) {
			return getRelativePosition(event.getChart(), event.getNativeEvent());
		}
		// if here, event is not consistent
		// then returns the default
		return DEFAULT_EVENT_POINT;
	}

	/**
	 * A common occurrence is taking an event, such as a click, and finding the data coordinates on the chart where the event occurred. It provides the relative point on canvas.
	 * 
	 * @param chart chart instance
	 * @param event native event to be used for getting the point.
	 * @return a point object
	 */
	public EventPoint getRelativePosition(IsChart chart, AbstractEvent event) {
		// checks if even is consistent
		if (event != null) {
			return getRelativePosition(chart, event.getNativeEvent());
		}
		// if here, event is not consistent
		// then returns the default
		return DEFAULT_EVENT_POINT;
	}

	/**
	 * A common occurrence is taking an event, such as a click, and finding the data coordinates on the chart where the event occurred. It provides the relative point on canvas.
	 * 
	 * @param chart chart instance
	 * @param event native event to be used for getting the point.
	 * @return a point object
	 */
	public EventPoint getRelativePosition(IsChart chart, NativeBaseEvent event) {
		// checks if even is consistent
		if (Charts.hasNative(chart) && event != null) {
			// creates and returns the point
			return new EventPoint(nativeObject.getRelativePosition(event, Charts.getNative(chart)));
		}
		// if here, event is not consistent
		// then returns the default
		return DEFAULT_EVENT_POINT;
	}

	// --------------
	// CHART
	// --------------

	/**
	 * Parses font options and returns a normalized font object.
	 * 
	 * @param font a object that contains font options to be parsed.
	 * @return a font object
	 */
	public IsImmutableFont toFont(IsDefaultFont font) {
		// checks if argument is consistent
		// creates and returns the font
		return toFont(font != null ? font.create() : Defaults.get().getGlobal().getFont().create());
	}

	/**
	 * Parses font options and returns a normalized font object.
	 * 
	 * @param font a object that contains font options to be parsed.
	 * @return a font object
	 */
	public IsImmutableFont toFont(FontItem font) {
		// checks if argument is consistent
		if (font != null) {
			// creates and returns the font
			return new ImmutableFont(nativeObject.toFont(font.nativeObject()));
		}
		// if here, event is not consistent
		// then returns defaults
		return new ImmutableFont(Defaults.get().getGlobal().getFont().create().nativeObject());
	}

	/**
	 * Builds the font string (shorthand property of CSS font) to use in the canvas object.<br>
	 * See <a href="https://www.w3schools.com/tags/canvas_font.asp">here</a> CSS specification.
	 * 
	 * @param font a object that contains font options to be parsed.
	 * @return the font string to use in the canvas object.
	 */
	public String toFontString(IsDefaultFont font) {
		// checks if argument is consistent
		// creates and returns the CSS string
		return toFont(font).toCSSString();
	}

	/**
	 * Builds the font string (shorthand property of CSS font) to use in the canvas object.<br>
	 * See <a href="https://www.w3schools.com/tags/canvas_font.asp">here</a> CSS specification.
	 * 
	 * @param font a object that contains font options to be parsed.
	 * @return the font string to use in the canvas object.
	 */
	public String toFontString(FontItem font) {
		// checks if argument is consistent
		// creates and returns the CSS string
		return toFont(font).toCSSString();
	}

	// --------------
	// CHART
	// --------------

	/**
	 * Clips an area on the canvas context using the {@link ChartAreaNode} of the chart.
	 * 
	 * @param chart chart instance
	 */
	public void clipArea(IsChart chart) {
		// checks if context is consistent
		if (IsChart.isConsistent(chart)) {
			clipArea(chart, chart.getNode().getChartArea());
		}
	}

	/**
	 * Clips an area on the canvas context, using the {@link Canvas} of the chart.
	 * 
	 * @param chart chart instance
	 * @param area area to clip on the context
	 */
	public void clipArea(IsChart chart, IsArea area) {
		// checks if context is consistent
		if (IsChart.isConsistent(chart)) {
			clipArea(chart.getCanvas(), area);
		}
	}

	/**
	 * Clips an area on the canvas context, using the {@link Canvas} of the chart.<br>
	 * The area is specified by size.<br>
	 * Starting points are 0.
	 * 
	 * @param chart chart instance
	 * @param width the rectangle's width. Positive values are to the right, and negative to the left
	 * @param height the rectangle's height. Positive values are down, and negative are up
	 */
	public void clipArea(IsChart chart, double width, double height) {
		// checks if context is consistent
		if (IsChart.isConsistent(chart)) {
			clipArea(chart.getCanvas(), width, height);
		}
	}

	/**
	 * Clips an area on the canvas context, using the {@link Canvas} of the chart.<br>
	 * The area is specified by coordinates and size.
	 * 
	 * @param chart chart instance
	 * @param x the x-axis coordinate of the rectangle's starting point
	 * @param y the y-axis coordinate of the rectangle's starting point
	 * @param width the rectangle's width. Positive values are to the right, and negative to the left
	 * @param height the rectangle's height. Positive values are down, and negative are up
	 */
	public void clipArea(IsChart chart, double x, double y, double width, double height) {
		// checks if context is consistent
		if (IsChart.isConsistent(chart)) {
			clipArea(chart.getCanvas(), x, y, width, height);
		}
	}

	// --------------
	// CANVAS
	// --------------

	/**
	 * Clips an area on the canvas context, using the {@link Context2dItem} of the canvas.
	 * 
	 * @param canvas canvas where to apply the clippping
	 * @param area area to clip on the context
	 */
	public void clipArea(Canvas canvas, IsArea area) {
		// checks if context is consistent
		if (canvas != null) {
			clipArea(canvas.getContext2d(), area);
		}
	}

	/**
	 * Clips an area on the canvas context, using the {@link Context2dItem} of the canvas.<br>
	 * The area is specified by size.<br>
	 * Starting points are 0.
	 * 
	 * @param canvas canvas where to apply the clippping
	 * @param width the rectangle's width. Positive values are to the right, and negative to the left
	 * @param height the rectangle's height. Positive values are down, and negative are up
	 */
	public void clipArea(Canvas canvas, double width, double height) {
		// checks if context is consistent
		if (canvas != null) {
			clipArea(canvas.getContext2d(), width, height);
		}
	}

	/**
	 * Clips an area on the canvas context, using the {@link Context2dItem} of the canvas.<br>
	 * The area is specified by coordinates and size.
	 * 
	 * @param canvas canvas where to apply the clippping
	 * @param x the x-axis coordinate of the rectangle's starting point
	 * @param y the y-axis coordinate of the rectangle's starting point
	 * @param width the rectangle's width. Positive values are to the right, and negative to the left
	 * @param height the rectangle's height. Positive values are down, and negative are up
	 */
	public void clipArea(Canvas canvas, double x, double y, double width, double height) {
		// checks if context is consistent
		if (canvas != null) {
			clipArea(canvas.getContext2d(), x, y, width, height);
		}
	}

	// --------------
	// CONTEXT 2D
	// --------------

	/**
	 * Clips an area on the canvas context.
	 * 
	 * @param context context of the canvas
	 * @param area area to clip on the context
	 */
	public void clipArea(Context2dItem context, IsArea area) {
		// checks if context is consistent
		if (area != null && IsArea.isConsistent(area)) {
			clipArea(context, area.getLeft(), area.getTop(), area.getWidth(), area.getHeight());
		}
	}

	/**
	 * Clips an area on the canvas context.<br>
	 * The area is specified by size.<br>
	 * Starting points are 0.
	 * 
	 * @param context context of the canvas
	 * @param width the rectangle's width. Positive values are to the right, and negative to the left
	 * @param height the rectangle's height. Positive values are down, and negative are up
	 */
	public void clipArea(Context2dItem context, double width, double height) {
		clipArea(context, 0, 0, width, height);
	}

	/**
	 * Clips an area on the canvas context.<br>
	 * The area is specified by coordinates and size.
	 * 
	 * @param context context of the canvas
	 * @param x the x-axis coordinate of the rectangle's starting point
	 * @param y the y-axis coordinate of the rectangle's starting point
	 * @param width the rectangle's width. Positive values are to the right, and negative to the left
	 * @param height the rectangle's height. Positive values are down, and negative are up
	 */
	public void clipArea(Context2dItem context, double x, double y, double width, double height) {
		// checks if context is consistent
		if (context != null && areValuesConsistent(x, y, width, height) && width >= 0 && height >= 0) {
			// clips the area in the canvas
			context.save();
			// begins path
			context.beginPath();
			// selects the area
			context.rect(x, y, width, height);
			// clip
			context.clip();
		}
	}

	// --------------
	// UNCLIP
	// --------------

	/**
	 * Unclips the area previously set.
	 * 
	 * @param context context of the canvas where an area has been clipped.
	 */
	public void unclipArea(Context2dItem context) {
		// checks if context is consistent
		if (context != null) {
			// invokes "restore" to unclip
			context.restore();
		}
	}

	/**
	 * Returns <code>true</code> if the values passed as argument are consistent.
	 * 
	 * @param values the values to be checked
	 * @return <code>true</code> if the values passed as argument are consistent
	 */
	private boolean areValuesConsistent(double... values) {
		// scans values
		for (double value : values) {
			// checks if value is consistent
			if (Checker.isNegative(value)) {
				// is not consistent
				// then returns false
				return false;
			}
		}
		// if here, all values are consistent
		return true;
	}

	// ----------------
	// INTL by CHARTJS
	// ----------------

	/**
	 * Format a number using a localized number formatter.<br>
	 * It uses the locale of the browser.
	 * 
	 * @param number the number to format
	 * @return a number formatted string
	 */
	public String formatNumber(double number) {
		return formatNumber(number, (String) null);
	}

	/**
	 * Format a number using a localized number formatter.
	 * 
	 * @param number the number to format
	 * @param locale the locale to pass to the <code>Intl.NumberFormat</code> constructor
	 * @return a number formatted string
	 */
	public String formatNumber(double number, CLocale locale) {
		return formatNumber(number, locale, null);
	}

	/**
	 * Format a number using a localized number formatter.
	 * 
	 * @param number the number to format
	 * @param locale the locale to pass to the <code>Intl.NumberFormat</code> constructor
	 * @return a number formatted string
	 */
	public String formatNumber(double number, String locale) {
		return formatNumber(number, locale, null);
	}

	/**
	 * Format a number using a localized number formatter.
	 * 
	 * @param number the number to format
	 * @param locale the locale to pass to the <code>Intl.NumberFormat</code> constructor
	 * @param options <code>Intl</code> number format options
	 * @return a number formatted string
	 */
	public String formatNumber(double number, CLocale locale, NumberFormatOptions options) {
		// invokes the native methods to format the number
		return formatNumber(number, locale != null ? locale.getIdentifier() : Window.undefined(), options);
	}

	/**
	 * Format a number using a localized number formatter.
	 * 
	 * @param number the number to format
	 * @param locale the locale to pass to the <code>Intl.NumberFormat</code> constructor
	 * @param options <code>Intl</code> number format options
	 * @return a number formatted string
	 */
	public String formatNumber(double number, String locale, NumberFormatOptions options) {
		// checks if options are consistent
		NativeObject optionsToPass;
		if (options != null) {
			// creates envelop
			ChartEnvelop<NativeObject> envelop = new ChartEnvelop<>(true);
			// loads native object from options
			options.load(envelop);
			// stores options to pass to native method of chart helpers
			optionsToPass = envelop.getContent();
		} else {
			// sets as undefined
			optionsToPass = Window.undefined();
		}
		// invokes the native methods to format the number
		return nativeObject.formatNumber(number, locale != null ? locale : Window.undefined(), optionsToPass);
	}

	/**
	 * Maps a font element normalized by CHART.JS by {@link Helpers#toFont(org.pepstock.charba.client.items.FontItem)}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class ImmutableFont extends AbstractImmutableFont {

		/**
		 * Creates a immutable font to use, wrapping a native object instance, and providing a CSS string.
		 * 
		 * @param nativeObject native object to map java script properties
		 */
		private ImmutableFont(NativeObject nativeObject) {
			super(nativeObject);
		}

	}

}
