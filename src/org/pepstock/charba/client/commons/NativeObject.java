/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.commons;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Base object for all native objects implemented in Charba.<br>
 * it wraps java script object with and without <code>prototype</code>.<br>
 * It's also mapping the java script <code>proxy</code>, used by CHART.JS.<br>
 * PAY ATTENTION that the java script object class name is "?" because otherwise J2CL is not able to cast to an object.<br>
 * Please be also aware that you can not use <code>instanceof</code> against this class.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.GENERIC, namespace = JsPackage.GLOBAL)
public interface NativeObject {

}
