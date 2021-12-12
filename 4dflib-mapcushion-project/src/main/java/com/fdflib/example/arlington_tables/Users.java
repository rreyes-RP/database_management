package com.fdflib.example.arlington_tables;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.example.clients.Client_schools;
import com.fdflib.model.state.CommonState;

/*
 * Created by Rorie Reyes on 12/5/21
 */

public class Users extends CommonState {
    public Client_schools arClient = Client_schools.Arlington_High_School;
    public String lName = "";
    public String fName = "";
    public String email = "";
    public String password = "";
    public Integer personalRankId = 0;
    public String personalIdNum = "";
    public SysRole systemRoleAssmt = null;
    public String color = "";
    public Integer numAdd = 0;
    public String streetAdd = "";
    public String city = "";
    public String state = "";
    public String country = "";
    public String idDateIssue = "";
    public String idDateExp = "";
    public String dob = "";
    public String gender = "";
    public String eyeCol = "";
    public String height = "";
    public String weight = "";
    public long currentSysRolesId = -1L;

    @FdfIgnore
    public SystemRole currentSysRoles = null;

    public Users() {super();}
}
