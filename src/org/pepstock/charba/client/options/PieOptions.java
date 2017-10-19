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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.commons.Key;

/**
 * 
 */
public class PieOptions extends BaseOptions{
	
	private static final double DEFAULT_CUTOUT_PERCENTAGE = 0D;
	
	private static final double DEFAULT_ROTATION = -0.5 * Math.PI;
	
	private static final double DEFAULT_CIRCUMFERENCE = 2 * Math.PI;

	private enum Property implements Key {
		cutoutPercentage,
		rotation,
		circumference
	}
	
	public PieOptions(AbstractChart<?, ?> chart) {
		super(chart);
		setCutoutPercentage(0);
		getAnimation().setAnimateRotate(true);
		getAnimation().setAnimateScale(true);
	}
	
//	cutoutPercentage Number 50 - for doughnut, 0 - for pie The percentage of the chart that is cut out of the middle.
//	rotation Number -0.5 * Math.PI Starting angle to draw arcs from.
//	circumference Number 2 * Math.PI Sweep to allow arcs to cover
//	animation.animateRotate Boolean true If true, the chart will animate in with a rotation animation. This property is in the options.animation object.
//	animation.animateScale Boolean false If true, will animate scaling the chart from the center outwards.
	
	public void setCutoutPercentage(double cutoutPercentage){
		  setValue(Property.cutoutPercentage, cutoutPercentage);
	}

	public double getCutoutPercentage(){
		  return getValue(Property.cutoutPercentage, DEFAULT_CUTOUT_PERCENTAGE);
	}

	public void setRotation(double rotation){
		  setValue(Property.rotation, rotation);
	}

	public double getRotation(){
		  return getValue(Property.rotation, DEFAULT_ROTATION);
	}

	public void setCircumference(double circumference){
		  setValue(Property.circumference, circumference);
	}

	public double getCircumference(){
		  return getValue(Property.circumference, DEFAULT_CIRCUMFERENCE);
	}

}