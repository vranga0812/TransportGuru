CREATE TABLE USERS(
    USER_ID            		INT NOT NULL AUTO_INCREMENT,
    FIRST_NAME         		VARCHAR(100) NOT NULL,
    LAST_NAME          		VARCHAR(50) NOT NULL,  -- changed the size of lastname
    COMPANY_NAME       		VARCHAR(500),
    NATIVE_LANGUAGE    		VARCHAR(50),
    EMAIL              		VARCHAR(75) NOT NULL UNIQUE,  -- changed length, added unique constraint
    PRIMARY_MOBILE     		VARCHAR(15) NOT NULL,  -- changed length 	
    SECONDARY_MOBILE   		VARCHAR(15),  -- changed length
--    USER_NAME          		VARCHAR(50) NOT NULL UNIQUE,
--    PASSWORD           		VARCHAR(50) NOT NULL,
    USER_TYPE				VARCHAR(50) NOT NULL,  -- Added
    REMARKS            		TEXT,
	STATUS					VARCHAR(30) NOT NULL,
    MOBILE_VERIFIED_FLAG 	CHAR(1) NOT NULL DEFAULT 'N',
    ID_VERIFIED_FLAG     	CHAR(1) NOT NULL DEFAULT 'N',
    CREATED_BY          	VARCHAR(50) NOT NULL,
    CREATED_DATE        	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
    UPDATED_BY         		VARCHAR(50) NOT NULL,
    UPDATED_DATE       		TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
    
    PRIMARY KEY(USER_ID)

);

CREATE TABLE USER_CREDENTIALS(

	USER_CREDENTIAL_ID 		INT NOT NULL,
	USER_NAME          		VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD           		VARCHAR(50) NOT NULL,
    LOGIN_ATTEMPTS			INT NOT NULL,
    IS_CHANGE_PASSWORD		VARCHAR(1) NOT NULL,
    LAST_LOGIN_TIME			TIMESTAMP,
    CREATED_BY          	VARCHAR(50) NOT NULL,
    CREATED_DATE        	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
    UPDATED_BY         		VARCHAR(50) NOT NULL,
    UPDATED_DATE       		TIMESTAMP DEFAULT NOW(),
	
    PRIMARY KEY(USER_CREDENTIAL_ID),
     
    FOREIGN KEY (USER_CREDENTIAL_ID) 
    REFERENCES USERS(USER_ID)
    ON DELETE CASCADE
    
);


CREATE TABLE ADDRESS(
	ADDRESS_ID				INT NOT NULL AUTO_INCREMENT,
	USER_ID					INT NOT NULL,
	ADDRESS_LABEL			VARCHAR(20) NOT NULL DEFAULT 'HOME_ADDRESS', -- added in v1.3
	ADDRESS1           		VARCHAR(100) NOT NULL, -- changed lenght
    ADDRESS2           		VARCHAR(100), -- changed length
    CITY               		VARCHAR(30) NOT NULL,  -- removed location and added City
    DISTRICT		   		VARCHAR(30),  -- added 
	STATE			   		VARCHAR(30) NOT NULL,  -- added
	PINCODE					INT,
    COUNTRY			   		VARCHAR(30) NOT NULL DEFAULT 'INDIA', -- added 	
    CREATED_BY          	VARCHAR(50),
    CREATED_DATE        	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
    UPDATED_BY         		VARCHAR(50),
    UPDATED_DATE       		TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
    
    PRIMARY KEY(ADDRESS_ID),
    
    FOREIGN KEY (USER_ID) 
    REFERENCES USERS(USER_ID)
    ON DELETE CASCADE
);

CREATE TABLE OTP(
	OTP_ID 					INT NOT NULL AUTO_INCREMENT,
	USER_ID					INT NOT NULL,
	CODE   					INT NOT NULL,
	PURPOSE					VARCHAR(50) NOT NULL,
	EXPIRY_DATETIME			TIMESTAMP NOT NULL,
	VERIFICATION_ATTEMPTS	INT NOT NULL,			-- ADDED IN 1.4
	GENERATION_ATTEMPTS		INT NOT NULL,			-- ADDED IN 1.4
	CREATED_BY          	VARCHAR(50) NOT NULL,
    CREATED_DATE        	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
    UPDATED_BY         		VARCHAR(50) NOT NULL,
    UPDATED_DATE       		TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
    
    PRIMARY KEY(OTP_ID),
    
    FOREIGN KEY (USER_ID) 
    REFERENCES USERS(USER_ID)
    ON DELETE CASCADE
    
);

CREATE TABLE VEHICLES(
   VEHICLE_ID        INT NOT NULL AUTO_INCREMENT,
   REGISTRATION_NUM  VARCHAR(100) NOT NULL UNIQUE,
   USER_ID           INT NOT NULL,
   VEH_CATEGORY      VARCHAR(100) NOT NULL, -- added NOT NULL contraint
   SUB_CATEGORY      VARCHAR(100),
   CAPACITY          VARCHAR(5) NOT NULL, -- added not null constraint and changed the size
   DEPARTURE_CITY    VARCHAR(100), -- ? is this field required in this table?
   ARRIVAL_CITY      VARCHAR(100), -- ? is this field required in this table?
   VERIFIED_FLAG     INT,
   REMARKS           TEXT,
   CREATED_BY        VARCHAR(50),
   CREATED_DATE      TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
   UPDATED_BY        VARCHAR(50),
   UPDATED_DATE      TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
   
   PRIMARY KEY(VEHICLE_ID),
   
   FOREIGN KEY (USER_ID) 
   REFERENCES USERS(USER_ID)
   ON DELETE CASCADE
);



