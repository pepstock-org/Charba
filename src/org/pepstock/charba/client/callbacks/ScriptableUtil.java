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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.data.DatasetCanvasObjectFactory;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.items.PaddingItem;

/**
 * Sets of common methods as utilities to manage scriptable options by callback and java interfaces.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScriptableUtil {

	private static final PaddingItem DEFAULT_PADDING_FOR_CALLBACK = new PaddingItem(0);

	/**
	 * To avoid any instantiation
	 */
	private ScriptableUtil() {
		// do nothing
	}

	/**
	 * Returns <code>true</code> if context is consistent, otherwise <code>false</code>.
	 * 
	 * @param context scriptable context
	 * @return <code>true</code> if context is consistent, otherwise <code>false</code>
	 */
	public static boolean isContextConsistent(ChartContext context) {
		// checks if arguments are consistent
		return context != null && context.isConsistent();
	}

	/**
	 * Returns the chart instance, contained in the context if context is consistent.
	 * 
	 * @param context scriptable context
	 * @return the chart instance, contained in the context if context is consistent
	 */
	public static IsChart retrieveChart(ChartContext context) {
		// checks if arguments are consistent
		if (isContextConsistent(context)) {
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
	 * Returns the padding item value as value of the property by invoking a callback which is typed to a paddng item.
	 * 
	 * @param context native object as context
	 * @param callback padding callback to execute
	 * @param defaultValue default padding if the callback returns an inconsistent value
	 * @param <C> type of context to pass to the callback
	 * @return a value of property related to the padding value
	 */
	public static <C extends ChartContext> PaddingItem getOptionValueAsPadding(C context, Scriptable<PaddingItem, C> callback, IsDefaultPadding defaultValue) {
		return ScriptableUtil.getOptionValue(context, callback, defaultValue != null ? defaultValue.create() : DEFAULT_PADDING_FOR_CALLBACK.create());
	}

	/**
	 * Returns the font item value as value of the property by invoking a callback which is typed to a font item.
	 * 
	 * @param context native object as context
	 * @param callback font callback to execute
	 * @param defaultValue default font if the callback returns an inconsistent value
	 * @param <C> type of context to pass to the callback
	 * @return a value of property related to the font value
	 */
	public static <C extends ChartContext> FontItem getOptionValueAsFont(C context, Scriptable<FontItem, C> callback, IsDefaultFont defaultValue) {
		// checks if default font is consistent
		Checker.checkIfValid(defaultValue, "Default font item");
		// gets value
		return ScriptableUtil.getOptionValue(context, callback, defaultValue.create());
	}

	/**
	 * Returns the enumeration value as value of the property by invoking a callback which is typed to a key.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param <T> type of result of the callback which extends a key
	 * @param <C> type of context to pass to the callback
	 * @return a value of property related to the enumeration value
	 */
	public static <T extends Key, C extends ChartContext> T getOptionValueAsString(C context, Scriptable<T, C> callback) {
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
	 * @param <C> type of context to pass to the callback
	 * @return a value of property related to the enumeration value
	 */
	public static <T extends Key, C extends ChartContext> T getOptionValueAsString(C context, Scriptable<T, C> callback, T defaultValue) {
		// checks if the chart is correct
		if (isContextConsistent(context) && callback != null) {
			// calls callback
			T result = callback.invoke(context);
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
	 * Returns the value, as {@link Number}, of the property as result of callback (the same type), passing a default value.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param <T> type of callback result, as {@link Number}
	 * @param <C> type of context to pass to the callback
	 * @return a value of property as result of callback invocation
	 */
	public static <T extends Number, C extends ChartContext> T getOptionValueAsNumber(C context, Scriptable<T, C> callback, T defaultValue) {
		// if here, chart, callback or result of callback are not consistent
		return getOptionValueAsNumber(context, callback, defaultValue, null);
	}

	/**
	 * Returns the value, as {@link Number}, of the property as result of callback (the same type), passing a default value and a {@link ScriptableResultChecker} to check the
	 * consistency of the result.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param checker {@link ScriptableResultChecker} to check the consistency of the value
	 * @param <T> type of callback result, as {@link Number}
	 * @param <C> type of context to pass to the callback
	 * @return a value of property as result of callback invocation
	 */
	public static <T extends Number, C extends ChartContext> T getOptionValueAsNumber(C context, Scriptable<T, C> callback, T defaultValue, ScriptableResultChecker<T> checker) {
		// checks if the chart is correct
		if (isContextConsistent(context) && callback != null) {
			T result = callback.invoke(context);
			// checks if consistent
			if (checker != null && result != null) {
				// passes thru the checker
				return checker.checkAndGet(result, defaultValue);
			} else if (result != null) {
				// if here, checker is not passed
				// then returns result
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
	 * @param <C> type of context to pass to the callback
	 * @return a value of property as result of callback invocation
	 */
	public static <T, C extends ChartContext> T getOptionValue(C context, Scriptable<T, C> callback) {
		return getOptionValue(context, callback, null);
	}

	/**
	 * Returns the value of the property as result of callback (the same type), passing a default value.
	 *
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param <T> type of callback result
	 * @param <C> type of context to pass to the callback
	 * @return a value of property as result of callback invocation
	 */
	public static <T, C extends ChartContext> T getOptionValue(C context, Scriptable<T, C> callback, T defaultValue) {
		// checks if the chart is correct
		if (isContextConsistent(context) && callback != null) {
			T result = callback.invoke(context);
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
	 * @param <C> type of context to pass to the callback
	 * @return a value of property as color
	 */
	public static <C extends ChartContext> Object getOptionValueAsColor(C context, Scriptable<?, C> callback, String defaultValue) {
		return getOptionValueAsColor(context, callback, defaultValue, true);
	}

	/**
	 * Returns a color value of property by a callback, checking all different types of object which can be used as value of the property in color ones.
	 * 
	 * @param context scriptable context
	 * @param callback callback to invoke
	 * @param defaultValue default value to return in case of chart, callback or result of callback are not consistent.
	 * @param hasPattern if <code>true</code> is able to manage also {@link Pattern} or {@link CanvasPatternItem}, otherwise it skips them.
	 * @param <C> type of context to pass to the callback
	 * @return a value of property as color
	 */
	public static <C extends ChartContext> Object getOptionValueAsColor(C context, Scriptable<?, C> callback, String defaultValue, boolean hasPattern) {
		// checks if the context is consistent
		if (isContextConsistent(context) && callback != null) {
			// calls callback
			Object result = callback.invoke(context);
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
	 * @param <C> type of context to pass to the callback
	 * @return a value of property as color
	 */
	public static <C extends ChartContext> Object handleCallbackResultAsColor(C context, Object result, String defaultValue, boolean hasPattern) {
		// checks if context is consistent
		Checker.checkIfValid(context, "Context argument");
		// invokes common handler
		Object checkedResult = doHandleCallbackResultAsColor(context, result, defaultValue, hasPattern);
		// checks if is a gradient
		if (result instanceof Gradient && context instanceof AbstractDatasetContext) {
			// cast the context to the data set context
			// in order to get data and data set index
			AbstractDatasetContext datasetContext = (AbstractDatasetContext) context;
			// is gradient instance
			Gradient gradient = (Gradient) result;
			// then it must be translated in the a canvas gradient
			return DatasetCanvasObjectFactory.get().createGradient(context.getChart(), gradient, datasetContext.getDatasetIndex(), datasetContext.getDataIndex());
		}
		return checkedResult;
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
	private static Object doHandleCallbackResultAsColor(ChartContext context, Object result, String defaultValue, boolean hasPattern) {
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
			} else if (result instanceof Gradient || result instanceof CanvasGradientItem) {
				// is canvas gradient or Charba gradient instance
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

	/**
	 * Parses the result of a {@link TickCallback} or {@link PointLabelCallback} callbacks, checking if it's a string or a list of strings.
	 * 
	 * @param result the result of callback to check
	 * @param defaultValue default value to return if the result fo callback is not consistent.
	 * @return return a string or a list of strings (multiline) or <code>default</code> if result is not consistent
	 */
	public static Object parseCallbackResult(Object result, String defaultValue) {
		// checks if a single string
		if (result instanceof String) {
			// returns the string
			return result;
		} else if (result instanceof List<?>) {
			// casts to list
			List<?> list = (List<?>) result;
			// checks if list is consistent
			if (!list.isEmpty()) {
				// creates the result array
				final List<String> normalizedList = new LinkedList<>();
				// scans list
				for (Object textItem : list) {
					// adds the string
					// to normalized list
					normalizedList.add(textItem.toString());
				}
				// checks if there is more than
				// returns the arrays of string for text
				return normalizedList.size() == 1 ? normalizedList.get(0) : ArrayString.fromOrNull(normalizedList);
			}
		}
		// if here, result is not consistent
		// then returns default
		return defaultValue;
	}
}
