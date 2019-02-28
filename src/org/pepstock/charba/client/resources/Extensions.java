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
package org.pepstock.charba.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference CHART.JS plugins or extensions, wrapped by Charba.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface Extensions extends ClientBundle {

	/**
	 * Static reference to extensions java script source code
	 */
	public static final Extensions INSTANCE = GWT.create(Extensions.class);

	/**
	 * Contains text representation of native chart <a href="https://github.com/emn178/chartjs-plugin-labels">labels plugin</a>
	 * code.
	 * 
	 * @return chart <a href="https://github.com/emn178/chartjs-plugin-labels">labels plugin</a> code FIXME wait for approval of
	 *         pull request to LABELS plugin
	 */
	@Source("js/chartjs-plugin-labels.min.js")
	TextResource labelsPlugin();

	/**
	 * Contains text representation of native chart <a href="https://github.com/chartjs/chartjs-plugin-datalabelss">datalabels
	 * plugin</a> code.
	 * 
	 * @return chart <a href="https://github.com/chartjs/chartjs-plugin-datalabelss">datalabels plugin</a> code
	 */
	@Source("js/chartjs-plugin-datalabels.min.js")
	TextResource datalabelsPlugin();
}