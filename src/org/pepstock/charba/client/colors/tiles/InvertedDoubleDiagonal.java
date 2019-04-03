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
 * Double DIAGONAL drawer to design a set of diagonals (form right to left) into tile.<br>
 * It designs a set of diagonals into the following tile sections (A, D and B-C):<br>
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
final class InvertedDoubleDiagonal extends DoubleDiagonal {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.Diagonal#drawTile(com.google.gwt.canvas.dom.client.Context2d,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2d context, String backgroundColor, String shapeColor, int size) {
		// applies a translation to the current transform
		context.translate(size, 0D);
		// applies rotation (90 degrees) to the current transform
		context.rotate(ROTATION_90_DEGREES);
		// invoke the diagonal shape drawer
		// which will design the diagonal from right to left
		super.drawTile(context, backgroundColor, shapeColor, size);
	}

}
