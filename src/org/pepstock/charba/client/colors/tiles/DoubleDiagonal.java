/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.colors.tiles;

import org.pepstock.charba.client.dom.elements.Context2dItem;

/**
 * Double DIAGONAL drawer to design a set of diagonals in the tile.<br>
 * It designs a set of diagonals in the following tile sections (B, C and A-D):<br>
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
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(org.pepstock.charba.client.dom.Context2dItem, java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2dItem context, String backgroundColor, String shapeColor, int size) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// apply the stroke properties
		applyStrokeProperties(context, shapeColor, size);
		// designs the shape in the B section
		drawDiagonal(context, size, 0D, 0D);
		// designs the shape in the C section
		drawDiagonal(context, size, halfSize, halfSize);
		// designs the shape in the A-D section
		drawWholeDiagonal(context, size);
		// draws the current path with the current stroke style
		context.stroke();
	}

	/**
	 * Designs a diagonal in the a tile section.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 */
	final void drawWholeDiagonal(Context2dItem context, int size) {
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