/**
 * http://usejsdoc.org/
 */

var SuggestionChangeType;
(function (SuggestionChangeType) {
    SuggestionChangeType[SuggestionChangeType["UNKNOWN"] = 0] = "UNKNOWN";
    SuggestionChangeType[SuggestionChangeType["COMMENT_ONLY"] = 1] = "COMMENT_ONLY";
})(SuggestionChangeType || (SuggestionChangeType = {}));
Object.defineProperties(SuggestionChangeType, {
	values: { value: [ SuggestionChangeType[0], SuggestionChangeType[1] ], writable: false}
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
 
function SuggestionChange(changeType, targetQualifier, target, change, note) {
	this.changeType = changeType || SuggestionChangeType.UNKNOWN;
	this.targetQualifier = targetQualifier || SuggestionTargetQualifier.UNKNOWN;
	this.target = target || null;
	this.change = change || null;
	this.note = note || null;
};
 
function SuggestPlugin(editor, url) {
	this.changeSet = {
		"changes": []
	};
	
	var sc = new SuggestionChange();

	// Add a button that opens a window
    editor.addButton('suggest', {
        text: 'Suggest Change',
        icon: false,
        onclick: this.onButtonClick
    });

    // Adds a menu item to the tools menu
    editor.addMenuItem('suggest', {
        text: 'Suggest Mode',
        context: 'tools',
        onclick: this.onMenuClick
    });
    
    editor.addCommand('suggestCreateChange', this.suggestCreateChange);
    
    editor.on('init', this.onInit);
}

SuggestPlugin.prototype.onInit = function() {
}

SuggestPlugin.prototype.getChangeSet = function() {
	return this.changeSet;
}

SuggestPlugin.prototype.onButtonClick = function() {
}

SuggestPlugin.prototype.onMenuClick = function() {
}

SuggestPlugin.prototype.suggestCreateChange = function() {
}

tinymce.PluginManager.add('suggest', SuggestPlugin);