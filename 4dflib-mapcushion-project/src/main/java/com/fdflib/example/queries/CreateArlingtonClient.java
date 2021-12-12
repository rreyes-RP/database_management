package com.fdflib.example.queries;

import com.fdflib.example.arlington_tables.Arlington_Client;
import com.fdflib.example.clients.Client_schools;
import com.fdflib.model.entity.FdfEntity;
import com.fdflib.model.util.SqlStatement;
import com.fdflib.model.util.WhereClause;
import com.fdflib.service.impl.FdfCommonServices;

import java.util.List;

public class CreateArlingtonClient extends FdfCommonServices {
    public Arlington_Client saveClient(Arlington_Client client) {
        if(client != null) {
            return this.save(Arlington_Client.class, client).current;
        }

        return null;
    }

    public Arlington_Client getClientById(long id) {
        return getClientWithHistoryById(id).current;
    }

    public FdfEntity<Arlington_Client> getClientWithHistoryById(long id) {
        FdfEntity<Arlington_Client> client = new FdfEntity<>();

        // get the test
        if(id >= 0) {
            client = this.getEntityById(Arlington_Client.class, id);
        }

        return client;
    }

    public FdfEntity<Arlington_Client> customClientQuery(String name) {
        FdfEntity<Arlington_Client> arClient = new FdfEntity<>();
        if(arClient != null) {
            WhereClause whereClient = new WhereClause();
            whereClient.name = "arlingtonClient";
            whereClient.operator = WhereClause.Operators.EQUAL;
            whereClient.value = name;
            whereClient.valueDataType = String.class;

            List<Arlington_Client> returnedStates = SqlStatement.build().where(whereClient).run(Arlington_Client.class);

            // create a List of entities

            return manageReturnedEntity(returnedStates);
        }

        return arClient;
    }
}
