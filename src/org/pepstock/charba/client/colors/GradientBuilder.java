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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.utils.Window;

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
	
	// exception message when a property is missing
	private static final String MISSING_COLORS = "The gradient does not have any stop color";
	// gradient instance to build
	private final Gradient gradient;

	/**
	 * Creates the builder, wrapping a gradient object.
	 * 
	 * @param gradient gradient instance to wrap
	 */
	private GradientBuilder(Gradient gradient) {
		this.gradient = gradient;
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
		return new GradientBuilder(new Gradient(type, orientation, scope));
	}

	/**
	 * Sets the start and stop color of gradient, when the gradient will have only 2 colors.
	 * 
	 * @param start starting color, with offset 0
	 * @param stop stopping color, with offset 1
	 * @return gradient builder instance
	 * @return gradient builder instance
	 */
	public GradientBuilder addColorsStartStop(IsColor start, IsColor stop) {
		gradient.addColorsStartStop(start, stop);
		return this;
	}

	/**
	 * Sets the start and stop color of gradient, when the gradient will have only 2 colors.
	 * 
	 * @param start starting color, with offset 0
	 * @param stop stopping color, with offset 1
	 * @return gradient builder instance
	 */
	public GradientBuilder addColorsStartStop(String start, String stop) {
		gradient.addColorsStartStop(start, stop);
		return this;
	}

	/**
	 * Adds a stopping color with its offset
	 * 
	 * @param offset offset of color
	 * @param color color instance
	 * @return gradient builder instance
	 */
	public GradientBuilder addColorStop(double offset, IsColor color) {
		gradient.addColorStop(offset, color);
		return this;
	}

	/**
	 * Adds a stopping color with its offset
	 * 
	 * @param offset offset of color
	 * @param color color instance
	 * @return gradient builder instance
	 */
	public GradientBuilder addColorStop(double offset, String color) {
		gradient.addColorStop(offset, color);
		return this;
	}

	/**
	 * Adds a stopping color with its offset
	 * 
	 * @param color color instance
	 * @return gradient builder instance
	 */
	public GradientBuilder addColorStop(GradientColor color) {
		gradient.addColorStop(color);
		return this;
	}

	/**
	 * Builds and returns a gradient instance.
	 * 
	 * @return a gradient instance, built by the builder.
	 */
	public Gradient build() {
		// checks if there is any color
		if (!gradient.hasColors()) {
			// no color, exception
			throw new IllegalArgumentException(MISSING_COLORS);
		}
		// sorts the colors by their offset
		gradient.sortColors();
		// generates id
		gradient.generateId();
		
		// FIXME
		Window.getConsole().log("gradient", gradient.toJSON());
		
		// returns the instance
		return gradient;
	}
	
	/**
	 * Creates a gradient, previously stored into a native java script object.
	 *
	 * @param nativeObject native java script object wrapped by gradient.
	 * @return a gradient instance, built by the builder.
	 */
	public static Gradient build(NativeObject nativeObject) {
		// creates the object with native one
		Gradient gradient = new Gradient(nativeObject);
		// checks if there is any color
		if (!gradient.hasColors()) {
			// no color, exception
			throw new IllegalArgumentException(MISSING_COLORS);
		}
		// if here, gradient is consistent
		return gradient;
	}

}
