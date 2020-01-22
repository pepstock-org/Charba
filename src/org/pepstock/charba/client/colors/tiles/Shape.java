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

/**
 * Enumerates all available shapes applicable onto a tile.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Shape implements IsShape
{
	/**
	 * Draws a back slashed line.
	 */
	BACK_SLASHED_LINE(new BackSlashedLine()),
	/**
	 * Draws a box on the tile.
	 */
	BOX(new Box()),
	/**
	 * Draws a cross on the tile.
	 */
	CROSS(new Cross()),
	/**
	 * Draws a cross and a dash on the tile.
	 */
	CROSS_DASH(new CrossDash()),
	/**
	 * Draws a dash on the tile.
	 */
	DASH(new Dash()),
	/**
	 * Draws a diagonal (from left to right) on the tile.
	 */
	DIAGONAL(new Diagonal()),
	/**
	 * Draws a diamond on the tile.
	 */
	DIAMOND(new Diamond()),
	/**
	 * Draws a diamond and a box on the tile.
	 */
	DIAMOND_BOX(new DiamondBox()),
	/**
	 * Draws a disc on the tile.
	 */
	DISC(new Disc()),
	/**
	 * Draws a dot on the tile.
	 */
	DOT(new Dot()),
	/**
	 * Draws a dot and a dash on the tile.
	 */
	DOT_DASH(new DotDash()),
	/**
	 * Draws a double diagonals on the tile.
	 */
	DOUBLE_DIAGONAL(new DoubleDiagonal()),
	/**
	 * Draws a start (no filled) on the tile.
	 */
	EMPTY_STAR(new EmptyStar()),
	/**
	 * Draws a diagonal (from right to left) on the tile.
	 */
	INVERTED_DIAGONAL(new InvertedDiagonal()),
	/**
	 * Draws a double diagonals (from right to left) on the tile.
	 */
	INVERTED_DOUBLE_DIAGONAL(new InvertedDoubleDiagonal()),
	/**
	 * Draws a triangle (with spike down) on the tile.
	 */
	INVERTED_TRIANGLE(new InvertedTriangle()),
	/**
	 * Draws a line on the tile.
	 */
	LINE(new Line()),
	/**
	 * Draws a plus on the tile.
	 */
	PLUS(new Plus()),
	/**
	 * Draws a ring on the tile.
	 */
	RING(new Ring()),
	/**
	 * Draws a slashed line.
	 */
	SLASHED_LINE(new SlashedLine()),
	/**
	 * Draws an empty tile.
	 */
	SOLID(new Solid()),
	/**
	 * Draws a star.
	 */
	STAR(new Star()),
	/**
	 * Draws a square on the tile.
	 */
	SQUARE(new Square()),
	/**
	 * Draws a triangle on the tile.
	 */
	TRIANGLE(new Triangle()),
	/**
	 * Draws a weave on the tile.
	 */
	WEAVE(new Weave()),
	/**
	 * Draws a zig zag line on the tile.
	 */
	ZIGZAG(new ZigZag()),
	/**
	 * Draws a line vertically on the tile.
	 */
	VERTICAL_LINE(new VerticalLine()),
	/**
	 * Draws a zig zag vertically on the tile.
	 */
	VERTICAL_ZIGZAG(new VerticalZigZag());

	// instance of shape drawer
	private final ShapeDrawer drawer;

	/**
	 * Creates a shaper with own drawer.
	 * 
	 * @param drawer the shape drawer instance
	 */
	private Shape(ShapeDrawer drawer) {
		this.drawer = drawer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return name();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.IsShape#getKeyPrefix()
	 */
	@Override
	public String getKeyPrefix() {
		return name();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.IsShape#getDrawer()
	 */
	@Override
	public ShapeDrawer getDrawer() {
		return drawer;
	}

}