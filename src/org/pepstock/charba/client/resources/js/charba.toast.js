/**
  Copyright 2021 Andrea "Stock" Stocchero

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
// ------------------------------
// creates toasting GLOBAL object
// ------------------------------
// sets default create function in case
// toasting creation is attempted before dom has finished loading
CharbaToast = {
  init: function() {
    // -------------------------------
    // creates the element for toasting
    // MAIN CONTAINER
    // -------------------------------
    const container = document.createElement('div');
    container.id = 'ct-container';
    document.body.appendChild(container);
    // replaces "create" method when DOM has finished loading
    CharbaToast.create = function (pId, pOptions) {
      // creates 
      const result = Object.assign({}, pOptions || {});
      // manages options 
      const options = Object.assign({}, CharbaToast.defaults, CharbaToast.overrides, result);
      // checks consistenncy of toast
      const hasTitle = typeof options.title.content === 'string';
      const hasLabel = Array.isArray(options.label.content) && options.label.content.length > 0;
      // If not consistent
      // returns null
      if (!hasTitle && !hasLabel) {
        return null;
      }
      // -------------------------------
      // creates the element for toasting
      // -------------------------------
      const toasting = document.createElement('div');
      toasting.id = 'toast-' + pId;
      toasting.className = 'ct-toast';
      const wrapper = document.createElement('div');
      // -------------------------------
      // BORDER RADIUS
      // -------------------------------
      wrapper.style.borderRadius = options.borderRadius + 'px';
      // -------------------------------
      // BOX SHADOW
      // -------------------------------
      if (options.hideShadow) {
        wrapper.style.boxShadow = 'none';
      }
      // -------------------------------
      // TITLE
      // -------------------------------
      if (hasTitle) {
        const title = document.createElement('h4');
        title.className = 'ct-title';
        title.innerHTML = options.title.content;
        // title color
        if (typeof options.title.color === 'string') {
          title.style.color = options.title.color;
        }
        if (typeof options.title.font === 'object') {
          // normalizes font 
          const font = Object.assign({}, CharbaToast.defaults.titleFont, options.title.font);
          const normFont = CharbaToast.helper.toFont.apply(this, [font]);
          title.style.font = normFont.string;
        }
        wrapper.appendChild(title);
      }
      // -------------------------------
      // LABEL
      // -------------------------------
      if (hasLabel) {
        const text = document.createElement('p');
        text.className = 'ct-text';
        text.innerHTML = options.label.content.join('<br/>');
        // title color
        if (typeof options.label.color === 'string') {
          text.style.color = options.label.color;
        }
        if (typeof options.label.font === 'object') {
          // normalizes font 
          const font = Object.assign({}, CharbaToast.defaults.labelFont, options.label.font);
          const normFont = CharbaToast.helper.toFont.apply(this, [font]);
          text.style.font = normFont.string;
        }
        wrapper.appendChild(text);
      }
      // -------------------------------
      // ICON
      // -------------------------------
      if (options.icon && options.icon.src) {
        wrapper.classList += ' img';
        const icon = document.createElement('img');
        icon.src = options.icon.src;
        icon.className = 'ct-icon';
        wrapper.appendChild(icon);
      }
      // -------------------------------
      // PROGRESS BAR
      // -------------------------------
      if (!options.hideProgressBar) {
        const cssAnimation = document.createElement('style');
        cssAnimation.id = 'style-' + toasting.id;
        cssAnimation.type = 'text/css';
        const rules = document.createTextNode(`
            @keyframes animate-${pId} {
              0% {
                width: 100%
              }
              100% {
                width: 0%;
              }
            }
        `);
        cssAnimation.appendChild(rules);
        document.getElementsByTagName('head')[0].appendChild(cssAnimation);
        const progressBar = document.createElement('div');
        progressBar.classList = 'progress-bar';
        progressBar.style.animationName = 'animate-' + pId;
        progressBar.style.animationDuration = options.timeout / 1000 + 's';
        progressBar.style.animationTimingFunction = 'linear';
        progressBar.style.animationFillMode = 'forwards';
        if (options.progressBarType) {
          progressBar.classList += ' ' + options.progressBarType;    
        }
        wrapper.appendChild(progressBar);
      }
      // -------------------------------
      // Custom CLICK EVENT callback
      // -------------------------------
      if (typeof options.onClick === 'function') {
        toasting.addEventListener('click', (event) => options.onClick.apply(this, [result, event]));
      }
      // sets status
      result.status = 'showing';
      // -------------------------------
      // HIDING / AUTO HIDE
      // -------------------------------
      toasting.hide = function () {
        toasting.className += ' ct-fadeOut';
        toasting.addEventListener('animationend', removeToast, false);
        return null;
      };
      if (options.autoHide) {
        setTimeout(toasting.hide, options.timeout + 200);
      }
      // -------------------------------
      // TYPE
      // -------------------------------
      if (options.type) {
        wrapper.className += ' ct-' + options.type;
      } else {
        wrapper.className += ' ct-default';
      }
      // -------------------------------
      // Internal CLICK EVENT callback
      // -------------------------------
      toasting.addEventListener('click', toasting.hide);
      // -------------------------------
      // REMOVE toast
      // -------------------------------
      function removeToast() {
        document.getElementById('ct-container').removeChild(toasting);
        const style = document.querySelector('#style-' + toasting.id);
        if (style) {
          style.remove();
        }
        result.status = 'shown';
        result.closeDateTime = Date.now();
        CharbaToast.currentOpenItems--;
        // checks and calls callback
        if (typeof options.onClose === 'function') {
          options.onClose.apply(this, [result]);
        }
        // checks fo internal close handler
        if (typeof CharbaToast.onClose === 'function') {
          CharbaToast.onClose.apply(this, [result]);
        }
      }    
      // -------------------------------
      // ADD to DOCUMENT
      // -------------------------------
      toasting.appendChild(wrapper);
      document.getElementById('ct-container').appendChild(toasting);
      result.id = pId;
      result.openDateTime = Date.now();
      result.element = toasting;
      CharbaToast.currentOpenItems++;
      // checks and calls callback
      if (typeof options.onOpen === 'function') {
        options.onOpen.apply(this, [result]);
      }
      return result;
    }
  },
  create: function () {
    console.error(['DOM has not finished loading.', '\tInvoke create method when DOM\s readyState is complete'].join('\n'));
  },
  helper: {
    lineHeightRegExp: new RegExp(/^(normal|(\d+(?:\.\d+)?)(px|em|%)?)$/),
    fontStyleRegExp: new RegExp(/^(normal|italic|initial|inherit|unset|(oblique( -?[0-9]?[0-9]deg)?))$/), 
    /**
     * ToLineHeight
     */
    toLineHeight: function(value, size) {
      const matches = ('' + value).match(CharbaToast.helper.lineHeightRegExp);
      if (!matches || matches[1] === 'normal') {
        return size * 1.2;
      }
      value = +matches[2];
      switch (matches[3]) {
      case 'px':
        return value;
      case '%':
        value /= 100;
        break;
      default:
        break;
      }
      return size * value;
    },
    /**
     * ToFont
     */
    toFont: function(options) {
      let size = options.size;
      if (typeof size === 'string') {
        size = parseInt(size, 10);
      }
      let style = options.style;
      if (style && !('' + style).match(CharbaToast.helper.fontStyleRegExp)) {
        console.warn('Invalid font style specified: "' + style + '"');
        style = '';
      }
      const font = {
        family: options.family,
        lineHeight: CharbaToast.helper.toLineHeight.apply(this, [options.lineHeight, size]),
        size,
        style,
        weight: options.weight,
        string: ''
      };
      font.string = (font.style ? font.style + ' ' : '')
		+ (font.weight ? font.weight + ' ' : '')
		+ font.size + 'px '
		+ font.family;
		
      return font;
    }
  },
  defaults: {
    autoHide: true,
    borderRadius: 8,
    hideProgressBar: false,
    hideShadow: false,
    icon: undefined,
    onClick: undefined,
    onClose: undefined,
    onOpen: undefined,
    progressBarType: undefined,
    title: {
      content: undefined,
      color: undefined,
      font: undefined
    },
    label: {
      content: undefined,
      color: undefined,
      font: undefined
    },
    timeout: 4000,
    type: undefined,
    titleFont: {
      family: 'system-ui, -apple-system, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji"',
      lineHeight: 1.2,
      size: 15,
      style: 'normal',
      weight: 'bold',
    },
    labelFont: {
      family: 'system-ui, -apple-system, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji"',
      lineHeight: '20px',
      size: 14,
      style: 'normal',
      weight: 'normal',
    }
  },
  overrides: {},
  onClose: undefined, // internal use only
  currentOpenItems : 0
};
// freeze defaults and helper
Object.freeze(CharbaToast.defaults);
Object.freeze(CharbaToast.helper);
// needs DOM to be ready
if (document.readyState === 'complete') {
  CharbaToast.init();
} else {
  window.addEventListener('DOMContentLoaded', CharbaToast.init);
}
