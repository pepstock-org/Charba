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
package org.pepstock.charba.client.colors.tiles;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;

/**
 * Builds tiles to use inside the pattern, as canvas pattern.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TilesBuilder {

	// static instance for singleton
	private static final TilesBuilder INSTANCE = new TilesBuilder();
	// cache of canvas patterns to avoid to create the same canvas pattern if already used
	private static final Map<String, CanvasPattern> CANVAS_PATTERNS = new HashMap<>();
	// message to show when the browser can't support canvas
	private static final String CANVAS_NOT_SUPPORTED_MESSAGE = "Ops... Canvas element is not supported...";
	// gets if Canvas is supported
	private final boolean isCanvasSupported = Canvas.isSupported();
	// canvas where draws the pattern
	private final CanvasElement canvas;
	// default configuration
	private final TilesBuilderDefaults defaults;

	/**
	 * To avoid any instantiation
	 */
	private TilesBuilder() {
		// checks if canvas is supported
		if (isCanvasSupported) {
			// creates a canvas
			canvas = Document.get().createCanvasElement();
			// defaults configuration
			defaults = new TilesBuilderDefaults();
		} else {
			// canvas not supported
			// throws exception
			throw new IllegalArgumentException(CANVAS_NOT_SUPPORTED_MESSAGE);
		}
	}

	/**
	 * Singleton method to get static instance.
	 * 
	 * @return tiles builder instance
	 */
	public static TilesBuilder get() {
		return INSTANCE;
	}

	/**
	 * Returns the default values of tiles configuration.
	 * 
	 * @return the default values of tiles configuration
	 */
	public TilesBuilderDefaults getDefaults() {
		return defaults;
	}

	/**
	 * Returns a canvas pattern, using default values, shape is <code>square</code>, background color, shape color and size.
	 * 
	 * @return a tile as canvas pattern
	 */
	public CanvasPattern createTile() {
		return createTile(defaults.getShape());
	}

	/**
	 * Returns a canvas pattern, using the shape as argument and the other default values, background color, shape color and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @return a tile as canvas pattern
	 */
	public CanvasPattern createTile(IsShape shape) {
		return createTile(shape, defaults.getBackgroundColorAsString());
	}

	/**
	 * Returns a canvas pattern, using the shape and back ground color as arguments and the other default values, shape color and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a tile as canvas pattern
	 */
	public CanvasPattern createTile(IsShape shape, IsColor backgroundColor) {
		return createTile(shape, backgroundColor, defaults.getShapeColor());
	}

	/**
	 * Returns a canvas pattern, using the shape and back ground color as arguments and the other default values, shape color and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a tile as canvas pattern
	 */
	public CanvasPattern createTile(IsShape shape, String backgroundColor) {
		return createTile(shape, backgroundColor, defaults.getShapeColorAsString());
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @return a tile as canvas pattern
	 */
	public CanvasPattern createTile(IsShape shape, String backgroundColor, String shapeColor) {
		return createTile(shape, backgroundColor, shapeColor, defaults.getSize());
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @return a tile as canvas pattern
	 */
	public CanvasPattern createTile(IsShape shape, IsColor backgroundColor, IsColor shapeColor) {
		return createTile(shape, backgroundColor, shapeColor, defaults.getSize());
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color, shape color and size as arguments.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @param size size of canvas pattern
	 * @return a tile as canvas pattern
	 */
	public CanvasPattern createTile(IsShape shape, IsColor backgroundColor, IsColor shapeColor, int size) {
		return createTile(shape, backgroundColor != null ? backgroundColor.toRGBA() : defaults.getBackgroundColorAsString(), shapeColor != null ? shapeColor.toRGBA() : defaults.getShapeColorAsString(), size);
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color, shape color and size as arguments.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @param size size of canvas pattern
	 * @return a tile as canvas pattern
	 */
	public CanvasPattern createTile(IsShape shape, String backgroundColor, String shapeColor, int size) {
		// checks consistency of all parameters
		// if not consistent, it applies the default value
		IsShape shapeParam = shape != null ? shape : defaults.getShape();
		String backgroundColorParam = backgroundColor != null ? backgroundColor : TilesBuilderDefaults.DEFAULT_BACKGROUND_COLOR_AS_STRING;
		String shapeColorParam = shapeColor != null ? shapeColor : TilesBuilderDefaults.DEFAULT_SHAPE_COLOR_AS_STRING;
		// checks the minimum size of canvas pattern
		int sizeParam = Math.max(size, TilesBuilderDefaults.MINIMUM_SIZE);
		// creates a unique key based on arguments
		// in order to store the canvas pattern when created and
		// if all further requests for the same canvas pattern, returns the cached one
		StringBuilder keyBuilder = new StringBuilder(shapeParam.name());
		keyBuilder.append(backgroundColorParam).append(shapeColorParam).append(sizeParam);
		String key = keyBuilder.toString().replaceAll("\\s+", "").toLowerCase(Locale.getDefault());
		// checks if the canvas pattern is already created with those parameters
		if (CANVAS_PATTERNS.containsKey(key)) {
			// if yes returns the cached one
			return CANVAS_PATTERNS.get(key);
		}
		// creates a canvas
		CanvasPattern pattern = shapeParam.getDrawer().createTile(canvas, backgroundColorParam, shapeColorParam, sizeParam);
		// stores it into cache
		CANVAS_PATTERNS.put(key, pattern);
		return pattern;
	}

	/**
	 * Returns a CHARBA pattern, using default values, shape is <code>square</code>, background color, shape color and size.
	 * 
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern() {
		return new Pattern(createTile());
	}

	/**
	 * Returns a CHARBA pattern, using the shape as argument and the other default values, background color, shape color and
	 * size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(IsShape shape) {
		return new Pattern(createTile(shape));
	}

	/**
	 * Returns a CHARBA pattern, using the shape and back ground color as arguments and the other default values, shape color
	 * and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(IsShape shape, IsColor backgroundColor) {
		return new Pattern(createTile(shape, backgroundColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape and back ground color as arguments and the other default values, shape color
	 * and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(IsShape shape, String backgroundColor) {
		return new Pattern(createTile(shape, backgroundColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(IsShape shape, String backgroundColor, String shapeColor) {
		return new Pattern(createTile(shape, backgroundColor, shapeColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(IsShape shape, IsColor backgroundColor, IsColor shapeColor) {
		return new Pattern(createTile(shape, backgroundColor, shapeColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color, shape color and size as arguments.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @param size size of canvas pattern
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(IsShape shape, String backgroundColor, String shapeColor, int size) {
		return new Pattern(createTile(shape, backgroundColor, shapeColor, size));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color, shape color and size as arguments.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @param size size of canvas pattern
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(IsShape shape, IsColor backgroundColor, IsColor shapeColor, int size) {
		return new Pattern(createTile(shape, backgroundColor, shapeColor, size));
	}

}
