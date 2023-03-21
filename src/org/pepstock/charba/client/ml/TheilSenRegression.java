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
 * In TheilSen Regression, the estimation of the model is done by calculating the slopes and intercepts of a subpopulation of all possible combinations of subsample points.<br>
 * The final slope and intercept is then defined as the spatial median of these slopes and intercepts.<br>
 * The TheilSen Regression model can be represented using the below equation:<br>
 * <br>
 * <b style="font: italic bold 24px courier;">y = a<sub>0</sub> + a<sub>1</sub>x</b><br>
 * <br>
 * where<br>
 * <ul>
 * <li><b style="font: italic bold 24px courier;">a<sub>0</sub></b> is the intercept of the Regression line (can be obtained putting x=0)
 * <li><b style="font: italic bold 24px courier;">a<sub>1</sub></b> is the slope of the regression line, which tells whether the line is increasing or decreasing
 * </ul>
 * <br>
 * This regression can be computed efficiently, and is insensitive to outliers.<br>
 * It can be significantly more accurate than non-robust simple linear regression (least squares) for skewed and heteroskedastic data, and competes well against least squares even
 * for normally distributed data in terms of statistical power.<br>
 * It has been called "the most popular nonparametric technique for estimating a linear trend".<br>
 * See details on <a href="https://en.wikipedia.org/wiki/Theil%E2%80%93Sen_estimator">Theil–Sen estimator</a>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TheilSenRegression extends BaseLinearRegression<NativeTheilSenRegression> {

	/**
	 * Creates the TheilSen regression object, using the passed regression descriptor.
	 * 
	 * @param descriptor regression description used to create new regression
	 */
	TheilSenRegression(RegressionDescriptor descriptor) {
		super(RegressionType.THEIL_SEN, NativeTheilSenRegression.load(descriptor));
	}

	/**
	 * Creates the TheilSen regression object, using the passed data to calculate the formula.
	 * 
	 * @param x values bound to x
	 * @param y values bound to y
	 */
	TheilSenRegression(List<Double> x, List<Double> y) {
		super(RegressionType.THEIL_SEN, new NativeTheilSenRegression(ArrayDouble.fromOrEmpty(x), ArrayDouble.fromOrEmpty(y)));
	}

}