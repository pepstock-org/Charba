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
package org.pepstock.charba.client.treemap;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.NativeArrayDoubleContainer;

/**
 * Specifies the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Dash extends NativeArrayDoubleContainer {

	/**
	 * Creates an empty object.
	 */
	public Dash() {
		this((double[]) null);
	}

	/**
	 * Creates the object setting the values of dash sequence.
	 * 
	 * @param values values of dash sequence
	 */
	public Dash(double... values) {
		this(ArrayDouble.fromOrEmpty(values));
	}

	/**
	 * Creates the object with a native array passed as argument.
	 * 
	 * @param nativeArray native array which maps a dash sequence
	 */
	Dash(ArrayDouble nativeArray) {
		super(nativeArray == null ? ArrayDouble.fromOrEmpty((List<Double>) null) : nativeArray);
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param values the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public void setValues(double... values) {
		push(values);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	public List<Double> getValues() {
		return ArrayListHelper.list(getNativeArray());
	}

}