/*
 * Copyright 2010 10gen Inc.
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

import com.mongodb.hadoop.output.*;
import com.mongodb.hadoop.util.*;
import org.apache.commons.logging.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.Progressable;

import java.io.IOException;

// Commons
// Hadoop

public class MongoOutputFormat<K, V> implements OutputFormat<K, V> {

    @Override
    public void checkOutputSpecs(FileSystem fileSystem, JobConf entries) throws IOException {
    }

    /**
     * Get the record writer that points to the output collection.
     */
    @Override
    public RecordWriter<K, V> getRecordWriter(FileSystem fileSystem, JobConf conf, String s, Progressable progressable) throws IOException {
        return new MongoRecordWriter(MongoConfigUtil.getOutputCollection(conf));
    }

    public MongoOutputFormat(){ }

    private static final Log LOG = LogFactory.getLog( MongoOutputFormat.class );
}

