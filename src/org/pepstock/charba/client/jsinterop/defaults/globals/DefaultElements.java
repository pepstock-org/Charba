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
package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle;

/**
 * CHART.JS default values for ELEMENTS element.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class DefaultElements implements IsDefaultElements {

	private final DefaultArc arc = new DefaultArc();

	private final DefaultLine line = new DefaultLine();

	private final DefaultPoint point = new DefaultPoint();

	private final DefaultRectangle rectangle = new DefaultRectangle();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getArc()
	 */
	@Override
	public IsDefaultArc getArc() {
		return arc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getLine()
	 */
	@Override
	public IsDefaultLine getLine() {
		return line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getPoint()
	 */
	@Override
	public IsDefaultPoint getPoint() {
		return point;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getRectangle()
	 */
	@Override
	public IsDefaultRectangle getRectangle() {
		return rectangle;
	}

}
