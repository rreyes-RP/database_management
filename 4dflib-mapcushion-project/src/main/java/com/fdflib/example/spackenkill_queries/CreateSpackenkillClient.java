package com.fdflib.example.spackenkill_queries;

import com.fdflib.example.arlington_tables.Arlington_Client;
import com.fdflib.example.spackenkill_tables.Spackenkill_Client;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.model.util.WhereClause;
import com.fdflib.service.impl.FdfCommonServices;

import java.util.List;

public class CreateSpackenkillClient extends FdfCommonServices {
    public Spackenkill_Client saveClient(Spackenkill_Client client) {
        if(client != null) {
            return this.save(Spackenkill_Client.class, client).current;
        }

        return null;
    }

    public Spackenkill_Client getClientById(long id) {
        return getClientWithHistoryById(id).current;
    }

    public FdfEntity<Spackenkill_Client> getClientWithHistoryById(long id) {
        FdfEntity<Spackenkill_Client> client = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            client = this.getEntityById(Spackenkill_Client.class, id);
        }

        return client;
    }

    public FdfEntity<Spackenkill_Client> customClientQuery(String name) {
        FdfEntity<Spackenkill_Client> spClient = new FdfEntity<>();
        if(spClient != null) {
            WhereClause whereClient = new WhereClause();
            whereClient.name = "spackenkillClient";
            whereClient.operator = WhereClause.Operators.EQUAL;
            whereClient.value = name;
            whereClient.valueDataType = String.class;

            List<Spackenkill_Client> returnedStates = SqlStatement.build().where(whereClient).run(Spackenkill_Client.class);

            // create a List of entities

            return manageReturnedEntity(returnedStates);
        }

        return spClient;
    }
}
