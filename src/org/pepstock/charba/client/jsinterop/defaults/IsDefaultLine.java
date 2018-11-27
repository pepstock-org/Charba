package org.pepstock.charba.client.jsinterop.defaults;

import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.JoinStyle;

public interface IsDefaultLine extends IsDefaultArc{
	
	/**
	 * Returns the Bezier curve tension (0 for no Bezier curves).
	 * 
	 * @return the Bezier curve tension (0 for no Bezier curves). Default is 0.4F.
	 */
	double getTension();

	/**
	 * Returns how the end points of every line are drawn. There are three possible values for this property and those are:
	 * butt, round and square. By default this property is set to butt.
	 * 
	 * @return how the end points of every line are drawn. Default is {@link org.pepstock.charba.client.enums.CapStyle#butt}.
	 * @see org.pepstock.charba.client.enums.CapStyle
	 */
	CapStyle getBorderCapStyle();

	/**
	 * Returns the line dash pattern offset or "phase".
	 * 
	 * @return the line dash pattern offset or "phase". Default is 0.
	 */
	int getBorderDashOffset();

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
	JoinStyle getBorderJoinStyle();

	/**
	 * Returns <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep Bezier control inside the chart, <code>false</code> for no restriction. Default is <code>true</code>.
	 */
	boolean isCapBezierPoints();

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line. Default is {@link org.pepstock.charba.client.enums.Fill#origin}.
	 * @see org.pepstock.charba.client.enums.Fill
	 */
	Fill getFill();

	/**
	 * Returns <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @return <code>true</code> to show the line as a stepped line (tension will be ignored). Default is <code>false</code>.
	 */
	boolean isStepped();

}
