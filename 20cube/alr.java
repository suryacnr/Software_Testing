package iSAFE;

public class ALR {
	
	
	// New Booking Popup Labels
	
	public static String Origin_booking             ="Export";
	public static String distination_booking        ="Import";
	public static String crossTrade_booking         ="CrossTrade";
	public static String SLIBooking_booking         ="SLIBooking";
	public static String Create_Booking             ="Create";
	
	
	// Consol module Tap Lable
	
	public static String Organization_label         = "Organization";
	public static String Arrival_label              = "Arrival";
	public static String Departure_label            = "Departure";
	public static String Generalheader_lable        ="General";
	
	// Consol module Left Labels
	
	public static String Arrivaldeparture_label     ="Arrival & Departure";
	public static String Routing_label              ="Routing";
	public static String General_lable              ="General";
	
	
	// Create new Consol module Labels(General tab)
	
	public static String consol_label               ="Consol";
	public static String AgentTypedropdrown_label   ="Agent Type";
	public static String Mode_of_TransportLabel     =" Mode of Transport";
	public static String Packing_ModeLabel          ="Packing Mode";
	public static String BOL_textLabel              ="BOL";	
	public static String MBL_TypeLabel              ="MBL Type";
	public static String FristloadLabel             ="First Load";
	public static String LastDischargeLabel         ="Last Discharge";
	public static String PhaseLabel                 ="Phase";
	public static String Service_levelLabel         ="Service level";
	public static String MBL_Payment_termsLabel     ="MBL Payment Terms";
	public static String MBL_Formatlabel            ="MBL Format";
	
	// consol module (Organization tab)
	
	public static String SendingAgentname_label     = "Sending Agent";
	public static String ReceivingAgentname_label   = "Receiving Agent";
	public static String Carrier_label              = "Carrier";
	public static String Creditor_label             = "Creditor";
	public static String Agent_Ref_label            = "Agent Ref";
	public static String CarrierBkgRef_label        = "Carrier Bkg. Ref";
	
	
	// consol module (Arrival departure)
	public static String COT_label                  = "CTO";
	public static String CFS_label                  = "CFS";
	public static String Containeryard_label        = "Container Yard";
	public static String Last_Foreign_Port_label    = "Last Foreign Port";
//	public static String 
	
	
	
	// Consol module add routing leg
	
	public static String mode_label                 = "mode";
	public static String type_label                 = "type";
	public static String vessel_label               ="vessel";
	public static String VoyageFlight_label         ="voyageflight";
	public static String ETAdate_label              ="eta";
	public static String ETDdate_label              ="etd";
	public static String VerifyPOL_label            ="Load";
	public static String VerifyPOD_label            ="Discharge";
	public static String POD_label                  ="pod";
	public static String POL_label                  ="pol";
	
	// Consol module Error Warning Code
	
	public static String Vessel_errorcode           ="EF0110 - Vessel is Mandatory in Leg 1";
	public static String Voyage_errorcode           ="EF0115 - Voyage/Flight is Mandatory in Leg 1";
	public static String POD_leg1_errorcode         ="EF0131 - POD is Mandatory in Leg 1";
	public static String POL_leg1_errorcode         ="EF0098 - POL is Mandatory in Leg 1";
	public static String ETA_leg1_errorcode         ="EF0104 - ETA is Mandatory in Leg 1";
	public static String ETD_leg1_errorcode         ="EF0128 - ETD is Mandatory in Leg 1";
	public static String POD_leg2_errorcode         ="EF0131 - POD is Mandatory in Leg 2";
	public static String ETA_leg2_errorcode	        ="EF0104 - ETA is Mandatory in Leg 2";
	
	// Main Menu Labels
	
	public static String dashboardLabel  		    = "Dashboard";
	public static String myTasksLabel   		    = "My Tasks";	
	public static String warehouseLabel   		    = "Warehouse";
	public static String controlTowerLabel 		    = "Control Tower";
	public static String mastersLabel 	  	        = "Masters";		
	
	//Warehouse SubMenu Labels
	
	public static String inwardLabel  			    = "Inward";
	public static String outwardLabel   		    = "Outward";
	public static String pickLabel  			    = "Pick";
	public static String releaseLabel		        = "Release";
	public static String adjustmentsLabel 		    = "Adjustments";
	public static String stockTransferLabel    	    = "Stock Transfer";
	public static String ownershipTransferLabel     = "Ownership Transfer";
	public static String cycleCountLabel   		    = "Cycle Count";
	public static String inventoryLabel   		    = "Inventory";
	public static String warehouseReportsBetaLabel  = "Warehouse Reports Beta";
	public static String wMSReportsLabel  		    = "WMS Reports";
	public static String allDocumentsLabel  	    = "All Documents";
	
