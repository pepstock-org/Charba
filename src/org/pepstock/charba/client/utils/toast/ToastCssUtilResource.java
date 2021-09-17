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
		"#ct-container{line-height:1.15;-webkit-text-size-adjust:100%;font-family:system-ui,-apple-system,\"Segoe UI\",Roboto,Helvetica,Arial,sans-serif,\"Apple Color Emoji\",\"Segoe UI Emoji\";top:0;right:0;display:flex;flex-direction:column;justify-content:flex-end;position:fixed;width:auto;z-index:1202}#ct-container *{margin:0;padding:0;box-sizing:border-box}#ct-container ::before,#ct-container ::after{box-sizing:border-box}#ct-container .ct-toast{align-self:flex-end;display:inline-block;overflow:auto;animation-duration:.3s;animation-name:toasting;animation-timing-function:cubic-bezier(0.215,0.61,0.355,1)}#ct-container .ct-toast a,#ct-container .ct-toast a:hover{color:#549edb!important;text-decoration:none!important}#ct-container .ct-toast>div{background-color:white;min-width:200px;max-width:400px;margin:5px 20px;padding:15px;box-shadow:0 1px 6px rgba(0,0,0,0.08),0 1px 3px rgba(0,0,0,0.19);position:relative;cursor:pointer;overflow-y:hidden}#ct-container .ct-toast>div.img{padding:15px 15px 15p",
		"x 60px}#ct-container .ct-toast>div.ct-success{background-color:#51c625}#ct-container .ct-toast>div.ct-success .ct-title,#ct-container .ct-toast>div.ct-success .ct-text{color:white}#ct-container .ct-toast>div.ct-warning{background-color:#db9215}#ct-container .ct-toast>div.ct-warning .ct-title,#ct-container .ct-toast>div.ct-warning .ct-text{color:white}#ct-container .ct-toast>div.ct-error{background-color:#db2b1d}#ct-container .ct-toast>div.ct-error .ct-title,#ct-container .ct-toast>div.ct-error .ct-text{color:white}#ct-container .ct-toast>div.ct-info{background-color:#27abdb}#ct-container .ct-toast>div.ct-info .ct-title,#ct-container .ct-toast>div.ct-info .ct-text{color:white}#ct-container .ct-toast>div .progress-bar{opacity:.5;left:0;right:0;bottom:0;height:3px;position:absolute;background:gray}#ct-container .ct-toast>div .progress-bar.rainbow{opacity:.6;background:linear-gradient(45deg,#002024 0%,#a72c86 48%,#00d4ff 100%)}#ct-container .ct-toast>div .progress-bar.error{opacity:.9;backg",
		"round:#db2b1d}#ct-container .ct-toast>div .progress-bar.warning{opacity:.9;background:#db9215}#ct-container .ct-toast>div .progress-bar.info{opacity:.9;background:#27abdb}#ct-container .ct-toast>div .progress-bar.success{opacity:.9;background:#51c625}#ct-container .ct-toast>div .ct-title{font-size:15px;font-weight:700;color:#616161}#ct-container .ct-toast>div .ct-text{font-size:14px;font-weight:400;line-height:20px;color:#616161}#ct-container .ct-toast>div .ct-icon{top:50%;left:15px;width:30px;position:absolute;transform:translateY(-50%)}#ct-container .ct-fadeOut{animation-name:toastingFadeOut;animation-duration:.6s;animation-timing-function:cubic-bezier(0.215,0.61,0.355,1);animation-fill-mode:forwards;overflow:hidden}@keyframes toasting{0%{transform:translate3d(400px,0,0);opacity:0}50%{opacity:1}80%{transform:translate3d(-15px,0,0)}to{transform:translate3d(0,0,0)}}@keyframes toastingFadeOut{0%{transform:translate3d(0,0,0)}15%{transform:translate3d(-15px,0,0)}40%{opacity:1}50%{transform",
		":translate3d(400px,0,0);opacity:0;max-height:250px}to{transform:translate3d(400px,0,0);opacity:0;max-height:0}}"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.toast.min.css</code> content.
	 */
	ToastCssUtilResource() {
		super(ResourceName.TOAST_CSS_UTIL, CONTENT);
	}

}
