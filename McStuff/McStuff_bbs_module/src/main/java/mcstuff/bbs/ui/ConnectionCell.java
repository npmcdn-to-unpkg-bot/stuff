package mcstuff.bbs.ui;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import mcstuff.bbs.model.BBSConnection;
import mcstuff.javafx.spring.SpringFXMLLoader.NodeWithController;
import mcstuff.modules.BBSModule;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) class ConnectionCell extends ListCell<BBSConnection> {
	
	@Autowired
	protected BBSModule bbsModule;
	
	@Autowired
	BBSModuleHomeController parentController;

	Parent cellTemplate;
	ConnectionCellController controller;
	ListView<BBSConnection> listView;
			
	@PostConstruct
	protected void init() throws IOException {
    	NodeWithController nodeWithController = bbsModule.getAppConfig().getFXMLLoader().load("/mcstuff/bbs/ui/ConnectionCell.fxml");
    	cellTemplate = (Parent)nodeWithController.node; 
    	controller = (ConnectionCellController) nodeWithController.controller;        	
    	setGraphic(cellTemplate); 
    	controller.setCell(this);
	}
	
    @Override
    protected void updateItem(BBSConnection item, boolean empty) {
    	super.updateItem(item, empty);
    	controller.setConnection(item);
    }
    
    @Override
    public void updateSelected(boolean selected) {
    	super.updateSelected(selected);
    	controller.updateSelected(selected);
    }
}