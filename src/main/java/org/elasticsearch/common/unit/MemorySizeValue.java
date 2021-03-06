/*
 * Licensed to ElasticSearch and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. ElasticSearch licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.common.unit;

import org.elasticsearch.monitor.jvm.JvmInfo;

import static org.elasticsearch.common.unit.ByteSizeValue.parseBytesSizeValue;

/** Utility methods to get memory sizes. */
public enum MemorySizeValue {
    ;

    /** Parse the provided string as a memory size. This method either accepts absolute values such as
     *  <tt>42</tt> (default assumed unit is byte) or <tt>2mb</tt>, or percentages of the heap size: if
     *  the heap is 1G, <tt>10%</tt> will be parsed as <tt>100mb</tt>.  */
    public static ByteSizeValue parseBytesSizeValueOrHeapRatio(String sValue) {
        if (sValue.endsWith("%")) {
            double percent = Double.parseDouble(sValue.substring(0, sValue.length() - 1));
            return new ByteSizeValue((long) ((percent / 100) * JvmInfo.jvmInfo().getMem().getHeapMax().bytes()), ByteSizeUnit.BYTES);
        } else {
            return parseBytesSizeValue(sValue);
        }
    }
}
