#!/bin/bash

mysql $MYSQL_DATABASE -u root -p$MYSQL_ROOT_PASSWORD --execute "SET GLOBAL local_infile=true"

mysqlimport --ignore-lines=1 --fields-terminated-by=, --local -u root -p$MYSQL_ROOT_PASSWORD $MYSQL_DATABASE ./data/fair.csv
