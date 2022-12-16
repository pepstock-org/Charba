/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.ml;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.enums.RegressionType;

/**
 * Simple Linear Regression is a type of Regression algorithms that models the relationship between a dependent variable and a single independent variable.<br>
 * The relationship shown by a Simple Linear Regression model is linear or a sloped straight line, hence it is called Simple Linear Regression.<br>
 * The key point in Simple Linear Regression is that the dependent variable must be a continuous/real value.<br>
 * However, the independent variable can be measured on continuous or categorical values.<br>
 * The Simple Linear Regression model can be represented using the below equation:<br>
 * <br>
 * <b style="font: italic bold 24px courier;">y = a<sub>0</sub> + a<sub>1</sub>x</b><br>
 * <br>
 * where<br>
 * <ul>
 * <li><b style="font: italic bold 24px courier;">a<sub>0</sub></b> is the intercept of the Regression line (can be obtained putting x=0)
 * <li><b style="font: italic bold 24px courier;">a<sub>1</sub></b> is the slope of the regression line, which tells whether the line is increasing or decreasing
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LinearRegression extends BaseLinearRegression<NativeLinearRegression> {

	/**
	 * Creates the simple linear regression object, using the passed regression descriptor.
	 * 
	 * @param descriptor regression description used to create new regression
	 */
	LinearRegression(RegressionDescriptor descriptor) {
		super(RegressionType.LINEAR, NativeLinearRegression.load(descriptor));
	}

	/**
	 * Creates the simple linear regression object, using the passed data to calculate the formula.
	 * 
	 * @param x values bound to x
	 * @param y values bound to y
	 */
	LinearRegression(List<Double> x, List<Double> y) {
		super(RegressionType.LINEAR, new NativeLinearRegression(ArrayDouble.fromOrEmpty(x), ArrayDouble.fromOrEmpty(y)));
	}

}