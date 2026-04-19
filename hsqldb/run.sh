#!/bin/bash

java -cp hsqldb.jar org.hsqldb.Server -database.0 file:storage/mydb -dbname.0 mydb
