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

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.NativeObject;
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
	private static final Map<String, Gradient> GRADIENT = new HashMap<>();
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
		if (type == null) {
			// then throws an exception
			throw new IllegalArgumentException("Gradient type argument is null");
		}
		// checks if orientation is consistent
		if (orientation == null) {
			// then throws an exception
			throw new IllegalArgumentException("Gradient orientation argument is null");
		}
		// checks if scope is consistent
		if (scope == null) {
			// then throws an exception
			throw new IllegalArgumentException("Gradient scope argument is null");
		}
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
	 * Builds and returns a gradient instance.
	 * 
	 * @return a gradient instance, built by the builder.
	 */
	public Gradient build() {
		// checks if there is any color
		if (colors.isEmpty()) {
			// no color, exception
			throw new IllegalArgumentException(Gradient.MISSING_COLORS);
		}
		// sorts the color in order to have the list from less to greater
		Collections.sort(colors, Gradient.COMPARATOR);
		// generates id
		String id = generateId();
		// checks if gradient is cached
		if (GRADIENT.containsKey(id)) {
			// returns the cached object
			return GRADIENT.get(id);
		}
		// gets gradient reference
		Gradient result = new Gradient(id, type, orientation, scope, colors);
		// stores the object into the cache
		GRADIENT.put(id, result);
		// returns the instance
		return result;
	}
	
	/**
	 * Creates a gradient, previously stored into a native java script object.
	 *
	 * @param nativeObject native java script object wrapped by gradient.
	 * @return a gradient instance, built by the builder.
	 */
	public static Gradient build(NativeObject nativeObject) {
		// checks if argument is consistent
		if (nativeObject != null) {
			// extracts the id from object
			String id = Id.getStringProperty(CanvasObject.Property.CHARBA_OBJECT_ID, nativeObject);
			if (id == null) {
				// without the id, is not consistent
				// exception!
				throw new IllegalArgumentException(Utilities.applyTemplate(CanvasObject.MISSING_PROPERTY, CanvasObject.Property.CHARBA_OBJECT_ID.value()));
			}
			// checks if gradient is cached
			if (GRADIENT.containsKey(id)) {
				// returns the cached object
				return GRADIENT.get(id);
			}
			// creates new gradient
			Gradient result = new Gradient(nativeObject);
			// stores the object into the cache
			GRADIENT.put(id, result);
			// returns the instance
			return result;
		} else {
			// if here, argument is null
			// then exception
			throw new IllegalArgumentException("Native object argument is null");
		}
	}
	
	/**
	 * Creates an unique id for the canvas object.<br>
	 * <br>
	 * <code>[type]-[orientation]-[scope]-[list of colors]</code>
	 * <br>
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