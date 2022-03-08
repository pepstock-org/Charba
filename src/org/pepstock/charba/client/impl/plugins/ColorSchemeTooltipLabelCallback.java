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
package org.pepstock.charba.client.impl.plugins;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.BarChart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.TooltipLabelCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.data.BarBorderWidth;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.TooltipLabelColor;
import org.pepstock.charba.client.items.TooltipLabelPointStyle;

/**
 * Custom tooltip labels callback.<br>
 * This callback solved the issue that the tooltip failed about the {@link BarBorderWidth} options set to a {@link BarChart}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ColorSchemeTooltipLabelCallback implements TooltipLabelCallback {

	// static map to get all tooltiè labels callbacks defined for the chart
	// which will be wrapped by this callback
	private static final Map<String, TooltipLabelCallback> CALLBACKS = new HashMap<>();

	/**
	 * Returns the delegated callback previously set by user.
	 * 
	 * @param chart chart instance
	 * @return the delegated callback previously set by user
	 */
	TooltipLabelCallback getDelegatedCallback(IsChart chart) {
		return CALLBACKS.get(chart.getId());
	}

	/**
	 * Sets the delegated callback previously set by user.
	 * 
	 * @param chart chart instance
	 * @param delegatedCallback the delegated callback previously set by user
	 */
	void setDelegatedCallback(IsChart chart, TooltipLabelCallback delegatedCallback) {
		CALLBACKS.put(chart.getId(), delegatedCallback);
	}

	/**
	 * Removes the delegated callback previously set by user.
	 * 
	 * @param chart chart instance
	 */
	void removeDelegatedCallback(IsChart chart) {
		CALLBACKS.remove(chart.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback#onBeforeLabel(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.TooltipItem)
	 */
	@Override
	public List<String> onBeforeLabel(IsChart chart, TooltipItem item) {
		// gets user callback if there is
		TooltipLabelCallback delegatedCallback = getCallback(chart);
		// if the tooltip callback set by user is consistent
		if (delegatedCallback != null) {
			// invokes the tooltip callback
			return delegatedCallback.onBeforeLabel(chart, item);
		}
		// if here, uses the default
		return TooltipLabelCallback.super.onBeforeLabel(chart, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback#onLabel(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.TooltipItem)
	 */
	@Override
	public List<String> onLabel(IsChart chart, TooltipItem item) {
		// gets user callback if there is
		TooltipLabelCallback delegatedCallback = getCallback(chart);
		// if the tooltip callback set by user is consistent
		if (delegatedCallback != null) {
			// invokes the tooltip callback
			return delegatedCallback.onLabel(chart, item);
		}
		// if here, uses the default
		return TooltipLabelCallback.super.onLabel(chart, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback#onLabelPointStyle(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.TooltipItem)
	 */
	@Override
	public TooltipLabelPointStyle onLabelPointStyle(IsChart chart, TooltipItem item) {
		// gets user callback if there is
		TooltipLabelCallback delegatedCallback = getCallback(chart);
		// if the tooltip callback set by user is consistent
		if (delegatedCallback != null) {
			// invokes the tooltip callback
			return delegatedCallback.onLabelPointStyle(chart, item);
		}
		// if here, uses the default
		return TooltipLabelCallback.super.onLabelPointStyle(chart, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback#onLabelTextColor(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.TooltipItem)
	 */
	@Override
	public IsColor onLabelTextColor(IsChart chart, TooltipItem item) {
		// gets user callback if there is
		TooltipLabelCallback delegatedCallback = getCallback(chart);
		// if the tooltip callback set by user is consistent
		if (delegatedCallback != null) {
			// invokes the tooltip callback
			return delegatedCallback.onLabelTextColor(chart, item);
		}
		// if here, uses the default
		return TooltipLabelCallback.super.onLabelTextColor(chart, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback#onAfterLabel(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.TooltipItem)
	 */
	@Override
	public List<String> onAfterLabel(IsChart chart, TooltipItem item) {
		// gets user callback if there is
		TooltipLabelCallback delegatedCallback = getCallback(chart);
		// if the tooltip callback set by user is consistent
		if (delegatedCallback != null) {
			// invokes the tooltip callback
			return delegatedCallback.onAfterLabel(chart, item);
		}
		// if here, uses the default
		return TooltipLabelCallback.super.onAfterLabel(chart, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback#onLabelColor(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.items.TooltipItem)
	 */
	@Override
	public TooltipLabelColor onLabelColor(IsChart chart, TooltipItem item) {
		TooltipLabelColor result = TooltipLabelCallback.super.onLabelColor(chart, item);
		// gets user callback if there is
		TooltipLabelCallback delegatedCallback = getCallback(chart);
		// if the tooltip callback set by user is consistent
		if (delegatedCallback != null) {
			// invokes the tooltip callback
			return delegatedCallback.onLabelColor(chart, item);
		}
		// if here, uses the default
		return result;
	}

	/**
	 * Searches for the user callback for a specific chart instance.<br>
	 * If not found, returns <code>null</code>.
	 * 
	 * @param chart chart instance to check
	 * @return the user callback for a specific chart instance.<br>
	 *         If not found, returns <code>null</code>.
	 */
	private TooltipLabelCallback getCallback(IsChart chart) {
		// checks if the chart is consistent
		// and the labels are consistent
		if (IsChart.isValid(chart)) {
			// checks if there is a delegated tooltip callback
			return CALLBACKS.get(chart.getId());
		}
		// if here, not present
		return null;
	}
}
