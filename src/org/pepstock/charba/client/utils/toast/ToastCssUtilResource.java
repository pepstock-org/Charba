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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>charba.toast.min.css</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ToastCssUtilResource extends AbstractInjectableResource {

	// encoded javascript content of charba.toast.min.css
	private static final String[] CONTENT = {
		"#ct-container{line-height: 1.15;-webkit-text-size-adjust: 100%;font-family: 'Helvetica Neue', 'Helvetica', 'Arial', sans-serif;top: 0;right: 0;display: flex;flex-direction: column;justify-content: flex-end;position: fixed;width: auto;z-index: 1202;}#ct-container *{margin: 0;padding: 0;}#ct-container *,#ct-container ::before,#ct-container ::after{box-sizing: border-box;}#ct-container .ct-toast{align-self: flex-end;display: inline-block;overflow: auto;animation-duration: 0.3s;animation-name: toasting;animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);}#ct-container .ct-toast a, #ct-container .ct-toast a:hover{color: #549EDB !important;text-decoration: none !important;}#ct-container .ct-toast > div{background-color: white;min-width: 200px;max-width: 400px;margin: 5px 20px;padding: 15px 15px;box-shadow: 0 1px 6px rgba(0, 0, 0, 0.08), 0 1px 3px rgba(0, 0, 0, 0.19);position: relative;cursor: pointer;overflow-y: hidden;}#ct-container .ct-toast > div.img{padding: 15px 15px 15px 60px",
		";}#ct-container .ct-toast > div.ct-success{background-color: #51C625;}#ct-container .ct-toast > div.ct-success .ct-title,#ct-container .ct-toast > div.ct-actions .ct-action,#ct-container .ct-toast > div.ct-success .ct-label{color: white;}#ct-container .ct-toast > div.ct-warning{background-color: #DB9215;}#ct-container .ct-toast > div.ct-warning .ct-title,#ct-container .ct-toast > div.ct-warning .ct-action,#ct-container .ct-toast > div.ct-warning .ct-label{color: white;}#ct-container .ct-toast > div.ct-error{background-color: #DB2B1D;}#ct-container .ct-toast > div.ct-error .ct-title,#ct-container .ct-toast > div.ct-error .ct-action,#ct-container .ct-toast > div.ct-error .ct-label{color: white;}#ct-container .ct-toast > div.ct-info{background-color: #27ABDB;}#ct-container .ct-toast > div.ct-info .ct-title,#ct-container .ct-toast > div.ct-info .ct-action,#ct-container .ct-toast > div.ct-info .ct-label{color: white;}#ct-container .ct-toast > div.ct-dark{background-color: black;}#ct-containe",
		"r .ct-toast > div.ct-dark .ct-title,#ct-container .ct-toast > div.ct-dark .ct-action,#ct-container .ct-toast > div.ct-dark .ct-label{color: white;}#ct-container .ct-toast > div .progress-bar{opacity: 0.5;left: 0;right: 0;bottom: 0;height: 3px;position: absolute;background: gray;}#ct-container .ct-toast > div .progress-bar.rainbow{opacity: 0.6;background: linear-gradient(45deg, #002024 0%, #a72c86 48%, #00d4ff 100%);}#ct-container .ct-toast > div .progress-bar.error{opacity: 0.9;background: #DB2B1D;}#ct-container .ct-toast > div .progress-bar.warning{opacity: 0.9;background: #DB9215;}#ct-container .ct-toast > div .progress-bar.info{opacity: 0.9;background: #27ABDB;}#ct-container .ct-toast > div .progress-bar.success{opacity: 0.9;background: #51C625;}#ct-container .ct-toast > div .ct-title{font-size: 15px;font-weight: 700;color: #616161;}#ct-container .ct-toast > div .ct-label{font-size: 14px;font-weight: 400;line-height: 20px;color: #616161;}#ct-container .ct-toast > div .ct-icon{top: 50",
		"%;left: 15px;width: 30px;position: absolute;transform: translateY(-50%);}#ct-container .ct-toast > div .ct-actions{width:100%;text-align: left;}#ct-container .ct-toast > div .ct-action{display: inline-block;margin: 8px 5px 0px 0px;padding: 5px 5px;}#ct-container .ct-fadeOut{animation-name: toastingFadeOut;animation-duration: 0.6s;animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);animation-fill-mode: forwards;overflow: hidden;}@keyframes toasting {0% { transform: translate3d(400px, 0, 0);opacity: 0;}  50%{opacity: 1;}  80%{transform: translate3d(-15px, 0, 0);}  100%{transform: translate3d(0, 0, 0);}}@keyframes toastingFadeOut {0% { transform: translate3d(0, 0, 0);}  15%{transform: translate3d(-15px, 0, 0);}  40%{opacity: 1;}  50%{transform: translate3d(400px, 0, 0);opacity: 0;max-height: 250px;}  100%{transform: translate3d(400px, 0, 0);opacity: 0;max-height: 0;}}"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.toast.min.css</code> content.
	 */
	ToastCssUtilResource() {
		super(ResourceName.TOAST_CSS_UTIL, CONTENT);
	}

}
