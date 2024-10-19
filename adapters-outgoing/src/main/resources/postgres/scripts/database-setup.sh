 #!/bin/bash -x
set -e
echo "---- Creating default user and database. ----"
export PGPASSWORD=${POSTGRES_PASSWORD}; psql --username=${POSTGRES_USER} --dbname=${POSTGRES_DB} <<-EOSQL
CREATE USER ${DEFAULT_USER} WITH PASSWORD '${DEFAULT_USER_PASSWORD}';
CREATE DATABASE ${DEFAULT_DB} WITH OWNER ${DEFAULT_USER};
EOSQL
echo "---- Ending setup successfully ----"