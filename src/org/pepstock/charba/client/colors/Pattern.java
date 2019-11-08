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

import org.pepstock.charba.client.colors.tiles.TilesFactoryDefaults;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.utils.Utilities;

import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * Entity to apply a canvas pattern into a dataset as background.<br>
 * See <a href="https://www.toptal.com/designers/subtlepatterns/">here</a> some interesting pattern images.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Pattern extends CanvasObject {

	// exception message
	private static final String IMG_OR_CANVAS_PATTERN_NULL_MESSAGE = "Image or canvas pattern instance is not consitent or null!";
	// default instance of image.
	private static final ImageElement DEFAULT_IMAGE = null;
	// default instance of canvas pattern.
	private static final CanvasPattern DEFAULT_CANVAS_PATTERN = null;

	/**
	 * Name of properties of native object. ALL INTERNAL USE ONLY
	 */
	private enum Property implements Key
	{
		CHARBA_PATTERN_IMG("_charbaPatternImg"),
		CHARBA_PATTERN_REPETITION("_charbaPatternRepetition"),
		CHARBA_PATTERN_CANVAS("_charbaPatternCanvas"),
		CHARBA_PATTERN_WIDTH("_charbaPatternWidth"),
		CHARBA_PATTERN_HEIGHT("_charbaPatternHeight");

		// name value of property
		private final String value;

		/**
		 * Creates a property with the value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}

	/**
	 * Creates the object using an image to use in the pattern.<br>
	 * The repetition used is repeat.
	 * 
	 * @param image image to use as pattern
	 */
	public Pattern(ImageResource image) {
		this(image, Context2d.Repetition.REPEAT);
	}

	/**
	 * Creates the object using an image to use in the pattern.<br>
	 * The repetition used is repeat.
	 * 
	 * @param image image to use as pattern
	 */
	public Pattern(Image image) {
		this(image, Context2d.Repetition.REPEAT);
	}

	/**
	 * Creates the object using an image to use in the pattern.<br>
	 * The repetition used is repeat.
	 * 
	 * @param image image to use as pattern
	 */
	public Pattern(ImageElement image) {
		this(image, Context2d.Repetition.REPEAT);
	}

	/**
	 * Creates the object using an image to use in the pattern and repetition to apply to pattern.
	 * 
	 * @param image image to use as pattern
	 * @param repetition repetition value to apply to pattern
	 */
	public Pattern(ImageResource image, Context2d.Repetition repetition) {
		this(Utilities.toImageElement(image), repetition);
	}

	/**
	 * Creates the object using an image to use in the pattern and repetition to apply to pattern.
	 * 
	 * @param image image to use as pattern
	 * @param repetition repetition value to apply to pattern
	 */
	public Pattern(Image image, Context2d.Repetition repetition) {
		this(Utilities.toImageElement(image), repetition);
	}

	/**
	 * Creates the object using an image to use in the pattern and repetition to apply to pattern.
	 * 
	 * @param image image to use as pattern
	 * @param repetition repetition value to apply to pattern
	 */
	public Pattern(ImageElement image, Context2d.Repetition repetition) {
		// checks if image is not consistent
		if (image != null) {
			// creates pattern
			setValue(Property.CHARBA_PATTERN_IMG, image);
			setValue(Property.CHARBA_PATTERN_WIDTH, image.getWidth());
			setValue(Property.CHARBA_PATTERN_HEIGHT, image.getHeight());
			setValue(Property.CHARBA_PATTERN_REPETITION, repetition == null ? Context2d.Repetition.REPEAT.name() : repetition.name());
		} else {
			// if here, image is null
			// then exception
			throw new IllegalArgumentException(IMG_OR_CANVAS_PATTERN_NULL_MESSAGE);
		}
	}

	/**
	 * Creates the object using an already created canvas pattern.<br>
	 * The dimension of canvas pattern image will be the default {@link TilesFactoryDefaults#DEFAULT_SIZE}. This is mainly used
	 * by tiles.
	 * 
	 * @param canvasPattern canvas pattern instance
	 */
	public Pattern(CanvasPattern canvasPattern) {
		this(canvasPattern, TilesFactoryDefaults.DEFAULT_SIZE, TilesFactoryDefaults.DEFAULT_SIZE);
	}

	/**
	 * Creates the object using an already created canvas pattern.<br>
	 * The dimension of canvas pattern image is unique then the image of pattern is a square. This is mainly used by tiles.
	 * 
	 * @param canvasPattern canvas pattern instance
	 * @param squareSize size of image applied to canvasPattern to be a square
	 */
	public Pattern(CanvasPattern canvasPattern, int squareSize) {
		this(canvasPattern, squareSize, squareSize);
	}

	/**
	 * Creates the object using an already created canvas pattern.<br>
	 * This is mainly used by tiles.
	 * 
	 * @param canvasPattern canvas pattern instance
	 * @param width width of image applied to canvasPattern
	 * @param height height of image applied to canvasPattern
	 */
	public Pattern(CanvasPattern canvasPattern, int width, int height) {
		// checks if canvas pattern is not consistent
		if (canvasPattern != null) {
			// creates pattern
			setValue(Property.CHARBA_PATTERN_CANVAS, canvasPattern);
			// sets repetition even is not used
			// to normalizes the properties
			setValue(Property.CHARBA_PATTERN_REPETITION, Context2d.Repetition.REPEAT.name());
			// stores size checks the max value
			setValue(Property.CHARBA_PATTERN_WIDTH, Math.max(width, TilesFactoryDefaults.DEFAULT_SIZE));
			setValue(Property.CHARBA_PATTERN_HEIGHT, Math.max(height, TilesFactoryDefaults.DEFAULT_SIZE));
		} else {
			// if here, image is null
			// then exception
			throw new IllegalArgumentException(IMG_OR_CANVAS_PATTERN_NULL_MESSAGE);
		}
	}

	/**
	 * Creates the object using a native object where pattern info are stored.
	 * 
	 * @param nativeObject native object where pattern info are stored
	 */
	Pattern(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the image used into pattern if exists.
	 * 
	 * @return the image used into pattern if exists, otherwise <code>null</code>.
	 */
	public ImageElement getImage() {
		return getValue(Property.CHARBA_PATTERN_IMG, DEFAULT_IMAGE);
	}

	/**
	 * Returns the image width used into pattern.
	 * 
	 * @return the image width used into pattern.
	 */
	public int getWidth() {
		return getValue(Property.CHARBA_PATTERN_WIDTH, TilesFactoryDefaults.DEFAULT_SIZE);
	}

	/**
	 * Returns the image height used into pattern.
	 * 
	 * @return the image height used into pattern.
	 */
	public int getHeight() {
		return getValue(Property.CHARBA_PATTERN_HEIGHT, TilesFactoryDefaults.DEFAULT_SIZE);
	}

	/**
	 * Returns the repetition to use into pattern.
	 * 
	 * @return the repetition to use into pattern
	 */
	public Context2d.Repetition getRepetition() {
		String value = getValue(Property.CHARBA_PATTERN_REPETITION, Context2d.Repetition.REPEAT.name());
		return Context2d.Repetition.valueOf(value);
	}

	/**
	 * Returns the canvas pattern if exists.
	 * 
	 * @return the canvas pattern if exists, otherwise <code>null</code>.
	 */
	public CanvasPattern getCanvasPattern() {
		return getValue(Property.CHARBA_PATTERN_CANVAS, DEFAULT_CANVAS_PATTERN);
	}

	/**
	 * Inner class to create pattern by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	public static final class PatternFactory implements NativeObjectContainerFactory<Pattern> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public Pattern create(NativeObject nativeObject) {
			return new Pattern(nativeObject);
		}
	}

}
