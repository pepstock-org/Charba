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
package org.pepstock.charba.client.annotation.callbacks;

import org.pepstock.charba.client.annotation.AbstractAnnotation;
import org.pepstock.charba.client.annotation.AnnotationContext;
import org.pepstock.charba.client.annotation.AnnotationPlugin;
import org.pepstock.charba.client.annotation.LineLabel;
import org.pepstock.charba.client.callbacks.Scriptable;

/**
 * Callback interface of {@link AnnotationPlugin#ID} plugin to set <code>xAdjust</code> and <code>yAdjust</code> property at runtime, to {@link AbstractAnnotation} and
 * {@link LineLabel}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface AdjustSizeCallback extends Scriptable<Double, AnnotationContext> {

}