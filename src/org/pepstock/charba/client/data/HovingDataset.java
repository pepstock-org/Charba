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
package org.pepstock.charba.client.data;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The chart allows a number of properties to be specified for each data set.<br>
 * These are used to set display properties for a specific data set.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class HovingDataset extends HovingFlexDataset {

	/**
	 * Creates the data set using a default and chart type related to the data set.
	 * 
	 * @param type chart type related to the data set
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	HovingDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#setColors(org.pepstock.charba.client.commons.Key, java.lang.String[])
	 */
	@Override
	void setColors(Key key, String... colors) {
		// stores value
		setArrayValue(key, ArrayString.fromOrEmpty(colors));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#setColors(org.pepstock.charba.client.commons.Key, org.pepstock.charba.client.colors.IsColor[])
	 */
	@Override
	void setColors(Key key, IsColor... colors) {
		// stores value
		setArrayValue(key, ArrayString.fromOrEmpty(colors));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#getColors(org.pepstock.charba.client.commons.Key, java.lang.String)
	 */
	@Override
	ArrayString getColors(Key key, String defaultvalue) {
		return getArrayValue(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#setWidths(org.pepstock.charba.client.commons.Key, int[])
	 */
	@Override
	void setWidths(Key key, int... widths) {
		// stores value
		setArrayValue(key, ArrayInteger.fromOrEmpty(widths));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#getWidths(org.pepstock.charba.client.commons.Key, int)
	 */
	@Override
	ArrayInteger getWidths(Key key, int defaultvalue) {
		return getArrayValue(key);
	}

	/**
	 * Returns the border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border width callback, if set, otherwise <code>null</code>
	 */
	public WidthCallback<DatasetContext> getBorderWidthCallback() {
		return getInternalBorderWidthCallback();
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public void setBorderWidth(WidthCallback<DatasetContext> borderWidthCallback) {
		setInternalBorderWidth(borderWidthCallback);
	}

	/**
	 * Sets the border width callback.
	 * 
	 * @param borderWidthCallback the border width callback to set
	 */
	public void setBorderWidth(NativeCallback borderWidthCallback) {
		setInternalBorderWidth(borderWidthCallback);
	}

	/**
	 * Sets the fill color of the arcs when hovered.
	 * 
	 * @param colors the fill color of the arcs when hovered
	 */
	@Override
	public void setHoverBackgroundColor(IsColor... colors) {
		// resets callback
		setHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// call super
		super.setHoverBackgroundColor(colors);
	}

	/**
	 * Sets the fill color of the arcs when hovered as string.
	 * 
	 * @param colors the fill color of the arcs when hovered as string
	 */
	@Override
	public void setHoverBackgroundColor(String... colors) {
		// resets callback
		setHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// call super
		super.setHoverBackgroundColor(colors);
	}

	/**
	 * Sets the fill pattern of the arcs in the data set when hovered.
	 * 
	 * @param colors the fill pattern of the arcs in the data set when hovered.
	 */
	@Override
	public void setHoverBackgroundColor(Pattern... colors) {
		// resets callback
		setHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// call super
		super.setHoverBackgroundColor(colors);
	}

	/**
	 * Sets the fill gradient of the arcs in the data set when hovered.
	 * 
	 * @param colors the fill gradient of the arcs in the data set when hovered.
	 */
	@Override
	public void setHoverBackgroundColor(Gradient... colors) {
		// resets callback
		setHoverBackgroundColor((ColorCallback<DatasetContext>) null);
		// call super
		super.setHoverBackgroundColor(colors);
	}

	/**
	 * Returns the fill color of the arcs when hovered as string.<br>
	 * If property is missing or not a color, returns an empty list.
	 * 
	 * @return list of the fill color of the arcs when hovered as string.<br>
	 *         If property is missing or not a color, returns an empty list
	 */
	@Override
	public List<String> getHoverBackgroundColorAsString() {
		// checks if there is callback
		if (getHoverBackgroundColorCallback() != null) {
			// if here, the property is a callback
			// returns empty list
			return Collections.emptyList();
		}
		// calls super
		return super.getHoverBackgroundColorAsString();
	}

	/**
	 * Returns the fill patterns of the arcs in the data set when hovered.<br>
	 * If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the fill patterns of the arcs in the data set when hovered.<br>
	 *         If property is missing or not a pattern, returns an empty list
	 */
	@Override
	public List<Pattern> getHoverBackgroundColorAsPatterns() {
		// checks if there is callback
		if (getHoverBackgroundColorCallback() != null) {
			// if here, the property is a callback
			// returns empty list
			return Collections.emptyList();
		}
		// calls super
		return super.getHoverBackgroundColorAsPatterns();

	}

	/**
	 * Returns the fill gradients of the arcs in the data set when hovered.<br>
	 * If property is missing or not a gradient, returns an empty list.
	 * 
	 * @return list of the fill gradients of the arcs in the data set when hovered.<br>
	 *         If property is missing or not a gradient, returns an empty list.
	 */
	@Override
	public List<Gradient> getHoverBackgroundColorAsGradient() {
		// checks if there is callback
		if (getHoverBackgroundColorCallback() != null) {
			// if here, the property is a callback
			// returns empty list
			return Collections.emptyList();
		}
		// calls super
		return super.getHoverBackgroundColorAsGradient();
	}

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * 
	 * @param colors the stroke color of the arcs when hovered as string
	 */
	@Override
	public void setHoverBorderColor(IsColor... colors) {
		// resets callback
		setHoverBorderColor((ColorCallback<DatasetContext>) null);
		// call super
		super.setHoverBorderColor(colors);
	}

	/**
	 * Sets the stroke color of the arcs when hovered as string.
	 * 
	 * @param colors the stroke color of the arcs when hovered as string
	 */
	@Override
	public void setHoverBorderColor(String... colors) {
		// resets callback
		setHoverBorderColor((ColorCallback<DatasetContext>) null);
		// call super
		super.setHoverBorderColor(colors);
	}

	/**
	 * Sets the stroke gradient of the arcs in the data set when hovered as gradient.
	 * 
	 * @param colors the stroke gradient of the arcs in the data set when hovered as gradient.
	 */
	@Override
	public void setHoverBorderColor(Gradient... colors) {
		// resets callback
		setHoverBorderColor((ColorCallback<DatasetContext>) null);
		// call super
		super.setHoverBorderColor(colors);
	}

	/**
	 * Returns the stroke color of the arcs when hovered.
	 * 
	 * @return list of the stroke color of the arcs when hovered.
	 */
	@Override
	public List<String> getHoverBorderColorAsString() {
		// checks if there is callback
		if (getHoverBorderColorCallback() != null) {
			// if here, the property is a callback
			// returns empty list
			return Collections.emptyList();
		}
		// calls super
		return super.getHoverBorderColorAsString();
	}

	/**
	 * Returns the stroke gradients of the arcs in the data set when hovered.<br>
	 * If property is missing or not a pattern, returns an empty list.
	 * 
	 * @return list of the stroke gradients of the arcs in the data set when hovered.<br>
	 *         If property is missing or not a pattern, returns an empty list.
	 */
	@Override
	public List<Gradient> getHoverBorderColorAsGradient() {
		// checks if there is callback
		if (getHoverBorderColorCallback() != null) {
			// if here, the property is a callback
			// returns empty list
			return Collections.emptyList();
		}
		// calls super
		return super.getHoverBorderColorAsGradient();
	}

	/**
	 * Returns the stroke width of the arcs when hovered.
	 * 
	 * @return list of the stroke width of the arcs when hovered.
	 */
	@Override
	public List<Integer> getHoverBorderWidth() {
		// checks if the callback has not been set
		if (getHoverBorderWidthCallback() == null) {
			// call super
			return super.getHoverBorderWidth();
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the hover border width callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover border width callback, if set, otherwise <code>null</code>.
	 */
	public WidthCallback<DatasetContext> getHoverBorderWidthCallback() {
		return getInternalHoverBorderWidthCallback();
	}

	/**
	 * Sets the hover border width callback.
	 * 
	 * @param hoverBorderWidthCallback the hover border width callback to set
	 */
	public void setHoverBorderWidth(WidthCallback<DatasetContext> hoverBorderWidthCallback) {
		setInternalHoverBorderWidth(hoverBorderWidthCallback);
	}

}