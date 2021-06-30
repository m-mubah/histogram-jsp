CREATE DB FIRST BEFORE ATTEMPTING TO RUN

to create DB:

go to glassfish directory

open terminal on windows

.\bin\asadmin start-domain
.\bin\asadmin start-database

go to localhost:4848

create new jdbc connection pool
	pool name: HistogramPool
	resource type: javax.sql.DataSource
	driver venodr: derby

	click next and finish
	
	edit HistogramPool > additional properties > clear everything
	
	add the following proeprties

	connectionAttributes : ;create=true
	DatabaseName : Histogram
	serverName: localhost
	User : APP
	Password : APP
	PortNumber : 1527
	
	save > check if ping is ok

	if everything ok and you want data to presist after restart, go to additional properties and remove connectionAttributes. otherwise to next step

create new jdbc resource
	JNDI name : jdbc/histogram
	poolname : HistogramPool
	click ok to save

connect to database: (this is explained using intellij database tool)

select derby. use network driver
	Host : localhost
	Port : 1527
	User : APP
	Password : APP
	Database : Histogram

	Test connection > OK 

Open console from database tool. Enter following query

CREATE TABLE STUDENTS (
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
    MARKS INTEGER NOT NULL,
    PRIMARY KEY (ID)
)

execute. 

Always make sure DB is running before starting the project!
Now everything is setup! run the project!

To upload a file. Refer to grades.txt in resources