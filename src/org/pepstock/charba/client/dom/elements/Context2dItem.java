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
package org.pepstock.charba.client.dom.elements;

import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.enums.Repetition;
import org.pepstock.charba.client.dom.enums.TextAlign;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.items.UndefinedValues;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;

/**
 * DOM object which provides the 2D rendering context for the drawing surface of a canvas element.<br>
 * It is used for drawing shapes, text, images, and other objects.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_CANVAS_CONTEXT_2D, namespace = JsPackage.GLOBAL)
public final class Context2dItem extends BaseHtmlElement {

	/**
	 * To avoid any instantiation
	 */
	private Context2dItem() {
		// do nothing
	}

	/**
	 * Returns the canvas of the canvas rendering context 2D object.
	 *
	 * @return the canvas of the canvas rendering context 2D object
	 */
	@JsProperty
	public native Canvas getCanvas();

	/**
	 * Returns the filling styling is used for colors and styles inside shapes.
	 *
	 * @return color or style to use inside shapes. Default "#000" (black).
	 */
	@JsProperty(name = "fillStyle")
	private native Object nativeGetFillStyle();

	/**
	 * Sets the filling styling is used for colors and styles inside shapes.
	 *
	 * @param fillStyle color or style to use inside shapes.
	 */
	@JsProperty(name = "fillStyle")
	private native void nativeSetFillStyle(Object fillStyle);

	/**
	 * Sets the filling styling is used for colors inside shapes.
	 * 
	 * @param color color to use inside shapes
	 */
	@JsOverlay
	public void setFillColor(String color) {
		// checks if argument is consistent
		if (color != null) {
			// sets the fill color
			nativeSetFillStyle(color);
		}
	}

	/**
	 * Sets the filling styling is used for colors inside shapes.
	 * 
	 * @param color color to use inside shapes
	 */
	@JsOverlay
	public void setFillColor(IsColor color) {
		// checks if argument is consistent
		if (IsColor.isValid(color)) {
			// sets the fill color
			setFillColor(color.toRGBA());
		}
	}

	/**
	 * Sets the filling styling is used for gradient style inside shapes.
	 * 
	 * @param gradient gradient style to use inside shapes
	 */
	@JsOverlay
	public void setFillGradient(CanvasGradientItem gradient) {
		// checks if argument is consistent
		if (gradient != null) {
			// sets the fill gradient
			nativeSetFillStyle(gradient);
		}
	}

	/**
	 * Sets the filling styling is used for pattern style inside shapes.
	 * 
	 * @param pattern pattern style to use inside shapes
	 */
	@JsOverlay
	public final void setFillPattern(CanvasPatternItem pattern) {
		// checks if argument is consistent
		if (pattern != null) {
			// sets the fill pattern
			nativeSetFillStyle(pattern);
		}
	}

	/**
	 * Returns the filling styling is used for colors inside shapes.
	 * 
	 * @return color to use inside shapes
	 */
	@JsOverlay
	public String getFillColorAsString() {
		return getColorAsString(nativeGetFillStyle());
	}

	/**
	 * Returns the filling styling is used for colors inside shapes.
	 * 
	 * @return color to use inside shapes
	 */
	@JsOverlay
	public IsColor getFillColor() {
		return getColor(nativeGetFillStyle());
	}

	/**
	 * Returns the filling styling is used for gradient style inside shapes.
	 * 
	 * @return gradient style to use inside shapes
	 */
	@JsOverlay
	public CanvasGradientItem getFillGradient() {
		return getGradient(nativeGetFillStyle());
	}

	/**
	 * Returns the filling styling is used for pattern style inside shapes.
	 * 
	 * @return pattern style to use inside shapes
	 */
	@JsOverlay
	public final CanvasPatternItem getFillPattern() {
		return getPattern(nativeGetFillStyle());
	}

	/**
	 * Returns the current text style to use when drawing text.<br>
	 * This string uses the same syntax as the CSS font specifier.
	 *
	 * @return the current text style to use when drawing text
	 */
	@JsProperty
	public native String getFont();

	/**
	 * Sets the current text style to use when drawing text.<br>
	 * This string uses the same syntax as the CSS font specifier.
	 *
	 * @param font the current text style to use when drawing text
	 */
	@JsProperty
	public native void setFont(String font);

	/**
	 * Returns the shape used to draw the end points of lines.
	 *
	 * @return the shape used to draw the end points of lines as string
	 */
	@JsProperty(name = "lineCap")
	private native String nativeGetLineCap();

	/**
	 * Sets the shape used to draw the end points of lines.
	 *
	 * @param lineCap the shape used to draw the end points of lines as string
	 */
	@JsProperty(name = "lineCap")
	private native void nativeSetLineCap(String lineCap);

	/**
	 * Sets the shape used to draw the end points of lines.
	 * 
	 * @param lineCap the shape used to draw the end points of lines.
	 */
	@JsOverlay
	public void setLineCap(CapStyle lineCap) {
		// checks if argument is consistent
		if (Key.isValid(lineCap)) {
			// stores the line cap
			nativeSetLineCap(lineCap.value());
		}
	}

	/**
	 * Returns the shape used to draw the end points of lines.
	 *
	 * @return the shape used to draw the end points of lines
	 */
	@JsOverlay
	public CapStyle getLineCap() {
		return Key.getKeyByValue(CapStyle.values(), nativeGetLineCap(), CapStyle.BUTT);
	}

	/**
	 * Returns the line dash offset, or "phase.".<br>
	 * Specifies where to start a dash array on a line.
	 *
	 * @return the line dash offset, or "phase."
	 */
	@JsProperty
	public native double getLineDashOffset();

	/**
	 * Sets the line dash offset, or "phase.".<br>
	 * Specifies where to start a dash array on a line.
	 *
	 * @param lineDashOffset the line dash offset, or "phase."
	 */
	@JsProperty
	public native void setLineDashOffset(double lineDashOffset);

	/**
	 * Returns the type of corners where two lines meet.
	 *
	 * @return the type of corners where two lines meet
	 */
	@JsProperty(name = "lineJoin")
	private native String nativeGetLineJoin();

	/**
	 * Sets the type of corners where two lines meet.
	 *
	 * @param lineJoin the type of corners where two lines meet
	 */
	@JsProperty(name = "lineJoin")
	private native void nativeSetLineJoin(String lineJoin);

	/**
	 * Sets the type of corners where two lines meet.
	 *
	 * @param lineJoin the type of corners where two lines meet
	 */
	@JsOverlay
	public void setLineJoin(JoinStyle lineJoin) {
		// checks if argument is consistent
		if (Key.isValid(lineJoin)) {
			// stores the line cap
			nativeSetLineJoin(lineJoin.value());
		}
	}

	/**
	 * Returns the type of corners where two lines meet.
	 *
	 * @return the type of corners where two lines meet
	 */
	@JsOverlay
	public JoinStyle getLineJoin() {
		return Key.getKeyByValue(JoinStyle.values(), nativeGetLineJoin(), JoinStyle.BEVEL);
	}

	/**
	 * Returns the thickness of lines.
	 *
	 * @return the thickness of lines
	 */
	@JsProperty
	public native double getLineWidth();

	/**
	 * Sets the thickness of lines.
	 *
	 * @param lineWidth the thickness of lines
	 */
	@JsProperty
	public native void setLineWidth(double lineWidth);

	/**
	 * Sets the line dash pattern used when stroking lines.<br>
	 * It uses an array of values that specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param lineDash an array of integers that specify distances to alternately draw a line and a gap (in coordinate space units).<br>
	 *            If the number of elements in the array is odd, the elements of the array get copied and concatenated.<br>
	 *            If the array is empty, the line dash list is cleared and line strokes return to being solid.
	 */
	@JsMethod
	private native void setLineDash(ArrayInteger lineDash);

	/**
	 * Sets the line dash pattern used when stroking lines.<br>
	 * It uses an array of values that specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param lineDash an array of integers that specify distances to alternately draw a line and a gap (in coordinate space units).<br>
	 *            If the number of elements in the array is odd, the elements of the array get copied and concatenated.<br>
	 *            If the array is empty, the line dash list is cleared and line strokes return to being solid.
	 */
	@JsOverlay
	public void setLineDash(int... lineDash) {
		setLineDash(ArrayInteger.fromOrEmpty(lineDash));
	}

	/**
	 * Sets the line dash pattern used when stroking lines.<br>
	 * It uses a list of values that specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param lineDash a list of integers that specify distances to alternately draw a line and a gap (in coordinate space units).<br>
	 *            If the number of elements in the list is odd, the elements of the list get copied and concatenated.<br>
	 *            If the list is empty, the line dash list is cleared and line strokes return to being solid.
	 */
	@JsOverlay
	public void setLineDash(List<Integer> lineDash) {
		setLineDash(ArrayInteger.fromOrEmpty(lineDash));
	}

	/**
	 * Returns the miter limit ratio.
	 *
	 * @return the miter limit ratio
	 */
	@JsProperty
	public native double getMiterLimit();

	/**
	 * Sets the miter limit ratio.
	 *
	 * @param miterLimit the miter limit ratio
	 */
	@JsProperty
	public native void setMiterLimit(double miterLimit);

	/**
	 * Returns the amount of blur applied to shadows.
	 *
	 * @return the amount of blur applied to shadows.<br>
	 *         The default is 0 (no blur).
	 */
	@JsProperty
	public native double getShadowBlur();

	/**
	 * Sets the amount of blur applied to shadows.
	 *
	 * @param shadowBlur the amount of blur applied to shadows.<br>
	 *            The default is 0 (no blur).
	 */
	@JsProperty
	public native void setShadowBlur(double shadowBlur);

	/**
	 * Returns the color of shadows.<br>
	 * The shadow's rendered opacity will be affected by the opacity of the fillStyle color when filling, and of the strokeStyle color when stroking.
	 *
	 * @return the color of shadows
	 */
	@JsProperty
	public native String getShadowColor();

	/**
	 * Sets the color of shadows.<br>
	 * The shadow's rendered opacity will be affected by the opacity of the fillStyle color when filling, and of the strokeStyle color when stroking.
	 *
	 * @param shadowColor the color of shadows
	 */
	@JsProperty
	public native void setShadowColor(String shadowColor);

	/**
	 * Returns the distance that shadows will be offset horizontally.
	 *
	 * @return the distance that shadows will be offset horizontally
	 */
	@JsProperty
	public native double getShadowOffsetX();

	/**
	 * Sets the distance that shadows will be offset horizontally.
	 *
	 * @param shadowOffsetX the distance that shadows will be offset horizontally
	 */
	@JsProperty
	public native void setShadowOffsetX(double shadowOffsetX);

	/**
	 * Returns the distance that shadows will be offset vertically.
	 *
	 * @return the distance that shadows will be offset vertically
	 */
	@JsProperty
	public native double getShadowOffsetY();

	/**
	 * Sets the distance that shadows will be offset vertically.
	 *
	 * @param shadowOffsetY the distance that shadows will be offset vertically.
	 */
	@JsProperty
	public native void setShadowOffsetY(double shadowOffsetY);

	/**
	 * Returns the stroke styling is used for the lines around shapes.<br>
	 * The color, gradient, or pattern to use for the strokes (outlines) around shapes.<br>
	 * The default is #000 (black).
	 *
	 * @return the stroke styling is used for the lines around shapes
	 */
	@JsProperty(name = "strokeStyle")
	private native Object nativeGetStrokeStyle();

	/**
	 * Sets the stroke styling is used for the lines around shapes.<br>
	 * The color, gradient, or pattern to use for the strokes (outlines) around shapes.<br>
	 * The default is #000 (black).
	 *
	 * @param strokeStyle the stroke styling is used for the lines around shapes
	 */
	@JsProperty(name = "strokeStyle")
	private native void nativeSetStrokeStyle(Object strokeStyle);

	/**
	 * Sets the stroke color is used for the lines around shapes.
	 * 
	 * @param color the stroke color is used for the lines around shapes
	 */
	@JsOverlay
	public void setStrokeColor(String color) {
		// checks if argument is consistent
		if (color != null) {
			// stores the stroke color
			nativeSetStrokeStyle(color);
		}
	}

	/**
	 * Sets the stroke color is used for the lines around shapes.
	 * 
	 * @param color the stroke color is used for the lines around shapes
	 */
	@JsOverlay
	public void setStrokeColor(IsColor color) {
		// checks if argument is consistent
		if (IsColor.isValid(color)) {
			// stores the stroke color
			setStrokeColor(color.toRGBA());
		}
	}

	/**
	 * Sets the stroke gradient is used for the lines around shapes.
	 * 
	 * @param gradient the stroke gradient is used for the lines around shapes
	 */
	@JsOverlay
	public void setStrokeGradient(CanvasGradientItem gradient) {
		// checks if argument is consistent
		if (gradient != null) {
			// stores the stroke gradient
			nativeSetStrokeStyle(gradient);
		}
	}

	/**
	 * Sets the stroke pattern is used for the lines around shapes.
	 * 
	 * @param pattern the stroke pattern is used for the lines around shapes
	 */
	@JsOverlay
	public final void setStrokePattern(CanvasPatternItem pattern) {
		// checks if argument is consistent
		if (pattern != null) {
			// stores the stroke pattern
			nativeSetStrokeStyle(pattern);
		}
	}

	/**
	 * Returns the stroke color is used for the lines around shapes.
	 * 
	 * @return color to use for the lines around shapes
	 */
	@JsOverlay
	public String getStrokeColorAsString() {
		return getColorAsString(nativeGetStrokeStyle());
	}

	/**
	 * Returns the stroke color is used for the lines around shapes.
	 * 
	 * @return color to use for the lines around shapes
	 */
	@JsOverlay
	public IsColor getStrokeColor() {
		return getColor(nativeGetStrokeStyle());
	}

	/**
	 * Returns the stroke gradient is used for the lines around shapes.
	 * 
	 * @return gradient style to use for the lines around shapes
	 */
	@JsOverlay
	public CanvasGradientItem getStrokeGradient() {
		return getGradient(nativeGetStrokeStyle());
	}

	/**
	 * Returns the stroke pattern is used for the lines around shapes.
	 * 
	 * @return pattern style to use for the lines around shapes
	 */
	@JsOverlay
	public final CanvasPatternItem getStrokePattern() {
		return getPattern(nativeGetFillStyle());
	}

	/**
	 * Returns the current text alignment used when drawing text.
	 *
	 * @return the current text alignment used when drawing text as string
	 */
	@JsProperty(name = "textAlign")
	private native String nativeGetTextAlign();

	/**
	 * Sets the current text alignment used when drawing text.
	 *
	 * @param textAlign the current text alignment used when drawing text as string
	 */
	@JsProperty(name = "textAlign")
	private native void nativeSetTextAlign(String textAlign);

	/**
	 * Sets the current text alignment used when drawing text.
	 *
	 * @param textAlign the current text alignment used when drawing text
	 */
	@JsOverlay
	public void setTextAlign(TextAlign textAlign) {
		// checks if argument is consistent
		if (Key.isValid(textAlign)) {
			// stores the text align
			nativeSetTextAlign(textAlign.value());
		}
	}

	/**
	 * Returns the current text alignment used when drawing text.
	 *
	 * @return the current text alignment used when drawing text
	 */
	@JsOverlay
	public TextAlign getTextAlign() {
		return Key.getKeyByValue(TextAlign.values(), nativeGetTextAlign(), TextAlign.START);
	}

	/**
	 * Returns the current text baseline used when drawing text.
	 *
	 * @return the current text baseline used when drawing text as sting
	 */
	@JsProperty(name = "textBaseline")
	private native String nativeGetTextBaseline();

	/**
	 * Sets the current text baseline used when drawing text.
	 *
	 * @param textBaseline the current text baseline used when drawing text as string
	 */
	@JsProperty(name = "textBaseline")
	private native void nativeSetTextBaseline(String textBaseline);

	/**
	 * Sets the current text baseline used when drawing text.
	 *
	 * @param textBaseline the current text baseline used when drawing text
	 */
	@JsOverlay
	public void setTextBaseline(TextBaseline textBaseline) {
		// checks if argument is consistent
		if (Key.isValid(textBaseline)) {
			// stores the text baseline
			nativeSetTextBaseline(textBaseline.value());
		}
	}

	/**
	 * Returns the current text baseline used when drawing text.
	 *
	 * @return the current text baseline used when drawing text
	 */
	@JsOverlay
	public TextBaseline getTextBaseline() {
		return Key.getKeyByValue(TextBaseline.values(), nativeGetTextBaseline(), TextBaseline.ALPHABETIC);
	}

	/**
	 * Adds a circular arc to the current sub-path.<br>
	 * The arc method creates a circular arc centered at (x, y) with a radius of radius.<br>
	 * The path starts at "startAngle", ends at "endAngle", and travels in the direction given by "anticlockwise" (defaulting to clockwise).
	 *
	 * @param x the horizontal coordinate of the arc's center
	 * @param y the vertical coordinate of the arc's center
	 * @param radius the arc's radius. Must be positive
	 * @param startAngle the angle at which the arc starts in radians, measured from the positive x-axis
	 * @param endAngle the angle at which the arc ends in radians, measured from the positive x-axis
	 * @param anticlockwise if <code>true</code>, draws the arc counter-clockwise between the start and end angles.<br>
	 *            The default is <code>false</code>(clockwise).
	 */
	@JsMethod
	public native void arc(double x, double y, double radius, double startAngle, double endAngle, boolean anticlockwise);

	/**
	 * Adds a circular arc to the current sub-path.<br>
	 * The arc method creates a circular arc centered at (x, y) with a radius of radius.<br>
	 * The path starts at "startAngle", ends at "endAngle", and travels in the clockwise direction.
	 *
	 * @param x the horizontal coordinate of the arc's center
	 * @param y the vertical coordinate of the arc's center
	 * @param radius the arc's radius. Must be positive
	 * @param startAngle the angle at which the arc starts in radians, measured from the positive x-axis
	 * @param endAngle the angle at which the arc ends in radians, measured from the positive x-axis
	 */
	@JsMethod
	public native void arc(double x, double y, double radius, double startAngle, double endAngle);

	/**
	 * adds a circular arc to the current sub-path, using the given control points and radius.<br>
	 * The arc is automatically connected to the path's latest point with a straight line, if necessary for the specified parameters.<br>
	 * This method is commonly used for making rounded corners.
	 *
	 * @param x1 the x-axis coordinate of the first control point
	 * @param y1 the y-axis coordinate of the first control point
	 * @param x2 the x-axis coordinate of the second control point
	 * @param y2 the y-axis coordinate of the second control point
	 * @param radius the arc's radius. Must be non-negative.
	 */
	@JsMethod
	public native void arcTo(double x1, double y1, double x2, double y2, double radius);

	/**
	 * Starts a new path by emptying the list of sub-paths.<br>
	 * Call this method when you want to create a new path.
	 */
	@JsMethod
	public native void beginPath();

	/**
	 * Adds a cubic B\u00e9zier curve to the current sub-path.<br>
	 * It requires three points: the first two are control points and the third one is the end point.<br>
	 * The starting point is the latest point in the current path.
	 *
	 * @param cp1x the x-axis coordinate of the first control point
	 * @param cp1y the y-axis coordinate of the first control point
	 * @param cp2x the x-axis coordinate of the second control point
	 * @param cp2y the y-axis coordinate of the second control point
	 * @param x the x-axis coordinate of the end point
	 * @param y the y-axis coordinate of the end point
	 */
	@JsMethod
	public native void bezierCurveTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y);

	/**
	 * Erases the pixels in a rectangular area by setting them to transparent black.
	 *
	 * @param x the x-axis coordinate of the rectangle's starting point
	 * @param y the y-axis coordinate of the rectangle's starting point
	 * @param width the rectangle's width. Positive values are to the right, and negative to the left
	 * @param height the rectangle's height. Positive values are down, and negative are up
	 */
	@JsMethod
	public native void clearRect(double x, double y, double width, double height);

	/**
	 * Turns the current or given path into the current clipping region.<br>
	 * It replaces any previous clipping region..
	 */
	@JsMethod
	public native void clip();

	/**
	 * Causes the point of the pen to move back to the start of the current sub-path.<br>
	 * It tries to draw a straight line from the current point to the start.<br>
	 * If the shape has already been closed or has only one point, this function does nothing.
	 */
	@JsMethod
	public native void closePath();

	/**
	 * Creates a gradient along the line connecting two given coordinates.
	 *
	 * @param x0 the x-axis coordinate of the start point
	 * @param y0 the y-axis coordinate of the start point
	 * @param x1 the x-axis coordinate of the end point
	 * @param y1 the y-axis coordinate of the end point
	 * @return a canvas gradient item
	 */
	@JsMethod
	public native CanvasGradientItem createLinearGradient(double x0, double y0, double x1, double y1);

	/**
	 * Creates a pattern using the specified image (could be also a canvas) and repetition.
	 *
	 * @param image an canvas or image object
	 * @param repetition a string indicating how to repeat the pattern's image
	 * @return a canvas pattern item
	 */
	@JsMethod
	private native CanvasPatternItem createPattern(Object image, String repetition);

	/**
	 * Creates a pattern using the specified canvas and repetition.
	 *
	 * @param canvas an canvas object
	 * @param repetition a repetition object indicating how to repeat the pattern's image
	 * @return a canvas pattern item
	 */
	@JsOverlay
	public final CanvasPatternItem createPattern(Canvas canvas, Repetition repetition) {
		// checks if argument is consistent
		if (canvas != null) {
			// gets the repetition checking if the argument is consistent
			// if not, uses the default
			Repetition checkedRepetition = Key.isValid(repetition) ? repetition : Repetition.NO_REPEAT;
			// creates and returns the pattern
			return createPattern(canvas, checkedRepetition.value());
		}
		// if here the argument is not consistent
		// then exception
		throw new IllegalArgumentException("Canvas element is null");
	}

	/**
	 * Creates a pattern using the specified image and repetition.
	 *
	 * @param image an image object
	 * @param repetition a repetition object indicating how to repeat the pattern's image
	 * @return a canvas pattern item
	 */
	@JsOverlay
	public final CanvasPatternItem createPattern(Img image, Repetition repetition) {
		// checks if argument is consistent
		if (image != null) {
			// gets the repetition checking if the argument is consistent
			// if not, uses the default
			Repetition checkedRepetition = Key.isValid(repetition) ? repetition : Repetition.NO_REPEAT;
			// creates and returns the pattern
			return createPattern(image, checkedRepetition.value());
		}
		// if here the argument is not consistent
		// then exception
		throw new IllegalArgumentException("Image element is null");
	}

	/**
	 * Creates a radial gradient using the size and coordinates of two circles.
	 *
	 * @param x0 the x-axis coordinate of the start circle
	 * @param y0 the y-axis coordinate of the start circle
	 * @param r0 the radius of the start circle. Must be non-negative and finite
	 * @param x1 the x-axis coordinate of the end circle
	 * @param y1 the y-axis coordinate of the end circle
	 * @param r1 the radius of the end circle. Must be non-negative and finite
	 * @return a canvas gradient item
	 */
	@JsMethod
	public native CanvasGradientItem createRadialGradient(double x0, double y0, double r0, double x1, double y1, double r1);

	/**
	 * Draws a canvas image source onto the canvas.
	 * 
	 * @param image an element to draw into the context. The specification permits any canvas image source
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dWidth the width to draw the image in the destination canvas.<br>
	 *            This allows scaling of the drawn image.<br>
	 *            If not specified, the image is not scaled in width when drawn
	 * @param dHeight the height to draw the image in the destination canvas.<br>
	 *            This allows scaling of the drawn image.<br>
	 *            If not specified, the image is not scaled in height when drawn
	 */
	@JsMethod(name = "drawImage")
	private native void nativeDrawImage(Canvas image, double dx, double dy, double dWidth, double dHeight);

	/**
	 * Draws an image onto the canvas.
	 * 
	 * @param image an element to draw into the context. The specification permits any canvas image source
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dWidth the width to draw the image in the destination canvas.<br>
	 *            This allows scaling of the drawn image.<br>
	 *            If not specified, the image is not scaled in width when drawn
	 */
	@JsMethod(name = "drawImage")
	private native void nativeDrawImage(Canvas image, double dx, double dy, double dWidth);

	/**
	 * Draws an image onto the canvas.
	 * 
	 * @param image an element to draw into the context. The specification permits any canvas image source
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 */
	@JsMethod(name = "drawImage")
	private native void nativeDrawImage(Canvas image, double dx, double dy);

	/**
	 * Draws a canvas onto the canvas.
	 * 
	 * @param canvas canvas instance to draw into the context
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source canvas
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source canvas
	 * @param dWidth the width to draw the canvas in the destination canvas.<br>
	 *            This allows scaling of the drawn canvas.<br>
	 *            If not specified, the canvas is not scaled in width when drawn
	 * @param dHeight the height to draw the canvas in the destination canvas.<br>
	 *            This allows scaling of the drawn canvas.<br>
	 *            If not specified, the canvas is not scaled in height when drawn
	 */
	@JsOverlay
	public final void drawImage(Canvas canvas, double dx, double dy, double dWidth, double dHeight) {
		nativeDrawImage(checkAndGetDrawImageArgument(canvas), dx, dy, dWidth, dHeight);
	}

	/**
	 * Draws a canvas onto the canvas.
	 * 
	 * @param canvas canvas instance to draw into the context
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source canvas
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source canvas
	 * @param dWidth the width to draw the canvas in the destination canvas.<br>
	 *            This allows scaling of the drawn canvas.<br>
	 *            If not specified, the canvas is not scaled in width when drawn
	 */
	@JsOverlay
	public final void drawImage(Canvas canvas, double dx, double dy, double dWidth) {
		nativeDrawImage(checkAndGetDrawImageArgument(canvas), dx, dy, dWidth);
	}

	/**
	 * Draws a canvas onto the canvas.
	 * 
	 * @param canvas canvas instance to draw into the context
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source canvas
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source canvas
	 */
	@JsOverlay
	public final void drawImage(Canvas canvas, double dx, double dy) {
		nativeDrawImage(checkAndGetDrawImageArgument(canvas), dx, dy);
	}

	/**
	 * Draws a canvas image source onto the canvas.
	 * 
	 * @param image an element to draw into the context. The specification permits any canvas image source
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dWidth the width to draw the image in the destination canvas.<br>
	 *            This allows scaling of the drawn image.<br>
	 *            If not specified, the image is not scaled in width when drawn
	 * @param dHeight the height to draw the image in the destination canvas.<br>
	 *            This allows scaling of the drawn image.<br>
	 *            If not specified, the image is not scaled in height when drawn
	 */
	@JsMethod(name = "drawImage")
	private native void nativeDrawImage(Img image, double dx, double dy, double dWidth, double dHeight);

	/**
	 * Draws an image onto the canvas.
	 * 
	 * @param image an element to draw into the context. The specification permits any canvas image source
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dWidth the width to draw the image in the destination canvas.<br>
	 *            This allows scaling of the drawn image.<br>
	 *            If not specified, the image is not scaled in width when drawn
	 */
	@JsMethod(name = "drawImage")
	private native void nativeDrawImage(Img image, double dx, double dy, double dWidth);

	/**
	 * Draws an image onto the canvas.
	 * 
	 * @param image an element to draw into the context. The specification permits any canvas image source
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 */
	@JsMethod(name = "drawImage")
	private native void nativeDrawImage(Img image, double dx, double dy);

	/**
	 * Draws an image onto the canvas.
	 * 
	 * @param image image instance to draw into the context
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dWidth the width to draw the image in the destination canvas.<br>
	 *            This allows scaling of the drawn image.<br>
	 *            If not specified, the image is not scaled in width when drawn
	 * @param dHeight the height to draw the image in the destination canvas.<br>
	 *            This allows scaling of the drawn image.<br>
	 *            If not specified, the image is not scaled in height when drawn
	 */
	@JsOverlay
	public final void drawImage(Img image, double dx, double dy, double dWidth, double dHeight) {
		nativeDrawImage(checkAndGetDrawImageArgument(image), dx, dy, dWidth, dHeight);
	}

	/**
	 * Draws an image onto the canvas.
	 * 
	 * @param image image instance to draw into the context
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dWidth the width to draw the image in the destination canvas.<br>
	 *            This allows scaling of the drawn image.<br>
	 *            If not specified, the image is not scaled in width when drawn
	 */
	@JsOverlay
	public final void drawImage(Img image, double dx, double dy, double dWidth) {
		nativeDrawImage(checkAndGetDrawImageArgument(image), dx, dy, dWidth);
	}

	/**
	 * Draws an image onto the canvas.
	 * 
	 * @param image image instance to draw into the context
	 * @param dx the x-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 * @param dy the y-axis coordinate in the destination canvas at which to place the top-left corner of the source image
	 */
	@JsOverlay
	public final void drawImage(Img image, double dx, double dy) {
		nativeDrawImage(checkAndGetDrawImageArgument(image), dx, dy);
	}

	/**
	 * Checks if the object passed as argument is consistent.<br>
	 * If not, throws a {@link IllegalArgumentException}.
	 * 
	 * @param object object to check
	 * @param <T> type of drawable object, image or canvas
	 * @return the instance of the object passed as argument
	 */
	@JsOverlay
	private final <T> T checkAndGetDrawImageArgument(T object) {
		// checks if argument is consistent
		if (object == null) {
			// if here, argument is null
			throw new IllegalArgumentException("Object to draw is null");
		}
		// returns the argument
		return object;
	}

	/**
	 * Fills the current or given path with the current fill style.
	 */
	@JsMethod
	public native void fill();

	/**
	 * Draws a rectangle that is filled according to the current fill style.<br>
	 * This method draws directly to the canvas without modifying the current path, so any subsequent fill or stroke calls will have no effect on it.
	 *
	 * @param x the x-axis coordinate of the rectangle's starting point
	 * @param y the y-axis coordinate of the rectangle's starting point
	 * @param width the rectangle's width. Positive values are to the right, and negative to the left
	 * @param height the rectangle's height. Positive values are down, and negative are up
	 */
	@JsMethod
	public native void fillRect(double x, double y, double width, double height);

	/**
	 * Draws a text string at the specified coordinates, filling the string's characters with the current fill style.<br>
	 * An parameter allows specifying a maximum width for the rendered text, which the user agent will achieve by condensing the text or by using a lower font size.
	 *
	 * @param text the string to render into the context.<br>
	 *            The text is rendered using the settings specified by font, text align, text baseline, and direction.
	 * @param x the x-axis coordinate of the point at which to begin drawing the text, in pixels
	 * @param y the y-axis coordinate of the point at which to begin drawing the text, in pixels
	 * @param maxWidth the maximum number of pixels wide the text may be once rendered.<br>
	 *            If not specified, there is no limit to the width of the text
	 */
	@JsMethod
	public native void fillText(String text, double x, double y, double maxWidth);

	/**
	 * Draws a text string at the specified coordinates, filling the string's characters with the current fill style.
	 *
	 * @param text the string to render into the context.<br>
	 *            The text is rendered using the settings specified by font, text align, text baseline, and direction.
	 * @param x the x-axis coordinate of the point at which to begin drawing the text, in pixels
	 * @param y the y-axis coordinate of the point at which to begin drawing the text, in pixels
	 */
	@JsMethod
	public native void fillText(String text, double x, double y);

	/**
	 * adds a straight line to the current sub-path by connecting the sub-path's last point to the specified (x, y) coordinates.
	 *
	 * @param x the x-axis coordinate of the line's end point
	 * @param y the y-axis coordinate of the line's end point
	 */
	@JsMethod
	public native void lineTo(double x, double y);

	/**
	 * Returns a text metric item that contains information about the measured text.
	 *
	 * @param text the text to measure
	 * @return a text metric item
	 */
	@JsMethod
	public native TextMetricsItem measureText(String text);

	/**
	 * Begins a new sub-path at the point specified by the given (x, y) coordinates.
	 *
	 * @param x the x-axis (horizontal) coordinate of the point
	 * @param y the y-axis (vertical) coordinate of the point
	 */
	@JsMethod
	public native void moveTo(double x, double y);

	/**
	 * Adds a quadratic B\u00e9zier curve to the current sub-path.<br>
	 * It requires two points: the first one is a control point and the second one is the end point.<br>
	 * The starting point is the latest point in the current path.
	 *
	 * @param cpx the x-axis coordinate of the control point
	 * @param cpy the y-axis coordinate of the control point
	 * @param x the x-axis coordinate of the end point
	 * @param y the y-axis coordinate of the end point
	 */
	@JsMethod
	public native void quadraticCurveTo(double cpx, double cpy, double x, double y);

	/**
	 * Adds a rectangle to the current path.
	 *
	 * @param x the x-axis coordinate of the rectangle's starting point
	 * @param y the y-axis coordinate of the rectangle's starting point
	 * @param width the rectangle's width. Positive values are to the right, and negative to the left
	 * @param height the rectangle's height. Positive values are down, and negative are up
	 */
	@JsMethod
	public native void rect(double x, double y, double width, double height);

	/**
	 * Restores the most recently saved canvas state by popping the top entry in the drawing state stack.<br>
	 * If there is no saved state, this method does nothing.
	 */
	@JsMethod
	public native void restore();

	/**
	 * Adds a rotation to the transformation matrix.
	 *
	 * @param angle the rotation angle, clockwise in radians.<br>
	 *            You can use <code>degree * Math.PI / 180</code> to calculate a radian from a degree.
	 */
	@JsMethod
	public native void rotate(double angle);

	/**
	 * Saves the entire state of the canvas by pushing the current state onto a stack.
	 */
	@JsMethod
	public native void save();

	/**
	 * Adds a scaling transformation to the canvas units horizontally and/or vertically.
	 *
	 * @param x scaling factor in the horizontal direction.<br>
	 *            A negative value flips pixels across the vertical axis.<br>
	 *            A value of 1 results in no horizontal scaling
	 * @param y scaling factor in the vertical direction.<br>
	 *            A negative value flips pixels across the horizontal axis.<br>
	 *            A value of 1 results in no vertical scaling
	 */
	@JsMethod
	public native void scale(double x, double y);

	/**
	 * Strokes (outlines) the current or given path with the current stroke style.<br>
	 * Strokes are aligned to the center of a path; in other words, half of the stroke is drawn on the inner side, and half on the outer side.
	 */
	@JsMethod
	public native void stroke();

	/**
	 * Draws a rectangle that is stroked (outlined) according to the current strokeStyle and other context settings.
	 *
	 * @param x the x-axis coordinate of the rectangle's starting point
	 * @param y the y-axis coordinate of the rectangle's starting point
	 * @param width the rectangle's width. Positive values are to the right, and negative to the left
	 * @param height the rectangle's height. Positive values are down, and negative are up
	 */
	@JsMethod
	public native void strokeRect(double x, double y, double width, double height);

	/**
	 * Strokes - that is, draws the outlines of - the characters of a text string at the specified coordinates.<br>
	 * An optional parameter allows specifying a maximum width for the rendered text, which the user agent will achieve by condensing the text or by using a lower font size.
	 *
	 * @param text the text specifying the text string to render into the context.<br>
	 *            The text is rendered using the settings specified by font, textAlign, textBaseline, and direction.
	 * @param x the x-axis coordinate of the point at which to begin drawing the text
	 * @param y the y-axis coordinate of the point at which to begin drawing the text
	 * @param maxWidth The maximum width the text may be once rendered.<br>
	 *            If not specified, there is no limit to the width of the text.
	 */
	@JsMethod
	public native void strokeText(String text, double x, double y, double maxWidth);

	/**
	 * Strokes - that is, draws the outlines of - the characters of a text string at the specified coordinates.
	 *
	 * @param text the text specifying the text string to render into the context.<br>
	 *            The text is rendered using the settings specified by font, textAlign, textBaseline, and direction.
	 * @param x the x-axis coordinate of the point at which to begin drawing the text
	 * @param y the y-axis coordinate of the point at which to begin drawing the text
	 */
	@JsMethod
	public native void strokeText(String text, double x, double y);

	/**
	 * Multiplies the current transformation with the matrix described by the arguments of this method.<br>
	 * This lets you scale, rotate, translate (move), and skew the context.
	 *
	 * @param m11 horizontal scaling. A value of 1 results in no scaling
	 * @param m12 vertical skewing
	 * @param m21 horizontal skewing
	 * @param m22 vertical scaling. A value of 1 results in no scaling
	 * @param dx horizontal translation (moving)
	 * @param dy vertical translation (moving)
	 */
	@JsMethod
	public native void transform(double m11, double m12, double m21, double m22, double dx, double dy);

	/**
	 * Adds a translation transformation to the current matrix by moving the canvas and its origin x units horizontally and y units vertically on the grid.
	 *
	 * @param x distance to move in the horizontal direction.<br>
	 *            Positive values are to the right, and negative to the left
	 * @param y distance to move in the vertical direction.<br>
	 *            Positive values are down, and negative are up
	 */
	@JsMethod
	public native void translate(double x, double y);

	/**
	 * Returns the fill or stroke color is used for shapes.
	 * 
	 * @param value value stored inside the context
	 * @return color used to fill or stroke shapes
	 */
	@JsOverlay
	private String getColorAsString(Object value) {
		// checks if value is a string
		if (value instanceof String) {
			// casts and returns the value
			return (String) value;
		} else if (value == null) {
			return HtmlColor.BLACK.toHex();
		}
		// if here is not a string
		// then returns undefined value
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the fill or stroke color is used for shapes.
	 * 
	 * @param value value stored inside the context
	 * @return color used to fill or stroke shapes
	 */
	@JsOverlay
	private IsColor getColor(Object value) {
		// gets the fill color as string
		String valueAsString = getColorAsString(value);
		// checks if value is consistent
		if (valueAsString != null) {
			// transforms as iscolor
			return ColorBuilder.parse(valueAsString);
		}
		// if here is not a string
		// then returns undefined value
		return HtmlColor.BLACK;
	}

	/**
	 * Returns the fill or stroke gradient is used for shapes.
	 * 
	 * @param value value stored inside the context
	 * @return gradient used to fill or stroke shapes
	 */
	@JsOverlay
	private CanvasGradientItem getGradient(Object value) {
		// checks if value is a string
		if (JsHelper.get().isCanvasGradient(value)) {
			// casts and returns the value
			return Js.cast(value);
		}
		// if here is not a string
		// then returns undefined value
		return null;
	}

	/**
	 * Returns the fill or stroke pattern is used for shapes.
	 * 
	 * @param value value stored inside the context
	 * @return pattern used to fill or stroke shapes
	 */
	@JsOverlay
	private final CanvasPatternItem getPattern(Object value) {
		// checks if value is a string
		if (JsHelper.get().isCanvasPattern(value)) {
			// casts and returns the value
			return Js.cast(value);
		}
		// if here is not a string
		// then returns undefined value
		return null;
	}

}
