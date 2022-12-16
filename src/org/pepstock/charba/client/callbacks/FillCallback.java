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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.enums.AbsoluteDatasetIndexFill;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.RelativeDatasetIndexFill;

/**
 * Callback interface to set <code>fill</code> property at runtime.<br>
 * The result object can have one of following type:<br>
 * <ul>
 * <li>{@link Fill}
 * <li>Boolean
 * <li>Integer for {@link AbsoluteDatasetIndexFill}
 * <li>String for {@link RelativeDatasetIndexFill}
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @see IsFill
 * @see Fill
 * @see AbsoluteDatasetIndexFill
 * @see RelativeDatasetIndexFill
 */
public interface FillCallback extends Scriptable<Object, DatasetContext> {

}