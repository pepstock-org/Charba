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
import org.pepstock.charba.client.enums.PointStyle;

/**
 * STAR drawer to design a {@link PointStyle#STAR} in the tile.<br>
 * It designs the point style in the following tile section (A):<br>
 * <br>
 * 
 * <pre>
 * +---------+---------+
 * |                   |
 * |                   |
 * |                   |
 * |         A         |
 * |                   |
 * |                   |
 * |                   |
 * +---------+---------+
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class PointStyleStar extends AbstractPointStyleShapeDrawer {

	/**
	 * Draws the tile using the point style applying the requested shape.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 * @param x x point center of canvas
	 * @param y y point center of canvas
	 * @param radius the radius of the tile, only for {@link PointStyle} drawing
	 * @param rotation the rotation (in degrees) to create the tile, only for {@link PointStyle} drawing
	 * @param rad rad calculates on rotation
	 */
	@Override
	protected void drawPointStyle(Context2dItem context, int size, double x, double y, double radius, double rotation, double rad) {
		double xOffset = Math.cos(rad) * radius;
		double yOffset = Math.sin(rad) * radius;
		context.moveTo(x - xOffset, y - yOffset);
		context.lineTo(x + xOffset, y + yOffset);
		context.moveTo(x + yOffset, y - xOffset);
		context.lineTo(x - yOffset, y + xOffset);
		double newRad = rad + QUARTER_PI;
		xOffset = Math.cos(newRad) * radius;
		yOffset = Math.sin(newRad) * radius;
		context.moveTo(x - xOffset, y - yOffset);
		context.lineTo(x + xOffset, y + yOffset);
		context.moveTo(x + yOffset, y - xOffset);
		context.lineTo(x - yOffset, y + xOffset);
	}

}
