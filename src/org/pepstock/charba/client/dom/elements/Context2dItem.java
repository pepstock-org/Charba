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

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.dom.BaseHtmlElement;
import org.pepstock.charba.client.dom.enums.ElementRepetition;
import org.pepstock.charba.client.dom.enums.ElementTextAlign;
import org.pepstock.charba.client.dom.enums.ElementTextBaseline;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class Context2dItem extends BaseHtmlElement {

	@JsType(isNative = true, name = "?", namespace = JsPackage.GLOBAL)
	public interface StyleType {
		@JsOverlay
		static StyleType of(Object o) {
			return Js.cast(o);
		}

		@JsOverlay
		default CanvasGradientItem asCanvasGradient() {
			return Js.cast(this);
		}

		@JsOverlay
		default CanvasPatternItem asCanvasPattern() {
			return Js.cast(this);
		}

		@JsOverlay
		default String asString() {
			return Js.asString(this);
		}
	}

	@JsType(isNative = true, name = "?", namespace = JsPackage.GLOBAL)
	public interface PatternImageElementType {
		@JsOverlay
		static PatternImageElementType of(Object o) {
			return Js.cast(o);
		}
	}

	@JsType(isNative = true, name = "?", namespace = JsPackage.GLOBAL)
	public interface DrawImageElementType {
		@JsOverlay
		static PatternImageElementType of(Object o) {
			return Js.cast(o);
		}
	}

	/**
	 * To avoid any instantiation
	 */
	Context2dItem() {
	}

	/**
	 * Returns the 'canvas' property of the canvas rendering context 2D object.
	 *
	 * @return the 'canvas' property value
	 */
	@JsProperty
	public native CanvasElement getCanvas();

	/**
	 * Sets the 'fillStyle' property of the canvas rendering context 2D object.
	 *
	 * @param fillStyle the 'fillStyle' property to set
	 */
	@JsProperty(name = "fillStyle")
	private native void nativeSetFillStyle(Object fillStyle);

	@JsOverlay
	public void setFillColor(String color) {
		if (color != null) {
			nativeSetFillStyle(StyleType.of(color));
		}
	}

	@JsOverlay
	public void setFillColor(IsColor color) {
		if (IsColor.isValid(color)) {
			setFillColor(color.toRGBA());
		}
	}

	@JsOverlay
	public void setFillGradient(CanvasGradientItem gradient) {
		if (gradient != null) {
			nativeSetFillStyle(StyleType.of(gradient));
		}
	}

	@JsOverlay
	public final void setFillPattern(CanvasPatternItem pattern) {
		if (pattern != null) {
			nativeSetFillStyle(StyleType.of(pattern));
		}
	}

	/**
	 * Returns the 'font' property of the canvas rendering context 2D object.
	 *
	 * @return the 'font' property value
	 */
	@JsProperty
	public native String getFont();

	/**
	 * Sets the 'font' property of the canvas rendering context 2D object.
	 *
	 * @param font the 'font' property to set
	 */
	@JsProperty
	public native void setFont(String font);

	/**
	 * Returns the 'lineCap' property of the canvas rendering context 2D object.
	 *
	 * @return the 'lineCap' property value
	 */
	@JsProperty(name = "lineCap")
	private native String nativeGetLineCap();

	/**
	 * Sets the 'lineCap' property of the canvas rendering context 2D object.
	 *
	 * @param lineCap the 'lineCap' property to set
	 */
	@JsProperty(name = "lineCap")
	private native void nativeSetLineCap(String lineCap);

	@JsOverlay
	public void setLineCap(CapStyle lineCap) {
		if (Key.isValid(lineCap)) {
			nativeSetLineCap(lineCap.value());
		}
	}

	@JsOverlay
	public CapStyle getLineCap() {
		return Key.getKeyByValue(CapStyle.class, nativeGetLineCap(), CapStyle.BUTT);
	}

	/**
	 * Returns the 'lineDashOffset' property of the canvas rendering context 2D object.
	 *
	 * @return the 'lineDashOffset' property value
	 */
	@JsProperty
	public native int getLineDashOffset();

	/**
	 * Sets the 'lineDashOffset' property of the canvas rendering context 2D object.
	 *
	 * @param lineDashOffset the 'lineDashOffset' property to set
	 */
	@JsProperty
	public native void setLineDashOffset(int lineDashOffset);

	/**
	 * Returns the 'lineJoin' property of the canvas rendering context 2D object.
	 *
	 * @return the 'lineJoin' property value
	 */
	@JsProperty(name = "lineJoin")
	private native String nativeGetLineJoin();

	/**
	 * Sets the 'lineJoin' property of the canvas rendering context 2D object.
	 *
	 * @param lineJoin the 'lineJoin' property to set
	 */
	@JsProperty(name = "lineJoin")
	private native void nativeSetLineJoin(String lineJoin);

	@JsOverlay
	public void setLineJoin(JoinStyle lineJoin) {
		if (Key.isValid(lineJoin)) {
			nativeSetLineJoin(lineJoin.value());
		}
	}

	@JsOverlay
	public JoinStyle getLineJoin() {
		return Key.getKeyByValue(JoinStyle.class, nativeGetLineJoin(), JoinStyle.BEVEL);
	}

	/**
	 * Returns the 'lineWidth' property of the canvas rendering context 2D object.
	 *
	 * @return the 'lineWidth' property value
	 */
	@JsProperty
	public native double getLineWidth();

	/**
	 * Sets the 'lineWidth' property of the canvas rendering context 2D object.
	 *
	 * @param lineWidth the 'lineWidth' property to set
	 */
	@JsProperty
	public native void setLineWidth(double lineWidth);

	/**
	 * Returns the 'miterLimit' property of the canvas rendering context 2D object.
	 *
	 * @return the 'miterLimit' property value
	 */
	@JsProperty
	public native double getMiterLimit();

	/**
	 * Sets the 'miterLimit' property of the canvas rendering context 2D object.
	 *
	 * @param miterLimit the 'miterLimit' property to set
	 */
	@JsProperty
	public native void setMiterLimit(double miterLimit);

	/**
	 * Returns the 'shadowBlur' property of the canvas rendering context 2D object.
	 *
	 * @return the 'shadowBlur' property value
	 */
	@JsProperty
	public native double getShadowBlur();

	/**
	 * Sets the 'shadowBlur' property of the canvas rendering context 2D object.
	 *
	 * @param shadowBlur the 'shadowBlur' property to set
	 */
	@JsProperty
	public native void setShadowBlur(double shadowBlur);

	/**
	 * Returns the 'shadowColor' property of the canvas rendering context 2D object.
	 *
	 * @return the 'shadowColor' property value
	 */
	@JsProperty
	public native String getShadowColor();

	/**
	 * Sets the 'shadowColor' property of the canvas rendering context 2D object.
	 *
	 * @param shadowColor the 'shadowColor' property to set
	 */
	@JsProperty
	public native void setShadowColor(String shadowColor);

	/**
	 * Returns the 'shadowOffsetX' property of the canvas rendering context 2D object.
	 *
	 * @return the 'shadowOffsetX' property value
	 */
	@JsProperty
	public native double getShadowOffsetX();

	/**
	 * Sets the 'shadowOffsetX' property of the canvas rendering context 2D object.
	 *
	 * @param shadowOffsetX the 'shadowOffsetX' property to set
	 */
	@JsProperty
	public native void setShadowOffsetX(double shadowOffsetX);

	/**
	 * Returns the 'shadowOffsetY' property of the canvas rendering context 2D object.
	 *
	 * @return the 'shadowOffsetY' property value
	 */
	@JsProperty
	public native double getShadowOffsetY();

	/**
	 * Sets the 'shadowOffsetY' property of the canvas rendering context 2D object.
	 *
	 * @param shadowOffsetY the 'shadowOffsetY' property to set
	 */
	@JsProperty
	public native void setShadowOffsetY(double shadowOffsetY);

	/**
	 * Sets the 'strokeStyle' property of the canvas rendering context 2D object.
	 *
	 * @param strokeStyle the 'strokeStyle' property to set
	 */
	@JsProperty(name = "strokeStyle")
	private native void nativeSetStrokeStyle(StyleType strokeStyle);

	@JsOverlay
	public void setStrokeColor(String color) {
		if (color != null) {
			nativeSetStrokeStyle(StyleType.of(color));
		}
	}

	@JsOverlay
	public void setStrokeColor(IsColor color) {
		if (IsColor.isValid(color)) {
			setStrokeColor(color.toRGBA());
		}
	}

	@JsOverlay
	public void setStrokeGradient(CanvasGradientItem gradient) {
		if (gradient != null) {
			nativeSetStrokeStyle(StyleType.of(gradient));
		}
	}

	@JsOverlay
	public final void setStrokePattern(CanvasPatternItem pattern) {
		if (pattern != null) {
			nativeSetStrokeStyle(StyleType.of(pattern));
		}
	}

	/**
	 * Returns the 'textAlign' property of the canvas rendering context 2D object.
	 *
	 * @return the 'textAlign' property value
	 */
	@JsProperty(name = "textAlign")
	private native String nativeGetTextAlign();

	/**
	 * Sets the 'textAlign' property of the canvas rendering context 2D object.
	 *
	 * @param textAlign the 'textAlign' property to set
	 */
	@JsProperty(name = "textAlign")
	private native void nativeSetTextAlign(String textAlign);

	@JsOverlay
	public void setTextAlign(ElementTextAlign textAlign) {
		if (Key.isValid(textAlign)) {
			nativeSetTextAlign(textAlign.value());
		}
	}

	@JsOverlay
	public ElementTextAlign getTextAlign() {
		return Key.getKeyByValue(ElementTextAlign.class, nativeGetTextAlign(), ElementTextAlign.START);
	}

	/**
	 * Returns the 'textBaseline' property of the canvas rendering context 2D object.
	 *
	 * @return the 'textBaseline' property value
	 */
	@JsProperty(name = "textBaseline")
	private native String nativeGetTextBaseline();

	/**
	 * Sets the 'textBaseline' property of the canvas rendering context 2D object.
	 *
	 * @param textBaseline the 'textBaseline' property to set
	 */
	@JsProperty(name = "textBaseline")
	private native void nativeSetTextBaseline(String textBaseline);

	@JsOverlay
	public void setTextBaseline(ElementTextBaseline textBaseline) {
		if (Key.isValid(textBaseline)) {
			nativeSetTextBaseline(textBaseline.value());
		}
	}

	@JsOverlay
	public ElementTextBaseline getTextBaseline() {
		return Key.getKeyByValue(ElementTextBaseline.class, nativeGetTextBaseline(), ElementTextBaseline.ALPHABETIC);
	}

	/**
	 * Draws an arc. If a current subpath exists, a line segment is added from the current point to the starting point of the
	 * arc. If {@code anticlockwise} is false and {@code endAngle - startAngle} is equal to or greater than {@code
	 * 2 * Math.PI}, or if {@code anticlockwise} is {@code true} and {@code
	 * startAngle - endAngle} is equal to or greater than {@code 2 * Math.PI}, then the arc is the whole circumference of the
	 * circle.
	 *
	 * @param x the x coordinate of the center of the arc
	 * @param y the y coordinate of the center of the arc
	 * @param radius the radius of the arc
	 * @param startAngle the start angle, measured in radians clockwise from the positive x-axis
	 * @param endAngle the end angle, measured in radians clockwise from the positive x-axis
	 * @param anticlockwise if {@code true}, the arc is drawn in an anticlockwise direction
	 */
	@JsMethod
	public native void arc(double x, double y, double radius, double startAngle, double endAngle, boolean anticlockwise);

	/**
	 * Draws an arc. If a current subpath exists, a line segment is added from the current point to the starting point of the
	 * arc. If {@code endAngle -
	 * startAngle} is equal to or greater than {@code 2 * Math.Pi}, the arc is the whole circumference of the circle.
	 *
	 * @param x the x coordinate of the center of the arc
	 * @param y the y coordinate of the center of the arc
	 * @param radius the radius of the arc
	 * @param startAngle the start angle, measured in radians clockwise from the positive x-axis
	 * @param endAngle the end angle, measured in radians clockwise from the positive x-axis
	 */
	@JsMethod
	public native void arc(double x, double y, double radius, double startAngle, double endAngle);

	/**
	 * Adds an arc to the current subpath, connecting it to the current point with a line segment.
	 *
	 * @param x1 the x coordinate of the starting point of the arc
	 * @param y1 the y coordinate of the starting point of the arc
	 * @param x2 the x coordinate of the ending point of the arc
	 * @param y2 the y coordinate of the ending point of the arc
	 * @param radius the radius of a circle containing the arc
	 */
	@JsMethod
	public native void arcTo(double x1, double y1, double x2, double y2, double radius);

	/**
	 * Begins a new path.
	 */
	@JsMethod
	public native void beginPath();

	/**
	 * Draws a cubic B\u00e9zier curve from the current point to the point (x, y), with control points (cp1x, cp1y) and (cp2x,
	 * cp2y).
	 *
	 * @param cp1x the x coordinate of the first control point
	 * @param cp1y the y coordinate of the first control point
	 * @param cp2x the x coordinate of the second control point
	 * @param cp2y the y coordinate of the second control point
	 * @param x the x coordinate of the end point
	 * @param y the y coordinate of the end point
	 */
	@JsMethod
	public native void bezierCurveTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y);

	/**
	 * Clears a rectangle.
	 *
	 * @param x the x coordinate of the rectangle's upper-left corner
	 * @param y the y coordinate of the rectangle's upper-left corner
	 * @param w the width of the rectangle
	 * @param h the height of the rectangle
	 */
	@JsMethod
	public native void clearRect(double x, double y, double w, double h);

	/**
	 * Creates a new clipping region from the current path.
	 */
	@JsMethod
	public native void clip();

	/**
	 * Closes the current path.
	 */
	@JsMethod
	public native void closePath();

	/**
	 * Creates a linear gradient.
	 *
	 * @param x0 the x coordinate of the starting point of the gradient
	 * @param y0 the y coordinate of the starting point of the gradient
	 * @param x1 the x coordinate of the ending point of the gradient
	 * @param y1 the y coordinate of the ending point of the gradient
	 * @return a canvas gradient object
	 */
	@JsMethod
	public native CanvasGradientItem createLinearGradient(double x0, double y0, double x1, double y1);

	/**
	 * Creates a pattern from another canvas.
	 *
	 * @param image an {@link CanvasElement} object
	 * @param repetition a {@link ElementRepetition} object
	 * @return a {@link CanvasPattern} object
	 */
	// FIXME
	@JsMethod
	private native CanvasPatternItem createPattern(PatternImageElementType image, String repetition);

	/**
	 * Creates a pattern from another canvas.
	 *
	 * @param image an {@link CanvasElement} object
	 * @param repetition a {@link ElementRepetition} object
	 * @return a {@link CanvasPatternItem} object
	 */
	// FIXME
	@JsOverlay
	public final CanvasPatternItem createPattern(CanvasElement image, ElementRepetition repetition) {
		// checks if arguments are consistent
		if (image != null) {
			ElementRepetition checkedRepetition = Key.isValid(repetition) ? repetition : ElementRepetition.NO_REPEAT;
			return createPattern(Js.<PatternImageElementType> uncheckedCast(image), checkedRepetition.value());
		}
		throw new IllegalArgumentException("Canvas element is null");
	}

	/**
	 * Creates a pattern from an image.
	 *
	 * @param image an {@link ImageElement} object
	 * @param repetition a {@link ElementRepetition} object
	 * @return a {@link CanvasPattern} object
	 */
	@JsOverlay
	public final CanvasPatternItem createPattern(ImageElement image, ElementRepetition repetition) {
		// checks if arguments are consistent
		if (image != null) {
			ElementRepetition checkedRepetition = Key.isValid(repetition) ? repetition : ElementRepetition.NO_REPEAT;
			return createPattern(Js.<PatternImageElementType> uncheckedCast(image), checkedRepetition.value());
		}
		throw new IllegalArgumentException("Image element is null");
	}

	/**
	 * Creates a radial gradient.
	 *
	 * @param x0 the x coordinate of the center of the start circle of the gradient
	 * @param y0 the y coordinate of the center of the start circle of the gradient
	 * @param r0 the radius of the start circle of the gradient
	 * @param x1 the x coordinate of the center of the end circle of the gradient
	 * @param y1 the y coordinate of the center of the end circle of the gradient
	 * @param r1 the radius of the end circle of the gradient
	 * @return a {@link CanvasGradient} object
	 */
	@JsMethod
	public native CanvasGradientItem createRadialGradient(double x0, double y0, double r0, double x1, double y1, double r1);

	@JsMethod
	private native void drawImage(DrawImageElementType image, double dx, double dy, double dw, double dh, double sx, double sy, double sw, double sh);

	@JsMethod
	private native void drawImage(DrawImageElementType image, double dx, double dy, double dw, double dh, double sx, double sy, double sw);

	@JsMethod
	private native void drawImage(DrawImageElementType image, double dx, double dy, double dw, double dh, double sx, double sy);

	@JsMethod
	private native void drawImage(DrawImageElementType image, double dx, double dy, double dw, double dh, double sx);

	@JsMethod
	private native void drawImage(DrawImageElementType image, double dx, double dy, double dw, double dh);

	@JsMethod
	private native void drawImage(DrawImageElementType image, double dx, double dy, double dw);

	@JsMethod
	private native void drawImage(DrawImageElementType image, double dx, double dy);

	@JsOverlay
	public final void drawImage(CanvasElement image, double dx, double dy, double dw, double dh, double sx, double sy, double sw, double sh) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw, dh, sx, sy, sw, sh);
	}

	@JsOverlay
	public final void drawImage(CanvasElement image, double dx, double dy, double dw, double dh, double sx, double sy, double sw) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw, dh, sx, sy, sw);
	}

	@JsOverlay
	public final void drawImage(CanvasElement image, double dx, double dy, double dw, double dh, double sx, double sy) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw, dh, sx, sy);
	}

	@JsOverlay
	public final void drawImage(CanvasElement image, double dx, double dy, double dw, double dh, double sx) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw, dh, sx);
	}

	@JsOverlay
	public final void drawImage(CanvasElement image, double dx, double dy, double dw, double dh) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw, dh);
	}

	@JsOverlay
	public final void drawImage(CanvasElement image, double dx, double dy, double dw) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw);
	}

	@JsOverlay
	public final void drawImage(CanvasElement image, double dx, double dy) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy);
	}

	/**
	 * Draws a scaled subset of an image.
	 *
	 * @param image an {@link CanvasElement} object
	 * @param sx the x coordinate of the upper-left corner of the source rectangle
	 * @param sy the y coordinate of the upper-left corner of the source rectangle
	 * @param sw the width of the source rectangle
	 * @param sh the width of the source rectangle
	 * @param dx the x coordinate of the upper-left corner of the destination rectangle
	 * @param dy the y coordinate of the upper-left corner of the destination rectangle
	 * @param dw the width of the destination rectangle
	 * @param dh the height of the destination rectangle
	 */
	@JsOverlay
	public final void drawImage(ImageElement image, double dx, double dy, double dw, double dh, double sx, double sy, double sw, double sh) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw, dh, sx, sy, sw, sh);
	}

	@JsOverlay
	public final void drawImage(ImageElement image, double dx, double dy, double dw, double dh, double sx, double sy, double sw) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw, dh, sx, sy, sw);
	}

	@JsOverlay
	public final void drawImage(ImageElement image, double dx, double dy, double dw, double dh, double sx, double sy) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw, dh, sx, sy);
	}

	@JsOverlay
	public final void drawImage(ImageElement image, double dx, double dy, double dw, double dh, double sx) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw, dh, sx);
	}

	/**
	 * Draws a scaled image.
	 *
	 * @param image an {@link CanvasElement} object
	 * @param dx the x coordinate of the upper-left corner of the destination rectangle
	 * @param dy the y coordinate of the upper-left corner of the destination rectangle
	 * @param dw the width of the destination rectangle
	 */
	@JsOverlay
	public final void drawImage(ImageElement image, double dx, double dy, double dw, double dh) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw, dh);
	}

	/**
	 * Draws an image.
	 *
	 * @param image an {@link CanvasElement} object
	 * @param dx the x coordinate of the upper-left corner of the destination rectangle
	 * @param dy the y coordinate of the upper-left corner of the destination rectangle
	 */
	@JsOverlay
	public final void drawImage(ImageElement image, double dx, double dy, double dw) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy, dw);
	}

	/**
	 * Draws a scaled image.
	 *
	 * @param image an {@link CanvasElement} object
	 * @param dx the x coordinate of the upper-left corner of the destination rectangle
	 * @param dy the y coordinate of the upper-left corner of the destination rectangle
	 * @param dw the width of the destination rectangle
	 * @param dh the height of the destination rectangle
	 */
	@JsOverlay
	public final void drawImage(ImageElement image, double dx, double dy) {
		drawImage(Js.<DrawImageElementType> uncheckedCast(image), dx, dy);
	}

	/**
	 * Fills the current path.
	 */
	@JsMethod
	public native void fill();

	/**
	 * Fills a rectangle.
	 *
	 * @param x the x coordinate of the rectangle's upper-left corner
	 * @param y the y coordinate of the rectangle's upper-left corner
	 * @param w the width of the rectangle
	 * @param h the height of the rectangle
	 */
	@JsMethod
	public native void fillRect(double x, double y, double w, double h);

	/**
	 * Draws filled text squeezed into the given max width.
	 *
	 * @param text the text as a String
	 * @param x the x coordinate of the text position
	 * @param y the y coordinate of the text position
	 * @param maxWidth the maximum width for the text
	 */
	@JsMethod
	public native void fillText(String text, double x, double y, double maxWidth);

	/**
	 * Draws filled text.
	 *
	 * @param text the text as a String
	 * @param x the x coordinate of the text position
	 * @param y the y coordinate of the text position
	 */
	@JsMethod
	public native void fillText(String text, double x, double y);

	@JsMethod
	public native double[] getLineDash();

	/**
	 * Returns true if the given point is in the current path.
	 *
	 * @param x the x coordinate of the point to test.
	 * @param y the y coordinate of the point to test. FIXME
	 * @return {@code true} if the given point is in the current path.
	 */
	@JsMethod
	public native boolean isPointInPath(double x, double y, String fillRule);

	/**
	 * Returns true if the given point is in the current path.
	 *
	 * @param x the x coordinate of the point to test.
	 * @param y the y coordinate of the point to test.
	 * @return {@code true} if the given point is in the current path.
	 */
	@JsMethod
	public native boolean isPointInPath(double x, double y);

	/**
	 * Adds a line from the current point to the point (x, y) to the current path.
	 *
	 * @param x the x coordinate of the line endpoint
	 * @param y the y coordinate of the line endpoint
	 */
	@JsMethod
	public native void lineTo(double x, double y);

	/**
	 * Returns the metrics for the given text.
	 *
	 * @param text the text to measure, as a String
	 * @return a {@link TextMetrics} object
	 */
	@JsMethod
	public native TextMetricsItem measureText(String text);

	/**
	 * Terminates the current path and sets the current path position to the point (x, y).
	 *
	 * @param x the x coordinate of the new position
	 * @param y the y coordinate of the new position
	 */
	@JsMethod
	public native void moveTo(double x, double y);

	/**
	 * Draws a quadratic B\u00e9zier curve from the current point to the point (x, y), with control point (cpx, cpy).
	 *
	 * @param cpx the x coordinate of the control point
	 * @param cpy the y coordinate of the control point
	 * @param x the x coordinate of the end point
	 * @param y the y coordinate of the end point
	 */
	@JsMethod
	public native void quadraticCurveTo(double cpx, double cpy, double x, double y);

	/**
	 * Creates a new rectangular path.
	 *
	 * @param x the x coordinate of the rectangle's upper-left corner
	 * @param y the y coordinate of the rectangle's upper-left corner
	 * @param w the width of the rectangle
	 * @param h the height of the rectangle
	 */
	@JsMethod
	public native void rect(double x, double y, double w, double h);

	/**
	 * Restores the context's state.
	 */
	@JsMethod
	public native void restore();

	/**
	 * Applies rotation to the current transform.
	 *
	 * @param angle the clockwise rotation angle, in radians
	 */
	@JsMethod
	public native void rotate(double angle);

	/**
	 * Saves the context's state.
	 */
	@JsMethod
	public native void save();

	/**
	 * Applies scale to the current transform.
	 *
	 * @param x the scale factor along the x-axis
	 * @param y the scale factor along the y-axis
	 */
	@JsMethod
	public native void scale(double x, double y);

	@JsMethod
	private native void setLineDash(ArrayInteger lineDash);

	@JsOverlay
	public void setLineDash(int... lineDash) {
		setLineDash(ArrayInteger.fromOrEmpty(lineDash));
	}

	@JsOverlay
	public void setLineDash(List<Integer> lineDash) {
		setLineDash(ArrayInteger.fromOrEmpty(lineDash));
	}

	/**
	 * Sets the 2D transformation matrix.
	 *
	 * @param m11 the value at position (1, 1) of the matrix
	 * @param m12 the value at position (1, 2) of the matrix
	 * @param m21 the value at position (2, 1) of the matrix
	 * @param m22 the value at position (2, 2) of the matrix
	 * @param dx the x translation value
	 * @param dy the y translation value
	 */
	@JsMethod
	public native void setTransform(double m11, double m12, double m21, double m22, double dx, double dy);

	/**
	 * Draws the current path with the current stroke style.
	 */
	@JsMethod
	public native void stroke();

	/**
	 * Draws the outline of a rectangle with the current stroke style.
	 *
	 * @param x the x coordinate of the rectangle's upper-left corner
	 * @param y the y coordinate of the rectangle's upper-left corner
	 * @param w the width of the rectangle
	 * @param h the height of the rectangle
	 */
	@JsMethod
	public native void strokeRect(double x, double y, double w, double h);

	/**
	 * Draws the text outline, squeezing the text into the given max width by compressing the font.
	 *
	 * @param text the text as a String
	 * @param x the x coordinate of the text position
	 * @param y the y coordinate of the text position
	 * @param maxWidth the maximum width for the text
	 */
	@JsMethod
	public native void strokeText(String text, double x, double y, double maxWidth);

	/**
	 * Draws the text outline.
	 *
	 * @param text the text as a String
	 * @param x the x coordinate of the text position
	 * @param y the y coordinate of the text position
	 */
	@JsMethod
	public native void strokeText(String text, double x, double y);

	/**
	 * Multiplies the current transform by the given matrix.
	 *
	 * @param m11 the value at position (1, 1) of the matrix
	 * @param m12 the value at position (1, 2) of the matrix
	 * @param m21 the value at position (2, 1) of the matrix
	 * @param m22 the value at position (2, 2) of the matrix
	 * @param dx the x translation value
	 * @param dy the y translation value
	 */
	@JsMethod
	public native void transform(double m11, double m12, double m21, double m22, double dx, double dy);

	/**
	 * Applies a translation to the current transform.
	 *
	 * @param x the amount of translation along the x-axis
	 * @param y the amount of translation along the y-axis
	 */
	@JsMethod
	public native void translate(double x, double y);
}