CREATE TABLE TRIPS(
  TRIP_ID              INT NOT NULL AUTO_INCREMENT,
  VEHICLE_ID           INT,		
  USER_ID              INT NOT NULL, 
  DEPARTURE_CITY       VARCHAR(100) NOT NULL, -- added not null constraint
  ARRIVAL_CITY         VARCHAR(100) NOT NULL, -- added not null constraint
  DEPARTURE_TIME       DATE NOT NULL,		  -- added not null constraint
  VIA_POINT            VARCHAR(100) default 'NONE', -- added default value  ? can this be multiple cities?
  DRIVER_NAME          VARCHAR(100),
  DRIVER_PHONE_NO      VARCHAR(10),
  DRIVER_EMAIL_ADDRESS VARCHAR(100), 
  DRIVER_LANGUAGE      VARCHAR(100), -- comma seperated values
 -- TRIP_TYPE			   VARCHAR(15),	 -- added 
  TRIP_STATUS		   VARCHAR(15),  -- ADDED
  REMARKS              TEXT,
  CREATED_BY           VARCHAR(50),
  CREATED_DATE         TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
  UPDATED_BY           VARCHAR(50),
  UPDATED_DATE         TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
 
  PRIMARY KEY(TRIP_ID),
  
  FOREIGN KEY (VEHICLE_ID) 
	REFERENCES VEHICLES(VEHICLE_ID)
  ON DELETE CASCADE,
  
  FOREIGN KEY (USER_ID) 
	REFERENCES USERS(USER_ID)
  ON DELETE CASCADE
);


CREATE TABLE INQUIRIES(
  INQUIRY_ID		   INT NOT NULL AUTO_INCREMENT,	
  TRIP_ID              INT,		
  USER_ID              INT NOT NULL ,
  DEPARTURE_CITY       VARCHAR(100) NOT NULL, 
  ARRIVAL_CITY         VARCHAR(100) NOT NULL, 
  DEPARTURE_TIME       DATE NOT NULL,		  
  VEH_CATEGORY         VARCHAR(100) NOT NULL, 
  SUB_CATEGORY         VARCHAR(100),
  DEPARTURE_TIME_THRESHOLD INT,
  ARRIVAL_TIME_THRESHOLD INT,
  CAPACITY_THRESHOLD   INT,	
  INQUIRY_STATUS	   VARCHAR(15), 
  REMARKS              TEXT,
  CREATED_BY           VARCHAR(50),
  CREATED_DATE         TIMESTAMP DEFAULT NOW(), 
  UPDATED_BY           VARCHAR(50),
  UPDATED_DATE         TIMESTAMP DEFAULT NOW(), 
  
  PRIMARY KEY(INQUIRY_ID),
  
  FOREIGN KEY (TRIP_ID) 
	REFERENCES TRIPS(TRIP_ID)    			-- ? IS THIS FOREIGN KEY REQUIRED OR HAVE THIS FILED WITH NO FOREIGN KEY?
  ON DELETE CASCADE,
  
  FOREIGN KEY (USER_ID) 
	REFERENCES USERS(USER_ID)
  ON DELETE CASCADE
);
 
     
CREATE TABLE ORDERS(
  ORDER_ID 			INT NOT NULL AUTO_INCREMENT, -- we also should have some random order ID to show to the customer as the order id  that we show to the customer should not be a sequence
  ORDER_NUMBER 		varchar(20)  NOT NULL UNIQUE, -- added id for the above comment. giving it varchar as we might generate alpha numeric number
  USER_ID 			INT,
  INQUIRY_ID		INT,
  TRIP_ID		    INT,
  ORDER_ESTIMATE   	float,			-- added
  STATUS           	VARCHAR(15),	
  REMARKS          	TEXT,
  CREATED_BY       	VARCHAR(50),
  CREATED_DATE     	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
  UPDATED_BY       	VARCHAR(50),
  UPDATED_DATE     	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
  PRIMARY KEY(ORDER_ID),
  
  FOREIGN KEY (TRIP_ID) 
  REFERENCES TRIPS(TRIP_ID)
  ON DELETE CASCADE,
  
  FOREIGN KEY (INQUIRY_ID) 
  REFERENCES INQUIRIES(INQUIRY_ID)
  ON DELETE CASCADE,
  
  FOREIGN KEY (USER_ID) 
     REFERENCES USERS(USER_ID)
  ON DELETE CASCADE
);
     
-- CREATE TABLE PAYMENTS(
--  PAYMENT_ID 			INT NOT NULL AUTO_INCREMENT,
--  ORDER_ID  			INT NOT NULL,   				-- added not null contraint
--  BILLING_DETAILS		VARCHAR(100),			-- ? what kind of values goes here ?
--  BILLING_ADDRESS_ID 	INT,			-- modified from BILLING_ADDRESS to BILLING_ADDRESS1 
--  PAYMENT_STATUS		VARCHAR(15),  -- such as initiated, PENDING, AUTHORIZED, COMPLETED etc.., 	
--  VERIFICATION_FLAG 	CHAR(1),				 -- ? what is this field for ?
--  CREATED_BY          VARCHAR(50),
--  CREATED_DATE       	TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
--  UPDATED_BY         	VARCHAR(50),
--  UPDATED_DATE        TIMESTAMP DEFAULT NOW(), -- changed the field type to timestamp and added default value
--  PRIMARY KEY(PAYMENT_ID),
--  FOREIGN KEY (ORDER_ID) 
--     REFERENCES ORDERS(ORDER_ID)
--  ON DELETE CASCADE
--  FOREIGN KEY (BILLING_ADDRESS_ID) 
--          REFERENCES ADDRESS(ADDRESS_ID)
--    ON DELETE CASCADE
-- );