	//Masters SubMenu Labels
	
	public static String organizationLabel  	    = "Organization";
	public static String productsLabel  		    = "Products";
	public static String warehouseMasterLabel  	    = "Warehouse Master";
	public static String warehouseAreaLabel  	    = "Warehouse Area";
	public static String warehouseLocationLabel     = "Warehouse Location";
	public static String organizationCodeAndNameLabel     = "Organization Name/Code";
	
	
	//Inward Header Labels
	
	public static String headerLabel  	    		= "Header";
	public static String systemFiltersLabel 		= "System Filters";
	public static String savedFiltersLabel  		= "Saved Filters";
	public static String exportLabel  	    		= "Export";
	public static String recentLabel  	    		= "Recent";
	public static String scheduleLabel      		= "Schedule";
	public static String refreshLabel  	   		 	= "Refresh";
	public static String resetLabel  	    		= "Reset";
	public static String newLabel  	    			= "New";
	public static String filterLabel  	    		= "Filter";
	public static String itemsPerPageLabel  		= "items per page";
	
	//Inward Filter Labels
	
	public static String inwardNoLabel  	    	= "Inward No";
	public static String statusLabel      			= "Status";
	public static String clientLabel  			    = "Client";
	public static String inwardReferenceLabel  		= "Inward Reference";
	public static String customerReferenceLabel  	= "Customer Reference";
	public static String bookingDateLabel  		    = "Booking Date";
	public static String arrivalDateLabel  			= "Arrival Date";
	public static String finalizedDateLabel  		= "Finalized Date";
	public static String supplierLabel  			= "Supplier";
	public static String totalPalletsLabel 			= "Total Pallets";
	public static String totalPackTypeLabel 		= "Total Pack Type";
	public static String saveAsFilterLabel 		    = "Save as Filter";
	public static String clearLabel 		        = "Clear";
	public static String applyLabel 		        = "Apply";
	
	//Inward SubMenu Labels
	
	public static String generalLabel  			    = "General";
	public static String aSNLinesLabel		  	    = "ASN Lines";
	public static String receiveLinesLabel  	    = "Receive Lines";
	public static String productSummaryLabel  	    = "Product Summary";
	public static String containersLabel  	 	    = "Containers";
	public static String referencesAndServicesLabel = "References & Services";
	public static String documentsLabel  	   	    = "Documents";
	
	//Inward General Labels
	
	public static String clientCodeAndNameLabel 	= "Client Code And Name";
	public static String wareHouseCodeAndNameLabel	= "Warehouse Code And Name";
	public static String supplierCodeAndNameLabel	= "Supplier Code And Name";
	public static String inwardTypeLabel  			= "Inward Type";
	public static String eTDLabel  				    = "ETD";
	public static String eTALabel  				    = "ETA";	
	
	public static String cargoReadyDateLabel		= "Cargo Ready Date";
	public static String supplierInvoiceLabel		= "Supplier Invoice #";
	public static String factoryInvoiceLabel		= "Factory Invoice #";
	public static String factoryPKLLabel			= "Factory PKL #";
	public static String buyingHouseAgentLabel		= "Buying House Agent";
	public static String stuffingTypeLabel			= "Stuffing Type";
	public static String additionalTerms			= "Additional Terms";
	public static String goodsDesc					= "Goods Desc";
	public static String putawayStartedDateLabel  	= "Putaway Started Date";
	public static String putawayCompletedDateLabel  = "Putaway Completed Date";
	public static String totalPackagesLabel 		= "Total Packages";
	public static String totalUnitsLabel 			= "Total Units";
	public static String saveLabel  				= "Save";
	public static String moreLabel  				= "More";	
	
	//Inward More Labels
	
	public static String saveAndCloseLabel  		= "Save & Close";
	public static String finalizeMoreLabel  		= "Finalize";
	public static String cancelInwardLabel  		= "Cancel Inward";
	public static String okLabel  		            = "OK";
	public static String yesLabel           		= "YES";
	public static String noLabel    			    = "NO";
	public static String cancelLabel    			= "Cancel";
	
	//Inward ASN Lines Labels
	
