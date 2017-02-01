INSERT INTO USER_ACCOUNT (ID, USERNAME, PASSWORD, SALT, FIRST_NAME, SURNAME, EMAIL, ACTIVE, BLOCKED, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'user', '541338d92cd0dbf230d7e7666dd99adaea8da7a478f5456947c2445aecea0b1a', 'ad68dc115126d9d1', 'userName', 'userSurname', 'user@gmail.com', 1, 0, current_timestamp, 0);
INSERT INTO USER_ACCOUNT (ID, USERNAME, PASSWORD, SALT, FIRST_NAME, SURNAME, EMAIL, ACTIVE, BLOCKED, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'admin', 'b03209e6c608cdf3753ab36449703abeab6aa7aab628e569b37a55381d4aa021', '94bd6b18b8f70298', 'adminName', 'adminSurname', 'admin@gmail.com', 1, 0, current_timestamp, 0);

INSERT INTO ROLE (ID, NAME, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'ROLE_USER', current_timestamp, 0);
INSERT INTO ROLE (ID, NAME, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'ROLE_ADMIN', current_timestamp, 0);

INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES
  (1, 3);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES
  (2, 4);

INSERT INTO CUSTOMER (ID, CUSTOMER_ID, COMPANY_NAME, STREET, STREET2, POSTCODE, COUNTRY, CITY, VATID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 1.0, 'SMSC', 'Amtsgericht', 'Amtsgericht', '3254', 'Germany', 'Stuttgart', 5672394.0, current_timestamp, 0);

INSERT INTO CUSTOMER_USER_ACCOUNT(CUSTOMER_ID, USER_ID) VALUES
  (5, 1);

INSERT INTO CUSTOMER_USER_ACCOUNT(CUSTOMER_ID, USER_ID) VALUES
  (5, 2);

INSERT INTO CUSTOMER_CONTACT (ID, FIRST_NAME, SURNAME, PHONE, MOBILE_PHONE, FAX, EMAIL_ADDRESS, CUSTOMER_ID, SALUTATION, TYPE, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'SMSC', 'SMSC', '0674329568', '0504569753', 'fake_fax', 'smsc@bulk.io', 5, 'MR', 'CEO', current_timestamp, 0);

INSERT INTO DASHBOARD (ID, NAME, ICON, USER_ACCOUNT_ID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'default', 'user', 1, current_timestamp, 0);

INSERT INTO DASHBOARD_BOX_TYPE (ID, NAME, TYPE, KIND, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'Ivan feeds', 'STATUS', 'FEEDBACK_STATUS', current_timestamp, 0);
INSERT INTO DASHBOARD_BOX_TYPE (ID, NAME, TYPE, KIND, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'Petia profit', 'CHART', 'PIE_CHART', current_timestamp, 0);
INSERT INTO DASHBOARD_BOX_TYPE (ID, NAME, TYPE, KIND, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'Rusia chart profit', 'CHART', 'SERIAL_CHART', current_timestamp, 0);
INSERT INTO DASHBOARD_BOX_TYPE (ID, NAME, TYPE, KIND, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'Ivan chart profit', 'CHART', 'LINE_CHART', current_timestamp, 0);
INSERT INTO DASHBOARD_BOX_TYPE (ID, NAME, TYPE, KIND, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'Kolia chart profit', 'CHART', 'BAR_CHART', current_timestamp, 0);
INSERT INTO DASHBOARD_BOX_TYPE (ID, NAME, TYPE, KIND, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'Masha bubble chartat', 'CHART', 'BUBBLE_CHART', current_timestamp, 0);

INSERT INTO DASHBOARD_BOX (ID, WIDTH, HEIGHT, ORDER_NUMBER, NAME, DESCRIPTION, DASHBOARD_ID, DASHBOARD_BOX_TYPE_ID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'WIDTH_25', 'HEIGHT_25', 1, 'Box 1',  'Box 1 desc', 7, 8, current_timestamp, 0);
INSERT INTO DASHBOARD_BOX (ID, WIDTH, HEIGHT, ORDER_NUMBER, NAME, DESCRIPTION, DASHBOARD_ID, DASHBOARD_BOX_TYPE_ID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'WIDTH_25', 'HEIGHT_25', 2, 'Box 2',  'Box 2 desc', 7, 8, current_timestamp, 0);
INSERT INTO DASHBOARD_BOX (ID, WIDTH, HEIGHT, ORDER_NUMBER, NAME, DESCRIPTION, DASHBOARD_ID, DASHBOARD_BOX_TYPE_ID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'WIDTH_25', 'HEIGHT_25', 3, 'Box 3',  'Box 3 desc', 7, 8, current_timestamp, 0);
INSERT INTO DASHBOARD_BOX (ID, WIDTH, HEIGHT, ORDER_NUMBER, NAME, DESCRIPTION, DASHBOARD_ID, DASHBOARD_BOX_TYPE_ID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'WIDTH_25', 'HEIGHT_25', 4, 'Box 4',  'Box 4 desc', 7, 8, current_timestamp, 0);
INSERT INTO DASHBOARD_BOX (ID, WIDTH, HEIGHT, ORDER_NUMBER, NAME, DESCRIPTION, DASHBOARD_ID, DASHBOARD_BOX_TYPE_ID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'WIDTH_50', 'HEIGHT_50', 5, 'Box 5',  'Box 5 desc', 7, 9, current_timestamp, 0);
INSERT INTO DASHBOARD_BOX (ID, WIDTH, HEIGHT, ORDER_NUMBER, NAME, DESCRIPTION, DASHBOARD_ID, DASHBOARD_BOX_TYPE_ID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'WIDTH_50', 'HEIGHT_50', 6, 'Box 6',  'Box 6 desc', 7, 10, current_timestamp, 0);
INSERT INTO DASHBOARD_BOX (ID, WIDTH, HEIGHT, ORDER_NUMBER, NAME, DESCRIPTION, DASHBOARD_ID, DASHBOARD_BOX_TYPE_ID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'WIDTH_50', 'HEIGHT_50', 7, 'Box 7',  'Box 7 desc', 7, 11, current_timestamp, 0);
INSERT INTO DASHBOARD_BOX (ID, WIDTH, HEIGHT, ORDER_NUMBER, NAME, DESCRIPTION, DASHBOARD_ID, DASHBOARD_BOX_TYPE_ID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'WIDTH_50', 'HEIGHT_50', 8, 'Box 8',  'Box 8 desc', 7, 12, current_timestamp, 0);
INSERT INTO DASHBOARD_BOX (ID, WIDTH, HEIGHT, ORDER_NUMBER, NAME, DESCRIPTION, DASHBOARD_ID, DASHBOARD_BOX_TYPE_ID, LAST_MODIFIED_DATE, VERSION) VALUES
  (HIBERNATE_SEQUENCE.nextval, 'WIDTH_50', 'HEIGHT_50', 9, 'Box 9',  'Box 9 desc', 7, 13, current_timestamp, 0);

