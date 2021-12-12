package com.fdflib.example.spackenkill_tables;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

/*
 * Created by Rorie Reyes on 12/5/21
 */

public class SK_Users extends CommonState {
    public String lName = "";
    public String fName = "";
    public String email = "";
    public String password = "";
    public Integer personalRankId = 0;
    public String personalIdNum = "";
    public SK_SysRole systemRoleAssmt = null;
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
    public SK_SystemRole currentSysRoles = null;

    public SK_Users() {super();}
}
