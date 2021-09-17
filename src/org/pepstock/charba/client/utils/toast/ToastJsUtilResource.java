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
		"CharbaToast={init:function $CharbaToast$init$(){var $container$$=document.createElement(\"div\");$container$$.id=\"ct-container\";document.body.appendChild($container$$);CharbaToast.create=function $CharbaToast$create$($pId$$,$pOptions$$){function $removeToast$$(){document.getElementById(\"ct-container\").removeChild($toasting$$);var $style$$=document.querySelector(\"#style-\"+$toasting$$.id);$style$$&&$style$$.remove();$result$$.showing=!1;\"function\"===typeof $options$$.onClose&&$options$$.onClose.apply(this,",
		"[$result$$])}var $$jscomp$this$$=this,$result$$=Object.assign({},$pOptions$$||{}),$options$$=Object.assign({},CharbaToast.defaults,CharbaToast.overrides,$result$$),$toasting$$=document.createElement(\"div\");$toasting$$.id=\"toast-\"+$pId$$;$toasting$$.className=\"ct-toast\";var $wrapper$$=document.createElement(\"div\");$wrapper$$.style.borderRadius=$options$$.borderRadius+\"px\";var $cssAnimation_icon_progressBar_text$$10_title$$=document.createElement(\"h4\");$cssAnimation_icon_progressBar_text$$10_title$$.className=",
		"\"ct-title\";$cssAnimation_icon_progressBar_text$$10_title$$.innerHTML=$options$$.title.content;\"string\"===typeof $options$$.title.color&&($cssAnimation_icon_progressBar_text$$10_title$$.style.color=$options$$.title.color);if(\"object\"===typeof $options$$.title.font){var $font$$=Object.assign({},CharbaToast.defaults.titleFont,$options$$.title.font),$font$$=CharbaToast.helper.toFont.apply(this,[$font$$]);$cssAnimation_icon_progressBar_text$$10_title$$.style.font=$font$$.string}$wrapper$$.appendChild($cssAnimation_icon_progressBar_text$$10_title$$);",
		"Array.isArray($options$$.label.content)&&($cssAnimation_icon_progressBar_text$$10_title$$=document.createElement(\"p\"),$cssAnimation_icon_progressBar_text$$10_title$$.className=\"ct-text\",$cssAnimation_icon_progressBar_text$$10_title$$.innerHTML=$options$$.label.content.join(\"<br/>\"),\"string\"===typeof $options$$.label.color&&($cssAnimation_icon_progressBar_text$$10_title$$.style.color=$options$$.label.color),\"object\"===typeof $options$$.label.font&&($font$$=Object.assign({},CharbaToast.defaults.labelFont,",
		"$options$$.label.font),$font$$=CharbaToast.helper.toFont.apply(this,[$font$$]),$cssAnimation_icon_progressBar_text$$10_title$$.style.font=$font$$.string),$wrapper$$.appendChild($cssAnimation_icon_progressBar_text$$10_title$$));$options$$.icon&&$options$$.icon.src&&($wrapper$$.classList+=\" img\",$cssAnimation_icon_progressBar_text$$10_title$$=document.createElement(\"img\"),$cssAnimation_icon_progressBar_text$$10_title$$.src=$options$$.icon.src,$cssAnimation_icon_progressBar_text$$10_title$$.className=",
		"\"ct-icon\",$wrapper$$.appendChild($cssAnimation_icon_progressBar_text$$10_title$$));$options$$.hideProgressBar||($cssAnimation_icon_progressBar_text$$10_title$$=document.createElement(\"style\"),$cssAnimation_icon_progressBar_text$$10_title$$.id=\"style-\"+$toasting$$.id,$cssAnimation_icon_progressBar_text$$10_title$$.type=\"text/css\",$font$$=document.createTextNode(\"\\n            @keyframes animate-\"+$pId$$+\" {\\n              0% {\\n                width: 100%\\n              }\\n              100% {\\n                width: 0%;\\n              }\\n            }\\n        \"),",
		"$cssAnimation_icon_progressBar_text$$10_title$$.appendChild($font$$),document.getElementsByTagName(\"head\")[0].appendChild($cssAnimation_icon_progressBar_text$$10_title$$),$cssAnimation_icon_progressBar_text$$10_title$$=document.createElement(\"div\"),$cssAnimation_icon_progressBar_text$$10_title$$.classList=\"progress-bar\",$cssAnimation_icon_progressBar_text$$10_title$$.style.animationName=\"animate-\"+$pId$$,$cssAnimation_icon_progressBar_text$$10_title$$.style.animationDuration=$options$$.timeout/1E3+",
		"\"s\",$cssAnimation_icon_progressBar_text$$10_title$$.style.animationTimingFunction=\"linear\",$cssAnimation_icon_progressBar_text$$10_title$$.style.animationFillMode=\"forwards\",$options$$.progressBarType&&($cssAnimation_icon_progressBar_text$$10_title$$.classList+=\" \"+$options$$.progressBarType),$wrapper$$.appendChild($cssAnimation_icon_progressBar_text$$10_title$$));\"function\"===typeof $options$$.onClick&&$toasting$$.addEventListener(\"click\",function($event$$){return $options$$.onClick.apply($$jscomp$this$$,",
		"[$result$$,$event$$])});$result$$.showing=!0;$toasting$$.hide=function $$toasting$$$hide$(){$toasting$$.className+=\" ct-fadeOut\";$toasting$$.addEventListener(\"animationend\",$removeToast$$,!1);return null};$options$$.autoHide&&setTimeout($toasting$$.hide,$options$$.timeout+200);$wrapper$$.className=$options$$.type?$wrapper$$.className+(\" ct-\"+$options$$.type):$wrapper$$.className+\" ct-default\";$toasting$$.addEventListener(\"click\",$toasting$$.hide);$toasting$$.appendChild($wrapper$$);document.getElementById(\"ct-container\").appendChild($toasting$$);",
		"$result$$.id=$pId$$;$result$$.dateTime=Date.now();$result$$.element=$toasting$$;\"function\"===typeof $options$$.onOpen&&$options$$.onOpen.apply(this,[$result$$]);return $result$$}},create:function $CharbaToast$create$(){console.error(\"DOM has not finished loading.\\n\\tInvoke create method when DOMs readyState is complete\")},helper:{lineHeightRegExp:new RegExp(/^(normal|(\\d+(?:\\.\\d+)?)(px|em|%)?)$/),fontStyleRegExp:new RegExp(/^(normal|italic|initial|inherit|unset|(oblique( -?[0-9]?[0-9]deg)?))$/),toLineHeight:function $CharbaToast$helper$toLineHeight$($value$$,",
		"$size$$){var $matches$$=(\"\"+$value$$).match(CharbaToast.helper.lineHeightRegExp);if(!$matches$$||\"normal\"===$matches$$[1])return 1.2*$size$$;$value$$=+$matches$$[2];switch($matches$$[3]){case \"px\":return $value$$;case \"%\":$value$$/=100}return $size$$*$value$$},toFont:function $CharbaToast$helper$toFont$($font$$4_options$$){var $size$$=$font$$4_options$$.size;\"string\"===typeof $size$$&&($size$$=parseInt($size$$,10));var $style$$=$font$$4_options$$.style;$style$$&&!(\"\"+$style$$).match(CharbaToast.helper.fontStyleRegExp)&&",
		"(console.warn('Invalid font style specified: \"'+$style$$+'\"'),$style$$=\"\");$font$$4_options$$={family:$font$$4_options$$.family,lineHeight:CharbaToast.helper.toLineHeight.apply(this,[$font$$4_options$$.lineHeight,$size$$]),size:$size$$,style:$style$$,weight:$font$$4_options$$.weight,string:\"\"};$font$$4_options$$.string=($font$$4_options$$.style?$font$$4_options$$.style+\" \":\"\")+($font$$4_options$$.weight?$font$$4_options$$.weight+\" \":\"\")+$font$$4_options$$.size+\"px \"+$font$$4_options$$.family;return $font$$4_options$$}},",
		"defaults:{autoHide:!0,borderRadius:8,hideProgressBar:!1,icon:void 0,onClick:void 0,onClose:void 0,onOpen:void 0,progressBarType:void 0,title:{content:\"Default title\",color:void 0,font:void 0},label:{content:void 0,color:void 0,font:void 0},timeout:4E3,type:void 0,titleFont:{family:'system-ui, -apple-system, \"Segoe UI\", Roboto, Helvetica, Arial, sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\"',lineHeight:1.2,size:15,style:\"normal\",weight:\"bold\"},labelFont:{family:'system-ui, -apple-system, \"Segoe UI\", Roboto, Helvetica, Arial, sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\"',",
		"lineHeight:\"20px\",size:14,style:\"normal\",weight:\"normal\"}},overrides:{}};Object.freeze(CharbaToast.defaults);Object.freeze(CharbaToast.helper);\"complete\"===document.readyState?CharbaToast.init():window.addEventListener(\"DOMContentLoaded\",CharbaToast.init);"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.toast.min.js</code> content.
	 */
	ToastJsUtilResource() {
		super(ResourceName.TOAST_JS_UTIL, CONTENT);
	}

}