	public static String addNewLabel  				= "Add New";
	public static String copyLabel  				= "Copy";
	public static String deleteLabel  				= "Delete";
	public static String customizeLabel  			= "Customize";
	public static String bulkUploadLabel  			= "Bulk Upload";
	public static String downloadTemplateLabel  	= "Download Template";
	public static String searchLabel  				= "Search";
	public static String sNoLabel  					= "S.No";
	public static String productCodeLabel  			= "Product Code";
	public static String productDescriptionLabel  	= "Product Description";
	public static String commodityLabel  			= "Commodity";
	public static String packLabel  			    = "Pack";
	public static String packTypeLabel  			= "Pack Type";
	public static String quantityLabel  			= "Quantity";
	public static String quantityUQLabel  			= "Quantity UQ";
	public static String productConditionLabel  	= "Product Condition";
	public static String palletIDLabel  			= "Pallet ID";
	public static String serialNumberLabel  		= "SERIAL NUMBER";
	public static String mandatoryValueLabel  		= "MANDATORY VALUE";
	public static String nonMandatoryLabel  		= "NON MANDATORY";
	public static String uDF1Label  				= "UDF 1";
	public static String uDF2Label  				= "UDF 2";
	public static String uDF3Label  				= "UDF 3";
	public static String packingDateLabel  			= "Packing Date";
	public static String expiryDateLabel  			= "Expiry Date";
	public static String additionalReference1Label  = "Additional Reference1";
	public static String recordsPerPageLabel       	= "Records Per Page.";
	public static String convertAllLinesAsReceiptLabel = "Convert All Lines As Receipt";
	
	////Inward Customize Labels
	
	public static String heightLabel            	= "Height";
	public static String userLevelSaveLabel        	= "User Level Save";
	public static String doneLabel            	    = "Done";
	
	//Inward Receive Lines Labels
	
	public static String locationLabel              = "Location";
	public static String locationStatusLabel        = "Location Status";
	public static String areaTypeLabel              = "Area Type";
	public static String areaNameLabel              = "Area Name";
	public static String commentsInvoiceNoLabel     = "Comments / Invoice No";
	public static String allocateLocationLabel      = "Allocate Location";
	
		
	//Inward Product Summary Labels
	
	public static String expectedPacksLabel  		= "Expected Packs";
	public static String receivedPacksLabel  		= "Received Packs";
	public static String expectedQuantityLabel  	= "Expected Quantity";
	public static String receivedQuantityLabel  	= "Received Quantity";
	public static String damagedQuantityLabel   	= "Damaged Quantity";
	
	//Inward Containers Labels
	
	public static String containerNoLabel  	        = "Container No";
	public static String sealNoLabel 			 	= "Seal No";
	public static String typeLabel				  	= "Type";
	public static String chargeableLabel  			= "Chargeable";
	public static String palletizedLabel  			= "Palletized";
	public static String itemsCountLabel  			= "Items count";
	public static String itemCountLabel  			= "Item Count";
	public static String palletsCountLabel  		= "Pallets Count";
	public static String palletCountLabel  		    = "Pallet Count";
			
	//Inward References & Services Labels
	
	public static String referenceTypeLabel  		= "Reference Type";
	public static String referenceLabel  			= "Reference";
	public static String dateBookedLabel  			= "Date Booked";
	public static String completedDateLabel  		= "Completed Date";
	public static String contractorLabel  			= "Contractor";
	public static String countLabel  				= "Count";
	public static String serviceLocationLabel  		= "Service Location";
	
	//Inward Documents Labels
	
	public static String aSNDocumentLabel  			= "ASN Document";
	public static String materialInwardNoteLabel  	= "Material Inward Note";
	public static String gRNNotificationLabel  		= "GRN Notification";
	public static String putawayDocumentLabel  		= "Putaway Document";
	public static String putawayConfirmationLabel  	= "Putaway Confirmation";
	public static String unloadingCheckListLabel  	= "Unloading CheckList";
	public static String pickupLabel	= "PickUp";
	
	//Outward Header Labels
	
	public static String batchUploadLabel  			= "Batch Upload";
	
	//Batch Upload Labels
	
	public static String uploadFileLabel  			= "Upload File";
	public static String viewUploadedFilesLabel     = "View Uploaded Files";
	public static String instanceIDLabel            = "Instance ID";
	public static String fileNameLabel              = "File Name";
	public static String uploadedByLabel            = "Uploaded By";
	public static String uploadedDateTimeLabel      = "Uploaded DateTime";
	public static String RemarksLabel               = "Remarks";
	public static String EntitySourceLabel          = "EntitySource";
	
	//Outward Filter Labels
	
	public static String outwardNoLabel  			= "Outward No";
	public static String warehouseCodeLabel  		= "Warehouse Code";
	public static String orderNoLabel               = "Order No";
	public static String orderDateLabel             = "Order Date";
	public static String requiredDateLabel  	    = "Required Date";
	public static String pickNumberLabel  			= "Pick Number";
	public static String consigneeLabel  			= "Consignee";
	
