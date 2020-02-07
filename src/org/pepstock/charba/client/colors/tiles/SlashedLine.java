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

import org.pepstock.charba.client.dom.elements.Context2dItem;

/**
 * SLASH drawer to design a slash into tile.<br>
 * It designs a slash into the following tile sections (A, B and C):<br>
 * <br>
 * 
 * <pre>
 * +-----+-------+-----+
 * |     |       |     |
 * |     |       |  B  |
 * |     |       |     |
 * +-----|   A   |-----+
 * |     |       |     |
 * |  C  |       |     |
 * |     |       |     |
 * +-----+-------+-----+
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class SlashedLine extends ShapeDrawer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(org.pepstock.charba.client.dom.Context2dItem,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2dItem context, String backgroundColor, String shapeColor, int size) {
		// calculates fifth dimension
		final double quarterSize = size / 4D;
		// apply the stroke properties
		applyStrokeProperties(context, shapeColor, size);
		// designs the shape into A section
		drawDiagonal(context, size, quarterSize * -2D, 0D, quarterSize * 6D);
		// designs the shape into C section
		drawDiagonal(context, quarterSize * 3D, quarterSize * -4D, quarterSize * -1D, size);
		// designs the shape into B section
		drawDiagonal(context, size + quarterSize, 0D, quarterSize, size * 2D);
		// draws the current path with the current stroke style
		context.stroke();
	}

	/**
	 * Designs a slash into a tile section.
	 * 
	 * @param context context of canvas to design the shape
	 * @param startX offset X where starts drawing
	 * @param startY offset Y where starts drawing
	 * @param endX offset X where ends drawing
	 * @param endY offset Y where ends drawing
	 */
	void drawDiagonal(Context2dItem context, double startX, double startY, double endX, double endY) {
		// draws shape
		// to point for drawing line
		context.moveTo(startX, startY);
		context.lineTo(endX, endY);
	}
}
