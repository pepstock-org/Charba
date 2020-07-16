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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.data.DatasetCanvasObjectFactory;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;

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
	public static IsChart retrieveChart(AbstractScriptableContext context, Object callback) {
		// checks if arguments are consistent
		if (callback != null && context != null) {
			// gets chart instance
			return context.getChart();
		}
		// if here, chart or callback are not consistent
		return null;
	}

	// ------------------------------------
	// --- OPTIONS callbacks management
	// ------------------------------------

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
	 * Returns the enumeration value of the property by invoking a callback which is typed to a key, passing a default value. as argument.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param <T> type of callback which extends a key
	 * @return a value of property related to the enumeration value
	 */
	public static <T extends Key> T getOptionValueAsString(ScriptableContext context, Scriptable<T> callback, T defaultValue) {
		// gets chart instance
		IsChart chart = retrieveChart(context, callback);
		// checks if the chart is correct
		if (IsChart.isValid(chart) && callback != null) {
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
		IsChart chart = retrieveChart(context, callback);
		// checks if the chart is correct
		if (IsChart.isValid(chart) && callback != null) {
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
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the property in color ones.<br>
	 * By defaults, is able to manage also {@link Pattern} or {@link CanvasPatternItem}.
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
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the property in color ones.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param hasPattern if <code>true</code> is able to manage also {@link Pattern} or {@link CanvasPatternItem}, otherwise it skips them.
	 * @return a value of property as color
	 */
	public static Object getOptionValueAsColor(ScriptableContext context, Scriptable<?> callback, String defaultValue, boolean hasPattern) {
		// gets chart instance
		IsChart chart = retrieveChart(context, callback);
		// checks if the chart is correct
		// checks if the chart is correct
		if (IsChart.isValid(chart) && callback != null && context != null) {
			// calls callback
			Object result = callback.invoke(chart, context);
			// invokes the callback result handler
			return handleCallbackResultAsColor(context, result, defaultValue, hasPattern);
		}
		// if here, chart, callback or result of callback are not consistent
		return defaultValue;
	}

	/**
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the property in color ones.
	 * 
	 * @param context scriptable context
	 * @param result result of callback invocation
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param hasPattern if <code>true</code> is able to manage also {@link Pattern} or {@link CanvasPatternItem}, otherwise it skips them.
	 * @return a value of property as color
	 */
	public static Object handleCallbackResultAsColor(ScriptableContext context, Object result, String defaultValue, boolean hasPattern) {
		// invokes common handler
		Object checkedResult = doHandleCallbackResultAsColor(context, result, defaultValue, hasPattern);
		// checks if is a gradient
		if (result instanceof Gradient) {
			// is gradient instance
			Gradient gradient = (Gradient) result;
			// then it must be translated into a canvas gradient
			return DatasetCanvasObjectFactory.get().createGradient(context.getChart(), gradient, context.getDatasetIndex(), context.getIndex());
		}
		return checkedResult;
	}

	// ------------------------------------
	// --- SCALES callbacks management
	// ------------------------------------

	/**
	 * Returns the enumeration value as value of the property by invoking a callback which is typed to a key.
	 * 
	 * @param axis axis instance where the callback should be applied
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param <T> type of callback which extends a key
	 * @return a value of property related to the enumeration value
	 */
	public static <T extends Key> T getOptionValueAsString(Axis axis, ScaleScriptableContext context, ScaleScriptable<T> callback) {
		// invokes the other methods with null as default value
		return getOptionValueAsString(axis, context, callback, null);
	}

	/**
	 * Returns the enumeration value of the property by invoking a callback which is typed to a key, passing a default value. as argument.
	 * 
	 * @param axis axis instance where the callback should be applied
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param <T> type of callback which extends a key
	 * @return a value of property related to the enumeration value
	 */
	public static <T extends Key> T getOptionValueAsString(Axis axis, ScaleScriptableContext context, ScaleScriptable<T> callback, T defaultValue) {
		// gets chart instance
		IsChart chart = retrieveChart(context, callback);
		// checks if the axis and chart are correct
		if (axis != null && IsChart.isValid(chart) && callback != null) {
			// calls callback
			T result = callback.invoke(axis, context);
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
	 * @param axis axis instance where the callback should be applied
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param <T> type of callback result
	 * @return a value of property as result of callback invocation
	 */
	public static <T> T getOptionValue(Axis axis, ScaleScriptableContext context, ScaleScriptable<T> callback) {
		return getOptionValue(axis, context, callback, null);
	}

	/**
	 * Returns the value of the property as result of callback (the same type), passing a default value.
	 * 
	 * @param axis axis instance where the callback should be applied
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param <T> type of callback result
	 * @return a value of property as result of callback invocation
	 */
	public static <T> T getOptionValue(Axis axis, ScaleScriptableContext context, ScaleScriptable<T> callback, T defaultValue) {
		// gets chart instance
		IsChart chart = retrieveChart(context, callback);
		// checks if the axis and chart are correct
		if (axis != null && IsChart.isValid(chart) && callback != null) {
			T result = callback.invoke(axis, context);
			// checks if consistent
			if (result != null) {
				return result;
			}
		}
		// if here, chart, callback or result of callback are not consistent
		return defaultValue;
	}

	/**
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the property in color ones.<br>
	 * By defaults, is able to manage also {@link Pattern} or {@link CanvasPatternItem}.
	 * 
	 * @param axis axis instance where the callback should be applied
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @return a value of property as color
	 */
	public static Object getOptionValueAsColor(Axis axis, ScaleScriptableContext context, ScaleScriptable<?> callback, String defaultValue) {
		return getOptionValueAsColor(axis, context, callback, defaultValue, true);
	}

	/**
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the property in color ones.
	 * 
	 * @param axis axis instance where the callback should be applied
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param hasPattern if <code>true</code> is able to manage also {@link Pattern} or {@link CanvasPatternItem}, otherwise it skips them.
	 * @return a value of property as color
	 */
	public static Object getOptionValueAsColor(Axis axis, ScaleScriptableContext context, ScaleScriptable<?> callback, String defaultValue, boolean hasPattern) {
		// gets chart instance
		IsChart chart = retrieveChart(context, callback);
		// checks if the chart is correct
		// checks if the chart is correct
		if (IsChart.isValid(chart) && callback != null && context != null) {
			// calls callback
			Object result = callback.invoke(axis, context);
			// invokes common handler
			Object checkedResult = doHandleCallbackResultAsColor(context, result, defaultValue, hasPattern);
			// checks if is a gradient
			if (result instanceof Gradient) {
				// is gradient instance
				Gradient gradient = (Gradient) result;
				// then it must be translated into a canvas gradient
				return DatasetCanvasObjectFactory.get().createGradient(context.getChart(), gradient, 0, context.getIndex());
			}
			// returns the result
			return checkedResult;
		}
		// if here, chart, callback or result of callback are not consistent
		return defaultValue;
	}

	/**
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the property in color ones.
	 * 
	 * @param context scriptable context
	 * @param result result of callback invocation
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param hasPattern if <code>true</code> is able to manage also {@link Pattern} or {@link CanvasPatternItem}, otherwise it skips them.
	 * @return a value of property as color
	 */
	private static Object doHandleCallbackResultAsColor(AbstractScriptableContext context, Object result, String defaultValue, boolean hasPattern) {
		// checks if the context and chart are correct
		if (context != null && IsChart.isValid(context.getChart()) && result != null) {
			// gets chart instance
			IsChart chart = context.getChart();
			// checks result
			if (result instanceof IsColor) {
				// is color instance
				IsColor color = (IsColor) result;
				// checks if the color is consistent
				if (IsColor.isConsistent(color)) {
					// then returns RGBA representation
					return color.toRGBA();
				}
			} else if (result instanceof String) {
				// is string instance
				return result;
			} else if (result instanceof Pattern && hasPattern) {
				// is pattern instance
				Pattern pattern = (Pattern) result;
				return DatasetCanvasObjectFactory.get().createPattern(chart, pattern);
			} else if (result instanceof CanvasGradientItem || result instanceof Gradient) {
				// is canvas gradient or charba gradient instance
				return result;
			} else if (result instanceof CanvasPatternItem && hasPattern) {
				// is canvas pattern instance
				return result;
			} else if (hasPattern) {
				// another instance not null
				// returns to string
				return result.toString();
			}
		}
		// if here, chart, callback or result of callback are not consistent
		return defaultValue;
	}
}