	//Outward SubMenu Labels
	
	public static String lineLabel		  	    	= "Line";
	public static String dispatchLabel  	    	= "Dispatch";

	//Outward General Labels
	
	public static String consigneeCodeAndNameLabel  = "Consignee Code And Name";
	public static String orderNumberLabel  			= "Order Number";
	public static String pickOptionsLabel  		    = "Pick Options";
	public static String dropModeLabel  		    = "Drop Mode";
	public static String goodsDescriptionLabel 		= "Goods Description";
	public static String outwardTypeLabel 			= "Outward Type";
	
	//Outward More Labels
	
	public static String cancelOutwardLabel 		= "Cancel Outward";
	
	//Outward Line Labels

	public static String quantityMetLabel  	    	= "Quantity Met";
	public static String shortfallQtyLabel  	 	= "Shortfall Qty";
	public static String reservedQtyLabel  			= "Reserved Qty";
	public static String commentInvoiceNoLabel  	= "Comment / Invoice No";

	//Outward Pick Labels
	
	public static String pickOptionLabel  			= "Pick Option";	
	public static String unitsLabel  				= "Units";
	public static String uQLabel  					= "UQ";		
	public static String pickedDateTimeLabel  		= "Picked Date Time";
	
	//Pick Number Hyper link Header Labels
	
	public static String detailsLabel  			    = "Details";
	public static String pickAllocationHeaderLabel  = "Pick Allocation";
	public static String pickSlipLabel              = "Pick Slip";
	
	//Pick Details Label
	
	public static String attachLabel  			    = "Attach";
	public static String editLabel				    = "Edit";
	public static String detachLabel                = "Detach";
	public static String cancelPickLabel            = "Cancel Pick";
	
	//Pick Allocation Label
	
	public static String packsLabel                 = "Packs";
	public static String packsUqLabel               = "Packs UQ";
	public static String QtyUqLabel                 = "Qty UQ";
	public static String orderedQtyLabel            = "Ordered Qty";
	public static String allocatedQtyLabel          = "Allocated Qty";
	public static String allocateLabel  		    = "Allocate Stock";
	public static String deAllocateLabel  		    = "Deallocate Stock";
	public static String isAllocateLabel            = "Is Allocate";
	public static String inLocationQtyLabel         = "InLocation Qty";
	public static String availableToPickLabel       = "Available To Pick";
	public static String committedQtyLabel          = "Committed Qty";
	public static String inTransitQtyLabel          = "In Transit Qty";
	public static String totalQtyLabel              = "Total Qty";
	public static String inventoryStatusLabel       = "Inventory Status";
	
	//Pick Slip Label
	
	public static String qtyLabel                   = "Qty";
	public static String additionalRef1CodeLabel    = "Additional Ref1 Code";
	
	//Pick Documents Label
	
	public static String pickConfirmationSheetLabel = "Pick Confirmation Sheet";
	
	//Outward Dispatch Label
	
	//Outward Documents Label
	
	public static String outwardDocumentLabel       = "Outward Document";
	public static String orderSummaryDocumentLabel  = "Order Summary Document";
	public static String deliveryChallanDocumentLabel= "Delivery Challan Document";
	public static String bmwPackingListLabel        = "BMW Packing List";
	public static String loadingCheckListLabel      = "Loading CheckList";
	
	//Pick Filter Labels
	
	public static String pickNoLabel                = "Pick No";
	public static String createdDateTimeLabel       = "Created Date Time";
	
	//Release Header Labels
	
	public static String PackingLabel               = "Packing";
	
	//Release Details Label
	
	public static String completePickLabel          = "Complete Pick";
	public static String finalizeOutwardLabel  	    = "Finalize Outward";
	public static String createMaterialTransferInwardLabel = "Create Material Transfer Inward";
	public static String packedQuantityLabel  	    = "Packed Quantity";
	public static String unPackedQuantityLabel      = "UnPacked Quantity";
	public static String finalizePickLabel  		= "Finalize Pick";
	
	
	//Cycle Count Filter Labels
	
	public static String cycleCountNoLabel  		= "Cycle Count No";
	public static String dateLabel  		        = "Date";
	
	//Cycle Count Header Labels
	
	public static String linesLabel  		        = "Lines";
	
	//Cycle Count General Labels
	
	public static String stockTakeNumberLabel        = "Stock Take Number";
	public static String stockTakeDateLabel  	    = "Stock Take Date";
	public static String countEmptyLocationsLabel   = "Count Empty Locations";
	public static String loadNewStocktakeLabel      = "Load New Stocktake";
	
