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
		"CharbaToast={init:function $CharbaToast$init$(){var $container$$=document.createElement(\"div\");$container$$.id=\"ct-container\";document.body.appendChild($container$$);CharbaToast.create=function $CharbaToast$create$($pId$$,$font$$,$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$,$pOptions_wrapper$$,$hasLabel_pDateTime_rules_text$$){function $removeToast$$(){document.getElementById(\"ct-container\").removeChild($toasting$$);var $style$$=document.querySelector(\"#style-\"+$toasting$$.id);$style$$&&",
		"$style$$.remove();$result$$.status=\"closed\";$result$$.dateTime.closed=Date.now();CharbaToast.currentOpenItems--;\"function\"===typeof $options$$.onClose&&$options$$.onClose.apply(this,[$result$$]);\"function\"===typeof CharbaToast.onClose&&CharbaToast.onClose.apply(this,[$result$$])}var $$jscomp$this$$=this,$result$$={id:$pId$$,title:$font$$,label:$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$,dateTime:Object.assign({},$hasLabel_pDateTime_rules_text$$||{})},$hasTitle_title$$=\"string\"===typeof $font$$&&",
		"0<$font$$.trim().length;$hasLabel_pDateTime_rules_text$$=Array.isArray($cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$)&&0<$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.length;if(!$hasTitle_title$$&&!$hasLabel_pDateTime_rules_text$$)return Object.assign($result$$,{status:\"invalid\",dateTime:{invalid:Date.now()}});var $options$$=Object.assign({},CharbaToast.defaults,CharbaToast.overrides,$pOptions_wrapper$$||{}),$toasting$$=document.createElement(\"div\");$toasting$$.id=\"toast-\"+",
		"$pId$$;$toasting$$.className=\"ct-toast\";$pOptions_wrapper$$=document.createElement(\"div\");$pOptions_wrapper$$.style.borderRadius=$options$$.borderRadius+\"px\";$options$$.hideShadow&&($pOptions_wrapper$$.style.boxShadow=\"none\");$hasTitle_title$$&&($hasTitle_title$$=document.createElement(\"h4\"),$hasTitle_title$$.className=\"ct-title\",$hasTitle_title$$.innerHTML=$font$$,\"string\"===typeof $options$$.title.color&&($hasTitle_title$$.style.color=$options$$.title.color),\"object\"===typeof $options$$.title.font&&",
		"($font$$=Object.assign({},CharbaToast.defaults.titleFont,$options$$.title.font),$font$$=CharbaToast.helper.toFont.apply(this,[$font$$]),$hasTitle_title$$.style.font=$font$$.string),$pOptions_wrapper$$.appendChild($hasTitle_title$$));$hasLabel_pDateTime_rules_text$$&&($hasLabel_pDateTime_rules_text$$=document.createElement(\"p\"),$hasLabel_pDateTime_rules_text$$.className=\"ct-text\",$hasLabel_pDateTime_rules_text$$.innerHTML=$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.join(\"<br/>\"),\"string\"===",
		"typeof $options$$.label.color&&($hasLabel_pDateTime_rules_text$$.style.color=$options$$.label.color),\"object\"===typeof $options$$.label.font&&($cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$=Object.assign({},CharbaToast.defaults.labelFont,$options$$.label.font),$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$=CharbaToast.helper.toFont.apply(this,[$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$]),$hasLabel_pDateTime_rules_text$$.style.font=$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.string),",
		"$pOptions_wrapper$$.appendChild($hasLabel_pDateTime_rules_text$$));$options$$.icon&&$options$$.icon.src&&($pOptions_wrapper$$.classList+=\" img\",$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$=document.createElement(\"img\"),$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.src=$options$$.icon.src,$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.className=\"ct-icon\",$pOptions_wrapper$$.appendChild($cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$));$options$$.hideProgressBar||",
		"($cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$=document.createElement(\"style\"),$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.id=\"style-\"+$toasting$$.id,$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.type=\"text/css\",$hasLabel_pDateTime_rules_text$$=document.createTextNode(\"\\n            @keyframes animate-\"+$pId$$+\" {\\n              0% {\\n                width: 100%\\n              }\\n              100% {\\n                width: 0%;\\n              }\\n            }\\n        \"),",
		"$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.appendChild($hasLabel_pDateTime_rules_text$$),document.getElementsByTagName(\"head\")[0].appendChild($cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$),$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$=document.createElement(\"div\"),$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.classList=\"progress-bar\",$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.style.animationName=\"animate-\"+$pId$$,$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.style.animationDuration=",
		"$options$$.timeout/1E3+\"s\",$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.style.animationTimingFunction=\"linear\",$cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.style.animationFillMode=\"forwards\",$options$$.progressBarType&&($cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$.classList+=\" \"+$options$$.progressBarType),$pOptions_wrapper$$.appendChild($cssAnimation_font$0_icon_normFont$1_pLabel_progressBar$$));\"function\"===typeof $options$$.onClick&&$toasting$$.addEventListener(\"click\",",
		"function($event$$){return $options$$.onClick.apply($$jscomp$this$$,[$result$$,$event$$])});$toasting$$.hide=function $$toasting$$$hide$(){$toasting$$.className+=\" ct-fadeOut\";$toasting$$.addEventListener(\"animationend\",$removeToast$$,!1);return null};$options$$.autoHide&&setTimeout($toasting$$.hide,$options$$.timeout+200);$pOptions_wrapper$$.className=$options$$.type?$pOptions_wrapper$$.className+(\" ct-\"+$options$$.type):$pOptions_wrapper$$.className+\" ct-default\";$toasting$$.addEventListener(\"click\",",
		"$toasting$$.hide);$toasting$$.appendChild($pOptions_wrapper$$);document.getElementById(\"ct-container\").appendChild($toasting$$);$result$$.status=\"opened\";$result$$.dateTime.opened=Date.now();$result$$.element=$toasting$$;CharbaToast.currentOpenItems++;\"function\"===typeof $options$$.onOpen&&$options$$.onOpen.apply(this,[$result$$]);return $result$$}},create:function $CharbaToast$create$(){console.error(\"DOM has not finished loading.\\n\\tInvoke create method when DOMs readyState is complete\")},helper:{lineHeightRegExp:new RegExp(/^(normal|(\\d+(?:\\.\\d+)?)(px|em|%)?)$/),",
		"fontStyleRegExp:new RegExp(/^(normal|italic|initial|inherit|unset|(oblique( -?[0-9]?[0-9]deg)?))$/),toLineHeight:function $CharbaToast$helper$toLineHeight$($value$$,$size$$){var $matches$$=(\"\"+$value$$).match(CharbaToast.helper.lineHeightRegExp);if(!$matches$$||\"normal\"===$matches$$[1])return 1.2*$size$$;$value$$=+$matches$$[2];switch($matches$$[3]){case \"px\":return $value$$;case \"%\":$value$$/=100}return $size$$*$value$$},toFont:function $CharbaToast$helper$toFont$($font$$4_options$$){var $size$$=",
		"$font$$4_options$$.size;\"string\"===typeof $size$$&&($size$$=parseInt($size$$,10));var $style$$=$font$$4_options$$.style;$style$$&&!(\"\"+$style$$).match(CharbaToast.helper.fontStyleRegExp)&&($style$$=\"\");$font$$4_options$$={family:$font$$4_options$$.family,lineHeight:CharbaToast.helper.toLineHeight.apply(this,[$font$$4_options$$.lineHeight,$size$$]),size:$size$$,style:$style$$,weight:$font$$4_options$$.weight,string:\"\"};$font$$4_options$$.string=($font$$4_options$$.style?$font$$4_options$$.style+\" \":",
		"\"\")+($font$$4_options$$.weight?$font$$4_options$$.weight+\" \":\"\")+$font$$4_options$$.size+\"px \"+$font$$4_options$$.family;return $font$$4_options$$}},defaults:{autoHide:!0,borderRadius:8,hideProgressBar:!1,hideShadow:!1,icon:void 0,onClick:void 0,onClose:void 0,onOpen:void 0,progressBarType:void 0,title:{color:void 0,font:void 0},label:{color:void 0,font:void 0},timeout:4E3,type:void 0,titleFont:{family:'system-ui, -apple-system, \"Segoe UI\", Roboto, Helvetica, Arial, sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\"',",
		"lineHeight:1.2,size:15,style:\"normal\",weight:\"bold\"},labelFont:{family:'system-ui, -apple-system, \"Segoe UI\", Roboto, Helvetica, Arial, sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\"',lineHeight:\"20px\",size:14,style:\"normal\",weight:\"normal\"}},overrides:{},onClose:void 0,currentOpenItems:0};Object.freeze(CharbaToast.defaults);Object.freeze(CharbaToast.helper);\"complete\"===document.readyState?CharbaToast.init():window.addEventListener(\"DOMContentLoaded\",CharbaToast.init);"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.toast.min.js</code> content.
	 */
	ToastJsUtilResource() {
		super(ResourceName.TOAST_JS_UTIL, CONTENT);
	}

}
