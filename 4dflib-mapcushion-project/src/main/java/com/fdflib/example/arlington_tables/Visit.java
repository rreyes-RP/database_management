package com.fdflib.example.arlington_tables;

import com.fdflib.annotation.FdfIgnore;
import com.fdflib.model.state.CommonState;

public class Visit extends CommonState {
    public String name = "";
    public String streetAddress = "";
    public String city = "";
    public String state = "";
    public String personalIdNum = "";
    public CheckStatus checkStatus = null;
    public Destination destination = null;
    public long currentUserId = -1L;

    @FdfIgnore
    public Users currentUser = null;

    public Visit() {super();}

}
