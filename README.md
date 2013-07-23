SysDataPC
=========

Collecting and presenting system data

This project contains two applications:
 - Java console application DataGenerator that collects CPU and network utilization data generated on interval provided by user and saves it to a mySQL DB.
 - Grails/Groovy web application that displays graph of the data collected with a choice of intervals (per min, per hour, per day).
   Below the graph application shows the table with all of the data
   
***************************
USAGE INSTRUCTIONS:
***************************


DATA LAYER:
***************************

 - mySQL instalation required
 - create database datagenerator
 - create table cpudata
 - create table netdata


You can run the provided script



DATA GENERATOR APPLICATION:
***************************

DataGenerator is java 1.7 console application developed in Eclipse IDE. It uses SIGAR free tool which provides excellent results with system monitoring and collecting data.
You need to perform the following steps:
 - download DataGenerator folder and import it as Eclipse (Java) project
 - from http://sourceforge.net/projects/sigar/files/ download SIGAR and unpack it
 - in Java Build Path of the DataGenerator project add external JARs:
   - mysql-connector-java-5.1.23-bin.jar (<installation path>\Connector J 5.1.23\)
   - sigar.jar (\sigar-bin\lib\)
 - when starting application, you'll be asked to provide mySQL username, password and the time interval in which the data will be collected



DATA REPORTER APPLICATION:
***************************

DataReporter is Grails/Groovy web application developed in Groovy/Grails Tool Suite (GGTS) IDE.
You need to perform the following steps:
 - download DataReporter folder and import it in GGTS as Grails project
 - in Config.groovy file (lines 62 and 63), in conf section of the project, you need to set values for two variables - DB username and password...values are the same ones you entered when staring DataGenerator application
 - run the following script on datagenerator DB:

use datagenerator;
grant all on datagenerator.* to grails@localhost identified by 'server';
flush privileges;

 - execute grails run-app from GGTS command prompt
 - after a while you'll see the message:

Server running. Browse to http://localhost:9090/DataReporter

 - you can open the web application using provided address