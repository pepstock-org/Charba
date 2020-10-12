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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.enums.Repetition;
import org.pepstock.charba.client.utils.Window;

/**
 * The pattern builder is the entry point to create a canvas pattern.<br>
 * Entity to apply a canvas pattern into a dataset as background.<br>
 * See <a href="https://www.toptal.com/designers/subtlepatterns/">here</a> some interesting pattern images.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class PatternBuilder {
	
	// pattern instance to build
	private final Pattern pattern;
	
	/**
	 * Creates the builder, wrapping a pattern object.
	 * 
	 * @param pattern pattern instance to wrap
	 */
	private PatternBuilder(Pattern pattern) {
		this.pattern = pattern;
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
		return new PatternBuilder(new Pattern(image, repetition));
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
		return new PatternBuilder(new Pattern(canvasPattern, width, height));
	}
	
	/**
	 * Builds and returns a pattern instance.
	 * 
	 * @return a pattern instance, built by the builder.
	 */
	public Pattern build() {
		// generates id
		pattern.generateId();
		
		// FIXME
		Window.getConsole().log("pattern", pattern.toJSON());
		
		// returns the instance
		return pattern;
	}
	
	/**
	 * Creates a pattern, previously stored into a native java script object
	 *
	 * @param nativeObject native java script object wrapped by pattern.
	 * @return a pattern instance, built by the builder.
	 */
	public static Pattern build(NativeObject nativeObject) {
		// creates the object with native one
		return new Pattern(nativeObject);
	}

}
