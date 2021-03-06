/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.apm.collector.storage.h2.dao.alarm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.skywalking.apm.collector.client.h2.H2Client;
import org.apache.skywalking.apm.collector.storage.h2.base.dao.AbstractPersistenceH2DAO;
import org.apache.skywalking.apm.collector.storage.table.alarm.ApplicationAlarmList;
import org.apache.skywalking.apm.collector.storage.table.alarm.ApplicationAlarmListTable;

/**
 * @author peng-yongsheng
 */
public abstract class AbstractApplicationAlarmListH2PersistenceDAO extends AbstractPersistenceH2DAO<ApplicationAlarmList> {

    AbstractApplicationAlarmListH2PersistenceDAO(H2Client client) {
        super(client);
    }

    @Override protected final ApplicationAlarmList h2DataToStreamData(ResultSet resultSet) throws SQLException {
        ApplicationAlarmList applicationAlarmList = new ApplicationAlarmList();
        applicationAlarmList.setId(resultSet.getString(ApplicationAlarmListTable.ID.getName()));
        applicationAlarmList.setMetricId(resultSet.getString(ApplicationAlarmListTable.METRIC_ID.getName()));
        applicationAlarmList.setSourceValue(resultSet.getInt(ApplicationAlarmListTable.SOURCE_VALUE.getName()));

        applicationAlarmList.setAlarmType(resultSet.getInt(ApplicationAlarmListTable.ALARM_TYPE.getName()));

        applicationAlarmList.setApplicationId(resultSet.getInt(ApplicationAlarmListTable.APPLICATION_ID.getName()));

        applicationAlarmList.setTimeBucket(resultSet.getLong(ApplicationAlarmListTable.TIME_BUCKET.getName()));
        applicationAlarmList.setAlarmContent(resultSet.getString(ApplicationAlarmListTable.ALARM_CONTENT.getName()));

        return applicationAlarmList;
    }

    @Override protected final Map<String, Object> streamDataToH2Data(ApplicationAlarmList streamData) {
        Map<String, Object> target = new HashMap<>();
        target.put(ApplicationAlarmListTable.ID.getName(), streamData.getId());
        target.put(ApplicationAlarmListTable.METRIC_ID.getName(), streamData.getMetricId());
        target.put(ApplicationAlarmListTable.SOURCE_VALUE.getName(), streamData.getSourceValue());

        target.put(ApplicationAlarmListTable.ALARM_TYPE.getName(), streamData.getAlarmType());

        target.put(ApplicationAlarmListTable.APPLICATION_ID.getName(), streamData.getApplicationId());

        target.put(ApplicationAlarmListTable.TIME_BUCKET.getName(), streamData.getTimeBucket());
        target.put(ApplicationAlarmListTable.ALARM_CONTENT.getName(), streamData.getAlarmContent());

        return target;
    }
}
