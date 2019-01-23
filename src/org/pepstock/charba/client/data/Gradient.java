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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.enums.GradientOrientation;
import org.pepstock.charba.client.enums.GradientType;

import com.google.gwt.canvas.dom.client.CanvasGradient;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Gradient {
	
	// default canvas gradient
	static final CanvasGradient DEFAULT_CANVAS_GRADIENT = null;

	/**
	 * Default of gradient, with <code>null</code> on canvas gradient.
	 */
	public static final Gradient DEFAULT_GRADIENT = new Gradient(DEFAULT_CANVAS_GRADIENT);

	private final GradientOrientation orientation;
	
	private final GradientType type;
	
	private CanvasGradient gradient = null;
	
	public Gradient() {
		this(GradientType.linear);
	}

	public Gradient(GradientType type) {
		this(type, GradientOrientation.vertical);
	}

	public Gradient(GradientType type, GradientOrientation orientation) {
		this.type = type;
		this.orientation = orientation;
	}

	/**
	 * @return the orientation
	 */
	public GradientOrientation getOrientation() {
		return orientation;
	}

	/**
	 * @return the type
	 */
	public GradientType getType() {
		return type;
	}

	Gradient(CanvasGradient gradient) {
		this(null, null);
		this.gradient = gradient;
	}

	/**
	 * @return the gradient
	 */
	public CanvasGradient getGradient() {
		return gradient;
	}

	/**
	 * @param gradient the gradient to set
	 */
	void setGradient(CanvasGradient gradient) {
		this.gradient = gradient;
	}

}
