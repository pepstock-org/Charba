package org.pepstock.charba.client.jsinterop.options.elements.line;

import org.pepstock.charba.client.jsinterop.commons.ArrayInteger;
import org.pepstock.charba.client.jsinterop.options.elements.arc.NativeArc;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Line elements are used to represent the line in a line chart.<br>
 * While chart types provide settings to configure the styling of each dataset, you sometimes want to style all datasets the
 * same way.<br>
 * Options can be configured for four different types of elements: arc, lines, points, and rectangles.<br>
 * When set, these options apply to all objects of that type unless specifically overridden by the configuration attached to a
 * dataset.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public final class NativeLine extends NativeArc {
	
	/**
	 * 
	 */
	protected NativeLine() {
	}

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @param tension the Bezier curve tension (0 for no Bezier curves).
	 */
	@JsProperty
	public native void setTension(double tension);

	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @return the Bezier curve tension (0 for no Bezier curves). Default is 0.4F.
	 */
	@JsProperty
	public native double getTension();

	/**
	 * Sets how the end points of every line are drawn. There are three possible values for this property and those are: butt,
	 * round and square.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	@JsProperty
	public native void setBorderCapStyle(String borderCapStyle);

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn. Default is {@link org.pepstock.charba.client.enums.CapStyle#butt}.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	@JsProperty
	public native String getBorderCapStyle();

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines
	 * and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating
	 *            lengths of lines and gaps which describe the pattern.
	 */
	@JsProperty
	public native void setBorderDash(ArrayInteger borderDash);

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 * lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of
	 *         lines and gaps which describe the pattern.
	 */
	@JsProperty
	public native ArrayInteger getBorderDash();

	/**
	 * Sets the line dash pattern offset or "phase".
	 * 
	 * @param borderDashOffset the line dash pattern offset or "phase".
	 */
	@JsProperty
	public native void setBorderDashOffset(int borderDashOffset);

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase". Default is 0.
	 */
	@JsProperty
	public native int getBorderDashOffset();

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderJoinStyle There are three possible values for this property: round, bevel and miter. By default this
	 *            property is set to miter.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	@JsProperty
	public native void setBorderJoinStyle(String borderJoinStyle);

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 * (degenerate segments with zero lengths, whose specified endpoints and control points are exactly at the same position,
	 * are skipped).<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter. By default this property is set to
	 *         miter. Default is {@link org.pepstock.charba.client.enums.JoinStyle#miter}.
	 * @see org.pepstock.charba.client.enums.JoinStyle
	 */
	@JsProperty
	public native String getBorderJoinStyle();

	/**
	 * Sets <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @param capBezierPoints <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 */
	@JsProperty
	public native void setCapBezierPoints(boolean capBezierPoints);

	/**
	 * Returns <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction. Default is <code>true</code>.
	 */
	@JsProperty
	public native boolean isCapBezierPoints();

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	@JsProperty
	public native void setFill(Object fill);

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line. Default is {@link org.pepstock.charba.client.enums.Fill#origin}.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	@JsProperty
	public native Object getFill();
	/**
	 * Sets <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @param stepped <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	@JsProperty
	public native void setStepped(boolean stepped);

	/**
	 * Returns <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @return <code>true</code> to show the line as a stepped line (tension will be ignored). Default is <code>false</code>.
	 */
	@JsProperty
	public native boolean isStepped();
}
