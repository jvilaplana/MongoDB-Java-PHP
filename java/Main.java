package com.jordiv;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.Block;
import static com.mongodb.client.model.Filters.*;


public class Main {

    public static void main(String[] args) {

        MongoClientURI uri = new MongoClientURI(
                "mongodb://<usuari>:<contrasenya>@cursnosql-shard-00-00.saj8u.mongodb.net:27017,cursnosql-shard-00-01.saj8u.mongodb.net:27017,cursnosql-shard-00-02.saj8u.mongodb.net:27017/<dbname>?ssl=true&replicaSet=atlas-m3kyo0-shard-0&authSource=admin&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("sample_mflix");

        System.out.println("Número total de documents a 'movies': " + database.getCollection("movies").count() + "\n");

        Document myDoc = database.getCollection("movies").find().first();
        System.out.println(myDoc.toJson() + "\n\n------\n");

        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };

        database.getCollection("movies").find(lte("year", 1893)).forEach(printBlock);
    }
}
