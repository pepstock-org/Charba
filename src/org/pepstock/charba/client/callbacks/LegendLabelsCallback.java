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
package org.pepstock.charba.client.callbacks;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.items.LegendLabelItem;

/**
 * Generates legend items for each thing in the legend.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface LegendLabelsCallback {

	/**
	 * Generates legend items for each thing in the legend. Default implementation returns the text + styling for the color box.
	 * 
	 * @param chart chart instance
	 * @param defaultLabels list of labels created by CHART.JS using the out of the box generate labels callback.
	 * @return a list of legend items. if <code>null</code>, uses the default implementation
	 */
	List<LegendLabelItem> generateLegendLabels(IsChart chart, List<LegendLabelItem> defaultLabels);

}