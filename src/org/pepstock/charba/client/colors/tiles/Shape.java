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
public enum Shape
{
	box(new Box()),
	cross(new Cross()),
	crossDash(new CrossDash()),
	dash(new Dash()),
	diagonal(new Diagonal()),
	diagonalRightLeft(new DiagonalRightLeft()),
	diamond(new Diamond()),
	diamondBox(new DiamondBox()),
	disc(new Disc()),
	dot(new Dot()),
	dotDash(new DotDash()),
	line(new Line()),
	plus(new Plus()),
	ring(new Ring()),
	square(new Square()),
	triangle(new Triangle()),
	weave(new Weave()),
	zigzag(new ZigZag()),
	verticalLine(new VerticalLine()),
	verticalTriangle(new VerticalTriangle()),
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

	/**
	 * Returns the instance of shape drawer.
	 * 
	 * @return the instance of shape drawer.
	 */
	final ShapeDrawer getDrawer() {
		return drawer;
	}
}