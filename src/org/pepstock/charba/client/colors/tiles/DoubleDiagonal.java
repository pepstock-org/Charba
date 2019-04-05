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
 * Double DIAGONAL drawer to design a set of diagonals into tile.<br>
 * It designs a set of diagonals into the following tile sections (B, C and A-D):<br>
 * <br>
 * 
 * <pre>
 * +---------+---------+
 * |         |         |
 * |    A    |    B    |
 * |         |         |
 * +---------+---------+
 * |         |         |
 * |    C    |    D    |
 * |         |         |
 * +---------+---------+
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
class DoubleDiagonal extends Diagonal {

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
		// designs the shape into B section
		drawDiagonal(context, size, 0D, 0D);
		// designs the shape into C section
		drawDiagonal(context, size, halfSize, halfSize);
		// designs the shape into A-D section
		drawWholeDiagonal(context, size);
		// draws the current path with the current stroke style
		context.stroke();
	}

	/**
	 * Designs a diagonal into a tile section.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 */
	final void drawWholeDiagonal(Context2d context, int size) {
		// draws shape
		// to point for drawing line
		context.moveTo(0D, 0D);
		context.lineTo(size, size);
		// draws shape
		// to point for drawing line
		context.moveTo(size - 1D, -1D);
		context.lineTo(size + 1D, 1D);
		// draws shape
		// to point for drawing line
		context.moveTo(-1D, size - 1D);
		context.lineTo(1D, size + 1D);

	}

}
