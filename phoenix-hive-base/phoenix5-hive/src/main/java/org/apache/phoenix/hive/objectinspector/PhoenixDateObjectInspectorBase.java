/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.phoenix.hive.objectinspector;

import org.apache.hadoop.hive.common.type.Date;
import org.apache.hadoop.hive.serde2.io.DateWritableV2;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DateObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;

/**
 * ObjectInspector for date type
 */

public class PhoenixDateObjectInspectorBase extends AbstractPhoenixObjectInspector<DateWritableV2>
        implements DateObjectInspector {

    public PhoenixDateObjectInspectorBase() {
        super(TypeInfoFactory.dateTypeInfo);
    }

    @Override
    public Object copyObject(Object o) {
        return o == null ? null : java.sql.Date.valueOf(o.toString());
    }

    @Override
    public Date getPrimitiveJavaObject(Object o) {
        if (o == null) {
            return null;
        }
        return Date.valueOf(((java.sql.Date) o).toString());
    }

    @Override
    public DateWritableV2 getPrimitiveWritableObject(Object o) {
        DateWritableV2 value = null;

        if (o != null) {
            try {
                value = new DateWritableV2(getPrimitiveJavaObject(o));
            } catch (Exception e) {
                logExceptionMessage(o, "DATE");
                value = new DateWritableV2();
            }
        }

        return value;
    }
}
