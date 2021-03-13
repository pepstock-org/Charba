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
 * DOTDASH drawer to design a dot and a dash in the tile.<br>
 * It designs a dot and a dash in the the following tile sections (A and B):<br>
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
final class DotDash extends ShapeDrawer {
	// dots instance
	private final Dot dot = new Dot();
	// dash instance
	private final Dash dash = new Dash();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(org.pepstock.charba.client.dom.Context2dItem, java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2dItem context, String backgroundColor, String shapeColor, int size) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// calculates diameter of arc
		final double diameter = size / 10D;
		// apply the stroke properties
		applyStrokeProperties(context, shapeColor, size);
		// designs the shape (DASH) in the B section
		dash.drawDash(context, size, halfSize, halfSize);
		// draws the current path with the current stroke style
		context.stroke();
		// apply the fill properties
		applyFillProperties(context, shapeColor);
		// designs the shape in the A section
		dot.drawDot(context, size, 0D, 0D, diameter);
		// fills the current path
		context.fill();
	}
}
