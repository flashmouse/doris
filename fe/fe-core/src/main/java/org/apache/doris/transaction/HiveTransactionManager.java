// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.transaction;

import org.apache.doris.common.UserException;
import org.apache.doris.datasource.hive.HMSTransaction;
import org.apache.doris.datasource.hive.HiveMetadataOps;
import org.apache.doris.persist.EditLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HiveTransactionManager implements TransactionManager {

    private final Map<Long, HMSTransaction> transactions = new ConcurrentHashMap<>();
    private final TransactionIdGenerator idGenerator = new TransactionIdGenerator();
    private final HiveMetadataOps ops;

    public HiveTransactionManager(HiveMetadataOps ops) {
        this.ops = ops;
    }

    public Long getNextTransactionId() {
        return idGenerator.getNextTransactionId();
    }

    @Override
    public void setEditLog(EditLog editLog) {
        this.idGenerator.setEditLog(editLog);
    }

    @Override
    public long begin() {
        long id = idGenerator.getNextTransactionId();
        HMSTransaction hiveTransaction = new HMSTransaction(ops);
        transactions.put(id, hiveTransaction);
        return id;
    }

    @Override
    public void commit(long id) throws UserException {
        getTransactionWithException(id).commit();
        transactions.remove(id);
    }

    @Override
    public void rollback(long id) {
        getTransactionWithException(id).rollback();
        transactions.remove(id);
    }

    @Override
    public HMSTransaction getTransaction(long id) {
        return getTransactionWithException(id);
    }

    public HMSTransaction getTransactionWithException(long id) {
        HMSTransaction hiveTransaction = transactions.get(id);
        if (hiveTransaction == null) {
            throw new RuntimeException("Can't find transaction for " + id);
        }
        return hiveTransaction;
    }
}