	//Cycle Count Document Label
	
	public static String cycleCountDocumentLabel      = "Cycle Count Document";
	public static String cycleCountVarianceDocumentLabel = "Cycle Count Variance Document";
	
	//Cycle Count More Label 
	
	public static String cancelCycleCountLabel      = "Cancel CycleCount";
	
	//Cycle Count Lines Labels
	
	public static String systemUnitsLabel           = "System Units";
	public static String actualCountLabel           = "Actual Count";
	public static String dateVerifiedLabel          = "Date Verified";
	public static String commentLabel               = "Comment";
	public static String dateClosedLabel            = "Date Closed";
	public static String closeLinesLabel            = "Close Lines";
	
	
	//Organization Filter Label
	
	public static String cityLabel  			    = "City"; 
	public static String stateLabel  			    = "State";
	public static String isForwarderLabel  		    = "IsForwarder";
	public static String shipperLabel  			    = "Shipper";
	public static String isConsignorLabel  		    = "IsConsignor";
	public static String isWarehouseClientLabel     = "IsWarehouseClient";
	public static String isConsigneeLabel  		    = "IsConsignee";
	public static String isBrokerLabel  		    = "IsBroker";
	public static String isCompetitorLabel  	    = "IsCompetitor";
	public static String isTransportClientLabel     = "IsTransportClient";
	public static String isServiceLabel  		    = "IsService";
	public static String isCarrierLabel  		    = "IsCarrier";
	
	//Organization Table
	
	public static String codeLabel  			    = "Code";
	public static String fullNameLabel  		    = "Full Name";
	public static String unlocoLabel  		        = "UNLOCO";
	
	//Organization General Label
	
	public static String organizationCodeLabel      = "Organization Code";
	public static String organizationFullNameLabel  = "Organization Full Name";
	public static String isActiveLabel  		    = "Is Active";
	public static String enableOrganizationCodeLabel= "Enable Organization Code";
	public static String consignorLabel  			= "Consignor";
	public static String forwarderLabel  			= "Forwarder";
	public static String transportClientLabel  		= "Transport Client";
	public static String warehouseClientLabel  		= "Warehouse Client";
	public static String brokerLabel  			    = "Broker";
	public static String roadFreightDepotLabel 		= "Road Freight Depot";
	public static String storeLabel  			    = "Store";
	public static String carrierLabel  			    = "Carrier";
	public static String servicesLabel  			= "Services";
	public static String address1Label  			= "Address1";
	public static String address2Label  			= "Address2";
	public static String regionLabel  			    = "Region";
	public static String zoneLabel  			    = "Zone";
    public static String relatedPortLabel  			= "Related Port";
	public static String countryLabel  			    = "Country";
	public static String postCodeLabel  			= "PostCode";
	public static String languageLabel  			= "Language";
	public static String phoneLabel  			    = "Phone";
	public static String mobileLabel  			    = "81234 56789";
	public static String emailLabel  			    = "Email";
	public static String faxLabel  			        = "Fax";
	public static String closeLabel  			    = "Close";														
	public static String generateScriptLabel  	    = "Generate Script";
	
	
	// edit label

	public static String address1_Label = "Address1";
	public static String address2_Label = "Address2";
	public static String contact_Name_Label = "Contact Name";
	public static String mail_Label = "Mail";
	public static String phone_label = "Phone";

	public static String email_Label = "Email";
	public static String contactName_Label = "name";

	public static String orgName_Label = "Name";
	
	//Generate Script Labels
	
	public static String tableLabel  	            = "Table";
	public static String pkLabel  	                = "PK";
	public static String tenantCodeLabel  	        = "TenantCode";		
	public static String insertLabel  	            = "Insert";				
	public static String updateLabel  	            = "Update";	
	public static String generateLabel  	        = "Generate";	
										
	//Organization Address Label						
	
	public static String setAsDefaultLabel  	    = "Set As Default";		
	//Organization Contact Label
	
	
	public static String contactNameLabel  	    	= "Contact Name";	
	public static String jobTitleLabel  	   	 	= "Job Title";
	public static String JobCategoryLabel  	    	= "JobCategory";
	public static String workPhoneLabel  	    	= "Work Phone";
	public static String mobilePhoneLabel  	    	= "Mobile Phone";
	public static String homePhoneLabel  	    	= "Home Phone";
	public static String otherPhoneLabel  	    	= "Other Phone";
	
	//Organization Company Label
	
	public static String CompanyLabel  	    		= "Company";
	public static String controllingBranchLabel  	= "Controlling Branch";
	public static String isPayablleLabel  	    	= "Is Payablle";
	public static String isReceivableLabel  	   	= "Is Receivable";
	
