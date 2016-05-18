/**
 * A Suggestion change plugin for the tinyMce editor
 */

var SuggestionChangeType;
(function (SuggestionChangeType) {
    SuggestionChangeType[SuggestionChangeType["UNKNOWN"] = 0] = "UNKNOWN";
    SuggestionChangeType[SuggestionChangeType["COMMENT_ONLY"] = 1] = "COMMENT_ONLY";
})(SuggestionChangeType || (SuggestionChangeType = {}));
Object.defineProperties(SuggestionChangeType, {
	values: { value: [ SuggestionChangeType[0], SuggestionChangeType[1] 
	], writable: false}
});


var SuggestionTargetQualifier;
(function (SuggestionTargetQualifier) {
    SuggestionTargetQualifier[SuggestionTargetQualifier["UNKNOWN"] = 0] = "UNKNOWN";
    SuggestionTargetQualifier[SuggestionTargetQualifier["BEFORE"] = 1] = "BEFORE";
    SuggestionTargetQualifier[SuggestionTargetQualifier["AFTER"] = 2] = "AFTER";
    SuggestionTargetQualifier[SuggestionTargetQualifier["PHRASE"] = 3] = "PHRASE";
})(SuggestionTargetQualifier || (SuggestionTargetQualifier = {}));
Object.defineProperties(SuggestionTargetQualifier, {
	values: { value: [ SuggestionTargetQualifier[0], SuggestionTargetQualifier[1],
		SuggestionTargetQualifier[2], SuggestionTargetQualifier[3]
	], writable: false}
});
 
/**
 * Represents a suggested change to the document being edited.
 * @constructor
 * @param {SuggestionChangeType} changeType - The change type of this change.
 * @param {SuggestionTargetQualifier} targetQualifier - The qualifier which describes how to handle the target.
 * @param {string} target - The target of the change.
 */
function SuggestionChange(changeType, targetQualifier, target, change, note) {
	this.changeType = changeType || SuggestionChangeType.UNKNOWN;
	this.targetQualifier = targetQualifier || SuggestionTargetQualifier.UNKNOWN;
	this.target = target || null;
	this.change = change || null;
	this.note = note || null;
};
 
function SuggestPlugin(editor, url) {
	this.editor = editor;
	this.url = url;
	
	var enabled = false;
	this.enabled = enabled;
	
	var suggestMenuItem;	
	
	this.changeSet = {
		"changes": []
	};
	
	editor.addCommand('suggestions', function() {
		var dom = editor.dom;
		
		dom.toggleClass(editor.getBody(), 'suggestions');
		enabled = editor.dom.hasClass(editor.getBody(), 'suggestions');

		if (suggestMenuItem) {
			suggestMenuItem.active(dom.hasClass(editor.getBody(), 'suggestions'));
		}

		editor.fire('Suggestions');
    });
    
    editor.addCommand('suggestCreateChange', function() {
    });
	
		
	// Add a button that opens a window
    editor.addButton('suggest', {
        text: 'Suggest Change',
        icon: false,
        onclick: this.onButtonClick
    });

    // Adds a menu item to the tools menu
    editor.addMenuItem('suggest', {
		cmd: 'suggestions',    
        text: 'Suggestions',
        onPostRender: function() {
        	var self = this;
			self.active(enabled);
			editor.on('Suggestions', function() {
				self.active(editor.dom.hasClass(editor.getBody(), 'suggestions'));
			});        	
        },
		selectable: true,
		context: 'view',
		prependToContext: true
    });
        
    editor.on('init', function() {
    });
}

tinymce.PluginManager.add('suggest', SuggestPlugin);