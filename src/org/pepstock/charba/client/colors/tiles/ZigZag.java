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
 * ZIGZAG drawer to design a zigzag line in the tile.<br>
 * It designs a zigzag line in the following tile sections (A and B):<br>
 * <br>
 * 
 * <pre>
 * +-------------------+
 * |                   |
 * |         A         |
 * |                   |
 * +-------------------+
 * |                   |
 * |         B         |
 * |                   |
 * +-------------------+
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
class ZigZag extends ShapeDrawer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(org.pepstock.charba.client.dom.Context2dItem, java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2dItem context, String backgroundColor, String shapeColor, int size) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// apply the stroke properties
		applyStrokeProperties(context, shapeColor, size);
		// designs the shape in the A section
		drawZigZag(context, size, 0D);
		// designs the shape in the B section
		drawZigZag(context, size, halfSize);
		// draws the current path with the current stroke style
		context.stroke();
	}

	/**
	 * Designs a zigzag in the a tile section.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 * @param offsetY offset Y where starts drawing
	 */
	final void drawZigZag(Context2dItem context, int size, double offsetY) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// calculates quarter dimension
		final double quarterSize = size / 4D;
		// calculates tenth dimension
		final double tenthSize = size / 10D;
		// draws shape
		// to point for drawing line
		context.moveTo(0, tenthSize + offsetY);
		context.lineTo(quarterSize, (halfSize - tenthSize) + offsetY);
		context.lineTo(halfSize, tenthSize + offsetY);
		context.lineTo(size - quarterSize, (halfSize - tenthSize) + offsetY);
		context.lineTo(size, tenthSize + offsetY);
	}
}
