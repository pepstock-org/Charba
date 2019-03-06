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
import com.google.gwt.resources.client.ExternalTextResource;

/**
 * Client bundle to reference CHART.JS and other java script codes, always needed to CHARBA.<br>
 * It also includes some image resources for datasets items selector plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface ExternalResources extends Resources<ExternalTextResource> {

	/**
	 * Static reference to resources java script source code
	 */
	public static final ExternalResources INSTANCE = GWT.create(ExternalResources.class);

	/**
	 * Contains text representation of native chart.js code. FIXME downloaded NOT ufficial version to solve the issue about
	 * POINTS and GRADIENTS color FIXME https://www.chartjs.org/docs/latest/developers/
	 * 
	 * @return chart.js code
	 */
	@Source("js/chart.bundle.min.js")
	ExternalTextResource chartJs();

//	/**
//	 * This java script with a set of static methods used as utility and needed to improve JSINTEROP adoption for CHARBA,
//	 * because JSINTEROP is not able to address all java script model.
//	 * 
//	 * @return CHARBA java script code.
//	 */
//	@Source("js/charba.helper.min.js")
//	TextResource charbaHelper();
//
//	/**
//	 * Returns a image to use into datasets items selector plugin as clear icon.
//	 * 
//	 * @return a image to use into datasets items selector plugin as clear icon
//	 */
//	@Source("/images/clear_black_18dp.png")
//	ImageResource clearSelection18();
//
//	/**
//	 * Returns a image to use into datasets items selector plugin as clear icon.
//	 * 
//	 * @return a image to use into datasets items selector plugin as clear icon
//	 */
//	@Source("/images/clear_black_24dp.png")
//	ImageResource clearSelection24();
//
//	/**
//	 * Returns a image to use into datasets items selector plugin as clear icon.
//	 * 
//	 * @return a image to use into datasets items selector plugin as clear icon
//	 */
//	@Source("/images/clear_black_36dp.png")
//	ImageResource clearSelection36();

}