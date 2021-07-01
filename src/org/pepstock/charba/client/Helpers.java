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
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.IsArea;
import org.pepstock.charba.client.resources.ResourcesType;

/**
 * Singleton object to use the helpers utility of CHART.JS.<br>
 * It maps the java script object <code>chart.helpers</code>.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Helpers {

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

}
