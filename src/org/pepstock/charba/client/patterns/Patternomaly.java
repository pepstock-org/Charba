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
package org.pepstock.charba.client.patterns;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.resources.Extensions;

import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * Patternomaly java script library implementation.<br>
 * This injects the Patternomaly java script, invokes it and uses a cache to improve the performance and do not create a pattern
 * every time if already created.<br>
 * Rather than relying solely on color to display information patterns provide a better way for viewers to differentiate
 * information.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Patternomaly {
	
	// static instance for singleton
	private static final Patternomaly INSTANCE = new Patternomaly();

	/**
	 * Default background color, {@value DEFAULT_BACKGROUND_COLOR}
	 */
	public static final String DEFAULT_BACKGROUND_COLOR = "rgba(100, 100, 100, 0.7)";
	/**
	 * Default pattern color, {@value DEFAULT_PATTERN_COLOR}
	 */
	public static final String DEFAULT_PATTERN_COLOR = "rgba(255, 255, 255, 0.8)";
	/**
	 * Default pattern size, 20
	 */
	public static final int DEFAULT_SIZE = 20;
	
	// minimum size that can be requested
	private static final int MINIMUM_SIZE = 10;
	// cache of canvas pattern to avoid to create the same pattern if already used
	private static final Map<String, CanvasPattern> CANVAS_PATTERNS = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private Patternomaly() {
		// Inject Patternomaly if not already loaded
		Injector.ensureInjected(Extensions.INSTANCE.patternomaly());
	}

	/**
	 * Singleton method to get static instance.
	 * 
	 * @return patternomaly instance
	 */
	public static Patternomaly get() {
		return INSTANCE;
	}

	/**
	 * Returns a canvas pattern, using default values.<br>
	 * Share is <code>square</code>, background color {@value DEFAULT_BACKGROUND_COLOR}, pattern color
	 * {@value DEFAULT_PATTERN_COLOR} and size 20.
	 * 
	 * @return a canvas pattern
	 */
	public CanvasPattern draw() {
		return draw(Shape.square);
	}

	/**
	 * Returns a canvas pattern, using the shape as argument and the other default values.<br>
	 * Background color {@value DEFAULT_BACKGROUND_COLOR}, pattern color
	 * {@value DEFAULT_PATTERN_COLOR} and size 20.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @return a canvas pattern
	 */
	public CanvasPattern draw(Shape shape) {
		return draw(shape, DEFAULT_BACKGROUND_COLOR);
	}

	/**
	 * Returns a canvas pattern, using the shape and back ground color as arguments and the other default values.<br>
	 * Pattern color {@value DEFAULT_PATTERN_COLOR} and size 20.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a canvas pattern
	 */
	public CanvasPattern draw(Shape shape, IsColor backgroundColor) {
		return draw(shape, backgroundColor, null);
	}

	/**
	 * Returns a canvas pattern, using the shape and back ground color as arguments and the other default values.<br>
	 * Pattern color {@value DEFAULT_PATTERN_COLOR} and size 20.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a canvas pattern
	 */
	public CanvasPattern draw(Shape shape, String backgroundColor) {
		return draw(shape, backgroundColor, null);
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color and pattern color as arguments and the other default
	 * values.<br>
	 * Size 20.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param patternColor pattern color 
	 * @return a canvas pattern
	 */
	public CanvasPattern draw(Shape shape, String backgroundColor, String patternColor) {
		return draw(shape, backgroundColor, patternColor, DEFAULT_SIZE);
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color and pattern color as arguments and the other default
	 * values.<br>
	 * Size 20.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param patternColor pattern color 
	 * @return a canvas pattern
	 */
	public CanvasPattern draw(Shape shape, IsColor backgroundColor, IsColor patternColor) {
		return draw(shape, backgroundColor, patternColor, DEFAULT_SIZE);
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color, pattern color and size as arguments.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param patternColor pattern color 
	 * @param size size of canvas pattern
	 * @return a canvas pattern
	 */
	public CanvasPattern draw(Shape shape, IsColor backgroundColor, IsColor patternColor, int size) {
		return draw(shape, backgroundColor != null ? backgroundColor.toRGBA() : DEFAULT_BACKGROUND_COLOR, patternColor != null ? patternColor.toRGBA() : DEFAULT_PATTERN_COLOR, size);
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color, pattern color and size as arguments.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param patternColor pattern color 
	 * @param size size of canvas pattern
	 * @return a canvas pattern
	 */
	public CanvasPattern draw(Shape shape, String backgroundColor, String patternColor, int size) {
		// checks consistency of all parameters
		// if not consistent, it applies the default value
		String shapeParam = shape != null ? shape.getValue() : Shape.square.getValue();
		String backgroundColorParam = backgroundColor != null ? backgroundColor : DEFAULT_BACKGROUND_COLOR;
		String patternColorParam = patternColor != null ? patternColor : DEFAULT_PATTERN_COLOR;
		// checks the minimum size of canvas pattern
		int sizeParam = Math.max(size, MINIMUM_SIZE);
		// creates a unique key based on arguments
		// in order to store the canvas pattern when created and 
		// if all further requests for the same canvas pattern, returns the cached one
		StringBuilder keyBuilder = new StringBuilder(shapeParam);
		keyBuilder.append(backgroundColorParam).append(patternColorParam).append(sizeParam);
		String key = keyBuilder.toString().replaceAll("\\s+", "").toLowerCase(Locale.getDefault());
		// checks if the canvas pattern is already created with those parameters
		if (CANVAS_PATTERNS.containsKey(key)) {
			// if yes returns the cached one
			return CANVAS_PATTERNS.get(key);
		}
		// invokes Patternomaly to create the canvas pattern
		CanvasPattern pattern = NativePatternomaly.draw(shapeParam, backgroundColorParam, patternColorParam, sizeParam);
		// stores it into cache
		CANVAS_PATTERNS.put(key, pattern);
		return pattern;
	}

	/**
	 * Returns a CHARBA pattern, using default values.<br>
	 * Share is <code>square</code>, background color {@value DEFAULT_BACKGROUND_COLOR}, pattern color
	 * {@value DEFAULT_PATTERN_COLOR} and size 20.
	 * 
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern() {
		return new Pattern(draw());
	}

	/**
	 * Returns a CHARBA pattern, using the shape as argument and the other default values.<br>
	 * Background color {@value DEFAULT_BACKGROUND_COLOR}, pattern color
	 * {@value DEFAULT_PATTERN_COLOR} and size 20.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape) {
		return new Pattern(draw(shape));
	}

	/**
	 * Returns a CHARBA pattern, using the shape and back ground color as arguments and the other default values.<br>
	 * Pattern color {@value DEFAULT_PATTERN_COLOR} and size 20.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, IsColor backgroundColor) {
		return new Pattern(draw(shape, backgroundColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape and back ground color as arguments and the other default values.<br>
	 * Pattern color {@value DEFAULT_PATTERN_COLOR} and size 20.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, String backgroundColor) {
		return new Pattern(draw(shape, backgroundColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color and pattern color as arguments and the other default
	 * values.<br>
	 * Size 20.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param patternColor pattern color 
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, String backgroundColor, String patternColor) {
		return new Pattern(draw(shape, backgroundColor, patternColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color and pattern color as arguments and the other default
	 * values.<br>
	 * Size 20.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param patternColor pattern color 
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, IsColor backgroundColor, IsColor patternColor) {
		return new Pattern(draw(shape, backgroundColor, patternColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color, pattern color and size as arguments.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param patternColor pattern color 
	 * @param size size of canvas pattern
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, String backgroundColor, String patternColor, int size) {
		return new Pattern(draw(shape, backgroundColor, patternColor, size));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color, pattern color and size as arguments.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param patternColor pattern color 
	 * @param size size of canvas pattern
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, IsColor backgroundColor, IsColor patternColor, int size) {
		return new Pattern(draw(shape, backgroundColor, patternColor, size));
	}

}
