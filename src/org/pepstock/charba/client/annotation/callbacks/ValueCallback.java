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

import org.pepstock.charba.client.annotation.AnnotationContext;
import org.pepstock.charba.client.annotation.AnnotationPlugin;
import org.pepstock.charba.client.annotation.BoxAnnotation;
import org.pepstock.charba.client.annotation.EllipseAnnotation;
import org.pepstock.charba.client.annotation.LineAnnotation;
import org.pepstock.charba.client.annotation.PointAnnotation;
import org.pepstock.charba.client.callbacks.Scriptable;

/**
 * Callback interface of {@link AnnotationPlugin#ID} plugin to set: <br>
 * <ul>
 * <li><code>xMin</code> for {@link BoxAnnotation}, {@link EllipseAnnotation} and {@link LineAnnotation}
 * <li><code>xMax</code> for {@link BoxAnnotation}, {@link EllipseAnnotation} and {@link LineAnnotation}
 * <li><code>yMin</code> for {@link BoxAnnotation}, {@link EllipseAnnotation} and {@link LineAnnotation}
 * <li><code>yMax</code> for {@link BoxAnnotation}, {@link EllipseAnnotation} and {@link LineAnnotation}
 * <li><code>xValue</code> for {@link PointAnnotation}
 * <li><code>yValue</code> for {@link PointAnnotation}
 * <li><code>value</code> for {@link LineAnnotation}, infinitive line
 * <li><code>endValue</code> for {@link LineAnnotation}, infinitive line
 * </ul>
 * <br>
 * properties at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ValueCallback extends Scriptable<Object, AnnotationContext> {

}