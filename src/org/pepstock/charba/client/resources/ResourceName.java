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

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the constants names to use as element id for scripts, mandatory to CHARBA to work properly.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum ResourceName implements Key
{
	/**
	 * Script element id for CHART.JS source code.
	 */
	CHART("chartJs", false),
	/**
	 * Script element id for CHART.JS date time adapter.
	 */
	DATE_TIME_ADAPTER("datetimeAdapter", false),
	/**
	 * Script element id for CHART.JS date time library.
	 */
	DATE_TIME_LIBRARY("datetimeLibrary", true),
	/**
	 * Script element id for CHARBA helper.
	 */
	CHARBA_HELPER("charbaHelper", false),
	/**
	 * Script element id for CHART.JS DATALABELS plugin.
	 */
	DATALABELS_PLUGIN("datalabels", false),
	/**
	 * Script element id for CHART.JS ZOOM plugin.
	 */
	ZOOM_PLUGIN("zoom", false),
	/**
	 * Script element id for HAMMER JS library.
	 */
	HAMMER_LIBRARY("hammerjs", true),
	/**
	 * Script element id for CHART.JS LABELS plugin.
	 */
	LABELS_PLUGIN("labels", false),
	/**
	 * Script element id for CHART.JS ANNOTATION plugin.
	 */
	ANNOTATION_PLUGIN("annotation", false),
	/**
	 * Script element id for CHART.JS TREEMAP controller.
	 */
	TREEMAP_CONTROLLER("treemap", false),
	/**
	 * Script element id for CHART.JS SANKEY controller.
	 */
	SANKEY_CONTROLLER("sankey", false),
	/**
	 * Script element id for CHART.JS MATRIX controller.
	 */
	MATRIX_CONTROLLER("matrix", false),
	/**
	 * Script element id for CHART.JS GEO controller.
	 */
	GEO_CONTROLLER("geo", false);

	// name value of property
	private final String value;
	// flag to know if the resource can be override by user
	private final boolean override;

	/**
	 * Creates with the value of resource name.
	 * 
	 * @param value the value of resource name
	 * @param override <code>true</code> if the resource can be override by user
	 */
	private ResourceName(String value, boolean override) {
		this.value = value;
		this.override = override;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/**
	 * Returns <code>true</code> if the resource can be override by user.
	 * 
	 * @return <code>true</code> if the resource can be override by user
	 */
	boolean isOverride() {
		return override;
	}

}
