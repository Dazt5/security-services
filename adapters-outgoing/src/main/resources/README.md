# Liquibase commands

To be able to run these commands, you need to have a terminal at path `/security-services/adapters-outgoing`
because this module is the one that have liquibase plugin configured, also you need to have it configured
All environment variables are required, also you need to set up a Maven app in your path.

## Update Database

- When changes on the liquibase scripts were made, you can update database schema using following command
  - `` mvn liquibase:update ``

## Rollback Database

- If you want to rollback changes in your schema you can use following rollback command
  - `` mvn liquibase:rollback -Dliquibase.rollbackTag=${yourTag} ``
  - ${yourTag}: means before what tag you want to roll back db changes, by default existing tags are:
    - v0.0.0
    - v1.0.0
    - Inside `/security-services/adapters-outgoing/src/main/resources/db/changelog` you can see all tags defined.