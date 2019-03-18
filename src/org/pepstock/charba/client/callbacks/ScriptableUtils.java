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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.data.CanvasObjectFactory;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * Sets of common methods as utilities to manage scriptable options by callback and java interfaces.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScriptableUtils {

	/**
	 * To avoid any instantiatiopn
	 */
	private ScriptableUtils() {
		// do nothing
	}
	
	/**
	 * Returns the chart instance if callback and chart itself are consistent.
	 * 
	 * @param context scriptable context
	 * @param callback callback to check if consistent
	 * @return a char instance if callback and chart itself are consistent
	 */
	public static AbstractChart<?, ?> retrieveChart(ScriptableContext context, Object callback) {
		// checks if callback is consistent
		if (callback != null) {
			// gets chart instance
			return context.getChart();
		}
		// if here, chart or callback are not consistent
		return null;
	}

	/**
	 * Returns the enumeration value as value of the property by invoking a callback which is typed to a key.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param <T> type of callback which extends a key
	 * @return a value of property related to the enumeration value
	 */
	public static <T extends Key> T getOptionValueAsString(ScriptableContext context, Scriptable<T> callback) {
		// invokes the other methods with null as default value
		return getOptionValueAsString(context, callback, null);
	}

	/**
	 * Returns the enumeration value of the property by invoking a callback which is typed to a key, passing a default value. as
	 * argument.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param <T> type of callback which extends a key
	 * @return a value of property related to the enumeration value
	 */
	public static <T extends Key> T getOptionValueAsString(ScriptableContext context, Scriptable<T> callback, T defaultValue) {
		// gets chart instance
		AbstractChart<?, ?> chart = retrieveChart(context, callback);
		// checks if the chart is correct
		if (chart != null) {
			// calls callback
			T result = callback.invoke(chart, context);
			// checks result
			if (result != null) {
				// returns the string value
				return result;
			}
		}
		// if here, chart, callback or result of callback are not consistent
		return defaultValue;
	}

	/**
	 * Returns the value of the property as result of callback (the same type).
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param <T> type of callback result
	 * @return a value of property as result of callback invocation
	 */
	public static <T> T getOptionValue(ScriptableContext context, Scriptable<T> callback) {
		return getOptionValue(context, callback, null);
	}

	/**
	 * Returns the value of the property as result of callback (the same type), passing a default value.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param <T> type of callback result
	 * @return a value of property as result of callback invocation
	 */
	public static <T> T getOptionValue(ScriptableContext context, Scriptable<T> callback, T defaultValue) {
		// gets chart instance
		AbstractChart<?, ?> chart = retrieveChart(context, callback);
		// checks if the chart is correct
		if (chart != null) {
			// calls callback
			T result = callback.invoke(chart, context);
			// checks if consistent
			if (result != null) {
				return result;
			}
		}
		// if here, chart, callback or result of callback are not consistent
		return defaultValue;
	}

	/**
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the
	 * property in color ones.<br>
	 * By defaults, is able to manage also {@link Pattern} or {@link CanvasPattern}.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @return a value of property as color
	 */
	public static Object getOptionValueAsColor(ScriptableContext context, Scriptable<?> callback, String defaultValue) {
		return getOptionValueAsColor(context, callback, defaultValue, true);
	}

	/**
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the
	 * property in color ones.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param hasPattern if <code>true</code> is able to manage also {@link Pattern} or {@link CanvasPattern}, otherwise it
	 *            skkips them.
	 * @return a value of property as color
	 */
	public static Object getOptionValueAsColor(ScriptableContext context, Scriptable<?> callback, String defaultValue, boolean hasPattern) {
		// gets chart instance
		AbstractChart<?, ?> chart = retrieveChart(context, callback);
		// checks if the chart is correct
		if (chart != null) {
			// calls callback
			Object result = callback.invoke(chart, context);
			// checks result
			if (result instanceof IsColor) {
				// is color instance
				IsColor color = (IsColor) result;
				return color.toRGBA();
			} else if (result instanceof String) {
				// is string instance
				return (String) result;
			} else if (result instanceof Pattern && hasPattern) {
				// is pattern instance
				Pattern pattern = (Pattern) result;
				return CanvasObjectFactory.createPattern(chart, pattern);
			} else if (result instanceof Gradient) {
				// is gradient instance
				// checks if chart is initialized
				if (chart.isInitialized()) {
					Gradient gradient = (Gradient) result;
					return CanvasObjectFactory.createGradient(chart, gradient, context.getDatasetIndex(), context.getIndex());
				}
				// otherwise returns default
			} else if (result instanceof CanvasGradient) {
				// is canvas gradient instance
				return (CanvasGradient) result;
			} else if (result instanceof CanvasPattern && hasPattern) {
				// is canvas pattern instance
				return (CanvasPattern) result;
			} else if (result != null && hasPattern) {
				// another instance not null
				// returns to string
				return result.toString();
			}
		}
		// if here, chart, callback or result of callback are not consistent
		return defaultValue;
	}
}