	//Organization Warehouse Label
	
	public static String productPreferenceLabel	    = "Product Preference";
	public static String ReceiveLabel	            = "Receive";
	public static String PickingLabel	            = "Picking";
	public static String BarcodeLabel	            = "Barcode";
	public static String consignmentConfigurationLabel = "Additional Configuration";
	
	//Product Preference Label
	
	public static String productConfigurationLabel  = "Product Configuration";
	public static String userDefinedField1NameLabel = "User Defined Field 1 Name";
	public static String uDF1NameLabel  			= "UDF 1 Name";		
	public static String userDefinedField2NameLabel = "User Defined Field 2 Name";
	public static String uDF2NameLabel  			= "UDF 2 Name";
	public static String userDefinedField3NameLabel = "User Defined Field 3 Name";
	public static String uDF3NameLabel  			= "UDF 3 Name";
	public static String userDefinedField1TypeLabel = "User Defined Field 1 Type";
	public static String userDefinedField2TypeLabel = "User Defined Field 2 Type";
	public static String userDefinedField3TypeLabel = "User Defined Field 3 Type";
	public static String usePackingDateLabel  		= "Use Packing Date";
	public static String useExpiryDateLabel  		= "Use Expiry Date";
	
	//Receive Label
	
	public static String receiveConfigurationLabel  = "Receive Configuration";
	public static String putawaySameProductTogetherLabel = "Putaway Same Product Together";
	public static String overridingSystemSettingLabel = "Overriding System Setting";
	public static String locationFallbackLabel      = "Location Fallback";
	public static String pickFaceLabel              = "Pick Face";
	public static String clientAreaLabel            = "Client Area";
	public static String productAreaLabel           = "Product Area";
	
	//Picking Label
	
	public static String pickingConfigurationLabel  = "Picking Configuration";
	public static String fifoFallbackLabel          = "FIFO Fallback";
	
	//Barcode Label
	
	public static String barcodeConfigurationLabel  = "Barcode Configuration";
	public static String barcodeTypeLabel           = "Barcode Type";
	public static String qrCodeLabel 				= "QR Code";
	public static String productRuleLabel           = "Product Rule";
	public static String udf1RuleLabel              = "UDF1 Rule";
	public static String udf2RuleLabel              = "UDF2 Rule";
	public static String udf3RuleLabel              = "UDF3 Rule";
	
	//Consignment Configuration Label
	
	public static String autoManifestLabel          = "Auto Manifest";
	
	//Product Label
	
	public static String productBulkUploadLabel     = "Product Bulk Upload";	
	
	//Product Bulk Upload Label
	
	public static String chooseYourOptionLabel      = "Choose your option";
	public static String uploadLabel                = "Upload";
	
	//Product Filter Label
	
	public static String productLabel  			    = "Product";
	public static String ownerLabel  			    = "Owners";
	public static String suppliersLabel  		    = "Suppliers";
	public static String brandLabel  			    = "Brand";
	public static String modelLabel  			    = "Model";
	
	//Product Header Labels
	
	public static String productAndRelatedOrganizationLabel = "Product & Related Organization";	
	public static String unitConversionsLabel  	    = "Unit Conversions";
	public static String barcodesLabel  	        = "Barcodes";
	public static String bomLabel  	                = "BOM";
	public static String additionalDetailsLabel     = "Additional Details";
	
	
	//Product & Related Organization Label	
	
	public static String stockUnitLabel 		    = "Stock Unit";
	public static String depthLabel                 = "Depth";
	public static String widthLabel                 = "Width";
	public static String grossWeightLabel           = "Gross Weight";
	public static String cubicWeightLabel           = "Cubic Weight";
	public static String netWeightLabel             = "Net Weight";
	public static String palletSizeLabel            = "Pallet Size";
	public static String RTLabel  		            = "Relationship Type";
	public static String clientUqLabel		        = "Client UQ";	
	public static String useUDF1Label  			    = "Use UDF 1";
	public static String useUDF2Label  		     	= "Use UDF 2";
	public static String useUDF3Label  			    = "Use UDF 3";	
	public static String isUdf1ReleaseCapturedLabel = "Is UDF1 ReleaseCaptured";
	public static String isUdf2ReleaseCapturedLabel	= "Is UDF2 ReleaseCaptured";
	public static String isUdf3ReleaseCapturedLabel	= "Is UDF3 ReleaseCaptured";
	public static String pickModeLabel		        = "Pick Mode";
	public static String localPartNumberLabel		= "Local Part Number";
	public static String localPartDescriptionLabel  = "Local Part Description";	
	
