package com.app.util;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.AbstractDataStore;
import com.google.cloud.datastore.Blob;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DSFactory implements DataStoreFactory {

    private final Datastore datastore;

    public DSFactory() {
        this.datastore = DatastoreOptions.getDefaultInstance().getService();
    }

    @Override
    public <V extends java.io.Serializable> DataStore<V> getDataStore(String id) throws IOException {
        return new DatastoreDataStore<>(this, id);
    }

    Datastore getDatastore() {
        return datastore;
    }

    static class DatastoreDataStore<V extends java.io.Serializable> extends AbstractDataStore<V> {

        private final Datastore datastore;

        protected DatastoreDataStore(DSFactory dataStoreFactory, String id) {
            super(dataStoreFactory, id);
            this.datastore = dataStoreFactory.getDatastore();
        }

        @Override
        public Set<String> keySet() throws IOException {
            Set<String> keys = new HashSet<>();
            Query<Entity> query = Query.newEntityQueryBuilder().setKind(getId()).build();
            QueryResults<Entity> results = datastore.run(query);
            while (results.hasNext()) {
                keys.add(results.next().getKey().getName());
            }
            return keys;
        }

        @Override
        public Collection<V> values() throws IOException {
            Set<V> values = new HashSet<>();
            Query<Entity> query = Query.newEntityQueryBuilder().setKind(getId()).build();
            QueryResults<Entity> results = datastore.run(query);
            while (results.hasNext()) {
                try{ 
                    values.add(serializeToJavaObject(results.next().getBlob("value").toByteArray()));
                    
                }
                catch(ClassNotFoundException e){
                    continue;
                }
               
            }
            return values;
        }

        @Override
        public V get(String key) throws IOException {
            Key datastoreKey = datastore.newKeyFactory().setKind(getId()).newKey(key);
            Entity entity = datastore.get(datastoreKey);
            if (entity == null) {
                return null;
            }
            try{

                return serializeToJavaObject(entity.getBlob("value").toByteArray());
            }
            catch(ClassNotFoundException e){
               return null;
            }
            
        }

        @Override
        public DataStore<V> set(String key, V value) throws IOException {
            Key datastoreKey = datastore.newKeyFactory().setKind(getId()).newKey(key);
            Entity entity = Entity.newBuilder(datastoreKey)
                    .set("value", Blob.copyFrom(serializeToBytes(value)))
                    .build();
            datastore.put(entity);
            return this;
        }

        @Override
        public DataStore<V> clear() throws IOException {
            Query<Entity> query = Query.newEntityQueryBuilder().setKind(getId()).build();
            QueryResults<Entity> results = datastore.run(query);
            while (results.hasNext()) {
                datastore.delete(results.next().getKey());
            }
            return this;
        }

        @Override
        public DataStore<V> delete(String key) throws IOException {
            Key datastoreKey = datastore.newKeyFactory().setKind(getId()).newKey(key);
            datastore.delete(datastoreKey);
            return this;
        }

        @Override
        public boolean containsKey(String key) throws IOException {
            return get(key) != null;
        }

        @Override
        public boolean containsValue(V value) throws IOException {
            for (V storedValue : values()) {
                if (storedValue.equals(value)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int size() throws IOException {
            int size = 0;
            Query<Entity> query = Query.newEntityQueryBuilder().setKind(getId()).build();
            QueryResults<Entity> results = datastore.run(query);
            while (results.hasNext()) {
                results.next();
                size++;
            }
            return size;
        }

        private byte[] serializeToBytes(V value) throws IOException {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            oos.close();
            return bos.toByteArray();
        }

        private V serializeToJavaObject(byte[] bytes) throws IOException, ClassNotFoundException {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            V obj = (V) ois.readObject();
            ois.close();
            return obj;
        }
    }
}