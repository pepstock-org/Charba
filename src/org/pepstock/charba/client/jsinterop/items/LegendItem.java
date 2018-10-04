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
package org.pepstock.charba.client.jsinterop.items;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.commons.Array;
import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * JavaScript object which contains the legends hit box size.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public class LegendItem extends NativeObject {

	@JsProperty(name = "datasetIndex")
	native int getNativeDatasetIndex();

	@JsProperty(name = "index")
	native int getNativeIndex();

	@JsProperty(name = "text")
	native String getNativeText();

	@JsProperty(name = "fillStyle")
	native String getNativeFillStyle();

	@JsProperty(name = "hidden")
	native boolean isNativeHidden();
	
	@JsProperty(name = "hidden")
	native void setNativeHidden(boolean hidden);

	@JsProperty(name = "lineCap")
	native String getNativeLineCap();

	@JsProperty(name = "lineDash")
	native ArrayInteger getNativeLineDash();

	@JsProperty(name = "lineDashOffset")
	native int getNativeLineDashOffset();

	@JsProperty(name = "lineJoin")
	native String getNativeLineJoin();

	@JsProperty(name = "lineWidth")
	native Object getNativeLineWidth();

	@JsProperty(name = "strokeStyle")
	native Object getNativeStrokeStyle();

	@JsProperty(name = "pointStyle")
	native Object getNativePointStyle();

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	@JsOverlay
	public final int getDatasetIndex() {
		return Checker.check(getNativeDatasetIndex(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the dataset index of the chart (for POLAR and PIE charts)
	 * 
	 * @return the dataset index of the chart (for POLAR and PIE charts). Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	@JsOverlay
	public final int getIndex() {
		return Checker.check(getNativeIndex(), UndefinedValues.INTEGER);
	}
	
	/**
	 * Returns the label that will be displayed
	 * 
	 * @return the label that will be displayed. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public final String getText() {
		return Checker.check(getNativeText(), UndefinedValues.STRING);
	}

	/**
	 * Returns the fill style of the legend box
	 * 
	 * @return the fill style of the legend box
	 */
	@JsOverlay
	public final IsColor getFillStyle() {
		return ColorBuilder.parse(Checker.check(getNativeFillStyle(), Defaults.getGlobal().getDefaultColorAsString()));
	}

	/**
	 * Returns true if this item represents a hidden dataset. Label will be rendered with a strike-through effect
	 * 
	 * @return <code>true</code> if this item represents a hidden dataset. Label will be rendered with a strike-through effect.<br>
	 * Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	@JsOverlay
	public final boolean isHidden() {
		return Checker.check(isNativeHidden(), UndefinedValues.BOOLEAN);
	}

	/**
	 * Sets if the dataset must be hidden.
	 * 
	 * @param hidden <code>true</code> if the dataset must be hidden, otherwise <code>false</code>.
	 */
	@JsOverlay
	public final void setHidden(boolean hidden) {
		setNativeHidden(hidden);
	}

	/**
	 * Returns how the end points of every box border are drawn. There are three possible values for this property and those
	 * are: butt, round and square.
	 * 
	 * @return how the end points of every box border are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	@JsOverlay
	public final CapStyle getLineCap() {
		return Enumer.deserialize(getNativeLineCap(), CapStyle.class, CapStyle.butt);
	}

	/**
	 * Returns the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths
	 * of lines and gaps which describe the pattern.
	 * 
	 * @return the box border dash pattern used when stroking lines, using an array of values which specify alternating lengths
	 *         of lines and gaps which describe the pattern.
	 */
	@JsOverlay
	public final List<Integer> getLineDash() {
		return ArrayListHelper.unmodifiableList(getNativeLineDash());
	}

	/**
	 * Returns the box border dash pattern offset or "phase".
	 * 
	 * @return the box border dash pattern offset or "phase". Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getLineDashOffset() {
		return Checker.check(getNativeLineDashOffset(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns how two connecting segments (of box border) with non-zero lengths in a shape are joined together (degenerate
	 * segments with zero lengths, whose specified endpoints and control points are exactly at the same position, are
	 * skipped).<br>
	 * There are three possible values for this property: round, bevel and miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	@JsOverlay
	public final JoinStyle getLineJoin() {
		return Enumer.deserialize(getNativeLineJoin(), JoinStyle.class, JoinStyle.miter);
	}

	/**
	 * Returns the width of line in pixels.
	 * 
	 * @return the width of line in pixels.
	 */
	@JsOverlay
	public final List<Integer> getLineWidth() {
		// loads stored data
		Object values = getNativeLineWidth();
		if (Array.isArray(values)) {
			return ArrayListHelper.unmodifiableList((ArrayInteger)values);
		} else {
			return ArrayListHelper.unmodifiableList(ArrayInteger.of(Checker.check(values, UndefinedValues.INTEGER)));
		}
	}

	/**
	 * Returns the stroke style of the legend box
	 * 
	 * @return the stroke style of the legend box
	 */
	@JsOverlay
	public final List<IsColor> getStrokeStyle() {
		// creates result
		List<IsColor> result = new LinkedList<>();
		// loads stored data
		Object values = getNativeStrokeStyle();
		if (Array.isArray(values)) {
			ArrayString array = (ArrayString)values;
			// scans all value
			for (int i=0 ; i<array.length(); i++) {
				// creates and adds color
				result.add(ColorBuilder.parse(array.get(i)));
			}
		} else {
			result.add(ColorBuilder.parse(Checker.check(values, Defaults.getGlobal().getDefaultColorAsString())));
		}
		return Collections.unmodifiableList(result);
	}

	/**
	 * Returns the style of the legend box (only used if usePointStyle is true)
	 * 
	 * @return the style of the legend box
	 * @see org.pepstock.charba.client.enums.PointStyle
	 */
	@JsOverlay
	public final List<PointStyle> getPointStyle() {
		// loads stored data
		Object values = getNativePointStyle();
		if (Array.isArray(values)) {
			;
			return ArrayListHelper.unmodifiableList(PointStyle.class, (ArrayString)values);
		} else {
			return ArrayListHelper.unmodifiableList(PointStyle.class, ArrayString.of(Checker.check(values, Defaults.getGlobal().getElements().getPoint().getPointStyle().name())));
		}
	}
	
}