	//Unit Conversions Organization Label	
	
	public static String quantityInParentLabel      = "Quantity In Parent";
	public static String packageLabel               = "Package";
	public static String parentPackageLabel         = "Parent Package";
	
	//Warehouse Label
	
	public static String replenishmentMinimumLabel  = "Replenishment Minimum";
	public static String replenishmentMaximumLabel 	= "Replenishment Maximum";
	public static String replenishmentMultipleLabel = "Replenishment Multiple";
	public static String putawayAreaLabel           = "Putaway Area";
	public static String bomAreaLabel               = "BOM Area";
	public static String receiveUqLabel             = "Receive UQ";
	public static String releaseUqLabel             = "Release UQ";
	public static String stocktakeCycleLabel        = "Stocktake Cycle";
	public static String expiryPeriodLabel          = "Expiry Period";
	
	//BOM Label
	
	public static String componentLabel             = "Component";
	public static String hasChildrenLabel           = "Has Children";
	public static String componentQuantityLabel     = "Component Quantity";
	public static String reusableLabel              = "Reusable";
	
	//Additional Details Label
	
	public static String brandNameLabel             = "Brand Name";
	public static String tradeZoneLabel             = "Trade Zone";
	
	//warehouse master Label
	
	public static String warehouseNameLabel  		= "Warehouse Name";
	
	//warehouse master Filter Label
	
	public static String branchCodeLabel  			= "Branch Code";
	public static String warehouseTypeLabel  		= "Warehouse Type";
	
	//Warehouse Submenu Labels
	
	public static String areaLabel  		        = "Area";
	public static String warehouseConfigLabel  		= "Warehouse Config";
	public static String clientConfigLabel  		= "Client Config";
	public static String dockConfigLabel  		    = "Dock Config";
	
	//Warehouse General Labels
	
	public static String branchNameLabel  		    = "Branch Name";
	public static String countryCodeLabel  		    = "Country Code";
	
	//Warehouse Config Labels
	
	public static String enableGatepassLabel  		= "Enable Gatepass";
	public static String enableDamageCapturedLabel  = "Enable Damage Captured";
	public static String isGatepassMandatoryLabel   = "Is Gatepass Mandatory";
	
	//Client Config Labels
	
	public static String organizationNameLabel  	= "Organization Name";
	
	//Dock Config Labels
	
	public static String dockNameLabel  	        = "Dock Name";
	public static String dockOrderLabel  	        = "Dock Order";
	public static String purposeLabel           	= "Purpose";
	
	//Warehouse Area Labels
	
	public static String maximumVolumeLabel        	= "Maximum Volume";
	public static String maximumWeightLabel        	= "Maximum Weight";
	public static String currentVolumeLabel        	= "Current Volume";
	public static String currentWeightLabel        	= "Current Weight";
	public static String availableVolumeLabel      	= "Available Volume";
	public static String availableWeightLabel      	= "Available Weight";
	
	//Warehouse Location Header Labels
	
	public static String rowLabel           	    = "Row";
	
	//Row Labels
	
	public static String rowNameLabel           	= "Row Name";
	public static String rowPathSequenceLabel       = "Row Path Sequence";
	public static String columnsInRowLabel          = "Columns in Row";
	public static String levelInRowLabel           	= "Level in Row";
	public static String traysInLocationLabel 	    = "Trays in Location";
	public static String sortColumnLabel           	= "Sort Column";
    public static String columnThenLevelLabel       = "Column Then Level";
	public static String levelThenColumnLabel       = "Level Then Column";
	
	//Location Labels
	
	public static String pickMethodLabel            = "Pick Method";
	public static String maxWeightLabel             = "Max. Weight";
	public static String maxCubicLabel              = "Max. Cubic";
	public static String maxQtyLabel                = "Max. Qty";
	public static String generateBarcodeLabel       = "Generate Barcode";
	
	//CheckBox Label
	
	public static String checkboxLabel  			= "checkbox";
	
	public static String pickPathSeqLabel 		    = "Pick Path Seq";
	public static String receiveLinesbuttonLabel    = "ReceiveLines";
	public static String receiptRefLabel="Receipt Ref";
	public static String receiptReferenceLabel="Receipt Reference";
	public static String adjustmentNoLabel="Adjustment No";
	public static String inLocationQuantityLabel="In Location Qty";
	public static String locationTypeLabel ="Location Type";	
	public static String mandatoryFieldLabel = "MANDATORY FIELD";
	public static String nonMandatoryFieldLabel = "NON MANDATORY FIELD";
	

	public static String pageForwardLabel="Page forward";

