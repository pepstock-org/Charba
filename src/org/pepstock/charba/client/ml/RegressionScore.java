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
package org.pepstock.charba.client.ml;

import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * When building a regression model, you need to evaluate the goodness of the model, that is how well the model fits the training data used to build the model and how accurate is
 * the model in predicting the outcome for new unseen test observations.<br>
 * It maps the most commonly known evaluation metrics.<br>
 * Metrics:<br>
 * <ul>
 * <li><b>R (R)</b>, which is the proportion of variation in the outcome that is explained by the predictor variables. In multiple regression models, R corresponds to the
 * correlation between the observed outcome values and the predicted values by the model. The Higher the R, the better the model
 * <li><b>R-squared (R2)</b>, which is the proportion of variation in the outcome that is explained by the predictor variables. In multiple regression models, R2 corresponds to the
 * squared correlation between the observed outcome values and the predicted values by the model. The Higher the R-squared, the better the model
 * <li><b>Pearson's chi-squared (Chi2)</b>, which is the sum of differences between observed and expected outcome frequencies (that is, counts of observations), each squared and
 * divided by the expectation
 * <li><b>Root Mean Squared Error (RMSE)</b>, which measures the average error performed by the model in predicting the outcome for an observation. Mathematically, the RMSE is the
 * square root of the mean squared error (MSE), which is the average squared difference between the observed actual outcome values and the values predicted by the model
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.ML_REGRESSION_SCORE, namespace = JsPackage.GLOBAL)
public interface RegressionScore {

	/**
	 * Returns the R metric.
	 * 
	 * @return the R metric
	 */
	@JsProperty
	double getR();

	/**
	 * Returns the R-squared (R2) metric.
	 * 
	 * @return the R-squared (R2) metric
	 */
	@JsProperty
	double getR2();

	/**
	 * Returns the Pearson's chi-squared (Chi2) metric.
	 * 
	 * @return the Pearson's chi-squared (Chi2) metric.
	 */
	@JsProperty
	double getChi2();

	/**
	 * Returns the Root Mean Squared Error (RMSE) metric.
	 * 
	 * @return the Root Mean Squared Error (RMSE) metric.
	 */
	@JsProperty
	double getRmsd();
}