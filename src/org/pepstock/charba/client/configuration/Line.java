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
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.CubicInterpolationMode;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.options.ExtendedOptions;

/**
 * Line elements are used to represent the line in a line chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Line extends AbstractConfigurationElement {

	/**
	 * Builds the object with options, root and setting the line element.
	 * 
	 * @param options options instance
	 */
	Line(ExtendedOptions options) {
		super(options, options.getElements().getLine());
	}

	/**
	 * Returns the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 * 
	 * @param tension the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 */
	public void setTension(double tension) {
		getOptions().getElements().getLine().setTension(tension);
	}

	/**
	 * Returns the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 * 
	 * @return the B\u00e9zier curve tension (0 for no B\u00e9zier curves).
	 */
	public double getTension() {
		return getOptions().getElements().getLine().getTension();
	}

	/**
	 * Sets how the end points of every line are drawn. 
	 *  
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	public void setBorderCapStyle(CapStyle borderCapStyle) {
		getOptions().getElements().getLine().setBorderCapStyle(borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	public CapStyle getBorderCapStyle() {
		return getOptions().getElements().getLine().getBorderCapStyle();
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setBorderDash(int... borderDash) {
		getOptions().getElements().getLine().setBorderDash(borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Integer> getBorderDash() {
		return getOptions().getElements().getLine().getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset the line dash pattern offset.
	 */
	public void setBorderDashOffset(int borderDashOffset) {
		getOptions().getElements().getLine().setBorderDashOffset(borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset
	 */
	public int getBorderDashOffset() {
		return getOptions().getElements().getLine().getBorderDashOffset();
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped).
	 * 
	 * @param borderJoinStyle how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		getOptions().getElements().getLine().setBorderJoinStyle(borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together 
	 */
	public JoinStyle getBorderJoinStyle() {
		return getOptions().getElements().getLine().getBorderJoinStyle();
	}

	/**
	 * Sets <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @param capBezierPoints <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 */
	public void setCapBezierPoints(boolean capBezierPoints) {
		getOptions().getElements().getLine().setCapBezierPoints(capBezierPoints);
	}

	/**
	 * Returns <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 * 
	 * @return <code>true</code> to keep B\u00e9zier control inside the chart, <code>false</code> for no restriction.
	 */
	public boolean isCapBezierPoints() {
		return getOptions().getElements().getLine().isCapBezierPoints();
	}

	/**
	 * Sets algorithm used to interpolate a smooth curve from the discrete data points.<br>
	 * The following interpolation modes are supported:<br>
	 * <br>
	 * 
	 * <pre>
	 * 'default'
	 * 'monotone'
	 * </pre>
	 * 
	 * <br>
	 * The 'default' algorithm uses a custom weighted cubic interpolation, which produces pleasant curves for all types of datasets.<br>
	 * The 'monotone' algorithm is more suited to y = f(x) datasets : it preserves monotonicity (or piecewise monotonicity) of the dataset being interpolated, and ensures local
	 * extremums (if any) stay at input data points.
	 * 
	 * @param mode algorithm used to interpolate a smooth curve from the discrete data points
	 */
	public void setCubicInterpolationMode(CubicInterpolationMode mode) {
		getOptions().getElements().getLine().setCubicInterpolationMode(mode);
	}

	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * 
	 * @return algorithm used to interpolate a smooth curve from the discrete data points.
	 */
	public CubicInterpolationMode getCubicInterpolationMode() {
		return getOptions().getElements().getLine().getCubicInterpolationMode();
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill <code>true</code> to fill, otherwise <code>false</code>.
	 */
	public void setFill(boolean fill) {
		getOptions().getElements().getLine().setFill(fill);
	}

	/**
	 * Sets how to fill the area under the line, by absolute dataset index.
	 * 
	 * @param index absolute dataset index of the chart.
	 */
	public void setFill(int index) {
		getOptions().getElements().getLine().setFill(index);
	}

	/**
	 * Sets how to fill the area under the line, by relative dataset index.
	 * 
	 * @param index relative dataset index of the chart.
	 */
	public void setFill(String index) {
		getOptions().getElements().getLine().setFill(index);
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	public void setFill(IsFill fill) {
		getOptions().getElements().getLine().setFill(fill);
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	public IsFill getFill() {
		return getOptions().getElements().getLine().getFill();
	}

	/**
	 * Sets <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @param stepped <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public void setStepped(boolean stepped) {
		getOptions().getElements().getLine().setStepped(stepped);
	}

	/**
	 * Returns <code>true</code> to show the line as a stepped line (tension will be ignored).
	 * 
	 * @return <code>true</code> to show the line as a stepped line (tension will be ignored).
	 */
	public boolean isStepped() {
		return getOptions().getElements().getLine().isStepped();
	}

}