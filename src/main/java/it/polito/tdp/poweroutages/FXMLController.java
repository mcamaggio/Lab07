/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbNerc"
    private ComboBox<Nerc> cmbNerc; // Value injected by FXMLLoader

    @FXML // fx:id="txtYears"
    private TextField txtYears; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    private Model model;
    
    @FXML
    void doRun(ActionEvent event) {
    
    	txtResult.clear();
    	
    	try {
    		
    		Nerc selectedNerc = cmbNerc.getSelectionModel().getSelectedItem();
    		if(selectedNerc == null) {
    			txtResult.setText("Select a NERC (area identifier)");
    			return;
    		}
    		
    		int maxY = Integer.parseInt(txtYears.getText());
    		if(maxY < 0) {
    			txtResult.setText("Insert a number of years greater than 0!");
    			return;
    		}
    		
    		int maxH = Integer.parseInt(txtHours.getText());
    		if(maxH < 0) {
    			txtResult.setText("Insert a number of hours greater than 0!");
    			return;
    		}
    		
    		txtResult.setText(String.format("Computing the worst case analysis... for %d hours and %d years", maxH, maxY));
    		
    		List<PowerOutages> worstCase = model.getWorstCase(maxY, maxH, selectedNerc);
    		
    		txtResult.clear();
    		txtResult.appendText("Tot people affected: " +model.sumAffectedPeople(worstCase) + "\n");
    		txtResult.appendText("Tot hours of outage: " +model.sumOutageHours(worstCase) + "\n");
    		
    		for(PowerOutages ee : worstCase) {
    			txtResult.appendText(String.format("%d %s %s %d %d", ee.getYear(), ee.getOutageStart(),
    							ee.getOutageEnd(), ee.getOutageDuration(), ee.getAffectedPeople()));
    			txtResult.appendText("\n");
    		}
    		
    	} catch (NumberFormatException e) {
    		txtResult.setText("Insert a valid number of years and of hours!");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbNerc != null : "fx:id=\"cmbNerc\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
        // Utilizzare questo font per incolonnare correttamente i dati;
        txtResult.setStyle("-fx-font-family: monospace");
    }
    
    private void setChoiceNerc() {
    	cmbNerc.getItems().addAll(model.getNercList());
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	setChoiceNerc();
    }
}
