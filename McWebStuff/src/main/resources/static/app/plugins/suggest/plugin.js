/**
 * http://usejsdoc.org/
 */
function SuggestPlugin(editor, url) {
	this.changeSet = {
		"changes": []
	};

	// Add a button that opens a window
    editor.addButton('suggest', {
        text: 'Suggest',
        icon: false,
        onclick: this.onButtonClick
    });

    // Adds a menu item to the tools menu
    editor.addMenuItem('suggest', {
        text: 'Suggest plugin',
        context: 'tools',
        onclick: this.onMenuClick
    });
    
    editor.addCommand('suggestCreateChange', this.suggestCreateChange);
    
    editor.on('init', this.onInit);
}

SuggestPlugin.prototype = Object.create(Object.prototype);
SuggestPlugin.prototype.constructor = SuggestPlugin;

SuggestPlugin.prototype.onInit = function() {
	console.log('On Init');
}

SuggestPlugin.prototype.getChangeSet = function() {
	return this.changeSet;
}

SuggestPlugin.prototype.onButtonClick = function() {
	console.log('On Button Click');
}

SuggestPlugin.prototype.onMenuClick = function() {
	console.log('On Menu Click');
}

SuggestPlugin.prototype.suggestCreateChange = function() {
}

tinymce.PluginManager.add('suggest', SuggestPlugin);