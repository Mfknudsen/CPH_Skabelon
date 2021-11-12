## CA 2 Skabelon

### Preconditions:
In order to use this code, you should have a local developer setup + a "matching" droplet on Digital Ocean as described in the 3. semester guidelines

### How To Use:
Download the Skabelon.

Rename All Instances of the word Skabelon to the name of the active project.

Under mavenworkflow.yml uncomment line 54 and 57. This will allow the project to be deployed through GitHub Actions.

Under pom.xml uncomment line 19 and replace the "rename.me" to your droplet.
Also replace the database name on line 23 to your database.

Under persistence.xml replace "starcode" on line 20 with your database for final use and on line 38 replace "startcode_test" with your test database.
Replace also the user and password if needed.

####Rest:
When making new Rest classes remember to place them in ApplicationConfig.java under "Insert new Rest Classes Here".

Use DemoResource.java in the rest package for how to setup endpoints.

####Entities:
When making new Entities classes remember to place them in persistence.xml under "Insert new Entities Here".

Use RenameMe.java in the entities package for how to setup entities.

####DTOs
Use RenameMeDTO in the dtos package for hot to setup dtos.

####Facades:
Remember to use IFacade with the intended DTO when making new Facades.

Use FacadeExample in the package facades for how to setup facades.