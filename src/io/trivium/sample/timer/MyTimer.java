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

import io.trivium.extension.Binding;
import io.trivium.extension.BindingState;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

public class MyTimer extends Binding {
    Timer t = null;
    int interval_ms = 10000;

    @Override
    protected void start() {
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerTick tick = new TimerTick();
                tick.timestamp=System.currentTimeMillis();
                getLogger().log(Level.FINE,getName() + "=>tick " + tick.timestamp);
                emit(tick);
            }
        },0,interval_ms);
        setState(BindingState.running);
    }

    @Override
    protected void stop() {
        if(t!=null){
            t.cancel();
            t=null;
        }
        setState(BindingState.stopped);
    }
}
