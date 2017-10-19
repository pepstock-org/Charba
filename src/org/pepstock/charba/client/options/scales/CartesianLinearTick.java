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
package org.pepstock.charba.client.options.scales;

import org.pepstock.charba.client.commons.Key;

/**
 * 
 */
public final class CartesianLinearTick extends CartesianTick{
	
	private static final boolean DEFAULT_BEGIN_AT_ZERO = false;

	private static final double DEFAULT_MIN = Double.MIN_VALUE;
	
	private static final double DEFAULT_MAX = Double.MAX_VALUE;
	
	private static final int DEFAULT_MAX_TICKS_LIMIT = 11;
	
	private static final double DEFAULT_STEP_SIZE = Double.MIN_VALUE;
	
	private static final double DEFAULT_SUGGESTED_MAX = Double.MAX_VALUE;
	
	private static final double DEFAULT_SUGGESTED_MIN = Double.MIN_VALUE;
	
	private enum Property implements Key {
		beginAtZero,
		min,
		max,
		maxTicksLimit,
		stepSize,
		suggestedMax,
		suggestedMin
	}
	
	
	
//	beginAtZero Boolean  if true, scale will include 0 if it is not already included.
//	min Number  User defined minimum number for the scale, overrides minimum value from data. more...
//	max Number  User defined maximum number for the scale, overrides maximum value from data. more...
//	maxTicksLimit Number 11 Maximum number of ticks and gridlines to show.
//	stepSize Number  User defined fixed step size for the scale. more...
//	suggestedMax Number  Adjustment used when calculating the maximum data value. more...
//	suggestedMin Number  Adjustment used when calculating the minimum data value. more...
	
	CartesianLinearTick() {
	}

	public void setBeginAtZero(boolean beginAtZero){
		  setValue(Property.beginAtZero, beginAtZero);
	}

	public boolean isBeginAtZero(){
		  return getValue(Property.beginAtZero, DEFAULT_BEGIN_AT_ZERO);
	}

	public void setMin(double min){
		  setValue(Property.min, min);
	}

	public double getMin(){
		  return getValue(Property.min, DEFAULT_MIN);
	}

	public void setMax(double max){
		  setValue(Property.max, max);
	}

	public double getMax(){
		  return getValue(Property.max, DEFAULT_MAX);
	}

	public void setMaxTicksLimit(int maxTicksLimit){
		  setValue(Property.maxTicksLimit, maxTicksLimit);
	}

	public int getMaxTicksLimit(){
		  return getValue(Property.maxTicksLimit, DEFAULT_MAX_TICKS_LIMIT);
	}

	public void setStepSize(double stepSize){
		  setValue(Property.stepSize, stepSize);
	}

	public double getStepSize(){
		  return getValue(Property.stepSize, DEFAULT_STEP_SIZE);
	}

	public void setSuggestedMax(double suggestedMax){
		  setValue(Property.suggestedMax, suggestedMax);
	}

	public double getSuggestedMax(){
		  return getValue(Property.suggestedMax, DEFAULT_SUGGESTED_MAX);
	}

	public void setSuggestedMin(double suggestedMin){
		  setValue(Property.suggestedMin, suggestedMin);
	}

	public double getSuggestedMin(){
		  return getValue(Property.suggestedMin, DEFAULT_SUGGESTED_MIN);
	}
	
}