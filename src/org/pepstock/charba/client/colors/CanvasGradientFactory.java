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
package org.pepstock.charba.client.colors;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.items.ChartAreaNode;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CanvasGradientFactory {

	/**
	 * To avoid any instantiation
	 */
	private CanvasGradientFactory() {
		// do nothing
	}

	public static CanvasGradient createGradient(AbstractChart<?, ?> chart, Gradient gradient) {
		if (chart.isInitialized()) {
			CanvasGradient result = null;
			if (GradientType.linear.equals(gradient.getType())) {
				result = createLinearGradient(chart, gradient);
			} else {
				result = createRadialGradient(chart, gradient);
			}
			if (result != null) {
				for (GradientColor color : gradient.getColors()) {
					result.addColorStop(color.getOffset(), color.getColorAsString());
				}
			}
			return result;
		} else {
			throw new IllegalArgumentException("Chart is not initialized");
		}
	}
	
	private static CanvasGradient createLinearGradient(AbstractChart<?, ?> chart, Gradient gradient) {
		Canvas canvas = chart.getCanvas();
		Context2d context = canvas.getContext2d();
		double x0 = 0;
        double y0 = 0;
        double x1 = 0;
        double y1 = 0;
        
        final double top;
        final double bottom;
        final double left;
        final double right;
        if (GradientScope.canvas.equals(gradient.getScope())) {
        	top = 0;
        	left = 0;
        	right = canvas.getOffsetWidth();
        	bottom = canvas.getOffsetHeight();
        } else {
        	ChartAreaNode chartArea = chart.getNode().getChartArea();
        	top = chartArea.getTop();
        	left = chartArea.getLeft();
        	bottom = chartArea.getBottom();
        	right = chartArea.getRight();
        }
        
        if (GradientOrientation.topDown.equals(gradient.getOrientation())){
        	x0 = left;
        	y0 = top;
        	x1 = left;
        	y1 = bottom;
        } else if (GradientOrientation.bottomUp.equals(gradient.getOrientation())){
        	x0 = left;
        	y0 = bottom;
        	x1 = left;
        	y1 = top;
        } else if (GradientOrientation.leftRight.equals(gradient.getOrientation())){
        	x0 = left;
        	y0 = top;
        	x1 = right;
        	y1 = top;
        } else if (GradientOrientation.rightLeft.equals(gradient.getOrientation())){
        	x0 = right;
        	y0 = top;
        	x1 = left;
        	y1 = top;
        } else if (GradientOrientation.topRight.equals(gradient.getOrientation())){
        	x0 = left;
        	y0 = top;
        	x1 = right;
        	y1 = bottom;
        } else if (GradientOrientation.bottomLeft.equals(gradient.getOrientation())){
        	x0 = right;
        	y0 = bottom;
        	x1 = left;
        	y1 = top;
        } else if (GradientOrientation.topLeft.equals(gradient.getOrientation())){
        	x0 = right;
        	y0 = top;
        	x1 = left;
        	y1 = bottom;
        } else if (GradientOrientation.bottomRight.equals(gradient.getOrientation())){
        	x0 = left;
        	y0 = bottom;
        	x1 = right;
        	y1 = top;
        } else {
        	throw new IllegalArgumentException("Gradient orientation is wrong ["+gradient.getOrientation()+"]");
        }
        return context.createLinearGradient(x0, y0, x1, y1);
	}

	private static CanvasGradient createRadialGradient(AbstractChart<?, ?> chart, Gradient gradient) {
		Canvas canvas = chart.getCanvas();
		Context2d context = canvas.getContext2d();
		double x0 = 0;
        double y0 = 0;
        double r0 = 0;

        double x1 = 0;
        double y1 = 0;
        double r1 = 0;
        
        final double centerX;
        final double centerY;
        final double radius;
        if (GradientScope.canvas.equals(gradient.getScope())) {
        	centerX = (canvas.getOffsetWidth() / 2);
        	centerY = (canvas.getOffsetHeight() / 2);
        	radius = (Math.max(canvas.getOffsetWidth(), canvas.getOffsetHeight()) / 2);
        } else {
        	ChartAreaNode chartArea = chart.getNode().getChartArea();
        	centerX = ((chartArea.getRight() - chartArea.getLeft()) / 2) + chartArea.getLeft();
        	centerY = ((chartArea.getBottom() - chartArea.getTop()) / 2) + chartArea.getTop();
        	radius = (Math.max((chartArea.getRight() - chartArea.getLeft()), (chartArea.getBottom() - chartArea.getTop())) / 2);
        }
        
        if (GradientOrientation.inOut.equals(gradient.getOrientation())){
        	x0 = centerX;
        	y0 = centerY;
        	r0 = 0;
        	x1 = centerX;
        	y1 = centerY;
        	r1 = radius;
        } else if (GradientOrientation.outIn.equals(gradient.getOrientation())){
        	x0 = centerX;
        	y0 = centerY;
        	r0 = radius;
        	x1 = centerX;
        	y1 = centerY;
        	r1 = 0;
        } else {
        	throw new IllegalArgumentException("Gradient orientation is wrong ["+gradient.getOrientation()+"]");
        }
        return context.createRadialGradient(x0, y0, r0, x1, y1, r1);
	}

}
