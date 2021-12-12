package com.fdflib.example;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import com.fdflib.example.arlington_tables.*;
import com.fdflib.example.clients.Client_schools;
import com.fdflib.example.queries.*;
import com.fdflib.example.spackenkill_queries.*;
import com.fdflib.example.spackenkill_tables.*;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.persistence.database.DatabaseUtil;
import com.fdflib.service.FdfServices;
import com.fdflib.util.FdfSettings;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rorie Reyes on 12/11/21
 */
public class DutchessCountyTenant {
    public static void main(String[] args) {
        System.out.println("Hello 4DF World!"); // Display the string.

        // use the  settings within this method to customize the 4DFLib.  Note, everything in this method is optional.
        setOptionalSettings();

        // Create a array that will hold the classes that make up our 4df data model
        List<Class> myModel = new ArrayList<>();

        //Add our classes
        myModel.add(SystemRole.class);
        myModel.add(Users.class);
        myModel.add(Locations.class);
        myModel.add(Visit.class);
        myModel.add(Arlington_Client.class);
        myModel.add(SK_SystemRole.class);
        myModel.add(SK_Users.class);
        myModel.add(SK_Locations.class);
        myModel.add(SK_Visit.class);
        myModel.add(Spackenkill_Client.class);

        // call the initialization of library!
        FdfServices.initializeFdfDataModel(myModel);

        // insert some data
        try {
            insertSomeData();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // do a few queries and output the results

    }

    /**
     * Everything set in this method is optional, but useful
     */
    private static void setOptionalSettings() {

        // get the 4dflib settings singleton
        FdfSettings fdfSettings = FdfSettings.getInstance();

        // set the database type and name and connection information
        // PostgreSQL settings
        //fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.POSTGRES;
        //fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_POSTGRES;

        // postgres default root user
        // root user settings are only required for initial database creation.  Once the database is created you
        // should remove this information
        //fdfSettings.DB_ROOT_USER = "postgres";

        // MySQL settings
        //fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MYSQL;
        //fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MYSQL;

        // MariaDB settings
        fdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MARIADB;
        fdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MARIADB;

        // MariaDB and MySQL default
        // root user settings are only required for initial database creation.  Once the database is created you
        // should remove this information
        fdfSettings.DB_ROOT_USER = "root";

        // root user password
        fdfSettings.DB_ROOT_PASSWORD = "password";

        // Database encoding
        fdfSettings.DB_ENCODING = DatabaseUtil.DatabaseEncoding.UTF8;

        // Application Database name
        fdfSettings.DB_NAME = "dutchesscountyschools";
        // Database host
        fdfSettings.DB_HOST = "localhost";

        // Port is not required for DB defaults can be changed when needed
        //fdfSettings.DB_PORT = 3306;

        // Database user information
        fdfSettings.DB_USER = "dutchesscounty";
        fdfSettings.DB_PASSWORD = "dutchcountpass";

        // set the default system information
        fdfSettings.DEFAULT_SYSTEM_NAME = "Dutchess County Core API";
        fdfSettings.DEFAULT_SYSTEM_DESCRIPTION = "Central API service for the Dutchess County Application";

        // set the default tenant information
        fdfSettings.DEFAULT_TENANT_NAME = "Dutchess County";
        fdfSettings.DEFAULT_TENANT_DESRIPTION = "Main system Tenant for Dutchess County";
        fdfSettings.DEFAULT_TENANT_IS_PRIMARY = true;
        fdfSettings.DEFAULT_TENANT_WEBSITE = "http://www.4dflib.com";

        // local dev, no ssl
        fdfSettings.USE_SSL = false;


    }

    private static void insertSomeData() throws InterruptedException {
        InsertUsers iu = new InsertUsers();
        InsertSysRoles sr = new InsertSysRoles();
        InsertLocation il = new InsertLocation();
        CheckInStatus cis = new CheckInStatus();
        CreateArlingtonClient cac = new CreateArlingtonClient();


        /*
            ####################################################################
            ####################################################################
            ####################################################################
            #################Arlington High School District#####################
            ####################################################################
            ####################################################################
            ####################################################################
         */

        /*
            ####################################################################
            #########################Insert Users###############################
            ####################################################################
         */
        Users michael = new Users();
        michael.fName = "Michael";
        michael.lName = "Scott";
        michael.email = "michael.g.scott@arlington.edu";
        michael.password = "password123";
        michael.personalRankId = 1;
        michael.personalIdNum = "RS2342-32123";
        michael.color = "red";
        michael.numAdd = 23;
        michael.streetAdd = "Condo Lane";
        michael.city = "Poughkeepsie";
        michael.state = "NY";
        michael.country = "USA";
        michael.idDateIssue = "1983-03-15";
        michael.idDateExp = "2024-03-15";
        michael.dob = "1965-03-15";
        michael.gender = "Male";
        michael.eyeCol = "Hazel";
        michael.height = "5'9in";
        michael.weight = "168lbs";
        iu.saveUsers(michael);

        Users jim = new Users();
        jim.fName = "Jim";
        jim.lName = "Halpert";
        jim.email = "jim.halpert@arlington.edu";
        jim.password = "ilovepam";
        jim.personalRankId = 1;
        jim.personalIdNum = "RS9825-69157";
        jim.color = "blue";
        jim.numAdd = 32;
        jim.streetAdd = "Parents Drive";
        jim.city = "Poughkeepsie";
        jim.state = "NY";
        jim.country = "USA";
        jim.idDateIssue = "1996-10-01";
        jim.idDateExp = "2022-10-01";
        jim.dob = "1978-10-01";
        jim.gender = "Male";
        jim.eyeCol = "Hazel";
        jim.height = "6'3in";
        jim.weight = "200lbs";
        iu.saveUsers(jim);

        Users pam = new Users();
        pam.fName = "Pam";
        pam.lName = "Beesly";
        pam.email = "pam.beesly@arlington.edu";
        pam.password = "jimlovesme";
        pam.personalRankId = 1;
        pam.personalIdNum = "RS8532-82547";
        pam.color = "orange";
        pam.numAdd = 25;
        pam.streetAdd = "Roy Boulevard";
        pam.city = "Poughkeepsie";
        pam.state = "NY";
        pam.country = "USA";
        pam.idDateIssue = "1997-03-25";
        pam.idDateExp = "2025-03-25";
        pam.dob = "1979-03-25";
        pam.gender = "Female";
        pam.eyeCol = "Green";
        pam.height = "5'6in";
        pam.weight = "126lbs";
        iu.saveUsers(pam);

        Users dwight = new Users();
        dwight.fName = "Dwight";
        dwight.lName = "Schrute";
        dwight.email = "dwight.k.schrute@arlington.edu";
        dwight.password = "imnotdwigt";
        dwight.personalRankId = 1;
        dwight.personalIdNum = "RS7453-65129";
        dwight.color = "green";
        dwight.numAdd = 1;
        dwight.streetAdd = "Schrute Farm Road";
        dwight.city = "Saugerties";
        dwight.state = "NY";
        dwight.country = "USA";
        dwight.idDateIssue = "1988-01-20";
        dwight.idDateExp = "2027-01-20";
        dwight.dob = "1970-01-20";
        dwight.gender = "Male";
        dwight.eyeCol = "Blue";
        dwight.height = "6'2in";
        dwight.weight = "183lbs";
        iu.saveUsers(dwight);

        Users andy = new Users();
        andy.fName = "Andy";
        andy.lName = "Bernard";
        andy.email = "andy.bernard@arlington.edu";
        andy.password = "narddawg";
        andy.personalRankId = 1;
        andy.personalIdNum = "RS3645-10947";
        andy.color = "purple";
        andy.numAdd = 45;
        andy.streetAdd = "Roodoodoo Circle";
        andy.city = "Kingston";
        andy.state = "NY";
        andy.country = "USA";
        andy.idDateIssue = "1992-01-24";
        andy.idDateExp = "2023-01-24";
        andy.dob = "1974-01-24";
        andy.gender = "Male";
        andy.eyeCol = "Blue";
        andy.height = "5'11in";
        andy.weight = "165lbs";
        iu.saveUsers(andy);

        Users angela = new Users();
        angela.fName = "Angela";
        angela.lName = "Martin";
        angela.email = "angela.martin@arlington.edu";
        angela.password = "bandit";
        angela.personalRankId = 1;
        angela.personalIdNum = "RS3492-71946";
        angela.color = "pink";
        angela.numAdd = 1;
        angela.streetAdd = "Bandit Road";
        angela.city = "Saugerties";
        angela.state = "NY";
        angela.country = "USA";
        angela.idDateIssue = "1989-06-25";
        angela.idDateExp = "2022-06-25";
        angela.dob = "1971-06-25";
        angela.gender = "Female";
        angela.eyeCol = "Green";
        angela.height = "5'1in";
        angela.weight = "82lbs";
        iu.saveUsers(angela);

        Users kevin = new Users();
        kevin.fName = "Kevin";
        kevin.lName = "Malone";
        kevin.email = "kevin.malone@arlington.edu";
        kevin.password = "fewworddotrick";
        kevin.personalRankId = 1;
        kevin.personalIdNum = "RS6284-40954";
        kevin.color = "brown";
        kevin.numAdd = 36;
        kevin.streetAdd = "Chilli Road";
        kevin.city = "Fishkill";
        kevin.state = "NY";
        kevin.country = "USA";
        kevin.idDateIssue = "1990-11-29";
        kevin.idDateExp = "2023-11-29";
        kevin.dob = "1972-11-29";
        kevin.gender = "Male";
        kevin.eyeCol = "Green";
        kevin.height = "6'0in";
        kevin.weight = "209lbs";
        iu.saveUsers(kevin);

        Users creed = new Users();
        creed.fName = "Creed";
        creed.lName = "Bratton";
        creed.email = "creed.bratton@aol.com";
        creed.password = "readmyblog";
        creed.personalRankId = 1;
        creed.personalIdNum = "RS8351-30954";
        creed.color = "yellow";
        creed.numAdd = 56;
        creed.streetAdd = "Hone Street";
        creed.city = "New Paltz";
        creed.state = "NY";
        creed.country = "USA";
        creed.idDateIssue = "1961-02-08";
        creed.idDateExp = "2022-02-08";
        creed.dob = "1943-02-08";
        creed.gender = "Male";
        creed.eyeCol = "Black";
        creed.height = "5'11in";
        creed.weight = "166lbs";
        iu.saveUsers(creed);

        Users will = new Users();
        will.fName = "Will";
        will.lName = "Ferrell";
        will.email = "w.ferrell@gmail.com";
        will.password = "ishouldbemanager";
        will.personalRankId = 1;
        will.personalIdNum = "RS7310-71047";
        will.color = "black";
        will.numAdd = 6;
        will.streetAdd = "Irving Road";
        will.city = "Poughkeepsie";
        will.state = "NY";
        will.country = "USA";
        will.idDateIssue = "1985-07-16";
        will.idDateExp = "2022-07-16";
        will.dob = "1967-07-16";
        will.gender = "Male";
        will.eyeCol = "Hazel";
        will.height = "6'3in";
        will.weight = "183lbs";
        iu.saveUsers(will);

        Users oscar = new Users();
        oscar.fName = "Oscar";
        oscar.lName = "Martinez";
        oscar.email = "o.martinez@gmail.com";
        oscar.password = "actually";
        oscar.personalRankId = 1;
        oscar.personalIdNum = "RS6384-48321";
        oscar.color = "teal";
        oscar.numAdd = 9;
        oscar.streetAdd = "Jones Street";
        oscar.city = "Poughkeepsie";
        oscar.state = "NY";
        oscar.country = "USA";
        oscar.idDateIssue = "1976-11-18";
        oscar.idDateExp = "2022-11-18";
        oscar.dob = "1958-11-18";
        oscar.gender = "Male";
        oscar.eyeCol = "Brown";
        oscar.height = "5'9in";
        oscar.weight = "145lbs";
        iu.saveUsers(oscar);

        /*
            ####################################################################
            #########################Insert Roles###############################
            ####################################################################
         */
        SystemRole omniAdmin = new SystemRole();
        omniAdmin.systemRoleAssmmt = SysRole.OmniAdministrator;
        omniAdmin.sysDescription = "Can administer any client, can also create, edit or delete clients";
        sr.saveSysRole(omniAdmin);

        SystemRole clientAdmin = new SystemRole();
        clientAdmin.systemRoleAssmmt = SysRole.ClientAdministrator;
        clientAdmin.sysDescription = "Can administer information within client account";
        sr.saveSysRole(clientAdmin);

        SystemRole clientMgr = new SystemRole();
        clientMgr.systemRoleAssmmt = SysRole.ClientManager;
        clientMgr.sysDescription = "Manager of client account can perform client operations including managing maps, beacons and viewing " +
                "users current and past locations";
        sr.saveSysRole(clientMgr);

        SystemRole clientBeaMgr = new SystemRole();
        clientBeaMgr.systemRoleAssmmt = SysRole.ClientBeaconManager;
        clientBeaMgr.sysDescription = "Manager of client account can perform client operations including managing beacons and viewing users " +
                "current locations";
        sr.saveSysRole(clientBeaMgr);

        SystemRole clientMapViewer = new SystemRole();
        clientMapViewer.systemRoleAssmmt = SysRole.ClientMapViewer;
        clientMapViewer.sysDescription = "Client account with privileges to view users current location information, can be used by school " +
                "administrators and local authorities to view the current location of personal without needing to modify information.";
        sr.saveSysRole(clientMapViewer);

        SystemRole clientUser = new SystemRole();
        clientUser.systemRoleAssmmt = SysRole.ClientUser;
        clientUser.sysDescription = "Basic client user account can report locations to the system but not see maps or locations.";
        sr.saveSysRole(clientUser);

        Thread.sleep(3000);

        /*
            ##########################################################################
            ########################Assign System Roles to Users######################
            #############################Update User Records##########################
            ##########################################################################
         */

        /*
            #####################################
            Michael Scott System Role Assignments
            #####################################
         */
        michael = iu.getUsersByPerIdNum("RS2342-32123");

        // output the current system role
        if(michael != null) {
            System.out.println("User: " + michael.fName + " " + michael.lName + " System Role: " + michael.systemRoleAssmt);
        }

        // assign Michael to system role
        michael.currentSysRolesId = omniAdmin.id;
        michael.systemRoleAssmt = omniAdmin.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(michael);

        // try again
        michael = iu.getUsersByPerIdNum("RS2342-32123");
        System.out.println("User: " + michael.fName + " " + michael.lName + " System Role: " + michael.systemRoleAssmt);

        /*
            #####################################
            Jim Halpert System Role Assignments
            #####################################
         */

        jim = iu.getUsersByPerIdNum("RS9825-69157");

        // output the current system role
        if(jim != null) {
            System.out.println("User: " + jim.fName + " " + jim.lName + " System Role: " + jim.systemRoleAssmt);
        }

        // assign Jim to system role
        jim.currentSysRolesId = clientMgr.id;
        jim.systemRoleAssmt = clientMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(jim);

        // try again
        jim = iu.getUsersByPerIdNum("RS9825-69157");
        System.out.println("User: " + jim.fName + " " + jim.lName + " System Role: " + jim.systemRoleAssmt);

        jim.currentSysRolesId = clientAdmin.id;
        jim.systemRoleAssmt = clientAdmin.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(jim);

        // try again
        jim = iu.getUsersByPerIdNum("RS9825-69157");
        System.out.println("User: " + jim.fName + " " + jim.lName + " System Role: " + jim.systemRoleAssmt);

        /*
            #####################################
            Pam Beesly System Role Assignments
            #####################################
         */

        // output the current system role
        pam = iu.getUsersByPerIdNum("RS8532-82547");
        // output the current system role
        if(pam != null) {
            System.out.println("User: " + pam.fName + " " + pam.lName + " System Role: " + pam.systemRoleAssmt);
        }

        // assign Pam to system role
        pam.currentSysRolesId = clientMgr.id;
        pam.systemRoleAssmt = clientMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(pam);

        // try again
        pam = iu.getUsersByPerIdNum("RS8532-82547");
        System.out.println("User: " + pam.fName + " " + pam.lName + " System Role: " + pam.systemRoleAssmt);

        /*
            Chaning Pam's last name to Halpert and her address
         */
        System.out.println("User: " + pam.fName + " just got married. Her last name is not " + pam.lName + " anymore. " +
                "Please change to " + jim.lName + "!");
        pam.lName = jim.lName;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(pam);

        // See if Pam's last name changed
        pam = iu.getUsersByPerIdNum("RS8532-82547");
        System.out.println("User: " + pam.fName + "'s new last name is " + pam.lName);

        // Changin Pam's address
        System.out.println("User: " + pam.fName + " moved in with " + jim.fName + " " + jim.lName + ". Her address is " +
                "the same as his.");
        pam.numAdd = jim.numAdd;
        pam.streetAdd = jim.streetAdd;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(pam);

        // See if Pam's address changed
        pam = iu.getUsersByPerIdNum("RS8532-82547");
        System.out.println("User: " + pam.fName + " " + pam.lName + " now lives on " + pam.numAdd + " " + pam.streetAdd + ".");

        // Adding Client Beacon Manager as another role for Pam
        pam.currentSysRolesId = clientBeaMgr.id;
        pam.systemRoleAssmt = clientBeaMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(pam);

        // try again
        pam = iu.getUsersByPerIdNum("RS8532-82547");
        System.out.println("User: " + pam.fName + " " + pam.lName + " System Role: " + pam.systemRoleAssmt);

        /*
            #####################################
            Dwight Schrute System Role Assignments
            #####################################
         */

        // output the current system role
        dwight = iu.getUsersByPerIdNum("RS7453-65129");
        // output the current system role
        if(dwight != null) {
            System.out.println("User: " + dwight.fName + " " + dwight.lName + " System Role: " + dwight.systemRoleAssmt);
        }

        // assign dwight to system role
        dwight.currentSysRolesId = clientMgr.id;
        dwight.systemRoleAssmt = clientMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(dwight);

        // try again
        dwight = iu.getUsersByPerIdNum("RS7453-65129");
        System.out.println("User: " + dwight.fName + " " + dwight.lName + " System Role: " + dwight.systemRoleAssmt);

        dwight.currentSysRolesId = clientAdmin.id;
        dwight.systemRoleAssmt = clientAdmin.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(dwight);

        // try again
        dwight = iu.getUsersByPerIdNum("RS7453-65129");
        System.out.println("User: " + dwight.fName + " " + dwight.lName + " System Role: " + dwight.systemRoleAssmt);

        /*
            #####################################
            Andy Bernard System Role Assignments
            #####################################
         */

        // output the current system role
        andy = iu.getUsersByPerIdNum("RS3645-10947");
        // output the current system role
        if(andy != null) {
            System.out.println("User: " + andy.fName + " " + andy.lName + " System Role: " + andy.systemRoleAssmt);
        }

        // assign andy to system role
        andy.currentSysRolesId = clientMgr.id;
        andy.systemRoleAssmt = clientMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(andy);

        // try again
        andy = iu.getUsersByPerIdNum("RS3645-10947");
        System.out.println("User: " + andy.fName + " " + andy.lName + " System Role: " + andy.systemRoleAssmt);

        andy.currentSysRolesId = clientAdmin.id;
        andy.systemRoleAssmt = clientAdmin.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(andy);

        // try again
        andy = iu.getUsersByPerIdNum("RS3645-10947");
        System.out.println("User: " + andy.fName + " " + andy.lName + " System Role: " + andy.systemRoleAssmt);

        /*
            #####################################
            Angela Martin System Role Assignments
            #####################################
         */

        // output the current system role
        angela = iu.getUsersByPerIdNum("RS3492-71946");
        // output the current system role
        if(angela != null) {
            System.out.println("User: " + angela.fName + " " + angela.lName + " System Role: " + angela.systemRoleAssmt);
        }

        // assign angela to system role
        angela.currentSysRolesId = clientMapViewer.id;
        angela.systemRoleAssmt = clientMapViewer.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(angela);

        // try again
        angela = iu.getUsersByPerIdNum("RS3492-71946");
        System.out.println("User: " + angela.fName + " " + angela.lName + " System Role: " + angela.systemRoleAssmt);

        /*
            Angela got married to Dwight Shrute. Change her last name to Shrute.
         */
        System.out.println("User: " + angela.fName + " just got married. Her last name is not " + angela.lName + " anymore. " +
                "Please change to " + dwight.lName + "!");
        angela.lName = dwight.lName;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(angela);

        // See if Angela's last name changed
        angela = iu.getUsersByPerIdNum("RS3492-71946");
        System.out.println("User: " + angela.fName + "'s new last name is " + angela.lName);

        // Assign Client Beacon Manager role to Angela
        angela.currentSysRolesId = clientBeaMgr.id;
        angela.systemRoleAssmt = clientBeaMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(angela);

        // try again
        angela = iu.getUsersByPerIdNum("RS3492-71946");
        System.out.println("User: " + angela.fName + " " + angela.lName + " System Role: " + angela.systemRoleAssmt);

        /*
            #####################################
            Kevin Malone System Role Assignments
            #####################################
         */

        // output the current system role
        kevin = iu.getUsersByPerIdNum("RS6284-40954");
        // output the current system role
        if(kevin != null) {
            System.out.println("User: " + kevin.fName + " " + kevin.lName + " System Role: " + kevin.systemRoleAssmt);
        }

        // assign kevin to system role
        kevin.currentSysRolesId = clientMapViewer.id;
        kevin.systemRoleAssmt = clientMapViewer.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(kevin);

        // try again
        kevin = iu.getUsersByPerIdNum("RS6284-40954");
        System.out.println("User: " + kevin.fName + " " + kevin.lName + " System Role: " + kevin.systemRoleAssmt);

        /*
            #####################################
            Creed Bratton System Role Assignments
            #####################################
         */

        // output the current system role
        creed = iu.getUsersByPerIdNum("RS8351-30954");
        // output the current system role
        if(creed != null) {
            System.out.println("User: " + creed.fName + " " + creed.lName + " System Role: " + creed.systemRoleAssmt);
        }

        // assign creed to system role
        creed.currentSysRolesId = clientUser.id;
        creed.systemRoleAssmt = clientUser.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(creed);

        // try again
        creed = iu.getUsersByPerIdNum("RS8351-30954");
        System.out.println("User: " + creed.fName + " " + creed.lName + " System Role: " + creed.systemRoleAssmt);

        /*
            #####################################
            Will Ferrell System Role Assignments
            #####################################
         */

        // output the current system role
        will = iu.getUsersByPerIdNum("RS7310-71047");
        // output the current system role
        if(will != null) {
            System.out.println("User: " + will.fName + " " + will.lName + " System Role: " + will.systemRoleAssmt);
        }

        // assign will to system role
        will.currentSysRolesId = clientUser.id;
        will.systemRoleAssmt = clientUser.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(will);

        // try again
        will = iu.getUsersByPerIdNum("RS7310-71047");
        System.out.println("User: " + will.fName + " " + will.lName + " System Role: " + will.systemRoleAssmt);


        /*
            #####################################
            Oscar Martinez System Role Assignments
            #####################################
         */

        // output the current system role
        oscar = iu.getUsersByPerIdNum("RS6384-48321");
        if(oscar != null) {
            System.out.println("User: " + oscar.fName + " " + oscar.lName + " System Role: " + oscar.systemRoleAssmt);
        }

        // assign oscar to system role
        oscar.currentSysRolesId = clientUser.id;
        oscar.systemRoleAssmt = clientUser.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(oscar);

        // try again
        oscar = iu.getUsersByPerIdNum("RS6384-48321");
        System.out.println("User: " + oscar.fName + " " + oscar.lName + " System Role: " + oscar.systemRoleAssmt);

        /*
            ##############################################
            ############Once Roles Are Assigned###########
            #############Create Tenant/Client#############
            ##############################################
         */

        // Creating Arlington High School Tenant
        Arlington_Client arlClient = new Arlington_Client();
        arlClient.arlingtonClient = Client_schools.Arlington_High_School;
        arlClient.omniAdminRole = michael.fName + " " + michael.lName;
        arlClient.numFloors = 2;
        cac.saveClient(arlClient);
        
        /*
            ####################################################################
            #########################Insert Beacons#############################
            ####################################################################
         */
        
        Locations rssi_zero = new Locations();
        rssi_zero.client = arlClient.arlingtonClient;
        rssi_zero.floor = 1;
        rssi_zero.beaconRssi = 0;
        rssi_zero.longitude = 41.6734;
        rssi_zero.latitude = 73.7966;
        il.saveLocations(rssi_zero);

        Locations rssi_one = new Locations();
        rssi_one.client = arlClient.arlingtonClient;
        rssi_one.floor = 1;
        rssi_one.beaconRssi = 1;
        rssi_one.longitude = 42.6734;
        rssi_one.latitude = 73.2678;
        il.saveLocations(rssi_one);

        Locations rssi_two = new Locations();
        rssi_two.client = arlClient.arlingtonClient;
        rssi_two.floor = 1;
        rssi_two.beaconRssi = 2;
        rssi_two.longitude = 41.4734;
        rssi_two.latitude = 74.9172;
        il.saveLocations(rssi_two);

        Locations rssi_three = new Locations();
        rssi_three.client = arlClient.arlingtonClient;
        rssi_three.floor = 1;
        rssi_three.beaconRssi = 3;
        rssi_three.longitude = 41.2134;
        rssi_three.latitude = 73.0012;
        il.saveLocations(rssi_three);

        Locations rssi_four = new Locations();
        rssi_four.client = arlClient.arlingtonClient;
        rssi_four.floor = 1;
        rssi_four.beaconRssi = 4;
        rssi_four.longitude = 41.6267;
        rssi_four.latitude = 73.8192;
        il.saveLocations(rssi_four);

        Locations rssi_five = new Locations();
        rssi_five.client = arlClient.arlingtonClient;
        rssi_five.floor = 1;
        rssi_five.beaconRssi = 5;
        rssi_five.longitude = 41.9473;
        rssi_five.latitude = 74.5812;
        il.saveLocations(rssi_five);

        Locations rssi_six = new Locations();
        rssi_six.client = arlClient.arlingtonClient;
        rssi_six.floor = 1;
        rssi_six.beaconRssi = 6;
        rssi_six.longitude = 41.4721;
        rssi_six.latitude = 73.6123;
        il.saveLocations(rssi_six);

        Locations rssi_seven = new Locations();
        rssi_seven.client = arlClient.arlingtonClient;
        rssi_seven.floor = 1;
        rssi_seven.beaconRssi = 7;
        rssi_seven.longitude = 41.4567;
        rssi_seven.latitude = 73.4492;
        il.saveLocations(rssi_seven);

        Locations rssi_eight = new Locations();
        rssi_eight.client = arlClient.arlingtonClient;
        rssi_eight.floor = 1;
        rssi_eight.beaconRssi = 8;
        rssi_eight.longitude = 43.1002;
        rssi_eight.latitude = 73.6621;
        il.saveLocations(rssi_eight);

        Locations rssi_nine = new Locations();
        rssi_nine.client = arlClient.arlingtonClient;
        rssi_nine.floor = 1;
        rssi_nine.beaconRssi = 9;
        rssi_nine.longitude = 41.0094;
        rssi_nine.latitude = 74.1192;
        il.saveLocations(rssi_nine);

        Locations rssi_ten = new Locations();
        rssi_ten.client = arlClient.arlingtonClient;
        rssi_ten.floor = 2;
        rssi_ten.beaconRssi = 10;
        rssi_ten.longitude = 42.9421;
        rssi_ten.latitude = 73.8932;
        il.saveLocations(rssi_ten);

        Locations rssi_eleven = new Locations();
        rssi_eleven.client = arlClient.arlingtonClient;
        rssi_eleven.floor = 2;
        rssi_eleven.beaconRssi = 11;
        rssi_eleven.longitude = 42.8345;
        rssi_eleven.latitude = 73.3382;
        il.saveLocations(rssi_eleven);

        Locations rssi_twelve = new Locations();
        rssi_twelve.client = arlClient.arlingtonClient;
        rssi_twelve.floor = 2;
        rssi_twelve.beaconRssi = 12;
        rssi_twelve.longitude = 42.1093;
        rssi_twelve.latitude = 73.2934;
        il.saveLocations(rssi_twelve);

        Locations rssi_thirteen = new Locations();
        rssi_thirteen.client = arlClient.arlingtonClient;
        rssi_thirteen.floor = 2;
        rssi_thirteen.beaconRssi = 13;
        rssi_thirteen.longitude = 41.6832;
        rssi_thirteen.latitude = 73.8805;
        il.saveLocations(rssi_thirteen);

        Locations rssi_fourteen = new Locations();
        rssi_fourteen.client = arlClient.arlingtonClient;
        rssi_fourteen.floor = 2;
        rssi_fourteen.beaconRssi = 14;
        rssi_fourteen.longitude = 41.6001;
        rssi_fourteen.latitude = 73.0022;
        il.saveLocations(rssi_fourteen);

        Locations rssi_fifteen = new Locations();
        rssi_fifteen.client = arlClient.arlingtonClient;
        rssi_fifteen.floor = 2;
        rssi_fifteen.beaconRssi = 15;
        rssi_fifteen.longitude = 42.1834;
        rssi_fifteen.latitude = 73.3352;
        il.saveLocations(rssi_fifteen);

        Locations rssi_sixteen = new Locations();
        rssi_sixteen.client = arlClient.arlingtonClient;
        rssi_sixteen.floor = 2;
        rssi_sixteen.beaconRssi = 16;
        rssi_sixteen.longitude = 41.7345;
        rssi_sixteen.latitude = 73.8492;
        il.saveLocations(rssi_sixteen);

        Locations rssi_seventeen = new Locations();
        rssi_seventeen.client = arlClient.arlingtonClient;
        rssi_seventeen.floor = 2;
        rssi_seventeen.beaconRssi = 17;
        rssi_seventeen.longitude = 41.9021;
        rssi_seventeen.latitude = 74.4765;
        il.saveLocations(rssi_seventeen);

        Locations rssi_eighteen = new Locations();
        rssi_eighteen.client = arlClient.arlingtonClient;
        rssi_eighteen.floor = 2;
        rssi_eighteen.beaconRssi = 18;
        rssi_eighteen.longitude = 44.732;
        rssi_eighteen.latitude = 73.7576;
        il.saveLocations(rssi_eighteen);

        Locations rssi_nineteen = new Locations();
        rssi_nineteen.client = arlClient.arlingtonClient;
        rssi_nineteen.floor = 2;
        rssi_nineteen.beaconRssi = 19;
        rssi_nineteen.longitude = 43.4012;
        rssi_nineteen.latitude = 73.7006;
        il.saveLocations(rssi_nineteen);

        /*
            ####################################################################
            ###############Insert Visitors every 5-10 seconds###################
            ####################################################################
         */
        Visit mscott = new Visit();
        mscott.name = michael.fName + " " + michael.lName;
        mscott.streetAddress = michael.numAdd + " " + michael.streetAdd;
        mscott.city = michael.city;
        mscott.state = michael.state;
        mscott.personalIdNum = michael.personalIdNum;
        mscott.checkStatus = CheckStatus.CheckedIn;
        mscott.destination = Destination.Principal;
        mscott.currentUserId = michael.id;
        cis.saveVisit(mscott);

        Thread.sleep(10000);

        Visit jhalpert = new Visit();
        jhalpert.name = jim.fName + " " + jim.lName;
        jhalpert.streetAddress = jim.numAdd + " " + jim.streetAdd;
        jhalpert.city = jim.city;
        jhalpert.state = jim.state;
        jhalpert.personalIdNum = jim.personalIdNum;
        jhalpert.checkStatus = CheckStatus.CheckedIn;
        jhalpert.destination = Destination.Gym;
        jhalpert.currentUserId = jim.id;
        cis.saveVisit(jhalpert);

        Thread.sleep(5000);

        Visit dshrute = new Visit();
        dshrute.name = dwight.fName + " " + dwight.lName;
        dshrute.streetAddress = dwight.numAdd + " " + dwight.streetAdd;
        dshrute.city = dwight.city;
        dshrute.state = dwight.state;
        dshrute.personalIdNum = dwight.personalIdNum;
        dshrute.checkStatus = CheckStatus.JustArrived;
        dshrute.destination = Destination.History;
        dshrute.currentUserId = dwight.id;
        cis.saveVisit(dshrute);

        Thread.sleep(5000);

        Visit abernard = new Visit();
        abernard.name = andy.fName + " " + andy.lName;
        abernard.streetAddress = andy.numAdd + " " + andy.streetAdd;
        abernard.city = andy.city;
        abernard.state = andy.state;
        abernard.personalIdNum = andy.personalIdNum;
        abernard.checkStatus = CheckStatus.JustArrived;
        abernard.destination = Destination.Music;
        abernard.currentUserId = andy.id;
        cis.saveVisit(abernard);

        Thread.sleep(5000);

        Visit wferrell = new Visit();
        wferrell.name = will.fName + " " + will.lName;
        wferrell.streetAddress = will.numAdd + " " + will.streetAdd;
        wferrell.city = will.city;
        wferrell.state = will.state;
        wferrell.personalIdNum = will.personalIdNum;
        wferrell.checkStatus = CheckStatus.CheckedOutCompleted;
        wferrell.destination = Destination.Out;
        wferrell.currentUserId = will.id;
        cis.saveVisit(wferrell);

        /*
            Running queries based on project
         */

        /*
            ##############################################
            ##########Update the Check In Status##########
            ##############################################
         */



        List<Visit> visit01 = cis.getVisitByCheckStatus(CheckStatus.JustArrived);
        for(Visit visit: visit01) {
            // see if there should be a visit and get the visit
            if(visit.currentUserId >= 0) {
                visit.currentUser = iu.getUsersById(visit.currentUserId);
            }

            String users = (visit.currentUser != null) ? visit.currentUser.fName : "no check in";

            System.out.println("Check In Status: " + users + " - " + visit.checkStatus);
        }

        // Wait a few seconds
        Thread.sleep(6000);

        // change all Just Arrived Check In Status to CheckedIn
        for (Visit visit: visit01) {
            visit.checkStatus = CheckStatus.CheckedIn;
            cis.saveVisit(visit);
        }

        // Wait a few seconds
        Thread.sleep(6000);

        System.out.println("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        // re-run the query and output the results again, this time with history so we can see the change!
        List<FdfEntity<Visit>> visit01WithHistory = cis.getVisitByCheckStatusWithHistory(CheckStatus.CheckedIn);
        for(FdfEntity<Visit> visitWithHistory: visit01WithHistory) {
            // first output the visiting current status
            System.out.println("Check In Status: " + visitWithHistory.current.name + " - " +
                    visitWithHistory.current.checkStatus);

            System.out.println("----- History -----");
            // Now show the check in history records
            for (Visit visitHistory: visitWithHistory.history) {
                System.out.println("Start time: " + visitHistory.arsd + " End time: " + visitHistory.ared +
                        " check in status: " + visitHistory.checkStatus + " for " + visitHistory.currentUser);
            }
            System.out.println("______________________");
        }

        /*
            ####################################################################
            ####################################################################
            ####################################################################
            ################Spackenkill High School District####################
            ####################################################################
            ####################################################################
            ####################################################################
         */

        SK_InsertUsers skiu = new SK_InsertUsers();
        SK_InsertSysRoles sksr = new SK_InsertSysRoles();
        SK_InsertLocation skil = new SK_InsertLocation();
        SK_CheckInStatus skcis = new SK_CheckInStatus();
        CreateSpackenkillClient csc = new CreateSpackenkillClient();

        SK_Users will2 = new SK_Users();
        will2.fName = "Will";
        will2.lName = "Ferrell";
        will2.email = "will.ferrell@spackenkill.com";
        will2.password = "iammanager";
        will2.personalRankId = 1;
        will2.personalIdNum = "RS7310-71047";
        will2.color = "black";
        will2.numAdd = 6;
        will2.streetAdd = "Irving Road";
        will2.city = "Poughkeepsie";
        will2.state = "NY";
        will2.country = "USA";
        will2.idDateIssue = "1985-07-16";
        will2.idDateExp = "2022-07-16";
        will2.dob = "1967-07-16";
        will2.gender = "Male";
        will2.eyeCol = "Hazel";
        will2.height = "6'3in";
        will2.weight = "183lbs";
        skiu.saveUsers(will2);

        SK_Users brian = new SK_Users();
        brian.fName = "Brian";
        brian.lName = "Fantana";
        brian.email = "brian.fantana@spackenkill.edu";
        brian.password = "theoctagon";
        brian.personalRankId = 1;
        brian.personalIdNum = "RS4525-69102";
        brian.color = "blue";
        brian.numAdd = 8;
        brian.streetAdd = "Panther Road";
        brian.city = "Fishkill";
        brian.state = "NY";
        brian.country = "USA";
        brian.idDateIssue = "1993-8-22";
        brian.idDateExp = "2022-8-22";
        brian.dob = "1978-8-22";
        brian.gender = "Male";
        brian.eyeCol = "Hazel";
        brian.height = "6'0in";
        brian.weight = "180lbs";
        skiu.saveUsers(brian);

        SK_Users veronica = new SK_Users();
        veronica.fName = "Veronica";
        veronica.lName = "Corningstone";
        veronica.email = "veronica.corn@spackenkill.edu";
        veronica.password = "ronlovesme";
        veronica.personalRankId = 1;
        veronica.personalIdNum = "RS8546-88747";
        veronica.color = "orange";
        veronica.numAdd = 33;
        veronica.streetAdd = "Hellen Street";
        veronica.city = "Poughkeepsie";
        veronica.state = "NY";
        veronica.country = "USA";
        veronica.idDateIssue = "1994-05-21";
        veronica.idDateExp = "2022-05-21";
        veronica.dob = "1974-05-21";
        veronica.gender = "Female";
        veronica.eyeCol = "Green";
        veronica.height = "5'6in";
        veronica.weight = "126lbs";
        skiu.saveUsers(veronica);

        SK_Users champ = new SK_Users();
        champ.fName = "Champ";
        champ.lName = "Kind";
        champ.email = "Champ.Kind@spackenkill.edu";
        champ.password = "whaaaaamieee";
        champ.personalRankId = 1;
        champ.personalIdNum = "RS2853-84129";
        champ.color = "green";
        champ.numAdd = 1;
        champ.streetAdd = "Sports Road";
        champ.city = "Kingston";
        champ.state = "NY";
        champ.country = "USA";
        champ.idDateIssue = "1988-04-20";
        champ.idDateExp = "2027-04-20";
        champ.dob = "1970-04-20";
        champ.gender = "Male";
        champ.eyeCol = "Blue";
        champ.height = "6'2in";
        champ.weight = "200lbs";
        skiu.saveUsers(champ);

        SK_Users arturo = new SK_Users();
        arturo.fName = "Arturo";
        arturo.lName = "Mendes";
        arturo.email = "arturo.mendes@spackenkill.edu";
        arturo.password = "comoesta";
        arturo.personalRankId = 1;
        arturo.personalIdNum = "RS3140-90944";
        arturo.color = "purple";
        arturo.numAdd = 7;
        arturo.streetAdd = "James Circle";
        arturo.city = "Poughkeepsie";
        arturo.state = "NY";
        arturo.country = "USA";
        arturo.idDateIssue = "1992-12-19";
        arturo.idDateExp = "2023-12-19";
        arturo.dob = "1974-12-19";
        arturo.gender = "Male";
        arturo.eyeCol = "Blue";
        arturo.height = "5'11in";
        arturo.weight = "165lbs";
        skiu.saveUsers(arturo);

        SK_Users frank = new SK_Users();
        frank.fName = "Frank";
        frank.lName = "Vitchard";
        frank.email = "Frank.Vitchard@spackenkill.edu";
        frank.password = "notowen";
        frank.personalRankId = 1;
        frank.personalIdNum = "RS6482-70346";
        frank.color = "pink";
        frank.numAdd = 1102;
        frank.streetAdd = "Jerry Lane";
        frank.city = "Saugerties";
        frank.state = "NY";
        frank.country = "USA";
        frank.idDateIssue = "1989-10-05";
        frank.idDateExp = "2022-10-05";
        frank.dob = "1971-10-05";
        frank.gender = "Male";
        frank.eyeCol = "Green";
        frank.height = "5'11in";
        frank.weight = "180lbs";
        skiu.saveUsers(frank);

        SK_Users brick = new SK_Users();
        brick.fName = "Brick";
        brick.lName = "Tamland";
        brick.email = "brick.tamland@spackenkill.edu";
        brick.password = "loudnoises";
        brick.personalRankId = 1;
        brick.personalIdNum = "RS6544-09587";
        brick.color = "brown";
        brick.numAdd = 89;
        brick.streetAdd = "Lamp Road";
        brick.city = "Poughkeepsie";
        brick.state = "NY";
        brick.country = "USA";
        brick.idDateIssue = "1983-09-29";
        brick.idDateExp = "2023-09-29";
        brick.dob = "1970-09-29";
        brick.gender = "Male";
        brick.eyeCol = "Green";
        brick.height = "6'0in";
        brick.weight = "209lbs";
        skiu.saveUsers(brick);

        SK_Users seth = new SK_Users();
        seth.fName = "Seth";
        seth.lName = "Rogen";
        seth.email = "s.rogen@gmail.com.com";
        seth.password = "mcclovin";
        seth.personalRankId = 1;
        seth.personalIdNum = "RS7358-31974";
        seth.color = "yellow";
        seth.numAdd = 2;
        seth.streetAdd = "Hone Street";
        seth.city = "Kingston";
        seth.state = "NY";
        seth.country = "USA";
        seth.idDateIssue = "1980-03-09";
        seth.idDateExp = "2022-03-09";
        seth.dob = "1972-03-09";
        seth.gender = "Male";
        seth.eyeCol = "Black";
        seth.height = "5'11in";
        seth.weight = "200lbs";
        skiu.saveUsers(seth);

        SK_Users garth = new SK_Users();
        garth.fName = "Garth";
        garth.lName = "Parnell";
        garth.email = "g.parnell@gmail.com";
        garth.password = "whoisthisguy";
        garth.personalRankId = 1;
        garth.personalIdNum = "RS5810-24047";
        garth.color = "black";
        garth.numAdd = 90;
        garth.streetAdd = "Ferndale Lane";
        garth.city = "Poughkeepsie";
        garth.state = "NY";
        garth.country = "USA";
        garth.idDateIssue = "1978-08-26";
        garth.idDateExp = "2022-08-26";
        garth.dob = "1970-08-26";
        garth.gender = "Male";
        garth.eyeCol = "Hazel";
        garth.height = "6'3in";
        garth.weight = "205lbs";
        skiu.saveUsers(garth);

        SK_Users rorie = new SK_Users();
        rorie.fName = "Rorie";
        rorie.lName = "Reyes";
        rorie.email = "r.reyes@gmail.com";
        rorie.password = "notastudent";
        rorie.personalRankId = 1;
        rorie.personalIdNum = "RS6387-48333";
        rorie.color = "teal";
        rorie.numAdd = 481;
        rorie.streetAdd = "Maple Ave";
        rorie.city = "Poughkeepsie";
        rorie.state = "NY";
        rorie.country = "USA";
        rorie.idDateIssue = "2008-01-26";
        rorie.idDateExp = "2022-01-26";
        rorie.dob = "1991-01-26";
        rorie.gender = "Male";
        rorie.eyeCol = "Brown";
        rorie.height = "5'9in";
        rorie.weight = "170lbs";
        skiu.saveUsers(rorie);

        /*
            ####################################################################
            #########################Insert Roles###############################
            ####################################################################
         */
        SK_SystemRole omniManager = new SK_SystemRole();
        omniManager.systemRoleAssmmt = SK_SysRole.OmniManager;
        omniManager.sysDescription = "Manages all the Client Managers";
        sksr.saveSysRole(omniManager);

        SK_SystemRole omniMapViewer = new SK_SystemRole();
        omniMapViewer.systemRoleAssmmt = SK_SysRole.OmniMapViewer;
        omniMapViewer.sysDescription = "Manages all the Client Map Viewers";
        sksr.saveSysRole(omniMapViewer);

        SK_SystemRole omniBeaconMgr = new SK_SystemRole();
        omniBeaconMgr.systemRoleAssmmt = SK_SysRole.OmniBeaconManager;
        omniBeaconMgr.sysDescription = "Manages all the Client Beacon Managers";
        sksr.saveSysRole(omniBeaconMgr);

        SK_SystemRole skOmniAdmin = new SK_SystemRole();
        skOmniAdmin.systemRoleAssmmt = SK_SysRole.OmniAdministrator;
        skOmniAdmin.sysDescription = "Can administer any client, can also create, edit or delete clients";
        sksr.saveSysRole(skOmniAdmin);

        SK_SystemRole skCliAdmin = new SK_SystemRole();
        skCliAdmin.systemRoleAssmmt = SK_SysRole.ClientAdministrator;
        skCliAdmin.sysDescription = "Can administer information within client account";
        sksr.saveSysRole(skCliAdmin);

        SK_SystemRole skCliMgr = new SK_SystemRole();
        skCliMgr.systemRoleAssmmt = SK_SysRole.ClientManager;
        skCliMgr.sysDescription = "Manager of client account can perform client operations including managing maps, beacons and viewing users current and past locations";
        sksr.saveSysRole(skCliMgr);

        SK_SystemRole skCliBeaMgr = new SK_SystemRole();
        skCliBeaMgr.systemRoleAssmmt = SK_SysRole.ClientBeaconManager;
        skCliBeaMgr.sysDescription = "Manager of client account can perform client operations including managing beacons and viewing users current locations";
        sksr.saveSysRole(skCliBeaMgr);

        SK_SystemRole skCliMapView = new SK_SystemRole();
        skCliMapView.systemRoleAssmmt = SK_SysRole.ClientMapViewer;
        skCliMapView.sysDescription = "Client account with privileges to view users current location information, can be used by school \" +\n" +
                "                \"administrators and local authorities to view the current location of personal without needing to modify information.";
        sksr.saveSysRole(skCliMapView);

        SK_SystemRole skCliUser = new SK_SystemRole();
        skCliUser.systemRoleAssmmt = SK_SysRole.ClientUser;
        skCliUser.sysDescription = "Basic client user account can report locations to the system but not see maps or locations.";
        sksr.saveSysRole(skCliUser);

        /*
            ##########################################################################
            ########################Assign System Roles to Users######################
            #############################Update User Records##########################
            ##########################################################################
         */

        /*
            #####################################
            Will Ferrell 2 System Role Assignments
            #####################################
         */
        will2 = skiu.getUsersByPerIdNum("RS7310-71047");

        // output the current system role
        if(will2 != null) {
            System.out.println("User: " + will2.fName + " " + will2.lName + " System Role: " + will2.systemRoleAssmt);
        }

        // assign Michael to system role
        will2.currentSysRolesId = skOmniAdmin.id;
        will2.systemRoleAssmt = skOmniAdmin.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(will2);

        // try again
        will2 = skiu.getUsersByPerIdNum("RS7310-71047");
        System.out.println("User: " + will2.fName + " " + will2.lName + " System Role: " + will2.systemRoleAssmt);

        /*
            #####################################
            Brian Fantana System Role Assignments
            #####################################
         */

        brian = skiu.getUsersByPerIdNum("RS4525-69102");

        // output the current system role
        if(brian != null) {
            System.out.println("User: " + brian.fName + " " + brian.lName + " System Role: " + brian.systemRoleAssmt);
        }

        // assign Jim to system role
        brian.currentSysRolesId = omniManager.id;
        brian.systemRoleAssmt = omniManager.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(brian);

        // try again
        brian = skiu.getUsersByPerIdNum("RS4525-69102");
        System.out.println("User: " + brian.fName + " " + brian.lName + " System Role: " + brian.systemRoleAssmt);

        brian.currentSysRolesId = skCliAdmin.id;
        brian.systemRoleAssmt = skCliAdmin.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        iu.saveUsers(jim);

        // try again
        brian = skiu.getUsersByPerIdNum("RS4525-69102");
        System.out.println("User: " + brian.fName + " " + brian.lName + " System Role: " + brian.systemRoleAssmt);

        /*
            #####################################
            Veronica Corningstone System Role Assignments
            #####################################
         */

        // output the current system role
        veronica = skiu.getUsersByPerIdNum("RS8546-88747");
        // output the current system role
        if(veronica != null) {
            System.out.println("User: " + veronica.fName + " " + veronica.lName + " System Role: " + veronica.systemRoleAssmt);
        }

        // assign Pam to system role
        veronica.currentSysRolesId = omniBeaconMgr.id;
        veronica.systemRoleAssmt = omniBeaconMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(veronica);

        // try again
        veronica = skiu.getUsersByPerIdNum("RS8546-88747");
        System.out.println("User: " + veronica.fName + " " + veronica.lName + " System Role: " + veronica.systemRoleAssmt);

        // Adding Client Beacon Manager as another role for Pam
        veronica.currentSysRolesId = skCliAdmin.id;
        veronica.systemRoleAssmt = skCliAdmin.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(veronica);

        // try again
        veronica = skiu.getUsersByPerIdNum("RS8546-88747");
        System.out.println("User: " + veronica.fName + " " + veronica.lName + " System Role: " + veronica.systemRoleAssmt);

        /*
            #####################################
            Champ Kind System Role Assignments
            #####################################
         */

        // output the current system role
        champ = skiu.getUsersByPerIdNum("RS2853-84129");
        // output the current system role
        if(champ != null) {
            System.out.println("User: " + champ.fName + " " + champ.lName + " System Role: " + champ.systemRoleAssmt);
        }

        // assign champ to system role
        champ.currentSysRolesId = skCliMgr.id;
        champ.systemRoleAssmt = skCliMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(champ);

        // try again
        champ = skiu.getUsersByPerIdNum("RS2853-84129");
        System.out.println("User: " + champ.fName + " " + champ.lName + " System Role: " + champ.systemRoleAssmt);

        champ.currentSysRolesId = skCliMgr.id;
        champ.systemRoleAssmt = skCliBeaMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(champ);

        // try again
        champ = skiu.getUsersByPerIdNum("RS2853-84129");
        System.out.println("User: " + champ.fName + " " + champ.lName + " System Role: " + champ.systemRoleAssmt);

        /*
            #####################################
            Arturo Mendes Bernard System Role Assignments
            #####################################
         */

        // output the current system role
        arturo = skiu.getUsersByPerIdNum("RS3140-90944");
        // output the current system role
        if(arturo != null) {
            System.out.println("User: " + arturo.fName + " " + arturo.lName + " System Role: " + arturo.systemRoleAssmt);
        }

        // assign andy to system role
        arturo.currentSysRolesId = skCliBeaMgr.id;
        arturo.systemRoleAssmt = skCliBeaMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(arturo);

        // try again
        arturo = skiu.getUsersByPerIdNum("RS3140-90944");
        System.out.println("User: " + arturo.fName + " " + arturo.lName + " System Role: " + arturo.systemRoleAssmt);

        arturo.currentSysRolesId = skCliMapView.id;
        arturo.systemRoleAssmt = skCliMapView.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(arturo);

        // try again
        arturo = skiu.getUsersByPerIdNum("RS3140-90944");
        System.out.println("User: " + arturo.fName + " " + arturo.lName + " System Role: " + arturo.systemRoleAssmt);

        /*
            #####################################
            Frank Vitchard System Role Assignments
            #####################################
         */

        // output the current system role
        frank = skiu.getUsersByPerIdNum("RS6482-70346");
        // output the current system role
        if(frank != null) {
            System.out.println("User: " + frank.fName + " " + frank.lName + " System Role: " + frank.systemRoleAssmt);
        }

        // assign angela to system role
        frank.currentSysRolesId = skCliMapView.id;
        frank.systemRoleAssmt = skCliMapView.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(frank);

        // try again
        frank = skiu.getUsersByPerIdNum("RS6482-70346");
        System.out.println("User: " + frank.fName + " " + frank.lName + " System Role: " + frank.systemRoleAssmt);

        // Assign Client Beacon Manager role to Angela
        frank.currentSysRolesId = skCliBeaMgr.id;
        frank.systemRoleAssmt = skCliBeaMgr.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(frank);

        // try again
        frank = skiu.getUsersByPerIdNum("RS6482-70346");
        System.out.println("User: " + frank.fName + " " + frank.lName + " System Role: " + frank.systemRoleAssmt);

        /*
            #####################################
            Brick Tamland System Role Assignments
            #####################################
         */

        // output the current system role
        brick = skiu.getUsersByPerIdNum("RS6544-09587");
        // output the current system role
        if(brick != null) {
            System.out.println("User: " + brick.fName + " " + brick.lName + " System Role: " + brick.systemRoleAssmt);
        }

        // Changin Brick's name to Steve Carell
        System.out.println("User: " + brick.fName + " " + brick.lName + " is changing his name");
        brick.fName = "Steve";
        brick.lName = "Carell";
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(brick);

        // See if Brick Tamlan changed to Steve Carell
        brick = skiu.getUsersByPerIdNum("RS6544-09587");
        System.out.println("User: " + brick.fName + " " + brick.lName + " changed his name.");

        // assign kevin to system role
        brick.currentSysRolesId = skCliMapView.id;
        brick.systemRoleAssmt = skCliMapView.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(brick);

        // try again
        brick = skiu.getUsersByPerIdNum("RS6544-09587");
        System.out.println("User: " + brick.fName + " " + brick.lName + " System Role: " + brick.systemRoleAssmt);

        /*
            #####################################
            Seth Rogen System Role Assignments
            #####################################
         */

        // output the current system role
        seth = skiu.getUsersByPerIdNum("RS7358-31974");
        // output the current system role
        if(seth != null) {
            System.out.println("User: " + seth.fName + " " + seth.lName + " System Role: " + seth.systemRoleAssmt);
        }

        // assign creed to system role
        seth.currentSysRolesId = skCliUser.id;
        seth.systemRoleAssmt = skCliUser.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(seth);

        // try again
        seth = skiu.getUsersByPerIdNum("RS7358-31974");
        System.out.println("User: " + seth.fName + " " + seth.lName + " System Role: " + seth.systemRoleAssmt);

        /*
            #####################################
            Garth System Role Assignments
            #####################################
         */

        // output the current system role
        garth = skiu.getUsersByPerIdNum("RS5810-24047");
        // output the current system role
        if(garth != null) {
            System.out.println("User: " + garth.fName + " " + garth.lName + " System Role: " + garth.systemRoleAssmt);
        }

        // assign will to system role
        garth.currentSysRolesId = skCliUser.id;
        garth.systemRoleAssmt = skCliUser.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(garth);

        // try again
        garth = skiu.getUsersByPerIdNum("RS5810-24047");
        System.out.println("User: " + garth.fName + " " + garth.lName + " System Role: " + garth.systemRoleAssmt);


        /*
            #####################################
            Rorie Reyes System Role Assignments
            #####################################
         */

        // output the current system role
        rorie = skiu.getUsersByPerIdNum("RS6387-48333");
        if(rorie != null) {
            System.out.println("User: " + rorie.fName + " " + rorie.lName + " System Role: " + rorie.systemRoleAssmt);
        }

        // assign rorie to system role
        rorie.currentSysRolesId = skCliUser.id;
        rorie.systemRoleAssmt = skCliUser.systemRoleAssmmt;
        // Wait a few seconds
        Thread.sleep(3000);
        skiu.saveUsers(rorie);

        // try again
        rorie = skiu.getUsersByPerIdNum("RS6387-48333");
        System.out.println("User: " + rorie.fName + " " + rorie.lName + " System Role: " + rorie.systemRoleAssmt);

        /*
            ##############################################
            ############Once Roles Are Assigned###########
            #############Create Tenant/Client#############
            ##############################################
         */

        // Creating Spackenkill High School Tenant
        Spackenkill_Client spaClient = new Spackenkill_Client();
        spaClient.spackenkillClient = Client_schools.Spackenkill_High_School;
        spaClient.omniAdminRole = will2.fName + " " + will2.lName;
        spaClient.numFloors = 2;
        csc.saveClient(spaClient);

        /*
            ####################################################################
            #########################Insert Beacons#############################
            ####################################################################
         */

        SK_Locations rssi_twenty = new SK_Locations();
        rssi_twenty.client = spaClient.spackenkillClient;
        rssi_twenty.floor = 1;
        rssi_twenty.beaconRssi = 20;
        rssi_twenty.longitude = 41.6873;
        rssi_twenty.latitude = 73.1943;
        skil.saveLocations(rssi_twenty);

        SK_Locations rssi_twentyone = new SK_Locations();
        rssi_twentyone.client = spaClient.spackenkillClient;
        rssi_twentyone.floor = 1;
        rssi_twentyone.beaconRssi = 21;
        rssi_twentyone.longitude = 42.9483;
        rssi_twentyone.latitude = 73.1033;
        skil.saveLocations(rssi_twentyone);

        SK_Locations rssi_twentytwo = new SK_Locations();
        rssi_twentytwo.client = spaClient.spackenkillClient;
        rssi_twentytwo.floor = 1;
        rssi_twentytwo.beaconRssi = 22;
        rssi_twentytwo.longitude = 41.8372;
        rssi_twentytwo.latitude = 74.0495;
        skil.saveLocations(rssi_twentytwo);

        SK_Locations rssi_twentythree = new SK_Locations();
        rssi_twentythree.client = spaClient.spackenkillClient;
        rssi_twentythree.floor = 1;
        rssi_twentythree.beaconRssi = 23;
        rssi_twentythree.longitude = 41.8412;
        rssi_twentythree.latitude = 73.5896;
        skil.saveLocations(rssi_twentythree);

        SK_Locations rssi_twentyfour = new SK_Locations();
        rssi_twentyfour.client = spaClient.spackenkillClient;
        rssi_twentyfour.floor = 1;
        rssi_twentyfour.beaconRssi = 24;
        rssi_twentyfour.longitude = 41.1254;
        rssi_twentyfour.latitude = 73.7458;
        skil.saveLocations(rssi_twentyfour);

        SK_Locations rssi_twentyfive = new SK_Locations();
        rssi_twentyfive.client = spaClient.spackenkillClient;
        rssi_twentyfive.floor = 1;
        rssi_twentyfive.beaconRssi = 25;
        rssi_twentyfive.longitude = 41.1397;
        rssi_twentyfive.latitude = 74.9713;
        skil.saveLocations(rssi_twentyfive);

        SK_Locations rssi_twentysix = new SK_Locations();
        rssi_twentysix.client = spaClient.spackenkillClient;
        rssi_twentysix.floor = 1;
        rssi_twentysix.beaconRssi = 26;
        rssi_twentysix.longitude = 41.5138;
        rssi_twentysix.latitude = 73.3741;
        skil.saveLocations(rssi_twentysix);

        SK_Locations rssi_twentyseven = new SK_Locations();
        rssi_twentyseven.client = spaClient.spackenkillClient;
        rssi_twentyseven.floor = 1;
        rssi_twentyseven.beaconRssi = 27;
        rssi_twentyseven.longitude = 41.7145;
        rssi_twentyseven.latitude = 73.0125;
        skil.saveLocations(rssi_twentyseven);

        SK_Locations rssi_twentyeight = new SK_Locations();
        rssi_twentyeight.client = spaClient.spackenkillClient;
        rssi_twentyeight.floor = 1;
        rssi_twentyeight.beaconRssi = 28;
        rssi_twentyeight.longitude = 43.8136;
        rssi_twentyeight.latitude = 73.4589;
        skil.saveLocations(rssi_twentyeight);

        SK_Locations rssi_twentynine = new SK_Locations();
        rssi_twentynine.client = spaClient.spackenkillClient;
        rssi_twentynine.floor = 1;
        rssi_twentynine.beaconRssi = 29;
        rssi_twentynine.longitude = 41.8264;
        rssi_twentynine.latitude = 74.4912;
        skil.saveLocations(rssi_twentynine);

        SK_Locations rssi_thirty = new SK_Locations();
        rssi_thirty.client = spaClient.spackenkillClient;
        rssi_thirty.floor = 2;
        rssi_thirty.beaconRssi = 30;
        rssi_thirty.longitude = 42.4685;
        rssi_thirty.latitude = 73.3258;
        skil.saveLocations(rssi_thirty);

        SK_Locations rssi_thirtyone = new SK_Locations();
        rssi_thirtyone.client = spaClient.spackenkillClient;
        rssi_thirtyone.floor = 2;
        rssi_thirtyone.beaconRssi = 31;
        rssi_thirtyone.longitude = 42.8822;
        rssi_thirtyone.latitude = 73.6633;
        skil.saveLocations(rssi_thirtyone);

        SK_Locations rssi_thirtytwo = new SK_Locations();
        rssi_thirtytwo.client = spaClient.spackenkillClient;
        rssi_thirtytwo.floor = 2;
        rssi_thirtytwo.beaconRssi = 32;
        rssi_thirtytwo.longitude = 42.4712;
        rssi_thirtytwo.latitude = 73.6674;
        skil.saveLocations(rssi_thirtytwo);

        SK_Locations rssi_thirtythree = new SK_Locations();
        rssi_thirtythree.client = spaClient.spackenkillClient;
        rssi_thirtythree.floor = 2;
        rssi_thirtythree.beaconRssi = 33;
        rssi_thirtythree.longitude = 41.5800;
        rssi_thirtythree.latitude = 73.0097;
        skil.saveLocations(rssi_thirtythree);

        SK_Locations rssi_thirtyfour = new SK_Locations();
        rssi_thirtyfour.client = spaClient.spackenkillClient;
        rssi_thirtyfour.floor = 2;
        rssi_thirtyfour.beaconRssi = 34;
        rssi_thirtyfour.longitude = 41.1006;
        rssi_thirtyfour.latitude = 73.2200;
        skil.saveLocations(rssi_thirtyfour);

        SK_Locations rssi_thirtyfive = new SK_Locations();
        rssi_thirtyfive.client = spaClient.spackenkillClient;
        rssi_thirtyfive.floor = 2;
        rssi_thirtyfive.beaconRssi = 35;
        rssi_thirtyfive.longitude = 42.3481;
        rssi_thirtyfive.latitude = 73.2533;
        skil.saveLocations(rssi_thirtyfive);

        SK_Locations rssi_thirtysix = new SK_Locations();
        rssi_thirtysix.client = spaClient.spackenkillClient;
        rssi_thirtysix.floor = 2;
        rssi_thirtysix.beaconRssi = 36;
        rssi_thirtysix.longitude = 41.5437;
        rssi_thirtysix.latitude = 73.2948;
        skil.saveLocations(rssi_thirtysix);

        SK_Locations rssi_thirtyseven = new SK_Locations();
        rssi_thirtyseven.client = spaClient.spackenkillClient;
        rssi_thirtyseven.floor = 2;
        rssi_thirtyseven.beaconRssi = 37;
        rssi_thirtyseven.longitude = 41.1209;
        rssi_thirtyseven.latitude = 74.5674;
        skil.saveLocations(rssi_thirtyseven);

        SK_Locations rssi_thirtyeight = new SK_Locations();
        rssi_thirtyeight.client = spaClient.spackenkillClient;
        rssi_thirtyeight.floor = 2;
        rssi_thirtyeight.beaconRssi = 38;
        rssi_thirtyeight.longitude = 44.0327;
        rssi_thirtyeight.latitude = 73.6757;
        skil.saveLocations(rssi_thirtyeight);

        SK_Locations rssi_thirtynine = new SK_Locations();
        rssi_thirtynine.client = spaClient.spackenkillClient;
        rssi_thirtynine.floor = 2;
        rssi_thirtynine.beaconRssi = 39;
        rssi_thirtynine.longitude = 43.2104;
        rssi_thirtynine.latitude = 73.6007;
        skil.saveLocations(rssi_thirtynine);

        /*
            ####################################################################
            ###############Insert Visitors every 5-10 seconds###################
            ####################################################################
         */
        SK_Visit wferrell2 = new SK_Visit();
        wferrell2.name = will2.fName + " " + will2.lName;
        wferrell2.streetAddress = will2.numAdd + " " + will2.streetAdd;
        wferrell2.city = will2.city;
        wferrell2.state = will2.state;
        wferrell2.personalIdNum = will2.personalIdNum;
        wferrell2.checkStatus = SK_CheckStatus.CheckedIn;
        wferrell2.destination = SK_Destination.Principal;
        wferrell2.currentUserId = will2.id;
        skcis.saveVisit(wferrell2);

        Thread.sleep(5000);

        SK_Visit bfantana = new SK_Visit();
        bfantana.name = brian.fName + " " + brian.lName;
        bfantana.streetAddress = brian.numAdd + " " + brian.streetAdd;
        bfantana.city = brian.city;
        bfantana.state = brian.state;
        bfantana.personalIdNum = brian.personalIdNum;
        bfantana.checkStatus = SK_CheckStatus.CheckedIn;
        bfantana.destination = SK_Destination.Music;
        bfantana.currentUserId = brian.id;
        skcis.saveVisit(bfantana);

        Thread.sleep(5000);

        SK_Visit rreyes = new SK_Visit();
        rreyes.name = rorie.fName + " " + rorie.lName;
        rreyes.streetAddress = rorie.numAdd + " " + rorie.streetAdd;
        rreyes.city = rorie.city;
        rreyes.state = rorie.state;
        rreyes.personalIdNum = rorie.personalIdNum;
        rreyes.checkStatus = SK_CheckStatus.JustArrived;
        rreyes.destination = SK_Destination.History;
        rreyes.currentUserId = rorie.id;
        skcis.saveVisit(rreyes);

        /*
            Running queries based on project
         */

        Thread.sleep(3000);

        /*
            ##############################################
            ##########Update the Check In Status##########
            ##############################################
         */

        List<SK_Visit> visit02 = skcis.getVisitByCheckStatus(SK_CheckStatus.JustArrived);
        for(SK_Visit visit: visit02) {
            // see if there should be a visit and get the visit
            if(visit.currentUserId >= 0) {
                visit.currentUser = skiu.getUsersById(visit.currentUserId);
            }

            String users = (visit.currentUser != null) ? visit.currentUser.fName : "no check in";

            System.out.println("Check In Status: " + users + " - " + visit.checkStatus);
        }

        // Wait a few seconds
        Thread.sleep(6000);

        // change all Just Arrived Check In Status to CheckedIn
        for (SK_Visit visit: visit02) {
            visit.checkStatus = SK_CheckStatus.CheckedIn;
            skcis.saveVisit(visit);
        }

        List<SK_Visit> visit03 = skcis.getVisitByCheckStatus(SK_CheckStatus.CheckedIn);
        for(SK_Visit visit: visit03) {
            // see if there should be a visit and get the visit
            if(visit.currentUserId >= 0) {
                visit.currentUser = skiu.getUsersById(visit.currentUserId);
            }

            String users = (visit.currentUser != null) ? visit.currentUser.fName : "no check in";

            System.out.println("Check In Status: " + users + " - " + visit.checkStatus);
        }

        List<SK_Visit> visit02new = skcis.getVisitByCheckStatus(SK_CheckStatus.CheckedIn);
        visit02new.get(2).checkStatus = SK_CheckStatus.CheckedOutCompleted;
        skcis.saveVisit(visit02new.get(2));

        // Wait a few seconds
        Thread.sleep(6000);

        System.out.println("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        // re-run the query and output the results again, this time with history so we can see the change!
        List<FdfEntity<SK_Visit>> visit02WithHistory = skcis.getVisitByCheckStatusWithHistory(SK_CheckStatus.CheckedOutCompleted);
        for(FdfEntity<SK_Visit> visitWithHistory: visit02WithHistory) {
            // first output the visiting current status
            System.out.println("Check In Status: " + visitWithHistory.current.name + " - " +
                    visitWithHistory.current.checkStatus);

            System.out.println("----- History -----");
            // Now show the check in history records
            for (SK_Visit visitHistory: visitWithHistory.history) {
                System.out.println("Start time: " + visitHistory.arsd + " End time: " + visitHistory.ared +
                        " check in status: " + visitHistory.checkStatus + " for " + visitHistory.currentUser);
            }
            System.out.println("______________________");
        }

        /*
            #########################################
            Step 4: The system should provide
            the following ouput when run
            #########################################
         */
        // 4a) Query for tenants/client, show the name of the tenant
        // Arlington Tenant
        Arlington_Client arClient = cac.customClientQuery(Client_schools.Arlington_High_School.toString()).current;
        System.out.println("################# Query Tenant Name #################");
        System.out.println("Tenant Name" + "               |      Admin             |      Floors");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arClient.arlingtonClient + "     |     " + arClient.omniAdminRole + "     |     " + arClient.numFloors);

        // Spackenkill Tenant
        Spackenkill_Client spClient = csc.customClientQuery(Client_schools.Spackenkill_High_School.toString()).current;
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(spClient.spackenkillClient + "   |     " + spClient.omniAdminRole + "      |     " + spClient.numFloors);

        // 4b) Query for all users for each tenant show the output of queries individually for the tenant (tenant2 users
        // should not show in tenant1 query, etc)
        /*
            ########################
            Arlington Tenant
            ########################
        */

        Users arUserMS = iu.customUserQuery("RS2342-32123").current;
        Users arUserJH = iu.customUserQuery("RS9825-69157").current;
        Users arUserDS = iu.customUserQuery("RS7453-65129").current;
        Users arUserAB = iu.customUserQuery("RS3645-10947").current;
        Users arUserPH = iu.customUserQuery("RS8532-82547").current;
        Users arUserAM = iu.customUserQuery("RS3492-71946").current;
        Users arUserKM = iu.customUserQuery("RS6284-40954").current;
        Users arUserCB = iu.customUserQuery("RS8351-30954").current;
        Users arUserWF = iu.customUserQuery("RS7310-71047").current;
        Users arUserOM = iu.customUserQuery("RS6384-48321").current;

        System.out.print("\n");
        System.out.println("################# Query Users from Arlington High School #################");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("User" + "           |");
        System.out.println(arUserMS.fName + " " + arUserMS.lName);
        System.out.println(arUserJH.fName + " " + arUserJH.lName);
        System.out.println(arUserDS.fName + " " + arUserDS.lName);
        System.out.println(arUserAB.fName + " " + arUserAB.lName);
        System.out.println(arUserPH.fName + " " + arUserPH.lName);
        System.out.println(arUserAM.fName + " " + arUserAM.lName);
        System.out.println(arUserKM.fName + " " + arUserKM.lName);
        System.out.println(arUserCB.fName + " " + arUserCB.lName);
        System.out.println(arUserWF.fName + " " + arUserWF.lName);
        System.out.println(arUserOM.fName + " " + arUserOM.lName);

        /*
            ########################
            Spackenkill Tenant
            ########################
        */

        SK_Users skUserWF = skiu.customUserQuery("RS7310-71047").current;
        SK_Users skUserBF = skiu.customUserQuery("RS4525-69102").current;
        SK_Users skUserVC = skiu.customUserQuery("RS8546-88747").current;
        SK_Users skUserCK = skiu.customUserQuery("RS2853-84129").current;
        SK_Users skUserAM = skiu.customUserQuery("RS3140-90944").current;
        SK_Users skUserFV = skiu.customUserQuery("RS6482-70346").current;
        SK_Users skUserBT = skiu.customUserQuery("RS6544-09587").current;
        SK_Users skUserSR = skiu.customUserQuery("RS7358-31974").current;
        SK_Users skUserGP = skiu.customUserQuery("RS5810-24047").current;
        SK_Users skUserRR = skiu.customUserQuery("RS6387-48333").current;

        System.out.print("\n");
        System.out.println("################# Query Users from Spackenkill High School #################");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("User" + "           |");
        System.out.println(skUserWF.fName + " " + skUserWF.lName);
        System.out.println(skUserBF.fName + " " + skUserBF.lName);
        System.out.println(skUserVC.fName + " " + skUserVC.lName);
        System.out.println(skUserCK.fName + " " + skUserCK.lName);
        System.out.println(skUserAM.fName + " " + skUserAM.lName);
        System.out.println(skUserFV.fName + " " + skUserFV.lName);
        System.out.println(skUserBT.fName + " " + skUserBT.lName);
        System.out.println(skUserSR.fName + " " + skUserSR.lName);
        System.out.println(skUserGP.fName + " " + skUserGP.lName);
        System.out.println(skUserRR.fName + " " + skUserRR.lName);

        // 4c) Query for 2 users and their associated roles, output the id number of the user (or key), their firstName,
        // lastName and a list of the roles they have associated with them.

        System.out.print("\n");
        System.out.println("################# Query 2 Users and the following attributes #################\n" +
                            "################# ID | First Name | Last Name | Roles #################");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("ID   |   First Name    |   Last Name |   Roles   ");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skUserBF.id + "    |   " + skUserBF.fName + "         |   " + skUserBF.lName + "   |   " + skUserBF.systemRoleAssmt);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arUserCB.id + "    |   " + arUserCB.fName + "         |   " + arUserCB.lName + "   |   " + arUserCB.systemRoleAssmt);

        // 4d) Query for the 5 users that were modified in requirement (3.c.iii) output the original and modified records
        // including the time range represented by each (between what times was each version valid?)

        // 4e) Query for all checkins by tenant (at least 2 result sets) display the full name of the user checked in,
        // their destination, the time they checked in and the current status of their check in

        /*
            ########################
            Arlington Check In
            ########################
         */

        Visit arVisitMS = cis.customVisitQuery("RS2342-32123").current;
        Visit arVisitJH = cis.customVisitQuery("RS9825-69157").current;
        Visit arVisitDS = cis.customVisitQuery("RS7453-65129").current;
        Visit arVisitAB = cis.customVisitQuery("RS3645-10947").current;
        Visit arVisitWF = cis.customVisitQuery("RS7310-71047").current;

        System.out.print("\n");
        System.out.println("############### Query Current Check In Status from Arlington High School ###############");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Name           |     Destination     |     Time                      |     Check In Status");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arVisitMS.name + "  |     " + arVisitMS.destination + "       |     " + arVisitMS.arsd +
                "     |     " + arVisitMS.checkStatus);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arVisitJH.name + "    |     " + arVisitJH.destination + "             |     " + arVisitJH.arsd +
                "     |     " + arVisitJH.checkStatus);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arVisitDS.name + " |     " + arVisitDS.destination + "         |     " + arVisitDS.arsd +
                "     |     " + arVisitDS.checkStatus);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arVisitAB.name + "   |     " + arVisitAB.destination + "           |     " + arVisitAB.arsd +
                "     |     " + arVisitAB.checkStatus);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arVisitWF.name + "   |     " + arVisitWF.destination + "             |     " + arVisitWF.arsd +
                "     |     " + arVisitWF.checkStatus);

        /*
            ########################
            Spackenkill Check In
            ########################
         */

        SK_Visit skVisitWF = skcis.customVisitQuery("RS7310-71047").current;
        SK_Visit skVisitBF = skcis.customVisitQuery("RS4525-69102").current;
        SK_Visit skVisitRR = skcis.customVisitQuery("RS6387-48333").current;

        System.out.print("\n");
        System.out.println("############### Query Current Check In Status from Spackenkill High School ###############");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Name           |     Destination     |     Time                      |     Check In Status");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skVisitWF.name + "   |     " + skVisitWF.destination + "       |     " + skVisitWF.arsd +
                "     |     " + skVisitWF.checkStatus);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skVisitBF.name + "  |     " + skVisitBF.destination + "           |     " + skVisitBF.arsd +
                "     |     " + skVisitBF.checkStatus);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skVisitRR.name + "    |     " + skVisitRR.destination + "         |     " + skVisitRR.arsd +
                "     |     " + skVisitRR.checkStatus);

        // 4f) For 1 checkin that has gone through the complete cycle (all the way to completed / checked out) show the
        // history of the event and show the times every change to the status happened.

        // 4g) Query all the beacons located on floors in buildings for tenants. Output should first be grouped by
        // tenant. For each tenant the locations (buildings) should be shown, then the floors for each building and
        // finally the beacons located on each floor.

        /*
            ########################
            Arlington Beacon Data
            ########################
         */

        Locations arLocations_zero = il.customLocationQuery("0").current;
        Locations arLocations_one = il.customLocationQuery("1").current;
        Locations arLocations_two = il.customLocationQuery("2").current;
        Locations arLocations_three = il.customLocationQuery("3").current;
        Locations arLocations_four = il.customLocationQuery("4").current;
        Locations arLocations_five = il.customLocationQuery("5").current;
        Locations arLocations_six = il.customLocationQuery("6").current;
        Locations arLocations_seven = il.customLocationQuery("7").current;
        Locations arLocations_eight = il.customLocationQuery("8").current;
        Locations arLocations_nine = il.customLocationQuery("9").current;
        Locations arLocations_ten = il.customLocationQuery("10").current;
        Locations arLocations_eleven = il.customLocationQuery("11").current;
        Locations arLocations_twelve = il.customLocationQuery("12").current;
        Locations arLocations_thirteen = il.customLocationQuery("13").current;
        Locations arLocations_fourteen = il.customLocationQuery("14").current;
        Locations arLocations_fifteen = il.customLocationQuery("15").current;
        Locations arLocations_sixteen = il.customLocationQuery("16").current;
        Locations arLocations_seventeen = il.customLocationQuery("17").current;
        Locations arLocations_eighteen = il.customLocationQuery("18").current;
        Locations arLocations_nineteen = il.customLocationQuery("19").current;

        System.out.print("\n");
        System.out.println("############### Query Beacon information from Arlington High School ###############");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Location                  |     Floor     |     Beacon RSSI");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_zero.client + "     |     " + arLocations_zero.floor + "         |     " +
                arLocations_zero.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_one.client + "     |     " + arLocations_one.floor + "         |     " +
                arLocations_one.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_two.client + "     |     " + arLocations_two.floor + "         |     " +
                arLocations_two.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_three.client + "     |     " + arLocations_three.floor + "         |     " +
                arLocations_three.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_four.client + "     |     " + arLocations_four.floor + "         |     " +
                arLocations_four.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_five.client + "     |     " + arLocations_five.floor + "         |     " +
                arLocations_five.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_six.client + "     |     " + arLocations_six.floor + "         |     " +
                arLocations_six.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_seven.client + "     |     " + arLocations_seven.floor + "         |     " +
                arLocations_seven.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_eight.client + "     |     " + arLocations_eight.floor + "         |     " +
                arLocations_eight.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_nine.client + "     |     " + arLocations_nine.floor + "         |     " +
                arLocations_nine.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_ten.client + "     |     " + arLocations_ten.floor + "         |     " +
                arLocations_ten.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_eleven.client + "     |     " + arLocations_eleven.floor + "         |     " +
                arLocations_eleven.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_twelve.client + "     |     " + arLocations_twelve.floor + "         |     " +
                arLocations_twelve.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_thirteen.client + "     |     " + arLocations_thirteen.floor + "         |     " +
                arLocations_thirteen.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_fourteen.client + "     |     " + arLocations_fourteen.floor + "         |     " +
                arLocations_fourteen.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_fifteen.client + "     |     " + arLocations_fifteen.floor + "         |     " +
                arLocations_fifteen.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_sixteen.client + "     |     " + arLocations_sixteen.floor + "         |     " +
                arLocations_sixteen.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_seventeen.client + "     |     " + arLocations_seventeen.floor + "         |     " +
                arLocations_seventeen.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_eighteen.client + "     |     " + arLocations_eighteen.floor + "         |     " +
                arLocations_eighteen.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(arLocations_nineteen.client + "     |     " + arLocations_nineteen.floor + "         |     " +
                arLocations_nineteen.beaconRssi);

        /*
            ########################
            Spackenkill Beacon Data
            ########################
         */

        SK_Locations skLocations_twenty = skil.customLocationQuery("20").current;
        SK_Locations skLocations_twentyone = skil.customLocationQuery("21").current;
        SK_Locations skLocations_twentytwo = skil.customLocationQuery("22").current;
        SK_Locations skLocations_twentythree = skil.customLocationQuery("23").current;
        SK_Locations skLocations_twentyfour = skil.customLocationQuery("24").current;
        SK_Locations skLocations_twentyfive = skil.customLocationQuery("25").current;
        SK_Locations skLocations_twentysix = skil.customLocationQuery("26").current;
        SK_Locations skLocations_twentyseven = skil.customLocationQuery("27").current;
        SK_Locations skLocations_twentyeight = skil.customLocationQuery("28").current;
        SK_Locations skLocations_twentynine = skil.customLocationQuery("29").current;
        SK_Locations skLocations_thirty = skil.customLocationQuery("30").current;
        SK_Locations skLocations_thirtyone = skil.customLocationQuery("31").current;
        SK_Locations skLocations_thirtytwo = skil.customLocationQuery("32").current;
        SK_Locations skLocations_thirtythree = skil.customLocationQuery("33").current;
        SK_Locations skLocations_thirtyfour = skil.customLocationQuery("34").current;
        SK_Locations skLocations_thirtyfive = skil.customLocationQuery("35").current;
        SK_Locations skLocations_thirtysix = skil.customLocationQuery("36").current;
        SK_Locations skLocations_thirtyseven = skil.customLocationQuery("37").current;
        SK_Locations skLocations_thirtyeight = skil.customLocationQuery("38").current;
        SK_Locations skLocations_thirtynine = skil.customLocationQuery("39").current;

        System.out.print("\n");
        System.out.println("############### Query Beacon information from Spackenkill High School ###############");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Location                    |     Floor     |     Beacon RSSI");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_twenty.client + "     |     " + skLocations_twenty.floor + "         |     " +
                skLocations_twenty.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_twentyone.client + "     |     " + skLocations_twentyone.floor + "         |     " +
                skLocations_twentyone.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_twentytwo.client + "     |     " + skLocations_twentytwo.floor + "         |     " +
                skLocations_twentytwo.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_twentythree.client + "     |     " + skLocations_twentythree.floor + "         |     " +
                skLocations_twentythree.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_twentyfour.client + "     |     " + skLocations_twentyfour.floor + "         |     " +
                skLocations_twentyfour.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_twentyfive.client + "     |     " + skLocations_twentyfive.floor + "         |     " +
                skLocations_twentyfive.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_twentysix.client + "     |     " + skLocations_twentysix.floor + "         |     " +
                skLocations_twentysix.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_twentyseven.client + "     |     " + skLocations_twentyseven.floor + "         |     " +
                skLocations_twentyseven.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_twentyeight.client + "     |     " + skLocations_twentyeight.floor + "         |     " +
                skLocations_twentyeight.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_twentynine.client + "     |     " + skLocations_twentynine.floor + "         |     " +
                skLocations_twentynine.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_thirty.client + "     |     " + skLocations_thirty.floor + "         |     " +
                skLocations_thirty.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_thirtyone.client + "     |     " + skLocations_thirtyone.floor + "         |     " +
                skLocations_thirtyone.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_thirtytwo.client + "     |     " + skLocations_thirtytwo.floor + "         |     " +
                skLocations_thirtytwo.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_thirtythree.client + "     |     " + skLocations_thirtythree.floor + "         |     " +
                skLocations_thirtythree.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_thirtyfour.client + "     |     " + skLocations_thirtyfour.floor + "         |     " +
                skLocations_thirtyfour.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_thirtyfive.client + "     |     " + skLocations_thirtyfive.floor + "         |     " +
                skLocations_thirtyfive.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_thirtysix.client + "     |     " + skLocations_thirtysix.floor + "         |     " +
                skLocations_thirtysix.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_thirtyseven.client + "     |     " + skLocations_thirtyseven.floor + "         |     " +
                skLocations_thirtyseven.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_thirtyeight.client + "     |     " + skLocations_thirtyeight.floor + "         |     " +
                skLocations_thirtyeight.beaconRssi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(skLocations_thirtynine.client + "     |     " + skLocations_thirtynine.floor + "         |     " +
                skLocations_thirtynine.beaconRssi);
    }




}
