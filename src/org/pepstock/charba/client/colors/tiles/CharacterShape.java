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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.elements.TextMetricsItem;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.utils.Utilities;

/**
 * This is a shape which can draw a character on the tile.<br>
 * This object is also a shape drawer.<br>
 * The font size is calculated automatically based on tile size.<br>
 * It designs a char into the following tile sections (A and B):<br>
 * 
 * <pre>
 * +---------+---------+
 * |         |         |
 * |    A    |         |
 * |         |         |
 * +---------+---------+
 * |         |         |
 * |         |    B    |
 * |         |         |
 * +---------+---------+
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CharacterShape extends AbstractShape {

	/**
	 * Name of shape to draw a character, <b>{@value CHARACTER_SHAPE_NAME}</b>.
	 */
	public static final String CHARACTER_SHAPE_NAME = "char";
	// default font decrement
	private static final int FONT_SIZE_DECREMENT = 2;
	// string (char) to draw on the tile
	private final String character;
	// font family to apply on tile
	private final String fontFamily;

	/**
	 * Creates a shape with a character as shape, using the default font family
	 * 
	 * @param character character to draw into tile as shape
	 */
	public CharacterShape(String character) {
		this(character, Defaults.get().getGlobal().getFontFamily());
	}

	/**
	 * Creates a shape with a character as shape using passed font family.
	 * 
	 * @param character character to draw into tile as shape
	 * @param fontFamily font family to apply on tile
	 */
	public CharacterShape(String character, String fontFamily) {
		super(CHARACTER_SHAPE_NAME);
		// checks the character is consistent
		if (character == null) {
			// if not, exception
			throw new IllegalArgumentException("Character argument is null");
		}
		// checks if there is only 1 char
		if (character.trim().length() != 1) {
			// if not, exception
			throw new IllegalArgumentException("Character argument is invalid. Must be 1 char");
		}
		// stores attributes
		this.character = character;
		this.fontFamily = fontFamily != null ? fontFamily : Defaults.get().getGlobal().getFontFamily();
		// creates the prefix key for caching
		super.setKeyPrefix(CHARACTER_SHAPE_NAME + character + fontFamily);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(org.pepstock.charba.client.dom.Context2dItem, java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2dItem context, String backgroundColor, String shapeColor, int size) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// sets real font size
		final int realFontSize = calculateFontSize(context, character, halfSize, FontStyle.NORMAL, fontFamily);
		// apply the stroke properties
		applyFillProperties(context, shapeColor);
		// sets font as string
		context.setFont(Utilities.toCSSFontProperty(FontStyle.NORMAL, realFontSize, fontFamily));
		// sets alignment from center point
		context.setTextBaseline(TextBaseline.TOP);
		// gets metrics
		TextMetricsItem metrics = context.measureText(character);
		// designs the shape into A section
		drawChar(context, size, 0, 0, character, realFontSize, metrics);
		// designs the shape into B section
		drawChar(context, size, halfSize, halfSize, character, realFontSize, metrics);
		// draws the current path with the current stroke style
		context.fill();
	}

	/**
	 * Designs a char into a tile section.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 * @param offsetX offset X where starts drawing
	 * @param offsetY offset Y where starts drawing
	 * @param character character to draw on the tile
	 * @param fontSize font size to apply to character
	 * @param metrics canvas metrics to know the width of character
	 */
	private void drawChar(Context2dItem context, int size, double offsetX, double offsetY, String character, int fontSize, TextMetricsItem metrics) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// calculates x point (centering the char)
		final double x = (halfSize - metrics.getWidth()) / 2D + offsetX;
		// calculates y point (centering the char)
		final double y = (halfSize - fontSize) / 2D + offsetY;
		// draws text
		context.fillText(character, x, y);
	}

	/**
	 * Calculates the font size based on available space of the tile.
	 * 
	 * @param context context of canvas to design the shape
	 * @param value character to draw
	 * @param size size of tile
	 * @param style font style to use to apply the char on tile
	 * @param fontFamily font family to use to apply the char on tile
	 * @return the font size to use
	 */
	private int calculateFontSize(Context2dItem context, String value, double size, FontStyle style, String fontFamily) {
		// creates an instance for font size
		int calculatedFontSize = (int) size;
		// flag to exit form loop
		boolean check = true;
		// loop to calculate the size
		while (check) {
			// sets font
			context.setFont(Utilities.toCSSFontProperty(style, calculatedFontSize, fontFamily));
			// gets metrics
			TextMetricsItem metrics = context.measureText(value);
			// if the width is inside of tile size
			// exit
			if (metrics.getWidth() < (int) size) {
				check = false;
			} else {
				// decrements the font size
				calculatedFontSize = calculatedFontSize - FONT_SIZE_DECREMENT;
			}
		}
		// returns font size
		return calculatedFontSize;
	}

}