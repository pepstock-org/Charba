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
package org.pepstock.charba.client.colors;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.utils.Utilities;

/**
 * The gradient builder is the entry point to create a canvas gradient.<br>
 * A gradient is an image consisting of a progressive transition between two or more colors.<br>
 * Could be <code>Linear</code> or <code>Radial</code>.<br>
 * Can be created using the size of <code>CANVAS</code> or <code>CHART</code> area.<br>
 * The orientation to have a progressive transition, is defined by an enumeration in order to creates the gradient using the right coordinates and dimension, based on existing
 * items (canvas and chart).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GradientBuilder {

	// cache for the gradients created in order to build only when needed
	// K = canvas object id, V = gradient instance
	private static final Map<String, Gradient> GRADIENTS = new HashMap<>();
	// gradient type instance
	private final GradientType type;
	// gradient orientation instance
	private final GradientOrientation orientation;
	// gradient scope instance
	private final GradientScope scope;
	// contains the gradient colors
	private final List<GradientColor> colors = new LinkedList<>();

	/**
	 * Creates a gradient by a type, an orientation and a scope.
	 * 
	 * @param type gradient type
	 * @param orientation orientation of gradient
	 * @param scope scope of gradient
	 */
	private GradientBuilder(GradientType type, GradientOrientation orientation, GradientScope scope) {
		this.type = type;
		this.orientation = orientation;
		this.scope = scope;
	}

	/**
	 * Creates a LINEAR gradient, with <code>topDown</code> orientation and <code>chart</code> scope.
	 * 
	 * @return gradient builder instance
	 */
	public static GradientBuilder create() {
		return create(GradientType.LINEAR);
	}

	/**
	 * Creates a gradient by a type, with <code>chart</code> scope.
	 * 
	 * @param type gradient type
	 * @return gradient builder instance
	 */
	public static GradientBuilder create(GradientType type) {
		return create(type, GradientOrientation.getDefaultByType(type));
	}

	/**
	 * Creates a gradient by a type and an orientation, with <code>chart</code> scope.
	 * 
	 * @param type gradient type
	 * @param orientation orientation of gradient
	 * @return gradient builder instance
	 */
	public static GradientBuilder create(GradientType type, GradientOrientation orientation) {
		return create(type, orientation, GradientScope.CHART);
	}

	/**
	 * Creates a gradient by a type and a scope.
	 * 
	 * @param type gradient type
	 * @param scope scope of gradient
	 * @return gradient builder instance
	 */
	public static GradientBuilder create(GradientType type, GradientScope scope) {
		return create(type, GradientOrientation.getDefaultByType(type), scope);
	}

	/**
	 * Creates a gradient by a type, an orientation and a scope.
	 * 
	 * @param type gradient type
	 * @param orientation orientation of gradient
	 * @param scope scope of gradient
	 * @return gradient builder instance
	 */
	public static GradientBuilder create(GradientType type, GradientOrientation orientation, GradientScope scope) {
		// checks if type is consistent
		Checker.checkIfValid(type, "Gradient type argument");
		// checks if orientation is consistent
		Checker.checkIfValid(orientation, "Gradient orientation argument");
		// checks if scope is consistent
		Checker.checkIfValid(scope, "Gradient scope argument");
		// creates the builder
		return new GradientBuilder(type, orientation, scope);
	}

	/**
	 * Sets the start and stop color of gradient, when the gradient will have only 2 colors.
	 * 
	 * @param start starting color, with offset 0
	 * @param stop stopping color, with offset 1
	 * @return gradient builder instance
	 */
	public GradientBuilder addColorsStartStop(IsColor start, IsColor stop) {
		return addColorsStartStop(IsColor.checkAndGetValue(start), IsColor.checkAndGetValue(stop));
	}

	/**
	 * Sets the start and stop color of gradient, when the gradient will have only 2 colors.
	 * 
	 * @param start starting color, with offset 0
	 * @param stop stopping color, with offset 1
	 * @return gradient builder instance
	 */
	public GradientBuilder addColorsStartStop(String start, String stop) {
		colors.clear();
		return addColorStop(GradientColor.DEFAULT_OFFSET_START, start).addColorStop(GradientColor.DEFAULT_OFFSET_STOP, stop);
	}

	/**
	 * Adds a stopping color with its offset
	 * 
	 * @param offset offset of color
	 * @param color color instance
	 * @return gradient builder instance
	 */
	public GradientBuilder addColorStop(double offset, IsColor color) {
		return addColorStop(offset, IsColor.checkAndGetValue(color));
	}

	/**
	 * Adds a stopping color with its offset
	 * 
	 * @param offset offset of color
	 * @param color color instance
	 * @return gradient builder instance
	 */
	public GradientBuilder addColorStop(double offset, String color) {
		return addColorStop(new GradientColor(offset, color));
	}

	/**
	 * Adds a stopping color with its offset
	 * 
	 * @param color color instance
	 * @return gradient builder instance
	 */
	public GradientBuilder addColorStop(GradientColor color) {
		// checks if argument is consistent
		if (color != null) {
			// if consistent, adds color
			colors.add(color);
		}
		return this;
	}

	/**
	 * Sets an array of colors to the gradient.
	 * 
	 * @param colors colors array to set
	 * @return gradient builder instance
	 */
	public GradientBuilder setColors(IsColor... colors) {
		// checks if argument is consistent
		if (colors != null && colors.length > 0) {
			// checks if array of colors previously loaded is consistent
			Checker.checkIfGreaterThan(colors.length, 2, "Colors list");
			// removes all previous colors
			this.colors.clear();
			// gets index of last calculated color
			int lastCalculatedColorIndex = colors.length - 1;
			// calculates the increment of offset, and then due to 0 and 1 are already assigned
			// the increment is 1 divided by size of list - 1
			double offsetIncrement = GradientColor.DEFAULT_OFFSET_STOP / lastCalculatedColorIndex;
			// amount of offset, starting from 0
			double offset = 0D;
			// scans colors from 1 to last calculated index
			for (int i = 0; i < lastCalculatedColorIndex; i++) {
				// color at index 0 is color at offset 0
				addColorStop(offset, colors[i]);
				// increments offset
				offset += offsetIncrement;
			}
			// adds last color with offset 1
			addColorStop(GradientColor.DEFAULT_OFFSET_STOP, colors[lastCalculatedColorIndex]);
		}
		return this;
	}

	/**
	 * Sets the list of colors.
	 * 
	 * @param colors colors list to set
	 * @return gradient builder instance
	 */
	public GradientBuilder setColors(List<IsColor> colors) {
		// checks if argument is consistent
		if (ArrayListHelper.isConsistent(colors)) {
			// invokes the set colors by array
			return setColors(ArrayUtil.toColors(colors));
		}
		return this;
	}

	/**
	 * Builds and returns a gradient instance.
	 * 
	 * @return a gradient instance, built by the builder.
	 */
	public Gradient build() {
		// checks if there is any color
		Checker.assertCheck(!colors.isEmpty(), Gradient.MISSING_COLORS);
		// sorts the color in order to have the list from less to greater
		Collections.sort(colors, Gradient.COMPARATOR);
		// generates id
		String id = generateId();
		// checks if gradient is cached
		if (GRADIENTS.containsKey(id)) {
			// returns the cached object
			return GRADIENTS.get(id);
		}
		// gets gradient reference
		Gradient result = new Gradient(id, type, orientation, scope, colors);
		// stores the object in the cache
		GRADIENTS.put(id, result);
		// returns the instance
		return result;
	}

	/**
	 * Creates a gradient, previously stored in the a native java script object.
	 *
	 * @param nativeObject native java script object wrapped by gradient.
	 * @return a gradient instance, built by the builder.
	 */
	public static Gradient build(NativeObject nativeObject) {
		// checks if argument is consistent
		Checker.checkIfValid(nativeObject, "Native object argument");
		// extracts the id from object
		String id = JsHelper.get().getStringProperty(CanvasObject.Property.CHARBA_OBJECT_ID, nativeObject);
		// checks if id is consistent
		Checker.assertCheck(id != null, Utilities.applyTemplate(CanvasObject.MISSING_PROPERTY, CanvasObject.Property.CHARBA_OBJECT_ID.value()));
		// checks if gradient is cached
		if (GRADIENTS.containsKey(id)) {
			// returns the cached object
			return GRADIENTS.get(id);
		}
		// creates new gradient
		Gradient result = new Gradient(nativeObject);
		// stores the object in the cache
		GRADIENTS.put(id, result);
		// returns the instance
		return result;
	}

	/**
	 * Retrieves a cached pattern by a {@link CanvasGradientItem} instance.<br>
	 * If the pattern doesn't exist, returns <code>null</code>.
	 * 
	 * @param canvasGradient the canvas gradient to use for searching
	 * @return a cached pattern by a {@link CanvasGradientItem} instance.<br>
	 *         If the pattern doesn't exist, returns <code>null</code>
	 */
	public static Gradient retrieve(CanvasGradientItem canvasGradient) {
		// checks if argument is consistent
		if (canvasGradient != null) {
			// casts to native object
			// extracts the id from object
			String id = Id.get((NativeObject) canvasGradient.as());
			// checks if gradient is cached and id is consistent
			if (id != null && GRADIENTS.containsKey(id)) {
				// returns the cached object
				return GRADIENTS.get(id);
			}
		}
		// if here, the argument is not consistent or
		// the id is not found
		// then returns null
		return null;
	}

	/**
	 * Creates an unique id for the canvas object.<br>
	 * <br>
	 * <code>[type]-[orientation]-[scope]-[list of colors]</code> <br>
	 * 
	 * @return an unique id for the canvas object
	 */
	private String generateId() {
		// creates a builder
		StringBuilder sb = new StringBuilder();
		sb.append(type).append(Constants.MINUS);
		sb.append(orientation).append(Constants.MINUS);
		sb.append(scope).append(Constants.MINUS);
		sb.append(colors.toString());
		// returns it
		return sb.toString();
	}

}
