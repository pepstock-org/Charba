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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.colors.tiles.TilesFactoryDefaults;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.enums.Repetition;
import org.pepstock.charba.client.utils.Utilities;

/**
 * The pattern builder is the entry point to create a canvas pattern.<br>
 * Entity to apply a canvas pattern in the a data set as background.<br>
 * See <a href="https://www.toptal.com/designers/subtlepatterns/">here</a> some interesting pattern images.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PatternBuilder {

	// cache for the patterns created in order to build only when needed
	// K = canvas object id, V = pattern instance
	private static final Map<String, Pattern> PATTERNS = new HashMap<>();
	// image instance
	private final Img image;
	// canvas pattern instance
	private final CanvasPatternItem canvasPatternItem;
	// -----------
	// COMMON
	// -----------
	// repetition in the pattern
	private final Repetition repetition;
	// width of the pattern
	private final int width;
	// height of the pattern
	private final int height;

	/**
	 * Creates the object using an image to use in the pattern and repetition to apply to pattern.
	 * 
	 * @param image image to use as pattern
	 * @param repetition repetition value to apply to pattern
	 * @param width width of image
	 * @param height height of image
	 */
	private PatternBuilder(Img image, Repetition repetition, int width, int height) {
		this.image = image;
		this.repetition = repetition;
		this.width = width;
		this.height = height;
		// sets canvas pattern to null
		this.canvasPatternItem = null;
	}

	/**
	 * Creates the object using an already created canvas pattern.<br>
	 * This is mainly used by tiles.
	 * 
	 * @param canvasPatternItem canvas pattern instance
	 * @param width width of image applied to canvasPattern
	 * @param height height of image applied to canvasPattern
	 */
	private PatternBuilder(CanvasPatternItem canvasPatternItem, int width, int height) {
		this.canvasPatternItem = canvasPatternItem;
		this.repetition = Repetition.REPEAT;
		this.width = width;
		this.height = height;
		// sets image to null
		this.image = null;
	}

	/**
	 * Creates the object using an image to use in the pattern.<br>
	 * The repetition used is repeat.
	 * 
	 * @param image image to use as pattern
	 * @return pattern builder instance
	 */
	public static PatternBuilder create(Img image) {
		return create(image, Repetition.REPEAT);
	}

	/**
	 * Creates the object using an image to use in the pattern and repetition to apply to pattern.
	 * 
	 * @param image image to use as pattern
	 * @param repetition repetition value to apply to pattern
	 * @return pattern builder instance
	 */
	public static PatternBuilder create(Img image, Repetition repetition) {
		// checks if image is not consistent
		if (image != null) {
			// creates a pattern builder
			return new PatternBuilder(image, repetition == null ? Repetition.REPEAT : repetition, image.getWidth(), image.getHeight());
		} else {
			// if here, image is null
			// then exception
			throw new IllegalArgumentException("Image argument is null");
		}
	}

	/**
	 * Creates the object using an already created canvas pattern.<br>
	 * The dimension of canvas pattern image will be the default {@link TilesFactoryDefaults#DEFAULT_SIZE}. This is mainly used by tiles.
	 * 
	 * @param canvasPattern canvas pattern instance
	 * @return pattern builder instance
	 */
	public static PatternBuilder create(CanvasPatternItem canvasPattern) {
		return create(canvasPattern, TilesFactoryDefaults.DEFAULT_SIZE, TilesFactoryDefaults.DEFAULT_SIZE);
	}

	/**
	 * Creates the object using an already created canvas pattern.<br>
	 * The dimension of canvas pattern image is unique then the image of pattern is a square. This is mainly used by tiles.
	 * 
	 * @param canvasPattern canvas pattern instance
	 * @param squareSize size of image applied to canvasPattern to be a square
	 * @return pattern builder instance
	 */
	public static PatternBuilder create(CanvasPatternItem canvasPattern, int squareSize) {
		return create(canvasPattern, squareSize, squareSize);
	}

	/**
	 * Creates the object using an already created canvas pattern.<br>
	 * This is mainly used by tiles.
	 * 
	 * @param canvasPattern canvas pattern instance
	 * @param width width of image applied to canvasPattern
	 * @param height height of image applied to canvasPattern
	 * @return pattern builder instance
	 */
	public static PatternBuilder create(CanvasPatternItem canvasPattern, int width, int height) {
		// checks if canvas pattern is not consistent
		if (canvasPattern != null) {
			// creates a pattern builder
			return new PatternBuilder(canvasPattern, width, height);
		} else {
			// if here, canvas pattern is null
			// then exception
			throw new IllegalArgumentException("Canvas pattern argument is null");
		}

	}

	/**
	 * Builds and returns a pattern instance.
	 * 
	 * @return a pattern instance, built by the builder.
	 */
	public Pattern build() {
		// generates id
		String id = generateId();
		// checks if pattern is cached
		if (PATTERNS.containsKey(id)) {
			// returns the cached object
			return PATTERNS.get(id);
		}
		// gets pattern reference
		Pattern result;
		// creates new pattern
		if (image != null) {
			// if here, is creating the pattern by an image
			result = new Pattern(id, image, repetition, width, height);
		} else {
			// if here, is creating the pattern by a canvas pattern
			result = new Pattern(id, canvasPatternItem, width, height);
		}
		// stores the object in the the cache
		PATTERNS.put(id, result);
		// returns the instance
		return result;
	}

	/**
	 * Creates a pattern, previously stored in the a native java script object
	 *
	 * @param nativeObject native java script object wrapped by pattern.
	 * @return a pattern instance, built by the builder.
	 */
	public static Pattern build(NativeObject nativeObject) {
		// checks if argument is consistent
		if (nativeObject != null) {
			// extracts the id from object
			String id = Id.getStringProperty(CanvasObject.Property.CHARBA_OBJECT_ID, nativeObject);
			if (id == null) {
				// without the id, is not consistent
				// exception!
				throw new IllegalArgumentException(Utilities.applyTemplate(CanvasObject.MISSING_PROPERTY, CanvasObject.Property.CHARBA_OBJECT_ID.value()));
			}
			// checks if pattern is cached
			if (PATTERNS.containsKey(id)) {
				// returns the cached object
				return PATTERNS.get(id);
			}
			// creates new pattern
			Pattern result = new Pattern(nativeObject);
			// stores the object in the the cache
			PATTERNS.put(id, result);
			// returns the instance
			return result;
		} else {
			// if here, argument is null
			// then exception
			throw new IllegalArgumentException("Native object argument is null");
		}
	}

	/**
	 * Retrieves a cached pattern by a {@link CanvasPatternItem} instance.<br>
	 * If the pattern doesn't exist, returns <code>null</code>.
	 * 
	 * @param canvasPattern the canvas pattern to use for searching
	 * @return a cached pattern by a {@link CanvasPatternItem} instance.<br>
	 *         If the pattern doesn't exist, returns <code>null</code>
	 */
	public static Pattern retrieve(CanvasPatternItem canvasPattern) {
		// checks if argument is consistent
		if (canvasPattern != null) {
			// casts to native object
			// extracts the id from object
			String id = Id.get((NativeObject) canvasPattern.as());
			// checks if pattern is cached and id is consistent
			if (id != null && PATTERNS.containsKey(id)) {
				// returns the cached object
				return PATTERNS.get(id);
			}
		}
		// if here, the argument is not consistent or
		// the id is not found
		// then returns null
		return null;
	}

	/**
	 * Creates an unique id for the canvas object.<br>
	 * For pattern created by a {@link Img}, the format is the following: <br>
	 * <br>
	 * <code>Img-[hashcode]-[repetition]-[width]-[height]</code> <br>
	 * For pattern created by a {@link CanvasPatternItem}, the format is the following: <br>
	 * <br>
	 * <code>CanvasPatternItem-[hashcode]-[repetition]-[width]-[height]</code> <br>
	 * 
	 * @return an unique id for the canvas object
	 */
	private String generateId() {
		// creates a builder
		StringBuilder sb = new StringBuilder();
		// checks if is creating the pattern by an image
		if (image != null) {
			// if here, is creating the pattern by an image
			// uses the image class name and image hash code
			sb.append(Img.class.getSimpleName()).append(Constants.MINUS);
			sb.append(image.hashCode()).append(Constants.MINUS);
		} else {
			// if here, is creating the pattern by a canvas pattern
			// uses the pattern class name and pattern hash code
			sb.append(CanvasPatternItem.class.getSimpleName()).append(Constants.MINUS);
			sb.append(canvasPatternItem.hashCode()).append(Constants.MINUS);
		}
		// rest of the id is repetition, width and height.
		sb.append(repetition).append(Constants.MINUS);
		sb.append(width).append(Constants.MINUS);
		sb.append(height);
		// returns it
		return sb.toString();
	}

}
