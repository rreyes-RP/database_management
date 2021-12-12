package com.fdflib.example.spackenkill_queries;

import com.fdflib.example.arlington_tables.Locations;
import com.fdflib.example.spackenkill_tables.SK_Locations;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.model.util.WhereClause;
import com.fdflib.service.impl.FdfCommonServices;

import java.util.List;


public class SK_InsertLocation extends FdfCommonServices {
    public SK_Locations saveLocations(SK_Locations locations) {
        if(locations != null) {
            return this.save(SK_Locations.class, locations).current;
        }

        return null;
    }

    public SK_Locations getLocationsById(long id) { return getLocationsWithHistoryById(id).current;}

    public FdfEntity<SK_Locations> getLocationsWithHistoryById(long id) {
        FdfEntity<SK_Locations> locations = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            locations = this.getEntityById(SK_Locations.class, id);
        }

        return locations;
    }

    public FdfEntity<SK_Locations> customLocationQuery(String name) {
        FdfEntity<SK_Locations> locations = new FdfEntity<>();

        if(locations != null) {
            WhereClause whereName = new WhereClause();
            whereName.name = "beaconRssi";
            whereName.operator = WhereClause.Operators.EQUAL;
            whereName.value = name;
            whereName.valueDataType = String.class;

            List<SK_Locations> returnedStates = SqlStatement.build().where(whereName).run(SK_Locations.class);
            return manageReturnedEntity(returnedStates);

        }

        return locations;
    }
}
