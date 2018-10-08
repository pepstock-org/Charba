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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Configuration element to set all attributes and features of the default tooltip.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
final class NativeTooltipsCallbacks extends NativeObject {

	@JsProperty
	native void setBeforeTitle(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setTitle(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setAfterTitle(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setBeforeBody(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setBeforeLabel(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setLabel(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setLabelColor(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setLabelTextColor(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setAfterLabel(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setAfterBody(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setBeforeFooter(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setFooter(CallbackProxy.Proxy proxy);
	
	@JsProperty
	native void setAfterFooter(CallbackProxy.Proxy proxy);


}