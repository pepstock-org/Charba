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
package org.pepstock.charba.client.dom;

import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.NativeName;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Represents the location (URL) of the object it is linked to.<br>
 * Changes done on it are reflected on the object it relates to.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.DOM_LOCATION, namespace = JsPackage.GLOBAL)
public final class BaseLocation {

	/**
	 * To avoid any instantiation
	 */
	BaseLocation() {
		// do nothing
	}

	/**
	 * Is a string containing a '#' followed by the fragment identifier of the URL.
	 * 
	 * @return the fragment identifier of the URL
	 */
	@JsProperty
	public final native String getHash();

	/**
	 * Is a string containing the host, that is the hostname, a ':', and the port of the URL.
	 * 
	 * @return a string containing the host
	 */
	@JsProperty
	public final native String getHost();

	/**
	 * Is a string containing the domain of the URL.
	 * 
	 * @return a string containing the domain of the URL
	 */
	@JsProperty
	public final native String getHostname();

	/**
	 * Is a stringifier that returns a string containing the entire URL.<br>
	 * If changed, the associated document navigates to the new page.It can be set from a different origin than the associated document.
	 * 
	 * @return a stringifier that returns a string containing the entire URL
	 */
	@JsProperty
	public final native String getHref();

	/**
	 * Returns a string containing the canonical form of the origin of the specific location.
	 * 
	 * @return a string containing the canonical form of the origin of the specific location
	 */
	@JsProperty
	public final native String getOrigin();

	/**
	 * Is a string containing an initial '/' followed by the path of the URL.
	 * 
	 * @return a string containing the path of the URL.
	 */
	@JsProperty
	public final native String getPathname();

	/**
	 * Is a string containing the port number of the URL.
	 * 
	 * @return a string containing the port number of the URL
	 */
	@JsProperty
	public final native String getPort();

	/**
	 * Is a string containing the protocol scheme of the URL, including the final ':'.
	 * 
	 * @return a string containing the protocol scheme of the URL
	 */
	@JsProperty
	public final native String getProtocol();

	/**
	 * Is a string containing a '?' followed by the parameters or "querystring" of the URL.
	 * 
	 * @return the query string of the URL
	 */
	@JsProperty
	public final native String getSearch();

	/**
	 * Causes the window to load and display the document at the URL specified.<br>
	 * After the navigation occurs, the user can navigate back to the page that called Location.assign() by pressing the "back" button.
	 * 
	 * @param url new resource to set to URL
	 */
	@JsMethod
	public final native void assign(String url);

	/**
	 * Reloads the current URL, like the Refresh button.
	 */
	@JsMethod
	public native void reload();

	/**
	 * Replaces the current resource with the one at the provided URL.<br>
	 * The difference from the {@link BaseLocation#assign(String)} method is that after using {@link BaseLocation#replace(String)} the current page will not be saved in session
	 * History, meaning the user won't be able to use the back button to navigate to it.
	 * 
	 * @param url new resource to set to URL
	 */
	@JsMethod
	public native void replace(String url);

	/**
	 * Gets the URL parameter of the specified name.<br>
	 * Note that if multiple parameters have been specified with the same name, the last one will be returned.
	 *
	 * @param name the name of the URL parameter
	 * @return the value of the URL parameter, or <code>null</code> if missing
	 */
	@JsOverlay
	public String getParameter(String name) {
		return BaseLocationUtil.getParameter(this, name);
	}

	/**
	 * Returns an unmodifiable map of the URL query parameters for the host page at the time this method was called.<br>
	 * Any changes to the window's location will be reflected in the result of subsequent calls.
	 *
	 * @return a map from URL query parameter names to a list of values
	 */
	@JsOverlay
	public Map<String, List<String>> getParameterMap() {
		return BaseLocationUtil.getParameterMap(this);
	}
}
