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
 * RECT drawer to design a {@link PointStyle#RECT} into tile.<br>
 * It designs the point style into the following tile section (A):<br>
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
final class PointStyleRect extends PointStyleRectRot {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.AbstractPointStyleShapeDrawer#drawPointStyle(org.pepstock.charba.client.dom.
	 * Context2dItem, int, double, double, double, double, double)
	 */
	@Override
	protected void drawPointStyle(Context2dItem context, int size, double x, double y, double radius, double rotation, double rad) {
		if (rotation == 0D) {
			double changedSize = Math.sqrt(1D / 2D) * radius;
			context.rect(x - changedSize, y - changedSize, 2 * changedSize, 2 * changedSize);
			return;
		}
		double changedRad = rad + QUARTER_PI;
		super.drawPointStyle(context, size, x, y, radius, rotation, changedRad);
	}

}
