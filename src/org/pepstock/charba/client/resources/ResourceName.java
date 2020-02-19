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
 * Enumerates the constants names to use as element id for scripts, mandatory to CHARBA to work properly.<br>
 * This resource names can not be used for any custom injectable resource.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum ResourceName implements Key
{
	/**
	 * Script element id for CHART.JS source code.
	 */
	CHART("chartJs", false, "org.pepstock.charba.client.resources.ChartJsResource"),
	/**
	 * Script element id for CHART.JS date time adapter.
	 */
	DATE_TIME_ADAPTER("datetimeAdapter", true, null),
	/**
	 * Script element id for CHART.JS date time library.
	 */
	DATE_TIME_LIBRARY("datetimeLibrary", true, null),
	/**
	 * Script element id for CHARBA helper.
	 */
	CHARBA_HELPER("charbaHelper", false, "org.pepstock.charba.client.commons.JsHelperResource"),
	/**
	 * Script element id for CHART.JS ANNOTATION plugin.
	 */
	ANNOTATION_PLUGIN("annotation", false, "org.pepstock.charba.client.annotation.AnnotationPluginResource"),
	/**
	 * Script element id for CHART.JS DATALABELS plugin.
	 */
	DATALABELS_PLUGIN("datalabels", false, "org.pepstock.charba.client.datalabels.DataLabelsPluginResource"),
	/**
	 * Script element id for CHART.JS ZOOM plugin.
	 */
	ZOOM_PLUGIN("zoom", false, "org.pepstock.charba.client.zoom.ZoomPluginResource"),
	/**
	 * Script element id for HAMMER JS library.
	 */
	HAMMER_LIBRARY("hammerjs", false, "org.pepstock.charba.client.zoom.ZoomPluginHammerResource"),
	/**
	 * Script element id for CHART.JS LABELS plugin.
	 */
	LABELS_PLUGIN("labels", false, "org.pepstock.charba.client.labels.LabelsPluginResource");

	// name value of property
	private final String value;
	// flag to know if the resource can be override by user
	private final boolean override;
	// class name of CHARBA injectable resource implementation
	private final String className;

	/**
	 * Creates with the value of resource name.
	 * 
	 * @param value the value of resource name
	 * @param override <code>true</code> if the resource can be override by user
	 * @param className class name of CHARBA injectable resource implementation
	 */
	private ResourceName(String value, boolean override, String className) {
		this.value = value;
		this.override = override;
		this.className = className;
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

	/**
	 * Returns the class name of CHARBA injectable resource implementation.
	 * 
	 * @return class name of CHARBA injectable resource implementation
	 */
	String getClassName() {
		return className;
	}

}
