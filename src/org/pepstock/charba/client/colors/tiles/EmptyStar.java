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
 * STAR drawer to design a star (empty) into tile.<br>
 * It designs a star into the following tile sections (only A):<br>
 * <br>
 * 
 * <pre>
 * +-------------------+
 * |                   |
 * |                   |
 * |                   |
 * |         A         |
 * |                   |
 * |                   |
 * |                   |
 * +-------------------+
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class EmptyStar extends Star {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(com.google.gwt.canvas.dom.client.Context2d,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	void drawTile(Context2d context, String backgroundColor, String shapeColor, int size) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// calculates quarter dimension
		final double quarterSize = size / 4D;
		// apply the stroke properties
		applyStrokeProperties(context, shapeColor, size);
		// override line width to 1
		context.setLineWidth(1D);
		// designs the shape into A section
		drawStar(context, size, halfSize, halfSize, SPIKES, halfSize - 2, quarterSize - 1);
		// strokes the current path
		context.stroke();
	}

}
