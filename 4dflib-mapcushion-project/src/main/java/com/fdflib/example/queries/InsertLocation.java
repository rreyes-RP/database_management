package com.fdflib.example.queries;

import com.fdflib.example.arlington_tables.Locations;
import com.fdflib.example.spackenkill_tables.SK_Visit;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.model.util.WhereClause;
import com.fdflib.service.impl.FdfCommonServices;

import java.util.List;


public class InsertLocation extends FdfCommonServices {
    public Locations saveLocations(Locations locations) {
        if(locations != null) {
            return this.save(Locations.class, locations).current;
        }

        return null;
    }

    public Locations getLocationsById(long id) { return getLocationsWithHistoryById(id).current;}

    public FdfEntity<Locations> getLocationsWithHistoryById(long id) {
        FdfEntity<Locations> locations = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            locations = this.getEntityById(Locations.class, id);
        }

        return locations;
    }

    public FdfEntity<Locations> customLocationQuery(String name) {
        FdfEntity<Locations> locations = new FdfEntity<>();

        if(locations != null) {
            WhereClause whereName = new WhereClause();
            whereName.name = "beaconRssi";
            whereName.operator = WhereClause.Operators.EQUAL;
            whereName.value = name;
            whereName.valueDataType = String.class;

            List<Locations> returnedStates = SqlStatement.build().where(whereName).run(Locations.class);
            return manageReturnedEntity(returnedStates);

        }

        return locations;
    }
}
