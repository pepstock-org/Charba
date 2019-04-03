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
	backSlashedLine(new BackSlashedLine()),
	/**
	 * Draws a box on the tile.
	 */
	box(new Box()),
	/**
	 * Draws a cross on the tile.
	 */
	cross(new Cross()),
	/**
	 * Draws a cross and a dash on the tile.
	 */
	crossDash(new CrossDash()),
	/**
	 * Draws a dash on the tile.
	 */
	dash(new Dash()),
	/**
	 * Draws a diagonal (from left to right) on the tile.
	 */
	diagonal(new Diagonal()),
	/**
	 * Draws a diamond on the tile.
	 */
	diamond(new Diamond()),
	/**
	 * Draws a diamond and a box on the tile.
	 */
	diamondBox(new DiamondBox()),
	/**
	 * Draws a disc on the tile.
	 */
	disc(new Disc()),
	/**
	 * Draws a dot on the tile.
	 */
	dot(new Dot()),
	/**
	 * Draws a dot and a dash on the tile.
	 */
	dotDash(new DotDash()),
	/**
	 * Draws a double diagonals on the tile.
	 */
	doubleDiagonal(new DoubleDiagonal()),
	/**
	 * Draws a start (no filled) on the tile.
	 */
	emptyStar(new EmptyStar()),
	/**
	 * Draws a diagonal (from right to left) on the tile.
	 */
	invertedDiagonal(new InvertedDiagonal()),
	/**
	 * Draws a double diagonals (from right to left) on the tile.
	 */
	invertedDoubleDiagonal(new InvertedDoubleDiagonal()),
	/**
	 * Draws a triangle (with spike down) on the tile.
	 */
	invertedTriangle(new InvertedTriangle()),
	/**
	 * Draws a line on the tile.
	 */
	line(new Line()),
	/**
	 * Draws a plus on the tile.
	 */
	plus(new Plus()),
	/**
	 * Draws a ring on the tile.
	 */
	ring(new Ring()),
	/**
	 * Draws a slashed line.
	 */
	slashedLine(new SlashedLine()),
	/**
	 * Draws an empty tile.
	 */
	solid(new Solid()),
	/**
	 * Draws a star.
	 */
	star(new Star()),
	/**
	 * Draws a square on the tile.
	 */
	square(new Square()),
	/**
	 * Draws a triangle on the tile.
	 */
	triangle(new Triangle()),
	/**
	 * Draws a weave on the tile.
	 */
	weave(new Weave()),
	/**
	 * Draws a zig zag line on the tile.
	 */
	zigzag(new ZigZag()),
	/**
	 * Draws a line vertically on the tile.
	 */
	verticalLine(new VerticalLine()),
	/**
	 * Draws a zig zag vertically on the tile.
	 */
	verticalZigzag(new VerticalZigZag());

	// instance of shape drawer
	private final ShapeDrawer drawer;

	/**
	 * Creates a shaper with own drawer.
	 * 
	 * @param the shape drawer instance
	 */
	private Shape(ShapeDrawer drawer) {
		this.drawer = drawer;
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