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
 * DOT drawer to design a dot into tile.<br>
 * It designs a dot into the following tile sections (A and B):<br>
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
class Dot extends ShapeDrawer {

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
		// calculates diameter of dot
		final double diameter = size / 10D;
		// apply the fill properties
		applyFillProperties(context, shapeColor);
		// designs the shape into A section
		drawDot(context, size, 0, 0, diameter);
		// designs the shape into B section
		drawDot(context, size, halfSize, halfSize, diameter);
		// fills the current path
		context.fill();
	}

	/**
	 * Designs a dot into a tile section.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 * @param offsetX offset X where starts drawing
	 * @param offsetY offset Y where starts drawing
	 * @param diameter diameter of arc to design
	 */
	final void drawDot(Context2d context, int size, double offsetX, double offsetY, double diameter) {
		// calculates quarter dimension
		final double quarterSize = size / 4D;
		// calculates the center X of arc
		final double x = quarterSize + offsetX;
		// calculates the center Y of arc
		final double y = quarterSize + offsetY;
		// draws shape
		context.moveTo(x + quarterSize, y);
		context.arc(x, y, diameter, 0, 2 * Math.PI);
	}
}
