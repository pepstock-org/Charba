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
 * DIAMOND drawer to design a diamond in the tile.<br>
 * It designs a diamond in the the following tile sections (A and B):<br>
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
final class Diamond extends ShapeDrawer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(org.pepstock.charba.client.dom.Context2dItem, java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2dItem context, String backgroundColor, String shapeColor, int size) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// apply the fill properties
		applyFillProperties(context, shapeColor);
		// designs the shape in the A section
		drawDiamond(context, size, 0D, 0D);
		// designs the shape in the B section
		drawDiamond(context, size, halfSize, halfSize);
		// fills the current path
		context.fill();
	}

	/**
	 * Designs a diamond in the a tile section.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 * @param offsetX offset X where starts drawing
	 * @param offsetY offset Y where starts drawing
	 */
	void drawDiamond(Context2dItem context, int size, double offsetX, double offsetY) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// calculates quarter dimension
		final double quarterSize = size / 4D;
		// draws shape
		// to point to start drawing
		context.moveTo(quarterSize + offsetX, offsetY);
		context.lineTo(halfSize + offsetX, quarterSize + offsetY);
		context.lineTo(quarterSize + offsetX, halfSize + offsetY);
		context.lineTo(offsetX, quarterSize + offsetY);
	}
}
