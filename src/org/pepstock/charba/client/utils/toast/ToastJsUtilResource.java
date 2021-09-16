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
 * Contains the content of <code>charba.toast.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ToastJsUtilResource extends AbstractInjectableResource {

	// encoded javascript content of charba.toast.min.js
	private static final String[] CONTENT = {
		"CharbaToast={init:function $CharbaToast$init$(){var $container$$=document.createElement(\"div\");$container$$.id=\"ct-container\";document.body.appendChild($container$$);CharbaToast.create=function $CharbaToast$create$($pId$$,$pOptions$$){function $removeToast$$(){document.getElementById(\"ct-container\").removeChild($toasting$$);var $event$$=document.querySelector(\"#style-\"+$toasting$$.id);$event$$&&$event$$.remove();$result$$.showing=!1;\"function\"===typeof $options$$.onClose&&($event$$=new Event(\"toast-close\"),",
		"$options$$.onClose.apply(this,[$result$$,$event$$]))}var $$jscomp$this$$=this,$result$$=Object.assign({},$pOptions$$||{}),$options$$=Object.assign({},CharbaToast.defaults,CharbaToast.overrides,$result$$),$toasting$$=document.createElement(\"div\");$toasting$$.id=\"toast-\"+$pId$$;$toasting$$.className=\"ct-toast\";var $event_wrapper$$=document.createElement(\"div\"),$cssAnimation_icon_progressBar_text$$10_title$$=document.createElement(\"h4\");$cssAnimation_icon_progressBar_text$$10_title$$.className=\"ct-title\";",
		"$cssAnimation_icon_progressBar_text$$10_title$$.innerHTML=$options$$.title;$event_wrapper$$.appendChild($cssAnimation_icon_progressBar_text$$10_title$$);$options$$.text&&($cssAnimation_icon_progressBar_text$$10_title$$=document.createElement(\"p\"),$cssAnimation_icon_progressBar_text$$10_title$$.className=\"ct-text\",$cssAnimation_icon_progressBar_text$$10_title$$.innerHTML=$options$$.text,$event_wrapper$$.appendChild($cssAnimation_icon_progressBar_text$$10_title$$));$options$$.icon&&$options$$.icon.src&&",
		"($event_wrapper$$.classList+=\" img\",$cssAnimation_icon_progressBar_text$$10_title$$=document.createElement(\"img\"),$cssAnimation_icon_progressBar_text$$10_title$$.src=$options$$.icon.src,$cssAnimation_icon_progressBar_text$$10_title$$.className=\"ct-icon\",$event_wrapper$$.appendChild($cssAnimation_icon_progressBar_text$$10_title$$));if(!$options$$.hideProgressBar){$cssAnimation_icon_progressBar_text$$10_title$$=document.createElement(\"style\");$cssAnimation_icon_progressBar_text$$10_title$$.id=\"style-\"+",
		"$toasting$$.id;$cssAnimation_icon_progressBar_text$$10_title$$.type=\"text/css\";var $rules$$=document.createTextNode(\"\\n            @keyframes animate-\"+$pId$$+\" {\\n              0% {\\n                width: 100%\\n              }\\n              100% {\\n                width: 0%;\\n              }\\n            }\\n        \");$cssAnimation_icon_progressBar_text$$10_title$$.appendChild($rules$$);document.getElementsByTagName(\"head\")[0].appendChild($cssAnimation_icon_progressBar_text$$10_title$$);$cssAnimation_icon_progressBar_text$$10_title$$=",
		"document.createElement(\"div\");$cssAnimation_icon_progressBar_text$$10_title$$.classList=\"progress-bar\";$cssAnimation_icon_progressBar_text$$10_title$$.style.animationName=\"animate-\"+$pId$$;$cssAnimation_icon_progressBar_text$$10_title$$.style.animationDuration=$options$$.timeout/1E3+\"s\";$cssAnimation_icon_progressBar_text$$10_title$$.style.animationTimingFunction=\"linear\";$cssAnimation_icon_progressBar_text$$10_title$$.style.animationFillMode=\"forwards\";$options$$.progressBarType&&($cssAnimation_icon_progressBar_text$$10_title$$.classList+=",
		"\" \"+$options$$.progressBarType);$event_wrapper$$.appendChild($cssAnimation_icon_progressBar_text$$10_title$$)}\"function\"===typeof $options$$.onClick&&$toasting$$.addEventListener(\"click\",function($event$$){return $options$$.onClick.apply($$jscomp$this$$,[$result$$,$event$$])});$result$$.showing=!0;$toasting$$.hide=function $$toasting$$$hide$(){$toasting$$.className+=\" ct-fadeOut\";$toasting$$.addEventListener(\"animationend\",$removeToast$$,!1);return null};$options$$.autoHide&&setTimeout($toasting$$.hide,",
		"$options$$.timeout+200);$event_wrapper$$.className=$options$$.type?$event_wrapper$$.className+(\" ct-\"+$options$$.type):$event_wrapper$$.className+\" ct-default\";$toasting$$.addEventListener(\"click\",$toasting$$.hide);$toasting$$.appendChild($event_wrapper$$);document.getElementById(\"ct-container\").appendChild($toasting$$);$result$$.id=$pId$$;$result$$.dateTime=Date.now();$result$$.element=$toasting$$;\"function\"===typeof $options$$.onOpen&&($event_wrapper$$=new Event(\"toast-open\"),$options$$.onOpen.apply(this,",
		"[$result$$,$event_wrapper$$]));return $result$$}},create:function $CharbaToast$create$(){console.error(\"DOM has not finished loading.\\n\\tInvoke create method when DOMs readyState is complete\")},defaults:{autoHide:!0,hideProgressBar:!1,icon:void 0,onClick:void 0,onClose:void 0,onOpen:void 0,progressBarType:void 0,title:\"Default title\",text:void 0,timeout:4E3,type:void 0},overrides:{}};Object.freeze(CharbaToast.defaults);",
		"\"complete\"===document.readyState?CharbaToast.init():window.addEventListener(\"DOMContentLoaded\",CharbaToast.init);"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.toast.min.js</code> content.
	 */
	ToastJsUtilResource() {
		super(ResourceName.TOAST_JS_UTIL, CONTENT);
	}

}
