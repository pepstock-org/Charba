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
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;

/**
 * Builds tiles to use inside the pattern or also as images to apply wherever needed.<br>
 * It creates a canvas for all drawer and caches the tiles already created.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TilesBuilder {

	// static instance for singleton
	private static final TilesBuilder INSTANCE = new TilesBuilder();
	// cache of images to avoid to create the same image if already used
	private static final Map<String, ImageElement> IMAGES = new HashMap<>();
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
	public final TilesBuilderDefaults getDefaults() {
		return defaults;
	}

	/**
	 * Returns an image, using default values, shape is <code>square</code>, background color, shape color and size.
	 * 
	 * @return a tile as image
	 */
	public ImageElement createTile() {
		return createTile(defaults.getShape());
	}

	/**
	 * Returns an image, using the shape as argument and the other default values, background color, shape color and size.
	 * 
	 * @param shape shape to apply to image
	 * @return a tile as image
	 */
	public ImageElement createTile(Shape shape) {
		return createTile(shape, defaults.getBackgroundColorAsString());
	}

	/**
	 * Returns an image, using the shape and back ground color as arguments and the other default values, shape color and size.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @return a tile as image
	 */
	public ImageElement createTile(Shape shape, IsColor backgroundColor) {
		return createTile(shape, backgroundColor, defaults.getShapeColor());
	}

	/**
	 * Returns an image, using the shape and back ground color as arguments and the other default values, shape color and size.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @return a tile as image
	 */
	public ImageElement createTile(Shape shape, String backgroundColor) {
		return createTile(shape, backgroundColor, defaults.getShapeColorAsString());
	}

	/**
	 * Returns an image, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @param shapeColor shape color
	 * @return a tile as image
	 */
	public ImageElement createTile(Shape shape, String backgroundColor, String shapeColor) {
		return createTile(shape, backgroundColor, shapeColor, defaults.getSize());
	}

	/**
	 * Returns an image, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @param shapeColor shape color
	 * @return a tile as image
	 */
	public ImageElement createTile(Shape shape, IsColor backgroundColor, IsColor shapeColor) {
		return createTile(shape, backgroundColor, shapeColor, defaults.getSize());
	}

	/**
	 * Returns an image, using the shape, back ground color, shape color and size as arguments.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @param shapeColor shape color
	 * @param size size of image
	 * @return a tile as image
	 */
	public ImageElement createTile(Shape shape, IsColor backgroundColor, IsColor shapeColor, int size) {
		return createTile(shape, backgroundColor != null ? backgroundColor.toRGBA() : defaults.getBackgroundColorAsString(), shapeColor != null ? shapeColor.toRGBA() : defaults.getShapeColorAsString(), size);
	}

	/**
	 * Returns an image, using the shape, back ground color, shape color and size as arguments.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @param shapeColor shape color
	 * @param size size of image
	 * @return a tile as image
	 */
	public ImageElement createTile(Shape shape, String backgroundColor, String shapeColor, int size) {
		// checks consistency of all parameters
		// if not consistent, it applies the default value
		Shape shapeParam = shape != null ? shape : defaults.getShape();
		String backgroundColorParam = backgroundColor != null ? backgroundColor : TilesBuilderDefaults.DEFAULT_BACKGROUND_COLOR;
		String shapeColorParam = shapeColor != null ? shapeColor : TilesBuilderDefaults.DEFAULT_SHAPE_COLOR;
		// checks the minimum size of image
		int sizeParam = Math.max(size, TilesBuilderDefaults.DEFAULT_SIZE);
		// creates a unique key based on arguments
		// in order to store the image when created and
		// if all further requests for the same image, returns the cached one
		StringBuilder keyBuilder = new StringBuilder(shapeParam.name());
		keyBuilder.append(backgroundColorParam).append(shapeColorParam).append(sizeParam);
		String key = keyBuilder.toString().replaceAll("\\s+", "").toLowerCase(Locale.getDefault());
		// checks if the image is already created with those parameters
		if (IMAGES.containsKey(key)) {
			// if yes returns the cached one
			return IMAGES.get(key);
		}
		ImageElement image = shapeParam.getDrawer().createTile(canvas, backgroundColorParam, shapeColorParam, sizeParam);
		// stores it into cache
		IMAGES.put(key, image);
		return image;
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
	 * @param shape shape to apply to image
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape) {
		return new Pattern(createTile(shape));
	}

	/**
	 * Returns a CHARBA pattern, using the shape and back ground color as arguments and the other default values, shape color
	 * and size.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, IsColor backgroundColor) {
		return new Pattern(createTile(shape, backgroundColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape and back ground color as arguments and the other default values, shape color
	 * and size.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, String backgroundColor) {
		return new Pattern(createTile(shape, backgroundColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @param shapeColor shape color
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, String backgroundColor, String shapeColor) {
		return new Pattern(createTile(shape, backgroundColor, shapeColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @param shapeColor shape color
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, IsColor backgroundColor, IsColor shapeColor) {
		return new Pattern(createTile(shape, backgroundColor, shapeColor));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color, shape color and size as arguments.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @param shapeColor shape color
	 * @param size size of image
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, String backgroundColor, String shapeColor, int size) {
		return new Pattern(createTile(shape, backgroundColor, shapeColor, size));
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color, shape color and size as arguments.
	 * 
	 * @param shape shape to apply to image
	 * @param backgroundColor background color of image
	 * @param shapeColor shape color
	 * @param size size of image
	 * @return a CHARBA pattern
	 */
	public Pattern createPattern(Shape shape, IsColor backgroundColor, IsColor shapeColor, int size) {
		return new Pattern(createTile(shape, backgroundColor, shapeColor, size));
	}

}
