package com.fdflib.example.spackenkill_tables;

import com.fdflib.example.clients.Client_schools;
import com.fdflib.model.state.CommonState;

public class SK_Locations extends CommonState {
    public Client_schools client = null;
    public Integer floor = 0;
    public Integer beaconRssi = 0;
    public Double longitude = 0.0;
    public Double latitude = 0.0;

    public SK_Locations() {super();}

}
