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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.items.FillBaseline;
import org.pepstock.charba.client.items.FillColors;

/**
 * Interface to define fill defaults values.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultFillHandler {

	/**
	 * Returns how to fill the area under the line.
	 * 
	 * @return how to fill the area under the line.
	 */
	IsFill getFill();

	/**
	 * Returns the baseline value to use for filling.
	 * 
	 * @return the baseline value to use for filling
	 */
	FillBaseline getFillBaseline();

	/**
	 * Returns the above and below color of baseline to use for filling.
	 * 
	 * @return the above and below color of baseline to use for filling.
	 */
	FillColors getFillColors();

}