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
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.enums.Repetition;

/**
 * Entity to apply a canvas pattern into a dataset as background.<br>
 * See <a href="https://www.toptal.com/designers/subtlepatterns/">here</a> some interesting pattern images.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Pattern extends CanvasObject {

	// default instance of image.
	private static final Img DEFAULT_IMAGE = null;
	// default instance of canvas pattern.
	private static final CanvasPatternItem DEFAULT_CANVAS_PATTERN = null;

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
	 * Creates the object using an image to use in the pattern and repetition to apply to pattern.
	 * 
	 * @param id unique id, as string, of the object
	 * @param image image to use as pattern
	 * @param repetition repetition value to apply to pattern
	 * @param width width of image
	 * @param height height of image
	 */
	Pattern(String id, Img image, Repetition repetition, int width, int height) {
		super(id);
		// checks if image is not consistent
		if (image != null) {
			// creates pattern
			setValue(Property.CHARBA_PATTERN_IMG, image);
			setValue(Property.CHARBA_PATTERN_WIDTH, width);
			setValue(Property.CHARBA_PATTERN_HEIGHT, height);
			setValue(Property.CHARBA_PATTERN_REPETITION, repetition == null ? Repetition.REPEAT : repetition);
		} else {
			// if here, image is null
			// then exception
			throw new IllegalArgumentException("Image argument is null");
		}
	}

	/**
	 * Creates the object using an already created canvas pattern.
	 * 
	 * @param id unique id, as string, of the object
	 * @param canvasPattern canvas pattern instance
	 * @param width width of image applied to canvasPattern
	 * @param height height of image applied to canvasPattern
	 */
	Pattern(String id, CanvasPatternItem canvasPattern, int width, int height) {
		super(id);
		// checks if canvas pattern is not consistent
		if (canvasPattern != null) {
			// creates pattern
			setValue(Property.CHARBA_PATTERN_CANVAS, canvasPattern);
			// sets repetition even is not used
			// to normalizes the properties
			setValue(Property.CHARBA_PATTERN_REPETITION, Repetition.REPEAT);
			// stores size checks the max value
			setValue(Property.CHARBA_PATTERN_WIDTH, Math.max(width, TilesFactoryDefaults.DEFAULT_SIZE));
			setValue(Property.CHARBA_PATTERN_HEIGHT, Math.max(height, TilesFactoryDefaults.DEFAULT_SIZE));
			// stores the id into pattern
			store(canvasPattern);
		} else {
			// if here, image is null
			// then exception
			throw new IllegalArgumentException("Canvas pattern argument is null");
		}
	}

	/**
	 * Creates the object using a native object where pattern info are stored.
	 * 
	 * @param nativeObject native object where pattern info are stored
	 */
	Pattern(NativeObject nativeObject) {
		super(nativeObject);
		// checks if the pattern is built by a image or canvas pattern
		if (has(Property.CHARBA_PATTERN_IMG)) {
			// checks if image is consistent
			checkNativeObject(Property.CHARBA_PATTERN_IMG, ObjectType.OBJECT);
		} else {
			// checks if pattern is consistent
			checkNativeObject(Property.CHARBA_PATTERN_CANVAS, ObjectType.OBJECT);
		}
		// checks if repetition is consistent
		checkNativeObject(Property.CHARBA_PATTERN_REPETITION, ObjectType.STRING);
		// checks if width is consistent
		checkNativeObject(Property.CHARBA_PATTERN_WIDTH, ObjectType.NUMBER);
		// checks if height is consistent
		checkNativeObject(Property.CHARBA_PATTERN_HEIGHT, ObjectType.NUMBER);
	}

	/**
	 * Returns the image used into pattern if exists.
	 * 
	 * @return the image used into pattern if exists, otherwise <code>null</code>.
	 */
	public Img getImage() {
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
	public Repetition getRepetition() {
		return getValue(Property.CHARBA_PATTERN_REPETITION, Repetition.values(), Repetition.REPEAT);
	}

	/**
	 * Returns the canvas pattern if exists.
	 * 
	 * @return the canvas pattern if exists, otherwise <code>null</code>.
	 */
	public CanvasPatternItem getCanvasPattern() {
		return getValue(Property.CHARBA_PATTERN_CANVAS, DEFAULT_CANVAS_PATTERN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
