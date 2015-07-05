CREATE TABLE ADDRESS(
	ADDRESS_ID				INT NOT NULL AUTO_INCREMENT,
	ADDRESS1           		VARCHAR(100) NOT NULL, -- changed lenght
    ADDRESS2           		VARCHAR(100), -- changed length
    CITY               		VARCHAR(30) NOT NULL,  -- removed location and added City
    DISTRICT		   		VARCHAR(30),  -- added 
	STATE			   		VARCHAR(30) NOT NULL,  -- added
	PINCODE					INT,
    COUNTRY			   		VARCHAR(30) NOT NULL -- added 	
    CREATED_BY          	VARCHAR(100),
    CREATED_DATE        	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
    UPDATED_BY         		VARCHAR(50),
    UPDATED_DATE       		TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
);
CREATE TABLE USERS(
    USER_ID            		INT NOT NULL AUTO_INCREMENT,
    FIRST_NAME         		VARCHAR(100) NOT NULL,
    LAST_NAME          		VARCHAR(50) NOT NULL,  -- changed the size of lastname
    COMPANY_NAME       		VARCHAR(500),
    ADDRESS_ID				INT,
    NATIVE_LANGUAGE    		VARCHAR(50),
    EMAIL              		VARCHAR(75) UNIQUE,  -- changed length, added unique constraint
    PRIMARY_MOBILE     		VARCHAR(15),  -- changed length 	
    SECONDARY_MOBILE   		VARCHAR(15),  -- changed length
    USER_NAME          		VARCHAR(50),
    PASSWORD           		VARCHAR(50),
    USER_TYPE				VARCHAR(50),  -- Added
    REMARKS            		TEXT,
    MOBILE_VERIFIED_FLAG 	CHAR(1),
    ID_VERIFIED_FLAG     	CHAR(1),
    CREATED_BY          	VARCHAR(100),
    CREATED_DATE        	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
    UPDATED_BY         		VARCHAR(50),
    UPDATED_DATE       		TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
    PRIMARY KEY(USER_ID)
    FOREIGN KEY (ADDRESS_ID) 
          REFERENCES ADDRESS(ADDRESS_ID)
    ON DELETE CASCADE
);
    
    
CREATE TABLE VEHICLES(
   VEHICLE_ID        INT NOT NULL AUTO_INCREMENT,
   REGISTRATION_NUM  VARCHAR(100) NOT NULL UNIQUE,
   USER_ID           INT,
   VEH_CATEGORY      VARCHAR(100) NOT NULL, -- added NOT NULL contraint
   SUB_CATEGORY      VARCHAR(100),
   CAPACITY          VARCHAR(5) NOT NULL, -- added not null constraint and changed the size
   DEPARTURE_CITY    VARCHAR(100), -- ? is this field required in this table?
   ARRIVAL_CITY      VARCHAR(100), -- ? is this field required in this table?
   VERIFIED_FLAG     INT,
   REMARKS           TEXT,
   CREATED_BY        VARCHAR(100),
   CREATED_DATE      TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
   UPDATED_BY        VARCHAR(50),
   UPDATED_DATE      TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
   PRIMARY KEY(VEHICLE_ID),
   FOREIGN KEY (USER_id) 
       REFERENCES USERS(USER_ID)
   ON DELETE CASCADE
);



CREATE TABLE TRIPS(
  TRIP_ID              INT NOT NULL AUTO_INCREMENT,
  VEHICLE_ID           INT,		
  USER_ID              INT NOT NULL 
  DEPARTURE_CITY       VARCHAR(100) NOT NULL, -- added not null constraint
  ARRIVAL_CITY         VARCHAR(100) NOT NULL, -- added not null constraint
  DEPARTURE_TIME       DATE NOT NULL,		  -- added not null constraint
  VIA_POINT            VARCHAR(100) default 'NONE', -- added default value  ? can this be multiple cities?
  DRIVER_NAME          VARCHAR(100),
  DRIVER_PHONE_NO      VARCHAR(10),
  DRIVER_EMAIL_ADDRESS VARCHAR(100), 
  DRIVER_LANGUAGE      VARCHAR(100), -- comma seperated values
  TRIP_TYPE			   VARCHAR(15),	 -- added 
  TRIP_STATUS		   VARCHAR(15),  -- ADDED
  REMARKS              TEXT,
  CREATED_BY           VARCHAR(100),
  CREATED_DATE         TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
  UPDATED_BY           VARCHAR(50),
  UPDATED_DATE         TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
  PRIMARY KEY(TRIP_ID),
  FOREIGN KEY (VEHICLE_ID) 
	REFERENCES VEHICLES(VEHICLE_ID)
  ON DELETE CASCADE
  FOREIGN KEY (USER_ID) 
	REFERENCES USERS(USER_ID)
  ON DELETE CASCADE
);
     
     
CREATE TABLE ORDERS(
  ORDER_ID 			INT NOT NULL AUTO_INCREMENT, -- we also should have some random order ID to show to the customer as the order id  that we show to the customer should not be a sequence
  ORDER_NUMBER 		varchar(20)  NOT NULL UNIQUE, -- added id for the above comment. giving it varchar as we might generate alpha numeric number
  USER_ID 			INT,
  -- VEHICLE_ID	    INT,		 
  DEPARTURE_CITY   	VARCHAR(100) NOT NULL,  -- added not null contraint
  ARRIVAL_CITY     	VARCHAR(100) NOT NULL,  -- added not null constraint
  STATUS           	VARCHAR(15),
  ORDER_ESTIMATE   	float,				   -- added	
  REMARKS          	TEXT,
  CREATED_BY       	VARCHAR(100),
  CREATED_DATE     	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
  UPDATED_BY       	VARCHAR(50),
  UPDATED_DATE     	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
  PRIMARY KEY(ORDER_ID),
  FOREIGN KEY (VEHICLE_ID) 
  REFERENCES VEHICLES(VEHICLE_ID)
  ON DELETE CASCADE,
  FOREIGN KEY (USER_ID) 
     REFERENCES USERS(USER_ID)
  ON DELETE CASCADE
);
     
CREATE TABLE PAYMENTS(
  PAYMENT_ID 			INT NOT NULL AUTO_INCREMENT,
  ORDER_ID  			INT NOT NULL,   				-- added not null contraint
  BILLING_DETAILS		VARCHAR(100),			-- ? what kind of values goes here ?
  BILLING_ADDRESS_ID 	INT,			-- modified from BILLING_ADDRESS to BILLING_ADDRESS1 
  PAYMENT_STATUS		VARCHAR(15),  -- such as initiated, PENDING, AUTHORIZED, COMPLETED etc.., 	
  VERIFICATION_FLAG 	CHAR(1),				 -- ? what is this field for ?
  CREATED_BY          VARCHAR(100),
  CREATED_DATE       	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
  UPDATED_BY         	VARCHAR(50),
  UPDATED_DATE        TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
  PRIMARY KEY(PAYMENT_ID),
  FOREIGN KEY (ORDER_ID) 
     REFERENCES ORDERS(ORDER_ID)
  ON DELETE CASCADE
  FOREIGN KEY (BILLING_ADDRESS_ID) 
          REFERENCES ADDRESS(ADDRESS_ID)
    ON DELETE CASCADE
);