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

import com.google.gwt.canvas.dom.client.Context2d;

/**
 * DIAMOND BOX drawer to design a diamond into tile.<br>
 * It designs a diamond into the following tile sections (A and B):<br>
 * <br>
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
final class DiamondBox extends ShapeDrawer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(com.google.gwt.canvas.dom.client.Context2d,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2d context, String backgroundColor, String shapeColor, int size) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// apply the stroke properties
		applyStrokeProperties(context, shapeColor, size);
		// designs the shape into A section
		drawDiamond(context, size, 0D, 0D);
		// designs the shape into B section
		drawDiamond(context, size, halfSize, halfSize);
		// draws the current path with the current stroke style
		context.stroke();
	}

	/**
	 * Designs a diamond box into a tile section.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 * @param offsetX offset X where starts drawing
	 * @param offsetY offset Y where starts drawing
	 */
	void drawDiamond(Context2d context, int size, double offsetX, double offsetY) {
		// calculates half dimension
		final double halfSize = (size / 2D) - 1D;
		// calculates quarter dimension
		final double quarterSize = size / 4D;
		// draws shape
		// to point for drawing line
		context.moveTo(quarterSize + offsetX, offsetY + 1D);
		context.lineTo(halfSize + offsetX, quarterSize + offsetY);
		context.lineTo(quarterSize + offsetX, halfSize + offsetY);
		context.lineTo(offsetX + 1D, quarterSize + offsetY);
		context.lineTo(quarterSize + offsetX, offsetY + 1D);
	}
}
