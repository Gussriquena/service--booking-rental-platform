CREATE TABLE IF NOT EXISTS PROPERTY (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    ADDRESS VARCHAR(250) NOT NULL,
    ID_OWNER INTEGER NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID_OWNER) REFERENCES PROPERTY_USER (ID)
)