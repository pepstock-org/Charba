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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.AbstractChart;

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
public final class Pattern {
	// exception message
	private static final String IMG_NULL_MESSAGE = "Image instance is null!";

	// default canvas pattern
	static final CanvasPattern DEFAULT_CANVAS_PATTERN = null;

	/**
	 * Default of pattern, with <code>null</code> on canvas pattern.
	 */
	public static final Pattern DEFAULT_PATTERN = new Pattern(Pattern.DEFAULT_CANVAS_PATTERN);
	// canvas pattern
	private final CanvasPattern pattern;

	/**
	 * Creates the object using chart (to get canvas and context) to create the pattern and image to use in the pattern.<br>
	 * The repetition used is repeat.
	 * 
	 * @param chart chart instance
	 * @param image image to use as pattern
	 */
	public Pattern(AbstractChart<?, ?> chart, ImageResource image) {
		this(chart, image, Context2d.Repetition.REPEAT);
	}

	/**
	 * Creates the object using chart (to get canvas and context) to create the pattern and image to use in the pattern.<br>
	 * The repetition used is repeat.
	 * 
	 * @param chart chart instance
	 * @param image image to use as pattern
	 */
	public Pattern(AbstractChart<?, ?> chart, ImageElement image) {
		this(chart, image, Context2d.Repetition.REPEAT);
	}

	/**
	 * Creates the object using chart (to get canvas and context) to create the pattern, image to use in the pattern and
	 * repetition to apply to pattern.
	 * 
	 * @param chart chart instance
	 * @param image image to use as pattern
	 * @param repetition repetition value to apply to pattern
	 */
	public Pattern(AbstractChart<?, ?> chart, ImageResource image, Context2d.Repetition repetition) {
		// checks if image is not consistent
		if (image != null) {
			// reads image
			Image img = new Image(image);
			// creates pattern
			pattern = chart.getCanvas().getContext2d().createPattern(ImageElement.as(img.getElement()), repetition);
		} else {
			// if here, image is null
			// then exception
			throw new IllegalArgumentException(IMG_NULL_MESSAGE);
		}
	}

	/**
	 * Creates the object using chart (to get canvas and context) to create the pattern, image to use in the pattern and
	 * repetition to apply to pattern.
	 * 
	 * @param chart chart instance
	 * @param image image to use as pattern
	 * @param repetition repetition value to apply to pattern
	 */
	public Pattern(AbstractChart<?, ?> chart, ImageElement image, Context2d.Repetition repetition) {
		// checks if image is not consistent
		if (image != null) {
			// creates pattern
			pattern = chart.getCanvas().getContext2d().createPattern(image, repetition == null ? Context2d.Repetition.REPEAT : repetition);
		} else {
			// if here, image is null
			// then exception
			throw new IllegalArgumentException(IMG_NULL_MESSAGE);
		}
	}

	/**
	 * Creates the object using a canvas pattern. This is used internally of dataset.
	 * 
	 * @param pattern wrapped canvas pattern
	 */
	public Pattern(CanvasPattern pattern) {
		this.pattern = pattern;
	}

	/**
	 * Returns the canvas pattern.
	 * 
	 * @return the pattern
	 */
	public CanvasPattern getPattern() {
		return pattern;
	}

}