	public static String pageBackLabel="Page back";
	public static String totalLinePackagesLabel="Total Line Packages";
	public static String totalLineUnitsLabel ="Total Line Units";
	public static String totalLinePalletsLabel="Total Line Pallets";
	public static String totalWeightLabel			="Total Weight";
	public static String totalVolumeLabel			="Total Volume";
	public static String totalWeightUnitLabel="Total Weight Unit";
	
	public static String eventLabel="Event";
	public static String totalCubicLabel="Total Cubic";
	public static String totalCubicUnitLabel="Total Cubic Unit";
	public static String iNCOTermLabel="INCO Term";
	public static String transportModeLabel="Transport Mode";
	public static String containerModeLabel="Container Mode";
	public static String serviceLevelLabel="Service Level";
	public static String nameLabel 		        = "Name";
	public static String putawaycompletedCalAttribute="PutOrPickCompDateTime";
	public static String existingLabel ="Existing";
	public static String finalizeLabel = "Finalize";
	public static String locationSourceLabel = "Location Source";
	public static String stockTransferNoLabel = "Stock Transfer No";
	public static String facilityLabel ="Facility";
	public static String transporterLabel ="Transporter";
	public static String vehicleTypeLabel= "Vehicle Type";
	public static String vehicleNoLabel="Vehicle No";
	public static String driverNameLabel="Driver Name";
	public static String driverContactNo="Driver Contact No";
	public static String cancelTransferLabel = "Cancel Transfer";
	public static String addToLinesLabel= "Add To Line";
	public static String locationDestinationLabel ="Location Destination";
	public static String gatePassLabel ="GatePass";
	public static String gatePassDetailsLabel ="Gatepass Details";
	public static String transferFromClientCodeAndNameLabel= "Transfer From Client Code And Name";
	public static String adjustmentReasonLabel ="Adjustment Reason";
	public static String cancelAdjustmentLabel ="Cancel Adjustment";
	public static String hHLabel = "HH";
	public static String address = "Address";
	public static String PlaceholdercontactNameLabel = "ContactName";
	public static String controllingBranchNameLabel = "BRN_Code";
	public static String contactLabel = "Contact";
	public static String EmployeeLabel = "Employee";
	public static String portLabel = "Port";

	public static String downloadAsPdfLabel="Download as PDF";
	public static String downloadAsExcelLabel ="Download as Excel";
	
	public static String transferType="Transfer Type";
	public static String transferToWarehouse="Transfer To Warehouse";
	
	//booking page
	public static String freightLabel="Freight";
	public static String bookingLabel="Booking";
	public static String consignorFldLabel="Enter Consignor Code or Name here...";
	public static String directionDropDownlabel="Direction";	
	public static String transportModeDropDownLabel="Transport Mode";
	public static String transportModeSEAValue="SEA-Sea Freight";
	public static String transportModeAIRValue="AIR-Air Freight";
	public static String transportModeRAIValue="RAI-Rail Freight";
	public static String transportModeROAValue="ROA-Road Freight";	
	public static String transportModeCOUValue="COU-Courier";	
	public static String packingModeDropDownLabel="Packing Mode";
	public static String packingModeDropDownValue="LCL-Less Container Load";
	public static String originTextLabel="Origin";
	public static String POLTextLabel="POL";
	public static String destinationTextLabel="Destination";
	public static String PODTextLabel="POD";
	public static String originBranchTextLabel="Origin Branch";
	public static String destinationBranchTextLabel="Destination Branch";
	public static String IncotermsDropDownLabel="Incoterms";	
	public static String frieghtTermsDropDownLabel="Freight Terms";
	public static String domesticLabel="Is Domestic?";
	public static String consigneeFldLabel="Enter Consignee Code or Name here...";
	public static String notifyPartylabel="Enter Notify Party Code or Name here...";
	
	//Organization field values

	public static String addressTypeValue="CST - Customs Address of Record";
	public static String consol_Column = "Consol No";
	public static String consolhash_Label = "Consol #";
	public static String orderhash_Label = "Order #";
	
	// MainMenu Labels
	public static String purchaseOrder = "Purchase Order";
	public static String orders = "Orders";
	public static String payment_Label = "Payment";
	public static String shipments_Label = "Shipments"; 
	
	//oderslable Lables
	public static String orderlabels = "Order #";
	public static String orderNolabels = "Order No";
	public static String consignelabels ="Organization Code";
	public static String consignorlabels = "Consignor";	
	public static String consignelabel ="Consignee";
	public static String isconsignor="IsConsignor";
	public static String isconsignee="IsConsignee";
	
}
