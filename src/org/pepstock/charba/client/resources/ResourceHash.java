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
import org.pepstock.charba.client.items.Undefined;

/**
 * Contains the hash for all out-of-the-box injectable java script resources.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
enum ResourceHash {

	/**
	 * Hash item for 'LuxonLibraryResource' class.
	 */
	LUXON_LIBRARY_RESOURCE(ResourceName.DATE_TIME_LIBRARY, 15737955),
	/**
	 * Hash item for 'ChartJsResource' class.
	 */
	CHART_JS_RESOURCE(ResourceName.CHART, 16853005),
	/**
	 * Hash item for 'JsHelperResource' class.
	 */
	JS_HELPER_RESOURCE(ResourceName.CHARBA_HELPER, 985014),
	/**
	 * Hash item for 'GeoControllerResource' class.
	 */
	GEO_CONTROLLER_RESOURCE(ResourceName.GEO_CONTROLLER, 8961254),
	/**
	 * Hash item for 'AnnotationPluginResource' class.
	 */
	ANNOTATION_PLUGIN_RESOURCE(ResourceName.ANNOTATION_PLUGIN, 1237857),
	/**
	 * Hash item for 'DataLabelsPluginResource' class.
	 */
	DATA_LABELS_PLUGIN_RESOURCE(ResourceName.DATALABELS_PLUGIN, 1133123),
	/**
	 * Hash item for 'ZoomPluginResource' class.
	 */
	ZOOM_PLUGIN_RESOURCE(ResourceName.ZOOM_PLUGIN, 1016813),
	/**
	 * Hash item for 'LabelsPluginResource' class.
	 */
	LABELS_PLUGIN_RESOURCE(ResourceName.LABELS_PLUGIN, 1451863),
	/**
	 * Hash item for 'ZoomPluginHammerResource' class.
	 */
	ZOOM_PLUGIN_HAMMER_RESOURCE(ResourceName.HAMMER_LIBRARY, 1881773),
	/**
	 * Hash item for 'LuxonAdapterResource' class.
	 */
	LUXON_ADAPTER_RESOURCE(ResourceName.DATE_TIME_ADAPTER, 160328),
	/**
	 * Hash item for 'TreeMapControllerResource' class.
	 */
	TREE_MAP_CONTROLLER_RESOURCE(ResourceName.TREEMAP_CONTROLLER, 859201);
	
	// resource name instance
	private final ResourceName resourceName;
	// resource name instance
	private final int hash;
		
	/**
	 * Creates with the resource name and hash of injectable javascript resource.
	 * 
	 * @param resourceName resource name of java script content
	 * @param hash hash of the content
	 */
	private ResourceHash(ResourceName resourceName, int hash) {
		this.resourceName = resourceName;
		this.hash = hash;
	}
	
	/**
	 * Returns the resource name of the java script resource.
	 * 
	 * @return the resource name of the java script resource
	 */
	ResourceName getResourceName() {
		return resourceName;
	}

	/**
	 * Returns the hash of the content.
	 * 
	 * @return the hash of the content
	 */
	int getHash() {
		return hash;
	}
	
	/**
	 * Search the resource name and returns the calculated hash, when created.
	 * 
	 * @param resourceName the resource name of the java script resource
	 * @return the hash of the content
	 */
	static int hash(ResourceName resourceName) {
		// checks if argument is consistent
		if (Key.isValid(resourceName)) {
			// scans resource hash
			for (ResourceHash resourceHash : values()) {
				// checks if the resource name is equals
				if (Key.equals(resourceName, resourceHash.getResourceName())) {
					// found and returns the hash
					return resourceHash.getHash();
				}
			}
		}
		// if here, the argument is not consistent
		// then returns undefined
		return Undefined.INTEGER;
	}

}