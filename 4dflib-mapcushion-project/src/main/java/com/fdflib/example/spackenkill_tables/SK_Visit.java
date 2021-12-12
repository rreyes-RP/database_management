package com.fdflib.example.spackenkill_tables;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class SK_Visit extends CommonState {
    public String name = "";
    public String streetAddress = "";
    public String city = "";
    public String state = "";
    public String personalIdNum = "";
    public SK_CheckStatus checkStatus = null;
    public SK_Destination destination = null;
    public long currentUserId = -1L;

    @FdfIgnore
    public SK_Users currentUser = null;

    public SK_Visit() {super();}

}
