/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultFillHandler;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.items.FillBaseline;
import org.pepstock.charba.client.items.FillColors;

/**
 * Defines a configuration element which is managing the FILL property.<br>
 * It has being used in the options and datasets instances where FILL is required.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasFill extends IsDefaultFillHandler {

	/**
	 * Returns a fill handler instance to use in the default methods of this interface.
	 * 
	 * @return a fill handler instance
	 */
	FillHandler getFillHandler();

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill <code>true</code> to fill, otherwise <code>false</code>.
	 */
	default void setFill(boolean fill) {
		// checks if fill handler is consistent
		if (getFillHandler() != null) {
			getFillHandler().setFill(fill);
		}
	}

	/**
	 * Sets how to fill the area under the line, by absolute dataset index.
	 * 
	 * @param index absolute dataset index of the chart.
	 */
	default void setFill(int index) {
		// checks if fill handler is consistent
		if (getFillHandler() != null) {
			setFill(Fill.getFill(index));
		}
	}

	/**
	 * Sets how to fill the area under the line, by relative dataset index.
	 * 
	 * @param index relative dataset index of the chart.
	 */
	default void setFill(String index) {
		// checks if fill handler is consistent
		if (getFillHandler() != null) {
			setFill(Fill.getFill(index));
		}
	}

	/**
	 * Sets how to fill the area under the line.
	 * 
	 * @param fill how to fill the area under the line.
	 */
	default void setFill(IsFill fill) {
		// checks if fill handler is consistent
		if (getFillHandler() != null) {
			getFillHandler().setFill(fill);
		}
	}

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	@Override
	default IsFill getFill() {
		// checks if fill handler is consistent
		if (getFillHandler() != null) {
			return getFillHandler().getFill();
		}
		// if here, fill handler is not consistent and then
		// returns the default value
		return Defaults.get().getGlobal().getElements().getLine().getFill();
	}

	/**
	 * Sets the baseline value to use for filling.
	 * 
	 * @param baseline the baseline value to use for filling
	 */
	default void setFillBaseline(double baseline) {
		// checks if fill handler is consistent
		if (getFillHandler() != null) {
			getFillHandler().setFillBaseline(baseline);
		}
	}

	/**
	 * Sets the baseline value to use for filling.
	 * 
	 * @param baseline the baseline value to use for filling
	 */
	default void setFillBaseline(FillBaseline baseline) {
		// checks if fill handler is consistent
		if (getFillHandler() != null) {
			getFillHandler().setFillBaseline(baseline);
		}
	}

	/**
	 * Retunrs the baseline value to use for filling.
	 * 
	 * @return the baseline value to use for filling
	 */
	@Override
	default FillBaseline getFillBaseline() {
		// checks if fill handler is consistent
		if (getFillHandler() != null) {
			return getFillHandler().getFillBaseline();
		}
		// if here, fill handler is not consistent and then
		// returns null
		return null;
	}

	/**
	 * Sets the above and below color of baseline to use for filling.
	 * 
	 * @param colors the above and below color of baseline to use for filling.
	 */
	default void setFillColors(FillColors colors) {
		// checks if fill handler is consistent
		if (getFillHandler() != null) {
			getFillHandler().setFillColors(colors);
		}
	}

	/**
	 * Returns the above and below color of baseline to use for filling.
	 * 
	 * @return the above and below color of baseline to use for filling.
	 */
	@Override
	default FillColors getFillColors() {
		// checks if fill handler is consistent
		if (getFillHandler() != null) {
			return getFillHandler().getFillColors();
		}
		// if here, fill handler is not consistent and then
		// returns null
		return null;
	}
}