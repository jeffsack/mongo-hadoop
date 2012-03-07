/*
 * Copyright 2011 10gen Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.hadoop;

// Mongo

import com.mongodb.*;
import com.mongodb.hadoop.input.*;
import com.mongodb.hadoop.util.*;
import org.apache.commons.logging.*;
import org.apache.hadoop.mapred.*;
import org.bson.*;

import java.io.IOException;

// Commons
// Hadoop
// Java

public class MongoInputFormat implements InputFormat<ObjectHolder<Object>, BSONObject> {

    @Override
    public InputSplit[] getSplits(JobConf hadoopConfiguration, int i) throws IOException {
        final MongoConfig conf = new MongoConfig(hadoopConfiguration);
        return MongoSplitter.calculateSplitsArray(conf);
    }

    @Override
    public RecordReader<ObjectHolder<Object>, BSONObject> getRecordReader(InputSplit split, JobConf entries, Reporter reporter) throws IOException {
        if (!(split instanceof MongoInputSplit))
            throw new IllegalStateException("Creation of a new RecordReader requires a MongoInputSplit instance.");

        final MongoInputSplit mis = (MongoInputSplit) split;

        return new com.mongodb.hadoop.input.MongoRecordReader(mis);
    }

    private static final Log LOG = LogFactory.getLog(MongoInputFormat.class);
}
