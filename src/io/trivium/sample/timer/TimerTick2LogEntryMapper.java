/*
 * Copyright 2016 Jens Walter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.trivium.sample.timer;

import io.trivium.anystore.query.Query;
import io.trivium.extension.Task;
import io.trivium.extension.fact.LogEntry;

import java.util.logging.Level;

public class TimerTick2LogEntryMapper extends Task {
    TimerTick tick = new Query<TimerTick>(){
        {
            targetType = TimerTick.class;
        }
    }.getObject();

    LogEntry entry;

    @Override
    public boolean eval() throws Exception {
        entry = new LogEntry();
        getLogger().log(Level.FINE,getName() + "=>transforming " + tick.timestamp);
        entry.message = String.valueOf(tick.timestamp);
        return true;
    }
}
