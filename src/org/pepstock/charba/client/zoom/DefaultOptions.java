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
package org.pepstock.charba.client.zoom;

/**
 * {@link ZoomPlugin#ID} plugin default options.<br>
 * It contains all default values, PAN and ZOOM.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultOptions implements IsDefaultOptions {

	// defaults options instance
	static final DefaultOptions INSTANCE = new DefaultOptions();
	// default pan options
	private final DefaultPan pan = new DefaultPan();
	// default Zoom options
	private final DefaultZoom zoom = new DefaultZoom();

	/**
	 * Creates an empty options.
	 */
	private DefaultOptions() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.IsDefaultsOptions#getPan()
	 */
	@Override
	public DefaultPan getPan() {
		return pan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.IsDefaultsOptions#getZoom()
	 */
	@Override
	public DefaultZoom getZoom() {
		return zoom;
	}

}
