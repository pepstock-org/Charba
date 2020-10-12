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
import org.pepstock.charba.client.colors.PatternBuilder;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.impl.plugins.HtmlLegendItem;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Builds tiles creating a CHARBA pattern or canvas pattern passing all needed arguments and leveraging on tiles factory defaults.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TilesFactory {

	// static instance for singleton
	private static final TilesFactory INSTANCE = new TilesFactory();
	// cache of canvas patterns to avoid to create the same canvas pattern if already used
	private static final Map<String, CanvasPatternItem> CANVAS_PATTERNS = new HashMap<>();
	// message to show when the browser can't support canvas
	private static final String CANVAS_NOT_SUPPORTED_MESSAGE = "Ops... Canvas element is not supported...";
	// cache of canvas patterns to avoid to create the same canvas pattern if already used
	private static final Map<String, String> HTML_LEGEND_ITEMS = new HashMap<>();
	// string format to trim blanks
	private static final String REGEXP_TRIM_SPACES_PATTERN = "\\s+";
	// gets if Canvas is supported
	private final boolean isCanvasSupported = DOMBuilder.get().isCanvasSupported();
	// canvas where draws the pattern
	private final Canvas canvas;
	// default configuration
	private final TilesFactoryDefaults defaults;

	/**
	 * To avoid any instantiation
	 */
	private TilesFactory() {
		// checks if canvas is supported
		if (isCanvasSupported) {
			// creates a canvas
			canvas = DOMBuilder.get().createCanvasElement();
			// defaults configuration
			defaults = new TilesFactoryDefaults();
		} else {
			// canvas not supported
			// throws exception
			throw new IllegalArgumentException(CANVAS_NOT_SUPPORTED_MESSAGE);
		}
	}

	/**
	 * Returns the default values of tiles configuration.
	 * 
	 * @return the default values of tiles configuration
	 */
	public static TilesFactoryDefaults getDefaults() {
		return INSTANCE.defaults;
	}

	// -----------------------------------------
	// --- TILE - CanvasPatternItem
	// -----------------------------------------

	/**
	 * Returns a canvas pattern, using default values, shape is <code>square</code>, background color, shape color and size.
	 * 
	 * @return a tile as canvas pattern
	 */
	public static CanvasPatternItem createTile() {
		return createTile(INSTANCE.defaults.getShape());
	}

	/**
	 * Returns a canvas pattern, using the shape as argument and the other default values, background color, shape color and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @return a tile as canvas pattern
	 */
	public static CanvasPatternItem createTile(IsShape shape) {
		return createTile(shape, INSTANCE.defaults.getBackgroundColorAsString());
	}

	/**
	 * Returns a canvas pattern, using the shape and back ground color as arguments and the other default values, shape color and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a tile as canvas pattern
	 */
	public static CanvasPatternItem createTile(IsShape shape, IsColor backgroundColor) {
		return createTile(shape, backgroundColor, INSTANCE.defaults.getShapeColor());
	}

	/**
	 * Returns a canvas pattern, using the shape and back ground color as arguments and the other default values, shape color and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a tile as canvas pattern
	 */
	public static CanvasPatternItem createTile(IsShape shape, String backgroundColor) {
		return createTile(shape, backgroundColor, INSTANCE.defaults.getShapeColorAsString());
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @return a tile as canvas pattern
	 */
	public static CanvasPatternItem createTile(IsShape shape, String backgroundColor, String shapeColor) {
		return createTile(shape, backgroundColor, shapeColor, INSTANCE.defaults.getSize());
	}

	/**
	 * Returns a canvas pattern, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @return a tile as canvas pattern
	 */
	public static CanvasPatternItem createTile(IsShape shape, IsColor backgroundColor, IsColor shapeColor) {
		return createTile(shape, backgroundColor, shapeColor, INSTANCE.defaults.getSize());
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
	public static CanvasPatternItem createTile(IsShape shape, IsColor backgroundColor, IsColor shapeColor, int size) {
		return createTile(shape, IsColor.isConsistent(backgroundColor) ? backgroundColor.toRGBA() : INSTANCE.defaults.getBackgroundColorAsString(), IsColor.isConsistent(shapeColor) ? shapeColor.toRGBA() : INSTANCE.defaults.getShapeColorAsString(), size);
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
	public static CanvasPatternItem createTile(IsShape shape, String backgroundColor, String shapeColor, int size) {
		return buildTile(shape, backgroundColor, shapeColor, size);
	}

	// -----------------------------------------
	// --- TILE - Charba Pattern
	// -----------------------------------------

	/**
	 * Returns a CHARBA pattern, using default values, shape is <code>square</code>, background color, shape color and size.
	 * 
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern() {
		return PatternBuilder.create(createTile()).build();
	}

	/**
	 * Returns a CHARBA pattern, using the shape as argument and the other default values, background color, shape color and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(IsShape shape) {
		return PatternBuilder.create(createTile(shape)).build();
	}

	/**
	 * Returns a CHARBA pattern, using the shape and back ground color as arguments and the other default values, shape color and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(IsShape shape, IsColor backgroundColor) {
		return PatternBuilder.create(createTile(shape, backgroundColor)).build();
	}

	/**
	 * Returns a CHARBA pattern, using the shape and back ground color as arguments and the other default values, shape color and size.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(IsShape shape, String backgroundColor) {
		return PatternBuilder.create(createTile(shape, backgroundColor)).build();
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(IsShape shape, String backgroundColor, String shapeColor) {
		return PatternBuilder.create(createTile(shape, backgroundColor, shapeColor)).build();
	}

	/**
	 * Returns a CHARBA pattern, using the shape, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(IsShape shape, IsColor backgroundColor, IsColor shapeColor) {
		return PatternBuilder.create(createTile(shape, backgroundColor, shapeColor)).build();
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
	public static Pattern createPattern(IsShape shape, String backgroundColor, String shapeColor, int size) {
		return PatternBuilder.create(createTile(shape, backgroundColor, shapeColor, size), size).build();
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
	public static Pattern createPattern(IsShape shape, IsColor backgroundColor, IsColor shapeColor, int size) {
		return PatternBuilder.create(createTile(shape, backgroundColor, shapeColor, size), size).build();
	}

	// -----------------------------------------
	// --- TILE - Charba Point Style
	// -----------------------------------------

	/**
	 * Returns a CHARBA pattern, using the point style as argument and the other default values, background color, shape color and size.
	 * 
	 * @param style point style to apply to canvas pattern
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(PointStyle style) {
		return PatternBuilder.create(createTile(PointStyleShape.get(style))).build();
	}

	/**
	 * Returns a CHARBA pattern, using the point style and back ground color as arguments and the other default values, shape color and size.
	 * 
	 * @param style point style to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(PointStyle style, IsColor backgroundColor) {
		return PatternBuilder.create(createTile(PointStyleShape.get(style), backgroundColor)).build();
	}

	/**
	 * Returns a CHARBA pattern, using the point style and back ground color as arguments and the other default values, shape color and size.
	 * 
	 * @param style point style to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(PointStyle style, String backgroundColor) {
		return PatternBuilder.create(createTile(PointStyleShape.get(style), backgroundColor)).build();
	}

	/**
	 * Returns a CHARBA pattern, using the point style, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param style point style to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(PointStyle style, String backgroundColor, String shapeColor) {
		return PatternBuilder.create(createTile(PointStyleShape.get(style), backgroundColor, shapeColor)).build();
	}

	/**
	 * Returns a CHARBA pattern, using the point style, back ground color and shape color as arguments and the size as default value.
	 * 
	 * @param style point style to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(PointStyle style, IsColor backgroundColor, IsColor shapeColor) {
		return PatternBuilder.create(createTile(PointStyleShape.get(style), backgroundColor, shapeColor)).build();
	}

	/**
	 * Returns a CHARBA pattern, using the point style, back ground color, shape color and size as arguments.
	 * 
	 * @param style point style to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @param size size of canvas pattern
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(PointStyle style, String backgroundColor, String shapeColor, int size) {
		return PatternBuilder.create(createTile(PointStyleShape.get(style), backgroundColor, shapeColor, size), size).build();
	}

	/**
	 * Returns a CHARBA pattern, using the point style, back ground color, shape color and size as arguments.
	 * 
	 * @param style point style to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @param size size of canvas pattern
	 * @return a CHARBA pattern
	 */
	public static Pattern createPattern(PointStyle style, IsColor backgroundColor, IsColor shapeColor, int size) {
		return PatternBuilder.create(createTile(PointStyleShape.get(style), backgroundColor, shapeColor, size), size).build();
	}

	// -----------------------------------------
	// --- TILE - creation
	// -----------------------------------------

	/**
	 * Returns a canvas pattern, using the shape, back ground color, shape color and size as arguments.
	 * 
	 * @param shape shape to apply to canvas pattern
	 * @param backgroundColor background color of canvas pattern
	 * @param shapeColor shape color
	 * @param size size of canvas pattern
	 * @return a tile as canvas pattern
	 */
	private static CanvasPatternItem buildTile(IsShape shape, String backgroundColor, String shapeColor, int size) {
		// checks consistency of all parameters
		// if not consistent, it applies the default value
		IsShape shapeParam = IsShape.isValid(shape) ? shape : INSTANCE.defaults.getShape();
		// checks consistency of shape
		// this control is done after previous
		// assignment to check the methods of the interface
		// and not only the null instance
		IsShape.checkIfValid(shapeParam);
		String backgroundColorParam = backgroundColor != null ? backgroundColor : TilesFactoryDefaults.DEFAULT_BACKGROUND_COLOR_AS_STRING;
		String shapeColorParam = shapeColor != null ? shapeColor : TilesFactoryDefaults.DEFAULT_SHAPE_COLOR_AS_STRING;
		// checks the minimum size of canvas pattern
		int sizeParam = Math.max(size, TilesFactoryDefaults.MINIMUM_SIZE);
		// checks the minimum size of radius and rotation
		// creates a unique key based on arguments
		// in order to store the canvas pattern when created and
		// if all further requests for the same canvas pattern, returns the cached one
		StringBuilder keyBuilder = new StringBuilder(shape.getKeyPrefix());
		keyBuilder.append(backgroundColor).append(shapeColor).append(size);
		String key = keyBuilder.toString().replace(REGEXP_TRIM_SPACES_PATTERN, Constants.EMPTY_STRING).toLowerCase(Locale.getDefault());
		// checks if the canvas pattern is already created with those parameters
		if (CANVAS_PATTERNS.containsKey(key)) {
			// if yes returns the cached one
			return CANVAS_PATTERNS.get(key);
		}
		// creates a canvas pattern
		CanvasPatternItem pattern = shapeParam.getDrawer().createTile(INSTANCE.canvas, backgroundColorParam, shapeColorParam, sizeParam);
		// stores it into cache
		CANVAS_PATTERNS.put(key, pattern);
		return pattern;
	}

	// -----------------------------------------
	// --- TILE - Legend Item for POINT STYLE
	// -----------------------------------------

	/**
	 * Returns a point style picture in base64 PNG format using the legend item as source.<br>
	 * This is invoked ONLY from {@link HtmlLegend} plugin.
	 * 
	 * @param htmlLegendItem the legend item instance to create the tile
	 * @return a point style picture in base64 PNG format
	 */
	public static String createHtmlLegendItem(HtmlLegendItem htmlLegendItem) {
		// checks if html legend item is consistent
		// and the point style is not an image
		if (htmlLegendItem != null && htmlLegendItem.getLegendItem() != null && !htmlLegendItem.getLegendItem().isPointStyleAsImage()) {
			// gets unique key
			String key = htmlLegendItem.toUniqueKey();
			// checks if item is cached
			if (key != null && HTML_LEGEND_ITEMS.containsKey(key)) {
				return HTML_LEGEND_ITEMS.get(key);
			}
			// gets point style
			PointStyle pointStyle = htmlLegendItem.getLegendItem().getPointStyle();
			// gets the shape related to point style
			PointStyleShape shape = PointStyleShape.get(pointStyle);
			// checks if it is an instance of point style drawer
			if (shape.getDrawer() instanceof AbstractPointStyleShapeDrawer) {
				// casts to point style drawer
				AbstractPointStyleShapeDrawer pointStyleShape = (AbstractPointStyleShapeDrawer) shape.getDrawer();
				// invokes the drawing of the point style
				String result = pointStyleShape.drawTile(INSTANCE.canvas, htmlLegendItem);
				// stores it into cache
				HTML_LEGEND_ITEMS.put(key, result);
				return result;
			}
		}
		// if here, arguments are not consistent
		return UndefinedValues.STRING;
	}

	/**
	 * Clears all cached instance of point style by chart instance, wrapper into a legend item. This is invoked ONLY from {@link HtmlLegend} plugin.
	 * 
	 * @param htmlLegendItem the legend item instance to create the tile
	 */
	public static void clearHtmlLegendItems(HtmlLegendItem htmlLegendItem) {
		// checks if html legend item is consistent
		if (htmlLegendItem != null) {
			// scans items by key
			// remove by key
			HTML_LEGEND_ITEMS.keySet().removeIf(key -> key.startsWith(htmlLegendItem.getChart().getId()));
		}
	}

}
